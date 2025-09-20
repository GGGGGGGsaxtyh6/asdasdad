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

// Protecciones anti-debugging extremas
#define ANTI_DEBUG 1
#define INTEGRITY_CHECK 1
#define SELF_MODIFYING 1
#define VM_PROTECTION 1
#define CRYPTO_LAYERS 10

// Configuración del reto
#define MAX_PHASES 25
#define MAX_LIVES 3
#define MAX_SCORE 50000
#define TIME_LIMIT 21600  // 6 horas en segundos

// Estructuras de datos complejas
typedef struct {
    uint32_t magic;
    uint32_t version;
    uint32_t checksum;
    uint32_t flags;
    char data[512];
    uint32_t crc32;
    uint32_t timestamp;
    uint32_t nonce;
} challenge_header_t;

typedef struct {
    uint8_t opcode;
    uint8_t reg1;
    uint8_t reg2;
    uint8_t reg3;
    uint32_t immediate;
    uint32_t address;
    uint32_t flags;
} vm_instruction_t;

typedef struct {
    uint32_t registers[32];
    uint32_t memory[8192];
    uint32_t stack[2048];
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
    char discovered_secrets[100][256];
    uint32_t secret_count;
    uint32_t fake_flags_found;
    uint32_t real_flag_found;
    uint32_t crypto_keys[20];
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

// Variables globales
static game_state_t gs = {0};
static vm_state_t vm = {0};
static challenge_header_t header = {0};
static uint8_t encrypted_bytecode[16384] = {0};
static size_t bytecode_size = 0;
static uint8_t key_schedule[512] = {0};
static uint32_t sbox[512] = {0};
static uint32_t round_keys[64] = {0};
static uint8_t memory_pool[131072] = {0};
static uint32_t memory_checksum = 0;
static int anti_debug_count = 0;
static int integrity_failures = 0;
static int vm_crashes = 0;
static int crypto_failures = 0;

// Funciones de protección anti-debugging extremas
static void anti_debug_check_1(void) {
    if (ptrace(PTRACE_TRACEME, 0, 0, 0) == -1) {
        printf("🚨 DETECCIÓN DE DEBUGGER - SISTEMA COMPROMETIDO\n");
        exit(1);
    }
}

static void anti_debug_check_2(void) {
    struct rlimit rlim;
    getrlimit(RLIMIT_CORE, &rlim);
    if (rlim.rlim_cur != 0) {
        printf("🚨 DETECCIÓN DE CORE DUMP - SISTEMA COMPROMETIDO\n");
        exit(1);
    }
}

static void anti_debug_check_3(void) {
    char buf[256];
    snprintf(buf, sizeof(buf), "/proc/%d/status", getpid());
    FILE *f = fopen(buf, "r");
    if (f) {
        char line[256];
        while (fgets(line, sizeof(line), f)) {
            if (strstr(line, "TracerPid:") && strstr(line, "0") == NULL) {
                printf("🚨 DETECCIÓN DE TRACER - SISTEMA COMPROMETIDO\n");
                fclose(f);
                exit(1);
            }
        }
        fclose(f);
    }
}

static void anti_debug_check_4(void) {
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);
    usleep(1000);
    clock_gettime(CLOCK_MONOTONIC, &end);
    long elapsed = (end.tv_sec - start.tv_sec) * 1000000000 + (end.tv_nsec - start.tv_nsec);
    if (elapsed > 2000000) { // Más de 2ms indica debugger
        printf("🚨 DETECCIÓN DE TIMING ATTACK - SISTEMA COMPROMETIDO\n");
        exit(1);
    }
}

static void anti_debug_check_5(void) {
    uint32_t eax, ebx, ecx, edx;
    asm volatile("cpuid" : "=a"(eax), "=b"(ebx), "=c"(ecx), "=d"(edx) : "a"(1));
    if (ecx & (1 << 20)) { // VMX bit
        printf("🚨 DETECCIÓN DE VIRTUALIZACIÓN - SISTEMA COMPROMETIDO\n");
        exit(1);
    }
}

static void anti_debug_check_6(void) {
    char *ptr = mmap(NULL, 4096, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (ptr == MAP_FAILED) return;
    
    // Escribir código que se auto-modifica
    uint8_t code[] = {0x90, 0x90, 0x90, 0x90}; // NOPs
    memcpy(ptr, code, sizeof(code));
    
    // Cambiar permisos a ejecutable
    mprotect(ptr, 4096, PROT_READ | PROT_EXEC);
    
    // Ejecutar código
    void (*func)() = (void(*)())ptr;
    func();
    
    munmap(ptr, 4096);
}

static void anti_debug_check_7(void) {
    // Verificar integridad del binario en tiempo real
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
    
    // Calcular checksum simple
    uint32_t checksum = 0;
    for (long i = 0; i < size; i++) {
        checksum = (checksum << 1) ^ buffer[i];
    }
    
    // Verificar contra checksum esperado (calculado dinámicamente)
    uint32_t expected = 0xDEADBEEF; // Esto se calculará dinámicamente
    if (checksum != expected) {
        printf("🚨 DETECCIÓN DE MODIFICACIÓN - SISTEMA COMPROMETIDO\n");
        free(buffer);
        exit(1);
    }
    
    free(buffer);
}

static void anti_debug_check_8(void) {
    // Verificar que no hay breakpoints
    uint8_t *code_ptr = (uint8_t*)anti_debug_check_8;
    for (int i = 0; i < 100; i++) {
        if (code_ptr[i] == 0xCC) { // INT3 breakpoint
            printf("🚨 DETECCIÓN DE BREAKPOINT - SISTEMA COMPROMETIDO\n");
            exit(1);
        }
    }
}

static void anti_debug_check_9(void) {
    // Verificar stack canary
    volatile uint64_t canary = 0x123456789ABCDEF0;
    volatile char buffer[64];
    strcpy((char*)buffer, "test");
    if (canary != 0x123456789ABCDEF0) {
        printf("🚨 DETECCIÓN DE STACK OVERFLOW - SISTEMA COMPROMETIDO\n");
        exit(1);
    }
}

static void anti_debug_check_10(void) {
    // Verificar que el proceso no está siendo debuggeado
    int fd = open("/proc/self/stat", O_RDONLY);
    if (fd >= 0) {
        char buf[1024];
        read(fd, buf, sizeof(buf));
        close(fd);
        
        // Buscar el estado del proceso
        char *state = strchr(buf, ')') + 2;
        if (state && *state == 't') { // Estado 't' = traced
            printf("🚨 DETECCIÓN DE PROCESO TRAZADO - SISTEMA COMPROMETIDO\n");
            exit(1);
        }
    }
}

// Función de verificación de integridad
static void integrity_check(void) {
    if (!INTEGRITY_CHECK) return;
    
    // Verificar múltiples checksums
    uint32_t checksum1 = 0, checksum2 = 0, checksum3 = 0;
    
    // Checksum del código
    uint8_t *code_start = (uint8_t*)integrity_check;
    for (int i = 0; i < 1000; i++) {
        checksum1 = (checksum1 << 1) ^ code_start[i];
    }
    
    // Checksum de datos
    for (int i = 0; i < sizeof(gs); i++) {
        checksum2 = (checksum2 << 1) ^ ((uint8_t*)&gs)[i];
    }
    
    // Checksum de memoria
    for (int i = 0; i < sizeof(memory_pool); i++) {
        checksum3 = (checksum3 << 1) ^ memory_pool[i];
    }
    
    uint32_t expected = 0xDEADBEEF; // Se calculará dinámicamente
    if (checksum1 != expected || checksum2 != expected || checksum3 != expected) {
        printf("🚨 VERIFICACIÓN DE INTEGRIDAD FALLIDA - SISTEMA COMPROMETIDO\n");
        exit(1);
    }
}

// Sistema de criptografía multi-capa
static void init_crypto_system(void) {
    // Inicializar S-box personalizado
    for (int i = 0; i < 512; i++) {
        sbox[i] = (i * 0x9E3779B9) ^ 0x12345678;
        sbox[i] = ((sbox[i] << 13) | (sbox[i] >> 19)) ^ 0x87654321;
    }
    
    // Inicializar key schedule
    for (int i = 0; i < 512; i++) {
        key_schedule[i] = (i * 0x5A827999) ^ 0xFEDCBA98;
    }
    
    // Inicializar round keys
    for (int i = 0; i < 64; i++) {
        round_keys[i] = (i * 0x6ED9EBA1) ^ 0x13579BDF;
    }
}

// Algoritmo de cifrado personalizado
static void custom_encrypt(uint8_t *data, size_t len, uint32_t key) {
    for (size_t i = 0; i < len; i++) {
        data[i] ^= (key >> (i % 32)) & 0xFF;
        data[i] = sbox[data[i] % 512];
        data[i] ^= key_schedule[i % 512];
        data[i] = ((data[i] << 3) | (data[i] >> 5)) ^ round_keys[i % 64];
    }
}

// Máquina virtual personalizada
static void init_vm(void) {
    memset(&vm, 0, sizeof(vm));
    vm.running = 1;
    vm.pc = 0;
    vm.sp = 2047;
    
    // Inicializar memoria con datos aleatorios
    for (int i = 0; i < 8192; i++) {
        vm.memory[i] = (i * 0x9E3779B9) ^ 0x12345678;
    }
}

static void vm_execute_instruction(vm_instruction_t *inst) {
    switch (inst->opcode) {
        case 0x00: // NOP
            break;
        case 0x01: // LOAD
            vm.registers[inst->reg1] = inst->immediate;
            break;
        case 0x02: // STORE
            vm.memory[inst->address] = vm.registers[inst->reg1];
            break;
        case 0x03: // ADD
            vm.registers[inst->reg1] = vm.registers[inst->reg2] + vm.registers[inst->reg3];
            break;
        case 0x04: // SUB
            vm.registers[inst->reg1] = vm.registers[inst->reg2] - vm.registers[inst->reg3];
            break;
        case 0x05: // MUL
            vm.registers[inst->reg1] = vm.registers[inst->reg2] * vm.registers[inst->reg3];
            break;
        case 0x06: // XOR
            vm.registers[inst->reg1] = vm.registers[inst->reg2] ^ vm.registers[inst->reg3];
            break;
        case 0x07: // SHL
            vm.registers[inst->reg1] = vm.registers[inst->reg2] << vm.registers[inst->reg3];
            break;
        case 0x08: // SHR
            vm.registers[inst->reg1] = vm.registers[inst->reg2] >> vm.registers[inst->reg3];
            break;
        case 0x09: // CMP
            vm.flags = (vm.registers[inst->reg1] == vm.registers[inst->reg2]) ? 1 : 0;
            break;
        case 0x0A: // JMP
            vm.pc = inst->address;
            break;
        case 0x0B: // JZ
            if (vm.flags) vm.pc = inst->address;
            break;
        case 0x0C: // CALL
            vm.stack[vm.sp--] = vm.pc;
            vm.pc = inst->address;
            break;
        case 0x0D: // RET
            vm.pc = vm.stack[++vm.sp];
            break;
        case 0x0E: // HALT
            vm.running = 0;
            break;
        default:
            vm_crashes++;
            break;
    }
    vm.pc++;
    gs.vm_cycles++;
}

// Sistema de fases del reto
typedef enum {
    PHASE_INTRO = 0,
    PHASE_INVESTIGATION,
    PHASE_MEMORY_ANALYSIS,
    PHASE_CRYPTO_LAYER_1,
    PHASE_CRYPTO_LAYER_2,
    PHASE_CRYPTO_LAYER_3,
    PHASE_CRYPTO_LAYER_4,
    PHASE_CRYPTO_LAYER_5,
    PHASE_CRYPTO_LAYER_6,
    PHASE_CRYPTO_LAYER_7,
    PHASE_CRYPTO_LAYER_8,
    PHASE_CRYPTO_LAYER_9,
    PHASE_CRYPTO_LAYER_10,
    PHASE_VM_ANALYSIS,
    PHASE_VM_EXECUTION,
    PHASE_VM_DEBUGGING,
    PHASE_MEMORY_CORRUPTION,
    PHASE_STACK_OVERFLOW,
    PHASE_HEAP_EXPLOITATION,
    PHASE_FORMAT_STRING,
    PHASE_RETURN_ORIENTED,
    PHASE_JIT_COMPILATION,
    PHASE_SIDE_CHANNEL,
    PHASE_FINAL_BOSS,
    PHASE_ESCAPE,
    PHASE_COMPLETE
} phase_t;

// Funciones de interfaz
static void clear_screen(void) {
    printf("\033[2J\033[H");
}

static void print_banner(const char *title) {
    printf("\n╔══════════════════════════════════════════════════════════════╗\n");
    printf("║ %-60s ║\n", title);
    printf("╚══════════════════════════════════════════════════════════════╝\n");
}

static void print_stats(void) {
    printf("\n┌─ ESTADÍSTICAS ──────────────────────────────────────────────┐\n");
    printf("│ Jugador: %-20s Puntuación: %-8d │\n", gs.player_name, gs.score);
    printf("│ Vidas: %-3d Pistas: %-3d Fase: %-15d │\n", gs.lives, gs.hints_used, gs.phase);
    printf("│ Conocimiento: %-3d Engaño: %-3d Confianza: %-3d │\n", gs.knowledge_level, gs.deception_level, gs.trust_level);
    printf("│ Flags falsos: %-3d Flag real: %-3s Ciclos VM: %-8d │\n", gs.fake_flags_found, gs.real_flag_found ? "Sí" : "No", gs.vm_cycles);
    printf("│ Accesos memoria: %-8d Intentos debug: %-8d │\n", gs.memory_accesses, gs.debug_attempts);
    printf("│ Fallos integridad: %-8d Crashes VM: %-8d │\n", gs.integrity_failures, gs.vm_crashes);
    printf("└─────────────────────────────────────────────────────────────┘\n");
}

// Función principal
int main(int argc, char *argv[]) {
    // Inicializar sistema
    init_crypto_system();
    init_vm();
    
    // Protecciones anti-debugging
    if (ANTI_DEBUG) {
        anti_debug_check_1();
        anti_debug_check_2();
        anti_debug_check_3();
        anti_debug_check_4();
        anti_debug_check_5();
        anti_debug_check_6();
        anti_debug_check_7();
        anti_debug_check_8();
        anti_debug_check_9();
        anti_debug_check_10();
    }
    
    if (INTEGRITY_CHECK) {
        integrity_check();
    }
    
    // Inicializar estado del juego
    gs.phase = PHASE_INTRO;
    gs.lives = MAX_LIVES;
    gs.score = 0;
    gs.start_time = time(NULL);
    gs.phase_start = gs.start_time;
    
    // Mostrar introducción
    clear_screen();
    print_banner("🎮 CHRONO VM CHALLENGE - EDICIÓN INSANE");
    
    printf("\n🌃 NARRATIVA: 'EL INCIDENTE CHRONOVM'\n");
    printf("=====================================\n\n");
    
    printf("📅 Fecha: 15 de Marzo, 2024\n");
    printf("🕐 Hora: 03:47 AM\n");
    printf("📍 Ubicación: Centro de Datos Abandonado, Sector 7\n\n");
    
    printf("🔍 SITUACIÓN:\n");
    printf("   Eres un hacker de élite especializado en sistemas críticos.\n");
    printf("   Has sido contactado por una fuente anónima que afirma haber\n");
    printf("   descubierto algo perturbador en un sistema abandonado.\n");
    printf("   Un programa llamado 'ChronoVM' ha comenzado a mostrar\n");
    printf("   comportamientos anómalos, y tu misión es investigar qué\n");
    printf("   está sucediendo.\n\n");
    
    printf("⚠️  ADVERTENCIAS:\n");
    printf("   - El sistema tiene protecciones extremas\n");
    printf("   - Se han reportado múltiples flags falsos\n");
    printf("   - La verdad está enterrada en capas de engaño\n");
    printf("   - Un error podría costarte la vida\n");
    printf("   - Tiempo límite: 6 horas\n\n");
    
    printf("🎯 OBJETIVO:\n");
    printf("   Encuentra la verdad detrás de ChronoVM y descubre\n");
    printf("   el flag real entre las decenas de señuelos.\n\n");
    
    printf("💀 RECURSOS:\n");
    printf("   - 3 vidas (errores críticos te matan)\n");
    printf("   - Herramientas de análisis limitadas\n");
    printf("   - Tiempo limitado antes de que el sistema se autodestruya\n\n");
    
    printf("🚨 ¿Estás listo para enfrentar la verdad? (s/n): ");
    
    char response;
    if (scanf(" %c", &response) == 1 && (response == 's' || response == 'S')) {
        printf("\n🚀 ¡Perfecto! Comenzando la aventura...\n");
        sleep(2);
    } else {
        printf("\n💀 Has decidido no enfrentar la verdad. El sistema se autodestruye.\n");
        return 0;
    }
    
    // Bucle principal del juego
    while (gs.phase < PHASE_COMPLETE && gs.lives > 0) {
        clear_screen();
        print_stats();
        
        // Verificar tiempo límite
        time_t current_time = time(NULL);
        if (current_time - gs.start_time > TIME_LIMIT) {
            printf("⏰ TIEMPO AGOTADO - El sistema se autodestruye.\n");
            break;
        }
        
        // Ejecutar fase actual
        switch (gs.phase) {
            case PHASE_INTRO:
                printf("🎮 Presiona ENTER para comenzar la investigación...");
                getchar();
                gs.phase = PHASE_INVESTIGATION;
                break;
                
            case PHASE_INVESTIGATION:
                printf("🔍 FASE 1: INVESTIGACIÓN INICIAL\n");
                printf("   Analizando el sistema ChronoVM...\n");
                printf("   Se han detectado múltiples flags falsos.\n");
                printf("   ¿Cuál crees que es el flag real? (1-10): ");
                
                int choice;
                if (scanf("%d", &choice) == 1) {
                    printf("❌ ¡FALSO! Todos los flags en esta fase son señuelos.\n");
                    printf("   +50 puntos por descubrir la verdad\n");
                    gs.score += 50;
                    gs.fake_flags_found++;
                    gs.phase = PHASE_MEMORY_ANALYSIS;
                }
                break;
                
            case PHASE_MEMORY_ANALYSIS:
                printf("🧠 FASE 2: ANÁLISIS DE MEMORIA\n");
                printf("   Explorando la memoria del sistema...\n");
                printf("   Se han encontrado 20 flags más.\n");
                printf("   ¿Cuál crees que es el flag real? (1-20): ");
                
                if (scanf("%d", &choice) == 1) {
                    printf("❌ ¡FALSO! Todos los flags en memoria son señuelos.\n");
                    printf("   +100 puntos por persistir en la búsqueda\n");
                    gs.score += 100;
                    gs.fake_flags_found += 20;
                    gs.phase = PHASE_CRYPTO_LAYER_1;
                }
                break;
                
            case PHASE_CRYPTO_LAYER_1:
                printf("🔐 FASE 3: CAPA CRIPTOGRÁFICA 1\n");
                printf("   Descifrando comunicaciones encriptadas...\n");
                printf("   Algoritmo: ROT13 + XOR\n");
                printf("   Mensaje: Uryyb, jrypbzr gb gur puneqba!\n");
                printf("   ¿Cuál es el mensaje descifrado? ");
                
                char answer[256];
                if (fgets(answer, sizeof(answer), stdin) != NULL) {
                    answer[strcspn(answer, "\n")] = 0;
                    if (strcmp(answer, "Hello, welcome to the challenge!") == 0) {
                        printf("✅ ¡Correcto! +200 puntos\n");
                        gs.score += 200;
                        gs.knowledge_level++;
                        gs.phase = PHASE_CRYPTO_LAYER_2;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_2:
                printf("🔐 FASE 4: CAPA CRIPTOGRÁFICA 2\n");
                printf("   Descifrando algoritmo híbrido...\n");
                printf("   Algoritmo: SHA1 modificado + Caja S personalizada\n");
                printf("   Clave: ChronoVMSmurf\n");
                printf("   ¿Cuál es la clave de validación? ");
                
                char key[256];
                if (fgets(key, sizeof(key), stdin) != NULL) {
                    key[strcspn(key, "\n")] = 0;
                    if (strcmp(key, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +500 puntos\n");
                        gs.score += 500;
                        gs.knowledge_level += 2;
                        gs.phase = PHASE_CRYPTO_LAYER_3;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_3:
                printf("🔐 FASE 5: CAPA CRIPTOGRÁFICA 3\n");
                printf("   Descifrando autómata celular...\n");
                printf("   Regla: 30 con variaciones\n");
                printf("   Checksum esperado: 0x40008000\n");
                printf("   ¿Cuál es el checksum calculado? ");
                
                uint32_t checksum;
                if (scanf("%x", &checksum) == 1) {
                    if (checksum == 0x40008000) {
                        printf("✅ ¡Correcto! +300 puntos\n");
                        gs.score += 300;
                        gs.knowledge_level++;
                        gs.phase = PHASE_CRYPTO_LAYER_4;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_4:
                printf("🔐 FASE 6: CAPA CRIPTOGRÁFICA 4\n");
                printf("   Descifrando algoritmo de cifrado personalizado...\n");
                printf("   Algoritmo: Multi-capa con S-box personalizado\n");
                printf("   ¿Cuál es la clave de descifrado? ");
                
                char key4[256];
                if (fgets(key4, sizeof(key4), stdin) != NULL) {
                    key4[strcspn(key4, "\n")] = 0;
                    if (strcmp(key4, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +400 puntos\n");
                        gs.score += 400;
                        gs.knowledge_level += 2;
                        gs.phase = PHASE_CRYPTO_LAYER_5;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_5:
                printf("🔐 FASE 7: CAPA CRIPTOGRÁFICA 5\n");
                printf("   Descifrando algoritmo final...\n");
                printf("   Algoritmo: Híbrido con múltiples capas\n");
                printf("   ¿Cuál es la clave final? ");
                
                char key5[256];
                if (fgets(key5, sizeof(key5), stdin) != NULL) {
                    key5[strcspn(key5, "\n")] = 0;
                    if (strcmp(key5, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +600 puntos\n");
                        gs.score += 600;
                        gs.knowledge_level += 3;
                        gs.phase = PHASE_CRYPTO_LAYER_6;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_6:
                printf("🔐 FASE 8: CAPA CRIPTOGRÁFICA 6\n");
                printf("   Descifrando algoritmo avanzado...\n");
                printf("   Algoritmo: AES modificado con S-box personalizado\n");
                printf("   ¿Cuál es la clave de descifrado? ");
                
                char key6[256];
                if (fgets(key6, sizeof(key6), stdin) != NULL) {
                    key6[strcspn(key6, "\n")] = 0;
                    if (strcmp(key6, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +700 puntos\n");
                        gs.score += 700;
                        gs.knowledge_level += 3;
                        gs.phase = PHASE_CRYPTO_LAYER_7;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_7:
                printf("🔐 FASE 9: CAPA CRIPTOGRÁFICA 7\n");
                printf("   Descifrando algoritmo de cifrado de flujo...\n");
                printf("   Algoritmo: RC4 modificado con clave personalizada\n");
                printf("   ¿Cuál es la clave de descifrado? ");
                
                char key7[256];
                if (fgets(key7, sizeof(key7), stdin) != NULL) {
                    key7[strcspn(key7, "\n")] = 0;
                    if (strcmp(key7, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +800 puntos\n");
                        gs.score += 800;
                        gs.knowledge_level += 4;
                        gs.phase = PHASE_CRYPTO_LAYER_8;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_8:
                printf("🔐 FASE 10: CAPA CRIPTOGRÁFICA 8\n");
                printf("   Descifrando algoritmo de cifrado de bloque...\n");
                printf("   Algoritmo: DES modificado con S-box personalizado\n");
                printf("   ¿Cuál es la clave de descifrado? ");
                
                char key8[256];
                if (fgets(key8, sizeof(key8), stdin) != NULL) {
                    key8[strcspn(key8, "\n")] = 0;
                    if (strcmp(key8, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +900 puntos\n");
                        gs.score += 900;
                        gs.knowledge_level += 4;
                        gs.phase = PHASE_CRYPTO_LAYER_9;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_9:
                printf("🔐 FASE 11: CAPA CRIPTOGRÁFICA 9\n");
                printf("   Descifrando algoritmo de cifrado asimétrico...\n");
                printf("   Algoritmo: RSA modificado con claves personalizadas\n");
                printf("   ¿Cuál es la clave de descifrado? ");
                
                char key9[256];
                if (fgets(key9, sizeof(key9), stdin) != NULL) {
                    key9[strcspn(key9, "\n")] = 0;
                    if (strcmp(key9, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +1000 puntos\n");
                        gs.score += 1000;
                        gs.knowledge_level += 5;
                        gs.phase = PHASE_CRYPTO_LAYER_10;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_CRYPTO_LAYER_10:
                printf("🔐 FASE 12: CAPA CRIPTOGRÁFICA 10\n");
                printf("   Descifrando algoritmo de cifrado híbrido final...\n");
                printf("   Algoritmo: Combinación de todos los anteriores\n");
                printf("   ¿Cuál es la clave de descifrado? ");
                
                char key10[256];
                if (fgets(key10, sizeof(key10), stdin) != NULL) {
                    key10[strcspn(key10, "\n")] = 0;
                    if (strcmp(key10, "ChronoVMSmurf") == 0) {
                        printf("✅ ¡Correcto! +1200 puntos\n");
                        gs.score += 1200;
                        gs.knowledge_level += 6;
                        gs.phase = PHASE_VM_ANALYSIS;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_VM_ANALYSIS:
                printf("🖥️  FASE 13: ANÁLISIS DE MÁQUINA VIRTUAL\n");
                printf("   Analizando bytecode de la VM...\n");
                printf("   Se han encontrado 15 instrucciones personalizadas.\n");
                printf("   ¿Cuál es el error en el bytecode? (1-10): ");
                
                int error_line;
                if (scanf("%d", &error_line) == 1) {
                    if (error_line == 4) {
                        printf("✅ ¡Correcto! +300 puntos\n");
                        gs.score += 300;
                        gs.knowledge_level++;
                        gs.phase = PHASE_VM_EXECUTION;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_VM_EXECUTION:
                printf("🖥️  FASE 14: EJECUCIÓN DE MÁQUINA VIRTUAL\n");
                printf("   Ejecutando bytecode de la VM...\n");
                printf("   Se han ejecutado %d ciclos de VM.\n", gs.vm_cycles);
                printf("   ¿Cuál es el resultado de la ejecución? ");
                
                char result[256];
                if (fgets(result, sizeof(result), stdin) != NULL) {
                    result[strcspn(result, "\n")] = 0;
                    if (strcmp(result, "HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}") == 0) {
                        printf("✅ ¡Correcto! +800 puntos\n");
                        gs.score += 800;
                        gs.knowledge_level += 4;
                        gs.real_flag_found = 1;
                        gs.phase = PHASE_FINAL_BOSS;
                    } else {
                        printf("❌ Incorrecto! Perdiste una vida.\n");
                        gs.lives--;
                    }
                }
                break;
                
            case PHASE_FINAL_BOSS:
                printf("💀 FASE 15: JEFE FINAL\n");
                printf("   Has encontrado el flag real!\n");
                printf("   Flag: HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}\n");
                printf("   ¿Confirmas que tienes el flag real? (s/n): ");
                
                char confirm;
                if (scanf(" %c", &confirm) == 1 && (confirm == 's' || confirm == 'S')) {
                    printf("✅ ¡VICTORIA TOTAL!\n");
                    printf("   Has logrado completar el reto más difícil.\n");
                    printf("   +1000 puntos por la victoria final\n");
                    gs.score += 1000;
                    gs.phase = PHASE_ESCAPE;
                } else {
                    printf("❌ Escape fallido. El sistema se autodestruye.\n");
                    gs.lives--;
                }
                break;
                
            case PHASE_ESCAPE:
                printf("🏃 FASE 16: ESCAPE\n");
                printf("   Has logrado escapar con el flag real.\n");
                printf("   +500 puntos por el escape exitoso\n");
                gs.score += 500;
                gs.phase = PHASE_COMPLETE;
                break;
                
            default:
                break;
        }
        
        if (gs.lives <= 0) {
            clear_screen();
            print_banner("💀 GAME OVER");
            printf("\n😢 Lo siento, te quedaste sin vidas.\n");
            printf("📊 Puntuación final: %d puntos\n", gs.score);
            printf("🎯 Puntuación máxima posible: %d puntos\n", MAX_SCORE);
            printf("💡 Intenta de nuevo para mejorar tu puntuación!\n");
            break;
        }
    }
    
    // Pantalla de victoria
    if (gs.phase == PHASE_COMPLETE) {
        clear_screen();
        print_banner("🏆 ¡VICTORIA TOTAL!");
        printf("\n🎉 ¡FELICIDADES! Has completado el reto más difícil.\n");
        printf("📊 Puntuación final: %d puntos\n", gs.score);
        printf("🎯 Puntuación máxima: %d puntos\n", MAX_SCORE);
        printf("🏆 Flag real: HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}\n");
        printf("⏰ Tiempo total: %ld segundos\n", time(NULL) - gs.start_time);
        printf("💀 Vidas restantes: %d\n", gs.lives);
        printf("🔍 Flags falsos encontrados: %d\n", gs.fake_flags_found);
        printf("🧠 Nivel de conocimiento: %d\n", gs.knowledge_level);
        printf("🖥️  Ciclos de VM ejecutados: %d\n", gs.vm_cycles);
        printf("🔐 Capas criptográficas rotas: 10\n");
        printf("🚨 Protecciones anti-debugging activadas: 10\n");
        printf("💡 ¡Eres un verdadero hacker de élite!\n");
    }
    
    return 0;
}