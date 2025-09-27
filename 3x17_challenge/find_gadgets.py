#!/usr/bin/env python3

import subprocess
import sys

def find_gadgets():
    """Find useful ROP gadgets"""
    
    # Search for specific gadgets
    gadgets = [
        "pop rdi",
        "pop rsi", 
        "pop rdx",
        "pop rax",
        "syscall",
        "ret",
        "mov rdi, rax",
        "mov rsi, rax",
        "mov rdx, rax"
    ]
    
    print("=== Searching for ROP gadgets ===")
    
    for gadget in gadgets:
        print(f"\n--- {gadget} ---")
        try:
            result = subprocess.run(['r2', '-c', f'/R {gadget}', '3x17'], 
                                  capture_output=True, text=True, timeout=5)
            lines = result.stdout.split('\n')[:10]  # Limit to first 10 results
            for line in lines:
                if line.strip():
                    print(line)
        except subprocess.TimeoutExpired:
            print("Search timed out")
        except Exception as e:
            print(f"Error: {e}")

if __name__ == "__main__":
    find_gadgets()