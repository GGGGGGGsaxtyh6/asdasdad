const { Client, GatewayIntentBits, EmbedBuilder, AttachmentBuilder } = require('discord.js');
const Anthropic = require('@anthropic-ai/sdk');
const { exec } = require('child_process');
const fs = require('fs').promises;
const path = require('path');
require('dotenv').config();

// ============================================
// CONFIGURACIÓN
// ============================================
const DISCORD_TOKEN = process.env.DISCORD_TOKEN;
const ANTHROPIC_API_KEY = process.env.ANTHROPIC_API_KEY;
const AUTHORIZED_USERS = process.env.AUTHORIZED_USERS ? process.env.AUTHORIZED_USERS.split(',') : [];
const CLAUDE_MODEL = process.env.CLAUDE_MODEL || 'claude-sonnet-4-20250514';
const WORKSPACE_PATH = process.env.WORKSPACE_PATH || '/workspace';

// ============================================
// INICIALIZAR CLIENTES
// ============================================
const discord = new Client({
    intents: [
        GatewayIntentBits.Guilds,
        GatewayIntentBits.GuildMessages,
        GatewayIntentBits.MessageContent,
    ]
});

const anthropic = new Anthropic({
    apiKey: ANTHROPIC_API_KEY,
});

// ============================================
// ALMACENAMIENTO DE CONVERSACIONES
// ============================================
const conversations = new Map(); // userId -> array de mensajes

// ============================================
// HERRAMIENTAS DISPONIBLES PARA CLAUDE
// ============================================
const tools = [
    {
        name: "execute_command",
        description: "Ejecuta un comando de terminal en el workspace. Usa esto cuando el usuario pida ejecutar comandos, ver archivos con 'ls', o cualquier operación de terminal.",
        input_schema: {
            type: "object",
            properties: {
                command: {
                    type: "string",
                    description: "El comando a ejecutar (ejemplo: 'ls -la', 'pwd', 'cat archivo.txt')"
                }
            },
            required: ["command"]
        }
    },
    {
        name: "read_file",
        description: "Lee el contenido de un archivo del workspace. Usa esto cuando necesites ver el contenido de un archivo específico.",
        input_schema: {
            type: "object",
            properties: {
                file_path: {
                    type: "string",
                    description: "Ruta del archivo relativa al workspace (ejemplo: 'package.json', 'src/index.js')"
                }
            },
            required: ["file_path"]
        }
    },
    {
        name: "write_file",
        description: "Crea o sobrescribe un archivo en el workspace. Usa esto cuando el usuario pida crear o modificar archivos.",
        input_schema: {
            type: "object",
            properties: {
                file_path: {
                    type: "string",
                    description: "Ruta del archivo relativa al workspace"
                },
                content: {
                    type: "string",
                    description: "Contenido del archivo"
                }
            },
            required: ["file_path", "content"]
        }
    },
    {
        name: "list_directory",
        description: "Lista archivos y directorios en una ruta específica.",
        input_schema: {
            type: "object",
            properties: {
                directory: {
                    type: "string",
                    description: "Ruta del directorio relativa al workspace (vacío para raíz)"
                }
            },
            required: ["directory"]
        }
    },
    {
        name: "search_files",
        description: "Busca archivos por nombre usando un patrón.",
        input_schema: {
            type: "object",
            properties: {
                pattern: {
                    type: "string",
                    description: "Patrón de búsqueda (ejemplo: '*.js', 'test*')"
                }
            },
            required: ["pattern"]
        }
    }
];

// ============================================
// IMPLEMENTACIÓN DE HERRAMIENTAS
// ============================================
async function executeCommand(command) {
    return new Promise((resolve) => {
        exec(command, { cwd: WORKSPACE_PATH, timeout: 30000 }, (error, stdout, stderr) => {
            let result = {
                success: !error,
                stdout: stdout || '',
                stderr: stderr || '',
                error: error ? error.message : null
            };
            resolve(result);
        });
    });
}

async function readFile(filePath) {
    try {
        const fullPath = path.join(WORKSPACE_PATH, filePath);
        const content = await fs.readFile(fullPath, 'utf-8');
        return { success: true, content };
    } catch (error) {
        return { success: false, error: error.message };
    }
}

async function writeFile(filePath, content) {
    try {
        const fullPath = path.join(WORKSPACE_PATH, filePath);
        await fs.mkdir(path.dirname(fullPath), { recursive: true });
        await fs.writeFile(fullPath, content, 'utf-8');
        return { success: true, message: `Archivo ${filePath} creado/actualizado` };
    } catch (error) {
        return { success: false, error: error.message };
    }
}

async function listDirectory(directory) {
    try {
        const fullPath = path.join(WORKSPACE_PATH, directory || '');
        const files = await fs.readdir(fullPath, { withFileTypes: true });
        const fileList = files.map(file => ({
            name: file.name,
            type: file.isDirectory() ? 'directory' : 'file'
        }));
        return { success: true, files: fileList };
    } catch (error) {
        return { success: false, error: error.message };
    }
}

async function searchFiles(pattern) {
    return new Promise((resolve) => {
        exec(`find ${WORKSPACE_PATH} -name "${pattern}" 2>/dev/null | head -50`, (error, stdout) => {
            if (error || !stdout.trim()) {
                resolve({ success: false, message: 'No se encontraron archivos' });
                return;
            }
            const files = stdout.trim().split('\n').map(f => f.replace(WORKSPACE_PATH + '/', ''));
            resolve({ success: true, files });
        });
    });
}

// ============================================
// PROCESADOR DE HERRAMIENTAS
// ============================================
async function processToolCall(toolName, toolInput) {
    switch (toolName) {
        case 'execute_command':
            return await executeCommand(toolInput.command);
        case 'read_file':
            return await readFile(toolInput.file_path);
        case 'write_file':
            return await writeFile(toolInput.file_path, toolInput.content);
        case 'list_directory':
            return await listDirectory(toolInput.directory);
        case 'search_files':
            return await searchFiles(toolInput.pattern);
        default:
            return { error: `Herramienta desconocida: ${toolName}` };
    }
}

// ============================================
// FUNCIÓN PRINCIPAL: CHAT CON CLAUDE
// ============================================
async function chatWithClaude(userId, userMessage) {
    // Obtener historial de conversación
    if (!conversations.has(userId)) {
        conversations.set(userId, []);
    }
    const history = conversations.get(userId);

    // Agregar mensaje del usuario
    history.push({
        role: "user",
        content: userMessage
    });

    // System prompt
    const systemPrompt = `Eres un asistente de IA integrado en Cursor IDE, ahora respondiendo a través de Discord.

CONTEXTO:
- Estás ejecutándose en un servidor con acceso al workspace en: ${WORKSPACE_PATH}
- Puedes ejecutar comandos, leer/escribir archivos, y ayudar con desarrollo
- Responde de forma natural y conversacional en español
- Usa las herramientas disponibles cuando sea necesario

IMPORTANTE:
- Cuando el usuario pida ejecutar algo, usa la herramienta execute_command
- Si necesitas ver archivos, usa read_file o execute_command con 'cat'
- Para crear/editar archivos, usa write_file
- Siempre explica lo que estás haciendo y muestra los resultados
- Sé conciso pero informativo`;

    let response = '';
    let toolCalls = [];

    try {
        // Llamada inicial a Claude
        let apiResponse = await anthropic.messages.create({
            model: CLAUDE_MODEL,
            max_tokens: 4096,
            system: systemPrompt,
            messages: history,
            tools: tools
        });

        // Procesar respuesta y tool calls (loop agentic)
        while (apiResponse.stop_reason === 'tool_use') {
            // Extraer tool calls y texto
            const textContent = apiResponse.content.filter(c => c.type === 'text');
            if (textContent.length > 0) {
                response += textContent.map(c => c.text).join('\n');
            }

            const toolUseBlocks = apiResponse.content.filter(c => c.type === 'tool_use');
            
            // Agregar respuesta de Claude al historial
            history.push({
                role: "assistant",
                content: apiResponse.content
            });

            // Ejecutar herramientas
            const toolResults = [];
            for (const toolUse of toolUseBlocks) {
                console.log(`🔧 Ejecutando herramienta: ${toolUse.name}`, toolUse.input);
                const result = await processToolCall(toolUse.name, toolUse.input);
                
                toolResults.push({
                    type: "tool_result",
                    tool_use_id: toolUse.id,
                    content: JSON.stringify(result, null, 2)
                });

                toolCalls.push({
                    name: toolUse.name,
                    input: toolUse.input,
                    result: result
                });
            }

            // Agregar resultados al historial
            history.push({
                role: "user",
                content: toolResults
            });

            // Continuar conversación con Claude
            apiResponse = await anthropic.messages.create({
                model: CLAUDE_MODEL,
                max_tokens: 4096,
                system: systemPrompt,
                messages: history,
                tools: tools
            });
        }

        // Extraer respuesta final
        const finalText = apiResponse.content.filter(c => c.type === 'text');
        response += finalText.map(c => c.text).join('\n');

        // Agregar respuesta final al historial
        history.push({
            role: "assistant",
            content: response
        });

        // Limitar historial a últimos 20 mensajes
        if (history.length > 20) {
            conversations.set(userId, history.slice(-20));
        }

        return {
            success: true,
            response,
            toolCalls
        };

    } catch (error) {
        console.error('Error en chatWithClaude:', error);
        return {
            success: false,
            error: error.message
        };
    }
}

// ============================================
// UTILIDADES DISCORD
// ============================================
function isAuthorized(userId) {
    if (AUTHORIZED_USERS.length === 0) {
        console.warn('⚠️  No hay usuarios autorizados. El bot acepta comandos de cualquiera.');
        return true;
    }
    return AUTHORIZED_USERS.includes(userId);
}

function splitMessage(text, maxLength = 2000) {
    if (text.length <= maxLength) return [text];
    
    const chunks = [];
    let current = '';
    
    const lines = text.split('\n');
    for (const line of lines) {
        if ((current + line + '\n').length > maxLength) {
            if (current) chunks.push(current);
            current = line + '\n';
        } else {
            current += line + '\n';
        }
    }
    if (current) chunks.push(current);
    
    return chunks.length > 0 ? chunks : [text.substring(0, maxLength)];
}

// ============================================
// EVENTOS DE DISCORD
// ============================================
discord.once('ready', () => {
    console.log(`\n${'='.repeat(50)}`);
    console.log('🤖 BOT DE DISCORD CON CLAUDE AI');
    console.log(`${'='.repeat(50)}`);
    console.log(`✅ Conectado como: ${discord.user.tag}`);
    console.log(`🧠 Modelo Claude: ${CLAUDE_MODEL}`);
    console.log(`📁 Workspace: ${WORKSPACE_PATH}`);
    console.log(`🔑 Usuarios autorizados: ${AUTHORIZED_USERS.length > 0 ? AUTHORIZED_USERS.join(', ') : 'NINGUNO'}`);
    console.log(`${'='.repeat(50)}\n`);
    console.log('💬 Envía un mensaje mencionando al bot o con su nombre para hablar\n');
});

discord.on('messageCreate', async (message) => {
    // Ignorar mensajes de bots
    if (message.author.bot) return;

    // Verificar si el bot fue mencionado o el mensaje empieza con su nombre
    const botMentioned = message.mentions.has(discord.user);
    const botNamePattern = new RegExp(`^${discord.user.username}`, 'i');
    const startsWithBotName = botNamePattern.test(message.content);

    if (!botMentioned && !startsWithBotName) return;

    // Verificar autorización
    if (!isAuthorized(message.author.id)) {
        await message.reply('❌ No tienes autorización para usar este bot.');
        return;
    }

    // Extraer el mensaje (quitar mención o nombre del bot)
    let userMessage = message.content
        .replace(/<@!?\d+>/g, '') // Quitar menciones
        .replace(botNamePattern, '') // Quitar nombre del bot
        .trim();

    if (!userMessage) {
        await message.reply('¡Hola! Soy tu asistente de Cursor en Discord. ¿En qué puedo ayudarte?');
        return;
    }

    // Comandos especiales
    if (userMessage.toLowerCase() === 'reset' || userMessage.toLowerCase() === 'reiniciar') {
        conversations.delete(message.author.id);
        await message.reply('✅ Conversación reiniciada.');
        return;
    }

    // Mostrar indicador de escritura
    await message.channel.sendTyping();

    try {
        console.log(`\n📨 Mensaje de ${message.author.tag}: ${userMessage}`);

        // Procesar con Claude
        const result = await chatWithClaude(message.author.id, userMessage);

        if (!result.success) {
            await message.reply(`❌ Error: ${result.error}`);
            return;
        }

        // Enviar respuesta
        const chunks = splitMessage(result.response);
        for (let i = 0; i < chunks.length; i++) {
            if (i === 0) {
                await message.reply(chunks[i]);
            } else {
                await message.channel.send(chunks[i]);
            }
        }

        console.log(`✅ Respuesta enviada (${chunks.length} mensaje(s))`);

    } catch (error) {
        console.error('Error procesando mensaje:', error);
        await message.reply(`❌ Error interno: ${error.message}`);
    }
});

discord.on('error', error => {
    console.error('❌ Error del cliente de Discord:', error);
});

// ============================================
// INICIAR BOT
// ============================================
if (!DISCORD_TOKEN) {
    console.error('❌ ERROR: DISCORD_TOKEN no configurado en .env');
    process.exit(1);
}

if (!ANTHROPIC_API_KEY) {
    console.error('❌ ERROR: ANTHROPIC_API_KEY no configurado en .env');
    process.exit(1);
}

discord.login(DISCORD_TOKEN).catch(error => {
    console.error('❌ Error al conectar con Discord:', error);
    process.exit(1);
});