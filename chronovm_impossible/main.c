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

static void generate_flag(char *flag, size_t len) {
    const char *parts[] = {
        "HTB{",
        "ChronoVM_",
        "Smurf_",
        "Lock_",
        "VM_",
        "VirtualMachine",
        "}"
    };
    
    size_t pos = 0;
    for (int i = 0; i < 7 && pos < len - 1; i++) {
        size_t part_len = strlen(parts[i]);
        if (pos + part_len < len) {
            memcpy(flag + pos, parts[i], part_len);
            pos += part_len;
        }
    }
    flag[pos] = '\0';
}

static int validate_flag(const char *input) {
    char expected[256];
    generate_flag(expected, sizeof(expected));
    return strcmp(input, expected) == 0;
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
    print_banner("CHRONO VM CHALLENGE - IMPOSSIBLE EDITION");
    
    printf("\nNARRATIVE: 'THE CHRONOVM INCIDENT'\n");
    printf("===================================\n\n");
    
    printf("Date: March 15, 2024\n");
    printf("Time: 03:47 AM\n");
    printf("Location: Abandoned Data Center, Sector 7\n\n");
    
    printf("SITUATION:\n");
    printf("   You are an elite hacker specialized in critical systems.\n");
    printf("   You have been contacted by an anonymous source claiming to\n");
    printf("   have discovered something disturbing in an abandoned system.\n");
    printf("   A program called 'ChronoVM' has begun to show anomalous\n");
    printf("   behavior, and your mission is to investigate what is\n");
    printf("   happening.\n\n");
    
    printf("WARNINGS:\n");
    printf("   - The system has extreme protections\n");
    printf("   - Multiple false flags have been reported\n");
    printf("   - The truth is buried in layers of deception\n");
    printf("   - An error could cost you your life\n");
    printf("   - Time limit: 6 hours\n\n");
    
    printf("OBJECTIVE:\n");
    printf("   Find the truth behind ChronoVM and discover\n");
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
                printf("   Analyzing ChronoVM system...\n");
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
                printf("   Key: ChronoVMSmurf\n");
                printf("   What is the validation key? ");
                
                char key[256];
                if (fgets(key, sizeof(key), stdin) != NULL) {
                    key[strcspn(key, "\n")] = 0;
                    if (strcmp(key, "ChronoVMSmurf") == 0) {
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
                    if (strcmp(key4, "ChronoVMSmurf") == 0) {
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
                    if (strcmp(key5, "ChronoVMSmurf") == 0) {
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
                    if (validate_flag(result)) {
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
                printf("   Flag: HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}\n");
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
        printf("Real flag: HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}\n");
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