#!/usr/bin/env bash
set -euo pipefail

if [ "$#" -lt 3 ]; then
    echo "Usage: $0 <image> <passlist> <outdir> [timeout_seconds]" 1>&2
    exit 2
fi

image="$1"
passlist="$2"
outdir="$3"
timeout_seconds="${4:-5}"

mkdir -p "$outdir"
while IFS= read -r password; do
    [ -z "$password" ] && continue
    outfile="$outdir/extract_$(echo -n "$password" | tr ' ' '_' | tr -cd '[:alnum:]_').bin"
    rm -f "$outfile" || true
    if timeout "${timeout_seconds}s" steghide extract -sf "$image" -p "$password" -xf "$outfile" -f >/dev/null 2>&1; then
        echo "FOUND:$password:$outfile"
        exit 0
    fi
done < "$passlist"

echo "NOT_FOUND"
exit 1

