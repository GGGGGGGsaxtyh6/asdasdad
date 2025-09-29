#!/usr/bin/env python3
import re
import socket
import sys

HOST = sys.argv[1] if len(sys.argv) > 1 else "94.237.57.115"
PORT = int(sys.argv[2]) if len(sys.argv) > 2 else 36179
TIMEOUT = float(sys.argv[3]) if len(sys.argv) > 3 else 10.0

PROMPT = b"Your option: "
HEX_RE = re.compile(rb"^[0-9a-fA-F]{16,}$")

def recv_until(sock: socket.socket, token: bytes, max_bytes: int = 1_000_000) -> bytes:
    buf = bytearray()
    while True:
        chunk = sock.recv(4096)
        if not chunk:
            break
        buf.extend(chunk)
        if token in buf:
            break
        if len(buf) > max_bytes:
            break
    return bytes(buf)

def main() -> int:
    with socket.create_connection((HOST, PORT), timeout=TIMEOUT) as s:
        s.settimeout(TIMEOUT)
        data = recv_until(s, PROMPT)
        # print the menu to stderr for debugging
        sys.stderr.write(data.decode(errors="ignore"))
        s.sendall(b"1\n")
        # after selecting option 1, the server should return a single hex line + newline
        resp = recv_until(s, b"\n")
        # collect remaining for a brief moment (non-blocking-ish)
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                resp += chunk
        except Exception:
            pass
    # parse first hex-only line
    for line in resp.splitlines():
        if HEX_RE.match(line.strip()):
            print(line.strip().decode())
            return 0
    # fallback: print nothing
    return 1

if __name__ == "__main__":
    raise SystemExit(main())

