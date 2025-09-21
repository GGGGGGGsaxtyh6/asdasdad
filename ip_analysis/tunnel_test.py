#!/usr/bin/env python3
import socket
import time
import threading

def test_tunnel_protocols(host, port):
    """Test if port 10155 is a tunnel/proxy service"""
    
    # Test 1: SOCKS proxy
    print("Testing SOCKS proxy...")
    socks_handshake = b"\x05\x01\x00"  # SOCKS5, 1 auth method, no auth
    send_udp(host, port, socks_handshake)
    
    # Test 2: HTTP CONNECT method
    print("Testing HTTP CONNECT...")
    connect_request = b"CONNECT 127.0.0.1:22 HTTP/1.1\r\nHost: 127.0.0.1:22\r\n\r\n"
    send_udp(host, port, connect_request)
    
    # Test 3: Custom tunnel protocol
    print("Testing custom tunnel protocol...")
    tunnel_commands = [
        b"TUNNEL 127.0.0.1:22",
        b"PROXY 127.0.0.1:80", 
        b"FORWARD 127.0.0.1:443",
        b"BRIDGE 127.0.0.1:21",
        b"ROUTE 127.0.0.1:8080",
        b"PIPE 127.0.0.1:3306",
        b"LINK 127.0.0.1:5432",
    ]
    
    for cmd in tunnel_commands:
        print(f"  Sending: {cmd}")
        send_udp(host, port, cmd)
        time.sleep(0.5)
    
    # Test 4: Binary tunnel protocols
    print("Testing binary tunnel protocols...")
    binary_protocols = [
        # Length-prefixed target
        b"\x00\x0a127.0.0.1:22",
        b"\x00\x0b127.0.0.1:80",
        b"\x00\x0c127.0.0.1:443",
        # Fixed-length target
        b"\x01" + b"127.0.0.1:22".ljust(15, b"\x00"),
        b"\x02" + b"127.0.0.1:80".ljust(15, b"\x00"),
        # Magic numbers for tunnel protocols
        b"\xDE\xAD\xBE\xEF" + b"127.0.0.1:22",
        b"\xCA\xFE\xBA\xBE" + b"127.0.0.1:80",
        b"\xFE\xED\xFA\xCE" + b"127.0.0.1:443",
    ]
    
    for protocol in binary_protocols:
        print(f"  Sending binary: {protocol[:20].hex()}...")
        send_udp(host, port, protocol)
        time.sleep(0.5)

def send_udp(host, port, data):
    """Send UDP data"""
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        sock.settimeout(1)
        sock.sendto(data, (host, port))
        sock.close()
        return True
    except Exception as e:
        print(f"Error sending data: {e}")
        return False

def test_port_scanning_via_tunnel(host, port):
    """Test if we can scan ports through the tunnel"""
    print("Testing port scanning via tunnel...")
    
    # Try to scan common ports through tunnel
    common_ports = [21, 22, 23, 25, 53, 80, 110, 143, 443, 993, 995, 8080, 8443]
    
    for target_port in common_ports:
        # Try different formats
        formats = [
            f"SCAN 127.0.0.1:{target_port}",
            f"CHECK 127.0.0.1:{target_port}",
            f"TEST 127.0.0.1:{target_port}",
            f"PORT 127.0.0.1:{target_port}",
            # Binary format
            b"\x00\x0b" + f"127.0.0.1:{target_port}".encode(),
            # IP:PORT in binary
            b"\x7f\x00\x00\x01" + target_port.to_bytes(2, 'big'),
        ]
        
        for fmt in formats:
            if isinstance(fmt, str):
                fmt = fmt.encode()
            print(f"  Scanning port {target_port}: {fmt[:20].hex()}...")
            send_udp(host, port, fmt)
            time.sleep(0.2)

def test_service_enumeration_via_tunnel(host, port):
    """Test service enumeration through tunnel"""
    print("Testing service enumeration via tunnel...")
    
    services = [
        "FTP", "SSH", "TELNET", "SMTP", "DNS", "HTTP", "POP3", "IMAP", 
        "HTTPS", "IMAPS", "POP3S", "SSH", "MYSQL", "POSTGRES", "REDIS",
        "MONGODB", "ELASTICSEARCH", "KIBANA", "GRAFANA", "PROMETHEUS"
    ]
    
    for service in services:
        commands = [
            f"INFO {service}",
            f"SERVICE {service}",
            f"QUERY {service}",
            f"STATUS {service}",
            f"CHECK {service}",
        ]
        
        for cmd in commands:
            print(f"  Querying {service}: {cmd}")
            send_udp(host, port, cmd.encode())
            time.sleep(0.3)

if __name__ == "__main__":
    host = "5.161.142.77"
    port = 10155
    
    print(f"Tunnel/Proxy testing against {host}:{port}")
    print("=" * 50)
    
    # Test 1: Tunnel protocols
    test_tunnel_protocols(host, port)
    print()
    
    # Test 2: Port scanning via tunnel
    test_port_scanning_via_tunnel(host, port)
    print()
    
    # Test 3: Service enumeration
    test_service_enumeration_via_tunnel(host, port)
    print()
    
    print("Tunnel testing completed!")