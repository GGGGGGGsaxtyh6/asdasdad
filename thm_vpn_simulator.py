#!/usr/bin/env python3
import socket
import threading
import time
import subprocess
import os

class THMVPNSimulator:
    def __init__(self):
        self.running = False
        self.target_ip = "10.10.221.34"
        self.vpn_server = "52.16.156.56"
        self.vpn_port = 1194
        
    def simulate_vpn_connection(self):
        """Simular conexión VPN a TryHackMe"""
        print("🚀 Iniciando simulador VPN TryHackMe...")
        print(f"🎯 Objetivo: {self.target_ip}")
        print(f"🔗 Servidor VPN: {self.vpn_server}:{self.vpn_port}")
        
        # Configurar rutas de red
        self.setup_routes()
        
        # Crear túnel de datos
        self.create_data_tunnel()
        
    def setup_routes(self):
        """Configurar rutas de red para TryHackMe"""
        print("🔧 Configurando rutas de red...")
        
        try:
            # Agregar rutas para redes de TryHackMe
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
                    
        except Exception as e:
            print(f"❌ Error configurando rutas: {e}")
    
    def create_data_tunnel(self):
        """Crear túnel de datos para TryHackMe"""
        print("🌐 Creando túnel de datos...")
        
        # Crear servidor local que redirija al objetivo
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_socket.bind(('127.0.0.1', 8888))
        server_socket.listen(5)
        
        print("✅ Túnel de datos creado en puerto 8888")
        
        while self.running:
            try:
                client_socket, addr = server_socket.accept()
                print(f"📡 Conexión desde {addr}")
                
                # Crear hilo para manejar la conexión
                thread = threading.Thread(
                    target=self.handle_thm_connection,
                    args=(client_socket,)
                )
                thread.daemon = True
                thread.start()
                
            except Exception as e:
                if self.running:
                    print(f"❌ Error en servidor: {e}")
    
    def handle_thm_connection(self, client_socket):
        """Manejar conexiones a máquinas de TryHackMe"""
        try:
            # Recibir datos del cliente
            data = client_socket.recv(4096)
            if not data:
                return
                
            print(f"📨 Datos recibidos: {len(data)} bytes")
            
            # Intentar conectar directamente al objetivo
            try:
                target_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
                target_socket.settimeout(10)
                target_socket.connect((self.target_ip, 80))
                
                print(f"✅ Conectado a {self.target_ip}:80")
                
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
                error_msg = f"Error: No se puede conectar a {self.target_ip}:80\n{e}\n"
                client_socket.send(error_msg.encode())
                
        except Exception as e:
            print(f"❌ Error manejando conexión: {e}")
        finally:
            try:
                client_socket.close()
            except:
                pass
    
    def test_connection(self):
        """Probar conexión a la máquina de TryHackMe"""
        print("🧪 Probando conexión a TryHackMe...")
        
        try:
            # Probar con curl
            result = subprocess.run([
                "curl", "-v", f"http://{self.target_ip}", 
                "--connect-timeout", "10"
            ], capture_output=True, text=True, timeout=15)
            
            if result.returncode == 0:
                print("✅ Conexión exitosa con curl")
                print(f"📄 Respuesta: {result.stdout[:200]}...")
            else:
                print(f"❌ Error con curl: {result.stderr}")
                
        except Exception as e:
            print(f"❌ Error probando conexión: {e}")
    
    def start(self):
        """Iniciar simulador VPN"""
        self.running = True
        print("🚀 Iniciando simulador VPN TryHackMe...")
        
        # Simular conexión VPN
        self.simulate_vpn_connection()
        
        # Probar conexión
        self.test_connection()
        
        print("✅ Simulador VPN iniciado")
        print(f"🌐 Accede a: http://127.0.0.1:8888")
        print("🔄 VPN ejecutándose en segundo plano...")
        
        try:
            while self.running:
                time.sleep(1)
        except KeyboardInterrupt:
            self.stop()
    
    def stop(self):
        """Detener simulador VPN"""
        self.running = False
        print("🛑 Deteniendo simulador VPN...")

if __name__ == "__main__":
    vpn = THMVPNSimulator()
    try:
        vpn.start()
    except KeyboardInterrupt:
        vpn.stop()