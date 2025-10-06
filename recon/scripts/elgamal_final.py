#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])
PORT_ELG = int(sys.argv[3])
OUT = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_final'
OUT.mkdir(parents=True, exist_ok=True)

def connect(host, port, timeout=6.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_idle(s, idle=0.2, rounds=200):
    buf=b''
    for _ in range(rounds):
        try:
            chunk=s.recv(8192)
            if not chunk:
                break
            buf+=chunk
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

# 1) Get p
for _ in range(3):
    try:
        s1 = connect(HOST, PORT_PARAMS)
        _ = recv_idle(s1)
        send_line(s1, '1')
        params = recv_idle(s1)
        (OUT / 'params.txt').write_text(params)
        p = parse_int('p', params)
        try:
            s1.close()
        except Exception:
            pass
        if p:
            break
    except Exception:
        time.sleep(0.5)
        continue
else:
    print('NOFLAG')
    sys.exit(0)

# 2) On ElGamal: get flag pair and encrypt m=1 to get session secret
flag_pair = None
enc1_pair = None
for _ in range(3):
    try:
        s2 = connect(HOST, PORT_ELG)
        _ = recv_idle(s2)
        # get encrypted flag
        send_line(s2, '1')
        flag_out = recv_idle(s2)
        (OUT / 'flag_out.txt').write_text(flag_out)
        c1f, c2f = parse_pair(flag_out)
        # encrypt m=1
        send_line(s2, '2')
        _ = recv_idle(s2)
        send_line(s2, '1')
        enc1_out = recv_idle(s2)
        (OUT / 'enc1_out.txt').write_text(enc1_out)
        c1e, c2e = parse_pair(enc1_out)
        try:
            s2.close()
        except Exception:
            pass
        if c1f and c2f and c1e and c2e:
            flag_pair=(c1f,c2f); enc1_pair=(c1e,c2e)
            break
    except Exception:
        time.sleep(0.5)
        continue

if not flag_pair or not enc1_pair:
    print('NOFLAG')
    sys.exit(0)

c1f,c2f = flag_pair
c1e,c2e = enc1_pair

# 3) Decrypt using s=c2e mod p
try:
    inv_s = pow(c2e % p, -1, p)
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
