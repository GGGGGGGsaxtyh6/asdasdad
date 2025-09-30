#!/usr/bin/env bash
set -euo pipefail

# Use timeouts to avoid hanging during package operations
TIMEOUT=/usr/bin/timeout
T=40s

echo "[+] Removing workspace files..."
${TIMEOUT} "$T" bash -lc 'rm -rf /workspace/reto_xor50' || true

echo "[+] Purging tools (if installed)..."
if command -v sudo >/dev/null 2>&1; then
  ${TIMEOUT} "$T" sudo bash -lc 'apt-get -y purge nmap nikto ffuf || true' || true
  ${TIMEOUT} "$T" sudo bash -lc 'apt-get -y autoremove --purge || true' || true
  ${TIMEOUT} "$T" sudo bash -lc 'apt-get -y clean || true' || true
fi

echo "[+] Done."

