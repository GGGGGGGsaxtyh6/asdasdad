import React, { useEffect, useRef, useState, useCallback } from 'react';
import { Terminal } from '@xterm/xterm';
import { FitAddon } from '@xterm/addon-fit';
import { WebLinksAddon } from '@xterm/addon-web-links';
import '@xterm/xterm/css/xterm.css';
import { io, Socket } from 'socket.io-client';

interface TerminalProps {
  token: string;
  user: any;
  onDisconnect: () => void;
}

interface CommandResult {
  type: 'stdout' | 'stderr' | 'error' | 'exit' | 'info';
  output: string;
  sessionId: string;
  timestamp?: string;
}

const TerminalComponent: React.FC<TerminalProps> = ({ token, user, onDisconnect }) => {
  const terminalRef = useRef<HTMLDivElement>(null);
  const terminalInstance = useRef<Terminal | null>(null);
  const fitAddon = useRef<FitAddon | null>(null);
  const socket = useRef<Socket | null>(null);
  const [currentLine, setCurrentLine] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const [commandHistory, setCommandHistory] = useState<string[]>([]);
  const [historyIndex, setHistoryIndex] = useState(-1);

  const initializeTerminal = useCallback(() => {
    if (!terminalRef.current) return;

    // Crear instancia de terminal con tema personalizado
    const terminal = new Terminal({
      theme: {
        background: '#0d1117',
        foreground: '#c9d1d9',
        cursor: '#58a6ff',
        black: '#484f58',
        red: '#f85149',
        green: '#3fb950',
        yellow: '#d29922',
        blue: '#58a6ff',
        magenta: '#bc8cff',
        cyan: '#39d353',
        white: '#b1bac4',
        brightBlack: '#6e7681',
        brightRed: '#ff7b72',
        brightGreen: '#56d364',
        brightYellow: '#e3b341',
        brightBlue: '#79c0ff',
        brightMagenta: '#d2a8ff',
        brightCyan: '#56d364',
        brightWhite: '#f0f6fc',
      },
      fontFamily: 'Fira Code, Consolas, Monaco, monospace',
      fontSize: 14,
      lineHeight: 1.2,
      cursorBlink: true,
      cursorStyle: 'block',
      scrollback: 1000,
    });

    // Configurar addons
    const fit = new FitAddon();
    const webLinks = new WebLinksAddon();
    
    terminal.loadAddon(fit);
    terminal.loadAddon(webLinks);
    
    terminal.open(terminalRef.current);
    fit.fit();

    // Guardar referencias
    terminalInstance.current = terminal;
    fitAddon.current = fit;

    // Configurar prompt inicial
    const writePrompt = () => {
      terminal.write('\r\n\x1b[1;32m' + user.username + '\x1b[0m@\x1b[1;34mstealer-web\x1b[0m:\x1b[1;33m~\x1b[0m$ ');
    };

    // Escribir mensaje de bienvenida
    terminal.write('\x1b[1;36m');
    terminal.write('╔══════════════════════════════════════════════════════════════╗\r\n');
    terminal.write('║                    🚀 STEALER WEB TERMINAL 🚀                ║\r\n');
    terminal.write('║                                                              ║\r\n');
    terminal.write('║  Terminal educativa para aprendizaje de seguridad ofensiva   ║\r\n');
    terminal.write('║                                                              ║\r\n');
    terminal.write('║  Usuario: ' + user.username.padEnd(51) + ' ║\r\n');
    terminal.write('║  Rol: ' + user.role.padEnd(57) + ' ║\r\n');
    terminal.write('║  Fecha: ' + new Date().toLocaleString().padEnd(51) + ' ║\r\n');
    terminal.write('║                                                              ║\r\n');
    terminal.write('║  ⚠️  Solo comandos seguros para fines educativos            ║\r\n');
    terminal.write('╚══════════════════════════════════════════════════════════════╝\r\n');
    terminal.write('\x1b[0m');

    writePrompt();

    // Manejar entrada de datos
    terminal.onData((data) => {
      const code = data.charCodeAt(0);
      
      // Enter
      if (code === 13) {
        if (currentLine.trim()) {
          setCommandHistory(prev => [...prev, currentLine]);
          setHistoryIndex(-1);
          
          // Enviar comando al servidor
          if (socket.current) {
            socket.current.emit('executeCommand', {
              command: currentLine,
              sessionId: 'default'
            });
          }
          
          setCurrentLine('');
        } else {
          terminal.write('\r\n');
          writePrompt();
        }
      }
      // Backspace
      else if (code === 127) {
        if (currentLine.length > 0) {
          terminal.write('\b \b');
          setCurrentLine(prev => prev.slice(0, -1));
        }
      }
      // Flecha arriba (historial)
      else if (code === 27 && data.charCodeAt(1) === 91 && data.charCodeAt(2) === 65) {
        if (commandHistory.length > 0) {
          const newIndex = historyIndex === -1 ? commandHistory.length - 1 : Math.max(0, historyIndex - 1);
          setHistoryIndex(newIndex);
          
          // Limpiar línea actual
          terminal.write('\r\x1b[K');
          terminal.write('\x1b[1;32m' + user.username + '\x1b[0m@\x1b[1;34mstealer-web\x1b[0m:\x1b[1;33m~\x1b[0m$ ');
          
          const historyCommand = commandHistory[newIndex];
          terminal.write(historyCommand);
          setCurrentLine(historyCommand);
        }
      }
      // Flecha abajo (historial)
      else if (code === 27 && data.charCodeAt(1) === 91 && data.charCodeAt(2) === 66) {
        if (historyIndex !== -1) {
          const newIndex = historyIndex + 1;
          if (newIndex >= commandHistory.length) {
            setHistoryIndex(-1);
            setCurrentLine('');
            terminal.write('\r\x1b[K');
            terminal.write('\x1b[1;32m' + user.username + '\x1b[0m@\x1b[1;34mstealer-web\x1b[0m:\x1b[1;33m~\x1b[0m$ ');
          } else {
            setHistoryIndex(newIndex);
            terminal.write('\r\x1b[K');
            terminal.write('\x1b[1;32m' + user.username + '\x1b[0m@\x1b[1;34mstealer-web\x1b[0m:\x1b[1;33m~\x1b[0m$ ');
            const historyCommand = commandHistory[newIndex];
            terminal.write(historyCommand);
            setCurrentLine(historyCommand);
          }
        }
      }
      // Ctrl+C
      else if (code === 3) {
        terminal.write('^C\r\n');
        writePrompt();
        setCurrentLine('');
      }
      // Caracteres normales
      else if (code >= 32) {
        terminal.write(data);
        setCurrentLine(prev => prev + data);
      }
    });

    // Manejar redimensionamiento
    const handleResize = () => {
      if (fitAddon.current) {
        fitAddon.current.fit();
      }
    };

    window.addEventListener('resize', handleResize);

    return () => {
      window.removeEventListener('resize', handleResize);
      terminal.dispose();
    };
  }, [user, currentLine, commandHistory, historyIndex]);

  useEffect(() => {
    const cleanup = initializeTerminal();
    return cleanup;
  }, [initializeTerminal]);

  useEffect(() => {
    // Conectar socket
    const socketInstance = io('http://localhost:4000', {
      transports: ['websocket'],
      upgrade: true,
      rememberUpgrade: true
    });

    socket.current = socketInstance;

    // Autenticar
    socketInstance.emit('authenticate', token);

    // Manejar eventos del socket
    socketInstance.on('authenticated', (data) => {
      setIsConnected(true);
      console.log('Autenticado:', data);
    });

    socketInstance.on('auth_error', (error) => {
      console.error('Error de autenticación:', error);
      onDisconnect();
    });

    socketInstance.on('commandResult', (result: CommandResult) => {
      if (!terminalInstance.current) return;

      const terminal = terminalInstance.current;
      
      switch (result.type) {
        case 'stdout':
          terminal.write('\x1b[0m' + result.output);
          break;
        case 'stderr':
          terminal.write('\x1b[1;31m' + result.output + '\x1b[0m');
          break;
        case 'error':
          terminal.write('\r\n\x1b[1;31m❌ Error: ' + result.output + '\x1b[0m\r\n');
          break;
        case 'exit':
          terminal.write('\x1b[2m' + result.output + '\x1b[0m\r\n');
          break;
        case 'info':
          terminal.write('\r\n\x1b[1;33mℹ️  ' + result.output + '\x1b[0m\r\n');
          break;
      }

      // Escribir nuevo prompt
      setTimeout(() => {
        terminal.write('\r\n\x1b[1;32m' + user.username + '\x1b[0m@\x1b[1;34mstealer-web\x1b[0m:\x1b[1;33m~\x1b[0m$ ');
      }, 50);
    });

    socketInstance.on('disconnect', () => {
      setIsConnected(false);
      console.log('Desconectado del servidor');
    });

    socketInstance.on('connect', () => {
      setIsConnected(true);
      console.log('Conectado al servidor');
    });

    return () => {
      socketInstance.disconnect();
    };
  }, [token, user, onDisconnect]);

  return (
    <div style={{ height: '100vh', display: 'flex', flexDirection: 'column' }}>
      {/* Status Bar */}
      <div style={{
        background: 'linear-gradient(90deg, #1e3c72 0%, #2a5298 100%)',
        color: 'white',
        padding: '8px 16px',
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'center',
        fontSize: '14px',
        fontWeight: 'bold'
      }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: '16px' }}>
          <span>👤 {user.username}</span>
          <span>🔑 {user.role}</span>
          <span style={{ 
            color: isConnected ? '#4caf50' : '#f44336',
            display: 'flex',
            alignItems: 'center',
            gap: '4px'
          }}>
            {isConnected ? '🟢' : '🔴'} 
            {isConnected ? 'Conectado' : 'Desconectado'}
          </span>
        </div>
        <div>
          🕒 {new Date().toLocaleTimeString()}
        </div>
      </div>

      {/* Terminal */}
      <div
        ref={terminalRef}
        style={{
          flex: 1,
          background: '#0d1117',
          padding: '8px'
        }}
      />
    </div>
  );
};

export default TerminalComponent;