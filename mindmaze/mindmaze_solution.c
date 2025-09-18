#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <time.h>

// Simulación del reto MindMaze sin protecciones
// Basado en el análisis del binario original

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

// Flags
static int vm_initialized = 0;

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
    STATE_CRYPT,
    STATE_HASH,
    STATE_FINAL,
    STATE_SUCCESS,
    STATE_FAILURE,
    STATE_MAX
} fsm_state_t;

static fsm_state_t current_fsm_state = STATE_INIT;
static int fsm_step_counter = 0;
static unsigned long fsm_checksum = 0;

// FSM transition table
static int fsm_transitions[STATE_MAX][256] = {0};

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

// VM bytecode (del binario original)
static unsigned char vm_bytecode[] = {
    0x01, 0x00, 0x10, 0x00, 0x01, 0x01, 0x20, 0x00, 0x05, 0x02, 0x00, 0x01,
    0x06, 0x02, 0x00, 0x01, 0x07, 0x02, 0x02, 0x02, 0x08, 0x02, 0x00, 0x00,
    0x09, 0x0A, 0x00, 0x00, 0x10, 0x00, 0x00, 0x00, 0x01, 0x03, 0x42, 0x00,
    0x01, 0x04, 0x54, 0x00, 0x01, 0x05, 0x7B, 0x00, 0x0C, 0x00, 0x00, 0x00,
    0x10, 0x00, 0x00, 0x00, 0x01, 0x06, 0x13, 0x37, 0x01, 0x07, 0xDE, 0xAD,
    0x07, 0x08, 0x06, 0x07, 0x01, 0x09, 0xBE, 0xEF, 0x05, 0x0A, 0x08, 0x09,
    0x08, 0x0A, 0x00, 0x00, 0x09, 0x14, 0x00, 0x00, 0x10, 0x00, 0x00, 0x00,
    0x01, 0x0B, 0xCA, 0xFE, 0x01, 0x0C, 0xBA, 0xBE, 0x07, 0x0D, 0x0B, 0x0C,
    0x01, 0x0E, 0xDE, 0xAD, 0x05, 0x0F, 0x0D, 0x0E, 0x0C, 0x00, 0x00, 0x00,
    0x10, 0x00, 0x00, 0x00
};

// Polymorphic function for phase 1
__attribute__((noinline))
static void polymorphic_phase1() {
    volatile int x = 0x1337;
    volatile int y = 0xDEAD;
    volatile int z = x ^ y;
    
    // Reconstruct first part of flag
    char temp[] = {0x48, 0x54, 0x42, 0x7B, 0x6D, 0x69, 0x6E, 0x64, 0x6D, 0x61, 0x7A, 0x65, 0x5F, 0x31, 0x7D, 0x00};
    
    for (int i = 0; i < 16; i++) {
        temp[i] ^= (z & 0xFF);
        flag_part1[i] = temp[i];
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
    while (vm->running && vm->pc < (int)sizeof(vm_bytecode)) {
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
    // Initialize transition table
    for (int i = 0; i < STATE_MAX; i++) {
        for (int j = 0; j < 256; j++) {
            fsm_transitions[i][j] = ((i * 37 + j * 73 + (i ^ j) * 13) % (STATE_MAX - 2)) + 1;
        }
    }
}

static int fsm_process_input(char input) {
    fsm_step_counter++;
    
    int next_state = fsm_transitions[current_fsm_state][(unsigned char)input];
    
    // Complex state-specific logic
    switch (current_fsm_state) {
        case STATE_INIT:
            if (input == 'H' && fsm_step_counter == 1) {
                next_state = STATE_PHASE1;
                fsm_checksum = 0x48; // 'H'
            }
            break;
            
        case STATE_PHASE1:
            if (input == 'T' && fsm_step_counter == 2) {
                next_state = STATE_PHASE2;
                fsm_checksum ^= 0x54; // 'T'
            }
            break;
            
        case STATE_PHASE2:
            if (input == 'B' && fsm_step_counter == 3) {
                next_state = STATE_PHASE3;
                fsm_checksum ^= 0x42; // 'B'
            }
            break;
            
        case STATE_PHASE3:
            if (input == '{' && fsm_step_counter == 4) {
                next_state = STATE_VALIDATE;
                fsm_checksum ^= 0x7B; // '{'
            }
            break;
            
        case STATE_VALIDATE:
            if (input == 'm' && fsm_step_counter == 5) {
                next_state = STATE_CRYPT;
                fsm_checksum ^= 0x6D; // 'm'
            }
            break;
            
        case STATE_CRYPT:
            if (input == 'i' && fsm_step_counter == 6) {
                next_state = STATE_HASH;
                fsm_checksum ^= 0x69; // 'i'
            }
            break;
            
        case STATE_HASH:
            if (input == 'n' && fsm_step_counter == 7) {
                next_state = STATE_FINAL;
                fsm_checksum ^= 0x6E; // 'n'
            }
            break;
            
        case STATE_FINAL:
            if (input == 'd' && fsm_step_counter == 8) {
                fsm_checksum ^= 0x64; // 'd'
                if (fsm_checksum == 0x2b) {
                    next_state = STATE_SUCCESS;
                } else {
                    next_state = STATE_FAILURE;
                }
            } else {
                next_state = STATE_FAILURE;
            }
            break;
    }
    
    current_fsm_state = next_state;
    return next_state;
}

// Main game loop
static void game_loop() {
    char input[256];
    
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
                    fsm_process_input(input[i]);
                }
                cff_next_state = 5;
                break;
                
            case 5: // Validation
                {
                    // Multiple validation layers
                    int vm_validation = vm_initialized ? 1 : 0;
                    int fsm_validation = (current_fsm_state == STATE_SUCCESS && fsm_step_counter == 8) ? 1 : 0;
                    
                    // Checksum validation
                    unsigned long total_checksum = 0;
                    for (int i = 0; i < (int)strlen(input); i++) {
                        total_checksum += input[i] * (i + 1) * (i + 1);
                    }
                    
                    int checksum_validation = (total_checksum == 0x5311 && fsm_checksum == 0x2b) ? 1 : 0;
                    int timing_validation = 1; // Always pass
                    
                    // All validations must pass
                    if (vm_validation && fsm_validation && checksum_validation && timing_validation) {
                        printf("Congratulations! Flag: %s%s%s\n", flag_part1, flag_part2, flag_part3);
                        cff_next_state = 6;
                    } else {
                        printf("Try again...\n");
                        cff_next_state = 0;
                    }
                }
                break;
                
            case 6: // Exit
                return;
        }
        
        cff_state = cff_next_state;
    }
}

// Main function
int main() {
    // Initialize third part of flag
    strcpy(flag_part3, "_fsm_validated_3}");
    
    // Start the game
    game_loop();
    
    return 0;
}