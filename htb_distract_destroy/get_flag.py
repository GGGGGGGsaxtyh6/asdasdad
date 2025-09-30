#!/usr/bin/env python3
import requests
import re

try:
    # Get flag from API endpoint
    print("[*] Checking for flag...")
    
    # Try different endpoints
    endpoints = [
        'http://94.237.57.211:55233/flag',
        'http://94.237.57.211:55233/api/flag',
        'http://94.237.57.211:55233/get_flag',
    ]
    
    for endpoint in endpoints:
        try:
            resp = requests.get(endpoint, timeout=10)
            if resp.status_code == 200:
                print(f"[+] Response from {endpoint}:")
                print(resp.text)
                
                # Look for flag pattern
                flags = re.findall(r'HTB\{[^}]+\}', resp.text)
                if flags:
                    print(f"\n[+] ✅ FLAG FOUND: {flags[0]}")
                    with open('flag.txt', 'w') as f:
                        f.write(flags[0])
                    break
        except Exception as e:
            print(f"[-] Error with {endpoint}: {e}")
    
    # Also check the main page after solving
    print("\n[*] Checking main page after solve...")
    resp = requests.get('http://94.237.57.211:55233/', timeout=10)
    flags = re.findall(r'HTB\{[^}]+\}', resp.text)
    if flags:
        print(f"[+] ✅ FLAG FOUND: {flags[0]}")
        with open('flag.txt', 'w') as f:
            f.write(flags[0])
    
except Exception as e:
    print(f"[-] Error: {e}")
