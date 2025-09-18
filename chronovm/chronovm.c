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

// Sistema de fases interactivas
typedef enum {
    PHASE_INTRO = 0,
    PHASE_PUZZLE_1,
    PHASE_PUZZLE_2,
    PHASE_PUZZLE_3,
    PHASE_CRYPTO_CHALLENGE,
    PHASE_VM_DEBUGGING,
    PHASE_FINAL_BOSS,
    PHASE_COMPLETE
} phase_t;

static phase_t current_phase = PHASE_INTRO;
static int score = 0;
static int lives = 3;
static char player_name[64] = {0};
static int hints_used = 0;
static int time_limit = 300; // 5 minutos por fase
static time_t phase_start_time;

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
           current_phase == PHASE_PUZZLE_1 ? "Puzzle 1" :
           current_phase == PHASE_PUZZLE_2 ? "Puzzle 2" :
           current_phase == PHASE_PUZZLE_3 ? "Puzzle 3" :
           current_phase == PHASE_CRYPTO_CHALLENGE ? "Crypto Challenge" :
           current_phase == PHASE_VM_DEBUGGING ? "VM Debugging" :
           current_phase == PHASE_FINAL_BOSS ? "Final Boss" : "Completado");
    printf("└─────────────────────────────────────────────────────────────┘\n");
}

// Minijuego 1: Puzzle de memoria
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
    
    // ASCII Art de bienvenida
    print_ascii_art(
        "  _____ _                      _   __  __ \n"
        " / ____| |                    | | |  \\/  |\n"
        "| |    | |__  _ __ ___  _ __   | | | \\  / |\n"
        "| |    | '_ \\| '__/ _ \\| '_ \\  | | | |\\/| |\n"
        "| |____| | | | | | (_) | | | | |_| | |  | |\n"
        " \\_____|_| |_|_|  \\___/|_| |_|\\___/|_|  |_|\n"
        "\n"
        "    🎮 CHRONO VM CHALLENGE - EDICIÓN INTERACTIVA 🎮\n"
        "    ================================================\n"
    );
    
    // Solicitar nombre del jugador
    printf("\n👋 ¡Bienvenido al reto más complejo de reversing!\n");
    printf("🎯 Tu misión: Resolver 6 fases de desafíos progresivos\n");
    printf("💀 Tienes 3 vidas - úsalas sabiamente\n");
    printf("🏆 Puntuación máxima: 2150 puntos\n\n");
    
    printf("🔤 Ingresa tu nombre de hacker: ");
    if (fgets(player_name, sizeof(player_name), stdin)) {
        player_name[strcspn(player_name, "\n")] = 0;
    } else {
        strcpy(player_name, "Anonymous");
    }
    
    printf("\n🚀 ¡Perfecto %s! Comenzando la aventura...\n", player_name);
    sleep(2);
    
    // Bucle principal del juego
    while (current_phase < PHASE_COMPLETE && lives > 0) {
        clear_screen();
        print_stats();
        
        int success = 0;
        
        switch (current_phase) {
            case PHASE_INTRO:
                print_banner("🎬 INTRODUCCIÓN");
                printf("\n📖 Historia:\n");
                printf("   Un viejo programa llamado ChronoVM ha aparecido en un sistema abandonado.\n");
                printf("   Parece un simple reloj digital, pero en realidad esconde un mecanismo\n");
                printf("   de validación muy elaborado. Tu misión es descubrir cómo funciona\n");
                printf("   y revelar el secreto que protege. El tiempo corre...\n\n");
                printf("🎮 Presiona ENTER para comenzar...");
                getchar();
                current_phase = PHASE_PUZZLE_1;
                success = 1;
                break;
                
            case PHASE_PUZZLE_1:
                success = memory_puzzle();
                if (success) current_phase = PHASE_PUZZLE_2;
                break;
                
            case PHASE_PUZZLE_2:
                success = math_puzzle();
                if (success) current_phase = PHASE_PUZZLE_3;
                break;
                
            case PHASE_PUZZLE_3:
                success = pattern_puzzle();
                if (success) current_phase = PHASE_CRYPTO_CHALLENGE;
                break;
                
            case PHASE_CRYPTO_CHALLENGE:
                success = crypto_challenge();
                if (success) current_phase = PHASE_VM_DEBUGGING;
                break;
                
            case PHASE_VM_DEBUGGING:
                success = vm_debugging_challenge();
                if (success) current_phase = PHASE_FINAL_BOSS;
                break;
                
            case PHASE_FINAL_BOSS:
                success = final_boss();
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
            printf("🎯 Puntuación máxima posible: 2150 puntos\n");
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