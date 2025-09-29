#!/usr/bin/env bash
set -Eeuo pipefail

# Verify SHA-256 of a file
# Usage: verify_sha256.sh <file> <expected_sha256_hex>

if [[ $# -ne 2 ]]; then
    echo "Usage: $0 <file> <expected_sha256_hex>" >&2
    exit 2
fi

file="$1"
expected="${2,,}"

if [[ ! -f "$file" ]]; then
    echo "[sha256] File not found: $file" >&2
    exit 3
fi

actual=$(sha256sum "$file" | awk '{print tolower($1)}')
if [[ "$actual" == "$expected" ]]; then
    echo "[sha256] OK: $actual"
    exit 0
else
    echo "[sha256] MISMATCH: expected=$expected actual=$actual" >&2
    exit 1
fi

