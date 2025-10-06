#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
PORT = int(sys.argv[2])
BASE = Path('/workspace/recon/httpx') / f'{HOST}_flag_solo'
BASE.mkdir(parents=True, exist_ok=True)

def conn():
    s = socket.create_connection((HOST, PORT), timeout=5)
    s.settimeout(0.5)
    return s

def recv_idle(s, idle_wait=0.2, max_idle=15):
    data = b''
    idle = 0
    while True:
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            data += chunk
            idle = 0
        except Exception:
            idle += 1
            time.sleep(idle_wait)
            if idle >= max_idle:
                break
    return data.decode(errors='ignore')

def send_line(s, line: str):
    s.sendall((line + "\n").encode())

def get_flag_hex() -> str:
    s = conn()
    _ = recv_idle(s)
    send_line(s, '1')
    time.sleep(0.4)
    out = recv_idle(s)
    (BASE / 'get_flag_raw.txt').write_text(out)
    try:
        s.close()
    except Exception:
        pass
    # extract hex with at least one letter a-f (avoid decimal primes)
    cands = re.findall(r"[0-9a-fA-F]{24,}", out)
    cands = [c for c in cands if re.search(r"[a-fA-F]", c)]
    cands.sort(key=len, reverse=True)
    return cands[0] if cands else ''

def decrypt_with_hex(hexstr: str) -> str:
    s = conn()
    _ = recv_idle(s)
    send_line(s, '3')
    time.sleep(0.3)
    _ = recv_idle(s)
    send_line(s, hexstr)
    time.sleep(0.6)
    out = recv_idle(s)
    (BASE / 'decrypt_out.txt').write_text(out)
    try:
        s.close()
    except Exception:
        pass
    m = re.search(r"HTB\{[^}]+\}", out)
    return m.group(0) if m else ''

if __name__ == '__main__':
    flag_hex = get_flag_hex()
    if not flag_hex:
        print('NOFLAGHEX')
        sys.exit(0)
    flag = decrypt_with_hex(flag_hex)
    print(flag or 'NOFLAG')
