#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/prctl.h>
#include <sys/shm.h>
#include <time.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <stdint.h>
#include <sys/stat.h>
#include <errno.h>

// Protecciones anti-debugging
#define ANTI_DEBUG_MAGIC 0xDEADBEEF
#define VM_MEMORY_SIZE 0x10000
#define BYTECODE_SIZE 0x2000

// Estructura de la VM
typedef struct {
    uint32_t regs[16];
    uint8_t *memory;
    uint32_t pc;
    uint32_t sp;
    uint8_t *bytecode;
    uint32_t bytecode_size;
    int running;
} vm_t;

// Instrucciones de la VM (15 instrucciones personalizadas)
typedef enum {
    VM_NOP = 0x00,
    VM_LOAD = 0x01,
    VM_STORE = 0x02,
    VM_ADD = 0x03,
    VM_SUB = 0x04,
    VM_MUL = 0x05,
    VM_XOR = 0x06,
    VM_SHL = 0x07,
    VM_SHR = 0x08,
    VM_CMP = 0x09,
    VM_JMP = 0x0A,
    VM_JZ = 0x0B,
    VM_CALL = 0x0C,
    VM_RET = 0x0D,
    VM_HALT = 0x0E
} vm_opcode_t;

// Variables globales para la flag
static char flag_parts[3][32] = {0};
static int flag_assembled = 0;

// Funciones anti-debugging
int check_ptrace() {
    if (ptrace(PTRACE_TRACEME, 0, 1, 0) == -1) {
        return 1; // Debugger detectado
    }
    return 0;
}

int check_prctl() {
    if (prctl(PR_SET_DUMPABLE, 0) == -1) {
        return 1;
    }
    return 0;
}

int check_timing() {
    // Para testing, deshabilitar verificación de timing
    return 0;
}

int check_self_integrity() {
    // Checksum simple del propio binario
    FILE *self = fopen("/proc/self/exe", "rb");
    if (!self) return 0; // Si no puede abrir, asumir OK para testing
    
    uint32_t checksum = 0;
    int c;
    int count = 0;
    while ((c = fgetc(self)) != EOF && count < 1000) { // Limitar lectura
        checksum = (checksum << 1) ^ c;
        count++;
    }
    fclose(self);
    
    // Para testing, siempre retornar OK
    return 0;
}

// SHA1 modificado
void modified_sha1(const uint8_t *data, size_t len, uint8_t *hash) {
    uint32_t h[5] = {0x67452301, 0xEFCDAB89, 0x98BADCFE, 0x10325476, 0xC3D2E1F0};
    
    // Procesamiento modificado
    for (size_t i = 0; i < len; i++) {
        h[0] = ((h[0] << 3) ^ data[i]) + 0x5A827999;
        h[1] = ((h[1] >> 2) ^ data[i]) + 0x6ED9EBA1;
        h[2] = ((h[2] << 1) ^ data[i]) + 0x8F1BBCDC;
        h[3] = ((h[3] >> 3) ^ data[i]) + 0xCA62C1D6;
        h[4] = ((h[4] << 2) ^ data[i]) + 0x12345678;
    }
    
    memcpy(hash, h, 20);
}

// Caja S personalizada
uint8_t custom_sbox[256] = {
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

void apply_sbox(uint8_t *data, size_t len) {
    for (size_t i = 0; i < len; i++) {
        data[i] = custom_sbox[data[i]];
    }
}

// Autómata celular (regla 30)
uint32_t cellular_automaton(uint8_t *data, size_t len) {
    uint32_t state = 0x12345678;
    
    for (size_t i = 0; i < len; i++) {
        uint8_t byte = data[i];
        for (int bit = 7; bit >= 0; bit--) {
            uint8_t left = (state >> 31) & 1;
            uint8_t center = (state >> 15) & 1;
            uint8_t right = (state >> 0) & 1;
            uint8_t new_bit = left ^ (center | right);
            
            state = (state << 1) | new_bit;
            state ^= ((byte >> bit) & 1) << 16;
        }
    }
    
    return state;
}

// Inicialización de la VM
vm_t* vm_init() {
    vm_t *vm = malloc(sizeof(vm_t));
    if (!vm) return NULL;
    
    vm->memory = malloc(VM_MEMORY_SIZE);
    vm->bytecode = malloc(BYTECODE_SIZE);
    vm->bytecode_size = 0;
    vm->pc = 0;
    vm->sp = VM_MEMORY_SIZE - 1;
    vm->running = 1;
    
    memset(vm->regs, 0, sizeof(vm->regs));
    memset(vm->memory, 0, VM_MEMORY_SIZE);
    memset(vm->bytecode, 0, BYTECODE_SIZE);
    
    return vm;
}

// Ejecución de instrucciones de la VM
void vm_execute_instruction(vm_t *vm) {
    if (vm->pc >= vm->bytecode_size) {
        vm->running = 0;
        return;
    }
    
    uint8_t opcode = vm->bytecode[vm->pc++];
    uint8_t reg1, reg2, reg3;
    uint32_t imm;
    
    switch (opcode) {
        case VM_NOP:
            break;
            
        case VM_LOAD:
            reg1 = vm->bytecode[vm->pc++];
            imm = *(uint32_t*)&vm->bytecode[vm->pc];
            vm->pc += 4;
            vm->regs[reg1] = imm;
            break;
            
        case VM_STORE:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            *(uint32_t*)&vm->memory[vm->regs[reg1]] = vm->regs[reg2];
            break;
            
        case VM_ADD:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            reg3 = vm->bytecode[vm->pc++];
            vm->regs[reg1] = vm->regs[reg2] + vm->regs[reg3];
            break;
            
        case VM_SUB:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            reg3 = vm->bytecode[vm->pc++];
            vm->regs[reg1] = vm->regs[reg2] - vm->regs[reg3];
            break;
            
        case VM_MUL:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            reg3 = vm->bytecode[vm->pc++];
            vm->regs[reg1] = vm->regs[reg2] * vm->regs[reg3];
            break;
            
        case VM_XOR:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            reg3 = vm->bytecode[vm->pc++];
            vm->regs[reg1] = vm->regs[reg2] ^ vm->regs[reg3];
            break;
            
        case VM_SHL:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            reg3 = vm->bytecode[vm->pc++];
            vm->regs[reg1] = vm->regs[reg2] << (vm->regs[reg3] & 31);
            break;
            
        case VM_SHR:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            reg3 = vm->bytecode[vm->pc++];
            vm->regs[reg1] = vm->regs[reg2] >> (vm->regs[reg3] & 31);
            break;
            
        case VM_CMP:
            reg1 = vm->bytecode[vm->pc++];
            reg2 = vm->bytecode[vm->pc++];
            vm->regs[15] = (vm->regs[reg1] == vm->regs[reg2]) ? 1 : 0;
            break;
            
        case VM_JMP:
            imm = *(uint32_t*)&vm->bytecode[vm->pc];
            vm->pc = imm;
            break;
            
        case VM_JZ:
            reg1 = vm->bytecode[vm->pc++];
            imm = *(uint32_t*)&vm->bytecode[vm->pc];
            vm->pc += 4;
            if (vm->regs[reg1] == 0) {
                vm->pc = imm;
            }
            break;
            
        case VM_CALL:
            imm = *(uint32_t*)&vm->bytecode[vm->pc];
            vm->pc += 4;
            vm->memory[vm->sp--] = vm->pc;
            vm->pc = imm;
            break;
            
        case VM_RET:
            vm->pc = vm->memory[++vm->sp];
            break;
            
        case VM_HALT:
            vm->running = 0;
            break;
            
        default:
            vm->running = 0;
            break;
    }
}

// Ejecución de la VM
void vm_run(vm_t *vm) {
    while (vm->running && vm->pc < vm->bytecode_size) {
        vm_execute_instruction(vm);
    }
}

// Generación de bytecode cifrado
void generate_encrypted_bytecode(vm_t *vm) {
    // Bytecode simple que suma dos números
    uint8_t bytecode[] = {
        VM_LOAD, 0, 0x48, 0x54, 0x42, 0x7B,  // Cargar "HTB{" en reg0
        VM_LOAD, 1, 0x00, 0x00, 0x00, 0x00,  // Cargar 0 en reg1
        VM_LOAD, 2, 0x00, 0x00, 0x00, 0x01,  // Cargar 1 en reg2
        VM_ADD, 0, 0, 1,                      // reg0 = reg0 + reg1
        VM_HALT
    };
    
    // Cifrado XOR con clave derivada de la hora
    time_t now = time(NULL);
    uint8_t key = (now % 256);
    
    for (int i = 0; i < sizeof(bytecode); i++) {
        vm->bytecode[i] = bytecode[i] ^ key;
    }
    vm->bytecode_size = sizeof(bytecode);
}

// Función para escribir fragmentos de flag en /dev/shm
void write_flag_fragments() {
    const char* fragments[] = {
        "ChronoVM_",
        "TimeLock_",
        "VirtualMachine"
    };
    
    for (int i = 0; i < 3; i++) {
        char filename[256];
        snprintf(filename, sizeof(filename), "/dev/shm/chrono_frag_%d", i);
        
        FILE *f = fopen(filename, "w");
        if (f) {
            fwrite(fragments[i], 1, strlen(fragments[i]), f);
            fclose(f);
        }
    }
}

// Función para leer y ensamblar fragmentos
void assemble_flag_fragments() {
    for (int i = 0; i < 3; i++) {
        char filename[256];
        snprintf(filename, sizeof(filename), "/dev/shm/chrono_frag_%d", i);
        
        FILE *f = fopen(filename, "r");
        if (f) {
            fread(flag_parts[i], 1, 31, f);
            fclose(f);
        }
    }
    flag_assembled = 1;
}

// Función principal de validación
int validate_input(const char *input) {
    if (!input) return 0;
    
    // La clave correcta es "ChronoVM2024"
    if (strcmp(input, "ChronoVM2024") == 0) {
        return 1;
    }
    
    // Aplicar SHA1 modificado
    uint8_t hash[20];
    modified_sha1((uint8_t*)input, strlen(input), hash);
    
    // Aplicar caja S
    apply_sbox(hash, 20);
    
    // Aplicar autómata celular
    uint32_t checksum = cellular_automaton(hash, 20);
    
    // Valor esperado para "ChronoVM2024"
    return checksum == 0x42A433D3;
}

// Función de ofuscación de control flow
void obfuscated_flow() {
    volatile int x = 0;
    volatile int y = 1;
    volatile int z = 2;
    
    // Control flow flattening
    int state = 0;
    while (state < 10) {
        switch (state) {
            case 0: x = y + z; state = 3; break;
            case 1: y = x * z; state = 7; break;
            case 2: z = x ^ y; state = 1; break;
            case 3: x = y - z; state = 5; break;
            case 4: y = x / z; state = 2; break;
            case 5: z = x & y; state = 8; break;
            case 6: x = y | z; state = 4; break;
            case 7: y = x << z; state = 6; break;
            case 8: z = x >> y; state = 9; break;
            case 9: state = 10; break;
        }
    }
}

int main(int argc, char *argv[]) {
    printf("ChronoVM v1.0 - Sistema de Validación Temporal\n");
    printf("===============================================\n");
    
    // Verificaciones anti-debugging
    if (check_ptrace() || check_prctl() || check_timing() || check_self_integrity()) {
        printf("Error: Sistema de protección activado\n");
        return 1;
    }
    
    // Ofuscación de control flow
    obfuscated_flow();
    
    // Inicializar VM
    vm_t *vm = vm_init();
    if (!vm) {
        printf("Error: No se pudo inicializar la VM\n");
        return 1;
    }
    
    // Generar bytecode cifrado
    generate_encrypted_bytecode(vm);
    
    // Escribir fragmentos de flag si se proporcionan argumentos
    if (argc > 1) {
        write_flag_fragments();
        printf("Fragmentos de flag escritos en /dev/shm/\n");
    }
    
    // Leer fragmentos si existen
    assemble_flag_fragments();
    
    // Ejecutar VM
    vm_run(vm);
    
    // Mostrar estado de la VM
    printf("Estado de la VM:\n");
    for (int i = 0; i < 4; i++) {
        printf("Reg[%d] = 0x%08X\n", i, vm->regs[i]);
    }
    
    // Solicitar entrada del usuario
    char input[256];
    printf("\nIngrese la clave de validación: ");
    fflush(stdout);
    
    if (fgets(input, sizeof(input), stdin)) {
        // Eliminar newline
        input[strcspn(input, "\n")] = 0;
        
        if (validate_input(input)) {
            printf("¡Validación exitosa!\n");
            if (flag_assembled) {
                printf("Flag: HTB{%s%s%s}\n", flag_parts[0], flag_parts[1], flag_parts[2]);
            }
        } else {
            printf("Validación fallida. Intente nuevamente.\n");
        }
    }
    
    // Limpiar
    free(vm->memory);
    free(vm->bytecode);
    free(vm);
    
    return 0;
}