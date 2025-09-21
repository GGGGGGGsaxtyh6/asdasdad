#!/usr/bin/env python3
import socket
import time
import sys

def test_udp_binary(host, port, data):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.settimeout(5)
        
        # Send binary data
        sock.sendto(data, (host, port))
        
        # Try to receive response
        try:
            response, addr = sock.recvfrom(1024)
            return response
        except socket.timeout:
            return None
        except Exception as e:
            return f"Error receiving: {e}".encode()
    except Exception as e:
        return f"Error: {e}".encode()
    finally:
        sock.close()

host = "5.161.142.77"
port = 10155

# Test various binary patterns
test_data = [
    b"\x00\x01\x02\x03",
    b"\xFF\xFE\xFD\xFC",
    b"\x48\x65\x6C\x6C\x6F",  # "Hello" in hex
    b"\x01\x00\x00\x00",      # Little endian 1
    b"\x00\x00\x00\x01",      # Big endian 1
    b"GET",
    b"POST",
    b"HEAD",
    b"PUT",
    b"DELETE",
    b"OPTIONS",
    b"TRACE",
    b"CONNECT",
    b"\x16\x03\x01",          # TLS handshake start
    b"\x80\x01\x00\x01",      # DNS query pattern
]

print(f"Testing binary data at {host}:{port}")
print("=" * 50)

for i, data in enumerate(test_data):
    print(f"Test {i+1}: {data.hex() if len(data) <= 10 else data[:10].hex()+'...'}")
    response = test_udp_binary(host, port, data)
    if response:
        print(f"Response: {response.hex() if len(response) <= 20 else response[:20].hex()+'...'}")
        try:
            print(f"As text: {response.decode('utf-8', errors='ignore')}")
        except:
            pass
    else:
        print("No response")
    print("-" * 30)
    time.sleep(0.5)