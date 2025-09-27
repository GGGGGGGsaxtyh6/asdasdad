#!/usr/bin/env python3

import subprocess
import sys

def run_radare2_analysis():
    """Run radare2 analysis on the binary"""
    
    # First, let's analyze the binary with radare2
    r2_commands = [
        "aaa",  # Analyze all
        "afl",  # List functions
        "pdf @main",  # Disassemble main function
        "is",   # List symbols
        "ii",   # List imports
        "ie",   # List entry points
    ]
    
    for cmd in r2_commands:
        print(f"\n=== Running: {cmd} ===")
        try:
            result = subprocess.run(['r2', '-c', cmd, '3x17'], 
                                  capture_output=True, text=True, timeout=10)
            print(result.stdout)
            if result.stderr:
                print("STDERR:", result.stderr)
        except subprocess.TimeoutExpired:
            print("Command timed out")
        except Exception as e:
            print(f"Error: {e}")

if __name__ == "__main__":
    run_radare2_analysis()