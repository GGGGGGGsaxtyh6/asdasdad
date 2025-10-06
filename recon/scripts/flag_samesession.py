#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1]
PORT = int(sys.argv[2])
BASE = Path('/workspace/recon/httpx') / f'{HOST}_flag_samesession'
BASE.mkdir(parents=True, exist_ok=True)

def conn():
    s = socket.create_connection((HOST, PORT), timeout=5)
    s.settimeout(0.8)
    return s

def recv_until_menu(s, max_rounds=200, idle=0.2):
    buf = b''
    for _ in range(max_rounds):
        try:
            buf += s.recv(4096)
            if b"Your option:" in buf:
                break
        except Exception:
            time.sleep(idle)
            continue
    return buf.decode(errors='ignore')


def recv_some(s, rounds=40, idle=0.2):
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

if __name__ == '__main__':
    s = conn()
    out = recv_until_menu(s)
    # Step 1: Get flag
    send_line(s, '1')
    time.sleep(0.3)
    out1 = recv_until_menu(s)
    (BASE / 'get1.txt').write_text(out1)
    m = re.findall(r"[0-9a-fA-F]{24,}", out1)
    tok = m[0] if m else ''
    # Step 2: Decrypt in same session
    send_line(s, '3')
    time.sleep(0.3)
    out2 = recv_some(s)
    (BASE / 'dec_prompt.txt').write_text(out2)
    if tok:
        send_line(s, tok)
        time.sleep(0.5)
        out3 = recv_some(s, rounds=120)
        (BASE / 'decrypt_out.txt').write_text(out3)
        flag = re.search(r"HTB\{[^}]+\}", out3)
        if flag:
            print(flag.group(0))
            sys.exit(0)
    print('NOFLAG')
