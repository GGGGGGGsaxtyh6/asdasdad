
#include <stdio.h>
#include <sys/ptrace.h>
#include <unistd.h>
#include <stdlib.h>

long ptrace(enum __ptrace_request request, ...) {
    return 0;
}

pid_t getppid(void) {
    return 2;
}

char *getenv(const char *name) {
    return NULL;
}

int clock_gettime(clockid_t clk_id, struct timespec *tp) {
    tp->tv_sec = 0;
    tp->tv_nsec = 100000;
    return 0;
}

FILE *fopen(const char *pathname, const char *mode) {
    if (strstr(pathname, "/proc/self/exe") != NULL) {
        return NULL;
    }
    return fopen(pathname, mode);
}
