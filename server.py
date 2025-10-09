#!/usr/bin/env python3
import http.server
import socketserver
import os
from urllib.parse import parse_qs

PORT = 8000

class UploadHandler(http.server.SimpleHTTPRequestHandler):
    def do_POST(self):
        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length)
        
        # Guardar archivo
        filename = self.headers.get('X-Filename', 'uploaded_file.php')
        with open(filename, 'wb') as f:
            f.write(post_data)
        
        print(f"[+] Archivo recibido: {filename}")
        print(f"[+] Tamaño: {len(post_data)} bytes")
        
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()
        self.wfile.write(b'File uploaded successfully')
    
    def do_GET(self):
        if self.path == '/':
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            html = b'''
            <html><body>
            <h2>Upload Server Running</h2>
            <form action="/" method="post" enctype="multipart/form-data">
                <input type="file" name="file">
                <input type="submit" value="Upload">
            </form>
            </body></html>
            '''
            self.wfile.write(html)
        else:
            super().do_GET()

with socketserver.TCPServer(("0.0.0.0", PORT), UploadHandler) as httpd:
    print(f"[*] Servidor escuchando en puerto {PORT}")
    print(f"[*] URL: http://0.0.0.0:{PORT}")
    httpd.serve_forever()
