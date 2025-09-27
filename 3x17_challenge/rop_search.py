#!/usr/bin/env python3

import subprocess
import re

def search_gadgets():
    """Search for specific ROP gadgets"""
    
    # Use objdump to disassemble and search for gadgets
    print("=== Searching for ROP gadgets ===")
    
    try:
        # Get disassembly
        result = subprocess.run(['objdump', '-d', '3x17'], 
                              capture_output=True, text=True, timeout=30)
        
        if result.returncode != 0:
            print("Error running objdump")
            return
            
        lines = result.stdout.split('\n')
        
        # Search for specific patterns
        gadgets = {
            'pop_rdi': [],
            'pop_rsi': [],
            'pop_rdx': [],
            'pop_rax': [],
            'syscall': [],
            'ret': [],
            'mov_rdi_rax': [],
            'mov_rsi_rax': []
        }
        
        for i, line in enumerate(lines):
            # Look for pop rdi; ret
            if 'pop    %rdi' in line and i+1 < len(lines) and 'ret' in lines[i+1]:
                addr_match = re.search(r'([0-9a-f]+):', line)
                if addr_match:
                    gadgets['pop_rdi'].append(addr_match.group(1))
            
            # Look for pop rsi; ret  
            if 'pop    %rsi' in line and i+1 < len(lines) and 'ret' in lines[i+1]:
                addr_match = re.search(r'([0-9a-f]+):', line)
                if addr_match:
                    gadgets['pop_rsi'].append(addr_match.group(1))
            
            # Look for pop rdx; ret
            if 'pop    %rdx' in line and i+1 < len(lines) and 'ret' in lines[i+1]:
                addr_match = re.search(r'([0-9a-f]+):', line)
                if addr_match:
                    gadgets['pop_rdx'].append(addr_match.group(1))
            
            # Look for pop rax; ret
            if 'pop    %rax' in line and i+1 < len(lines) and 'ret' in lines[i+1]:
                addr_match = re.search(r'([0-9a-f]+):', line)
                if addr_match:
                    gadgets['pop_rax'].append(addr_match.group(1))
            
            # Look for syscall
            if 'syscall' in line:
                addr_match = re.search(r'([0-9a-f]+):', line)
                if addr_match:
                    gadgets['syscall'].append(addr_match.group(1))
        
        # Print found gadgets
        for gadget_type, addresses in gadgets.items():
            if addresses:
                print(f"\n{gadget_type}:")
                for addr in addresses[:5]:  # Show first 5
                    print(f"  0x{addr}")
                    
    except Exception as e:
        print(f"Error: {e}")

def search_functions():
    """Search for useful functions"""
    print("\n=== Searching for functions ===")
    
    try:
        result = subprocess.run(['objdump', '-t', '3x17'], 
                              capture_output=True, text=True, timeout=10)
        
        if result.returncode != 0:
            print("Error running objdump -t")
            return
            
        lines = result.stdout.split('\n')
        
        functions = []
        for line in lines:
            if 'system' in line or 'execve' in line or 'exit' in line:
                functions.append(line.strip())
        
        for func in functions[:10]:
            print(func)
            
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    search_gadgets()
    search_functions()