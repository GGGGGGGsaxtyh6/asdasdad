#!/usr/bin/env bash
set -euo pipefail

# Cleanup script: removes installed packages and workspace
# NOTE: Will use sudo for package removal when available

echo "[cleanup] Starting cleanup..."

if command -v apt-get >/dev/null 2>&1; then
  echo "[cleanup] Removing packages via apt..."
  sudo timeout 30s apt-get remove --purge -y curl wget ncat netcat-openbsd netcat-traditional nmap ffuf nikto python3-venv python3-pip 2>/dev/null || true
  sudo timeout 30s apt-get autoremove -y 2>/dev/null || true
  sudo timeout 30s apt-get autoclean -y 2>/dev/null || true
fi

echo "[cleanup] Removing workspace directory /workspace/reto_xor_50 ..."
rm -rf /workspace/reto_xor_50 || true

echo "[cleanup] Done."

