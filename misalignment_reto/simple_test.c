#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

void handler(int sig) {
    printf("Signal %d received!\n", sig);
    exit(1);
}

int main() {
    signal(SIGALRM, handler);
    signal(SIGTERM, handler);
    signal(SIGSEGV, handler);
    
    alarm(60);
    
    long a, b, c;
    printf("Enter 3 numbers: ");
    int result = scanf("%ld %ld %ld", &a, &b, &c);
    
    printf("scanf returned %d\n", result);
    printf("a=%ld, b=%ld, c=%ld\n", a, b, c);
    
    printf("Done\n");
    
    return 0;
}
