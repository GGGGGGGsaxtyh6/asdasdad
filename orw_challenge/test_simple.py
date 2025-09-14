#!/usr/bin/env python3

# Very simple shellcode test
# Just try to exit cleanly first

def create_exit_shellcode():
    shellcode = b""
    
    # Just exit cleanly
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax (sys_exit = 1)
    shellcode += b"\xcd\x80"              # int 0x80
    
    return shellcode

def create_open_test_shellcode():
    shellcode = b""
    
    # Try to open a simple file first
    # Push "/tmp/test\0"
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x50"                  # push eax (null terminator)
    shellcode += b"\x68\x74\x73\x65\x74"  # push "test"
    shellcode += b"\x68\x2f\x74\x6d\x70"  # push "/tmp"
    
    # open syscall
    shellcode += b"\x89\xe3"              # mov ebx, esp (filename)
    shellcode += b"\x31\xc9"              # xor ecx, ecx (flags)
    shellcode += b"\x31\xd2"              # xor edx, edx (mode)
    shellcode += b"\xb0\x05"              # mov al, 5 (open)
    shellcode += b"\xcd\x80"              # int 0x80
    
    # exit
    shellcode += b"\x31\xc0"              # xor eax, eax
    shellcode += b"\x40"                  # inc eax
    shellcode += b"\xcd\x80"              # int 0x80
    
    return shellcode

def main():
    # Test 1: Just exit
    exit_shellcode = create_exit_shellcode()
    print(f"Exit shellcode: {exit_shellcode.hex()}")
    with open('/workspace/orw_challenge/exit.bin', 'wb') as f:
        f.write(exit_shellcode)
    
    # Test 2: Try to open a file
    open_shellcode = create_open_test_shellcode()
    print(f"Open test shellcode: {open_shellcode.hex()}")
    with open('/workspace/orw_challenge/open_test.bin', 'wb') as f:
        f.write(open_shellcode)
    
    print("Test shellcodes created")

if __name__ == "__main__":
    main()