#!/usr/bin/env bash
base=${1:-http://94.237.55.43:36908}
out=/workspace/interstellar/scans/ffuf_dirs.txt
wordlist=${2:-/usr/share/seclists/Discovery/Web-Content/common.txt}
if [ ! -f "$wordlist" ]; then echo "No wordlist at $wordlist"; exit 0; fi
ffuf -w "$wordlist" -u "$base/FUZZ" -o "$out" -of md -mc all -sf | cat
