#!/usr/bin/env python3
"""
Calcular índices para sobrescribir stdin

stdin: 0x202100 a 0x2021e0 (224 bytes = 0xe0)
result: 0x202200

Para escribir en stdin+offset, necesito:
result_addr + (idx * 8) = stdin_addr + offset
0x202200 + (idx * 8) = 0x202100 + offset
idx * 8 = 0x202100 + offset - 0x202200
idx * 8 = offset - 0x100
idx = (offset - 0x100) / 8

Para offset 0xd8 (vtable):
idx = (0xd8 - 0x100) / 8 = -0x28 / 8 = -5

Para offset 0x00 (flags):
idx = (0x00 - 0x100) / 8 = -0x100 / 8 = -32
"""

result_addr = 0x202200
stdin_addr = 0x202100

print("="*60)
print("ÍNDICES PARA SOBRESCRIBIR STDIN")
print("="*60)
print()

important_offsets = {
    0x00: "flags",
    0x08: "_IO_read_ptr",
    0x10: "_IO_read_end",
    0x18: "_IO_read_base",
    0x20: "_IO_write_base",
    0x28: "_IO_write_ptr",
    0x30: "_IO_write_end",
    0xd8: "vtable pointer"
}

for offset, name in important_offsets.items():
    target_addr = stdin_addr + offset
    # result_addr + (idx * 8) = target_addr
    idx = (target_addr - result_addr) // 8
    print(f"stdin+0x{offset:02x} ({name:20s}): índice {idx:3d} -> 0x{target_addr:x}")

print()
print("[*] Pero el problema es:")
print("    - Necesito índices negativos muy grandes")
print("    - El código verifica: index > 0 && index <= 9")
print("    - No puedo usar índices negativos en la verificación")
print()
print("[!] Espera... déjame revisar el código de nuevo")
print("    Tal vez el check es diferente...")