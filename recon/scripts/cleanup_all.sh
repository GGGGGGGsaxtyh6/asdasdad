#!/usr/bin/env bash
set -euo pipefail
# WARNING: This removes installed packages and workspace content
if [[ ${FORCE_CLEAN:-0} -ne 1 ]]; then
  echo "Set FORCE_CLEAN=1 to proceed" >&2
  exit 1
fi
# Remove tools (best-effort)
sudo apt-get purge -y nmap ffuf nikto gobuster megatools apktool default-jre-headless jq ripgrep netcat-openbsd 7zip p7zip-full || true
sudo apt-get autoremove -y || true
sudo apt-get clean || true
# Remove workspace
rm -rf /workspace/recon || true
