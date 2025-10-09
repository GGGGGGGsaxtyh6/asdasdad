#!/bin/bash
# Script para probar exploit masivamente con variaciones

cd /workspace/work

echo "Iniciando intentos masivos del exploit..."
echo "Timestamp: $(date)"
echo ""

attempt=1
success=0

while [ $success -eq 0 ]; do
    echo "[Intento $attempt] $(date +%H:%M:%S)"
    
    # Probar exploit básico
    timeout 30s python3 exploit.py remote 2>&1 | tee -a mass_attempts.log | grep -E "FLAG|flag|pwn{|Resultado"
    
    # Verificar si obtuvimos algo
    if grep -qi "pwn{" mass_attempts.log 2>/dev/null || grep -qi "flag{" mass_attempts.log 2>/dev/null; then
        echo ""
        echo "======================================="
        echo "¡¡¡ FLAG ENCONTRADA !!!"
        echo "======================================="
        tail -20 mass_attempts.log
        success=1
        break
    fi
    
    attempt=$((attempt + 1))
    
    # Esperar entre 30-90 segundos entre intentos
    wait_time=$((30 + RANDOM % 60))
    sleep $wait_time
    
    # Cada 10 intentos, mostrar progreso
    if [ $((attempt % 10)) -eq 0 ]; then
        echo "==> $attempt intentos realizados hasta ahora..."
    fi
done

echo ""
echo "Script terminado después de $attempt intentos"
