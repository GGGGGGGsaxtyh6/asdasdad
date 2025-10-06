#!/usr/bin/env python3
import socket, sys, re, time
from pathlib import Path

def recv_all(sock, wait=0.2, attempts=25):
    sock.settimeout(0.5)
    data = b""
    for _ in range(attempts):
        try:
            chunk = sock.recv(4096)
            if not chunk:
                break
            data += chunk
            time.sleep(wait)
        except socket.timeout:
            time.sleep(wait)
            continue
        except Exception:
            break
    return data.decode(errors='ignore')

def talk(host: str, port: int, sends: list[str]):
    s = socket.create_connection((host, port), timeout=5)
    out = recv_all(s)
    for msg in sends:
        s.sendall(msg.encode())
        time.sleep(0.1)
        out += recv_all(s)
    try:
        s.shutdown(socket.SHUT_RDWR)
    except Exception:
        pass
    s.close()
    return out

def main():
    if len(sys.argv) < 3:
        print("Usage: menus_driver.py <host> <flag_port> [setter_port]", file=sys.stderr)
        sys.exit(2)
    host = sys.argv[1]
    flag_port = int(sys.argv[2])
    setter_port = int(sys.argv[3]) if len(sys.argv) >= 4 else None
    base = Path('/workspace/recon/httpx') / f'{host}_menus_py'
    base.mkdir(parents=True, exist_ok=True)
    enc_key = ''
    if setter_port:
        # Navigate setter: choose 2 (Update key), choose 1 and 0
        for val in ['1','0']:
            out = talk(host, setter_port, ["2\n", f"{val}\n", "4\n"])
            (base / f'setter_{val}.txt').write_text(out)
            m = re.search(r"Encrypted key:?\s*([0-9a-fA-F]{16,})", out)
            if m:
                enc_key = m.group(1)
                (base / f'encrypted_key_{val}.txt').write_text(enc_key)
                break
    # Flag service: get flag and optionally decrypt
    out_flag = talk(host, flag_port, ["1\n"])  # Get flag
    (base / 'flag_get.txt').write_text(out_flag)
    # Extract ciphertext-ish token from Get flag output
    m2 = re.search(r"([0-9a-fA-F]{24,})", out_flag)
    ciph = m2.group(1) if m2 else ''
    if ciph:
        (base / 'flag_cipher.txt').write_text(ciph)
    # Try decrypt menu with available token (prefer enc_key, else ciph)
    token = enc_key or ciph
    if token:
        out_dec = talk(host, flag_port, ["3\n", token + "\n"])  # Decrypt
        (base / 'flag_decrypt.txt').write_text(out_dec)
    # Grep HTB
    txt = ''
    for p in base.glob('*.txt'):
        txt += p.read_text() + "\n"
    m3 = re.search(r"HTB\{[^}]+\}", txt)
    if m3:
        print(m3.group(0))
    else:
        print("NOFLAG")

if __name__ == '__main__':
    main()
