#!/usr/bin/env bash
set -Eeuo pipefail

# Unzip with password using timeout windows
# Usage: unzip_with_password.sh <zip_file> <password> <dest_dir>

if [[ $# -lt 3 ]]; then
    echo "Usage: $0 <zip_file> <password> <dest_dir>" >&2
    exit 2
fi

zip_file="$1"
password="$2"
dest_dir="$3"

mkdir -p "$dest_dir"

for t in 10s 20s 30s; do
    echo "[unzip] timeout=$t -> unzip -P **** -d $dest_dir $zip_file"
    if timeout --foreground "$t" unzip -o -P "$password" -d "$dest_dir" "$zip_file"; then
        echo "[unzip] Done"
        exit 0
    fi
    echo "[unzip] Timed out at $t, escalating..." >&2
done

echo "[unzip] Failed within time limits"
exit 1

