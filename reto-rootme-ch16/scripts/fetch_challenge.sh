#!/usr/bin/env bash
set -euo pipefail

BASE_URL="http://challenge01.root-me.org/web-client/ch16/"
OUT_DIR="/workspace/reto-rootme-ch16/downloads"
TIMEOUT_SECS="${TIMEOUT_SECS:-20}"

mkdir -p "$OUT_DIR"

curl_bin="/workspace/reto-rootme-ch16/bin/with-timeout.sh $TIMEOUT_SECS curl -fsS"

hdrs=()
if [[ -n "${COOKIE:-}" ]]; then
  hdrs+=("-H" "Cookie: ${COOKIE}")
fi
cookie_file_args=()
if [[ -n "${COOKIE_FILE:-}" ]]; then
  cookie_file_args=("-b" "$COOKIE_FILE")
fi

pushd "$OUT_DIR" >/dev/null

eval $curl_bin -o ch16.html "${hdrs[@]}" "${cookie_file_args[@]}" "$BASE_URL/ch16.html" | cat || true

if [[ ! -s ch16.html ]]; then
  echo "Descarga fallida o vacía (probable bloqueo por autenticación)." >&2
  popd >/dev/null
  exit 0
fi

grep -Eo '(src|href)="[^"]+"' ch16.html | sed -E 's/^(src|href)="(.*)"$/\2/' | awk 'NF' | sort -u > links.txt || true

while read -r u; do
  [[ -z "$u" ]] && continue
  if [[ "$u" =~ ^https?:// ]]; then
    full="$u"
  else
    full="${BASE_URL%/}/$u"
  fi
  fname="$(basename "$u")"
  eval $curl_bin -O "${hdrs[@]}" "${cookie_file_args[@]}" "$full" || true
done < links.txt

popd >/dev/null
echo "Descarga completa en $OUT_DIR"
