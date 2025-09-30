#!/usr/bin/env python3
import sys, socket, os

if len(sys.argv) < 4:
    print(f"usage: {sys.argv[0]} <host> <port> <timeout_s>")
    sys.exit(1)
host, port, to = sys.argv[1], int(sys.argv[2]), float(sys.argv[3])
s=socket.socket()
s.settimeout(to)
s.connect((host, port))
s.shutdown(socket.SHUT_WR)
try:
    data = s.recv(1<<20)
    sys.stdout.buffer.write(data)
except socket.timeout:
    pass
finally:
    s.close()
