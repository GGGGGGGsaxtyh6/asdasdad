#!/usr/bin/env python3
import socket, time, re, sys, binascii
from pathlib import Path

HOST = sys.argv[1]
PORT = int(sys.argv[2])
BASE = Path('/workspace/recon/httpx') / f'{HOST}_flag_cp_same'
BASE.mkdir(parents=True, exist_ok=True)

def conn():
    s = socket.create_connection((HOST, PORT), timeout=5)
    s.settimeout(0.8)
    return s

def recv_until(s, needles, idle=0.2, rounds=200):
    if isinstance(needles, str):
        needles = [needles]
    buf = b''
    for _ in range(rounds):
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            buf += chunk
            txt = buf.decode(errors='ignore')
            if any(n in txt for n in needles):
                return txt
            time.sleep(idle)
        except Exception:
            time.sleep(idle)
            continue
    return buf.decode(errors='ignore')

def send_line(s, line: str):
    s.sendall((line + "\n").encode())


def extract_hex(txt: str):
    cands = re.findall(r"[0-9a-fA-F]{24,}", txt)
    cands.sort(key=len, reverse=True)
    return cands[0] if cands else ''

s = conn()
menu = recv_until(s, 'Your option:')
(Path(BASE / 'menu.txt')).write_text(menu)
# Step 1: Get flag cipher
send_line(s, '1')
flag_out = recv_until(s, 'Your option:')
(Path(BASE / 'flag_out.txt')).write_text(flag_out)
flag_hex = extract_hex(flag_out)
# Step 2: Encrypt Message with As of same length
L = len(flag_hex)//2 if flag_hex else 30
send_line(s, '2')
prompt = recv_until(s, ['Enter','message','plaintext','Your option:'])
(Path(BASE / 'enc_prompt.txt')).write_text(prompt)
pt = 'A'*L
send_line(s, pt)
enc_out = recv_until(s, 'Your option:')
(Path(BASE / 'enc_out.txt')).write_text(enc_out)
enc_hex = extract_hex(enc_out)
# Compute XOR
flag_txt = ''
if flag_hex and enc_hex:
    # key = enc ^ 0x41
    benc = binascii.unhexlify(enc_hex)
    key = bytes([b ^ 0x41 for b in benc])
    bflag = binascii.unhexlify(flag_hex)
    plen = min(len(bflag), len(key))
    pbytes = bytes([bflag[i] ^ key[i] for i in range(plen)])
    try:
        flag_txt = pbytes.decode()
    except Exception:
        flag_txt = pbytes.decode(errors='ignore')

(Path(BASE / 'flag_guess.txt')).write_text(flag_txt)
print(flag_txt if 'HTB{' in flag_txt else 'NOFLAG')
