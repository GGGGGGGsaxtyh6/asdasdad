#!/usr/bin/env python3
import socket, time, re, sys, binascii
from typing import Optional

HOST = sys.argv[1] if len(sys.argv) > 1 else '127.0.0.1'
PORT = int(sys.argv[2]) if len(sys.argv) > 2 else 41769


def connect():
    s = socket.create_connection((HOST, PORT), timeout=5)
    s.settimeout(0.6)
    return s


def recv_until_prompt(s, wait=0.15, rounds=120) -> str:
    data = b""
    for _ in range(rounds):
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            data += chunk
            # Heuristic: break if we see the menu or prompt
            if b"Your option:" in data or b"Enter" in data:
                time.sleep(wait)
                # one more read
                try:
                    data += s.recv(4096)
                except Exception:
                    pass
                break
        except Exception:
            time.sleep(wait)
            continue
    return data.decode(errors='ignore')


def send_line(s, line: str):
    s.sendall((line + "\n").encode())


def extract_hex(text: str) -> Optional[str]:
    m = re.findall(r"([0-9a-fA-F]{24,})", text)
    if not m:
        return None
    # take the last longer candidate
    m.sort(key=len, reverse=True)
    return m[0]


def xor_hex(h1: str, h2: str) -> str:
    b1 = binascii.unhexlify(h1)
    b2 = binascii.unhexlify(h2)
    n = min(len(b1), len(b2))
    return binascii.hexlify(bytes([b1[i] ^ b2[i] for i in range(n)])).decode()


def try_length(L: int) -> Optional[str]:
    s = connect()
    _ = recv_until_prompt(s)
    # Encrypt Message flow
    send_line(s, "2")
    _ = recv_until_prompt(s)
    pt = ("A" * L)
    send_line(s, pt)
    out = recv_until_prompt(s)
    c_enc = extract_hex(out)
    # Get flag
    send_line(s, "1")
    out2 = recv_until_prompt(s)
    c_flag = extract_hex(out2)
    # Try decrypt via XOR with 0x41
    res = None
    if c_enc and c_flag:
        # key = c_enc ^ 0x41 repeated
        k = xor_hex(c_enc, ("41" * (len(c_enc)//2)))
        # flag = c_flag ^ key
        # Pad key or trim
        fk = xor_hex(c_flag, k)
        try:
            pt_bytes = binascii.unhexlify(fk)
            txt = pt_bytes.decode(errors='ignore')
            if 'HTB{' in txt:
                res = txt
        except Exception:
            pass
    try:
        s.close()
    except Exception:
        pass
    return res


def main():
    # Try multiple lengths to match service output
    for L in [16, 24, 30, 32, 40, 48, 60, 64]:
        flag = try_length(L)
        if flag:
            print(flag)
            return
    print('NOFLAG')

if __name__ == '__main__':
    main()
