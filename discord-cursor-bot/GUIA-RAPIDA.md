# 🚀 Guía Rápida - 5 Minutos

## Lo que hace

**YO (el asistente de Cursor)** respondo tus mensajes de Discord. Es como chatear conmigo aquí, pero desde Discord.

## Instalación Express

### 1. Instalar dependencias (30 segundos)
```bash
cd /workspace/discord-cursor-bot
npm install
```

### 2. Obtener Token de Discord (2 minutos)
1. Ve a: https://discord.com/developers/applications
2. Click "New Application" → Dale un nombre
3. Ve a "Bot" → "Add Bot"
4. Copia el **TOKEN** 📋
5. Activa "MESSAGE CONTENT INTENT" ✅

### 3. Configurar .env (30 segundos)
```bash
cp .env.example .env
nano .env
```

Pega:
```env
DISCORD_TOKEN=pega_el_token_aqui
AUTHORIZED_USERS=tu_id_de_discord
WORKSPACE_PATH=/workspace
```

**¿Cómo obtener tu ID de Discord?**
- Discord → Configuración → Avanzado → Activar "Modo Desarrollador"
- Click derecho en tu usuario → "Copiar ID"

### 4. Configurar Cursor (1 minuto)

**Crear archivo de configuración:**

En Linux/Mac:
```bash
mkdir -p ~/.cursor/config
nano ~/.cursor/config/mcp.json
```

En Windows:
```
Crear: %APPDATA%\Cursor\config\mcp.json
```

**Pegar este contenido:**
```json
{
  "mcpServers": {
    "discord": {
      "command": "node",
      "args": ["/workspace/discord-cursor-bot/mcp-server.js"],
      "env": {
        "DISCORD_TOKEN": "pega_tu_token_aqui",
        "AUTHORIZED_USERS": "pega_tu_id_aqui",
        "WORKSPACE_PATH": "/workspace"
      }
    }
  }
}
```

⚠️ **Importante:** Reemplaza los valores con tus datos reales.

### 5. Reiniciar Cursor
- Cierra **completamente** Cursor
- Ábrelo de nuevo

### 6. Invitar bot a tu servidor (1 minuto)
1. Discord Dev Portal → Tu app → OAuth2 → URL Generator
2. Selecciona: `bot` ✅
3. Permisos: "Send Messages", "Read Messages" ✅
4. Copia la URL y ábrela → Selecciona tu servidor

## 🎉 ¡Listo! Prueba

En Discord:
```
@TuBot hola!
```

YO (el asistente de Cursor) responderé 🤖

## Verificación

En Cursor (aquí), pregúntame:
```
¿Tienes acceso a las herramientas de Discord?
```

Si respondo "Sí" y veo `get_pending_discord_messages` → ✅ Funciona

## Ejemplos de uso

```
@Bot ejecuta ls -la
@Bot muéstrame el archivo package.json  
@Bot crea un archivo test.txt con "Hola mundo"
@Bot ¿qué archivos hay en el workspace?
```

O simplemente conversación natural:
```
@Bot explícame cómo funciona este código
@Bot ayúdame a debuggear este error
```

## ⚠️ Problemas comunes

**"El bot no responde"**
- ¿Cursor está abierto? (debe estarlo)
- ¿Reiniciaste Cursor después de configurar mcp.json?
- ¿Mencionaste al bot en Discord? (@Bot)

**"No puedo ver herramientas de Discord"**
- Verifica que mcp.json esté en la ubicación correcta
- Verifica que la sintaxis JSON sea correcta
- Reinicia Cursor

**"No autorizado"**
- Verifica tu ID de Discord en AUTHORIZED_USERS
- Asegúrate de que sean los mismos valores en .env y mcp.json

## 🎯 Diferencia clave

❌ **Otros bots**: Ejecutan comandos tontos
✅ **Este bot**: **YO** (tu asistente de IA) respondo

Es como tener a Cursor en Discord 🚀

---

**¿Necesitas ayuda?** Pregúntame aquí en Cursor.