#!/usr/bin/env python3
import socket
import threading
import time
import sys
import subprocess
import os

class CustomVPN:
    def __init__(self):
        self.running = False
        self.tunnel_port = 8888
        self.target_ip = "10.10.221.34"
        
    def create_tunnel(self):
        """Crear túnel VPN personalizado"""
        try:
            # Crear socket servidor
            server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
            server_socket.bind(('127.0.0.1', self.tunnel_port))
            server_socket.listen(5)
            
            print(f"🔗 VPN Túnel iniciado en puerto {self.tunnel_port}")
            print(f"🎯 Objetivo: {self.target_ip}")
            
            while self.running:
                try:
                    client_socket, addr = server_socket.accept()
                    print(f"📡 Conexión desde {addr}")
                    
                    # Crear hilo para manejar la conexión
                    thread = threading.Thread(
                        target=self.handle_connection,
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
    
    def handle_connection(self, client_socket):
        """Manejar conexiones de clientes"""
        try:
            # Crear conexión al objetivo
            target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            target_socket.connect((self.target_ip, 80))
            
            # Crear hilos para reenviar datos
            def forward_data(src, dst, direction):
                try:
                    while True:
                        data = src.recv(4096)
                        if not data:
                            break
                        dst.send(data)
                        print(f"📤 {direction}: {len(data)} bytes")
                except:
                    pass
                finally:
                    src.close()
                    dst.close()
            
            # Iniciar reenvío bidireccional
            thread1 = threading.Thread(
                target=forward_data,
                args=(client_socket, target_socket, "CLIENT->TARGET")
            )
            thread2 = threading.Thread(
                target=forward_data,
                args=(target_socket, client_socket, "TARGET->CLIENT")
            )
            
            thread1.daemon = True
            thread2.daemon = True
            thread1.start()
            thread2.start()
            
            # Esperar a que termine la conexión
            thread1.join()
            thread2.join()
            
        except Exception as e:
            print(f"❌ Error en conexión: {e}")
        finally:
            try:
                client_socket.close()
            except:
                pass
    
    def start(self):
        """Iniciar VPN"""
        self.running = True
        print("🚀 Iniciando VPN personalizada...")
        
        # Crear hilo para el túnel
        tunnel_thread = threading.Thread(target=self.create_tunnel)
        tunnel_thread.daemon = True
        tunnel_thread.start()
        
        # Configurar proxy HTTP
        self.setup_proxy()
        
        print("✅ VPN personalizada iniciada")
        print(f"🌐 Proxy HTTP: http://127.0.0.1:{self.tunnel_port}")
        print(f"🎯 Objetivo: {self.target_ip}")
        
        try:
            while self.running:
                time.sleep(1)
        except KeyboardInterrupt:
            self.stop()
    
    def setup_proxy(self):
        """Configurar proxy HTTP"""
        try:
            # Crear proxy HTTP simple
            proxy_script = f"""
import socket
import threading

def handle_request(client_socket):
    try:
        request = client_socket.recv(4096).decode()
        if request:
            # Extraer URL del request
            lines = request.split('\\n')
            first_line = lines[0]
            url = first_line.split(' ')[1]
            
            # Conectar al objetivo
            target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            target_socket.connect(('{self.target_ip}', 80))
            
            # Reenviar request
            target_socket.send(request.encode())
            
            # Reenviar respuesta
            while True:
                data = target_socket.recv(4096)
                if not data:
                    break
                client_socket.send(data)
            
            target_socket.close()
    except Exception as e:
        print(f"Error en proxy: {{e}}")
    finally:
        client_socket.close()

def start_proxy():
    server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server.bind(('127.0.0.1', {self.tunnel_port}))
    server.listen(5)
    print(f"🌐 Proxy HTTP iniciado en puerto {self.tunnel_port}")
    
    while True:
        client_socket, addr = server.accept()
        thread = threading.Thread(target=handle_request, args=(client_socket,))
        thread.daemon = True
        thread.start()

if __name__ == "__main__":
    start_proxy()
"""
            
            with open('/tmp/http_proxy.py', 'w') as f:
                f.write(proxy_script)
            
            # Iniciar proxy en segundo plano
            subprocess.Popen(['python3', '/tmp/http_proxy.py'], 
                           stdout=subprocess.DEVNULL, 
                           stderr=subprocess.DEVNULL)
            
        except Exception as e:
            print(f"❌ Error configurando proxy: {e}")
    
    def stop(self):
        """Detener VPN"""
        self.running = False
        print("🛑 Deteniendo VPN...")

if __name__ == "__main__":
    vpn = CustomVPN()
    try:
        vpn.start()
    except KeyboardInterrupt:
        vpn.stop()