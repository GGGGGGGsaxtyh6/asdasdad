#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/prctl.h>
#include <sys/time.h>
#include <sys/resource.h>
#include <signal.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <time.h>
#include <stdint.h>
#include <sys/mman.h>
#include <errno.h>
#include <sys/wait.h>
#include <sys/syscall.h>
#include <linux/seccomp.h>
#include <linux/filter.h>
#include <linux/audit.h>

#define MAX_PHASES 50
#define MAX_LIVES 1
#define MAX_SCORE 100000
#define TIME_LIMIT 21600

typedef struct {
    uint32_t magic;
    uint32_t version;
    uint32_t checksum;
    uint32_t flags;
    char data[1024];
    uint32_t crc32;
    uint32_t timestamp;
    uint32_t nonce;
} header_t;

typedef struct {
    uint8_t opcode;
    uint8_t reg1;
    uint8_t reg2;
    uint8_t reg3;
    uint32_t immediate;
    uint32_t address;
    uint32_t flags;
} instruction_t;

typedef struct {
    uint32_t registers[64];
    uint32_t memory[16384];
    uint32_t stack[4096];
    uint32_t pc;
    uint32_t sp;
    uint32_t flags;
    uint8_t running;
    uint32_t cycles;
    uint32_t errors;
} vm_state_t;

typedef struct {
    uint32_t phase;
    uint32_t score;
    uint32_t lives;
    uint32_t hints_used;
    time_t start_time;
    time_t phase_start;
    char player_name[64];
    uint32_t knowledge_level;
    uint32_t deception_level;
    uint32_t trust_level;
    uint32_t suspicion_level;
    char discovered_secrets[200][256];
    uint32_t secret_count;
    uint32_t fake_flags_found;
    uint32_t real_flag_found;
    uint32_t crypto_keys[100];
    uint32_t vm_cycles;
    uint32_t memory_accesses;
    uint32_t debug_attempts;
    uint32_t anti_debug_triggers;
    uint32_t integrity_failures;
    uint32_t vm_crashes;
    uint32_t crypto_failures;
    uint32_t memory_corruptions;
    uint32_t stack_overflows;
    uint32_t heap_exploits;
    uint32_t format_strings;
    uint32_t rop_chains;
    uint32_t jit_compilations;
    uint32_t side_channels;
} game_state_t;

static game_state_t gs = {0};
static vm_state_t vm = {0};
static header_t header = {0};
static uint8_t encrypted_bytecode[65536] = {0};
static size_t bytecode_size = 0;
static uint8_t key_schedule[2048] = {0};
static uint32_t sbox[2048] = {0};
static uint32_t round_keys[256] = {0};
static uint8_t memory_pool[524288] = {0};
static uint32_t memory_checksum = 0;
static int anti_debug_count = 0;
static int integrity_failures = 0;
static int vm_crashes = 0;
static int crypto_failures = 0;

static void anti_debug_1(void) {
    if (ptrace(PTRACE_TRACEME, 0, 0, 0) == -1) {
        printf("DETECTION FAILED\n");
        exit(1);
    }
}

static void anti_debug_2(void) {
    struct rlimit rlim;
    getrlimit(RLIMIT_CORE, &rlim);
    if (rlim.rlim_cur != 0) {
        printf("DETECTION FAILED\n");
        exit(1);
    }
}

static void anti_debug_3(void) {
    char buf[256];
    snprintf(buf, sizeof(buf), "/proc/%d/status", getpid());
    FILE *f = fopen(buf, "r");
    if (f) {
        char line[256];
        while (fgets(line, sizeof(line), f)) {
            if (strstr(line, "TracerPid:") && strstr(line, "0") == NULL) {
                printf("DETECTION FAILED\n");
                fclose(f);
                exit(1);
            }
        }
        fclose(f);
    }
}

static void anti_debug_4(void) {
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);
    usleep(1000);
    clock_gettime(CLOCK_MONOTONIC, &end);
    long elapsed = (end.tv_sec - start.tv_sec) * 1000000000 + (end.tv_nsec - start.tv_nsec);
    if (elapsed > 2000000) {
        printf("DETECTION FAILED\n");
        exit(1);
    }
}

static void anti_debug_5(void) {
    uint32_t eax, ebx, ecx, edx;
    asm volatile("cpuid" : "=a"(eax), "=b"(ebx), "=c"(ecx), "=d"(edx) : "a"(1));
    if (ecx & (1 << 20)) {
        printf("DETECTION FAILED\n");
        exit(1);
    }
}

static void anti_debug_6(void) {
    char *ptr = mmap(NULL, 4096, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (ptr == MAP_FAILED) return;
    
    uint8_t code[] = {0x90, 0x90, 0x90, 0x90};
    memcpy(ptr, code, sizeof(code));
    
    mprotect(ptr, 4096, PROT_READ | PROT_EXEC);
    
    void (*func)() = (void(*)())ptr;
    func();
    
    munmap(ptr, 4096);
}

static void anti_debug_7(void) {
    FILE *self = fopen("/proc/self/exe", "rb");
    if (!self) return;
    
    fseek(self, 0, SEEK_END);
    long size = ftell(self);
    fseek(self, 0, SEEK_SET);
    
    uint8_t *buffer = malloc(size);
    if (!buffer) {
        fclose(self);
        return;
    }
    
    fread(buffer, 1, size, self);
    fclose(self);
    
    uint32_t checksum = 0;
    for (long i = 0; i < size; i++) {
        checksum = (checksum << 1) ^ buffer[i];
    }
    
    uint32_t expected = 0xDEADBEEF;
    if (checksum != expected) {
        printf("DETECTION FAILED\n");
        free(buffer);
        exit(1);
    }
    
    free(buffer);
}

static void anti_debug_8(void) {
    uint8_t *code_ptr = (uint8_t*)anti_debug_8;
    for (int i = 0; i < 100; i++) {
        if (code_ptr[i] == 0xCC) {
            printf("DETECTION FAILED\n");
            exit(1);
        }
    }
}

static void anti_debug_9(void) {
    volatile uint64_t canary = 0x123456789ABCDEF0;
    volatile char buffer[64];
    strcpy((char*)buffer, "test");
    if (canary != 0x123456789ABCDEF0) {
        printf("DETECTION FAILED\n");
        exit(1);
    }
}

static void anti_debug_10(void) {
    int fd = open("/proc/self/stat", O_RDONLY);
    if (fd >= 0) {
        char buf[1024];
        read(fd, buf, sizeof(buf));
        close(fd);
        
        char *state = strchr(buf, ')') + 2;
        if (state && *state == 't') {
            printf("DETECTION FAILED\n");
            exit(1);
        }
    }
}

static void integrity_check(void) {
    uint32_t checksum1 = 0, checksum2 = 0, checksum3 = 0;
    
    uint8_t *code_start = (uint8_t*)integrity_check;
    for (int i = 0; i < 1000; i++) {
        checksum1 = (checksum1 << 1) ^ code_start[i];
    }
    
    for (int i = 0; i < sizeof(gs); i++) {
        checksum2 = (checksum2 << 1) ^ ((uint8_t*)&gs)[i];
    }
    
    for (int i = 0; i < sizeof(memory_pool); i++) {
        checksum3 = (checksum3 << 1) ^ memory_pool[i];
    }
    
    uint32_t expected = 0xDEADBEEF;
    if (checksum1 != expected || checksum2 != expected || checksum3 != expected) {
        printf("INTEGRITY FAILED\n");
        exit(1);
    }
}

static void init_crypto_system(void) {
    for (int i = 0; i < 2048; i++) {
        sbox[i] = (i * 0x9E3779B9) ^ 0x12345678;
        sbox[i] = ((sbox[i] << 13) | (sbox[i] >> 19)) ^ 0x87654321;
    }
    
    for (int i = 0; i < 2048; i++) {
        key_schedule[i] = (i * 0x5A827999) ^ 0xFEDCBA98;
    }
    
    for (int i = 0; i < 256; i++) {
        round_keys[i] = (i * 0x6ED9EBA1) ^ 0x13579BDF;
    }
}

static void custom_encrypt(uint8_t *data, size_t len, uint32_t key) {
    for (size_t i = 0; i < len; i++) {
        data[i] ^= (key >> (i % 32)) & 0xFF;
        data[i] = sbox[data[i] % 2048];
        data[i] ^= key_schedule[i % 2048];
        data[i] = ((data[i] << 3) | (data[i] >> 5)) ^ round_keys[i % 256];
    }
}

static void init_vm(void) {
    memset(&vm, 0, sizeof(vm));
    vm.running = 1;
    vm.pc = 0;
    vm.sp = 4095;
    
    for (int i = 0; i < 16384; i++) {
        vm.memory[i] = (i * 0x9E3779B9) ^ 0x12345678;
    }
}

static void vm_execute_instruction(instruction_t *inst) {
    switch (inst->opcode) {
        case 0x00:
            break;
        case 0x01:
            vm.registers[inst->reg1] = inst->immediate;
            break;
        case 0x02:
            vm.memory[inst->address] = vm.registers[inst->reg1];
            break;
        case 0x03:
            vm.registers[inst->reg1] = vm.registers[inst->reg2] + vm.registers[inst->reg3];
            break;
        case 0x04:
            vm.registers[inst->reg1] = vm.registers[inst->reg2] - vm.registers[inst->reg3];
            break;
        case 0x05:
            vm.registers[inst->reg1] = vm.registers[inst->reg2] * vm.registers[inst->reg3];
            break;
        case 0x06:
            vm.registers[inst->reg1] = vm.registers[inst->reg2] ^ vm.registers[inst->reg3];
            break;
        case 0x07:
            vm.registers[inst->reg1] = vm.registers[inst->reg2] << vm.registers[inst->reg3];
            break;
        case 0x08:
            vm.registers[inst->reg1] = vm.registers[inst->reg2] >> vm.registers[inst->reg3];
            break;
        case 0x09:
            vm.flags = (vm.registers[inst->reg1] == vm.registers[inst->reg2]) ? 1 : 0;
            break;
        case 0x0A:
            vm.pc = inst->address;
            break;
        case 0x0B:
            if (vm.flags) vm.pc = inst->address;
            break;
        case 0x0C:
            vm.stack[vm.sp--] = vm.pc;
            vm.pc = inst->address;
            break;
        case 0x0D:
            vm.pc = vm.stack[++vm.sp];
            break;
        case 0x0E:
            vm.running = 0;
            break;
        default:
            vm_crashes++;
            break;
    }
    vm.pc++;
    gs.vm_cycles++;
}

static void generate_dynamic_flag(char *flag, int score, int knowledge, int cycles) {
    uint8_t enc[] = {0x48^0xAA, 0x54^0xBB, 0x42^0xCC, 0x7B^0xDD, 0x43^0xEE, 0x68^0xFF, 0x72^0x11, 0x6F^0x22, 0x6E^0x33, 0x6F^0x44, 0x56^0x55, 0x4D^0x66, 0x5F^0x77, 0x53^0x88, 0x6D^0x99, 0x75^0xAA, 0x72^0xBB, 0x66^0xCC, 0x5F^0xDD, 0x4C^0xEE, 0x6F^0xFF, 0x63^0x11, 0x6B^0x22, 0x5F^0x33, 0x56^0x44, 0x4D^0x55, 0x5F^0x66, 0x56^0x77, 0x69^0x88, 0x72^0x99, 0x74^0xAA, 0x75^0xBB, 0x61^0xCC, 0x6C^0xDD, 0x4D^0xEE, 0x61^0xFF, 0x63^0x11, 0x68^0x22, 0x69^0x33, 0x6E^0x44, 0x65^0x55, 0x7D^0x66};
    uint8_t key[] = {0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88, 0x99, 0xAA, 0xBB, 0xCC, 0xDD, 0xEE, 0xFF, 0x11, 0x22, 0x33, 0x44, 0x55, 0x66};
    
    for (int i = 0; i < 42; i++) {
        flag[i] = enc[i] ^ key[i];
    }
    flag[42] = '\0';
}

static void clear_screen(void) {
    printf("\033[2J\033[H");
}

static void print_banner(const char *title) {
    printf("\n╔══════════════════════════════════════════════════════════════╗\n");
    printf("║ %-60s ║\n", title);
    printf("╚══════════════════════════════════════════════════════════════╝\n");
}

static void print_stats(void) {
    printf("\n┌─ STATS ──────────────────────────────────────────────────────┐\n");
    printf("│ Player: %-20s Score: %-8d │\n", gs.player_name, gs.score);
    printf("│ Lives: %-3d Hints: %-3d Phase: %-15d │\n", gs.lives, gs.hints_used, gs.phase);
    printf("│ Knowledge: %-3d Deception: %-3d Trust: %-3d │\n", gs.knowledge_level, gs.deception_level, gs.trust_level);
    printf("│ Fake flags: %-3d Real flag: %-3s VM cycles: %-8d │\n", gs.fake_flags_found, gs.real_flag_found ? "Yes" : "No", gs.vm_cycles);
    printf("│ Memory accesses: %-8d Debug attempts: %-8d │\n", gs.memory_accesses, gs.debug_attempts);
    printf("│ Integrity failures: %-8d VM crashes: %-8d │\n", gs.integrity_failures, gs.vm_crashes);
    printf("└─────────────────────────────────────────────────────────────┘\n");
}


int main(int argc, char *argv[]) {
    init_crypto_system();
    init_vm();
    
    anti_debug_1();
    anti_debug_2();
    anti_debug_3();
    anti_debug_4();
    anti_debug_5();
    anti_debug_6();
    anti_debug_7();
    anti_debug_8();
    anti_debug_9();
    anti_debug_10();
    
    integrity_check();
    
    gs.phase = 0;
    gs.lives = MAX_LIVES;
    gs.score = 0;
    gs.start_time = time(NULL);
    gs.phase_start = gs.start_time;
    
    clear_screen();
    print_banner("VIRTUAL MACHINE CHALLENGE - IMPOSSIBLE EDITION");
    
    printf("\nNARRATIVE: 'THE IMPOSSIBLE INCIDENT'\n");
    printf("===================================\n\n");
    
    printf("Location: Abandoned Data Center, Sector 7\n\n");
    
    printf("SITUATION:\n");
    printf("   You are an elite hacker specialized in critical systems.\n");
    printf("   You have been contacted by an anonymous source claiming to\n");
    printf("   have discovered something disturbing in an abandoned system.\n");
    printf("   A mysterious program has begun to show anomalous\n");
    printf("   behavior, and your mission is to investigate what is\n");
    printf("   happening.\n\n");
    
    printf("WARNINGS:\n");
    printf("   - The system has extreme protections\n");
    printf("   - Multiple false flags have been reported\n");
    printf("   - The truth is buried in layers of deception\n");
    printf("   - An error could cost you your life\n");
    printf("   - Time limit: 6 hours\n\n");
    
    printf("OBJECTIVE:\n");
    printf("   Find the truth behind this system and discover\n");
    printf("   the real flag among dozens of decoys.\n\n");
    
    printf("RESOURCES:\n");
    printf("   - 1 life (critical errors kill you)\n");
    printf("   - Limited analysis tools\n");
    printf("   - Limited time before system self-destructs\n\n");
    
    printf("Are you ready to face the truth? (y/n): ");
    
    char response;
    if (scanf(" %c", &response) == 1 && (response == 'y' || response == 'Y')) {
        printf("\nPerfect! Starting the adventure...\n");
        sleep(2);
    } else {
        printf("\nYou have decided not to face the truth. The system self-destructs.\n");
        return 0;
    }
    
    while (gs.phase < MAX_PHASES && gs.lives > 0) {
        clear_screen();
        print_stats();
        
        time_t current_time = time(NULL);
        if (current_time - gs.start_time > TIME_LIMIT) {
            printf("TIME UP - The system self-destructs.\n");
            break;
        }
        
        switch (gs.phase) {
            case 0:
                printf("Press ENTER to start investigation...");
                getchar();
                gs.phase = 1;
                break;
                
            case 1:
                printf("PHASE 1: INITIAL INVESTIGATION\n");
                printf("   Analyzing target system...\n");
                printf("   Multiple false flags detected.\n");
                printf("   Which do you think is the real flag? (1-10): ");
                
                int choice;
                if (scanf("%d", &choice) == 1) {
                    printf("FALSE! All flags in this phase are decoys.\n");
                    printf("+50 points for discovering the truth\n");
                    gs.score += 50;
                    gs.fake_flags_found++;
                    gs.phase = 2;
                }
                break;
                
            case 2:
                printf("PHASE 2: MEMORY ANALYSIS\n");
                printf("   Exploring system memory...\n");
                printf("   20 more flags found.\n");
                printf("   Which do you think is the real flag? (1-20): ");
                
                if (scanf("%d", &choice) == 1) {
                    printf("FALSE! All flags in memory are decoys.\n");
                    printf("+100 points for persisting in the search\n");
                    gs.score += 100;
                    gs.fake_flags_found += 20;
                    gs.phase = 3;
                }
                break;
                
            case 3:
                printf("PHASE 3: CRYPTOGRAPHIC LAYER 1\n");
                printf("   Decrypting encrypted communications...\n");
                printf("   Algorithm: ROT13 + XOR\n");
                printf("   Message: Uryyb, jrypbzr gb gur puneqba!\n");
                printf("   What is the decrypted message? ");
                
                char answer[256];
                if (fgets(answer, sizeof(answer), stdin) != NULL) {
                    answer[strcspn(answer, "\n")] = 0;
                    if (strcmp(answer, "Hello, welcome to the challenge!") == 0) {
                        printf("CORRECT! +200 points\n");
                        gs.score += 200;
                        gs.knowledge_level++;
                        gs.phase = 4;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 4:
                printf("PHASE 4: CRYPTOGRAPHIC LAYER 2\n");
                printf("   Decrypting hybrid algorithm...\n");
                printf("   Algorithm: Modified SHA1 + Custom S-box\n");
                char dkey[16] = {0x43^0x12, 0x68^0x34, 0x72^0x56, 0x6F^0x78, 0x6E^0x9A, 0x6F^0xBC, 0x56^0xDE, 0x4D^0xF0, 0x53^0x21, 0x6D^0x43, 0x75^0x65, 0x72^0x87, 0x66^0xA9, 0};
                for(int i=0;i<13;i++) dkey[i]^=(0x12+i*0x22)&0xFF;
                printf("   Key: %s\n", dkey);
                printf("   What is the validation key? ");
                
                char key[256];
                if (fgets(key, sizeof(key), stdin) != NULL) {
                    key[strcspn(key, "\n")] = 0;
                    char expected_key[16];
                    for(int i=0;i<13;i++) expected_key[i] = (0x43^0x12)^((0x12+i*0x22)&0xFF);
                    expected_key[0] = 'C'; expected_key[1] = 'h'; expected_key[2] = 'r'; expected_key[3] = 'o';
                    expected_key[4] = 'n'; expected_key[5] = 'o'; expected_key[6] = 'V'; expected_key[7] = 'M';
                    expected_key[8] = 'S'; expected_key[9] = 'm'; expected_key[10] = 'u'; expected_key[11] = 'r';
                    expected_key[12] = 'f'; expected_key[13] = '\0';
                    if (strcmp(key, expected_key) == 0) {
                        printf("CORRECT! +500 points\n");
                        gs.score += 500;
                        gs.knowledge_level += 2;
                        gs.phase = 5;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 5:
                printf("PHASE 5: CRYPTOGRAPHIC LAYER 3\n");
                printf("   Decrypting cellular automaton...\n");
                printf("   Rule: 30 with variations\n");
                printf("   Expected checksum: 0x40008000\n");
                printf("   What is the calculated checksum? ");
                
                uint32_t checksum;
                if (scanf("%x", &checksum) == 1) {
                    if (checksum == 0x40008000) {
                        printf("CORRECT! +300 points\n");
                        gs.score += 300;
                        gs.knowledge_level++;
                        gs.phase = 6;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 6:
                printf("PHASE 6: CRYPTOGRAPHIC LAYER 4\n");
                printf("   Decrypting custom encryption algorithm...\n");
                printf("   Algorithm: Multi-layer with custom S-box\n");
                printf("   What is the decryption key? ");
                
                char key4[256];
                if (fgets(key4, sizeof(key4), stdin) != NULL) {
                    key4[strcspn(key4, "\n")] = 0;
                    char ek4[16];
                    uint8_t enc4[] = {0x43^0x77, 0x68^0x88, 0x72^0x99, 0x6F^0xAA, 0x6E^0xBB, 0x6F^0xCC, 0x56^0xDD, 0x4D^0xEE, 0x53^0xFF, 0x6D^0x11, 0x75^0x22, 0x72^0x33, 0x66^0x44, 0};
                    for(int i=0;i<13;i++) ek4[i] = enc4[i] ^ (0x77+(i*0x11));
                    ek4[13] = '\0';
                    if (strcmp(key4, ek4) == 0) {
                        printf("CORRECT! +400 points\n");
                        gs.score += 400;
                        gs.knowledge_level += 2;
                        gs.phase = 7;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 7:
                printf("PHASE 7: CRYPTOGRAPHIC LAYER 5\n");
                printf("   Decrypting final algorithm...\n");
                printf("   Algorithm: Hybrid with multiple layers\n");
                printf("   What is the final key? ");
                
                char key5[256];
                if (fgets(key5, sizeof(key5), stdin) != NULL) {
                    key5[strcspn(key5, "\n")] = 0;
                    char ek5[16];
                    uint8_t enc5[] = {0x43^0x66, 0x68^0x77, 0x72^0x88, 0x6F^0x99, 0x6E^0xAA, 0x6F^0xBB, 0x56^0xCC, 0x4D^0xDD, 0x53^0xEE, 0x6D^0xFF, 0x75^0x11, 0x72^0x22, 0x66^0x33, 0};
                    for(int i=0;i<13;i++) ek5[i] = enc5[i] ^ (0x66+(i*0x11));
                    ek5[13] = '\0';
                    if (strcmp(key5, ek5) == 0) {
                        printf("CORRECT! +600 points\n");
                        gs.score += 600;
                        gs.knowledge_level += 3;
                        gs.phase = 8;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 8:
                printf("PHASE 8: VIRTUAL MACHINE ANALYSIS\n");
                printf("   Analyzing VM bytecode...\n");
                printf("   15 custom instructions found.\n");
                printf("   What is the error in the bytecode? (1-10): ");
                
                int error_line;
                if (scanf("%d", &error_line) == 1) {
                    if (error_line == 4) {
                        printf("CORRECT! +300 points\n");
                        gs.score += 300;
                        gs.knowledge_level++;
                        gs.phase = 9;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 9:
                printf("PHASE 9: VIRTUAL MACHINE EXECUTION\n");
                printf("   Executing VM bytecode...\n");
                printf("   %d VM cycles executed.\n", gs.vm_cycles);
                printf("   What is the execution result? ");
                
                char result[256];
                if (fgets(result, sizeof(result), stdin) != NULL) {
                    result[strcspn(result, "\n")] = 0;
                    char exp_flag[64];
                    generate_dynamic_flag(exp_flag, gs.score, gs.knowledge_level, gs.vm_cycles);
                    if (strcmp(result, exp_flag) == 0) {
                        printf("CORRECT! +800 points\n");
                        gs.score += 800;
                        gs.knowledge_level += 4;
                        gs.real_flag_found = 1;
                        gs.phase = 10;
                    } else {
                        printf("INCORRECT! You lost a life.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case 10:
                printf("PHASE 10: FINAL BOSS\n");
                printf("   You have found the real flag!\n");
                char temp_flag[64];
                generate_dynamic_flag(temp_flag, gs.score, gs.knowledge_level, gs.vm_cycles);
                printf("   Flag: %s\n", temp_flag);
                printf("   Do you confirm you have the real flag? (y/n): ");
                
                char confirm;
                if (scanf(" %c", &confirm) == 1 && (confirm == 'y' || confirm == 'Y')) {
                    printf("VICTORY!\n");
                    printf("You have successfully completed the most difficult challenge.\n");
                    printf("+1000 points for final victory\n");
                    gs.score += 1000;
                    gs.phase = 11;
                } else {
                    printf("Escape failed. The system self-destructs.\n");
                    gs.lives--;
                }
                break;
                
            case 11:
                printf("PHASE 11: ESCAPE\n");
                printf("   You have successfully escaped with the real flag.\n");
                printf("   +500 points for successful escape\n");
                gs.score += 500;
                gs.phase = MAX_PHASES;
                break;
                
            default:
                break;
        }
        
        if (gs.lives <= 0) {
            clear_screen();
            print_banner("GAME OVER");
            printf("\nSorry, you ran out of lives.\n");
            printf("Final score: %d points\n", gs.score);
            printf("Maximum possible score: %d points\n", MAX_SCORE);
            printf("Try again to improve your score!\n");
            break;
        }
    }
    
    if (gs.phase == MAX_PHASES) {
        clear_screen();
        print_banner("TOTAL VICTORY!");
        printf("\nCONGRATULATIONS! You have completed the most difficult challenge.\n");
        printf("Final score: %d points\n", gs.score);
        printf("Maximum score: %d points\n", MAX_SCORE);
        char flag[64];
        generate_dynamic_flag(flag, gs.score, gs.knowledge_level, gs.vm_cycles);
        printf("Real flag: %s\n", flag);
        printf("Total time: %ld seconds\n", time(NULL) - gs.start_time);
        printf("Lives remaining: %d\n", gs.lives);
        printf("False flags found: %d\n", gs.fake_flags_found);
        printf("Knowledge level: %d\n", gs.knowledge_level);
        printf("VM cycles executed: %d\n", gs.vm_cycles);
        printf("Cryptographic layers broken: 5\n");
        printf("Anti-debugging protections activated: 10\n");
        printf("You are a true elite hacker!\n");
    }
    
    return 0;
}