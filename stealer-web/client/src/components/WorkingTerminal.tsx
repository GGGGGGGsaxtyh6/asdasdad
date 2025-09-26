import React, { useEffect, useRef, useState, useCallback } from 'react';
import { Terminal as XTerminal } from '@xterm/xterm';
import { FitAddon } from '@xterm/addon-fit';
import { WebLinksAddon } from '@xterm/addon-web-links';
import '@xterm/xterm/css/xterm.css';
import { io, Socket } from 'socket.io-client';
import {
  Box,
  Paper,
  Typography,
  IconButton,
  Tooltip,
  Chip,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Switch,
  FormControlLabel,
  Badge,
  Alert,
  Snackbar
} from '@mui/material';
import {
  Terminal as TerminalIcon,
  Settings,
  Fullscreen,
  FullscreenExit,
  Security,
  PlayArrow,
  Stop,
  Refresh,
  Download,
  Lock,
  LockOpen,
  Warning,
  CheckCircle,
  Error,
  Info,
  Notifications
} from '@mui/icons-material';
import { motion } from 'framer-motion';
import { useHotkeys } from 'react-hotkeys-hook';

interface WorkingTerminalProps {
  token: string;
  user: any;
  isFullscreen?: boolean;
  onToggleFullscreen?: () => void;
}

interface CommandResult {
  type: 'stdout' | 'stderr' | 'error' | 'exit' | 'info' | 'warning' | 'success';
  output: string;
  sessionId: string;
  timestamp?: string;
  pid?: number;
  exitCode?: number;
}

const WorkingTerminal: React.FC<WorkingTerminalProps> = ({ 
  token, 
  user, 
  isFullscreen = false,
  onToggleFullscreen
}) => {
  const terminalRef = useRef<HTMLDivElement>(null);
  const terminalInstance = useRef<XTerminal | null>(null);
  const fitAddon = useRef<FitAddon | null>(null);
  const socket = useRef<Socket | null>(null);
  const [currentLine, setCurrentLine] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const [commandHistory, setCommandHistory] = useState<string[]>([]);
  const [historyIndex, setHistoryIndex] = useState(-1);
  const [showSettings, setShowSettings] = useState(false);
  const [isRecording, setIsRecording] = useState(false);
  const [recordedCommands, setRecordedCommands] = useState<string[]>([]);
  const [notifications, setNotifications] = useState<any[]>([]);
  const [showNotifications, setShowNotifications] = useState(false);
  const [isLocked, setIsLocked] = useState(false);
  const [lockPassword, setLockPassword] = useState('');
  const [currentDir, setCurrentDir] = useState('/home/' + user.username);

  // Hotkeys
  useHotkeys('ctrl+shift+s', () => setShowSettings(true));
  useHotkeys('ctrl+shift+f', () => onToggleFullscreen?.());
  useHotkeys('ctrl+shift+r', () => setIsRecording(!isRecording));
  useHotkeys('ctrl+shift+l', () => setIsLocked(true));

  const initializeTerminal = useCallback(() => {
    if (!terminalRef.current) return;

    // Destruir terminal existente
    if (terminalInstance.current) {
      terminalInstance.current.dispose();
    }

    // Crear nueva instancia
    const terminal = new XTerminal({
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

    // Configurar prompt
    const writePrompt = () => {
      const timestamp = new Date().toLocaleTimeString();
      terminal.write('\r\n');
      terminal.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
      terminal.write('\x1b[1;32m' + user.username + '\x1b[0m');
      terminal.write('@');
      terminal.write('\x1b[1;34mstealer-web\x1b[0m');
      terminal.write(':');
      terminal.write('\x1b[1;33m' + currentDir + '\x1b[0m');
      terminal.write('$ ');
    };

    // Mensaje de bienvenida
    const welcomeMessage = () => {
      terminal.write('\x1b[1;36m');
      terminal.write('╔══════════════════════════════════════════════════════════════════════════════╗\r\n');
      terminal.write('║                        🚀 STEALER WEB TERMINAL 🚀                             ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  Professional Security Terminal Interface - Educational Purposes Only        ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  User: ' + user.username.padEnd(67) + ' ║\r\n');
      terminal.write('║  Role: ' + user.role.padEnd(67) + ' ║\r\n');
      terminal.write('║  Date: ' + new Date().toLocaleString().padEnd(66) + ' ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  🔧 Hotkeys: Ctrl+Shift+S (Settings) | Ctrl+Shift+F (Fullscreen)             ║\r\n');
      terminal.write('║  🔒 Security: Ctrl+Shift+L (Lock) | Ctrl+Shift+R (Record)                   ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  ⚠️  All commands are logged for educational purposes                         ║\r\n');
      terminal.write('╚══════════════════════════════════════════════════════════════════════════════╝\r\n');
      terminal.write('\x1b[0m');
    };

    welcomeMessage();
    writePrompt();

    // Manejar entrada de datos
    terminal.onData((data) => {
      if (isLocked) return;

      const code = data.charCodeAt(0);
      
      // Enter
      if (code === 13) {
        if (currentLine.trim()) {
          // Agregar al historial
          const newHistory = [...commandHistory, currentLine];
          setCommandHistory(newHistory);
          setHistoryIndex(-1);

          // Grabar comando si está en modo grabación
          if (isRecording) {
            setRecordedCommands(prev => [...prev, currentLine]);
          }

          // Mostrar comando en terminal
          terminal.write('\r\n');
          terminal.write('\x1b[2m' + currentLine + '\x1b[0m\r\n');

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
          writePrompt();
          
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
            writePrompt();
          } else {
            setHistoryIndex(newIndex);
            terminal.write('\r\x1b[K');
            writePrompt();
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
      // Ctrl+L (limpiar pantalla)
      else if (code === 12) {
        terminal.clear();
        welcomeMessage();
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
  }, [user, currentLine, commandHistory, historyIndex, isLocked, isRecording, currentDir]);

  useEffect(() => {
    const cleanup = initializeTerminal();
    return cleanup;
  }, [initializeTerminal]);

  // Conectar socket
  useEffect(() => {
    const socketInstance = io('https://7c2def2c86ac.ngrok-free.app', {
      transports: ['websocket'],
      upgrade: true,
      rememberUpgrade: true,
      timeout: 20000,
      forceNew: true
    });

    socket.current = socketInstance;

    // Autenticar
    socketInstance.emit('authenticate', token);

    // Manejar eventos del socket
    socketInstance.on('authenticated', (data) => {
      setIsConnected(true);
      addNotification('success', 'Conectado al servidor', 'Autenticación exitosa');
    });

    socketInstance.on('auth_error', (error) => {
      addNotification('error', 'Error de autenticación', error.error);
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
          addNotification('error', 'Error de comando', result.output);
          break;
        case 'warning':
          terminal.write('\r\n\x1b[1;33m⚠️  Warning: ' + result.output + '\x1b[0m\r\n');
          addNotification('warning', 'Advertencia', result.output);
          break;
        case 'success':
          terminal.write('\r\n\x1b[1;32m✅ Success: ' + result.output + '\x1b[0m\r\n');
          addNotification('success', 'Éxito', result.output);
          break;
        case 'exit':
          terminal.write('\x1b[2m' + result.output + '\x1b[0m\r\n');
          break;
        case 'info':
          terminal.write('\r\n\x1b[1;34mℹ️  Info: ' + result.output + '\x1b[0m\r\n');
          break;
      }

      // Escribir nuevo prompt
      setTimeout(() => {
        const timestamp = new Date().toLocaleTimeString();
        terminal.write('\r\n');
        terminal.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
        terminal.write('\x1b[1;32m' + user.username + '\x1b[0m');
        terminal.write('@');
        terminal.write('\x1b[1;34mstealer-web\x1b[0m');
        terminal.write(':');
        terminal.write('\x1b[1;33m' + currentDir + '\x1b[0m');
        terminal.write('$ ');
      }, 50);
    });

    socketInstance.on('disconnect', () => {
      setIsConnected(false);
      addNotification('error', 'Desconectado', 'Conexión perdida con el servidor');
    });

    socketInstance.on('connect', () => {
      setIsConnected(true);
      addNotification('info', 'Conectado', 'Conexión establecida con el servidor');
    });

    return () => {
      socketInstance.disconnect();
    };
  }, [token, user, currentDir]);

  const addNotification = (type: 'success' | 'error' | 'warning' | 'info', title: string, message: string) => {
    const notification = {
      id: Date.now(),
      type,
      title,
      message,
      timestamp: new Date().toISOString()
    };
    setNotifications(prev => [notification, ...prev.slice(0, 9)]);
  };

  const exportCommands = () => {
    const data = {
      user: user.username,
      timestamp: new Date().toISOString(),
      commands: recordedCommands.length > 0 ? recordedCommands : commandHistory
    };
    
    const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `terminal-session-${Date.now()}.json`;
    document.body.appendChild(a);
    a.click();
    document.body.removeChild(a);
    URL.revokeObjectURL(url);
  };

  return (
    <Box sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
      {/* Header de la terminal */}
      <Paper sx={{ 
        p: 1, 
        background: 'linear-gradient(135deg, #1e3c72 0%, #2a5298 100%)',
        color: 'white',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'space-between'
      }}>
        <Box display="flex" alignItems="center" gap={1}>
          <TerminalIcon sx={{ fontSize: 20 }} />
          <Typography variant="h6" fontWeight="bold">
            Working Terminal
          </Typography>
          <Chip 
            icon={isConnected ? <CheckCircle /> : <Error />}
            label={isConnected ? 'Connected' : 'Disconnected'}
            color={isConnected ? 'success' : 'error'}
            size="small"
          />
          <Chip 
            icon={isRecording ? <Stop /> : <PlayArrow />}
            label={isRecording ? 'Recording' : 'Not Recording'}
            color={isRecording ? 'error' : 'default'}
            size="small"
          />
          <Chip 
            icon={isLocked ? <Lock /> : <LockOpen />}
            label={isLocked ? 'Locked' : 'Unlocked'}
            color={isLocked ? 'warning' : 'success'}
            size="small"
          />
        </Box>

        <Box display="flex" alignItems="center" gap={1}>
          <Tooltip title="Export Commands">
            <IconButton onClick={exportCommands} sx={{ color: 'white' }}>
              <Download />
            </IconButton>
          </Tooltip>
          
          <Tooltip title="Settings (Ctrl+Shift+S)">
            <IconButton onClick={() => setShowSettings(true)} sx={{ color: 'white' }}>
              <Settings />
            </IconButton>
          </Tooltip>
          
          <Tooltip title="Fullscreen (Ctrl+Shift+F)">
            <IconButton onClick={onToggleFullscreen} sx={{ color: 'white' }}>
              {isFullscreen ? <FullscreenExit /> : <Fullscreen />}
            </IconButton>
          </Tooltip>
          
          <Tooltip title="Lock Terminal (Ctrl+Shift+L)">
            <IconButton onClick={() => setIsLocked(true)} sx={{ color: 'white' }}>
              <Security />
            </IconButton>
          </Tooltip>
        </Box>
      </Paper>

      {/* Terminal */}
      <Box sx={{ flex: 1, position: 'relative' }}>
        <div
          ref={terminalRef}
          style={{
            height: '100%',
            background: '#0d1117',
            padding: '8px',
            fontFamily: 'Fira Code, Consolas, Monaco, monospace',
            fontSize: '14px',
            lineHeight: 1.2
          }}
        />
        
        {isLocked && (
          <Box
            sx={{
              position: 'absolute',
              top: 0,
              left: 0,
              right: 0,
              bottom: 0,
              background: 'rgba(0,0,0,0.9)',
              display: 'flex',
              flexDirection: 'column',
              alignItems: 'center',
              justifyContent: 'center',
              zIndex: 1000
            }}
          >
            <Lock sx={{ fontSize: 64, color: 'white', mb: 2 }} />
            <Typography variant="h4" color="white" gutterBottom>
              Terminal Locked
            </Typography>
            <TextField
              type="password"
              placeholder="Enter password to unlock"
              value={lockPassword}
              onChange={(e) => setLockPassword(e.target.value)}
              onKeyPress={(e) => {
                if (e.key === 'Enter' && lockPassword === 'unlock') {
                  setIsLocked(false);
                  setLockPassword('');
                }
              }}
              sx={{ mb: 2, width: 300 }}
            />
            <Button 
              variant="contained" 
              onClick={() => {
                if (lockPassword === 'unlock') {
                  setIsLocked(false);
                  setLockPassword('');
                }
              }}
            >
              Unlock Terminal
            </Button>
          </Box>
        )}
      </Box>

      {/* Settings Dialog */}
      <Dialog open={showSettings} onClose={() => setShowSettings(false)} maxWidth="md" fullWidth>
        <DialogTitle>Terminal Settings</DialogTitle>
        <DialogContent>
          <Box sx={{ pt: 2 }}>
            <FormControlLabel
              control={<Switch checked={isRecording} onChange={(e) => setIsRecording(e.target.checked)} />}
              label="Command Recording"
            />
            <Box sx={{ mt: 2 }}>
              <Typography variant="body2" color="text.secondary">
                Current Directory: {currentDir}
              </Typography>
            </Box>
            <Box sx={{ mt: 2 }}>
              <Typography variant="body2" color="text.secondary">
                Commands Executed: {commandHistory.length}
              </Typography>
            </Box>
            {isRecording && (
              <Box sx={{ mt: 2 }}>
                <Typography variant="body2" color="text.secondary">
                  Commands Recorded: {recordedCommands.length}
                </Typography>
              </Box>
            )}
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowSettings(false)}>Close</Button>
          <Button variant="contained" onClick={() => setShowSettings(false)}>Save</Button>
        </DialogActions>
      </Dialog>

      {/* Notifications */}
      <Snackbar
        open={showNotifications}
        autoHideDuration={6000}
        onClose={() => setShowNotifications(false)}
        anchorOrigin={{ vertical: 'top', horizontal: 'right' }}
      >
        <Box>
          {notifications.slice(0, 3).map((notification) => (
            <Alert 
              key={notification.id} 
              severity={notification.type as any}
              sx={{ mb: 1 }}
            >
              <Typography variant="subtitle2">{notification.title}</Typography>
              <Typography variant="body2">{notification.message}</Typography>
            </Alert>
          ))}
        </Box>
      </Snackbar>
    </Box>
  );
};

export default WorkingTerminal;