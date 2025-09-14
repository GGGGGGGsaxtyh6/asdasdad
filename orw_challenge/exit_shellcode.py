#!/usr/bin/env python3

def create_exit_shellcode():
    shellcode = []
    
    # Just exit
    # mov $1, %eax (sys_exit)
    shellcode.extend([0xb8, 0x01, 0x00, 0x00, 0x00])
    
    # mov $0, %ebx (exit code)
    shellcode.extend([0xbb, 0x00, 0x00, 0x00, 0x00])
    
    # int $0x80
    shellcode.extend([0xcd, 0x80])
    
    return shellcode

if __name__ == "__main__":
    shellcode = create_exit_shellcode()
    
    print("Exit shellcode bytes:")
    print(''.join(f'\\x{b:02x}' for b in shellcode))
    
    print(f"\nLength: {len(shellcode)} bytes")
    
    # Write to file
    with open('exit.bin', 'wb') as f:
        f.write(bytes(shellcode))
    
    print("Exit shellcode written to exit.bin")