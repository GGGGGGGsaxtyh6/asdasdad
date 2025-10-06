#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
SETTER = int(sys.argv[2])
FLAG = int(sys.argv[3])
BASE = Path('/workspace/recon/httpx') / f'{HOST}_feed_setter_to_decrypt'
BASE.mkdir(parents=True, exist_ok=True)

def conn(p, t=5.0):
    s = socket.create_connection((HOST, p), timeout=t)
    s.settimeout(0.6)
    return s

def recv_idle(s, idle=0.2, rounds=60):
    buf = b''
    for _ in range(rounds):
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            buf += chunk
            time.sleep(idle)
        except Exception:
            time.sleep(idle)
            continue
    return buf.decode(errors='ignore')

def send_line(s, line: str):
    s.sendall((line + "\n").encode())

# 1) On setter, try Encrypt flag straight and capture hex
s = conn(SETTER)
out = recv_idle(s)
send_line(s, '3')
out += recv_idle(s)
(Path(BASE / 'setter_encrypt_flag.txt')).write_text(out)
hexes = re.findall(r"[0-9a-fA-F]{24,}", out)
hex_token = ''
if hexes:
    hexes.sort(key=len, reverse=True)
    hex_token = hexes[0]
try:
    s.close()
except Exception:
    pass

# 2) Feed token to flag decrypt
flag_out = ''
if hex_token:
    s2 = conn(FLAG)
    _ = recv_idle(s2)
    send_line(s2, '3')
    time.sleep(0.3)
    _ = recv_idle(s2)
    send_line(s2, hex_token)
    time.sleep(0.6)
    flag_out = recv_idle(s2)
    (BASE / 'flag_decrypt_out.txt').write_text(flag_out)
    try:
        s2.close()
    except Exception:
        pass

m = re.search(r"HTB\{[^}]+\}", flag_out)
print(m.group(0) if m else 'NOFLAG')
