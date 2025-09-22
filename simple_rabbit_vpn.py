#!/usr/bin/env python3
import socket
import threading
import time
import sys

def create_vpn_tunnel():
    """Crear túnel VPN simple"""
    target_ip = "10.10.221.34"
    target_port = 80
    local_port = 8888
    
    print("🚀 Iniciando Rabbit VPN...")
    print(f"🎯 Objetivo: {target_ip}:{target_port}")
    print(f"🌐 Puerto local: {local_port}")
    
    try:
        # Crear servidor
        server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server.bind(('127.0.0.1', local_port))
        server.listen(5)
        
        print(f"✅ Servidor VPN iniciado en puerto {local_port}")
        
        while True:
            try:
                client_socket, addr = server.accept()
                print(f"📡 Conexión desde {addr}")
                
                # Crear hilo para manejar la conexión
                thread = threading.Thread(
                    target=handle_connection,
                    args=(client_socket, target_ip, target_port)
                )
                thread.daemon = True
                thread.start()
                
            except Exception as e:
                print(f"❌ Error en servidor: {e}")
                
    except Exception as e:
        print(f"❌ Error creando servidor: {e}")

def handle_connection(client_socket, target_ip, target_port):
    """Manejar conexión de cliente"""
    try:
        # Recibir datos del cliente
        data = client_socket.recv(4096)
        if not data:
            return
            
        print(f"📨 Datos recibidos: {len(data)} bytes")
        
        # Intentar conectar al objetivo
        try:
            target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            target_socket.settimeout(10)
            target_socket.connect((target_ip, target_port))
            
            print(f"✅ Conectado a {target_ip}:{target_port}")
            
            # Enviar datos al objetivo
            target_socket.send(data)
            
            # Reenviar respuesta
            while True:
                response = target_socket.recv(4096)
                if not response:
                    break
                client_socket.send(response)
                print(f"📤 Enviados {len(response)} bytes")
            
            target_socket.close()
            print("✅ Conexión completada")
            
        except Exception as e:
            print(f"❌ Error conectando al objetivo: {e}")
            
            # Enviar respuesta de error
            error_msg = f"Error: No se puede conectar a {target_ip}:{target_port}\n{e}\n"
            client_socket.send(error_msg.encode())
            
    except Exception as e:
        print(f"❌ Error manejando conexión: {e}")
    finally:
        try:
            client_socket.close()
        except:
            pass

if __name__ == "__main__":
    try:
        create_vpn_tunnel()
    except KeyboardInterrupt:
        print("\n🛑 VPN detenida")
        sys.exit(0)