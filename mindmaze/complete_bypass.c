#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <time.h>
#include <signal.h>
#include <dlfcn.h>
#include <sys/mman.h>

// Hook completo para bypass de todas las protecciones
long ptrace(enum __ptrace_request request, ...) {
    return 0;  // Siempre éxito
}

pid_t getppid(void) {
    return 2;  // PID válido, no init
}

char *getenv(const char *name) {
    if (strcmp(name, "LD_PRELOAD") == 0) {
        return NULL;  // No hay LD_PRELOAD
    }
    return NULL;
}

// Hook para timing attacks
int clock_gettime(clockid_t clk_id, struct timespec *tp) {
    // Retornar tiempo fijo para evitar timing attacks
    tp->tv_sec = 0;
    tp->tv_nsec = 100000;  // 0.1ms - muy rápido
    return 0;
}

// Hook para integrity checks
FILE *fopen(const char *pathname, const char *mode) {
    if (strstr(pathname, "/proc/self/exe") != NULL) {
        // Simular que el archivo existe pero retornar NULL para bypass
        return NULL;
    }
    return fopen(pathname, mode);
}

// Hook para getpid
pid_t getpid(void) {
    return 12345;  // PID fijo
}

// Hook para time
time_t time(time_t *tloc) {
    static time_t fixed_time = 1600000000;
    if (tloc) *tloc = fixed_time;
    return fixed_time;
}

// Hook para srand
void srand(unsigned int seed) {
    // No hacer nada - mantener seed fijo
}

// Hook para rand
int rand(void) {
    return 42;  // Valor fijo
}

// Hook para RAND_bytes
int RAND_bytes(unsigned char *buf, int num) {
    // Llenar con valores fijos
    for (int i = 0; i < num; i++) {
        buf[i] = (i * 7 + 13) & 0xFF;
    }
    return 1;
}

// Hook para SHA256 functions
typedef struct {
    unsigned char data[64];
    unsigned int datalen;
    unsigned long long bitlen;
    unsigned int state[8];
} SHA256_CTX;

int SHA256_Init(SHA256_CTX *c) {
    memset(c, 0, sizeof(SHA256_CTX));
    return 1;
}

int SHA256_Update(SHA256_CTX *c, const void *data, size_t len) {
    return 1;
}

int SHA256_Final(unsigned char *md, SHA256_CTX *c) {
    // Retornar hash fijo
    unsigned char fixed_hash[32] = {
        0x12, 0x34, 0x56, 0x78, 0x9a, 0xbc, 0xde, 0xf0,
        0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88,
        0x99, 0xaa, 0xbb, 0xcc, 0xdd, 0xee, 0xff, 0x00,
        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08
    };
    memcpy(md, fixed_hash, 32);
    return 1;
}

// Hook para PKCS5_PBKDF2_HMAC
int PKCS5_PBKDF2_HMAC(const char *pass, int passlen, const unsigned char *salt, 
                      int saltlen, int iter, const void *digest, int keylen, unsigned char *out) {
    // Retornar clave fija
    for (int i = 0; i < keylen; i++) {
        out[i] = (i * 3 + 17) & 0xFF;
    }
    return 1;
}