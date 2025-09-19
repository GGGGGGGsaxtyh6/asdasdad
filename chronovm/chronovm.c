#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/prctl.h>
#include <sys/time.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include <signal.h>
#include <errno.h>
#include <stdint.h>
#include <sys/mman.h>
#include <termios.h>
#include <unistd.h>
#include <sys/ioctl.h>

// Protecciones anti-debugging
#define ANTI_DEBUG 1
#define INTEGRITY_CHECK 1
#define TIMING_CHECK 1

// Instrucciones de la VM
#define VM_NOP    0x00
#define VM_LOAD   0x01
#define VM_STORE  0x02
#define VM_ADD    0x03
#define VM_SUB    0x04
#define VM_MUL    0x05
#define VM_XOR    0x06
#define VM_SHL    0x07
#define VM_SHR    0x08
#define VM_CMP    0x09
#define VM_JMP    0x0A
#define VM_JZ     0x0B
#define VM_CALL   0x0C
#define VM_RET    0x0D
#define VM_HALT   0x0E

// Tamaños
#define VM_MEM_SIZE 4096
#define VM_STACK_SIZE 256
#define MAX_FRAGMENTS 8

// Estructura de la VM
typedef struct {
    uint32_t regs[16];
    uint32_t memory[VM_MEM_SIZE];
    uint32_t stack[VM_STACK_SIZE];
    int32_t sp;
    uint32_t pc;
    uint32_t flags;
    int running;
} vm_t;

// Variables globales
static vm_t vm;
static uint8_t *bytecode = NULL;
static size_t bytecode_size = 0;
static char fragments[MAX_FRAGMENTS][64];
static int fragment_count = 0;
static int fragments_enabled = 0;

// Sistema de fases interactivas con narrativa compleja
typedef enum {
    PHASE_INTRO = 0,
    PHASE_INVESTIGATION,
    PHASE_DECRYPTION,
    PHASE_VM_ANALYSIS,
    PHASE_MEMORY_DIVE,
    PHASE_CRYPTO_BREAK,
    PHASE_FINAL_REVELATION,
    PHASE_ESCAPE,
    PHASE_COMPLETE
} phase_t;

// Sistema de decisiones narrativas
typedef enum {
    DECISION_INVESTIGATE = 1,
    DECISION_ANALYZE = 2,
    DECISION_DECRYPT = 3,
    DECISION_DEBUG = 4,
    DECISION_ESCAPE = 5
} decision_t;

// Estados del juego
typedef struct {
    int trust_level;
    int suspicion_level;
    int knowledge_level;
    int deception_level;
    char discovered_secrets[10][256];
    int secret_count;
    int fake_flags_found;
    int real_flag_found;
} game_state_t;

static phase_t current_phase = PHASE_INTRO;
static int score = 0;
static int lives = 3;
static char player_name[64] = {0};
static int hints_used = 0;
static int time_limit = 300; // 5 minutos por fase
static time_t phase_start_time;
static game_state_t game_state = {0};
static int current_decision = 0;
static int story_branch = 0;
static char current_location[64] = "Unknown";
static char current_mission[256] = "Investigate the anomaly";

// Fake flags para confundir
static const char* fake_flags[] = {
    "HTB{Fake_Flag_1_ChronoVM}",
    "HTB{Decoy_Flag_TimeLock}",
    "HTB{False_Flag_VM_Challenge}",
    "HTB{Red_Herring_Smurf}",
    "HTB{Decoy_ChronoVM_Fake}",
    "HTB{False_TimeLock_VM}",
    "HTB{Decoy_Smurf_Challenge}",
    "HTB{Fake_VM_TimeLock}",
    "HTB{Red_Herring_ChronoVM}",
    "HTB{Decoy_Flag_Final}"
};

// Flags reales ocultas
static const char* real_flag = "HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}";

// Caja S personalizada
static const uint8_t custom_sbox[256] = {
    0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
    0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
    0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
    0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
    0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
    0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
    0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
    0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
    0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
    0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
    0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
    0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
    0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
    0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
    0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
    0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
};

// Constantes SHA1 modificadas
static const uint32_t sha1_constants[4] = {
    0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476
};

// Funciones de protección anti-debugging
static void anti_debug_check(void) {
    if (ptrace(PTRACE_TRACEME, 0, 1, 0) == -1) {
        printf("Debugger detected!\n");
        exit(1);
    }
    
    prctl(PR_SET_DUMPABLE, 0);
    
    // Timing check
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);
    volatile int dummy = 0;
    for (int i = 0; i < 1000; i++) {
        dummy += i;
    }
    clock_gettime(CLOCK_MONOTONIC, &end);
    
    long elapsed = (end.tv_sec - start.tv_sec) * 1000000000L + (end.tv_nsec - start.tv_nsec);
    if (elapsed > 1000000) { // Más de 1ms es sospechoso
        printf("Execution too slow, possible debugging!\n");
        exit(1);
    }
}

// Verificación de integridad del binario
static void integrity_check(void) {
    FILE *self = fopen("/proc/self/exe", "rb");
    if (!self) return;
    
    fseek(self, 0, SEEK_END);
    long size = ftell(self);
    fseek(self, 0, SEEK_SET);
    
    uint8_t *buffer = malloc(size);
    fread(buffer, 1, size, self);
    fclose(self);
    
    // Checksum simple del código
    uint32_t checksum = 0;
    for (long i = 0; i < size; i++) {
        checksum = (checksum << 1) ^ buffer[i];
    }
    
    // Valor esperado (se calcula en build time)
    if (checksum != 0x40008000) {
        printf("Binary integrity check failed!\n");
        exit(1);
    }
    
    free(buffer);
}

// SHA1 modificado con algoritmo más complejo
static void sha1_modified(const uint8_t *data, size_t len, uint8_t *hash) {
    uint32_t h[5];
    memcpy(h, sha1_constants, sizeof(h));
    h[4] = 0xC3D2E1F0; // Constante modificada
    
    // Procesamiento más complejo con múltiples rondas
    for (int round = 0; round < 3; round++) {
        for (size_t i = 0; i < len; i++) {
            uint32_t temp = data[i] ^ (i & 0xFF);
            h[0] = ((h[0] << 1) ^ temp) + h[1] + round;
            h[1] = ((h[1] << 2) ^ temp) + h[2] + (round * 2);
            h[2] = ((h[2] << 3) ^ temp) + h[3] + (round * 3);
            h[3] = ((h[3] << 4) ^ temp) + h[4] + (round * 4);
            h[4] = ((h[4] << 5) ^ temp) + h[0] + (round * 5);
            
            // Aplicar rotación adicional
            h[0] = (h[0] << 3) | (h[0] >> 29);
            h[1] = (h[1] << 7) | (h[1] >> 25);
            h[2] = (h[2] << 11) | (h[2] >> 21);
            h[3] = (h[3] << 13) | (h[3] >> 19);
            h[4] = (h[4] << 17) | (h[4] >> 15);
        }
    }
    
    memcpy(hash, h, 20);
}

// Aplicar caja S personalizada
static void apply_sbox(uint8_t *data, size_t len) {
    for (size_t i = 0; i < len; i++) {
        data[i] = custom_sbox[data[i]];
    }
}

// Autómata celular regla 30 con variaciones
static uint32_t cellular_automaton(uint32_t state) {
    uint32_t new_state = 0;
    for (int i = 0; i < 32; i++) {
        uint32_t left = (state >> ((i + 1) % 32)) & 1;
        uint32_t center = (state >> i) & 1;
        uint32_t right = (state >> ((i - 1 + 32) % 32)) & 1;
        
        // Aplicar regla 30 con variaciones basadas en posición
        uint32_t rule = (left << 2) | (center << 1) | right;
        uint32_t rule_variant = rule ^ (i & 0x7);
        
        if (rule_variant == 0b100 || rule_variant == 0b011 || 
            rule_variant == 0b010 || rule_variant == 0b001 ||
            rule_variant == 0b110 || rule_variant == 0b101) {
            new_state |= (1 << i);
        }
    }
    return new_state;
}

// Inicializar VM
static void vm_init(void) {
    memset(&vm, 0, sizeof(vm));
    vm.running = 1;
    vm.sp = VM_STACK_SIZE - 1;
    vm.pc = 0;
}

// Ejecutar instrucción VM
static void vm_execute(uint8_t opcode, uint32_t *operands) {
    switch (opcode) {
        case VM_NOP:
            break;
            
        case VM_LOAD:
            vm.regs[operands[0]] = operands[1];
            break;
            
        case VM_STORE:
            vm.memory[operands[0]] = vm.regs[operands[1]];
            break;
            
        case VM_ADD:
            vm.regs[operands[0]] = vm.regs[operands[1]] + vm.regs[operands[2]];
            break;
            
        case VM_SUB:
            vm.regs[operands[0]] = vm.regs[operands[1]] - vm.regs[operands[2]];
            break;
            
        case VM_MUL:
            vm.regs[operands[0]] = vm.regs[operands[1]] * vm.regs[operands[2]];
            break;
            
        case VM_XOR:
            vm.regs[operands[0]] = vm.regs[operands[1]] ^ vm.regs[operands[2]];
            break;
            
        case VM_SHL:
            vm.regs[operands[0]] = vm.regs[operands[1]] << vm.regs[operands[2]];
            break;
            
        case VM_SHR:
            vm.regs[operands[0]] = vm.regs[operands[1]] >> vm.regs[operands[2]];
            break;
            
        case VM_CMP:
            vm.flags = (vm.regs[operands[0]] == vm.regs[operands[1]]) ? 1 : 0;
            break;
            
        case VM_JMP:
            vm.pc = operands[0];
            return;
            
        case VM_JZ:
            if (vm.flags) {
                vm.pc = operands[0];
                return;
            }
            break;
            
        case VM_CALL:
            vm.stack[vm.sp--] = vm.pc + 5;
            vm.pc = operands[0];
            return;
            
        case VM_RET:
            vm.pc = vm.stack[++vm.sp];
            break;
            
        case VM_HALT:
            vm.running = 0;
            break;
    }
    vm.pc += 5;
}

// Descifrar bytecode
static void decrypt_bytecode(void) {
    // Clave fija derivada de constantes del sistema
    uint8_t key[16] = {0x43, 0x68, 0x72, 0x6F, 0x6E, 0x6F, 0x56, 0x4D, 
                       0x53, 0x6D, 0x75, 0x72, 0x66, 0x4C, 0x6F, 0x63};
    
    // XOR simple con la clave
    for (size_t i = 0; i < bytecode_size; i++) {
        bytecode[i] ^= key[i % 16];
    }
}

// Cargar bytecode cifrado
static void load_bytecode(void) {
    // Bytecode cifrado (se genera en build time)
    static const uint8_t encrypted_bytecode[] = {
        0x01, 0x00, 0x00, 0x00, 0x48,  // VM_LOAD r0, 'H'
        0x01, 0x01, 0x00, 0x00, 0x54,  // VM_LOAD r1, 'T'
        0x01, 0x02, 0x00, 0x00, 0x42,  // VM_LOAD r2, 'B'
        0x01, 0x03, 0x00, 0x00, 0x7B,  // VM_LOAD r3, '{'
        0x01, 0x04, 0x00, 0x00, 0x43,  // VM_LOAD r4, 'C'
        0x01, 0x05, 0x00, 0x00, 0x68,  // VM_LOAD r5, 'h'
        0x01, 0x06, 0x00, 0x00, 0x72,  // VM_LOAD r6, 'r'
        0x01, 0x07, 0x00, 0x00, 0x6F,  // VM_LOAD r7, 'o'
        0x01, 0x08, 0x00, 0x00, 0x6E,  // VM_LOAD r8, 'n'
        0x01, 0x09, 0x00, 0x00, 0x6F,  // VM_LOAD r9, 'o'
        0x01, 0x0A, 0x00, 0x00, 0x56,  // VM_LOAD r10, 'V'
        0x01, 0x0B, 0x00, 0x00, 0x4D,  // VM_LOAD r11, 'M'
        0x01, 0x0C, 0x00, 0x00, 0x5F,  // VM_LOAD r12, '_'
        0x01, 0x0D, 0x00, 0x00, 0x53,  // VM_LOAD r13, 'S'
        0x01, 0x0E, 0x00, 0x00, 0x6D,  // VM_LOAD r14, 'm'
        0x01, 0x0F, 0x00, 0x00, 0x75,  // VM_LOAD r15, 'u'
        0x01, 0x00, 0x00, 0x00, 0x72,  // VM_LOAD r0, 'r'
        0x01, 0x01, 0x00, 0x00, 0x66,  // VM_LOAD r1, 'f'
        0x01, 0x02, 0x00, 0x00, 0x5F,  // VM_LOAD r2, '_'
        0x01, 0x03, 0x00, 0x00, 0x4C,  // VM_LOAD r3, 'L'
        0x01, 0x04, 0x00, 0x00, 0x6F,  // VM_LOAD r4, 'o'
        0x01, 0x05, 0x00, 0x00, 0x63,  // VM_LOAD r5, 'c'
        0x01, 0x06, 0x00, 0x00, 0x6B,  // VM_LOAD r6, 'k'
        0x01, 0x07, 0x00, 0x00, 0x5F,  // VM_LOAD r7, '_'
        0x01, 0x08, 0x00, 0x00, 0x56,  // VM_LOAD r8, 'V'
        0x01, 0x09, 0x00, 0x00, 0x4D,  // VM_LOAD r9, 'M'
        0x01, 0x0A, 0x00, 0x00, 0x7D,  // VM_LOAD r10, '}'
        0x0E, 0x00, 0x00, 0x00, 0x00   // VM_HALT
    };
    
    bytecode_size = sizeof(encrypted_bytecode);
    bytecode = malloc(bytecode_size);
    memcpy(bytecode, encrypted_bytecode, bytecode_size);
    
    decrypt_bytecode();
}

// Crear fragmentos de flag
static void create_fragments(void) {
    if (!fragments_enabled) return;
    
    const char *flag_parts[] = {
        "ChronoVM_Smurf_",
        "Lock_VM_",
        "VirtualMachine"
    };
    
    for (int i = 0; i < 3; i++) {
        char filename[64];
        snprintf(filename, sizeof(filename), "/dev/shm/chrono_frag_%d", i);
        
        int fd = open(filename, O_CREAT | O_WRONLY | O_TRUNC, 0644);
        if (fd >= 0) {
            write(fd, flag_parts[i], strlen(flag_parts[i]));
            close(fd);
        }
    }
}

// Validar entrada del usuario con algoritmo más complejo
static int validate_input(const char *input) {
    if (!input) return 0;
    
    // Hash de la entrada
    uint8_t hash[20];
    sha1_modified((uint8_t*)input, strlen(input), hash);
    
    // Aplicar caja S múltiples veces
    apply_sbox(hash, 20);
    apply_sbox(hash, 20);
    
    // Autómata celular con más iteraciones
    uint32_t state = *(uint32_t*)hash;
    for (int i = 0; i < 150; i++) {
        state = cellular_automaton(state);
        // Aplicar XOR adicional cada 10 iteraciones
        if (i % 10 == 0) {
            state ^= (i * 0x1337);
        }
    }
    
    // Verificar checksum final
    return (state == 0x40008000);
}

// Mostrar reloj digital
static void show_clock(void) {
    time_t now = time(NULL);
    struct tm *tm_info = localtime(&now);
    
    printf("\n╔══════════════════════════════════════╗\n");
    printf("║           ChronoVM v2.0              ║\n");
    printf("║                                      ║\n");
    printf("║  Time: %02d:%02d:%02d                      ║\n", 
           tm_info->tm_hour, tm_info->tm_min, tm_info->tm_sec);
    printf("║  Date: %02d/%02d/%04d                    ║\n",
           tm_info->tm_mday, tm_info->tm_mon + 1, tm_info->tm_year + 1900);
    printf("║                                      ║\n");
    printf("║  Status: %s                    ║\n", 
           fragments_enabled ? "FRAGMENTS ENABLED" : "NORMAL MODE");
    printf("╚══════════════════════════════════════╝\n\n");
}

// Ejecutar VM
static void run_vm(void) {
    vm_init();
    
    while (vm.running && vm.pc < bytecode_size) {
        uint8_t opcode = bytecode[vm.pc];
        uint32_t operands[3];
        
        if (vm.pc + 4 < bytecode_size) {
            operands[0] = *(uint32_t*)(bytecode + vm.pc + 1);
            operands[1] = *(uint32_t*)(bytecode + vm.pc + 5);
            operands[2] = *(uint32_t*)(bytecode + vm.pc + 9);
        }
        
        vm_execute(opcode, operands);
    }
    
    // Construir flag desde registros
    char flag[64] = {0};
    int pos = 0;
    for (int i = 0; i < 16; i++) {
        if (vm.regs[i] > 0 && vm.regs[i] < 256) {
            flag[pos++] = (char)vm.regs[i];
        }
    }
    
    if (fragments_enabled) {
        printf("Flag fragments assembled: %s\n", flag);
    }
}

// Funciones de interfaz interactiva
static void clear_screen(void) {
    printf("\033[2J\033[H");
    fflush(stdout);
}

static void print_banner(const char *text) {
    printf("\n");
    printf("╔══════════════════════════════════════════════════════════════╗\n");
    printf("║ %-60s ║\n", text);
    printf("╚══════════════════════════════════════════════════════════════╝\n");
}

static void print_ascii_art(const char *art) {
    printf("%s\n", art);
}

static void print_progress_bar(int current, int total) {
    int width = 50;
    int filled = (current * width) / total;
    
    printf("\r[");
    for (int i = 0; i < width; i++) {
        if (i < filled) printf("█");
        else printf("░");
    }
    printf("] %d/%d", current, total);
    fflush(stdout);
}

static void print_stats(void) {
    printf("\n┌─ ESTADÍSTICAS ──────────────────────────────────────────────┐\n");
    printf("│ Jugador: %-20s Puntuación: %-8d │\n", player_name, score);
    printf("│ Vidas: %-3d Pistas usadas: %-3d Fase: %-15s │\n", 
           lives, hints_used, 
           current_phase == PHASE_INTRO ? "Introducción" :
           current_phase == PHASE_INVESTIGATION ? "Investigación" :
           current_phase == PHASE_DECRYPTION ? "Descifrado" :
           current_phase == PHASE_VM_ANALYSIS ? "Análisis VM" :
           current_phase == PHASE_MEMORY_DIVE ? "Memoria" :
           current_phase == PHASE_CRYPTO_BREAK ? "Crypto Break" :
           current_phase == PHASE_FINAL_REVELATION ? "Revelación" :
           current_phase == PHASE_ESCAPE ? "Escape" : "Completado");
    printf("│ Flags falsos: %-3d Flag real: %-3s Conocimiento: %-3d │\n", 
           game_state.fake_flags_found, 
           game_state.real_flag_found ? "Sí" : "No",
           game_state.knowledge_level);
    printf("└─────────────────────────────────────────────────────────────┘\n");
}

// Fase 1: Investigación inicial con fake flags
static int investigation_phase(void) {
    clear_screen();
    print_banner("🔍 FASE 1: INVESTIGACIÓN INICIAL");
    
    printf("\n🎯 Objetivo: Analizar el sistema ChronoVM\n");
    printf("📝 Instrucciones: Encuentra pistas ocultas en el sistema\n");
    printf("⏰ Tiempo límite: 60 segundos\n\n");
    
    printf("🔍 Análisis del sistema:\n");
    printf("   - Procesos activos: 15\n");
    printf("   - Memoria utilizada: 2.3GB\n");
    printf("   - Comunicaciones detectadas: 3\n");
    printf("   - Flags encontrados: 5\n\n");
    
    printf("🚨 ADVERTENCIA: Se han detectado múltiples flags falsos!\n");
    printf("   Solo uno es real. Los demás son señuelos.\n\n");
    
    // Mostrar fake flags
    printf("🔍 Flags detectados:\n");
    for (int i = 0; i < 5; i++) {
        printf("   %d. %s\n", i+1, fake_flags[i]);
    }
    
    printf("\n🔍 ¿Cuál crees que es el flag real? (1-5): ");
    int choice;
    if (scanf("%d", &choice) != 1 || choice < 1 || choice > 5) {
        printf("\n❌ Entrada inválida! Perdiste una vida.\n");
        lives--;
        return 0;
    }
    
    // Todos son fake flags en esta fase
    printf("\n❌ ¡FALSO! Todos los flags en esta fase son señuelos.\n");
    printf("   El flag real está más profundo en el sistema.\n");
    printf("   +50 puntos por descubrir la verdad\n");
    score += 50;
    game_state.fake_flags_found++;
    
    printf("\n✅ Has aprendido a identificar señuelos. Avanzando...\n");
    return 1;
}

// Fase 2: Descifrado con múltiples capas
static int decryption_phase(void) {
    clear_screen();
    print_banner("🔐 FASE 2: DESCIFRADO DE COMUNICACIONES");
    
    printf("\n🎯 Objetivo: Descifrar las comunicaciones encriptadas\n");
    printf("📝 Instrucciones: Resuelve el algoritmo criptográfico\n");
    printf("⏰ Tiempo límite: 120 segundos\n\n");
    
    printf("🔍 Comunicación interceptada:\n");
    printf("   Cifrado: Uryyb, jrypbzr gb gur puneqba!\n");
    printf("   Clave: ChronoVMSmurf\n");
    printf("   Algoritmo: ROT13 + XOR\n\n");
    
    printf("🔍 ¿Cuál es el mensaje descifrado? ");
    char answer[256];
    if (fgets(answer, sizeof(answer), stdin) == NULL) {
        printf("\n❌ Error de entrada!\n");
        return 0;
    }
    
    // Eliminar newline
    answer[strcspn(answer, "\n")] = 0;
    
    if (strcmp(answer, "Hello, welcome to the challenge!") == 0) {
        printf("\n✅ ¡Correcto! +200 puntos\n");
        score += 200;
        game_state.knowledge_level++;
        
        printf("\n🔍 Descifrando más comunicaciones...\n");
        printf("   Se han encontrado 3 flags más:\n");
        for (int i = 5; i < 8; i++) {
            printf("   - %s\n", fake_flags[i]);
        }
        printf("   ⚠️  Todos son señuelos avanzados!\n");
        
        return 1;
    } else {
        printf("\n❌ Incorrecto! Perdiste una vida.\n");
        lives--;
        return 0;
    }
}

// Fase 3: Análisis de VM con bytecode complejo
static int vm_analysis_phase(void) {
    clear_screen();
    print_banner("🖥️  FASE 3: ANÁLISIS DE MÁQUINA VIRTUAL");
    
    printf("\n🎯 Objetivo: Analizar la máquina virtual interna\n");
    printf("📝 Instrucciones: Encuentra el error en el bytecode\n");
    printf("⏰ Tiempo límite: 180 segundos\n\n");
    
    printf("🔍 Bytecode de la VM:\n");
    printf("   0x01 0x00 0x00 0x00 0x48  // VM_LOAD r0, 'H'\n");
    printf("   0x01 0x01 0x00 0x00 0x54  // VM_LOAD r1, 'T'\n");
    printf("   0x01 0x02 0x00 0x00 0x42  // VM_LOAD r2, 'B'\n");
    printf("   0x01 0x03 0x00 0x00 0x7B  // VM_LOAD r3, '{'\n");
    printf("   0x01 0x04 0x00 0x00 0x43  // VM_LOAD r4, 'C'\n");
    printf("   0x01 0x05 0x00 0x00 0x68  // VM_LOAD r5, 'h'\n");
    printf("   0x01 0x06 0x00 0x00 0x72  // VM_LOAD r6, 'r'\n");
    printf("   0x01 0x07 0x00 0x00 0x6F  // VM_LOAD r7, 'o'\n");
    printf("   0x01 0x08 0x00 0x00 0x6E  // VM_LOAD r8, 'n'\n");
    printf("   0x01 0x09 0x00 0x00 0x6F  // VM_LOAD r9, 'o'\n");
    printf("   0x01 0x0A 0x00 0x00 0x56  // VM_LOAD r10, 'V'\n");
    printf("   0x01 0x0B 0x00 0x00 0x4D  // VM_LOAD r11, 'M'\n");
    printf("   0x01 0x0C 0x00 0x00 0x5F  // VM_LOAD r12, '_'\n");
    printf("   0x01 0x0D 0x00 0x00 0x53  // VM_LOAD r13, 'S'\n");
    printf("   0x01 0x0E 0x00 0x00 0x6D  // VM_LOAD r14, 'm'\n");
    printf("   0x01 0x0F 0x00 0x00 0x75  // VM_LOAD r15, 'u'\n");
    printf("   0x01 0x00 0x00 0x00 0x72  // VM_LOAD r0, 'r'\n");
    printf("   0x01 0x01 0x00 0x00 0x66  // VM_LOAD r1, 'f'\n");
    printf("   0x01 0x02 0x00 0x00 0x5F  // VM_LOAD r2, '_'\n");
    printf("   0x01 0x03 0x00 0x00 0x4C  // VM_LOAD r3, 'L'\n");
    printf("   0x01 0x04 0x00 0x00 0x6F  // VM_LOAD r4, 'o'\n");
    printf("   0x01 0x05 0x00 0x00 0x63  // VM_LOAD r5, 'c'\n");
    printf("   0x01 0x06 0x00 0x00 0x6B  // VM_LOAD r6, 'k'\n");
    printf("   0x01 0x07 0x00 0x00 0x5F  // VM_LOAD r7, '_'\n");
    printf("   0x01 0x08 0x00 0x00 0x56  // VM_LOAD r8, 'V'\n");
    printf("   0x01 0x09 0x00 0x00 0x4D  // VM_LOAD r9, 'M'\n");
    printf("   0x01 0x0A 0x00 0x00 0x7D  // VM_LOAD r10, '}'\n");
    printf("   0x0E 0x00 0x00 0x00 0x00   // VM_HALT\n\n");
    
    printf("🔍 ¿En qué línea está el error? (1-6): ");
    int line;
    if (scanf("%d", &line) != 1) {
        printf("\n❌ Entrada inválida!\n");
        return 0;
    }
    
    // El error está en la línea 4 (índice 3) - VM_LOAD r4, 'C' debería ser VM_LOAD r4, 'S'
    if (line == 4) {
        printf("\n✅ ¡Correcto! +300 puntos\n");
        score += 300;
        game_state.knowledge_level++;
        
        printf("\n🔍 Análisis completado. Se han encontrado más flags:\n");
        printf("   - %s\n", fake_flags[8]);
        printf("   - %s\n", fake_flags[9]);
        printf("   ⚠️  Estos también son señuelos!\n");
        
        return 1;
    } else {
        printf("\n❌ Incorrecto! Perdiste una vida.\n");
        lives--;
        return 0;
    }
}

// Fase 4: Inmersión en memoria con fake flags
static int memory_dive_phase(void) {
    clear_screen();
    print_banner("🧠 FASE 4: INMERSIÓN EN MEMORIA");
    
    printf("\n🎯 Objetivo: Explorar la memoria del sistema\n");
    printf("📝 Instrucciones: Encuentra el flag real entre los señuelos\n");
    printf("⏰ Tiempo límite: 240 segundos\n\n");
    
    printf("🔍 Memoria del sistema explorada:\n");
    printf("   - Secciones encontradas: 15\n");
    printf("   - Flags detectados: 10\n");
    printf("   - Patrones sospechosos: 3\n");
    printf("   - Contramedidas activadas: 2\n\n");
    
    printf("🚨 ADVERTENCIA: El sistema detecta intrusiones!\n");
    printf("   Tienes tiempo limitado antes de ser detectado.\n\n");
    
    printf("🔍 Flags encontrados en memoria:\n");
    for (int i = 0; i < 10; i++) {
        printf("   %d. %s\n", i+1, fake_flags[i]);
    }
    
    printf("\n🔍 ¿Cuál crees que es el flag real? (1-10): ");
    int choice;
    if (scanf("%d", &choice) != 1 || choice < 1 || choice > 10) {
        printf("\n❌ Entrada inválida! Perdiste una vida.\n");
        lives--;
        return 0;
    }
    
    // Todos son fake flags en esta fase también
    printf("\n❌ ¡FALSO! Todos los flags en memoria son señuelos.\n");
    printf("   El flag real está encriptado en el bytecode de la VM.\n");
    printf("   +100 puntos por persistir en la búsqueda\n");
    score += 100;
    game_state.fake_flags_found += 10;
    
    printf("\n✅ Has aprendido que la verdad está más profundo.\n");
    return 1;
}

// Fase 5: Ruptura criptográfica final
static int crypto_break_phase(void) {
    clear_screen();
    print_banner("🔓 FASE 5: RUPTURA CRIPTOGRÁFICA");
    
    printf("\n🎯 Objetivo: Romper el cifrado final\n");
    printf("📝 Instrucciones: Resuelve el algoritmo híbrido\n");
    printf("⏰ Tiempo límite: 300 segundos\n\n");
    
    printf("🔍 Algoritmo criptográfico identificado:\n");
    printf("   - SHA1 modificado con constantes alteradas\n");
    printf("   - Caja S personalizada de 256 bytes\n");
    printf("   - Autómata celular regla 30 con variaciones\n");
    printf("   - Checksum final: 0x40008000\n\n");
    
    printf("🔑 Pistas descubiertas:\n");
    printf("   - La clave contiene 'ChronoVM'\n");
    printf("   - Incluye una referencia a 'Smurf'\n");
    printf("   - El flag real está encriptado en el bytecode\n");
    printf("   - Solo se puede descifrar con la clave correcta\n\n");
    
    printf("🔍 ¿Cuál es la clave de validación? ");
    char key[256];
    if (fgets(key, sizeof(key), stdin) == NULL) {
        printf("\n❌ Error de entrada!\n");
        return 0;
    }
    
    // Eliminar newline
    key[strcspn(key, "\n")] = 0;
    
    if (strcmp(key, "ChronoVMSmurf") == 0) {
        printf("\n✅ ¡Correcto! +500 puntos\n");
        score += 500;
        game_state.knowledge_level += 2;
        
        printf("\n🔓 Cifrado roto. Descifrando flag real...\n");
        printf("   El flag real es: %s\n", real_flag);
        game_state.real_flag_found = 1;
        
        return 1;
    } else {
        printf("\n❌ Incorrecto! Perdiste una vida.\n");
        lives--;
        return 0;
    }
}

// Fase 6: Revelación final
static int final_revelation_phase(void) {
    clear_screen();
    print_banner("💡 FASE 6: REVELACIÓN FINAL");
    
    printf("\n🎯 Objetivo: Descubrir la verdad\n");
    printf("📝 Instrucciones: Confirma que has encontrado el flag real\n");
    printf("⏰ Tiempo límite: 60 segundos\n\n");
    
    printf("🔍 Análisis final completado:\n");
    printf("   - Flags falsos encontrados: %d\n", game_state.fake_flags_found);
    printf("   - Nivel de conocimiento: %d\n", game_state.knowledge_level);
    printf("   - Flag real encontrado: %s\n", game_state.real_flag_found ? "Sí" : "No");
    printf("   - Puntuación actual: %d\n", score);
    printf("\n");
    
    if (game_state.real_flag_found) {
        printf("✅ ¡Has encontrado el flag real!\n");
        printf("   Flag: %s\n", real_flag);
        printf("   +1000 puntos por completar la misión\n");
        score += 1000;
        return 1;
    } else {
        printf("❌ No has encontrado el flag real.\n");
        printf("   Debes regresar y encontrar la clave correcta.\n");
        lives--;
        return 0;
    }
}

// Fase 7: Escape final
static int escape_phase(void) {
    clear_screen();
    print_banner("🏃 FASE 7: ESCAPE");
    
    printf("\n🎯 Objetivo: Escapar con el flag real\n");
    printf("📝 Instrucciones: Confirma tu escape exitoso\n");
    printf("⏰ Tiempo límite: 30 segundos\n\n");
    
    printf("🚨 SISTEMA EN AUTODESTRUCCIÓN\n");
    printf("   Tiempo restante: 5 minutos\n");
    printf("   Flag real: %s\n", real_flag);
    printf("   Estado: Listo para escapar\n\n");
    
    printf("🔍 ¿Confirmas que tienes el flag real? (s/n): ");
    char confirm;
    if (scanf(" %c", &confirm) == 1 && (confirm == 's' || confirm == 'S')) {
        printf("\n✅ ¡ESCAPE EXITOSO!\n");
        printf("   Has logrado escapar con el flag real.\n");
        printf("   +500 puntos por el escape exitoso\n");
        score += 500;
        return 1;
    } else {
        printf("\n❌ Escape fallido. El sistema se autodestruye.\n");
        lives--;
        return 0;
    }
}

// Minijuego 1: Puzzle de memoria (mantenido para compatibilidad)
static int memory_puzzle(void) {
    clear_screen();
    print_banner("🧠 PUZZLE DE MEMORIA - FASE 1");
    
    printf("\n🎯 Objetivo: Memoriza la secuencia de números y repítela\n");
    printf("📝 Instrucciones: Se mostrarán números por 3 segundos, luego repítelos\n");
    printf("⏰ Tiempo límite: 30 segundos\n\n");
    
    // Generar secuencia aleatoria
    int sequence[8];
    srand(time(NULL));
    for (int i = 0; i < 8; i++) {
        sequence[i] = rand() % 10;
    }
    
    printf("🔢 Secuencia: ");
    for (int i = 0; i < 8; i++) {
        printf("%d ", sequence[i]);
    }
    printf("\n");
    
    printf("\n⏳ Memorizando... ");
    for (int i = 3; i > 0; i--) {
        printf("%d ", i);
        fflush(stdout);
        sleep(1);
    }
    printf("\n");
    
    clear_screen();
    printf("✍️  Ahora repite la secuencia (8 números separados por espacios): ");
    
    int user_sequence[8];
    for (int i = 0; i < 8; i++) {
        if (scanf("%d", &user_sequence[i]) != 1) {
            printf("\n❌ Entrada inválida!\n");
            return 0;
        }
    }
    
    // Verificar secuencia
    for (int i = 0; i < 8; i++) {
        if (user_sequence[i] != sequence[i]) {
            printf("\n❌ Secuencia incorrecta! Perdiste una vida.\n");
            lives--;
            return 0;
        }
    }
    
    printf("\n✅ ¡Correcto! +100 puntos\n");
    score += 100;
    return 1;
}

// Minijuego 2: Puzzle matemático
static int math_puzzle(void) {
    clear_screen();
    print_banner("🔢 PUZZLE MATEMÁTICO - FASE 2");
    
    printf("\n🎯 Objetivo: Resuelve la ecuación criptográfica\n");
    printf("📝 Instrucciones: Encuentra el valor de X en la ecuación\n");
    printf("⏰ Tiempo límite: 60 segundos\n\n");
    
    // Generar ecuación compleja
    int a = rand() % 100 + 1;
    int b = rand() % 100 + 1;
    int c = rand() % 50 + 1;
    int d = rand() % 20 + 1;
    
    int result = (a * b) + (c * d) - (a % c);
    
    printf("🧮 Ecuación: (%d × %d) + (%d × %d) - (%d mod %d) = X\n", a, b, c, d, a, c);
    printf("🔍 ¿Cuál es el valor de X? ");
    
    int answer;
    if (scanf("%d", &answer) != 1) {
        printf("\n❌ Entrada inválida!\n");
        return 0;
    }
    
    if (answer == result) {
        printf("\n✅ ¡Correcto! +150 puntos\n");
        score += 150;
        return 1;
    } else {
        printf("\n❌ Incorrecto! La respuesta era %d. Perdiste una vida.\n", result);
        lives--;
        return 0;
    }
}

// Minijuego 3: Puzzle de patrones
static int pattern_puzzle(void) {
    clear_screen();
    print_banner("🔍 PUZZLE DE PATRONES - FASE 3");
    
    printf("\n🎯 Objetivo: Encuentra el patrón en la secuencia\n");
    printf("📝 Instrucciones: Completa la secuencia lógica\n");
    printf("⏰ Tiempo límite: 90 segundos\n\n");
    
    // Secuencia de Fibonacci modificada
    printf("🔢 Secuencia: 1, 1, 2, 3, 5, 8, 13, 21, ?, ?, ?\n");
    printf("🔍 ¿Cuáles son los siguientes 3 números? ");
    
    int answers[3];
    for (int i = 0; i < 3; i++) {
        if (scanf("%d", &answers[i]) != 1) {
            printf("\n❌ Entrada inválida!\n");
            return 0;
        }
    }
    
    // Verificar secuencia de Fibonacci
    int expected[] = {34, 55, 89};
    for (int i = 0; i < 3; i++) {
        if (answers[i] != expected[i]) {
            printf("\n❌ Patrón incorrecto! Perdiste una vida.\n");
            lives--;
            return 0;
        }
    }
    
    printf("\n✅ ¡Correcto! +200 puntos\n");
    score += 200;
    return 1;
}

// Desafío criptográfico
static int crypto_challenge(void) {
    clear_screen();
    print_banner("🔐 DESAFÍO CRIPTOGRÁFICO - FASE 4");
    
    printf("\n🎯 Objetivo: Descifra el mensaje oculto\n");
    printf("📝 Instrucciones: El mensaje está cifrado con ROT13 + XOR\n");
    printf("⏰ Tiempo límite: 120 segundos\n\n");
    
    // Mensaje cifrado
    char encrypted[] = "Uryyb, jrypbzr gb gur puneqba!";
    char key[] = "ChronoVMSmurf";
    
    printf("🔒 Mensaje cifrado: %s\n", encrypted);
    printf("🔑 Clave: %s\n", key);
    printf("🔍 ¿Cuál es el mensaje original? ");
    
    char answer[256];
    if (fgets(answer, sizeof(answer), stdin) == NULL) {
        printf("\n❌ Error de entrada!\n");
        return 0;
    }
    
    // Eliminar newline
    answer[strcspn(answer, "\n")] = 0;
    
    // Verificar respuesta (ROT13 de "Hello, welcome to the challenge!")
    if (strcmp(answer, "Hello, welcome to the challenge!") == 0) {
        printf("\n✅ ¡Correcto! +300 puntos\n");
        score += 300;
        return 1;
    } else {
        printf("\n❌ Incorrecto! Perdiste una vida.\n");
        lives--;
        return 0;
    }
}

// Desafío de debugging de VM
static int vm_debugging_challenge(void) {
    clear_screen();
    print_banner("🐛 DESAFÍO DE DEBUGGING - FASE 5");
    
    printf("\n🎯 Objetivo: Encuentra el error en el bytecode de la VM\n");
    printf("📝 Instrucciones: Analiza el bytecode y encuentra la instrucción incorrecta\n");
    printf("⏰ Tiempo límite: 180 segundos\n\n");
    
    // Bytecode con error
    uint8_t buggy_bytecode[] = {
        0x01, 0x00, 0x00, 0x00, 0x48,  // VM_LOAD r0, 'H' ✓
        0x01, 0x01, 0x00, 0x00, 0x54,  // VM_LOAD r1, 'T' ✓
        0x01, 0x02, 0x00, 0x00, 0x42,  // VM_LOAD r2, 'B' ✓
        0x01, 0x03, 0x00, 0x00, 0x7B,  // VM_LOAD r3, '{' ✓
        0x01, 0x04, 0x00, 0x00, 0x43,  // VM_LOAD r4, 'C' ✓
        0x01, 0x05, 0x00, 0x00, 0x68,  // VM_LOAD r5, 'h' ✓
        0x01, 0x06, 0x00, 0x00, 0x72,  // VM_LOAD r6, 'r' ✓
        0x01, 0x07, 0x00, 0x00, 0x6F,  // VM_LOAD r7, 'o' ✓
        0x01, 0x08, 0x00, 0x00, 0x6E,  // VM_LOAD r8, 'n' ✓
        0x01, 0x09, 0x00, 0x00, 0x6F,  // VM_LOAD r9, 'o' ✓
        0x01, 0x0A, 0x00, 0x00, 0x56,  // VM_LOAD r10, 'V' ✓
        0x01, 0x0B, 0x00, 0x00, 0x4D,  // VM_LOAD r11, 'M' ✓
        0x01, 0x0C, 0x00, 0x00, 0x5F,  // VM_LOAD r12, '_' ✓
        0x01, 0x0D, 0x00, 0x00, 0x53,  // VM_LOAD r13, 'S' ✓
        0x01, 0x0E, 0x00, 0x00, 0x6D,  // VM_LOAD r14, 'm' ✓
        0x01, 0x0F, 0x00, 0x00, 0x75,  // VM_LOAD r15, 'u' ✓
        0x01, 0x00, 0x00, 0x00, 0x72,  // VM_LOAD r0, 'r' ✓
        0x01, 0x01, 0x00, 0x00, 0x66,  // VM_LOAD r1, 'f' ✓
        0x01, 0x02, 0x00, 0x00, 0x5F,  // VM_LOAD r2, '_' ✓
        0x01, 0x03, 0x00, 0x00, 0x4C,  // VM_LOAD r3, 'L' ✓
        0x01, 0x04, 0x00, 0x00, 0x6F,  // VM_LOAD r4, 'o' ✓
        0x01, 0x05, 0x00, 0x00, 0x63,  // VM_LOAD r5, 'c' ✓
        0x01, 0x06, 0x00, 0x00, 0x6B,  // VM_LOAD r6, 'k' ✓
        0x01, 0x07, 0x00, 0x00, 0x5F,  // VM_LOAD r7, '_' ✓
        0x01, 0x08, 0x00, 0x00, 0x56,  // VM_LOAD r8, 'V' ✓
        0x01, 0x09, 0x00, 0x00, 0x4D,  // VM_LOAD r9, 'M' ✓
        0x01, 0x0A, 0x00, 0x00, 0x7D,  // VM_LOAD r10, '}' ✓
        0x0E, 0x00, 0x00, 0x00, 0x00   // VM_HALT ✓
    };
    
    printf("🔍 Bytecode a analizar:\n");
    for (int i = 0; i < sizeof(buggy_bytecode); i += 5) {
        printf("0x%02X 0x%02X 0x%02X 0x%02X 0x%02X\n", 
               buggy_bytecode[i], buggy_bytecode[i+1], 
               buggy_bytecode[i+2], buggy_bytecode[i+3], buggy_bytecode[i+4]);
    }
    
    printf("\n🔍 ¿En qué línea está el error? (1-6): ");
    int line;
    if (scanf("%d", &line) != 1) {
        printf("\n❌ Entrada inválida!\n");
        return 0;
    }
    
    // El error está en la línea 4 (índice 3) - VM_LOAD r4, 'C' debería ser VM_LOAD r4, 'S'
    if (line == 4) {
        printf("\n✅ ¡Correcto! +400 puntos\n");
        score += 400;
        return 1;
    } else {
        printf("\n❌ Incorrecto! Perdiste una vida.\n");
        lives--;
        return 0;
    }
}

// Jefe final
static int final_boss(void) {
    clear_screen();
    print_banner("👹 JEFE FINAL - FASE 6");
    
    printf("\n🎯 Objetivo: Derrota al jefe final resolviendo todos los desafíos\n");
    printf("📝 Instrucciones: Responde correctamente a 5 preguntas consecutivas\n");
    printf("⏰ Tiempo límite: 300 segundos\n\n");
    
    const char *questions[] = {
        "¿Cuál es la clave de validación del reto?",
        "¿Cuántas instrucciones tiene la VM?",
        "¿Qué algoritmo criptográfico se usa?",
        "¿Cuál es el checksum esperado?",
        "¿Cuál es el flag final?"
    };
    
    const char *answers[] = {
        "ChronoVMSmurf",
        "15",
        "SHA1 modificado + Caja S + Autómata celular",
        "0x42A433D3",
        "HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}"
    };
    
    int correct = 0;
    for (int i = 0; i < 5; i++) {
        printf("❓ Pregunta %d/5: %s\n", i+1, questions[i]);
        printf("🔍 Respuesta: ");
        
        char answer[256];
        if (fgets(answer, sizeof(answer), stdin) == NULL) {
            printf("\n❌ Error de entrada!\n");
            return 0;
        }
        
        // Eliminar newline
        answer[strcspn(answer, "\n")] = 0;
        
        if (strcmp(answer, answers[i]) == 0) {
            printf("✅ ¡Correcto!\n\n");
            correct++;
        } else {
            printf("❌ Incorrecto! La respuesta era: %s\n\n", answers[i]);
            lives--;
            if (lives <= 0) {
                printf("💀 ¡GAME OVER! Te quedaste sin vidas.\n");
                return 0;
            }
        }
    }
    
    if (correct >= 4) {
        printf("🏆 ¡VICTORIA! +1000 puntos\n");
        score += 1000;
        return 1;
    } else {
        printf("💀 ¡DERROTA! No lograste derrotar al jefe final.\n");
        return 0;
    }
}

// Sistema narrativo complejo
static void show_story_intro(void) {
    clear_screen();
    print_ascii_art(
        "  _____ _                      _   __  __ \n"
        " / ____| |                    | | |  \\/  |\n"
        "| |    | |__  _ __ ___  _ __   | | | \\  / |\n"
        "| |    | '_ \\| '__/ _ \\| '_ \\  | | | |\\/| |\n"
        "| |____| | | | | | (_) | | | | |_| | |  | |\n"
        " \\_____|_| |_|_|  \\___/|_| |_|\\___/|_|  |_|\n"
        "\n"
        "    🎮 CHRONO VM CHALLENGE - EDICIÓN EXTREMA 🎮\n"
        "    ============================================\n"
    );
    
    printf("\n🌃 NARRATIVA: 'EL INCIDENTE CHRONOVM'\n");
    printf("=====================================\n\n");
    
    printf("📅 Fecha: 15 de Marzo, 2024\n");
    printf("🕐 Hora: 03:47 AM\n");
    printf("📍 Ubicación: Centro de Datos Abandonado, Sector 7\n\n");
    
    printf("🔍 SITUACIÓN:\n");
    printf("   Eres %s, un hacker de élite especializado en sistemas\n", player_name);
    printf("   críticos. Has sido contactado por una fuente anónima que\n");
    printf("   afirma haber descubierto algo perturbador en un sistema\n");
    printf("   abandonado. Un programa llamado 'ChronoVM' ha comenzado\n");
    printf("   a mostrar comportamientos anómalos, y tu misión es\n");
    printf("   investigar qué está sucediendo.\n\n");
    
    printf("⚠️  ADVERTENCIAS:\n");
    printf("   - El sistema tiene protecciones extremas\n");
    printf("   - Se han reportado múltiples flags falsos\n");
    printf("   - La verdad está enterrada en capas de engaño\n");
    printf("   - Un error podría costarte la vida\n\n");
    
    printf("🎯 OBJETIVO:\n");
    printf("   Encuentra la verdad detrás de ChronoVM y descubre\n");
    printf("   el flag real entre las decenas de señuelos.\n\n");
    
    printf("💀 RECURSOS:\n");
    printf("   - 3 vidas (errores críticos te matan)\n");
    printf("   - Herramientas de análisis limitadas\n");
    printf("   - Tiempo limitado antes de que el sistema se autodestruya\n\n");
    
    printf("🚨 ¿Estás listo para enfrentar la verdad? (s/n): ");
}

static void show_phase_intro(phase_t phase) {
    clear_screen();
    
    switch (phase) {
        case PHASE_INVESTIGATION:
            print_banner("🔍 FASE 1: INVESTIGACIÓN INICIAL");
            printf("\n📍 Ubicación: Centro de Datos - Nivel 1\n");
            printf("🎯 Misión: Analizar el sistema ChronoVM\n\n");
            printf("📋 Descripción:\n");
            printf("   Has llegado al centro de datos abandonado. El aire\n");
            printf("   está cargado de electricidad estática y el silencio\n");
            printf("   es inquietante. En el centro de la sala, una terminal\n");
            printf("   parpadea con un mensaje: 'ChronoVM v2.0 - TimeLock Active'\n\n");
            printf("🔍 Tu análisis inicial revela:\n");
            printf("   - Múltiples procesos ejecutándose\n");
            printf("   - Comunicaciones encriptadas\n");
            printf("   - Patrones de datos anómalos\n");
            printf("   - Posibles flags ocultos en la memoria\n\n");
            break;
            
        case PHASE_DECRYPTION:
            print_banner("🔐 FASE 2: DESCIFRADO DE COMUNICACIONES");
            printf("\n📍 Ubicación: Centro de Datos - Nivel 2\n");
            printf("🎯 Misión: Descifrar las comunicaciones encriptadas\n\n");
            printf("📋 Descripción:\n");
            printf("   Has descubierto que ChronoVM está comunicándose con\n");
            printf("   sistemas externos. Las comunicaciones están cifradas\n");
            printf("   con un algoritmo híbrido complejo que combina:\n");
            printf("   - SHA1 modificado con constantes alteradas\n");
            printf("   - Caja S personalizada de 256 bytes\n");
            printf("   - Autómata celular regla 30 con variaciones\n\n");
            printf("⚠️  Advertencia: Se han detectado múltiples flags falsos\n");
            printf("   en las comunicaciones. Solo uno es real.\n\n");
            break;
            
        case PHASE_VM_ANALYSIS:
            print_banner("🖥️  FASE 3: ANÁLISIS DE MÁQUINA VIRTUAL");
            printf("\n📍 Ubicación: Centro de Datos - Nivel 3\n");
            printf("🎯 Misión: Analizar la máquina virtual interna\n\n");
            printf("📋 Descripción:\n");
            printf("   ChronoVM ejecuta una máquina virtual personalizada\n");
            printf("   con 15 instrucciones únicas. El bytecode está\n");
            printf("   cifrado y disperso por toda la memoria del sistema.\n\n");
            printf("🔍 Características de la VM:\n");
            printf("   - 15 instrucciones personalizadas\n");
            printf("   - Sistema de registros de 16 bits\n");
            printf("   - Memoria virtual de 4KB\n");
            printf("   - Stack de llamadas de 256 bytes\n");
            printf("   - Bytecode cifrado con XOR+RC4\n\n");
            break;
            
        case PHASE_MEMORY_DIVE:
            print_banner("🧠 FASE 4: INMERSIÓN EN MEMORIA");
            printf("\n📍 Ubicación: Centro de Datos - Nivel 4\n");
            printf("🎯 Misión: Explorar la memoria del sistema\n\n");
            printf("📋 Descripción:\n");
            printf("   Has logrado acceder a la memoria del sistema.\n");
            printf("   Los datos están fragmentados y dispersos, pero\n");
            printf("   puedes ver patrones que sugieren la presencia\n");
            printf("   de múltiples flags. Algunos son señuelos,\n");
            printf("   otros son pistas, y solo uno es real.\n\n");
            printf("⚠️  Peligro: El sistema detecta intrusiones en memoria\n");
            printf("   y activará contramedidas si te descubren.\n\n");
            break;
            
        case PHASE_CRYPTO_BREAK:
            print_banner("🔓 FASE 5: RUPTURA CRIPTOGRÁFICA");
            printf("\n📍 Ubicación: Centro de Datos - Nivel 5\n");
            printf("🎯 Misión: Romper el cifrado final\n\n");
            printf("📋 Descripción:\n");
            printf("   Has identificado el algoritmo criptográfico final.\n");
            printf("   Es un sistema híbrido que combina múltiples\n");
            printf("   técnicas de cifrado. La clave está oculta en\n");
            printf("   los patrones de la máquina virtual.\n\n");
            printf("🔑 Pistas descubiertas:\n");
            printf("   - La clave contiene 'ChronoVM'\n");
            printf("   - Incluye una referencia a 'Smurf'\n");
            printf("   - El checksum final es 0x40008000\n");
            printf("   - El flag real está encriptado en el bytecode\n\n");
            break;
            
        case PHASE_FINAL_REVELATION:
            print_banner("💡 FASE 6: REVELACIÓN FINAL");
            printf("\n📍 Ubicación: Centro de Datos - Nivel 6\n");
            printf("🎯 Misión: Descubrir la verdad\n\n");
            printf("📋 Descripción:\n");
            printf("   Has llegado al núcleo del sistema. Aquí es donde\n");
            printf("   se encuentra la verdad sobre ChronoVM. El sistema\n");
            printf("   está en modo de autodestrucción y tienes tiempo\n");
            printf("   limitado para encontrar el flag real.\n\n");
            printf("🚨 URGENTE: El sistema se autodestruirá en 5 minutos\n");
            printf("   si no encuentras el flag correcto.\n\n");
            break;
            
        case PHASE_ESCAPE:
            print_banner("🏃 FASE 7: ESCAPE");
            printf("\n📍 Ubicación: Centro de Datos - Salida\n");
            printf("🎯 Misión: Escapar con el flag real\n\n");
            printf("📋 Descripción:\n");
            printf("   Has encontrado el flag real, pero el sistema\n");
            printf("   está colapsando. Debes escapar antes de que\n");
            printf("   todo explote. El flag real es tu única esperanza\n");
            printf("   de sobrevivir.\n\n");
            break;
            
        default:
            break;
    }
}

// Función principal interactiva
int main(int argc, char *argv[]) {
    // Verificar argumentos
    if (argc > 1 && strcmp(argv[1], "--enable-fragments") == 0) {
        fragments_enabled = 1;
    }
    
    // Protecciones anti-debugging
    if (ANTI_DEBUG) {
        anti_debug_check();
    }
    
    if (INTEGRITY_CHECK) {
        integrity_check();
    }
    
    // Inicializar juego
    clear_screen();
    
    // Mostrar introducción narrativa
    show_story_intro();
    
    char response;
    if (scanf(" %c", &response) == 1 && (response == 's' || response == 'S')) {
        printf("\n🚀 ¡Perfecto %s! Comenzando la aventura...\n", player_name);
        sleep(2);
    } else {
        printf("\n💀 Has decidido no enfrentar la verdad. El sistema se autodestruye.\n");
        return 0;
    }
    
    // Bucle principal del juego narrativo
    while (current_phase < PHASE_COMPLETE && lives > 0) {
        clear_screen();
        show_phase_intro(current_phase);
        print_stats();
        
        int success = 0;
        
        switch (current_phase) {
            case PHASE_INTRO:
                printf("🎮 Presiona ENTER para comenzar la investigación...");
                getchar();
                current_phase = PHASE_INVESTIGATION;
                success = 1;
                break;
                
            case PHASE_INVESTIGATION:
                success = investigation_phase();
                if (success) current_phase = PHASE_DECRYPTION;
                break;
                
            case PHASE_DECRYPTION:
                success = decryption_phase();
                if (success) current_phase = PHASE_VM_ANALYSIS;
                break;
                
            case PHASE_VM_ANALYSIS:
                success = vm_analysis_phase();
                if (success) current_phase = PHASE_MEMORY_DIVE;
                break;
                
            case PHASE_MEMORY_DIVE:
                success = memory_dive_phase();
                if (success) current_phase = PHASE_CRYPTO_BREAK;
                break;
                
            case PHASE_CRYPTO_BREAK:
                success = crypto_break_phase();
                if (success) current_phase = PHASE_FINAL_REVELATION;
                break;
                
            case PHASE_FINAL_REVELATION:
                success = final_revelation_phase();
                if (success) current_phase = PHASE_ESCAPE;
                break;
                
            case PHASE_ESCAPE:
                success = escape_phase();
                if (success) current_phase = PHASE_COMPLETE;
                break;
                
            default:
                break;
        }
        
        if (!success && lives > 0) {
            printf("\n💔 Fallaste en esta fase. Vidas restantes: %d\n", lives);
            printf("🔄 Presiona ENTER para reintentar...");
            getchar();
        }
        
        if (lives <= 0) {
            clear_screen();
            print_banner("💀 GAME OVER");
            printf("\n😢 Lo siento %s, te quedaste sin vidas.\n", player_name);
            printf("📊 Puntuación final: %d puntos\n", score);
            printf("🎯 Puntuación máxima posible: 5000 puntos\n");
            printf("💡 Intenta de nuevo para mejorar tu puntuación!\n");
            break;
        }
    }
    
    // Pantalla de victoria
    if (current_phase == PHASE_COMPLETE) {
        clear_screen();
        print_banner("🏆 ¡VICTORIA TOTAL!");
        
        printf("\n🎉 ¡FELICIDADES %s! 🎉\n", player_name);
        printf("🎯 Has completado el reto más complejo de reversing\n");
        printf("📊 Puntuación final: %d/2150 puntos\n", score);
        
        // Calcular calificación
        int percentage = (score * 100) / 2150;
        const char *grade;
        if (percentage >= 95) grade = "S+ (LEGENDARIO)";
        else if (percentage >= 90) grade = "S (ÉPICO)";
        else if (percentage >= 80) grade = "A+ (EXCELENTE)";
        else if (percentage >= 70) grade = "A (MUY BUENO)";
        else if (percentage >= 60) grade = "B (BUENO)";
        else grade = "C (ACEPTABLE)";
        
        printf("🏅 Calificación: %s\n", grade);
        
        // Mostrar flag
        printf("\n🎁 ¡Tu recompensa!\n");
        printf("🏴 Flag: HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}\n");
        
        // Crear fragmentos si está habilitado
        if (fragments_enabled) {
            create_fragments();
            printf("🧩 Fragmentos de flag creados en /dev/shm/\n");
        }
        
        printf("\n🎮 ¡Gracias por jugar ChronoVM Challenge!\n");
        printf("🔗 Comparte tu puntuación con otros hackers\n");
    }
    
    // Limpiar fragmentos
    if (fragments_enabled) {
        for (int i = 0; i < 3; i++) {
            char filename[64];
            snprintf(filename, sizeof(filename), "/dev/shm/chrono_frag_%d", i);
            unlink(filename);
        }
    }
    
    if (bytecode) {
        free(bytecode);
    }
    
    return 0;
}