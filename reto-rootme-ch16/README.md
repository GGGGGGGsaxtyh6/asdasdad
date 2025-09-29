Reto Root-Me Web-Client ch16 - workspace

## Estructura

- `bin/`: wrappers con y sin timeout
  - `with-timeout.sh`: ejecuta un comando con `timeout` (usar siempre salvo escaneos)
  - `nc-timeout.sh`: wrapper para `nc` con timeout por defecto 10s
  - `curl-timeout.sh`: wrapper para `curl` con timeout (por defecto 10s)
  - `ffuf.sh`, `nmap.sh`, `nikto.sh`: wrappers de escaneo (sin timeout por política)
- `scripts/`: utilidades listas para usar
  - `install_tools.sh`: instala herramientas requeridas con `sudo` y timeout (máx 40s)
  - `fetch_challenge.sh`: descarga HTML/assets del reto; soporta cookie
  - `nc_connect.sh`: conexión `nc` siempre con timeout
  - `scan_ffuf.sh`, `scan_nmap.sh`, `scan_nikto.sh`: lanzadores de escaneo
- `downloads/`: descargas del reto
- `notes/`: notas del reto

## Requisitos

- Linux con `sudo` y `apt`.
- Red y DNS funcionales.

## Instalación de herramientas

Ejecuta (máx 40s por paso):

```
scripts/install_tools.sh 40
```

## Descarga del reto (con o sin cookie)

Por defecto intentará sin cookie. Si estás autenticado en Root-Me, exporta tu cookie:

```
export COOKIE="PHPSESSID=...; spip_session=..."
TIMEOUT_SECS=20 scripts/fetch_challenge.sh
```

También puedes usar un fichero de cookies (Netscape/`curl -c`):

```
export COOKIE_FILE="/ruta/a/cookies.txt"
scripts/fetch_challenge.sh
```

Los archivos se guardan en `downloads/`.

## Conexiones `nc` con timeout

```
scripts/nc_connect.sh <host> <port> [timeout_secs]
```

## Escaneos

Los escaneos no aplican timeout automáticamente (política del reto):

```
scripts/scan_nmap.sh -sC -sV <objetivo>
scripts/scan_ffuf.sh -w <wordlist> -u http://host/FUZZ
scripts/scan_nikto.sh -h http://host/
```

Nota: No se realizan escaneos por defecto. Solo se han dejado listos los lanzadores.
