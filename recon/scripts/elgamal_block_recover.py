#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])  # 50419
PORT_ELG    = int(sys.argv[3])  # 56357
OUT = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_blocks'
OUT.mkdir(parents=True, exist_ok=True)

def connect(host, port, timeout=6.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_idle(s, idle=0.2, rounds=240):
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

def send_line(s, line: str):
    s.sendall((line + "\n").encode())

def parse_int(label: str, text: str):
    m = re.search(rf"^{label}\s*=\s*(\d+)$", text, re.M)
    return int(m.group(1)) if m else None

def parse_pairs(text: str):
    return [(int(a), int(b)) for a, b in re.findall(r"\((\d+)\s*,\s*(\d+)\)", text)]

# 1) Fetch modulus p
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

# 2) Get encrypted flag pairs
s2 = connect(HOST, PORT_ELG)
_ = recv_idle(s2)
send_line(s2, '1')
flag_out = recv_idle(s2)
(OUT / 'flag_out.txt').write_text(flag_out)
flag_pairs = parse_pairs(flag_out)
if not flag_pairs:
    print('NOFLAG')
    sys.exit(0)

# 3) Encrypt message m=1 (should output pairs for m=1)
send_line(s2, '2')
_ = recv_idle(s2)
send_line(s2, '1')
enc1_out = recv_idle(s2)
(OUT / 'enc1_out.txt').write_text(enc1_out)
enc1_pairs = parse_pairs(enc1_out)
try:
    s2.close()
except Exception:
    pass
if not enc1_pairs:
    print('NOFLAG')
    sys.exit(0)

# 4) Build map c1 -> c2 for m=1 and decrypt per-block
enc_map = {c1: c2 for c1, c2 in enc1_pairs}
plain_blocks = []
for c1f, c2f in flag_pairs:
    c2e = enc_map.get(c1f)
    if c2e is None:
        continue
    # m = c2f * inv(c2e) mod p
    try:
        inv = pow(c2e % p, -1, p)
    except ValueError:
        continue
    mval = (c2f * inv) % p
    # convert each block to bytes (big endian) and append with a delimiter
    blen = (mval.bit_length() + 7) // 8
    block = mval.to_bytes(blen, 'big')
    plain_blocks.append(block)

pt = b''.join(plain_blocks)
try:
    txt = pt.decode()
except Exception:
    txt = pt.decode(errors='ignore')
(OUT / 'flag_plain.txt').write_text(txt)
print(txt if 'HTB{' in txt else 'NOFLAG')
