#!/usr/bin/env bash
set -euo pipefail
# Variables
TARGET_HOST="$1"            # e.g., 94.237.49.23
TARGET_PORTS="-p-"          # all ports
OUTDIR="${2:-/workspace/recon/scans}"  # default output dir

mkdir -p "$OUTDIR"

# Nmap - no timeout (per requerimiento)
# Full TCP scan + service/version + default scripts
nmap -sS -sV -sC $TARGET_PORTS -oA "$OUTDIR/nmap_initial" "$TARGET_HOST"

# Nmap UDP top ports (slow); optional
# nmap -sU --top-ports 200 -oA "$OUTDIR/nmap_udp_top200" "$TARGET_HOST"

# If 80/443 found, do nikto - no timeout
# nikto -h "http://$TARGET_HOST" -o "$OUTDIR/nikto_http.txt"
# nikto -h "https://$TARGET_HOST" -o "$OUTDIR/nikto_https.txt"

# ffuf examples - no timeout
# Fuzz web content
# ffuf -u http://$TARGET_HOST:80/FUZZ -w /workspace/recon/wordlists/common.txt -o "$OUTDIR/ffuf_common_80.json" -of json

# Fuzz vhosts
# ffuf -u http://$TARGET_HOST/ -H "Host: FUZZ.$TARGET_HOST" -w /workspace/recon/wordlists/subdomains.txt -o "$OUTDIR/ffuf_vhosts.json" -of json

# HTTPX quick check
# httpx -silent -title -status-code -ip -tech-detect -content-length -o "$OUTDIR/httpx.txt" < <(printf "%s\n" "$TARGET_HOST")

# Notes template
cat > "$OUTDIR/notes.txt" <<NOTE
Host: $TARGET_HOST
Date: $(date -Is)
Findings:
 - 
Next steps:
 - 
NOTE
