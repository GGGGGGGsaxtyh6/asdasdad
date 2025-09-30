#!/usr/bin/env bash
set -euo pipefail
BASE="https://websec.fr/level21/index.php"
WRAP="/workspace/websec_level21/scripts/tcurl.sh"
OUT="/workspace/websec_level21/outputs"
USER="bcfghjklopqrstuvwxz2" # 21 chars, no a/d/m/i/n
PASS="QWERTY99"
JAR="$OUT/cookies.txt"
mkdir -p "$OUT"
# Register
$WRAP "$BASE" -c "$JAR" -b "$JAR" -d "username=$USER&password=$PASS&register=Register" -o "$OUT/register.html" -D - > "$OUT/register_headers.txt" || true
# Login to get initial session cookie
$WRAP "$BASE" -c "$JAR" -b "$JAR" -d "username=$USER&password=$PASS&login=Login" -o "$OUT/login.html" -D - > "$OUT/login_headers.txt"
# Extract session cookie from Set-Cookie header
ORIG_SESSION=$(awk -F": " /^Set-Cookie:
