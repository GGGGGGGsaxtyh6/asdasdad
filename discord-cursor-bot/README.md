# 🤖 Discord Bot para Cursor IDE

Bot de Discord que permite interactuar con tu sesión de Cursor IDE desde Discord. Puedes ejecutar comandos, leer archivos, listar directorios y más.

## 🚀 Características

- ✅ Ejecutar comandos de terminal en el workspace
- 📄 Leer archivos del proyecto
- 📁 Listar directorios
- 🔍 Buscar archivos por patrón
- ✏️ Crear y editar archivos
- 🔒 Sistema de autorización por ID de usuario
- 📊 Manejo automático de mensajes largos

## 📋 Requisitos

- Node.js 16.9.0 o superior
- Una aplicación de Discord Bot con token

## 🛠️ Instalación

1. **Clonar o crear el proyecto:**
   ```bash
   cd /workspace/discord-cursor-bot
   ```

2. **Instalar dependencias:**
   ```bash
   npm install
   ```

3. **Configurar el bot:**
   - Copia `.env.example` a `.env`:
     ```bash
     cp .env.example .env
     ```
   - Edita `.env` y configura:
     - `DISCORD_TOKEN`: Token de tu bot de Discord
     - `AUTHORIZED_USERS`: IDs de usuarios autorizados (separados por comas)
     - `COMMAND_PREFIX`: Prefijo de comandos (por defecto: `!cursor`)

## 🔑 Crear Bot en Discord

1. Ve al [Portal de Desarrolladores de Discord](https://discord.com/developers/applications)
2. Haz clic en "New Application" y dale un nombre
3. Ve a la sección "Bot" en el menú lateral
4. Haz clic en "Add Bot"
5. Copia el token (lo necesitarás para el archivo `.env`)
6. Activa los siguientes "Privileged Gateway Intents":
   - ✅ MESSAGE CONTENT INTENT
   - ✅ SERVER MEMBERS INTENT (opcional)
   - ✅ PRESENCE INTENT (opcional)

## 📲 Invitar el Bot a tu Servidor

1. En el Portal de Desarrolladores, ve a "OAuth2" > "URL Generator"
2. Selecciona los siguientes scopes:
   - ✅ `bot`
3. Selecciona los permisos del bot:
   - ✅ Read Messages/View Channels
   - ✅ Send Messages
   - ✅ Embed Links
   - ✅ Attach Files
4. Copia la URL generada y ábrela en tu navegador
5. Selecciona tu servidor y autoriza el bot

## 🎮 Obtener tu ID de Usuario de Discord

1. En Discord, ve a Configuración > Avanzado
2. Activa el "Modo Desarrollador"
3. Haz clic derecho en tu usuario y selecciona "Copiar ID"
4. Pega este ID en el archivo `.env` en la variable `AUTHORIZED_USERS`

## ▶️ Ejecutar el Bot

```bash
npm start
```

O en modo desarrollo:
```bash
npm run dev
```

## 📖 Comandos Disponibles

| Comando | Descripción | Ejemplo |
|---------|-------------|---------|
| `!cursor help` | Muestra la ayuda | `!cursor help` |
| `!cursor exec <comando>` | Ejecuta un comando de terminal | `!cursor exec ls -la` |
| `!cursor read <archivo>` | Lee un archivo | `!cursor read package.json` |
| `!cursor ls [dir]` | Lista archivos en directorio | `!cursor ls` o `!cursor ls src` |
| `!cursor write <archivo>` | Crea/edita archivo | Ver ejemplo abajo |
| `!cursor find <patrón>` | Busca archivos | `!cursor find "*.js"` |
| `!cursor pwd` | Muestra directorio actual | `!cursor pwd` |

### Ejemplo de `write`:
```
!cursor write test.txt
Este es el contenido
del archivo
en múltiples líneas
```

## 🔒 Seguridad

⚠️ **IMPORTANTE**: Este bot puede ejecutar comandos en tu sistema. 

- Configura siempre los IDs de usuarios autorizados en `.env`
- No compartas tu token de Discord
- Revisa los comandos antes de ejecutarlos
- Considera usar un entorno aislado (contenedor, VM)
- No expongas este bot en servidores públicos

## 🐛 Solución de Problemas

### El bot no responde
- Verifica que el token sea correcto
- Asegúrate de que "MESSAGE CONTENT INTENT" esté activado
- Revisa que tu ID esté en `AUTHORIZED_USERS`

### Error de permisos
- Verifica que el bot tenga permisos para leer/enviar mensajes en el canal
- Revisa los permisos en Discord

### Comandos no se ejecutan
- Verifica que estés usando el prefijo correcto (por defecto `!cursor`)
- Asegúrate de que el bot esté online

## 📝 Licencia

MIT

## 🤝 Contribuciones

Las contribuciones son bienvenidas. Por favor, abre un issue o pull request.

---

**Workspace actual:** `/workspace`

**Prefijo por defecto:** `!cursor`