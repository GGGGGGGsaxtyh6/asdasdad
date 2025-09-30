#!/bin/bash
TARGET="94.237.49.23"
PORT="37326"

echo "=== Test: Enviar comando y esperar más tiempo ==="
(echo -ne "\x04lp\n"; sleep 5) | timeout 10s nc $TARGET $PORT | xxd

echo ""
echo "=== Test: Comando 0x06 (no usado comúnmente) ==="
echo -ne "\x06lp\n" | timeout 3s nc $TARGET $PORT | xxd

echo ""
echo "=== Test: Múltiples comandos en una conexión ==="
(echo -ne "\x04lp\n"; sleep 1; echo -ne "\x03lp\n"; sleep 1) | timeout 5s nc $TARGET $PORT | xxd