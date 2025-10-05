#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>

static void random_hex(char *out, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    unsigned int byte = (unsigned int)(rand() & 0xFFu);
    sprintf(out + (i * 2), "%02x", byte);
  }
  out[len * 2] = '\0';
}

int main(int argc, char **argv){
  long n = 100000;
  if (argc > 1) {
    n = strtol(argv[1], NULL, 10);
    if (n <= 0) n = 100000;
  }
  srand(0);
  char out[8*2+1];
  for (long i = 0; i < n; ++i) {
    random_hex(out, 8);
    puts(out);
  }
  return 0;
}
