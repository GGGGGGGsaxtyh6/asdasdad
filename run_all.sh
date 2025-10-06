#!/bin/bash

echo "╔════════════════════════════════════════════════════════════╗"
echo "║        🚀 SISTEMA AUTOMÁTICO DE PROXY TESTING             ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

# Paso 1: Ejecutar scraper
echo "📡 PASO 1: Obteniendo proxies de fuentes públicas..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
python3 proxy_scraper.py

# Verificar si se generó el archivo
if [ ! -f "valid_proxies.txt" ]; then
    echo ""
    echo "❌ ERROR: No se pudieron obtener proxies válidos"
    echo "   Intenta ejecutar de nuevo o verifica tu conexión"
    exit 1
fi

# Contar proxies
TOTAL=$(wc -l < valid_proxies.txt)
echo ""
echo "✅ Se obtuvieron $TOTAL proxies válidos"

if [ "$TOTAL" -eq 0 ]; then
    echo "❌ No hay proxies para probar"
    exit 1
fi

# Paso 2: Dividir en 3 archivos
echo ""
echo "📂 PASO 2: Dividiendo proxies en 3 grupos..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"

# Calcular líneas por archivo
LINES_PER_FILE=$(( ($TOTAL + 2) / 3 ))

# Dividir el archivo
split -l $LINES_PER_FILE valid_proxies.txt proxy_batch_

# Renombrar archivos
mv proxy_batch_aa proxies_group1.txt 2>/dev/null || touch proxies_group1.txt
mv proxy_batch_ab proxies_group2.txt 2>/dev/null || touch proxies_group2.txt
mv proxy_batch_ac proxies_group3.txt 2>/dev/null || touch proxies_group3.txt

# Limpiar archivos extra si existen
rm -f proxy_batch_* 2>/dev/null

# Contar líneas en cada archivo
G1=$(wc -l < proxies_group1.txt 2>/dev/null || echo 0)
G2=$(wc -l < proxies_group2.txt 2>/dev/null || echo 0)
G3=$(wc -l < proxies_group3.txt 2>/dev/null || echo 0)

echo "   Grupo 1: $G1 proxies → proxies_group1.txt"
echo "   Grupo 2: $G2 proxies → proxies_group2.txt"
echo "   Grupo 3: $G3 proxies → proxies_group3.txt"

# Paso 3: Ejecutar pruebas en paralelo
echo ""
echo "🔌 PASO 3: Probando conexiones en paralelo (3 terminales)..."
echo "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Crear directorio para logs
mkdir -p logs

# Ejecutar en paralelo con & (background)
python3 test_connections.py proxies_group1.txt "1" > logs/terminal1.log 2>&1 &
PID1=$!

python3 test_connections.py proxies_group2.txt "2" > logs/terminal2.log 2>&1 &
PID2=$!

python3 test_connections.py proxies_group3.txt "3" > logs/terminal3.log 2>&1 &
PID3=$!

echo "🔄 Procesos lanzados:"
echo "   Terminal 1 (PID: $PID1) → probando grupo 1"
echo "   Terminal 2 (PID: $PID2) → probando grupo 2"
echo "   Terminal 3 (PID: $PID3) → probando grupo 3"
echo ""
echo "⏳ Esperando resultados (esto puede tomar varios minutos)..."
echo ""

# Mostrar salida en tiempo real
tail -f logs/terminal1.log logs/terminal2.log logs/terminal3.log &
TAIL_PID=$!

# Esperar a que terminen todos los procesos
wait $PID1
wait $PID2
wait $PID3

# Matar el tail
kill $TAIL_PID 2>/dev/null

echo ""
echo "╔════════════════════════════════════════════════════════════╗"
echo "║                  ✅ PROCESO COMPLETADO                     ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""
echo "📋 Los resultados completos están en:"
echo "   • logs/terminal1.log"
echo "   • logs/terminal2.log"
echo "   • logs/terminal3.log"
echo ""
echo "📊 Ver resumen final:"
cat logs/terminal1.log logs/terminal2.log logs/terminal3.log | grep -A 5 "RESUMEN TERMINAL"
