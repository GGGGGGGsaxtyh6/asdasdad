#include <stdio.h>
#include <stdint.h>

int main() {
    int64_t a, b, c;
    int result;
    
    printf("Before scanf\n");
    fflush(stdout);
    
    result = scanf("%ld %ld %ld", &a, &b, &c);
    
    printf("After scanf, result=%d\n", result);
    fflush(stdout);
    
    if (result == 3) {
        printf("a=%ld, b=%ld, c=%ld\n", a, b, c);
    }
    
    return 0;
}
