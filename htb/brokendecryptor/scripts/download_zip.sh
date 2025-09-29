#!/usr/bin/env bash
set -Eeuo pipefail

# Download from Mega.nz using megacmd or megatools, bounded by timeouts
# Usage: download_zip.sh <mega_url> <output_path>

if [[ $# -lt 2 ]]; then
    echo "Usage: $0 <mega_url> <output_path>" >&2
    exit 2
fi

url="$1"
out="$2"
tmp_out="$out.part"
mkdir -p "$(dirname "$out")"

download_with_timeouts() {
    local cmd=("$@")
    for t in 20s 30s 40s; do
        echo "[download] timeout=$t -> ${cmd[*]}"
        if timeout --foreground "$t" "${cmd[@]}"; then
            return 0
        fi
        echo "[download] Timed out at $t, escalating..." >&2
    done
    return 1
}

if command -v mega-get >/dev/null 2>&1; then
    download_with_timeouts mega-get "$url" --path "$tmp_out"
    # mega-get may create a file/directory; normalize to desired path
    if [[ -d "$tmp_out" ]]; then
        # If it created a directory with the file inside, move the first file
        found_file="$(find "$tmp_out" -type f -maxdepth 1 | head -n1 || true)"
        if [[ -n "$found_file" ]]; then mv -f "$found_file" "$out"; fi
        rm -rf "$tmp_out"
    else
        mv -f "$tmp_out" "$out"
    fi
    exit 0
fi

if command -v megadl >/dev/null 2>&1; then
    download_with_timeouts megadl --path "$tmp_out" "$url"
    mv -f "$tmp_out" "$out"
    exit 0
fi

echo "[download] No Mega client available (megacmd or megatools)" >&2
exit 1

