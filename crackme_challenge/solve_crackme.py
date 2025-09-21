#!/usr/bin/env python3

import subprocess
import sys

def run_with_timeout(cmd, timeout=5):
    """Run command with timeout"""
    try:
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=timeout)
        return result.returncode, result.stdout, result.stderr
    except subprocess.TimeoutExpired:
        return -1, "", "Timeout expired"

def main():
    # Based on my analysis, the flag should be constructed as:
    # "FLAG-4092" + "849uio2jf" + "klsj4kl" = "FLAG-4092849uio2jfklsj4kl"

    flag = "FLAG-4092849uio2jfklsj4kl"
    print(f"Constructed flag: {flag}")
    print(f"Flag length: {len(flag)}")

    # Test 1: Run crackme1 normally
    print("\n=== Test 1: Normal execution ===")
    returncode, stdout, stderr = run_with_timeout("./crackme1")
    print(f"Return code: {returncode}")
    print(f"Output: {stdout.strip()}")

    # Test 2: Try with flag as input
    print("\n=== Test 2: With flag as input ===")
    returncode, stdout, stderr = run_with_timeout(f"echo '{flag}' | ./crackme1")
    print(f"Return code: {returncode}")
    print(f"Output: {stdout.strip()}")

    # Test 3: Check if it reads from stdin
    print("\n=== Test 3: Interactive input ===")
    returncode, stdout, stderr = run_with_timeout(f"printf '{flag}\n' | timeout 3 ./crackme1")
    print(f"Return code: {returncode}")
    print(f"Output: {stdout.strip()}")

    print(f"\n{'='*50}")
    print("Based on my analysis, this appears to be the flag.")
    print("The program constructs it internally and displays it.")
    print(f"{'='*50}")

if __name__ == "__main__":
    main()