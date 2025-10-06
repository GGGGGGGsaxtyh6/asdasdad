#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
PORT_PARAMS = int(sys.argv[2])
PORT_ELG = int(sys.argv[3])
OUT = Path('/workspace/recon/httpx') / f'{HOST}_elgamal_match'
OUT.mkdir(parents=True, exist_ok=True)

def connect(host, port, timeout=6.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_until_prompt(s, idle=0.2, rounds=240):
    buf=b''
    for _ in range(rounds):
        try:
            chunk=s.recv(8192)
            if not chunk:
                break
            buf+=chunk
            text=buf.decode(errors='ignore')
            if text.count('>')>=1 or 'Your option' in text or 'Enter' in text:
                # allow extra read
                time.sleep(idle)
                try:
                    buf+=s.recv(8192)
                except Exception:
                    pass
                break
            time.sleep(idle)
        except Exception:
            time.sleep(idle)
            continue
    return buf.decode(errors='ignore')

def send_line(s, line: str):
    s.sendall((line+"\n").encode())

# 1) Get p from params
s1=connect(HOST, PORT_PARAMS)
_ = recv_until_prompt(s1)
send_line(s1,'1')
params = recv_until_prompt(s1)
(OUT/'params.txt').write_text(params)
try: s1.close()
except Exception: pass
mp = re.search(r"^p\s*=\s*(\d+)$", params, re.M)
if not mp:
    print('NOFLAG')
    sys.exit(0)
p = int(mp.group(1))

# 2) On ElGamal: get list of flag pairs
s2=connect(HOST, PORT_ELG)
_ = recv_until_prompt(s2)
send_line(s2,'1')
flag_out = recv_until_prompt(s2)
(OUT/'flag_out.txt').write_text(flag_out)
pairs = re.findall(r"\((\d+)\s*,\s*(\d+)\)", flag_out)
flag_pairs = [(int(a),int(b)) for a,b in pairs]
# 3) Encrypt m=1 to get c1e,c2e
send_line(s2,'2')
_ = recv_until_prompt(s2)
send_line(s2,'1')
enc_out = recv_until_prompt(s2)
(OUT/'enc_out.txt').write_text(enc_out)
me = re.search(r"\((\d+)\s*,\s*(\d+)\)", enc_out)
if not me:
    print('NOFLAG')
    sys.exit(0)
c1e = int(me.group(1)); c2e = int(me.group(2))
try: s2.close()
except Exception: pass
# 4) Find match by c1
for c1f,c2f in flag_pairs:
    if c1f == c1e:
        # s=c2e, decrypt: m = c2f * inv(s) mod p
        try:
            inv = pow(c2e % p, -1, p)
        except ValueError:
            continue
        mval = (c2f * inv) % p
        blen = (mval.bit_length()+7)//8
        pt = mval.to_bytes(blen, 'big')
        try:
            txt = pt.decode()
        except Exception:
            txt = pt.decode(errors='ignore')
        (OUT/'flag_plain.txt').write_text(txt)
        print(txt if 'HTB{' in txt else 'NOFLAG')
        sys.exit(0)
# If no match
print('NOFLAG')
