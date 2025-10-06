#!/usr/bin/env python3
import socket, time, re, sys, binascii
from pathlib import Path

HOST = sys.argv[1]
PORT = int(sys.argv[2])
L = int(sys.argv[3]) if len(sys.argv) > 3 else 30  # chosen-plaintext length
BASE = Path('/workspace/recon/httpx') / f'{HOST}_flag_cp'
BASE.mkdir(parents=True, exist_ok=True)


def connect(host, port, timeout=5.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.7)
    return s


def recv_until_contains(s, needles, idle_wait=0.2, max_rounds=200):
    if isinstance(needles, str):
        needles = [needles]
    buf = b''
    for _ in range(max_rounds):
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            buf += chunk
            text = buf.decode(errors='ignore')
            if any(n in text for n in needles):
                return text
            time.sleep(idle_wait)
        except Exception:
            time.sleep(idle_wait)
            continue
    return buf.decode(errors='ignore')


def send_line(s, line: str):
    s.sendall((line + "\n").encode())


def extract_hex(txt: str):
    cands = re.findall(r"[0-9a-fA-F]{24,}", txt)
    cands.sort(key=len, reverse=True)
    return cands[0] if cands else ''


def xor_hex(h, byte_val):
    b = binascii.unhexlify(h)
    k = bytes([byte_val]) * len(b)
    return binascii.hexlify(bytes([b[i] ^ k[i] for i in range(len(b))])).decode()


def xor_hex_hex(h1, h2):
    b1 = binascii.unhexlify(h1)
    b2 = binascii.unhexlify(h2)
    n = min(len(b1), len(b2))
    return binascii.hexlify(bytes([b1[i] ^ b2[i] for i in range(n)])).decode()


def main():
    s = connect(HOST, PORT)
    # Read initial menu
    menu = recv_until_contains(s, ['Your option:', 'option:'])
    # Step 1: Encrypt Message
    send_line(s, '2')
    _ = recv_until_contains(s, ['Enter', 'plaintext', 'message'])
    pt = 'A' * L
    send_line(s, pt)
    enc_out = recv_until_contains(s, ['Your option:', 'option:'])
    (BASE / 'enc_out.txt').write_text(enc_out)
    enc_hex = extract_hex(enc_out)
    # Step 2: Get flag cipher
    send_line(s, '1')
    flag_out = recv_until_contains(s, ['Your option:', 'option:'])
    (BASE / 'flag_out.txt').write_text(flag_out)
    flag_hex = extract_hex(flag_out)
    # Step 3: Try decrypt message flow too (optional)
    send_line(s, '3')
    dec_prompt = recv_until_contains(s, ['Enter', 'cipher', 'ciphertext'])
    (BASE / 'dec_prompt.txt').write_text(dec_prompt)
    # Compute key as enc ^ 0x41 if we got enc_hex
    flag_txt = ''
    if enc_hex and flag_hex:
        key_hex = xor_hex(enc_hex, 0x41)
        plain_hex = xor_hex_hex(flag_hex, key_hex)
        try:
            flag_txt = binascii.unhexlify(plain_hex).decode(errors='ignore')
        except Exception:
            flag_txt = ''
    # Close
    try:
        s.close()
    except Exception:
        pass
    # Save and print
    if flag_txt:
        (BASE / 'flag_guess.txt').write_text(flag_txt)
        print(flag_txt)
    else:
        print('NOFLAG')

if __name__ == '__main__':
    main()
