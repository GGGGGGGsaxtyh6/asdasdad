# Análisis de IP 5.161.142.77:10155

## Resumen Ejecutivo
Se realizó un análisis exhaustivo de la IP 5.161.142.77, enfocándose especialmente en el puerto 10155 mencionado en la tarea. El servidor está alojado en Hetzner (Alemania) con el hostname us2.fsho.st.

## Información del Servidor
- **IP**: 5.161.142.77
- **Hostname**: us2.fsho.st
- **Proveedor**: Hetzner Online GmbH (Alemania)
- **Estado**: Activo y respondiendo
- **Latencia promedio**: ~80ms

## Puertos y Servicios Identificados

### Puertos TCP Abiertos:
- **Puerto 21**: FTP - ProFTPD Server (us2.fsho.st)
- **Puerto 22**: SSH - OpenSSH_9.2p1 Debian-2+deb12u5
- **Puerto 80**: HTTP - nginx/1.26.3 (redirige a HTTPS)
- **Puerto 443**: HTTPS - nginx/1.26.3 con certificado Let's Encrypt válido

### Puertos UDP:
- **Puerto 10155**: ABIERTO/FILTRADO - Servicio desconocido

### Puertos Temporalmente Abiertos:
- **Puerto 10190**: TCP - Estuvo abierto inicialmente pero se cerró durante el análisis

## Análisis del Puerto 10155 (Objetivo Principal)

### Estado del Puerto:
- **Protocolo**: UDP
- **Estado**: Abierto y acepta conexiones
- **Comportamiento**: Acepta datos pero no proporciona respuestas visibles

### Pruebas Realizadas:
1. **Comandos de texto**: help, version, encrypt, decrypt, encode, decode, etc.
2. **Datos binarios**: Patrones hexadecimales, TLS handshake, DNS queries
3. **Protocolos comunes**: HTTP, HTTPS, SSH-like, Telnet-like
4. **Algoritmos de cifrado**: AES, RSA, MD5, SHA256, Base64, Hex, Caesar, ROT13, XOR
5. **Herramientas utilizadas**: netcat, socat, scripts Python personalizados

### Resultado:
- El puerto acepta todas las conexiones UDP
- No se obtuvieron respuestas a ningún tipo de payload enviado
- Posibles explicaciones:
  - Servicio que requiere protocolo específico no identificado
  - Servicio que responde de manera asíncrona
  - Honeypot o servicio de logging
  - Servicio que requiere autenticación previa

## Análisis del Servidor Web

### HTTP (Puerto 80):
- Redirige automáticamente a HTTPS
- nginx/1.26.3

### HTTPS (Puerto 443):
- Certificado SSL válido para us2.fsho.st
- Emitido por Let's Encrypt
- Página por defecto de nginx
- No se encontraron directorios o archivos relacionados con cifrado

### Directorios Probados (Todos 404):
- /admin, /login, /encrypted, /cipher, /crypto, /secure, /private
- /hidden, /secret, /flag, /challenge, /ctf, /key, /decrypt
- /encode, /decode, /base64, /hex, /binary
- robots.txt, sitemap.xml, .htaccess, .env, etc.

## Análisis de Otros Servicios

### FTP (Puerto 21):
- ProFTPD Server
- No permite acceso anónimo
- Requiere credenciales válidas

### SSH (Puerto 22):
- OpenSSH_9.2p1 Debian
- Versión actualizada
- No se intentó fuerza bruta por políticas de seguridad

## Vulnerabilidades y Observaciones

### Observaciones de Seguridad:
1. Servicios actualizados (nginx 1.26.3, OpenSSH 9.2p1)
2. Certificado SSL válido y actual
3. Redirección automática HTTP → HTTPS
4. FTP con autenticación requerida
5. Puerto UDP 10155 con comportamiento anómalo

### Posibles Vectores de Análisis Adicional:
1. **Análisis de tráfico de red**: Capturar paquetes durante comunicación con 10155
2. **Fuzzing avanzado**: Usar herramientas especializadas como boofuzz
3. **Análisis temporal**: Monitorear respuestas en diferentes intervalos
4. **Ingeniería social**: Buscar información sobre el servicio en foros/documentación
5. **Análisis de protocolos propietarios**: El servicio podría usar un protocolo custom

## Conclusiones

El puerto 10155 UDP está definitivamente activo y acepta conexiones, pero no responde a ningún payload estándar probado. Esto sugiere:

1. **Servicio de cifrado personalizado**: Podría requerir un formato específico de mensaje
2. **Servicio asíncrono**: Las respuestas podrían llegar por otro canal
3. **Servicio de logging/monitoreo**: Solo registra actividad sin responder
4. **Protocolo propietario**: Requiere conocimiento específico del formato

### Recomendaciones para Continuar:
1. Análisis de tráfico de red con tcpdump/wireshark
2. Fuzzing con herramientas especializadas
3. Búsqueda de documentación del servicio
4. Análisis de otros puertos dinámicos que puedan abrirse
5. Monitoreo temporal del comportamiento del servicio

## Herramientas Utilizadas
- nmap (escaneo de puertos)
- netcat/nc (conexiones TCP/UDP)
- socat (conexiones avanzadas)
- curl (análisis HTTP/HTTPS)
- openssl (análisis SSL)
- Scripts Python personalizados
- Bash scripts para automatización

---
**Fecha del análisis**: 21 de septiembre de 2025
**Duración del análisis**: ~30 minutos
**Estado**: Análisis inicial completado, se requiere investigación adicional para el puerto 10155