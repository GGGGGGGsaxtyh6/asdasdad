#!/usr/bin/env python3

import subprocess
import re

def find_gadgets():
    """Find useful ROP gadgets systematically"""
    
    print("=== Finding ROP gadgets ===")
    
    try:
        # Get disassembly
        result = subprocess.run(['objdump', '-d', '3x17'], 
                              capture_output=True, text=True, timeout=30)
        
        lines = result.stdout.split('\n')
        
        # Look for specific gadget patterns
        gadgets = []
        
        for i, line in enumerate(lines):
            # Skip non-instruction lines
            if not re.match(r'^\s*[0-9a-f]+:', line):
                continue
                
            # Extract address
            addr_match = re.search(r'^(\s*)([0-9a-f]+):', line)
            if not addr_match:
                continue
                
            addr = addr_match.group(2)
            instruction = line.split('\t')[-1].strip()
            
            # Look for pop rdi; ret
            if 'pop    %rdi' in instruction:
                # Check if next instruction is ret
                if i+1 < len(lines):
                    next_line = lines[i+1]
                    if 'ret' in next_line and re.match(r'^\s*[0-9a-f]+:', next_line):
                        gadgets.append(f"0x{addr}: pop rdi; ret")
            
            # Look for pop rsi; ret
            if 'pop    %rsi' in instruction:
                if i+1 < len(lines):
                    next_line = lines[i+1]
                    if 'ret' in next_line and re.match(r'^\s*[0-9a-f]+:', next_line):
                        gadgets.append(f"0x{addr}: pop rsi; ret")
            
            # Look for pop rdx; ret
            if 'pop    %rdx' in instruction:
                if i+1 < len(lines):
                    next_line = lines[i+1]
                    if 'ret' in next_line and re.match(r'^\s*[0-9a-f]+:', next_line):
                        gadgets.append(f"0x{addr}: pop rdx; ret")
            
            # Look for pop rax; ret
            if 'pop    %rax' in instruction:
                if i+1 < len(lines):
                    next_line = lines[i+1]
                    if 'ret' in next_line and re.match(r'^\s*[0-9a-f]+:', next_line):
                        gadgets.append(f"0x{addr}: pop rax; ret")
            
            # Look for syscall
            if 'syscall' in instruction:
                gadgets.append(f"0x{addr}: syscall")
        
        print("Found gadgets:")
        for gadget in gadgets[:20]:  # Show first 20
            print(f"  {gadget}")
            
        return gadgets
        
    except Exception as e:
        print(f"Error: {e}")
        return []

def find_functions():
    """Find useful functions"""
    print("\n=== Finding functions ===")
    
    try:
        # Search for system function
        result = subprocess.run(['objdump', '-d', '3x17'], 
                              capture_output=True, text=True, timeout=20)
        
        lines = result.stdout.split('\n')
        
        functions = []
        for line in lines:
            if '<system@plt>' in line or '<system>' in line:
                addr_match = re.search(r'^(\s*)([0-9a-f]+):', line)
                if addr_match:
                    functions.append(f"0x{addr_match.group(2)}: system")
        
        for func in functions:
            print(f"  {func}")
            
        return functions
        
    except Exception as e:
        print(f"Error: {e}")
        return []

if __name__ == "__main__":
    gadgets = find_gadgets()
    functions = find_functions()