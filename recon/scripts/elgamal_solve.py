#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path
from typing import List, Tuple

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])  # 50419
PORT_ELG    = int(sys.argv[3])  # 56357
OUT = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_solve'
OUT.mkdir(parents=True, exist_ok=True)

def connect(host: str, port: int, timeout: float = 6.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_idle(s: socket.socket, idle: float = 0.2, rounds: int = 200) -> str:
    buf = b''
    for _ in range(rounds):
        try:
            chunk = s.recv(16384)
            if not chunk:
                break
            buf += chunk
            time.sleep(idle)
        except Exception:
            time.sleep(idle)
            continue
    return buf.decode(errors='ignore')

def send_line(s: socket.socket, line: str):
    s.sendall((line + "\n").encode())

def parse_int(label: str, text: str):
    m = re.search(rf"^{label}\s*=\s*(\d+)$", text, re.M)
    return int(m.group(1)) if m else None

def parse_pairs(text: str) -> List[Tuple[int, int]]:
    return [(int(a), int(b)) for a, b in re.findall(r"\((\d+)\s*,\s*(\d+)\)", text)]

# 1) Get modulus p from params service
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

# 2) On ElGamal menu: get encrypted flag pairs
s2 = connect(HOST, PORT_ELG)
_ = recv_idle(s2)
send_line(s2, '1')
flag_out = recv_idle(s2)
(OUT / 'flag_out.txt').write_text(flag_out)
flag_pairs = parse_pairs(flag_out)
if not flag_pairs:
    print('NOFLAG')
    sys.exit(0)

# 3) Encrypt message m=1 to derive session secrets for corresponding c1 values
send_line(s2, '2')
_ = recv_idle(s2)
send_line(s2, '1')
enc1_out = recv_idle(s2)
(OUT / 'enc1_out.txt').write_text(enc1_out)
enc_pairs = parse_pairs(enc1_out)
try:
    s2.close()
except Exception:
    pass
if not enc_pairs:
    print('NOFLAG')
    sys.exit(0)
enc_map = {c1: c2 for c1, c2 in enc_pairs}

# 4) Decrypt blocks where c1 matches
def inv_mod(a: int, m: int) -> int:
    return pow(a % m, -1, m)

plain_blocks: List[bytes] = []
for c1f, c2f in flag_pairs:
    c2e = enc_map.get(c1f)
    if c2e is None:
        continue
    try:
        mval = (c2f * inv_mod(c2e, p)) % p
    except ValueError:
        continue
    blen = (mval.bit_length() + 7) // 8
    block = mval.to_bytes(blen, 'big')
    plain_blocks.append(block)

pt = b''.join(plain_blocks)
(OUT / 'flag_plain.bin').write_bytes(pt)
try:
    txt = pt.decode()
except Exception:
    txt = pt.decode(errors='ignore')
(OUT / 'flag_plain.txt').write_text(txt)

m = re.search(r"HTB\{[^}]+\}", txt)
print(m.group(0) if m else 'NOFLAG')
