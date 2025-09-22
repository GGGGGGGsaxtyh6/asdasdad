#!/usr/bin/env python3
import socket
import threading
import time
import sys
import subprocess
import os
import requests
from urllib.parse import urlparse

class RabbitVPN:
    def __init__(self):
        self.running = False
        self.target_ip = "10.10.221.34"
        self.target_port = 80
        self.local_port = 8888
        self.proxy_servers = [
            "8.8.8.8:53",
            "1.1.1.1:53", 
            "208.67.222.222:53"
        ]
        
    def test_direct_connection(self):
        """Probar conexión directa al objetivo"""
        print(f"🧪 Probando conexión directa a {self.target_ip}:{self.target_port}")
        
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.settimeout(5)
            result = sock.connect_ex((self.target_ip, self.target_port))
            sock.close()
            
            if result == 0:
                print("✅ Conexión directa exitosa")
                return True
            else:
                print("❌ No se puede conectar directamente")
                return False
        except Exception as e:
            print(f"❌ Error en conexión directa: {e}")
            return False
    
    def create_http_tunnel(self):
        """Crear túnel HTTP"""
        print("🌐 Creando túnel HTTP...")
        
        try:
            # Crear servidor HTTP simple
            server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
            server_socket.bind(('127.0.0.1', self.local_port))
            server_socket.listen(5)
            
            print(f"🚀 Servidor VPN iniciado en puerto {self.local_port}")
            print(f"🎯 Objetivo: {self.target_ip}:{self.target_port}")
            
            while self.running:
                try:
                    client_socket, addr = server_socket.accept()
                    print(f"📡 Conexión desde {addr}")
                    
                    # Manejar conexión en hilo separado
                    thread = threading.Thread(
                        target=self.handle_http_request,
                        args=(client_socket,)
                    )
                    thread.daemon = True
                    thread.start()
                    
                except Exception as e:
                    if self.running:
                        print(f"❌ Error en servidor: {e}")
                        
        except Exception as e:
            print(f"❌ Error creando túnel: {e}")
        finally:
            server_socket.close()
    
    def handle_http_request(self, client_socket):
        """Manejar requests HTTP"""
        try:
            # Recibir request
            request = client_socket.recv(4096).decode('utf-8', errors='ignore')
            if not request:
                return
                
            print(f"📨 Request recibido: {len(request)} bytes")
            
            # Intentar conectar al objetivo
            try:
                target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                target_socket.settimeout(10)
                target_socket.connect((self.target_ip, self.target_port))
                
                # Enviar request al objetivo
                target_socket.send(request.encode())
                
                # Reenviar respuesta
                while True:
                    data = target_socket.recv(4096)
                    if not data:
                        break
                    client_socket.send(data)
                    print(f"📤 Enviados {len(data)} bytes")
                
                target_socket.close()
                print("✅ Conexión completada")
                
            except Exception as e:
                print(f"❌ Error conectando al objetivo: {e}")
                
                # Enviar respuesta de error
                error_response = (
                    "HTTP/1.1 503 Service Unavailable\r\n"
                    "Content-Type: text/html\r\n"
                    "Connection: close\r\n\r\n"
                    f"<html><body><h1>503 Service Unavailable</h1>"
                    f"<p>No se puede conectar a {self.target_ip}:{self.target_port}</p>"
                    f"<p>Error: {e}</p></body></html>"
                )
                client_socket.send(error_response.encode())
                
        except Exception as e:
            print(f"❌ Error manejando request: {e}")
        finally:
            try:
                client_socket.close()
            except:
                pass
    
    def test_with_curl(self):
        """Probar con curl"""
        print("🧪 Probando con curl...")
        try:
            response = requests.get(f"http://127.0.0.1:{self.local_port}", 
                                 timeout=10)
            print(f"✅ Respuesta HTTP: {response.status_code}")
            print(f"📄 Contenido: {response.text[:200]}...")
            return True
        except Exception as e:
            print(f"❌ Error con curl: {e}")
            return False
    
    def start(self):
        """Iniciar VPN"""
        self.running = True
        print("🚀 Iniciando Rabbit VPN...")
        print(f"🎯 Objetivo: {self.target_ip}:{self.target_port}")
        
        # Probar conexión directa
        if self.test_direct_connection():
            print("✅ Conexión directa disponible")
        else:
            print("⚠️  Usando túnel HTTP")
        
        # Crear túnel en hilo separado
        tunnel_thread = threading.Thread(target=self.create_http_tunnel)
        tunnel_thread.daemon = True
        tunnel_thread.start()
        
        # Esperar un poco para que el servidor se inicie
        time.sleep(2)
        
        # Probar la conexión
        self.test_with_curl()
        
        print("✅ Rabbit VPN iniciada")
        print(f"🌐 Accede a: http://127.0.0.1:{self.local_port}")
        print("🔄 VPN ejecutándose en segundo plano...")
        
        try:
            while self.running:
                time.sleep(1)
        except KeyboardInterrupt:
            self.stop()
    
    def stop(self):
        """Detener VPN"""
        self.running = False
        print("🛑 Deteniendo Rabbit VPN...")

if __name__ == "__main__":
    vpn = RabbitVPN()
    try:
        vpn.start()
    except KeyboardInterrupt:
        vpn.stop()