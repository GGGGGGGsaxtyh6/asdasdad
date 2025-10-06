#!/bin/bash
# Script rápido para probar los proxies obtenidos

echo "🚀 Probando proxy scraper..."
echo ""

# Ejecutar scraper
python3 proxy_scraper.py

echo ""
echo "============================================"
echo "📝 EJEMPLO DE USO CON NETCAT (nc):"
echo "============================================"
echo ""

# Si se generó el archivo de proxies válidos
if [ -f "valid_proxies.txt" ]; then
    # Leer primer proxy
    PROXY=$(head -n 1 valid_proxies.txt)
    
    if [ ! -z "$PROXY" ]; then
        IP=$(echo $PROXY | cut -d: -f1)
        PORT=$(echo $PROXY | cut -d: -f2)
        
        echo "Proxy encontrado: $IP:$PORT"
        echo ""
        echo "Para conectarte con nc, usa:"
        echo "  nc $IP $PORT"
        echo ""
        echo "Para hacer una petición HTTP:"
        echo "  echo -e 'GET http://httpbin.org/ip HTTP/1.0\\r\\n\\r\\n' | nc $IP $PORT"
        echo ""
        echo "Para testear conectividad:"
        echo "  nc -zv $IP $PORT"
        echo ""
        echo "📋 Ver todos los proxies válidos en: valid_proxies.txt"
    else
        echo "❌ No se encontraron proxies válidos. Intenta ejecutar de nuevo."
    fi
else
    echo "❌ No se generó el archivo de proxies. Verifica errores arriba."
fi
