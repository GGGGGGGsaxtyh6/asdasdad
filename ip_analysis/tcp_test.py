#!/usr/bin/env python3
import socket
import time
import sys

def test_tcp_service(host, port, message):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.settimeout(5)
        sock.connect((host, port))
        
        # Send message
        sock.send(message.encode() + b'\n')
        
        # Try to receive response
        try:
            data = sock.recv(1024)
            return data.decode('utf-8', errors='ignore')
        except socket.timeout:
            return None
        except Exception as e:
            return f"Error receiving: {e}"
    except Exception as e:
        return f"Error: {e}"
    finally:
        try:
            sock.close()
        except:
            pass

host = "5.161.142.77"
port = 10190

# Test various commands
commands = [
    "help",
    "?",
    "commands",
    "list",
    "menu",
    "test",
    "hello",
    "ping",
    "version",
    "encrypt test",
    "decrypt test", 
    "encode test",
    "decode test",
    "base64 test",
    "hex test",
    "md5 test",
    "sha256 test",
    "GET / HTTP/1.1",
    "POST / HTTP/1.1",
    "OPTIONS / HTTP/1.1"
]

print(f"Testing TCP service at {host}:{port}")
print("=" * 50)

for cmd in commands:
    print(f"Testing: {cmd}")
    response = test_tcp_service(host, port, cmd)
    if response:
        print(f"Response: {response}")
    else:
        print("No response")
    print("-" * 30)
    time.sleep(0.5)