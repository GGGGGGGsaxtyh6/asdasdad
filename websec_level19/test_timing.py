#!/usr/bin/env python3
"""
Test para analizar el timing del servidor
"""

import requests
import time
from email.utils import parsedate_to_datetime

URL = "https://websec.fr/level19/index.php"

print("Haciendo varias peticiones para analizar el timing...")

for i in range(5):
    before = time.time()
    response = requests.get(URL)
    after = time.time()
    
    # Obtener tiempo del servidor del header Date
    if 'Date' in response.headers:
        server_time = parsedate_to_datetime(response.headers['Date']).timestamp()
        print(f"\nPetición {i+1}:")
        print(f"  Tiempo local antes:  {before:.6f}")
        print(f"  Tiempo servidor:     {server_time:.6f}")
        print(f"  Tiempo local después:{after:.6f}")
        print(f"  Diferencia (servidor - local medio): {server_time - (before+after)/2:.6f}")
        print(f"  RTT: {(after - before):.6f}s")
    
    time.sleep(0.5)