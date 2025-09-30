#!/usr/bin/env bash
set -euo pipefail
ROOT="/workspace/reto_pwnable_xyz"
PKG_FILE="$ROOT/sub_50/installed_packages.txt"
WITH_TIMEOUT="$ROOT/sub_50/scripts/with_timeout.sh"
APT=(sudo apt-get -y)
TIMEOUT_DUR="${1:-35s}"
if [[ -f "$PKG_FILE" ]]; then
	mapfile -t PKGS < "$PKG_FILE"
else
	PKGS=(ffuf nikto nmap netcat-openbsd jq file)
fi
if (( ${#PKGS[@]} > 0 )); then
	"$WITH_TIMEOUT" "$TIMEOUT_DUR" "${APT[@]}" purge "${PKGS[@]}" || true
fi
"$WITH_TIMEOUT" "$TIMEOUT_DUR" "${APT[@]}" autoremove --purge || true
"$WITH_TIMEOUT" "$TIMEOUT_DUR" "${APT[@]}" autoclean || true
"$WITH_TIMEOUT" "$TIMEOUT_DUR" "${APT[@]}" clean || true
rm -rf "$ROOT"
