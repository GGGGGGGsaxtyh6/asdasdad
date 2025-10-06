#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

def connect(host, port, timeout=3.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.6)
    return s

def recv_some(s, wait=0.2, rounds=120):
    data = b""
    for _ in range(rounds):
        try:
            chunk = s.recv(4096)
            if not chunk:
                break
            data += chunk
            time.sleep(wait)
        except Exception:
            time.sleep(wait)
            continue
    return data.decode(errors='ignore')

def send_line(s, line: str):
    s.sendall((line + "\n").encode())

def run_setter(host: str, port: int, base: Path):
    out_all = ""
    # Step 1: Generate prime
    s = connect(host, port)
    out_all += recv_some(s)
    send_line(s, "1")  # Generate prime
    time.sleep(0.4)
    out_all += recv_some(s)
    # Parse prime
    m = re.search(r"(\d{20,})", out_all)
    prime = m.group(1) if m else ""
    # Step 2: Update key with prime if found; else try simple '1'
    send_line(s, "2")
    time.sleep(0.3)
    if prime:
        send_line(s, prime)
    else:
        send_line(s, "1")
    time.sleep(0.6)
    out_all += recv_some(s)
    # Step 3: Encrypt flag to get ciphertext
    send_line(s, "3")
    time.sleep(0.6)
    out_all += recv_some(s)
    # Extract ciphertext (hex)
    cands = re.findall(r"[0-9a-fA-F]{24,}", out_all)
    cipher = cands[-1] if cands else ""
    # Exit cleanly
    try:
        send_line(s, "4")
    except Exception:
        pass
    try:
        s.close()
    except Exception:
        pass
    base.mkdir(parents=True, exist_ok=True)
    (base / 'setter_out.txt').write_text(out_all)
    if prime:
        (base / 'setter_prime.txt').write_text(prime)
    if cipher:
        (base / 'setter_cipher.txt').write_text(cipher)
    return prime, cipher

def flag_decrypt(host: str, port: int, token: str, base: Path):
    s = connect(host, port)
    out = recv_some(s)
    send_line(s, "3")  # Decrypt Message
    time.sleep(0.4)
    out += recv_some(s)
    if token:
        s.sendall((token + "\n").encode())
        time.sleep(0.7)
        out += recv_some(s)
    try:
        s.close()
    except Exception:
        pass
    (base / 'flag_decrypt_session.txt').write_text(out)
    m = re.search(r"HTB\{[^}]+\}", out)
    return m.group(0) if m else ""

def flag_get(host: str, port: int, base: Path):
    s = connect(host, port)
    out = recv_some(s)
    send_line(s, "1")  # Get flag
    time.sleep(0.2)
    out += recv_some(s)
    try:
        s.close()
    except Exception:
        pass
    (base / 'flag_get_session.txt').write_text(out)
    m = re.search(r"([0-9a-fA-F]{24,})", out)
    return m.group(1) if m else ""

def main():
    if len(sys.argv) < 3:
        print("Usage: menus_orchestrator.py <host> <flag_port> [setter_port]", file=sys.stderr)
        sys.exit(2)
    host = sys.argv[1]
    flag_port = int(sys.argv[2])
    setter_port = int(sys.argv[3]) if len(sys.argv) >= 4 else None
    base = Path('/workspace/recon/httpx') / f'{host}_menus_orch'
    base.mkdir(parents=True, exist_ok=True)
    prime = cipher = ""
    if setter_port:
        prime, cipher = run_setter(host, setter_port, base)
    # Try decrypt with setter-produced cipher first, else use get flag token
    token = cipher or flag_get(host, flag_port, base)
    flag = flag_decrypt(host, flag_port, token, base)
    print(flag or 'NOFLAG')

if __name__ == '__main__':
    main()
