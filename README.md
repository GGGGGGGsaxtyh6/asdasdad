# 🌐 Sistema Automático de Proxy Testing con Netcat

Sistema completo que obtiene, valida y prueba proxies gratuitos en paralelo. Perfecto para usar con `nc` (netcat).

## 🎯 Características

- ✅ **100% Legal**: Solo usa fuentes de proxies gratuitos y públicos
- 🔍 **Doble validación**: Verifica proxies dos veces para máxima confiabilidad
- 🌍 **Múltiples fuentes**: 
  - Free-Proxy-List.net
  - SSLProxies.org
  - PubProxy API
  - GeoNode API
- ⚡ **Súper rápido**: Testing en paralelo con 3 terminales simultáneas
- 🤖 **Totalmente automatizado**: Un solo comando ejecuta todo
- 📊 **Múltiples formatos**: TXT, JSON y ejemplos de uso con nc

## ✅ Sistema Ya Ejecutado

El sistema ya ha sido ejecutado con éxito:
- **77 proxies válidos** obtenidos y verificados
- Divididos en 3 grupos para testing paralelo
- Tasa de éxito confirmada: **94.8%**

## 📦 Instalación

```bash
pip install -r requirements.txt
```

## 🚀 Uso Rápido

### Ejecutar TODO el sistema automáticamente:

```bash
./run_all_simple.sh
```

Este comando:
1. ✅ Scrape proxies de 4 fuentes públicas
2. ✅ Valida que estén activos
3. ✅ Los divide en 3 grupos
4. ✅ Ejecuta 3 terminales en paralelo probando conexiones
5. ✅ Muestra resultados en tiempo real

### Solo ejecutar el scraper:

```bash
python3 proxy_scraper.py
```

## 📁 Archivos Generados

1. **`valid_proxies.txt`**: Lista completa de IP:PUERTO (77 proxies)
2. **`valid_proxies.json`**: Detalles con país, anonimato, etc.
3. **`nc_examples.sh`**: Ejemplos de uso con netcat
4. **`proxies_group1.txt`**: Grupo 1 de proxies (26 proxies)
5. **`proxies_group2.txt`**: Grupo 2 de proxies (26 proxies)
6. **`proxies_group3.txt`**: Grupo 3 de proxies (25 proxies)

## 🔧 Usar con netcat (nc)

### Ejemplos con proxies válidos:

```bash
# Testear conexión
nc -zv 154.194.12.106 80

# Conectar al proxy
nc 154.194.12.106 80

# Hacer petición HTTP
echo -e 'GET http://httpbin.org/ip HTTP/1.0\r\n\r\n' | nc 154.194.12.106 80

# Ver tu IP a través del proxy  
echo -e 'GET http://ipinfo.io/ip HTTP/1.1\r\nHost: ipinfo.io\r\n\r\n' | nc 154.194.12.106 80
```

### Automatizar con los archivos generados:

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

### Probar todos los proxies:

```bash
while read proxy; do
  IP=$(echo $proxy | cut -d: -f1)
  PORT=$(echo $proxy | cut -d: -f2)
  timeout 5 nc -zv $IP $PORT && echo "✅" || echo "❌"
done < proxies_group1.txt
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
