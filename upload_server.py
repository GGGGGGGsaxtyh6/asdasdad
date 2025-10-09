#!/usr/bin/env python3
import http.server, socketserver

class Handler(http.server.SimpleHTTPRequestHandler):
    def do_POST(self):
        length = int(self.headers['Content-Length'])
        data = self.rfile.read(length)
        filename = self.headers.get('X-Filename', 'shell.php')
        with open(filename, 'wb') as f:
            f.write(data)
        print(f'[+] Archivo recibido: {filename} ({len(data)} bytes)')
        self.send_response(200)
        self.end_headers()
        self.wfile.write(b'File uploaded successfully')

PORT = 9000
with socketserver.TCPServer(('0.0.0.0', PORT), Handler) as httpd:
    print(f'[*] Servidor escuchando en 0.0.0.0:{PORT}')
    httpd.serve_forever()
