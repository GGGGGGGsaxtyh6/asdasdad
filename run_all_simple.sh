#!/bin/bash
# Versión simplificada que muestra salida directamente en la terminal

echo "╔════════════════════════════════════════════════════════════╗"
echo "║        🚀 SISTEMA AUTOMÁTICO DE PROXY TESTING             ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Paso 1: Ejecutar scraper
echo "📡 PASO 1: Obteniendo proxies..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
python3 proxy_scraper.py

# Verificar
if [ ! -f "valid_proxies.txt" ]; then
    echo "❌ ERROR: No se pudieron obtener proxies"
    exit 1
fi

TOTAL=$(wc -l < valid_proxies.txt)
echo ""
echo "✅ Proxies obtenidos: $TOTAL"

if [ "$TOTAL" -eq 0 ]; then
    echo "❌ No hay proxies para probar"
    exit 1
fi

# Paso 2: Dividir
echo ""
echo "📂 PASO 2: Dividiendo en 3 grupos..."
LINES_PER_FILE=$(( ($TOTAL + 2) / 3 ))
split -l $LINES_PER_FILE valid_proxies.txt proxy_batch_

mv proxy_batch_aa proxies_group1.txt 2>/dev/null || touch proxies_group1.txt
mv proxy_batch_ab proxies_group2.txt 2>/dev/null || touch proxies_group2.txt
mv proxy_batch_ac proxies_group3.txt 2>/dev/null || touch proxies_group3.txt
rm -f proxy_batch_* 2>/dev/null

G1=$(wc -l < proxies_group1.txt 2>/dev/null || echo 0)
G2=$(wc -l < proxies_group2.txt 2>/dev/null || echo 0)
G3=$(wc -l < proxies_group3.txt 2>/dev/null || echo 0)

echo "   ✓ Grupo 1: $G1 proxies"
echo "   ✓ Grupo 2: $G2 proxies"
echo "   ✓ Grupo 3: $G3 proxies"

# Paso 3: Probar en paralelo
echo ""
echo "🔌 PASO 3: Probando conexiones (3 terminales en paralelo)..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Ejecutar en paralelo
python3 test_connections.py proxies_group1.txt "1" &
python3 test_connections.py proxies_group2.txt "2" &
python3 test_connections.py proxies_group3.txt "3" &

# Esperar a que terminen
wait

echo ""
echo "╔════════════════════════════════════════════════════════════╗"
echo "║                  ✅ COMPLETADO                             ║"
echo "╚════════════════════════════════════════════════════════════╝"
