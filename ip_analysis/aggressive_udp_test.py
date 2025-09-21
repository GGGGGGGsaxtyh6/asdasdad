#!/usr/bin/env python3
import socket
import time
import threading
import random
import string

def send_udp_data(host, port, data):
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.settimeout(1)
        sock.sendto(data, (host, port))
        sock.close()
        return True
    except Exception as e:
        print(f"Error sending {data.hex()}: {e}")
        return False

def generate_random_data(size):
    return ''.join(random.choices(string.ascii_letters + string.digits, k=size)).encode()

def test_protocol_patterns(host, port):
    patterns = [
        # Common protocol headers
        b"\x16\x03\x01",  # TLS
        b"\x80\x01",      # DNS-like
        b"\x00\x01",      # Common binary pattern
        b"\xFF\xFE",      # BOM
        b"\xFE\xFF",      # Reverse BOM
        b"\x01\x02\x03\x04",  # Sequential
        b"\xDE\xAD\xBE\xEF",  # Magic number
        b"\xCA\xFE\xBA\xBE",  # Another magic
        b"\x00\x00\x00\x00",  # All zeros
        b"\xFF\xFF\xFF\xFF",  # All ones
        
        # Text commands that might trigger responses
        b"HELP\r\n",
        b"INFO\r\n", 
        b"STATUS\r\n",
        b"VERSION\r\n",
        b"PING\r\n",
        b"TEST\r\n",
        b"QUIT\r\n",
        b"EXIT\r\n",
        b"LIST\r\n",
        b"DIR\r\n",
        
        # Crypto-related commands
        b"ENCRYPT\r\n",
        b"DECRYPT\r\n",
        b"KEY\r\n",
        b"CIPHER\r\n",
        b"BASE64\r\n",
        b"HEX\r\n",
        b"MD5\r\n",
        b"SHA256\r\n",
        
        # JSON-like patterns
        b'{"cmd":"help"}\r\n',
        b'{"action":"test"}\r\n',
        b'{"method":"ping"}\r\n',
        
        # Binary length-prefixed messages
        b"\x00\x04test",
        b"\x00\x08password",
        b"\x00\x05hello",
        
        # HTTP-like patterns
        b"GET /\r\n\r\n",
        b"POST /\r\n\r\n",
        b"HEAD /\r\n\r\n",
    ]
    
    print(f"Testing {len(patterns)} protocol patterns...")
    for i, pattern in enumerate(patterns):
        print(f"Pattern {i+1}/{len(patterns)}: {pattern}")
        send_udp_data(host, port, pattern)
        time.sleep(0.1)

def test_random_data(host, port, count=100):
    print(f"Testing {count} random data packets...")
    for i in range(count):
        size = random.randint(1, 64)
        data = generate_random_data(size)
        print(f"Random {i+1}/{count}: {data[:16].hex()}{'...' if len(data) > 16 else ''}")
        send_udp_data(host, port, data)
        time.sleep(0.05)

def test_sequential_data(host, port):
    print("Testing sequential data patterns...")
    # Test different sizes
    for size in [1, 2, 4, 8, 16, 32, 64, 128]:
        data = b"A" * size
        print(f"Size {size}: {data[:16].hex()}{'...' if len(data) > 16 else ''}")
        send_udp_data(host, port, data)
        time.sleep(0.1)
    
    # Test incrementing patterns
    for i in range(256):
        data = bytes([i]) * 4
        print(f"Increment {i}: {data.hex()}")
        send_udp_data(host, port, data)
        time.sleep(0.01)

def flood_test(host, port, count=1000):
    print(f"Flooding with {count} packets...")
    for i in range(count):
        data = generate_random_data(random.randint(1, 32))
        send_udp_data(host, port, data)
        if i % 100 == 0:
            print(f"Flood progress: {i}/{count}")

if __name__ == "__main__":
    host = "5.161.142.77"
    port = 10155
    
    print(f"Aggressive UDP testing against {host}:{port}")
    print("=" * 60)
    
    # Test 1: Protocol patterns
    test_protocol_patterns(host, port)
    print()
    
    # Test 2: Random data
    test_random_data(host, port, 50)
    print()
    
    # Test 3: Sequential data
    test_sequential_data(host, port)
    print()
    
    # Test 4: Flood test
    flood_test(host, port, 200)
    print()
    
    print("Aggressive testing completed!")