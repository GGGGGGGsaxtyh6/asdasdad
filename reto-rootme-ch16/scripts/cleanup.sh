#!/usr/bin/env bash
set -euo pipefail

secs="${1:-40}"
run() { /workspace/reto-rootme-ch16/bin/with-timeout.sh "$secs" "$@"; }

if ! command -v sudo >/dev/null 2>&1; then
  echo "sudo no está disponible. Ejecútalo como root o instala sudo." >&2
  exit 1
fi

echo "Desinstalando paquetes instalados..."
export DEBIAN_FRONTEND=noninteractive
run sudo apt-get remove -y ffuf nikto nmap netcat-traditional || true
run sudo apt-get autoremove -y || true
run sudo apt-get clean || true

echo "Borrando workspace..."
rm -rf /workspace/reto-rootme-ch16 || true

echo "Limpieza completa."
