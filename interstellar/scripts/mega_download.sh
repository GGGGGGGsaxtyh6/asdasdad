#!/usr/bin/env bash
link="$1"
outdir="${2:-/workspace/interstellar/data}"
if [ -z "$link" ]; then echo "Usage: $0 <mega-link> [outdir]"; exit 1; fi
mkdir -p "$outdir"
if command -v megadl >/dev/null 2>&1; then
  cmd=(megadl --path "$outdir" "$link")
elif command -v megatools >/dev/null 2>&1; then
  cmd=(megatools dl --path "$outdir" "$link")
elif command -v mega-get >/dev/null 2>&1; then
  cmd=(mega-get "$link" "$outdir")
else
  echo "No Mega client installed"; exit 1
fi
exec timeout 40s "${cmd[@]}"
