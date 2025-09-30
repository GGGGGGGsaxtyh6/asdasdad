#!/usr/bin/env bash
set -euo pipefail
WS="/workspace/xor_50"
PACKAGES=(netcat-openbsd file binutils)
for pkg in "${PACKAGES[@]}"; do
	sudo bash -lc "timeout 10s apt-get purge -y $pkg || true" | cat
	sudo bash -lc "timeout 10s apt-get autoremove -y || true" | cat
	sudo bash -lc "timeout 10s apt-get autoclean -y || true" | cat
 done
rm -rf "$WS"
echo "Cleanup complete."
