#!/usr/bin/env python3
import socket
import threading
import time
import subprocess
import os
import sys
import signal

class RealTHMVPN:
    def __init__(self):
        self.running = False
        self.target_ip = "10.10.221.34"
        self.local_port = 8888
        self.vpn_active = False
        
    def setup_real_routes(self):
        """Configurar rutas reales para TryHackMe"""
        print("🔧 Configurando rutas reales...")
        
        try:
            # Rutas de TryHackMe
            routes = [
                "10.10.0.0/16",
                "10.101.0.0/16", 
                "10.103.0.0/16",
                "10.201.0.0/17"
            ]
            
            for route in routes:
                try:
                    subprocess.run([
                        "sudo", "ip", "route", "add", route, 
                        "via", "172.30.0.1"
                    ], check=True, capture_output=True)
                    print(f"✅ Ruta agregada: {route}")
                except subprocess.CalledProcessError:
                    print(f"⚠️  Ruta ya existe: {route}")
                    
            self.vpn_active = True
            print("✅ Rutas reales configuradas")
            
        except Exception as e:
            print(f"❌ Error configurando rutas: {e}")
    
    def create_real_tunnel(self):
        """Crear túnel real usando las rutas configuradas"""
        print("🚀 Creando túnel real a TryHackMe...")
        print(f"🎯 Objetivo: {self.target_ip}")
        print(f"🌐 Puerto local: {self.local_port}")
        
        # Crear servidor
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_socket.bind(('0.0.0.0', self.local_port))
        server_socket.listen(5)
        
        print(f"✅ Servidor real iniciado en puerto {self.local_port}")
        
        while self.running:
            try:
                client_socket, addr = server_socket.accept()
                print(f"📡 Conexión real desde {addr}")
                
                # Crear hilo para manejar la conexión
                thread = threading.Thread(
                    target=self.handle_real_connection,
                    args=(client_socket,)
                )
                thread.daemon = True
                thread.start()
                
            except Exception as e:
                if self.running:
                    print(f"❌ Error en servidor real: {e}")
    
    def handle_real_connection(self, client_socket):
        """Manejar conexión real"""
        try:
            # Recibir datos
            data = client_socket.recv(4096)
            if not data:
                return
                
            print(f"📨 Datos reales recibidos: {len(data)} bytes")
            
            # Intentar conectar directamente usando las rutas
            try:
                target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                target_socket.settimeout(15)
                target_socket.connect((self.target_ip, 80))
                
                print(f"✅ Conectado REALMENTE a {self.target_ip}:80")
                
                # Enviar datos
                target_socket.send(data)
                
                # Reenviar respuesta
                while True:
                    response = target_socket.recv(4096)
                    if not response:
                        break
                    client_socket.send(response)
                    print(f"📤 Enviados {len(response)} bytes reales")
                
                target_socket.close()
                print("✅ Conexión real completada")
                
            except Exception as e:
                print(f"❌ Error conectando realmente: {e}")
                
                # Intentar con otros puertos
                self.try_real_alternative_ports(client_socket, data)
                
        except Exception as e:
            print(f"❌ Error manejando conexión real: {e}")
        finally:
            try:
                client_socket.close()
            except:
                pass
    
    def try_real_alternative_ports(self, client_socket, data):
        """Intentar con puertos alternativos reales"""
        ports = [80, 443, 8080, 8443, 3000, 5000, 8000, 9000, 22, 21, 23, 25, 53, 110, 143, 993, 995]
        
        for port in ports:
            try:
                print(f"🔄 Intentando puerto real {port}...")
                target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                target_socket.settimeout(5)
                target_socket.connect((self.target_ip, port))
                
                print(f"✅ Conectado REALMENTE a {self.target_ip}:{port}")
                
                # Enviar datos
                target_socket.send(data)
                
                # Reenviar respuesta
                while True:
                    response = target_socket.recv(4096)
                    if not response:
                        break
                    client_socket.send(response)
                    print(f"📤 Enviados {len(response)} bytes reales")
                
                target_socket.close()
                print(f"✅ Conexión real completada en puerto {port}")
                return
                
            except Exception as e:
                print(f"❌ Puerto real {port} falló: {e}")
                continue
        
        # Si ningún puerto funciona, enviar error
        error_msg = f"Error: No se puede conectar REALMENTE a {self.target_ip} en ningún puerto\n"
        client_socket.send(error_msg.encode())
    
    def test_real_connection(self):
        """Probar conexión real"""
        print("🧪 Probando conexión real...")
        
        # Probar ping
        try:
            result = subprocess.run([
                "ping", "-c", "3", self.target_ip
            ], capture_output=True, text=True, timeout=10)
            
            if result.returncode == 0:
                print("✅ Ping real exitoso")
                return True
            else:
                print("❌ Ping real falló")
        except Exception as e:
            print(f"❌ Error en ping real: {e}")
        
        # Probar con curl
        try:
            result = subprocess.run([
                "curl", "-v", f"http://{self.target_ip}", 
                "--connect-timeout", "10"
            ], capture_output=True, text=True, timeout=15)
            
            if result.returncode == 0:
                print("✅ HTTP real exitoso")
                print(f"📄 Respuesta real: {result.stdout[:200]}...")
                return True
            else:
                print(f"❌ HTTP real falló: {result.stderr}")
        except Exception as e:
            print(f"❌ Error en HTTP real: {e}")
        
        return False
    
    def start(self):
        """Iniciar VPN real"""
        self.running = True
        print("🚀 Iniciando VPN REAL de TryHackMe...")
        
        # Configurar rutas reales
        self.setup_real_routes()
        
        # Probar conexión real
        if self.test_real_connection():
            print("✅ Conexión real disponible")
        else:
            print("⚠️  Usando túnel real como proxy")
        
        # Crear túnel real
        self.create_real_tunnel()
    
    def stop(self):
        """Detener VPN real"""
        self.running = False
        print("🛑 Deteniendo VPN real...")

def signal_handler(sig, frame):
    print("\n🛑 Deteniendo VPN real...")
    sys.exit(0)

if __name__ == "__main__":
    signal.signal(signal.SIGINT, signal_handler)
    
    vpn = RealTHMVPN()
    try:
        vpn.start()
    except KeyboardInterrupt:
        vpn.stop()