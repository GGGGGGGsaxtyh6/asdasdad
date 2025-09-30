#include <stdio.h>

int main() {
    long a, b, c;
    int result;
    printf("Enter 3 numbers: ");
    result = scanf("%ld %ld %ld", &a, &b, &c);
    printf("scanf returned: %d\n", result);
    if (result == 3) {
        printf("a=%ld, b=%ld, c=%ld\n", a, b, c);
        printf("b+c=%ld\n", b + c);
    }
    return 0;
}
