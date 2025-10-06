# 🌐 Proxy Scraper - IPs Válidas Gratuitas y Legales

Scraper de proxies gratuitos que obtiene IPs válidas de fuentes públicas y legales, perfecto para usar con `nc` (netcat).

## 🎯 Características

- ✅ **100% Legal**: Solo usa fuentes de proxies gratuitos y públicos
- 🔍 **Validación automática**: Verifica que los proxies estén activos
- 🌍 **Múltiples fuentes**: 
  - Free-Proxy-List.net
  - SSLProxies.org
  - PubProxy API
  - GeoNode API
- ⚡ **Rápido**: Validación paralela de proxies
- 📊 **Múltiples formatos**: TXT, JSON y ejemplos de uso con nc

## 📦 Instalación

```bash
pip install -r requirements.txt
```

## 🚀 Uso

### Ejecutar el scraper:

```bash
python3 proxy_scraper.py
```

El script generará 3 archivos:

1. **`valid_proxies.txt`**: Lista simple de IP:PUERTO
2. **`valid_proxies.json`**: Detalles completos con país, anonimato, etc.
3. **`nc_examples.sh`**: Ejemplos de uso con netcat

## 🔧 Usar con netcat (nc)

### Conectar a un proxy:

```bash
# Testear conexión
nc -zv <IP> <PUERTO>

# Conectar al proxy
nc <IP> <PUERTO>
```

### Hacer petición HTTP a través del proxy:

```bash
echo -e 'GET http://example.com HTTP/1.0\r\n\r\n' | nc <IP> <PUERTO>
```

### Ejemplo real con un proxy del archivo:

```bash
# Leer la primera línea del archivo
PROXY=$(head -n 1 valid_proxies.txt)
IP=$(echo $PROXY | cut -d: -f1)
PORT=$(echo $PROXY | cut -d: -f2)

# Testear
nc -zv $IP $PORT

# Usar
echo -e 'GET http://httpbin.org/ip HTTP/1.0\r\n\r\n' | nc $IP $PORT
```

## 📋 Formato de salida

### valid_proxies.txt:
```
192.168.1.1:8080
10.0.0.1:3128
...
```

### valid_proxies.json:
```json
[
  {
    "ip": "192.168.1.1",
    "port": 8080,
    "country": "US",
    "anonymity": "elite",
    "https": true
  }
]
```

## ⚙️ Configuración

Puedes modificar el timeout en el código:

```python
scraper = ProxyScraper(timeout=3)  # 3 segundos de timeout
```

## 🔒 Legalidad

Todos los proxies provienen de fuentes **públicas y gratuitas**:
- Están diseñados para uso público
- No requieren autenticación
- Son compartidos voluntariamente por sus operadores
- **Úsalos responsablemente**

## ⚠️ Notas importantes

- Los proxies gratuitos pueden ser **lentos e inestables**
- La disponibilidad cambia constantemente
- Ejecuta el scraper regularmente para obtener proxies frescos
- No uses estos proxies para actividades que requieran alta seguridad
- Algunos proxies pueden registrar tu tráfico

## 🛠️ Troubleshooting

### No se encuentran proxies válidos:

```bash
# Ejecuta el scraper varias veces
python3 proxy_scraper.py
```

### Error de conexión:

```bash
# Verifica tu conexión a internet
ping google.com

# Algunos sitios pueden estar bloqueados, el scraper intentará con todas las fuentes
```

## 📝 Licencia

Código libre para uso personal y educativo. Los proxies son proporcionados por servicios de terceros.
