#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
SETTER = int(sys.argv[2])  # 36986
FLAG = int(sys.argv[3])    # 41769
BASE = Path('/workspace/recon/httpx') / f'{HOST}_full_flag_flow'
BASE.mkdir(parents=True, exist_ok=True)

def conn(p, t=5.0):
    s = socket.create_connection((HOST, p), timeout=t)
    s.settimeout(0.6)
    return s

def recv_idle(s, idle=0.2, rounds=40):
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

# 1) Update key to 1 on setter
s = conn(SETTER)
out = recv_idle(s)
send_line(s, '2')
_ = recv_idle(s)
send_line(s, '1')
_ = recv_idle(s)
try:
    send_line(s, '4')
except Exception:
    pass
try:
    s.close()
except Exception:
    pass
(BASE / 'setter_update.txt').write_text(out)

# 2) Get flag ciphertext
s2 = conn(FLAG)
out2 = recv_idle(s2)
send_line(s2, '1')
time.sleep(0.4)
out2 += recv_idle(s2)
(BASE / 'flag_get.txt').write_text(out2)
try:
    s2.close()
except Exception:
    pass
m = re.findall(r"[0-9a-fA-F]{24,}", out2)
flag_hex = m[0] if m else ''

# 3) Decrypt using server with that ciphertext
res = ''
if flag_hex:
    s3 = conn(FLAG)
    _ = recv_idle(s3)
    send_line(s3, '3')
    time.sleep(0.3)
    _ = recv_idle(s3)
    send_line(s3, flag_hex)
    time.sleep(0.6)
    out3 = recv_idle(s3)
    (BASE / 'flag_decrypt.txt').write_text(out3)
    try:
        s3.close()
    except Exception:
        pass
    m2 = re.search(r"HTB\{[^}]+\}", out3)
    res = m2.group(0) if m2 else ''

print(res or 'NOFLAG')
