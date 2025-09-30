#!/usr/bin/env bash
base=${1:-http://94.237.55.43:36908}
out=/workspace/interstellar/scans/nikto.txt
nikto -host "$base" -ask no -output "$out" | cat
