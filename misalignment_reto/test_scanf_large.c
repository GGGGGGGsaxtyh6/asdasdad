#include <stdio.h>
#include <stdint.h>

int main() {
    int64_t a, b, c;
    int result;
    
    result = scanf("%ld %ld %ld", &a, &b, &c);
    printf("scanf returned: %d\n", result);
    
    if (result == 3) {
        printf("a=%ld\n", a);
        printf("b=%ld\n", b);  
        printf("c=%ld\n", c);
    }
    
    return 0;
}
