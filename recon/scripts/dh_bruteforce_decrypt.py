#!/usr/bin/env python3
import socket, time, re, sys, hashlib
from pathlib import Path

HOST = sys.argv[1]
PORT = int(sys.argv[2])
MAXA = int(sys.argv[3]) if len(sys.argv) > 3 else 5000
BASE = Path('/workspace/recon/httpx') / f'{HOST}_dh_bf'
BASE.mkdir(parents=True, exist_ok=True)

def conn():
    s = socket.create_connection((HOST, PORT), timeout=6)
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

def send_line(s, line:str):
    s.sendall((line+'\n').encode())

# 1) Fetch params
s = conn()
_ = recv_idle(s)
send_line(s, '1')
params = recv_idle(s)
(Path(BASE/'params.txt')).write_text(params)
# Parse p,g,A,B
get = lambda k: int(re.search(rf"^{k}\s*=\s*(\d+)$", params, re.M).group(1))
p = get('p'); g = get('g'); A = get('A'); B = get('B')
# 2) Get encrypted hex
send_line(s, '3')
flag_out = recv_idle(s)
(Path(BASE/'flag_out.txt')).write_text(flag_out)
enc_hex_m = re.search(r"encrypted\s*=\s*([0-9a-fA-F]{16,})", flag_out)
enc_hex = enc_hex_m.group(1) if enc_hex_m else ''
try:
    s.close()
except Exception:
    pass
if not enc_hex:
    print('NOENC')
    sys.exit(0)
ct = bytes.fromhex(enc_hex)

# Fast powmod
def powmod(b,e,m):
    x=1
    while e>0:
        if e&1: x=(x*b)%m
        b=(b*b)%m
        e>>=1
    return x

# 3) Try small exponents for A=g^a
found=None
for a in range(1, MAXA+1):
    if powmod(g,a,p)==A:
        found=a
        break
# 4) If found, compute shared s=B^a mod p, derive keystream as sha256(str(s)), XOR
if found is not None:
    sshared = powmod(B,found,p)
    key = hashlib.sha256(str(sshared).encode()).digest()
    # repeat key
    ks = (key*((len(ct)//len(key))+2))[:len(ct)]
    pt = bytes([c^k for c,k in zip(ct,ks)])
    try:
        txt = pt.decode()
    except Exception:
        txt = pt.decode(errors='ignore')
    (Path(BASE/'pt_guess.txt')).write_text(txt)
    print(txt if 'HTB{' in txt else 'NOFLAG')
else:
    print('NOSMALLA')
