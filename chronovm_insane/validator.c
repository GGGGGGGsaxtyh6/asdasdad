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

// Sistema de validación extremo
#define MAX_VALIDATION_ATTEMPTS 1000
#define VALIDATION_TIMEOUT 300
#define MAX_CRYPTO_LAYERS 20
#define MAX_VM_CYCLES 1000000

// Estructuras de validación
typedef struct {
    uint32_t magic;
    uint32_t version;
    uint32_t checksum;
    uint32_t flags;
    char data[1024];
    uint32_t crc32;
    uint32_t timestamp;
    uint32_t nonce;
    uint32_t signature;
} validation_header_t;

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
    uint32_t crypto_keys[50];
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
} validation_state_t;

// Variables globales
static validation_state_t vs = {0};
static validation_header_t vh = {0};
static uint8_t encrypted_bytecode[32768] = {0};
static size_t bytecode_size = 0;
static uint8_t key_schedule[1024] = {0};
static uint32_t sbox[1024] = {0};
static uint32_t round_keys[128] = {0};
static uint8_t memory_pool[262144] = {0};
static uint32_t memory_checksum = 0;
static int validation_attempts = 0;
static int validation_failures = 0;
static int crypto_layer = 0;
static int vm_cycle = 0;

// Funciones de validación anti-debugging extremas
static void validation_anti_debug_1(void) {
    if (ptrace(PTRACE_TRACEME, 0, 0, 0) == -1) {
        printf("🚨 VALIDACIÓN FALLIDA - DEBUGGER DETECTADO\n");
        exit(1);
    }
}

static void validation_anti_debug_2(void) {
    struct rlimit rlim;
    getrlimit(RLIMIT_CORE, &rlim);
    if (rlim.rlim_cur != 0) {
        printf("🚨 VALIDACIÓN FALLIDA - CORE DUMP DETECTADO\n");
        exit(1);
    }
}

static void validation_anti_debug_3(void) {
    char buf[256];
    snprintf(buf, sizeof(buf), "/proc/%d/status", getpid());
    FILE *f = fopen(buf, "r");
    if (f) {
        char line[256];
        while (fgets(line, sizeof(line), f)) {
            if (strstr(line, "TracerPid:") && strstr(line, "0") == NULL) {
                printf("🚨 VALIDACIÓN FALLIDA - TRACER DETECTADO\n");
                fclose(f);
                exit(1);
            }
        }
        fclose(f);
    }
}

static void validation_anti_debug_4(void) {
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);
    usleep(1000);
    clock_gettime(CLOCK_MONOTONIC, &end);
    long elapsed = (end.tv_sec - start.tv_sec) * 1000000000 + (end.tv_nsec - start.tv_nsec);
    if (elapsed > 2000000) {
        printf("🚨 VALIDACIÓN FALLIDA - TIMING ATTACK DETECTADO\n");
        exit(1);
    }
}

static void validation_anti_debug_5(void) {
    uint32_t eax, ebx, ecx, edx;
    asm volatile("cpuid" : "=a"(eax), "=b"(ebx), "=c"(ecx), "=d"(edx) : "a"(1));
    if (ecx & (1 << 20)) {
        printf("🚨 VALIDACIÓN FALLIDA - VIRTUALIZACIÓN DETECTADA\n");
        exit(1);
    }
}

static void validation_anti_debug_6(void) {
    char *ptr = mmap(NULL, 4096, PROT_READ | PROT_WRITE, MAP_PRIVATE | MAP_ANONYMOUS, -1, 0);
    if (ptr == MAP_FAILED) return;
    
    uint8_t code[] = {0x90, 0x90, 0x90, 0x90};
    memcpy(ptr, code, sizeof(code));
    
    mprotect(ptr, 4096, PROT_READ | PROT_EXEC);
    
    void (*func)() = (void(*)())ptr;
    func();
    
    munmap(ptr, 4096);
}

static void validation_anti_debug_7(void) {
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
        printf("🚨 VALIDACIÓN FALLIDA - MODIFICACIÓN DETECTADA\n");
        free(buffer);
        exit(1);
    }
    
    free(buffer);
}

static void validation_anti_debug_8(void) {
    uint8_t *code_ptr = (uint8_t*)validation_anti_debug_8;
    for (int i = 0; i < 100; i++) {
        if (code_ptr[i] == 0xCC) {
            printf("🚨 VALIDACIÓN FALLIDA - BREAKPOINT DETECTADO\n");
            exit(1);
        }
    }
}

static void validation_anti_debug_9(void) {
    volatile uint64_t canary = 0x123456789ABCDEF0;
    volatile char buffer[64];
    strcpy((char*)buffer, "test");
    if (canary != 0x123456789ABCDEF0) {
        printf("🚨 VALIDACIÓN FALLIDA - STACK OVERFLOW DETECTADO\n");
        exit(1);
    }
}

static void validation_anti_debug_10(void) {
    int fd = open("/proc/self/stat", O_RDONLY);
    if (fd >= 0) {
        char buf[1024];
        read(fd, buf, sizeof(buf));
        close(fd);
        
        char *state = strchr(buf, ')') + 2;
        if (state && *state == 't') {
            printf("🚨 VALIDACIÓN FALLIDA - PROCESO TRAZADO DETECTADO\n");
            exit(1);
        }
    }
}

// Función de verificación de integridad
static void validation_integrity_check(void) {
    uint32_t checksum1 = 0, checksum2 = 0, checksum3 = 0;
    
    uint8_t *code_start = (uint8_t*)validation_integrity_check;
    for (int i = 0; i < 1000; i++) {
        checksum1 = (checksum1 << 1) ^ code_start[i];
    }
    
    for (int i = 0; i < sizeof(vs); i++) {
        checksum2 = (checksum2 << 1) ^ ((uint8_t*)&vs)[i];
    }
    
    for (int i = 0; i < sizeof(memory_pool); i++) {
        checksum3 = (checksum3 << 1) ^ memory_pool[i];
    }
    
    uint32_t expected = 0xDEADBEEF;
    if (checksum1 != expected || checksum2 != expected || checksum3 != expected) {
        printf("🚨 VALIDACIÓN DE INTEGRIDAD FALLIDA\n");
        exit(1);
    }
}

// Sistema de criptografía multi-capa
static void init_validation_crypto_system(void) {
    for (int i = 0; i < 1024; i++) {
        sbox[i] = (i * 0x9E3779B9) ^ 0x12345678;
        sbox[i] = ((sbox[i] << 13) | (sbox[i] >> 19)) ^ 0x87654321;
    }
    
    for (int i = 0; i < 1024; i++) {
        key_schedule[i] = (i * 0x5A827999) ^ 0xFEDCBA98;
    }
    
    for (int i = 0; i < 128; i++) {
        round_keys[i] = (i * 0x6ED9EBA1) ^ 0x13579BDF;
    }
}

// Algoritmo de cifrado personalizado
static void validation_custom_encrypt(uint8_t *data, size_t len, uint32_t key) {
    for (size_t i = 0; i < len; i++) {
        data[i] ^= (key >> (i % 32)) & 0xFF;
        data[i] = sbox[data[i] % 1024];
        data[i] ^= key_schedule[i % 1024];
        data[i] = ((data[i] << 3) | (data[i] >> 5)) ^ round_keys[i % 128];
    }
}

// Función de validación principal
static int validate_challenge(void) {
    // Verificar protecciones anti-debugging
    validation_anti_debug_1();
    validation_anti_debug_2();
    validation_anti_debug_3();
    validation_anti_debug_4();
    validation_anti_debug_5();
    validation_anti_debug_6();
    validation_anti_debug_7();
    validation_anti_debug_8();
    validation_anti_debug_9();
    validation_anti_debug_10();
    
    // Verificar integridad
    validation_integrity_check();
    
    // Verificar criptografía
    init_validation_crypto_system();
    
    // Verificar flag
    char expected_flag[] = "HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}";
    char input_flag[256];
    
    printf("🔍 VALIDACIÓN DE FLAG:\n");
    printf("   Ingresa el flag para validar: ");
    
    if (fgets(input_flag, sizeof(input_flag), stdin) != NULL) {
        input_flag[strcspn(input_flag, "\n")] = 0;
        
        if (strcmp(input_flag, expected_flag) == 0) {
            printf("✅ VALIDACIÓN EXITOSA\n");
            printf("   Flag correcto: %s\n", expected_flag);
            printf("   Puntuación: 50000 puntos\n");
            printf("   Tiempo: %ld segundos\n", time(NULL) - vs.start_time);
            printf("   Nivel de conocimiento: %d\n", vs.knowledge_level);
            printf("   Flags falsos encontrados: %d\n", vs.fake_flags_found);
            printf("   Ciclos de VM: %d\n", vs.vm_cycles);
            printf("   Capas criptográficas rotas: 10\n");
            printf("   Protecciones anti-debugging activadas: 10\n");
            printf("   ¡Eres un verdadero hacker de élite!\n");
            return 1;
        } else {
            printf("❌ VALIDACIÓN FALLIDA\n");
            printf("   Flag incorrecto: %s\n", input_flag);
            printf("   Flag esperado: %s\n", expected_flag);
            validation_failures++;
            return 0;
        }
    }
    
    return 0;
}

// Función principal
int main(int argc, char *argv[]) {
    // Inicializar estado de validación
    vs.start_time = time(NULL);
    vs.phase = 0;
    vs.score = 0;
    vs.lives = 3;
    vs.knowledge_level = 0;
    vs.fake_flags_found = 0;
    vs.real_flag_found = 0;
    vs.vm_cycles = 0;
    vs.memory_accesses = 0;
    vs.debug_attempts = 0;
    vs.anti_debug_triggers = 0;
    vs.integrity_failures = 0;
    vs.vm_crashes = 0;
    vs.crypto_failures = 0;
    vs.memory_corruptions = 0;
    vs.stack_overflows = 0;
    vs.heap_exploits = 0;
    vs.format_strings = 0;
    vs.rop_chains = 0;
    vs.jit_compilations = 0;
    vs.side_channels = 0;
    
    // Mostrar banner
    printf("\n╔══════════════════════════════════════════════════════════════╗\n");
    printf("║ 🎮 CHRONO VM CHALLENGE - VALIDADOR INSANE                   ║\n");
    printf("╚══════════════════════════════════════════════════════════════╝\n");
    
    printf("\n🔍 VALIDACIÓN DE FLAG:\n");
    printf("   Este es el validador del reto ChronoVM Insane.\n");
    printf("   Solo se puede usar después de completar el reto principal.\n");
    printf("   El flag debe ser exactamente: HTB{ChronoVM_Smurf_Lock_VM_VirtualMachine}\n\n");
    
    // Ejecutar validación
    if (validate_challenge()) {
        printf("\n🏆 ¡VALIDACIÓN COMPLETADA CON ÉXITO!\n");
        printf("   Has demostrado ser un hacker de élite.\n");
        printf("   El reto ChronoVM Insane ha sido completado.\n");
        return 0;
    } else {
        printf("\n💀 VALIDACIÓN FALLIDA\n");
        printf("   El flag proporcionado no es correcto.\n");
        printf("   Intenta completar el reto principal primero.\n");
        return 1;
    }
}