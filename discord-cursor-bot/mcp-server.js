#!/usr/bin/env node

/**
 * MCP Server para Discord
 * Este servidor actúa como puente entre Discord y Cursor
 * Permite que el asistente de IA de Cursor responda mensajes de Discord
 */

const { Client, GatewayIntentBits } = require('discord.js');
require('dotenv').config();

// Cola de mensajes pendientes de respuesta
const pendingMessages = [];
let currentMessage = null;

// Cliente de Discord
const discord = new Client({
    intents: [
        GatewayIntentBits.Guilds,
        GatewayIntentBits.GuildMessages,
        GatewayIntentBits.MessageContent,
    ]
});

// ============================================
// PROTOCOLO MCP (Model Context Protocol)
// ============================================

// Leer mensajes del stdin (comandos de Cursor)
process.stdin.setEncoding('utf8');
let buffer = '';

process.stdin.on('data', (chunk) => {
    buffer += chunk;
    
    // Procesar mensajes JSON completos
    let newlineIndex;
    while ((newlineIndex = buffer.indexOf('\n')) !== -1) {
        const line = buffer.slice(0, newlineIndex);
        buffer = buffer.slice(newlineIndex + 1);
        
        if (line.trim()) {
            try {
                const message = JSON.parse(line);
                handleMCPMessage(message);
            } catch (error) {
                console.error('Error parsing MCP message:', error);
            }
        }
    }
});

// Enviar mensaje MCP a Cursor
function sendMCPMessage(message) {
    console.log(JSON.stringify(message));
}

// Manejar mensajes MCP desde Cursor
function handleMCPMessage(message) {
    switch (message.method) {
        case 'initialize':
            sendMCPMessage({
                jsonrpc: '2.0',
                id: message.id,
                result: {
                    protocolVersion: '0.1.0',
                    capabilities: {
                        tools: {}
                    },
                    serverInfo: {
                        name: 'discord-mcp-server',
                        version: '1.0.0'
                    }
                }
            });
            break;

        case 'tools/list':
            sendMCPMessage({
                jsonrpc: '2.0',
                id: message.id,
                result: {
                    tools: [
                        {
                            name: 'get_pending_discord_messages',
                            description: 'Obtiene mensajes pendientes de Discord que esperan respuesta',
                            inputSchema: {
                                type: 'object',
                                properties: {}
                            }
                        },
                        {
                            name: 'send_discord_response',
                            description: 'Envía una respuesta a Discord',
                            inputSchema: {
                                type: 'object',
                                properties: {
                                    messageId: {
                                        type: 'string',
                                        description: 'ID del mensaje al que responder'
                                    },
                                    response: {
                                        type: 'string',
                                        description: 'Texto de respuesta a enviar'
                                    }
                                },
                                required: ['messageId', 'response']
                            }
                        }
                    ]
                }
            });
            break;

        case 'tools/call':
            handleToolCall(message);
            break;

        default:
            if (message.id) {
                sendMCPMessage({
                    jsonrpc: '2.0',
                    id: message.id,
                    error: {
                        code: -32601,
                        message: `Method not found: ${message.method}`
                    }
                });
            }
    }
}

// Manejar llamadas a herramientas
async function handleToolCall(message) {
    const { name, arguments: args } = message.params;

    try {
        let result;

        switch (name) {
            case 'get_pending_discord_messages':
                result = {
                    content: [
                        {
                            type: 'text',
                            text: JSON.stringify({
                                pending: pendingMessages.length,
                                messages: pendingMessages.map(msg => ({
                                    id: msg.id,
                                    author: msg.author.tag,
                                    content: msg.content,
                                    channelName: msg.channel.name,
                                    timestamp: msg.createdAt.toISOString()
                                }))
                            }, null, 2)
                        }
                    ]
                };
                break;

            case 'send_discord_response':
                const targetMessage = pendingMessages.find(m => m.id === args.messageId);
                if (targetMessage) {
                    await targetMessage.reply(args.response);
                    // Remover de pendientes
                    const index = pendingMessages.indexOf(targetMessage);
                    if (index > -1) pendingMessages.splice(index, 1);
                    result = {
                        content: [
                            {
                                type: 'text',
                                text: '✅ Respuesta enviada exitosamente'
                            }
                        ]
                    };
                } else {
                    result = {
                        content: [
                            {
                                type: 'text',
                                text: '❌ Mensaje no encontrado'
                            }
                        ],
                        isError: true
                    };
                }
                break;

            default:
                throw new Error(`Unknown tool: ${name}`);
        }

        sendMCPMessage({
            jsonrpc: '2.0',
            id: message.id,
            result
        });

    } catch (error) {
        sendMCPMessage({
            jsonrpc: '2.0',
            id: message.id,
            error: {
                code: -32603,
                message: error.message
            }
        });
    }
}

// ============================================
// EVENTOS DE DISCORD
// ============================================

discord.once('ready', () => {
    console.error(`✅ Discord bot connected as ${discord.user.tag}`);
    console.error(`📡 MCP Server ready`);
});

discord.on('messageCreate', async (message) => {
    // Ignorar bots
    if (message.author.bot) return;

    // Verificar si menciona al bot
    const botMentioned = message.mentions.has(discord.user);
    if (!botMentioned) return;

    // Verificar autorización
    const AUTHORIZED_USERS = process.env.AUTHORIZED_USERS ? process.env.AUTHORIZED_USERS.split(',') : [];
    if (AUTHORIZED_USERS.length > 0 && !AUTHORIZED_USERS.includes(message.author.id)) {
        await message.reply('❌ No estás autorizado para usar este bot.');
        return;
    }

    // Agregar a pendientes
    pendingMessages.push(message);
    console.error(`📨 Nuevo mensaje de ${message.author.tag}: ${message.content}`);

    // Mostrar indicador de escritura
    await message.channel.sendTyping();
});

// ============================================
// INICIAR
// ============================================

const DISCORD_TOKEN = process.env.DISCORD_TOKEN;

if (!DISCORD_TOKEN) {
    console.error('❌ ERROR: DISCORD_TOKEN no configurado');
    process.exit(1);
}

discord.login(DISCORD_TOKEN).catch(error => {
    console.error('❌ Error al conectar:', error);
    process.exit(1);
});

// Manejar señales de terminación
process.on('SIGINT', () => {
    console.error('\n👋 Cerrando servidor MCP...');
    discord.destroy();
    process.exit(0);
});

process.on('SIGTERM', () => {
    discord.destroy();
    process.exit(0);
});