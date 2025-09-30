#!/usr/bin/env python3
import socket
import time
import sys

def get_server_info():
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        print("[*] Connecting to 94.237.57.211:55233...")
        s.connect(('94.237.57.211', 55233))
        print("[+] Connected!")
        
        # Wait a bit for server to send data
        time.sleep(2)
        
        # Receive all available data
        all_data = b""
        while True:
            try:
                s.settimeout(2)
                chunk = s.recv(4096)
                if not chunk:
                    break
                all_data += chunk
                print(chunk.decode('utf-8', errors='ignore'), end='')
                sys.stdout.flush()
            except socket.timeout:
                break
            except Exception as e:
                print(f"\n[!] Error receiving: {e}")
                break
        
        # If we got nothing, try sending newline to trigger response
        if len(all_data) == 0:
            print("\n[*] No data received, sending newline...")
            s.sendall(b'\n')
            time.sleep(1)
            try:
                data = s.recv(4096)
                print(data.decode('utf-8', errors='ignore'))
            except:
                pass
        
        s.close()
        print("\n[*] Connection closed")
        
    except Exception as e:
        print(f"[-] Error: {e}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    get_server_info()
