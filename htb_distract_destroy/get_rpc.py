#!/usr/bin/env python3
import socket
import re

def get_connection_info():
    host = "94.237.57.211"
    port = 55233
    
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(15)
        s.connect((host, port))
        
        # Recibir menú inicial
        data = s.recv(8192).decode(errors='ignore')
        print("=== MENU ===")
        print(data)
        print("=" * 50)
        
        # Enviar opción 1 para obtener información de conexión
        s.sendall(b"1\n")
        
        # Recibir respuesta
        response = b""
        s.settimeout(5)
        try:
            while True:
                chunk = s.recv(4096)
                if not chunk:
                    break
                response += chunk
        except socket.timeout:
            pass
        
        decoded = response.decode(errors='ignore')
        print("=== CONNECTION INFO ===")
        print(decoded)
        print("=" * 50)
        
        s.close()
        
        # Extraer información relevante
        rpc_match = re.search(r'(?:rpc|RPC|endpoint|Endpoint)[:\s]+(\S+)', decoded)
        private_key_match = re.search(r'(?:private|Private)[^:]*key[:\s]+(\S+)', decoded)
        setup_match = re.search(r'(?:setup|Setup)[^:]*(?:address|contract)[:\s]+(0x[a-fA-F0-9]+)', decoded)
        target_match = re.search(r'(?:target|Target)[^:]*(?:address|contract)[:\s]+(0x[a-fA-F0-9]+)', decoded)
        
        print("\n=== EXTRACTED INFO ===")
        if rpc_match:
            print(f"RPC: {rpc_match.group(1)}")
        if private_key_match:
            print(f"Private Key: {private_key_match.group(1)}")
        if setup_match:
            print(f"Setup Contract: {setup_match.group(1)}")
        if target_match:
            print(f"Target Contract: {target_match.group(1)}")
        
        # Guardar en archivo
        with open('connection_info.txt', 'w') as f:
            f.write(decoded)
            
        return decoded
        
    except Exception as e:
        print(f"Error: {e}")
        import traceback
        traceback.print_exc()
        return None

if __name__ == "__main__":
    get_connection_info()
