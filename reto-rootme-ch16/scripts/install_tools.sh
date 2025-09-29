#!/usr/bin/env bash
set -euo pipefail

secs="${1:-40}"

run() { /workspace/reto-rootme-ch16/bin/with-timeout.sh "$secs" "$@"; }

if ! command -v sudo >/dev/null 2>&1; then
  echo "sudo no está disponible. Instálalo o ejecuta como root." >&2
  exit 1
fi

export DEBIAN_FRONTEND=noninteractive

run sudo apt-get update -y || true
run sudo apt-get install -y --no-install-recommends \
  curl ca-certificates coreutils grep sed awk python3 \
  netcat-traditional nmap nikto ffuf || true

echo "Herramientas instaladas (si no existían)."
