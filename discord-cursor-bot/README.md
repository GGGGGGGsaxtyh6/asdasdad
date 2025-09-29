# 🤖 Discord Bot con Cursor AI (MCP)

Este bot permite que **YO (el asistente de IA de Cursor)** responda mensajes de Discord directamente, usando esta sesión de Cursor.

## 🎯 ¿Cómo funciona?

1. **Servidor MCP** escucha mensajes de Discord
2. Cuando llegas un mensaje, Cursor es notificado
3. **YO** (el asistente) proceso el mensaje y respondo
4. La respuesta se envía a Discord

**Es EXACTAMENTE igual que hablar conmigo aquí, pero desde Discord.**

## 📋 Requisitos

- ✅ Node.js instalado
- ✅ Token de Discord Bot
- ✅ Cursor IDE (esta sesión)
- ❌ **NO necesitas API de Anthropic** (uso la sesión actual)

## 🛠️ Instalación

### 1. Instalar dependencias

```bash
cd /workspace/discord-cursor-bot
npm install
```

### 2. Crear Bot en Discord

1. Ve a https://discord.com/developers/applications
2. Click en "New Application"
3. Ve a "Bot" > "Add Bot"
4. Copia el **Token**
5. Activa "MESSAGE CONTENT INTENT"

### 3. Configurar el bot

```bash
cp .env.example .env
nano .env
```

Configura:
```env
DISCORD_TOKEN=tu_token_aqui
AUTHORIZED_USERS=tu_id_de_discord
WORKSPACE_PATH=/workspace
```

Para obtener tu ID de Discord:
- Activa "Modo Desarrollador" en Discord
- Click derecho en tu usuario > Copiar ID

### 4. Configurar MCP en Cursor

Edita (o crea) el archivo de configuración de Cursor:

**En Linux/Mac:**
```bash
nano ~/.cursor/config/mcp.json
```

**En Windows:**
```
%APPDATA%\Cursor\config\mcp.json
```

Añade:
```json
{
  "mcpServers": {
    "discord": {
      "command": "node",
      "args": ["/workspace/discord-cursor-bot/mcp-server.js"],
      "env": {
        "DISCORD_TOKEN": "tu_token_aqui",
        "AUTHORIZED_USERS": "tu_id_aqui"
      }
    }
  }
}
```

### 5. Reiniciar Cursor

Cierra y abre Cursor para que cargue la configuración MCP.

### 6. Invitar bot a tu servidor

1. En Discord Developers > OAuth2 > URL Generator
2. Selecciona: `bot`
3. Permisos: "Send Messages", "Read Messages"
4. Copia la URL y ábrela en tu navegador

## 🚀 Uso

1. **El servidor MCP inicia automáticamente** cuando abres Cursor
2. En Discord, **menciona al bot**: `@TuBot hola`
3. **YO** (el asistente de Cursor) recibiré tu mensaje
4. Te responderé en Discord

## 💬 Ejemplo de conversación

**Tú en Discord:**
```
@MiBot ejecuta ls -la en el workspace
```

**Yo (desde Cursor):**
```
¡Claro! Voy a listar los archivos del workspace:

[ejecuto el comando y muestro resultados]
```

## 🔧 Verificar que funciona

En Cursor, puedes verificar que el servidor MCP está activo:

1. Abre el chat de Cursor (Cmd/Ctrl + L)
2. Escribe: "¿Tienes acceso a Discord?"
3. Si respondo que sí y veo mensajes pendientes, está funcionando

## 🐛 Solución de problemas

### El bot no responde en Discord
- Verifica que Cursor esté abierto
- Revisa la configuración en `mcp.json`
- Asegúrate de mencionar al bot en Discord

### "No puedo acceder a Discord"
- Verifica que `mcp.json` esté correctamente configurado
- Reinicia Cursor
- Revisa que el token de Discord sea válido

### Error de permisos
- Verifica que tu ID esté en `AUTHORIZED_USERS`
- Asegúrate de que el bot tenga permisos en el canal

## 📝 Comandos útiles

En Discord, puedes hablarme como lo haces aquí:

- `@Bot ejecuta [comando]` - Ejecutar comandos
- `@Bot lee el archivo [ruta]` - Leer archivos
- `@Bot crea un archivo [nombre]` - Crear archivos
- `@Bot lista el directorio` - Ver archivos
- O simplemente conversar naturalmente

## 🔒 Seguridad

⚠️ **IMPORTANTE:**
- Solo usuarios autorizados (en `AUTHORIZED_USERS`) pueden usar el bot
- El bot tiene acceso completo al workspace
- No uses en servidores públicos
- Mantén tu token seguro

## 📚 Arquitectura

```
Discord App (tú)
    ↓
Discord API
    ↓
Servidor MCP (mcp-server.js)
    ↓
Cursor AI (yo, el asistente)
    ↓
Ejecutar comandos/leer archivos
    ↓
Respuesta a Discord
```

## ✨ Ventajas

- ✅ Usas la sesión actual de Cursor (no pagas API externa)
- ✅ YO respondo (no un bot tonto)
- ✅ Puedo ejecutar comandos, leer/escribir archivos
- ✅ Conversaciones naturales como aquí
- ✅ Contexto del workspace completo

## 🤝 Contribuciones

Este es un proyecto experimental. Si encuentras problemas o mejoras, ¡son bienvenidas!

---

**Hecho con ❤️ usando Cursor AI y MCP**