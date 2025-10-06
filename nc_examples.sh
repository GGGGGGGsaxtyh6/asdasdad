#!/bin/bash
# Ejemplos de uso con netcat (nc)

# Conectar al proxy con nc:
nc 104.18.18.140 80

# Hacer petición HTTP a través del proxy:
echo -e 'GET http://example.com HTTP/1.0\r\n\r\n' | nc 104.18.18.140 80

# Testear conexión:
nc -zv 104.18.18.140 80

# Proxies disponibles:
# 104.18.18.140:80
# 203.24.102.178:80
# 104.16.1.164:80
# 104.16.131.238:80
# 104.18.22.111:80
# 165.227.104.238:8118
# 170.114.45.31:80
# 156.228.118.60:3128
# 173.208.213.170:15005
# 51.79.99.237:4601
