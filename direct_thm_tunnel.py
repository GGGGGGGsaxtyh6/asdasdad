#!/usr/bin/env python3
import socket
import threading
import time
import subprocess
import os
import sys

class DirectTHMTunnel:
    def __init__(self):
        self.running = False
        self.target_ip = "10.10.221.34"
        self.local_port = 8888
        
    def create_direct_tunnel(self):
        """Crear túnel directo usando las rutas configuradas"""
        print("🚀 Creando túnel directo a TryHackMe...")
        print(f"🎯 Objetivo: {self.target_ip}")
        print(f"🌐 Puerto local: {self.local_port}")
        
        # Crear servidor
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_socket.bind(('0.0.0.0', self.local_port))
        server_socket.listen(5)
        
        print(f"✅ Servidor iniciado en puerto {self.local_port}")
        
        while self.running:
            try:
                client_socket, addr = server_socket.accept()
                print(f"📡 Conexión desde {addr}")
                
                # Crear hilo para manejar la conexión
                thread = threading.Thread(
                    target=self.handle_direct_connection,
                    args=(client_socket,)
                )
                thread.daemon = True
                thread.start()
                
            except Exception as e:
                if self.running:
                    print(f"❌ Error en servidor: {e}")
    
    def handle_direct_connection(self, client_socket):
        """Manejar conexión directa"""
        try:
            # Recibir datos
            data = client_socket.recv(4096)
            if not data:
                return
                
            print(f"📨 Datos recibidos: {len(data)} bytes")
            
            # Intentar conectar directamente
            try:
                target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                target_socket.settimeout(15)
                target_socket.connect((self.target_ip, 80))
                
                print(f"✅ Conectado directamente a {self.target_ip}:80")
                
                # Enviar datos
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
                print(f"❌ Error conectando: {e}")
                
                # Intentar con otros puertos
                self.try_alternative_ports(client_socket, data)
                
        except Exception as e:
            print(f"❌ Error manejando conexión: {e}")
        finally:
            try:
                client_socket.close()
            except:
                pass
    
    def try_alternative_ports(self, client_socket, data):
        """Intentar con puertos alternativos"""
        ports = [80, 443, 8080, 8443, 3000, 5000, 8000, 9000]
        
        for port in ports:
            try:
                print(f"🔄 Intentando puerto {port}...")
                target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                target_socket.settimeout(5)
                target_socket.connect((self.target_ip, port))
                
                print(f"✅ Conectado a {self.target_ip}:{port}")
                
                # Enviar datos
                target_socket.send(data)
                
                # Reenviar respuesta
                while True:
                    response = target_socket.recv(4096)
                    if not response:
                        break
                    client_socket.send(response)
                    print(f"📤 Enviados {len(response)} bytes")
                
                target_socket.close()
                print(f"✅ Conexión completada en puerto {port}")
                return
                
            except Exception as e:
                print(f"❌ Puerto {port} falló: {e}")
                continue
        
        # Si ningún puerto funciona, enviar error
        error_msg = f"Error: No se puede conectar a {self.target_ip} en ningún puerto\n"
        client_socket.send(error_msg.encode())
    
    def test_direct_connection(self):
        """Probar conexión directa"""
        print("🧪 Probando conexión directa...")
        
        # Probar ping
        try:
            result = subprocess.run([
                "ping", "-c", "3", self.target_ip
            ], capture_output=True, text=True, timeout=10)
            
            if result.returncode == 0:
                print("✅ Ping exitoso")
                return True
            else:
                print("❌ Ping falló")
        except Exception as e:
            print(f"❌ Error en ping: {e}")
        
        # Probar con curl
        try:
            result = subprocess.run([
                "curl", "-v", f"http://{self.target_ip}", 
                "--connect-timeout", "10"
            ], capture_output=True, text=True, timeout=15)
            
            if result.returncode == 0:
                print("✅ HTTP exitoso")
                print(f"📄 Respuesta: {result.stdout[:200]}...")
                return True
            else:
                print(f"❌ HTTP falló: {result.stderr}")
        except Exception as e:
            print(f"❌ Error en HTTP: {e}")
        
        return False
    
    def start(self):
        """Iniciar túnel directo"""
        self.running = True
        print("🚀 Iniciando túnel directo a TryHackMe...")
        
        # Probar conexión directa
        if self.test_direct_connection():
            print("✅ Conexión directa disponible")
        else:
            print("⚠️  Usando túnel como proxy")
        
        # Crear túnel
        self.create_direct_tunnel()
    
    def stop(self):
        """Detener túnel"""
        self.running = False
        print("🛑 Deteniendo túnel...")

if __name__ == "__main__":
    tunnel = DirectTHMTunnel()
    try:
        tunnel.start()
    except KeyboardInterrupt:
        tunnel.stop()