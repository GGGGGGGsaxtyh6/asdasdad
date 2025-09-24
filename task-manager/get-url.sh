#!/bin/bash

echo "🔍 Obteniendo URL de ngrok..."

# Esperar un poco para que ngrok se inicie
sleep 3

# Intentar obtener la URL de diferentes maneras
if command -v curl >/dev/null 2>&1; then
    URL=$(curl -s http://localhost:4040/api/tunnels 2>/dev/null | grep -o '"public_url":"[^"]*"' | head -1 | cut -d'"' -f4)
    if [ ! -z "$URL" ]; then
        echo "✅ URL encontrada: $URL"
        echo ""
        echo "🌐 Accede a la aplicación en:"
        echo "   $URL"
        echo ""
        echo "📱 También puedes usar:"
        echo "   Frontend local: http://localhost:5173"
        echo "   Backend API: http://localhost:3001"
        exit 0
    fi
fi

echo "❌ No se pudo obtener la URL de ngrok"
echo "🔧 Verificando servicios..."

# Verificar si los servicios están ejecutándose
if pgrep -f "npm run dev" >/dev/null; then
    echo "✅ Servidores de desarrollo ejecutándose"
else
    echo "❌ Servidores de desarrollo no encontrados"
fi

if pgrep -f "ngrok" >/dev/null; then
    echo "✅ Ngrok ejecutándose"
else
    echo "❌ Ngrok no encontrado"
fi

echo ""
echo "🚀 Para iniciar manualmente:"
echo "   1. Backend: cd backend && npm run dev"
echo "   2. Frontend: cd frontend && npm run dev"
echo "   3. Ngrok: ngrok http 5173"