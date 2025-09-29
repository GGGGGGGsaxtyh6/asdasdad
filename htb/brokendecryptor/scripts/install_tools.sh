#!/usr/bin/env bash
set -Eeuo pipefail

# Install required tools using sudo and bounded by timeout attempts
# Tries per command: 20s -> 30s -> 40s

try_with_timeouts() {
    local cmd=("$@")
    for t in 20s 30s 40s; do
        echo "[install] Running with timeout=$t: ${cmd[*]}"
        if timeout --foreground "$t" "${cmd[@]}"; then
            return 0
        fi
        echo "[install] Timed out at $t, escalating..." >&2
    done
    echo "[install] Command failed within 40s window: ${cmd[*]}" >&2
    return 1
}

export DEBIAN_FRONTEND=noninteractive

# Refresh apt cache
try_with_timeouts sudo -E apt-get update -y -o Acquire::Retries=3

# Base tooling
try_with_timeouts sudo -E apt-get install -y --no-install-recommends \
    unzip python3 python3-venv python3-pip nmap ffuf nikto netcat-openbsd jq ca-certificates

# Mega clients: prefer megacmd, fallback to megatools if available
if ! command -v mega-get >/dev/null 2>&1; then
    try_with_timeouts bash -lc 'sudo -E apt-get install -y --no-install-recommends megacmd || sudo -E apt-get install -y --no-install-recommends megatools'
fi

echo "[install] Versions:"
set +e
python3 --version || true
pip3 --version || true
nmap --version | head -n 1 || true
ffuf -version 2>/dev/null | head -n 1 || true
nikto -Version 2>/dev/null | head -n 1 || true
nc -h 2>&1 | head -n 1 || true
if command -v mega-get >/dev/null 2>&1; then echo "megacmd present"; fi
if command -v megadl >/dev/null 2>&1; then echo "megatools present"; fi
set -e

echo "[install] Done"

