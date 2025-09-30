#!/usr/bin/env bash
target="${1:-94.237.55.43}"
port="${2:-36908}"
out="/workspace/interstellar/scans/nmap_${target//[^0-9.]/_}_${port}.txt"
nmap -Pn -n -sC -sV -p "$port" -oN "$out" "$target" | cat
