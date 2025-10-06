import requests
import json
from binascii import hexlify, unhexlify
import sys
import os

url_base = "https://aes.cryptohack.org/paper_plane"

with open("/workspace/scanning/encrypted_flag.txt", "r") as f:
    encrypted_data = json.load(f)

ciphertext_hex = encrypted_data["ciphertext"]
m0_hex = encrypted_data["m0"]
c0_hex = encrypted_data["c0"]

ciphertext = unhexlify(ciphertext_hex)
m0_original = unhexlify(m0_hex)
c0_original = unhexlify(c0_hex)

blocks = [ciphertext[i:i+16] for i in range(0, len(ciphertext), 16)]

# Cargar progreso si existe
progress_file = "/workspace/scanning/progress.json"
if os.path.exists(progress_file):
    with open(progress_file, "r") as f:
        progress = json.load(f)
        plaintext = bytearray(bytes.fromhex(progress.get("block0", "00"*16)))
        plaintext1 = bytearray(bytes.fromhex(progress.get("block1", "00"*16)))
        start_block = progress.get("current_block", 0)
        start_byte = progress.get("current_byte", 15)
        print(f"Continuando desde bloque {start_block}, byte {start_byte}")
else:
    plaintext = bytearray(16)
    plaintext1 = bytearray(16)
    start_block = 0
    start_byte = 15

def save_progress(block_num, byte_num):
    with open(progress_file, "w") as f:
        json.dump({
            "block0": plaintext.hex(),
            "block1": plaintext1.hex(),
            "current_block": block_num,
            "current_byte": byte_num
        }, f)

def check_padding(ct_hex, m0_hex, c0_hex):
    try:
        response = requests.get(f"{url_base}/send_msg/{ct_hex}/{m0_hex}/{c0_hex}/", timeout=2)
        result = response.json()
        return "error" not in result or "Can't decrypt" not in result.get("error", "")
    except:
        return False

def smart_range():
    charset = list(b'abcdefghijklmnopqrstuvwxyz_{}0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!')
    charset += list(range(1, 17))
    charset += [i for i in range(256) if i not in charset]
    return charset

# BLOQUE 0
if start_block == 0:
    print("=== BLOQUE 0 ===")
    ct_block = blocks[0]
    
    for pad_val in range(1, 17):
        byte_pos = 16 - pad_val
        if byte_pos < start_byte:
            continue
            
        sys.stdout.write(f"B{byte_pos}:")
        sys.stdout.flush()
        
        test_c0_base = bytearray(c0_original)
        for i in range(byte_pos + 1, 16):
            test_c0_base[i] = c0_original[i] ^ plaintext[i] ^ pad_val
        
        for guess in smart_range():
            test_c0 = bytearray(test_c0_base)
            test_c0[byte_pos] = c0_original[byte_pos] ^ guess ^ pad_val
            
            if check_padding(ct_block.hex(), m0_original.hex(), test_c0.hex()):
                plaintext[byte_pos] = guess
                c = chr(guess) if 32 <= guess < 127 else '?'
                sys.stdout.write(f"{guess:02x}({c}) ")
                sys.stdout.flush()
                save_progress(0, byte_pos - 1 if byte_pos > 0 else -1)
                break
        else:
            sys.stdout.write(f"?? ")
            sys.stdout.flush()
    
    print(f"\nBloque0: {bytes(plaintext)}")
    start_block = 1
    start_byte = 15

# BLOQUE 1
if start_block == 1:
    print("\n=== BLOQUE 1 ===")
    ct_block1 = blocks[1]
    
    for pad_val in range(1, 17):
        byte_pos = 16 - pad_val
        if start_block == 1 and byte_pos < start_byte:
            continue
            
        sys.stdout.write(f"B{byte_pos}:")
        sys.stdout.flush()
        
        test_c0_base = bytearray(blocks[0])
        for i in range(byte_pos + 1, 16):
            test_c0_base[i] = blocks[0][i] ^ plaintext1[i] ^ pad_val
        
        for guess in smart_range():
            test_c0 = bytearray(test_c0_base)
            test_c0[byte_pos] = blocks[0][byte_pos] ^ guess ^ pad_val
            
            if check_padding(ct_block1.hex(), bytes(plaintext).hex(), test_c0.hex()):
                plaintext1[byte_pos] = guess
                c = chr(guess) if 32 <= guess < 127 else '?'
                sys.stdout.write(f"{guess:02x}({c}) ")
                sys.stdout.flush()
                save_progress(1, byte_pos - 1 if byte_pos > 0 else -1)
                break
        else:
            sys.stdout.write(f"?? ")
            sys.stdout.flush()
    
    print(f"\nBloque1: {bytes(plaintext1)}")

full = bytes(plaintext) + bytes(plaintext1)
print(f"\n=== RESULTADO ===")
print(f"Hex: {full.hex()}")
print(f"Raw: {full}")

pad_len = full[-1]
if 1 <= pad_len <= 16 and all(full[-(i+1)] == pad_len for i in range(min(pad_len, len(full)))):
    flag = full[:-pad_len]
    print(f"\nFLAG: {flag.decode('utf-8', errors='replace')}")
    with open("/workspace/scanning/flag.txt", "w") as f:
        f.write(flag.decode('utf-8', errors='replace'))
else:
    print(f"\nPlaintext completo (sin padding detectado): {full}")

# Limpiar archivo de progreso
if os.path.exists(progress_file):
    os.remove(progress_file)
