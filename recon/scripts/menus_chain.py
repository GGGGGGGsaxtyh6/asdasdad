#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

HOST = sys.argv[1] if len(sys.argv) > 1 else '127.0.0.1'
FLAG_PORT = int(sys.argv[2]) if len(sys.argv) > 2 else 41769
SET_PORT = int(sys.argv[3]) if len(sys.argv) > 3 else 36986
BASE = Path('/workspace/recon/httpx') / f'{HOST}_menus_chain'
BASE.mkdir(parents=True, exist_ok=True)


def connect(host, port, timeout=5.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(1.0)
    return s


def recv_until_idle(s, idle_wait=0.25, max_idle=20, max_total=10.0):
    data = b''
    last_len = -1
    idle = 0
    start = time.time()
    while time.time() - start < max_total:
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            data += chunk
            if len(data) == last_len:
                idle += 1
                time.sleep(idle_wait)
                if idle >= max_idle:
                    break
            else:
                idle = 0
                last_len = len(data)
        except socket.timeout:
            idle += 1
            time.sleep(idle_wait)
            if idle >= max_idle:
                break
        except Exception:
            break
    return data.decode(errors='ignore')


def send_line(s, line: str):
    s.sendall((line + "\n").encode())


def extract_hex(text: str):
    hexes = re.findall(r"[0-9a-fA-F]{24,}", text)
    if not hexes:
        return ''
    # prefer the longest
    hexes.sort(key=len, reverse=True)
    return hexes[0]


def setter_encrypt_key(host, port):
    s = connect(host, port)
    out = recv_until_idle(s)
    # try Update key -> 1
    send_line(s, '2')
    time.sleep(0.2)
    send_line(s, '1')
    out += recv_until_idle(s)
    # then Encrypt flag
    send_line(s, '3')
    time.sleep(0.2)
    out += recv_until_idle(s)
    # as fallback, generate prime and update with prime, then encrypt
    if 'Encrypted key' in out and not re.search(r"[0-9a-fA-F]{24,}", out):
        send_line(s, '1')
        out += recv_until_idle(s)
        prime_match = re.search(r"(\d{20,})", out)
        if prime_match:
            prime = prime_match.group(1)
            send_line(s, '2')
            time.sleep(0.2)
            send_line(s, prime)
            out += recv_until_idle(s)
            send_line(s, '3')
            out += recv_until_idle(s)
    try:
        send_line(s, '4')
    except Exception:
        pass
    try:
        s.close()
    except Exception:
        pass
    (BASE / 'setter_full.txt').write_text(out)
    key_hex = extract_hex(out)
    if key_hex:
        (BASE / 'setter_key_hex.txt').write_text(key_hex)
    return key_hex


def flag_decrypt(host, port, token_hex):
    s = connect(host, port)
    out = recv_until_idle(s)
    # Decrypt Message
    send_line(s, '3')
    time.sleep(0.2)
    out += recv_until_idle(s)
    if token_hex:
        send_line(s, token_hex)
        time.sleep(0.4)
        out += recv_until_idle(s)
    try:
        s.close()
    except Exception:
        pass
    (BASE / 'flag_dec_out.txt').write_text(out)
    m = re.search(r"HTB\{[^}]+\}", out)
    return m.group(0) if m else ''


def main():
    key_hex = setter_encrypt_key(HOST, SET_PORT)
    # If still no key, try just getting a token from flag Get flag
    if not key_hex:
        s = connect(HOST, FLAG_PORT)
        out = recv_until_idle(s)
        send_line(s, '1')
        out += recv_until_idle(s)
        try:
            s.close()
        except Exception:
            pass
        key_hex = extract_hex(out)
        (BASE / 'flag_get_hex.txt').write_text(key_hex or '')
    flag = flag_decrypt(HOST, FLAG_PORT, key_hex)
    print(flag or 'NOFLAG')

if __name__ == '__main__':
    main()
