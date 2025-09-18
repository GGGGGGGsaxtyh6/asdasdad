CC = gcc
CFLAGS = -Wall -Wextra -O3 -fno-stack-protector -fno-pie -no-pie -s
LDFLAGS = -lcrypto -lz -lzstd -lbz2 -llz4 -ldl -static
OBJCOPY = objcopy
STRIP = strip

# Obfuscation flags
OBFUSCATE_FLAGS = -fno-inline -fno-builtin -fno-ident -fno-asynchronous-unwind-tables
OBFUSCATE_FLAGS += -fno-unwind-tables -fno-stack-protector -fno-plt
OBFUSCATE_FLAGS += -fomit-frame-pointer -fno-common -fno-ident
OBFUSCATE_FLAGS += -fno-unroll-loops -fno-tree-vectorize

# Control flow flattening (simulated with compiler optimizations)
CFF_FLAGS = -O3 -fno-inline-functions-called-once -fno-early-inlining
CFF_FLAGS += -fno-crossjumping -fno-cse-follow-jumps -fno-cse-skip-blocks

# Additional hardening flags
HARDEN_FLAGS = -D_FORTIFY_SOURCE=0 -fno-stack-protector -fno-common
HARDEN_FLAGS += -fno-builtin-malloc -fno-builtin-free -fno-builtin-realloc

# Final compilation flags
FINAL_CFLAGS = $(CFLAGS) $(OBFUSCATE_FLAGS) $(CFF_FLAGS) $(HARDEN_FLAGS)

mindmaze: mindmaze.c
	$(CC) $(FINAL_CFLAGS) -o mindmaze mindmaze.c $(LDFLAGS)
	$(STRIP) mindmaze
	$(OBJCOPY) --strip-all mindmaze
	@echo "Binary compiled and stripped successfully"

clean:
	rm -f mindmaze

.PHONY: clean