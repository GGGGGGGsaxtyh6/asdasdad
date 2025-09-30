#!/usr/bin/env python3
"""
Debug para verificar que la sesión se mantiene correctamente
"""

import requests
from html.parser import HTMLParser

URL = "https://websec.fr/level19/index.php"

class TokenParser(HTMLParser):
    def __init__(self):
        super().__init__()
        self.token = None
    def handle_starttag(self, tag, attrs):
        if tag == 'input' and dict(attrs).get('name') == 'token':
            self.token = dict(attrs).get('value')

print("[*] Test de sesión\n")

sess = requests.Session()

print("1. Primer GET:")
r1 = sess.get(URL)
print(f"   Cookies recibidas: {sess.cookies}")
p1 = TokenParser()
p1.feed(r1.text)
print(f"   Token CSRF: {p1.token}")

print("\n2. Segundo GET (debería mantener la sesión):")
r2 = sess.get(URL)
print(f"   Cookies: {sess.cookies}")
p2 = TokenParser()
p2.feed(r2.text)
print(f"   Token CSRF: {p2.token}")

print("\n3. POST con captcha incorrecto:")
data = {
    'captcha': 'AAAAAAAAAAAAAAAAAAAAAAAAA',
    'token': p2.token,
    'submit': ''
}
r3 = sess.post(URL, data=data)

print(f"   Status: {r3.status_code}")
if 'Invalid captcha' in r3.text:
    print("   Resultado: Captcha inválido (esperado)")
elif 'Invalid session token' in r3.text:
    print("   Resultado: Token inválido (problema de sesión!)")
else:
    print(f"   Resultado inesperado: {r3.text[:200]}")

print(f"\n[*] Las cookies se mantienen: {sess.cookies}")