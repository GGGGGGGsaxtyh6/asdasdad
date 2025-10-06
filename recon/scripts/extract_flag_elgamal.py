#!/usr/bin/env python3
import socket, time, re, sys, binascii
from pathlib import Path

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])    # 50419
PORT_ELG = int(sys.argv[3])       # 56357
BASE = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_extract'
BASE.mkdir(parents=True, exist_ok=True)

def conn(port, timeout=5.0):
    s = socket.create_connection((HOST, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_idle(s, idle=0.2, rounds=200):
    buf=b''
    for _ in range(rounds):
        try:
            chunk=s.recv(4096)
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

# 1) Fetch p from params service (50419)
s1 = conn(PORT_PARAMS)
_ = recv_idle(s1)
send_line(s1, '1')
params_out = recv_idle(s1)
(BASE / 'params.txt').write_text(params_out)
try:
    s1.close()
except Exception:
    pass
m = re.search(r"^p\s*=\s*(\d+)$", params_out, re.M)
if not m:
    print('NOP')
    sys.exit(0)
p = int(m.group(1))

# 2) On ElGamal service, get encrypted flag (option 1)
s2 = conn(PORT_ELG)
_ = recv_idle(s2)
send_line(s2, '1')
flag_out = recv_idle(s2)
(BASE / 'flag_pair.txt').write_text(flag_out)
# Parse first pair (c1, c2)
pair = re.search(r"\((\d+)\s*,\s*(\d+)\)", flag_out)
if not pair:
    print('NOPAIR')
    sys.exit(0)
C1f = int(pair.group(1)); C2f = int(pair.group(2))
# 3) Choose message m=1 to get s via option 2
send_line(s2, '2')
_ = recv_idle(s2)
send_line(s2, '1')
enc1_out = recv_idle(s2)
(BASE / 'enc1_pair.txt').write_text(enc1_out)
pair2 = re.search(r"\((\d+)\s*,\s*(\d+)\)", enc1_out)
if not pair2:
    print('NOENC1')
    sys.exit(0)
C1e = int(pair2.group(1)); C2e = int(pair2.group(2))
try:
    s2.close()
except Exception:
    pass
# In ElGamal, c2 = m * y^k mod p; with m=1 => c2 = s = y^k
s_secret = C2e % p
# Decrypt flag: m_flag = C2f * inv(s) mod p
inv_s = pow(s_secret, -1, p)
M = (C2f * inv_s) % p
# Convert to bytes
blen = (M.bit_length() + 7)//8
pt = M.to_bytes(blen, 'big')
try:
    txt = pt.decode()
except Exception:
    txt = pt.decode(errors='ignore')
(BASE / 'flag_plain.txt').write_text(txt)
print(txt if 'HTB{' in txt else 'NOFLAG')
