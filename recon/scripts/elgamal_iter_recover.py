#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path
from typing import List, Tuple, Dict

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])  # 50419
PORT_ELG    = int(sys.argv[3])  # 56357
OUT = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_iter'
OUT.mkdir(parents=True, exist_ok=True)

def connect(host: str, port: int, timeout: float = 6.0) -> socket.socket:
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_until_prompt(s: socket.socket, idle: float = 0.2, rounds: int = 240) -> str:
    buf = b''
    for _ in range(rounds):
        try:
            chunk = s.recv(16384)
            if not chunk:
                break
            buf += chunk
            txt = buf.decode(errors='ignore')
            if '>' in txt or 'Your option' in txt:
                # small extra read to flush
                time.sleep(idle)
                try:
                    buf += s.recv(4096)
                except Exception:
                    pass
                break
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

# 1) Fetch modulus p from params service
s1 = connect(HOST, PORT_PARAMS)
recv_until_prompt(s1)
send_line(s1, '1')
params = recv_until_prompt(s1)
(OUT / 'params.txt').write_text(params)
try:
    s1.close()
except Exception:
    pass
p = parse_int('p', params)
if not p:
    print('NOFLAG')
    sys.exit(0)

# 2) Get encrypted flag pairs (ordered)
s2 = connect(HOST, PORT_ELG)
recv_until_prompt(s2)
send_line(s2, '1')
flag_out = recv_until_prompt(s2)
(OUT / 'flag_out.txt').write_text(flag_out)
flag_pairs = parse_pairs(flag_out)
if not flag_pairs:
    print('NOFLAG')
    sys.exit(0)
# Map c1 to index for quick matching
c1_to_idx: Dict[int, int] = {}
for idx, (c1, c2) in enumerate(flag_pairs):
    c1_to_idx[c1] = idx

# 3) Iteratively request encrypt(m=1) and match c1 values to decrypt blocks
blocks: Dict[int, bytes] = {}
solved = 0
attempts = 0
max_attempts = 2000
while attempts < max_attempts and solved < len(flag_pairs):
    attempts += 1
    try:
        # request encrypt(m=1)
        send_line(s2, '2')
        recv_until_prompt(s2)
        send_line(s2, '1')
        enc_out = recv_until_prompt(s2)
    except Exception:
        # reconnect and continue
        try:
            s2.close()
        except Exception:
            pass
        s2 = connect(HOST, PORT_ELG)
        recv_until_prompt(s2)
        continue
    # parse pair
    m = re.search(r"\((\d+)\s*,\s*(\d+)\)", enc_out)
    if not m:
        continue
    c1e = int(m.group(1)); c2e = int(m.group(2))
    idx = c1_to_idx.get(c1e)
    if idx is None or idx in blocks:
        continue
    # decrypt this block: m = c2f * inv(c2e) mod p
    c1f, c2f = flag_pairs[idx]
    try:
        inv = pow(c2e % p, -1, p)
    except ValueError:
        continue
    mval = (c2f * inv) % p
    blen = (mval.bit_length() + 7) // 8
    block = mval.to_bytes(blen, 'big')
    blocks[idx] = block
    solved += 1
    # try assemble plaintext in order
    pt = b''.join(blocks.get(i, b'') for i in range(len(flag_pairs)))
    try:
        txt = pt.decode()
    except Exception:
        txt = pt.decode(errors='ignore')
    (OUT / 'partial.txt').write_text(txt)
    mflag = re.search(r"HTB\{[^}]+\}", txt)
    if mflag:
        (OUT / 'flag.txt').write_text(mflag.group(0))
        print(mflag.group(0))
        try:
            s2.close()
        except Exception:
            pass
        sys.exit(0)

print('NOFLAG')
try:
    s2.close()
except Exception:
    pass
