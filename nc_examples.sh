#!/bin/bash
# Ejemplos de uso con netcat (nc)

# Conectar al proxy con nc:
nc 154.194.12.106 80

# Hacer petición HTTP a través del proxy:
echo -e 'GET http://example.com HTTP/1.0\r\n\r\n' | nc 154.194.12.106 80

# Testear conexión:
nc -zv 154.194.12.106 80

# Proxies disponibles:
# 154.194.12.106:80
# 45.80.110.59:80
# 18.188.141.177:28080
# 104.167.25.166:3128
# 104.25.139.89:80
# 142.171.224.165:8080
# 54.245.27.232:9993
# 5.78.67.134:8088
# 107.181.168.145:4145
# 47.251.43.115:33333
