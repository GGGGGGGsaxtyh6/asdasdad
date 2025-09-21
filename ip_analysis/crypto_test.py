#!/usr/bin/env python3
import socket
import time
import sys

def test_udp_service(host, port, message):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.settimeout(5)
        
        # Send message
        sock.sendto(message.encode(), (host, port))
        
        # Try to receive response
        try:
            data, addr = sock.recvfrom(1024)
            return data.decode('utf-8', errors='ignore')
        except socket.timeout:
            return None
        except Exception as e:
            return f"Error receiving: {e}"
    except Exception as e:
        return f"Error: {e}"
    finally:
        sock.close()

host = "5.161.142.77"
port = 10155

# Test various crypto-related commands
commands = [
    "encrypt test",
    "decrypt test", 
    "encode test",
    "decode test",
    "base64 test",
    "hex test",
    "md5 test",
    "sha256 test",
    "caesar test",
    "rot13 test",
    "xor test",
    "aes test",
    "rsa test",
    "help",
    "?",
    "commands",
    "list",
    "menu",
    "test",
    "hello",
    "ping",
    "version"
]

print(f"Testing UDP service at {host}:{port}")
print("=" * 50)

for cmd in commands:
    print(f"Testing: {cmd}")
    response = test_udp_service(host, port, cmd)
    if response:
        print(f"Response: {response}")
    else:
        print("No response")
    print("-" * 30)
    time.sleep(0.5)