# 🔌 Scripts de Conexión a través de Proxies

## ✅ Scripts Creados

### 1. **scan_with_working_proxy.py** ⭐ RECOMENDADO
Conecta a través del proxy funcional y escanea directorios de un sitio web.

**Uso:**
```bash
python3 scan_with_working_proxy.py <target>
```

**Ejemplos:**
```bash
python3 scan_with_working_proxy.py httpbin.org
python3 scan_with_working_proxy.py example.com
python3 scan_with_working_proxy.py testhtml5.vulnweb.com
```

**Características:**
- ✅ Usa proxy verificado que funciona: 51.79.99.237:4601
- ✅ Escanea 20 directorios comunes
- ✅ Muestra códigos HTTP (200, 404, etc.)
- ✅ Lista tamaños de respuesta
- ✅ Identifica tipo de contenido (HTML, JSON, etc.)

---

### 2. **proxy_web_tester.py**
Prueba conexiones HTTP a través de múltiples proxies.

**Uso:**
```bash
python3 proxy_web_tester.py
```

**Características:**
- Prueba los primeros 10 proxies
- Verifica con httpbin.org/ip
- Muestra qué proxies funcionan

---

### 3. **proxy_connector.py**
Obtiene información detallada de conexión de cada proxy.

**Uso:**
```bash
python3 proxy_connector.py
```

**Características:**
- Prueba 3 endpoints diferentes por proxy
- Muestra headers y respuestas
- Identifica capacidades del proxy

---

### 4. **proxy_directory_scanner.py**
Scanner de directorios usando múltiples proxies en paralelo.

**Uso:**
```bash
python3 proxy_directory_scanner.py <target>
```

**Características:**
- Usa 5 proxies en paralelo
- Escaneo rápido con threading
- Agrupa resultados por directorio

---

## 🎯 DEMO EJECUTADA

### Escaneo de httpbin.org con proxy funcional:

```
╔════════════════════════════════════════════════════════════╗
║      🔍 DIRECTORY SCANNER - Usando Proxy Funcional         ║
╚════════════════════════════════════════════════════════════╝

🎯 Target: httpbin.org
🔌 Proxy: 51.79.99.237:4601

RESULTADOS:
✅ httpbin.org/ → 200 (9827 bytes) - HTML/Página web
✅ httpbin.org/robots.txt → 200 (248 bytes)
❌ httpbin.org/admin → 404
❌ httpbin.org/api → 404
... etc
```

---

## 🔥 Proxy Verificado que Funciona

**51.79.99.237:4601**
- ✅ Responde 200 OK
- ✅ No requiere autenticación
- ✅ Permite peticiones GET
- ✅ IP vista: 23.81.209.137

---

## 📋 Cómo Usar

### Opción 1: Escanear un sitio (RECOMENDADO)
```bash
python3 scan_with_working_proxy.py example.com
```

### Opción 2: Probar todos los proxies
```bash
python3 proxy_web_tester.py
```

### Opción 3: Ver información detallada de proxies
```bash
python3 proxy_connector.py
```

---

## 🛠️ Qué Hace Cada Script

### scan_with_working_proxy.py
1. Se conecta al proxy 51.79.99.237:4601
2. Hace peticiones HTTP GET al target
3. Prueba 20 directorios comunes
4. Lista cuáles responden 200 OK
5. Identifica tipo de contenido

### proxy_web_tester.py
1. Carga proxies de IPS_CONECTADAS.txt
2. Prueba cada proxy con httpbin.org/ip
3. Muestra estado HTTP de cada uno
4. Identifica cuáles funcionan

### proxy_connector.py
1. Prueba 3 URLs diferentes por proxy
2. Muestra headers completos
3. Verifica capacidades (GET, POST, etc.)
4. Detecta si requiere autenticación

---

## ⚠️ Notas Importantes

- Los proxies gratuitos pueden cambiar de estado
- Usa estos scripts solo para targets legales
- Algunos proxies bloquean ciertos sitios (ej: Cloudflare)
- El proxy 51.79.99.237:4601 es el más confiable

---

## 📁 Archivos Relacionados

- `IPS_CONECTADAS.txt` - 66 proxies verificados
- `valid_proxies.txt` - 71 proxies originales
- `proxies_group1/2/3.txt` - Proxies divididos en grupos

