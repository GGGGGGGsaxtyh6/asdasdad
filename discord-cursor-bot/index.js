const { Client, GatewayIntentBits, EmbedBuilder } = require('discord.js');
const { exec } = require('child_process');
const fs = require('fs').promises;
const path = require('path');
require('dotenv').config();

// Configuración
const TOKEN = process.env.DISCORD_TOKEN;
const AUTHORIZED_USERS = process.env.AUTHORIZED_USERS ? process.env.AUTHORIZED_USERS.split(',') : [];
const PREFIX = process.env.COMMAND_PREFIX || '!cursor';
const WORKSPACE_PATH = '/workspace';

// Crear cliente de Discord
const client = new Client({
    intents: [
        GatewayIntentBits.Guilds,
        GatewayIntentBits.GuildMessages,
        GatewayIntentBits.MessageContent,
    ]
});

// Función para verificar si el usuario está autorizado
function isAuthorized(userId) {
    if (AUTHORIZED_USERS.length === 0) {
        console.warn('⚠️  ADVERTENCIA: No hay usuarios autorizados configurados. El bot aceptará comandos de cualquiera.');
        return true;
    }
    return AUTHORIZED_USERS.includes(userId);
}

// Función para dividir mensajes largos
function splitMessage(text, maxLength = 2000) {
    const messages = [];
    let current = '';
    
    const lines = text.split('\n');
    for (const line of lines) {
        if ((current + line + '\n').length > maxLength) {
            if (current) messages.push(current);
            current = line + '\n';
        } else {
            current += line + '\n';
        }
    }
    if (current) messages.push(current);
    
    return messages.length > 0 ? messages : [text.substring(0, maxLength)];
}

// Función para ejecutar comandos de terminal
async function executeCommand(command, message) {
    return new Promise((resolve) => {
        exec(command, { cwd: WORKSPACE_PATH, timeout: 30000 }, (error, stdout, stderr) => {
            let output = '';
            
            if (stdout) output += `**Salida:**\n\`\`\`\n${stdout.substring(0, 1800)}\`\`\`\n`;
            if (stderr) output += `**Errores:**\n\`\`\`\n${stderr.substring(0, 1800)}\`\`\`\n`;
            if (error) output += `**Error de ejecución:**\n\`\`\`\n${error.message.substring(0, 500)}\`\`\``;
            
            if (!output) output = '✅ Comando ejecutado sin salida.';
            
            resolve(output);
        });
    });
}

// Función para leer archivos
async function readFile(filePath, message) {
    try {
        const fullPath = path.join(WORKSPACE_PATH, filePath);
        const content = await fs.readFile(fullPath, 'utf-8');
        
        if (content.length === 0) {
            return '📄 El archivo está vacío.';
        }
        
        const preview = content.substring(0, 1800);
        const truncated = content.length > 1800 ? '\n...(truncado)' : '';
        
        return `**Contenido de \`${filePath}\`:**\n\`\`\`\n${preview}${truncated}\`\`\``;
    } catch (error) {
        return `❌ Error al leer archivo: ${error.message}`;
    }
}

// Función para listar directorio
async function listDirectory(dirPath, message) {
    try {
        const fullPath = path.join(WORKSPACE_PATH, dirPath || '');
        const files = await fs.readdir(fullPath, { withFileTypes: true });
        
        if (files.length === 0) {
            return '📁 El directorio está vacío.';
        }
        
        const fileList = files.map(file => {
            const icon = file.isDirectory() ? '📁' : '📄';
            return `${icon} ${file.name}`;
        }).join('\n');
        
        return `**Contenido de \`${dirPath || '/'}\`:**\n\`\`\`\n${fileList.substring(0, 1800)}\`\`\``;
    } catch (error) {
        return `❌ Error al listar directorio: ${error.message}`;
    }
}

// Función para escribir archivos
async function writeFile(filePath, content, message) {
    try {
        const fullPath = path.join(WORKSPACE_PATH, filePath);
        await fs.mkdir(path.dirname(fullPath), { recursive: true });
        await fs.writeFile(fullPath, content, 'utf-8');
        return `✅ Archivo \`${filePath}\` creado/modificado exitosamente.`;
    } catch (error) {
        return `❌ Error al escribir archivo: ${error.message}`;
    }
}

// Función para buscar archivos
async function searchFiles(pattern, message) {
    return new Promise((resolve) => {
        exec(`find ${WORKSPACE_PATH} -name "${pattern}" 2>/dev/null | head -20`, (error, stdout, stderr) => {
            if (error || !stdout.trim()) {
                resolve('🔍 No se encontraron archivos con ese patrón.');
                return;
            }
            
            const files = stdout.trim().split('\n').map(f => f.replace(WORKSPACE_PATH, '')).join('\n');
            resolve(`**Archivos encontrados:**\n\`\`\`\n${files}\`\`\`\n`);
        });
    });
}

// Evento: Bot listo
client.once('ready', () => {
    console.log(`✅ Bot conectado como ${client.user.tag}`);
    console.log(`📁 Workspace: ${WORKSPACE_PATH}`);
    console.log(`🔑 Usuarios autorizados: ${AUTHORIZED_USERS.length > 0 ? AUTHORIZED_USERS.join(', ') : 'NINGUNO (⚠️  MODO ABIERTO)'}`);
    console.log(`⌨️  Prefijo de comandos: ${PREFIX}`);
});

// Evento: Mensaje recibido
client.on('messageCreate', async (message) => {
    // Ignorar mensajes de bots
    if (message.author.bot) return;
    
    // Verificar si el mensaje comienza con el prefijo
    if (!message.content.startsWith(PREFIX)) return;
    
    // Verificar autorización
    if (!isAuthorized(message.author.id)) {
        await message.reply('❌ No tienes autorización para usar este bot.');
        return;
    }
    
    // Parsear comando
    const args = message.content.slice(PREFIX.length).trim().split(/\s+/);
    const command = args.shift().toLowerCase();
    
    try {
        let response = '';
        
        switch (command) {
            case 'help':
            case 'ayuda':
                const embed = new EmbedBuilder()
                    .setColor(0x0099FF)
                    .setTitle('🤖 Bot de Discord - Cursor IDE')
                    .setDescription('Comandos disponibles:')
                    .addFields(
                        { name: `${PREFIX} exec <comando>`, value: 'Ejecuta un comando de terminal', inline: false },
                        { name: `${PREFIX} read <archivo>`, value: 'Lee el contenido de un archivo', inline: false },
                        { name: `${PREFIX} ls [directorio]`, value: 'Lista archivos en un directorio', inline: false },
                        { name: `${PREFIX} write <archivo>`, value: 'Crea/edita un archivo (líneas siguientes = contenido)', inline: false },
                        { name: `${PREFIX} find <patrón>`, value: 'Busca archivos por nombre (ejemplo: *.js)', inline: false },
                        { name: `${PREFIX} pwd`, value: 'Muestra el directorio de trabajo', inline: false },
                        { name: `${PREFIX} help`, value: 'Muestra esta ayuda', inline: false }
                    )
                    .setFooter({ text: `Workspace: ${WORKSPACE_PATH}` });
                
                await message.reply({ embeds: [embed] });
                return;
            
            case 'exec':
            case 'ejecutar':
                if (args.length === 0) {
                    await message.reply('❌ Debes especificar un comando. Ejemplo: `!cursor exec ls -la`');
                    return;
                }
                const cmd = args.join(' ');
                await message.reply(`⏳ Ejecutando: \`${cmd}\``);
                response = await executeCommand(cmd, message);
                break;
            
            case 'read':
            case 'leer':
                if (args.length === 0) {
                    await message.reply('❌ Debes especificar una ruta de archivo. Ejemplo: `!cursor read index.js`');
                    return;
                }
                response = await readFile(args.join(' '), message);
                break;
            
            case 'ls':
            case 'dir':
                response = await listDirectory(args.join(' '), message);
                break;
            
            case 'write':
            case 'escribir':
                if (args.length === 0) {
                    await message.reply('❌ Uso: `!cursor write <archivo>` seguido del contenido en las siguientes líneas.');
                    return;
                }
                const filePath = args[0];
                const contentLines = message.content.split('\n').slice(1);
                const fileContent = contentLines.join('\n');
                
                if (!fileContent.trim()) {
                    await message.reply('❌ No se proporcionó contenido para el archivo.');
                    return;
                }
                
                response = await writeFile(filePath, fileContent, message);
                break;
            
            case 'find':
            case 'buscar':
                if (args.length === 0) {
                    await message.reply('❌ Debes especificar un patrón. Ejemplo: `!cursor find "*.py"`');
                    return;
                }
                response = await searchFiles(args.join(' '), message);
                break;
            
            case 'pwd':
                response = `📁 Directorio de trabajo: \`${WORKSPACE_PATH}\``;
                break;
            
            default:
                response = `❌ Comando desconocido: \`${command}\`\nUsa \`${PREFIX} help\` para ver los comandos disponibles.`;
        }
        
        // Enviar respuesta (dividir si es muy larga)
        const messages = splitMessage(response);
        for (const msg of messages) {
            await message.reply(msg);
        }
        
    } catch (error) {
        console.error('Error procesando comando:', error);
        await message.reply(`❌ Error interno: ${error.message}`);
    }
});

// Manejo de errores
client.on('error', error => {
    console.error('Error del cliente de Discord:', error);
});

process.on('unhandledRejection', error => {
    console.error('Error no manejado:', error);
});

// Iniciar bot
if (!TOKEN) {
    console.error('❌ ERROR: No se encontró DISCORD_TOKEN en las variables de entorno.');
    console.error('Por favor, crea un archivo .env con tu token de Discord.');
    process.exit(1);
}

client.login(TOKEN);