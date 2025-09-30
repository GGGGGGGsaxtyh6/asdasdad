#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Captura completa de comando 0x04lp con timeout largo ==="
printf "\x04lp\n" | timeout 10s nc -v $TARGET $PORT 2>&1 | tee /tmp/lpd_output.txt
echo ""
echo "=== Contenido en hexdump ==="
cat /tmp/lpd_output.txt | xxd
echo ""
echo "=== Tamaño de respuesta ==="
wc -c /tmp/lpd_output.txt