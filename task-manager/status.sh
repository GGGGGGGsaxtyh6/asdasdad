#!/bin/bash

echo "🔍 Estado de Task Manager"
echo "========================"

# Verificar backend
if curl -s http://localhost:3001/health >/dev/null 2>&1; then
    echo "✅ Backend: http://localhost:3001"
else
    echo "❌ Backend: No disponible"
fi

# Verificar frontend
if curl -s http://localhost:5173 >/dev/null 2>&1; then
    echo "✅ Frontend: http://localhost:5173"
else
    echo "❌ Frontend: No disponible"
fi

# Verificar ngrok
if curl -s http://localhost:4040/api/tunnels >/dev/null 2>&1; then
    echo "✅ Ngrok: Ejecutándose"
    # Intentar obtener URL
    URL=$(curl -s http://localhost:4040/api/tunnels 2>/dev/null | grep -o '"public_url":"[^"]*"' | head -1 | cut -d'"' -f4)
    if [ ! -z "$URL" ]; then
        echo "🌐 URL Pública: $URL"
    else
        echo "⚠️  URL pública no disponible"
    fi
else
    echo "❌ Ngrok: No disponible"
fi

echo ""
echo "📱 Para acceder:"
echo "   - Local: http://localhost:5173"
echo "   - Público: Ver URL arriba"