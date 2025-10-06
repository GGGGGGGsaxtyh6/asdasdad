#!/usr/bin/env python3
import socket, time, re, sys
from pathlib import Path

def conn(host: str, port: int, timeout: float = 6.0):
    s = socket.create_connection((host, port), timeout=timeout)
    s.settimeout(0.8)
    return s

def recv_idle(s, idle=0.25, rounds=240):
    buf = b''
    for _ in range(rounds):
        try:
            chunk = s.recv(8192)
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

def parse_int(label: str, text: str):
    m = re.search(rf"^{label}\s*=\s*(\d+)$", text, re.M)
    return int(m.group(1)) if m else None

def parse_pair(text: str):
    m = re.search(r"\((\d+)\s*,\s*(\d+)\)", text)
    if not m:
        return None, None
    return int(m.group(1)), int(m.group(2))

if __name__ == '__main__':
    if len(sys.argv) < 4:
        print('Usage: elgamal_recover.py <host> <params_port_50419> <elg_port_56357>')
        sys.exit(2)
    host = sys.argv[1]
    pport = int(sys.argv[2])
    eport = int(sys.argv[3])
    outdir = Path('/workspace/recon/httpx') / f'{host}_elgamal_recover'
    outdir.mkdir(parents=True, exist_ok=True)
    # 1) Fetch p from params service
    try:
        s1 = conn(host, pport)
        _ = recv_idle(s1)
        send_line(s1, '1')
        params = recv_idle(s1)
        (outdir / 'params.txt').write_text(params)
        p = parse_int('p', params)
        # Close
        try:
            s1.close()
        except Exception:
            pass
    except Exception as e:
        p = None
    if not p:
        print('NOP')
        sys.exit(0)
    # 2) On ElGamal: get encrypted flag pair
    s2 = conn(host, eport)
    _ = recv_idle(s2)
    send_line(s2, '1')
    flag_out = recv_idle(s2)
    (outdir / 'flag_out.txt').write_text(flag_out)
    c1f, c2f = parse_pair(flag_out)
    # 3) Encrypt m=1 to get session secret
    send_line(s2, '2')
    _ = recv_idle(s2)
    send_line(s2, '1')
    enc1_out = recv_idle(s2)
    (outdir / 'enc1_out.txt').write_text(enc1_out)
    c1e, c2e = parse_pair(enc1_out)
    try:
        s2.close()
    except Exception:
        pass
    if not all([c1f, c2f, c1e, c2e]):
        print('NOFLAG')
        sys.exit(0)
    # If same k reused, c1 should match; proceed regardless and attempt decryption using s=c2e
    s_secret = c2e % p
    try:
        inv_s = pow(s_secret, -1, p)
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
    (outdir / 'flag_plain.txt').write_text(txt)
    print(txt if 'HTB{' in txt else 'NOFLAG')
