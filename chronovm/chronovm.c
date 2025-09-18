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
    if (checksum != 0x42A433D3) {
        printf("Binary integrity check failed!\n");
        exit(1);
    }
    
    free(buffer);
}

// SHA1 modificado
static void sha1_modified(const uint8_t *data, size_t len, uint8_t *hash) {
    uint32_t h[5];
    memcpy(h, sha1_constants, sizeof(h));
    h[4] = 0xC3D2E1F0; // Constante modificada
    
    // Procesamiento simplificado
    for (size_t i = 0; i < len; i++) {
        h[0] = ((h[0] << 1) ^ data[i]) + h[1];
        h[1] = ((h[1] << 2) ^ data[i]) + h[2];
        h[2] = ((h[2] << 3) ^ data[i]) + h[3];
        h[3] = ((h[3] << 4) ^ data[i]) + h[4];
        h[4] = ((h[4] << 5) ^ data[i]) + h[0];
    }
    
    memcpy(hash, h, 20);
}

// Aplicar caja S personalizada
static void apply_sbox(uint8_t *data, size_t len) {
    for (size_t i = 0; i < len; i++) {
        data[i] = custom_sbox[data[i]];
    }
}

// Autómata celular regla 30
static uint32_t cellular_automaton(uint32_t state) {
    uint32_t new_state = 0;
    for (int i = 0; i < 32; i++) {
        uint32_t left = (state >> ((i + 1) % 32)) & 1;
        uint32_t center = (state >> i) & 1;
        uint32_t right = (state >> ((i - 1 + 32) % 32)) & 1;
        
        uint32_t rule = (left << 2) | (center << 1) | right;
        if (rule == 0b100 || rule == 0b011 || rule == 0b010 || rule == 0b001) {
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
    time_t now = time(NULL);
    uint8_t key[16];
    
    // Derivar clave de la hora actual
    for (int i = 0; i < 16; i++) {
        key[i] = ((now >> (i * 2)) & 0xFF) ^ (i * 0x13);
    }
    
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
        0x01, 0x0D, 0x00, 0x00, 0x54,  // VM_LOAD r13, 'T'
        0x01, 0x0E, 0x00, 0x00, 0x69,  // VM_LOAD r14, 'i'
        0x01, 0x0F, 0x00, 0x00, 0x6D,  // VM_LOAD r15, 'm'
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
        "ChronoVM_",
        "TimeLock_",
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

// Validar entrada del usuario
static int validate_input(const char *input) {
    if (!input) return 0;
    
    // Hash de la entrada
    uint8_t hash[20];
    sha1_modified((uint8_t*)input, strlen(input), hash);
    
    // Aplicar caja S
    apply_sbox(hash, 20);
    
    // Autómata celular
    uint32_t state = *(uint32_t*)hash;
    for (int i = 0; i < 100; i++) {
        state = cellular_automaton(state);
    }
    
    // Verificar checksum final
    return (state == 0x42A433D3);
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

// Función principal
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
    
    // Mostrar interfaz
    show_clock();
    
    // Crear fragmentos si está habilitado
    create_fragments();
    
    // Cargar y ejecutar bytecode
    load_bytecode();
    run_vm();
    
    // Solicitar entrada del usuario
    printf("Enter validation key: ");
    char input[256];
    if (fgets(input, sizeof(input), stdin)) {
        // Eliminar newline
        input[strcspn(input, "\n")] = 0;
        
        if (validate_input(input)) {
            printf("\n✅ Validation successful!\n");
            printf("Flag: HTB{ChronoVM_TimeLock_VirtualMachine}\n");
        } else {
            printf("\n❌ Invalid key!\n");
            printf("The time is running out...\n");
        }
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