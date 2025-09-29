#!/bin/bash

echo "🤖 Configuración del Bot de Discord para Cursor"
echo "==============================================="
echo ""

# Verificar si existe .env
if [ ! -f .env ]; then
    echo "❌ No se encontró el archivo .env"
    echo "📝 Creando .env desde .env.example..."
    cp .env.example .env
    echo "✅ Archivo .env creado"
fi

echo ""
echo "📋 Pasos para configurar el bot:"
echo ""
echo "1️⃣  Crea un bot en Discord:"
echo "   🔗 https://discord.com/developers/applications"
echo ""
echo "2️⃣  Copia el TOKEN del bot"
echo ""
echo "3️⃣  Obtén tu ID de Discord:"
echo "   - Activa el Modo Desarrollador en Discord"
echo "   - Click derecho en tu usuario > Copiar ID"
echo ""
echo "4️⃣  Edita el archivo .env y configura:"
echo "   - DISCORD_TOKEN=tu_token_aqui"
echo "   - AUTHORIZED_USERS=tu_id_aqui"
echo ""
echo "5️⃣  Invita el bot a tu servidor:"
echo "   - En el Portal de Discord > OAuth2 > URL Generator"
echo "   - Marca: bot + permisos necesarios"
echo ""
echo "6️⃣  Ejecuta: npm start"
echo ""
echo "==============================================="
echo ""
echo "¿Deseas editar el archivo .env ahora? (s/n)"
read -r respuesta

if [ "$respuesta" = "s" ] || [ "$respuesta" = "S" ]; then
    ${EDITOR:-nano} .env
    echo ""
    echo "✅ Configuración guardada"
fi

echo ""
echo "🚀 Para iniciar el bot, ejecuta: npm start"
echo ""