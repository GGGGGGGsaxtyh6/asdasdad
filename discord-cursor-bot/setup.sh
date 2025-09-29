#!/bin/bash

echo "=================================================="
echo "🤖 SETUP - Discord Bot con Cursor AI (MCP)"
echo "=================================================="
echo ""

# Verificar que estamos en el directorio correcto
if [ ! -f "mcp-server.js" ]; then
    echo "❌ Error: Ejecuta este script desde /workspace/discord-cursor-bot"
    exit 1
fi

# Paso 1: Crear .env si no existe
if [ ! -f ".env" ]; then
    echo "📝 Creando archivo .env..."
    cp .env.example .env
    echo "✅ Archivo .env creado"
else
    echo "✅ Archivo .env ya existe"
fi

echo ""
echo "=================================================="
echo "📋 PASOS PARA CONFIGURAR:"
echo "=================================================="
echo ""
echo "1️⃣  CREAR BOT EN DISCORD"
echo "   🔗 https://discord.com/developers/applications"
echo "   - New Application"
echo "   - Bot > Add Bot"
echo "   - Copia el TOKEN"
echo "   - Activa MESSAGE CONTENT INTENT"
echo ""
echo "2️⃣  OBTENER TU ID DE DISCORD"
echo "   - Activa Modo Desarrollador en Discord"
echo "   - Click derecho en tu usuario > Copiar ID"
echo ""
echo "3️⃣  CONFIGURAR .env"
echo "   - DISCORD_TOKEN=tu_token"
echo "   - AUTHORIZED_USERS=tu_id"
echo ""
echo "4️⃣  CONFIGURAR MCP EN CURSOR"
echo "   Ubicación del archivo:"
echo "   - Linux/Mac: ~/.cursor/config/mcp.json"
echo "   - Windows: %APPDATA%\Cursor\config\mcp.json"
echo ""
echo "   Contenido (copia desde mcp-config-example.json):"
echo "   {" 
echo '     "mcpServers": {'
echo '       "discord": {'
echo '         "command": "node",'
echo '         "args": ["/workspace/discord-cursor-bot/mcp-server.js"],'
echo '         "env": {'
echo '           "DISCORD_TOKEN": "tu_token",'
echo '           "AUTHORIZED_USERS": "tu_id"'
echo '         }'
echo '       }'
echo '     }'
echo "   }"
echo ""
echo "5️⃣  REINICIAR CURSOR"
echo "   - Cierra completamente Cursor"
echo "   - Ábrelo de nuevo"
echo ""
echo "6️⃣  INVITAR BOT A TU SERVIDOR"
echo "   - Discord Dev Portal > OAuth2 > URL Generator"
echo "   - Selecciona: bot"
echo "   - Permisos: Send Messages, Read Messages"
echo "   - Usa la URL generada"
echo ""
echo "=================================================="
echo ""

# Preguntar si quiere editar .env ahora
read -p "¿Deseas editar .env ahora? (s/n): " respuesta

if [ "$respuesta" = "s" ] || [ "$respuesta" = "S" ]; then
    ${EDITOR:-nano} .env
    echo ""
    echo "✅ .env configurado"
fi

echo ""
echo "=================================================="
echo "🎯 PRÓXIMOS PASOS:"
echo "=================================================="
echo ""
echo "1. Configura el archivo .env con tus credenciales"
echo "2. Crea el archivo mcp.json en Cursor"
echo "3. Reinicia Cursor"
echo "4. ¡Menciona al bot en Discord!"
echo ""
echo "Para verificar: En Cursor, pregúntame '¿Tienes acceso a Discord?'"
echo ""
echo "=================================================="