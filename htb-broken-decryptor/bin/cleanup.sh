#!/usr/bin/env bash
set -euo pipefail

echo "[+] Removing workspace files..." >&2
rm -rf /workspace/htb-broken-decryptor || true

echo "[+] Purging packages (nmap, netcat-openbsd, megatools, unzip, python3-venv, python3-pip) ..." >&2
if command -v apt >/dev/null 2>&1; then
  sudo apt-get purge -y nmap netcat-openbsd megatools unzip python3-venv python3-pip || true
  sudo apt-get autoremove -y || true
  sudo apt-get clean || true
fi

echo "[+] Cleanup complete." >&2

