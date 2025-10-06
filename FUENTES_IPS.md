# 📍 Fuentes de IPs Válidas para Proxies

## 🌐 Fuentes Implementadas en el Scraper

### 1. **Free-Proxy-List.net**
- **URL**: https://free-proxy-list.net/
- **Tipo**: Web scraping
- **Actualización**: Cada 10 minutos
- **Características**: Más de 300 proxies, filtro por país, anonimato
- **Legal**: ✅ Servicio público gratuito

### 2. **SSLProxies.org**
- **URL**: https://www.sslproxies.org/
- **Tipo**: Web scraping
- **Características**: Solo proxies HTTPS/SSL
- **Legal**: ✅ Servicio público gratuito

### 3. **PubProxy API**
- **URL**: http://pubproxy.com/api/proxy
- **Tipo**: API REST
- **Límite**: 50 requests/día (versión gratuita)
- **Características**: Proxy frescos, metadata completa
- **Legal**: ✅ API pública y gratuita

### 4. **GeoNode**
- **URL**: https://proxylist.geonode.com/api/proxy-list
- **Tipo**: API REST
- **Características**: Base de datos grande, muy actualizada
- **Legal**: ✅ API pública

## 🔍 Otras Fuentes Disponibles (No implementadas aún)

### **ProxyScrape**
- **URL**: https://api.proxyscrape.com/v2/
- **API**: Gratuita con límites
- **Comando de ejemplo**:
```bash
curl "https://api.proxyscrape.com/v2/?request=get&protocol=http&timeout=10000&country=all&ssl=all&anonymity=all"
```

### **Proxy-List.download**
- **URL**: https://www.proxy-list.download/
- **Formatos**: TXT, JSON, CSV
- **Categorías**: HTTP, HTTPS, SOCKS4, SOCKS5

### **Spys.one**
- **URL**: https://spys.one/en/
- **Características**: Muy completo, velocidad, anonimato

### **ProxyNova**
- **URL**: https://www.proxynova.com/proxy-server-list/
- **Características**: Lista por país

## 🛠️ Cómo Usar las IPs con Netcat (nc)

### Conexión básica:
```bash
nc <IP> <PUERTO>
```

### Testear si el proxy responde:
```bash
nc -zv <IP> <PUERTO>
```

### Hacer petición HTTP a través del proxy:
```bash
# Método 1: GET simple
echo -e 'GET http://httpbin.org/ip HTTP/1.0\r\n\r\n' | nc <IP> <PUERTO>

# Método 2: Con headers
echo -e 'GET http://ipinfo.io/json HTTP/1.1\r\nHost: ipinfo.io\r\n\r\n' | nc <IP> <PUERTO>
```

### Verificar tu IP real vs IP del proxy:
```bash
# Ver tu IP real
curl ipinfo.io/ip

# Ver IP del proxy
echo -e 'GET http://ipinfo.io/ip HTTP/1.1\r\nHost: ipinfo.io\r\n\r\n' | nc <IP_PROXY> <PUERTO>
```

### Uso con curl a través de proxy:
```bash
# HTTP proxy
curl -x http://<IP>:<PUERTO> http://ipinfo.io/json

# SOCKS proxy
curl --socks5 <IP>:<PUERTO> http://ipinfo.io/json
```

## 📊 Tipos de Proxies

### **HTTP/HTTPS**
- Puerto común: 8080, 3128, 80, 8888
- Uso: Navegación web
- Comando nc: `nc <IP> 8080`

### **SOCKS4/SOCKS5**
- Puerto común: 1080
- Uso: Tráfico más versátil
- Requiere: Cliente SOCKS (no directamente con nc)

### **Transparent vs Elite**
- **Transparent**: Revela tu IP real
- **Anonymous**: Oculta tu IP pero revela que usas proxy
- **Elite**: Oculta tu IP y que usas proxy

## ⚡ Script Rápido para Obtener IPs

### Opción 1: Usar el scraper de Python
```bash
python3 proxy_scraper.py
```

### Opción 2: Curl directo (sin validación)
```bash
# ProxyScrape API
curl "https://api.proxyscrape.com/v2/?request=get&protocol=http&timeout=10000&country=all" > proxies.txt

# Ver las IPs
cat proxies.txt
```

### Opción 3: Usar APIs directamente
```bash
# PubProxy
curl "http://pubproxy.com/api/proxy?limit=5&format=txt" > proxies.txt

# ProxyScrape
curl "https://api.proxyscrape.com/v2/?request=displayproxies&protocol=http&timeout=10000&country=all&ssl=all&anonymity=all" > proxies.txt
```

## 🔒 Consideraciones de Seguridad

### ✅ HACER:
- Usar para pruebas y desarrollo
- Verificar que el proxy funciona antes de usarlo
- Rotar proxies regularmente
- Usar para scraping web ético

### ❌ NO HACER:
- Enviar información sensible (contraseñas, datos personales)
- Confiar en proxies gratuitos para privacidad real
- Usar para actividades ilegales
- Suponer que los proxies son seguros o privados

## 📝 Ejemplo Completo

```bash
# 1. Instalar dependencias
pip install -r requirements.txt

# 2. Ejecutar scraper
python3 proxy_scraper.py

# 3. Obtener primer proxy válido
PROXY=$(head -n 1 valid_proxies.txt)
IP=$(echo $PROXY | cut -d: -f1)
PORT=$(echo $PROXY | cut -d: -f2)

# 4. Testear conexión
nc -zv $IP $PORT

# 5. Usar el proxy
echo -e 'GET http://httpbin.org/ip HTTP/1.0\r\n\r\n' | nc $IP $PORT

# 6. O con curl
curl -x http://$IP:$PORT http://httpbin.org/ip
```

## 🚀 Para Usuarios Avanzados

### Verificar anonimato del proxy:
```bash
# Tu IP real
curl ipinfo.io/json

# A través del proxy
curl -x http://<IP>:<PUERTO> http://httpbin.org/headers
# Busca 'X-Forwarded-For' en la respuesta
```

### Medir velocidad del proxy:
```bash
time curl -x http://<IP>:<PUERTO> http://httpbin.org/ip
```

### Probar múltiples proxies en loop:
```bash
while read proxy; do
  IP=$(echo $proxy | cut -d: -f1)
  PORT=$(echo $proxy | cut -d: -f2)
  echo "Testing $IP:$PORT..."
  timeout 5 nc -zv $IP $PORT && echo "✓ OK" || echo "✗ FAIL"
done < valid_proxies.txt
```

## 📚 Recursos Adicionales

- **Lista de APIs de proxies gratuitas**: https://github.com/clarketm/proxy-list
- **Verificador de proxies online**: https://www.proxy-checker.net/
- **Tutorial de nc**: `man nc`

---

💡 **Tip**: Los proxies gratuitos cambian constantemente. Ejecuta el scraper diariamente para mantener una lista actualizada.
