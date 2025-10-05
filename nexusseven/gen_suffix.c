#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>

static void random_hex(char *out, size_t len) {
  for (size_t i = 0; i < len; ++i) {
    uint8_t byte = rand() & 0xFF;
    sprintf(out + (i * 2), "%02x", byte);
  }
  out[len * 2] = 0;
}

int main(){
  srand(0);
  char out[8*2+1];
  random_hex(out, 8);
  puts(out);
  return 0;
}
EOF

cc -O2 -o "/workspace/nexusseven/gen_suffix" "/workspace/nexusseven/gen_suffix.c" && "/workspace/nexusseven/gen_suffix"
