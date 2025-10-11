#include <stdio.h>
#include <string.h>

int main() {
    char buf[100];
    
    printf("Enter input: ");
    fflush(stdout);
    
    scanf("%99s", buf);
    
    printf("scanf read %lu bytes\n", strlen(buf));
    printf("Hex dump:\n");
    for (int i = 0; i < strlen(buf) + 5; i++) {
        printf("  [%d] 0x%02x\n", i, (unsigned char)buf[i]);
    }
    
    return 0;
}
