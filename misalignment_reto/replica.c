#include <stdio.h>
#include <string.h>
#include <stdint.h>

int main() {
    char buffer[152];  // 0x98
    int64_t *canary_ptr;
    int64_t num1, num2, num3;
    int64_t *write_ptr;
    
    // Inicializar
    memset(buffer, 0, 152);
    
    // Canario en offset 15
    canary_ptr = (int64_t*)(buffer + 15);
    *canary_ptr = 0xdeadbeef;
    
    printf("Canario inicial: 0x%016lx\n", *canary_ptr);
    printf("Dirección canario: %p\n", canary_ptr);
    printf("Dirección buffer: %p\n", buffer);
    
    // Leer 3 números
    printf("Enter num1 num2 num3: ");
    scanf("%ld %ld %ld", &num1, &num2, &num3);
    
    // Validar num1
    if (num1 < -7 || num1 > 9) {
        printf("num1 fuera de rango\n");
        return 1;
    }
    
    // Calcular offset de escritura
    int64_t offset = (num1 + 6) * 8 + 8;
    printf("Offset calculado: %ld\n", offset);
    
    // Escribir
    write_ptr = (int64_t*)(buffer + offset);
    printf("Escribiendo %ld en offset %ld\n", num2 + num3, offset);
    *write_ptr = num2 + num3;
    
    // Leer de vuelta
    printf("Result: %ld\n", *write_ptr);
    
    // Ver canario después
    printf("\nCanario después: 0x%016lx\n", *canary_ptr);
    
    // Mostrar bytes del canario
    unsigned char *canary_bytes = (unsigned char*)canary_ptr;
    printf("Bytes del canario: ");
    for (int i = 0; i < 8; i++) {
        printf("%02x ", canary_bytes[i]);
    }
    printf("\n");
    
    // Check final
    if (*canary_ptr == 0xb000000b5) {
        printf("\n¡FLAG!\n");
    }
    
    return 0;
}