#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])  # 50419
PORT_ELG    = int(sys.argv[3])  # 56357
OUT = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_get'
OUT.mkdir(parents=True, exist_ok=True)

def connect(host, port, timeout=6.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_idle(s, idle=0.2, rounds=200):
    buf = b''
    for _ in range(rounds):
        try:
            chunk = s.recv(8192)
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

def parse_int(label: str, text: str):
    m = re.search(rf"^{label}\s*=\s*(\d+)$", text, re.M)
    return int(m.group(1)) if m else None

def parse_pair(text: str):
    m = re.search(r"\((\d+)\s*,\s*(\d+)\)", text)
    if not m:
        return None, None
    return int(m.group(1)), int(m.group(2))

# 1) Get p from params service
s1 = connect(HOST, PORT_PARAMS)
_ = recv_idle(s1)
send_line(s1, '1')
params = recv_idle(s1)
(OUT / 'params.txt').write_text(params)
try:
    s1.close()
except Exception:
    pass
p = parse_int('p', params)
if not p:
    print('NOFLAG')
    sys.exit(0)

# 2) On ElGamal service: get encrypted flag pair
s2 = connect(HOST, PORT_ELG)
_ = recv_idle(s2)
send_line(s2, '1')
flag_out = recv_idle(s2)
(OUT / 'flag_out.txt').write_text(flag_out)
c1f, c2f = parse_pair(flag_out)
if not c1f or not c2f:
    print('NOFLAG')
    sys.exit(0)

# 3) Encrypt message m=1 to derive session secret
def get_enc_one(sock):
    send_line(sock, '2')
    _ = recv_idle(sock)
    send_line(sock, '1')
    enc_out = recv_idle(sock)
    (OUT / 'enc1_out.txt').write_text(enc_out)
    return parse_pair(enc_out)

c1e, c2e = get_enc_one(s2)
try:
    s2.close()
except Exception:
    pass
if not c1e or not c2e:
    print('NOFLAG')
    sys.exit(0)

# 4) Decrypt: in ElGamal, with m=1, c2e == y^k == session secret s
s_secret = c2e % p
try:
    inv_s = pow(s_secret, -1, p)
except ValueError:
    print('NOFLAG')
    sys.exit(0)
M = (c2f * inv_s) % p
blen = (M.bit_length() + 7)//8
pt = M.to_bytes(blen, 'big')
try:
    txt = pt.decode()
except Exception:
    txt = pt.decode(errors='ignore')
(OUT / 'flag_plain.txt').write_text(txt)
print(txt if 'HTB{' in txt else 'NOFLAG')
