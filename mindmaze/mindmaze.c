#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include <signal.h>
#include <dlfcn.h>

// Control Flow Flattening - Estado global
static int cff_state = 0;
static int cff_next_state = 0;

// VM State
typedef struct {
    unsigned char *memory;
    int pc;
    int sp;
    int flags;
    int registers[16];
    int running;
} vm_state_t;

// Anti-debugging flags
static int anti_debug_active = 1;
static int vm_initialized = 0;

// String encryption keys (derived at runtime)
static unsigned char xor_key[32];

// Flag fragments
static char flag_part1[32] = {0};
static char flag_part2[32] = {0};
static char flag_part3[32] = {0};

// FSM states
typedef enum {
    STATE_INIT = 0,
    STATE_PHASE1,
    STATE_PHASE2,
    STATE_PHASE3,
    STATE_VALIDATE,
    STATE_SUCCESS,
    STATE_FAILURE
} fsm_state_t;

static fsm_state_t current_fsm_state = STATE_INIT;

// FSM transition table (obfuscated)
static int fsm_transitions[7][256] = {0};

// VM Instructions
typedef enum {
    VM_NOP = 0,
    VM_LOAD,
    VM_STORE,
    VM_ADD,
    VM_SUB,
    VM_MUL,
    VM_XOR,
    VM_CMP,
    VM_JMP,
    VM_JZ,
    VM_JNZ,
    VM_CALL,
    VM_RET,
    VM_PUSH,
    VM_POP,
    VM_SYS,
    VM_HALT
} vm_instruction_t;

// VM bytecode (embedded in .rodata)
static unsigned char vm_bytecode[] = {
    0x01, 0x00, 0x10, 0x00,  // LOAD 0x10 into reg0
    0x01, 0x01, 0x20, 0x00,  // LOAD 0x20 into reg1
    0x05, 0x02, 0x00, 0x01,  // ADD reg0, reg1 -> reg2
    0x06, 0x02, 0x00, 0x01,  // SUB reg2, reg0 -> reg2
    0x07, 0x02, 0x02, 0x02,  // XOR reg2, reg2 -> reg2
    0x08, 0x02, 0x00, 0x00,  // CMP reg2, 0
    0x09, 0x0A, 0x00, 0x00,  // JZ to 0x0A
    0x10, 0x00, 0x00, 0x00,  // HALT
    0x01, 0x03, 0x42, 0x00,  // LOAD 0x42 into reg3
    0x01, 0x04, 0x54, 0x00,  // LOAD 0x54 into reg4
    0x01, 0x05, 0x7B, 0x00,  // LOAD 0x7B into reg5
    0x0C, 0x00, 0x00, 0x00,  // CALL function
    0x10, 0x00, 0x00, 0x00   // HALT
};

// Polymorphic function for phase 1
__attribute__((noinline))
static void polymorphic_phase1() {
    // This function will be obfuscated at compile time
    volatile int x = 0x1337;
    volatile int y = 0xDEAD;
    volatile int z = x ^ y;
    
    // Reconstruct first part of flag
    char temp[] = {0x48, 0x54, 0x42, 0x7B, 0x6D, 0x69, 0x6E, 0x64, 0x6D, 0x61, 0x7A, 0x65, 0x5F, 0x31, 0x7D, 0x00};
    
    for (int i = 0; i < 16; i++) {
        temp[i] ^= (z & 0xFF);
        flag_part1[i] = temp[i];
    }
    
    // Anti-tampering check
    if (z != (0x1337 ^ 0xDEAD)) {
        exit(1);
    }
}

// Anti-debugging functions
static int check_ptrace() {
    if (ptrace(PTRACE_TRACEME, 0, 1, 0) == -1) {
        return 1; // Being debugged
    }
    return 0;
}

static int check_ld_preload() {
    char *ld_preload = getenv("LD_PRELOAD");
    if (ld_preload != NULL) {
        return 1; // LD_PRELOAD detected
    }
    return 0;
}

static int check_parent_process() {
    pid_t ppid = getppid();
    if (ppid == 1) {
        return 1; // Parent is init, might be debugger
    }
    return 0;
}

static int check_debugger() {
    if (check_ptrace() || check_ld_preload() || check_parent_process()) {
        return 1;
    }
    return 0;
}

// String decryption function
static void decrypt_string(unsigned char *encrypted, int len, unsigned char *output) {
    for (int i = 0; i < len; i++) {
        output[i] = encrypted[i] ^ xor_key[i % 32];
    }
}

// VM implementation
static void vm_init(vm_state_t *vm) {
    vm->memory = malloc(4096);
    vm->pc = 0;
    vm->sp = 4095;
    vm->flags = 0;
    vm->running = 1;
    memset(vm->registers, 0, sizeof(vm->registers));
    memset(vm->memory, 0, 4096);
}

static void vm_execute(vm_state_t *vm) {
    while (vm->running && vm->pc < sizeof(vm_bytecode)) {
        unsigned char instruction = vm_bytecode[vm->pc];
        
        switch (instruction) {
            case VM_NOP:
                vm->pc++;
                break;
                
            case VM_LOAD: {
                int reg = vm_bytecode[vm->pc + 1];
                int value = vm_bytecode[vm->pc + 2] | (vm_bytecode[vm->pc + 3] << 8);
                vm->registers[reg] = value;
                vm->pc += 4;
                break;
            }
            
            case VM_ADD: {
                int reg1 = vm_bytecode[vm->pc + 1];
                int reg2 = vm_bytecode[vm->pc + 2];
                int reg3 = vm_bytecode[vm->pc + 3];
                vm->registers[reg1] = vm->registers[reg2] + vm->registers[reg3];
                vm->pc += 4;
                break;
            }
            
            case VM_XOR: {
                int reg1 = vm_bytecode[vm->pc + 1];
                int reg2 = vm_bytecode[vm->pc + 2];
                int reg3 = vm_bytecode[vm->pc + 3];
                vm->registers[reg1] = vm->registers[reg2] ^ vm->registers[reg3];
                vm->pc += 4;
                break;
            }
            
            case VM_CMP: {
                int reg1 = vm_bytecode[vm->pc + 1];
                int reg2 = vm_bytecode[vm->pc + 2];
                vm->flags = (vm->registers[reg1] == vm->registers[reg2]) ? 1 : 0;
                vm->pc += 3;
                break;
            }
            
            case VM_JZ: {
                int addr = vm_bytecode[vm->pc + 1];
                if (vm->flags) {
                    vm->pc = addr;
                } else {
                    vm->pc += 2;
                }
                break;
            }
            
            case VM_CALL: {
                // Special call to extract second part of flag
                char temp[] = {0x5F, 0x76, 0x6D, 0x5F, 0x70, 0x72, 0x6F, 0x74, 0x65, 0x63, 0x74, 0x65, 0x64, 0x5F, 0x32, 0x7D, 0x00};
                for (int i = 0; i < 17; i++) {
                    temp[i] ^= vm->registers[3] & 0xFF;
                    flag_part2[i] = temp[i];
                }
                vm->pc += 1;
                break;
            }
            
            case VM_HALT:
                vm->running = 0;
                break;
                
            default:
                vm->pc++;
                break;
        }
    }
}

// FSM implementation
static void init_fsm() {
    // Initialize transition table with obfuscated values
    for (int i = 0; i < 7; i++) {
        for (int j = 0; j < 256; j++) {
            fsm_transitions[i][j] = (i * 37 + j * 73) % 7;
        }
    }
}

static int fsm_process_input(char input) {
    int next_state = fsm_transitions[current_fsm_state][(unsigned char)input];
    
    switch (current_fsm_state) {
        case STATE_INIT:
            if (input == 'H') next_state = STATE_PHASE1;
            break;
        case STATE_PHASE1:
            if (input == 'T') next_state = STATE_PHASE2;
            break;
        case STATE_PHASE2:
            if (input == 'B') next_state = STATE_PHASE3;
            break;
        case STATE_PHASE3:
            if (input == '{') next_state = STATE_VALIDATE;
            break;
        case STATE_VALIDATE:
            // Complex validation logic
            if (input == 'm') next_state = STATE_SUCCESS;
            break;
    }
    
    current_fsm_state = next_state;
    return next_state;
}

// Main game loop with control flow flattening
static void game_loop() {
    char input[256];
    int checksum = 0;
    
    // Control flow flattening loop
    while (1) {
        switch (cff_state) {
            case 0: // Initialize
                printf("Welcome to MindMaze!\n");
                printf("Enter your solution: ");
                cff_next_state = 1;
                break;
                
            case 1: // Get input
                if (fgets(input, sizeof(input), stdin) == NULL) {
                    cff_next_state = 6; // Exit
                    break;
                }
                input[strcspn(input, "\n")] = 0;
                cff_next_state = 2;
                break;
                
            case 2: // Phase 1 - Polymorphic
                polymorphic_phase1();
                cff_next_state = 3;
                break;
                
            case 3: // Phase 2 - VM
                if (!vm_initialized) {
                    vm_state_t vm;
                    vm_init(&vm);
                    vm_execute(&vm);
                    vm_initialized = 1;
                }
                cff_next_state = 4;
                break;
                
            case 4: // Phase 3 - FSM
                init_fsm();
                for (int i = 0; input[i]; i++) {
                    int state = fsm_process_input(input[i]);
                    checksum += input[i] * (i + 1);
                }
                cff_next_state = 5;
                break;
                
            case 5: // Validation
                if (current_fsm_state == STATE_SUCCESS && checksum == 0x1337) {
                    printf("Congratulations! Flag: %s%s%s\n", flag_part1, flag_part2, flag_part3);
                    cff_next_state = 6;
                } else {
                    printf("Try again...\n");
                    cff_next_state = 0;
                }
                break;
                
            case 6: // Exit
                return;
        }
        
        cff_state = cff_next_state;
    }
}

// Obfuscated main function
int main() {
    // Anti-debugging checks
    if (anti_debug_active && check_debugger()) {
        printf("Debugging detected!\n");
        exit(1);
    }
    
    // Initialize encryption
    srand(time(NULL));
    for (int i = 0; i < 32; i++) {
        xor_key[i] = rand() & 0xFF;
    }
    
    // Initialize third part of flag
    strcpy(flag_part3, "_fsm_validated_3}");
    
    // Start the game
    game_loop();
    
    return 0;
}