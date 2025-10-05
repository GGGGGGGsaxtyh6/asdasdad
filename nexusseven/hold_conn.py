import socket, time

def main():
    host = '94.237.57.211'
    port = 40271
    s = socket.create_connection((host, port))
    req = (b"GET /index.html HTTP/1.1\r\n"
           b"Host: 94.237.57.211\r\n"
           b"Connection: keep-alive\r\n\r\n")
    s.sendall(req)
    # Drain headers to ensure response started
    s.settimeout(2.0)
    try:
        buf = b""
        while b"\r\n\r\n" not in buf and len(buf) < 16384:
            chunk = s.recv(1024)
            if not chunk:
                break
            buf += chunk
    except Exception:
        pass
    # Keep open so stats dir persists
    time.sleep(600)

if __name__ == '__main__':
    main()
