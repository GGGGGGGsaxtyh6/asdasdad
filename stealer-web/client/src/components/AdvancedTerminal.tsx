import React, { useEffect, useRef, useState, useCallback } from 'react';
import { Terminal as XTerminal } from '@xterm/xterm';
import { FitAddon } from '@xterm/addon-fit';
import { WebLinksAddon } from '@xterm/addon-web-links';
import '@xterm/xterm/css/xterm.css';
import { io, Socket } from 'socket.io-client';
import {
  Box,
  Paper,
  IconButton,
  Tooltip,
  Chip,
  Button,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  TextField,
  Select,
  MenuItem,
  FormControl,
  InputLabel,
  Switch,
  FormControlLabel,
  Slider,
  Typography,
  Divider,
  List,
  ListItem,
  ListItemText,
  ListItemIcon,
  Badge,
  Alert,
  Snackbar
} from '@mui/material';
import {
  PlayArrow,
  Stop,
  Pause,
  Refresh,
  Settings,
  Fullscreen,
  FullscreenExit,
  Close,
  Add,
  Remove,
  Save,
  Download,
  Upload,
  Code,
  BugReport,
  Security,
  Timeline,
  History,
  Terminal as TerminalIcon,
  Clear,
  Keyboard,
  Mouse,
  TouchApp,
  Visibility,
  VisibilityOff,
  Lock,
  LockOpen,
  VpnKey,
  Shield,
  Warning,
  CheckCircle,
  Error,
  Info,
  Notifications,
  FilterList,
  Search,
  Sort,
  MoreVert,
  ExpandMore,
  ExpandLess,
  KeyboardArrowUp,
  KeyboardArrowDown,
  ContentCopy,
  ContentPaste,
  Undo,
  Redo,
  FindInPage,
  FormatColorFill,
  Palette,
  Brightness4,
  Brightness7,
  Contrast,
  ZoomIn,
  ZoomOut,
  AspectRatio,
  Straighten,
  Tune,
  Speed,
  Memory,
  Storage,
  NetworkCheck,
  Computer,
  DeveloperMode,
  Build,
  Extension,
  Api,
  Webhook,
  CloudSync,
  CloudDone,
  CloudOff,
  Sync,
  SyncProblem,
  Wifi,
  WifiOff,
  Router,
  Lan,
  Hub,
  DeviceHub,
  Power,
  BatteryFull,
  BatteryStd,
  BatteryAlert,
  Cloud,
  CloudDownload,
  CloudUpload
} from '@mui/icons-material';
import { motion, AnimatePresence } from 'framer-motion';
import { useHotkeys } from 'react-hotkeys-hook';

interface AdvancedTerminalProps {
  token: string;
  user: any;
  isFullscreen?: boolean;
  onToggleFullscreen?: () => void;
  sessionId?: string;
  onSessionChange?: (sessionId: string) => void;
}

interface CommandResult {
  type: 'stdout' | 'stderr' | 'error' | 'exit' | 'info' | 'warning' | 'success';
  output: string;
  sessionId: string;
  timestamp?: string;
  pid?: number;
  exitCode?: number;
}

interface TerminalSession {
  id: string;
  name: string;
  isActive: boolean;
  commandCount: number;
  lastCommand: string;
  createdAt: string;
  workingDirectory: string;
  environment: string;
}

interface TerminalSettings {
  fontSize: number;
  fontFamily: string;
  theme: string;
  cursorBlink: boolean;
  cursorStyle: 'block' | 'underline' | 'bar';
  scrollback: number;
  bellStyle: 'none' | 'visual' | 'sound' | 'both';
  autoWrap: boolean;
  wordWrap: boolean;
  lineHeight: number;
  padding: number;
  opacity: number;
  backgroundOpacity: number;
}

const AdvancedTerminal: React.FC<AdvancedTerminalProps> = ({ 
  token, 
  user, 
  isFullscreen = false,
  onToggleFullscreen,
  sessionId = 'default',
  onSessionChange
}) => {
  const terminalRef = useRef<HTMLDivElement>(null);
  const terminalInstance = useRef<XTerminal | null>(null);
  const fitAddon = useRef<FitAddon | null>(null);
  const socket = useRef<Socket | null>(null);
  const [currentLine, setCurrentLine] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const [commandHistory, setCommandHistory] = useState<string[]>([]);
  const [historyIndex, setHistoryIndex] = useState(-1);
  const [sessions, setSessions] = useState<TerminalSession[]>([
    {
      id: 'default',
      name: 'Main Terminal',
      isActive: true,
      commandCount: 0,
      lastCommand: '',
      createdAt: new Date().toISOString(),
      workingDirectory: '/home/' + user.username,
      environment: 'bash'
    }
  ]);
  const [activeSession, setActiveSession] = useState('default');
  const [showSettings, setShowSettings] = useState(false);
  const [showSessions, setShowSessions] = useState(false);
  const [settings, setSettings] = useState<TerminalSettings>({
    fontSize: 14,
    fontFamily: 'Fira Code',
    theme: 'dark',
    cursorBlink: true,
    cursorStyle: 'block',
    scrollback: 1000,
    bellStyle: 'none',
    autoWrap: true,
    wordWrap: false,
    lineHeight: 1.2,
    padding: 8,
    opacity: 1,
    backgroundOpacity: 0.95
  });
  const [isRecording, setIsRecording] = useState(false);
  const [recordedCommands, setRecordedCommands] = useState<string[]>([]);
  const [showCommandPalette, setShowCommandPalette] = useState(false);
  const [searchQuery, setSearchQuery] = useState('');
  const [filteredHistory, setFilteredHistory] = useState<string[]>([]);
  const [notifications, setNotifications] = useState<any[]>([]);
  const [showNotifications, setShowNotifications] = useState(false);
  const [securityLevel, setSecurityLevel] = useState<'low' | 'medium' | 'high'>('medium');
  const [isLocked, setIsLocked] = useState(false);
  const [lockPassword, setLockPassword] = useState('');

  // Hotkeys avanzados
  useHotkeys('ctrl+shift+p', () => setShowCommandPalette(true));
  useHotkeys('ctrl+shift+s', () => setShowSettings(true));
  useHotkeys('ctrl+shift+t', () => setShowSessions(true));
  useHotkeys('ctrl+shift+l', () => setIsLocked(true));
  useHotkeys('ctrl+shift+r', () => setIsRecording(!isRecording));
  useHotkeys('ctrl+shift+f', () => onToggleFullscreen?.());
  useHotkeys('ctrl+shift+h', () => setShowNotifications(true));

  const initializeTerminal = useCallback(() => {
    if (!terminalRef.current) return;

    // Destruir terminal existente
    if (terminalInstance.current) {
      terminalInstance.current.dispose();
    }

    // Crear nueva instancia con configuración avanzada
    const terminal = new XTerminal({
      theme: {
        background: settings.theme === 'dark' ? '#0d1117' : '#ffffff',
        foreground: settings.theme === 'dark' ? '#c9d1d9' : '#24292f',
        cursor: settings.theme === 'dark' ? '#58a6ff' : '#0969da',
        black: settings.theme === 'dark' ? '#484f58' : '#656d76',
        red: settings.theme === 'dark' ? '#f85149' : '#cf222e',
        green: settings.theme === 'dark' ? '#3fb950' : '#1a7f37',
        yellow: settings.theme === 'dark' ? '#d29922' : '#9a6700',
        blue: settings.theme === 'dark' ? '#58a6ff' : '#0969da',
        magenta: settings.theme === 'dark' ? '#bc8cff' : '#8250df',
        cyan: settings.theme === 'dark' ? '#39d353' : '#1a7f37',
        white: settings.theme === 'dark' ? '#b1bac4' : '#6e7781',
        brightBlack: settings.theme === 'dark' ? '#6e7681' : '#8c959f',
        brightRed: settings.theme === 'dark' ? '#ff7b72' : '#d1242f',
        brightGreen: settings.theme === 'dark' ? '#56d364' : '#238636',
        brightYellow: settings.theme === 'dark' ? '#e3b341' : '#bf8700',
        brightBlue: settings.theme === 'dark' ? '#79c0ff' : '#2188ff',
        brightMagenta: settings.theme === 'dark' ? '#d2a8ff' : '#a475f9',
        brightCyan: settings.theme === 'dark' ? '#56d364' : '#39d353',
        brightWhite: settings.theme === 'dark' ? '#f0f6fc' : '#ffffff',
      },
      fontFamily: settings.fontFamily,
      fontSize: settings.fontSize,
      lineHeight: settings.lineHeight,
      cursorBlink: settings.cursorBlink,
      cursorStyle: settings.cursorStyle,
      scrollback: settings.scrollback,
      allowTransparency: true,
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

    // Configurar prompt avanzado
    const writePrompt = () => {
      const session = sessions.find(s => s.id === activeSession);
      const timestamp = new Date().toLocaleTimeString();
      terminal.write('\r\n');
      terminal.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
      terminal.write('\x1b[1;32m' + user.username + '\x1b[0m');
      terminal.write('@');
      terminal.write('\x1b[1;34mstealer-web\x1b[0m');
      terminal.write(':');
      terminal.write('\x1b[1;33m' + (session?.workingDirectory || '~') + '\x1b[0m');
      terminal.write('$ ');
    };

    // Mensaje de bienvenida avanzado
    const welcomeMessage = () => {
      terminal.write('\x1b[1;36m');
      terminal.write('╔══════════════════════════════════════════════════════════════════════════════╗\r\n');
      terminal.write('║                        🚀 ADVANCED STEALER WEB TERMINAL 🚀                     ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  Professional Security Terminal Interface - Educational Purposes Only        ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  User: ' + user.username.padEnd(67) + ' ║\r\n');
      terminal.write('║  Role: ' + user.role.padEnd(67) + ' ║\r\n');
      terminal.write('║  Session: ' + activeSession.padEnd(64) + ' ║\r\n');
      terminal.write('║  Security: ' + securityLevel.toUpperCase().padEnd(62) + ' ║\r\n');
      terminal.write('║  Date: ' + new Date().toLocaleString().padEnd(66) + ' ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  🔧 Hotkeys: Ctrl+Shift+P (Command Palette) | Ctrl+Shift+S (Settings)       ║\r\n');
      terminal.write('║  🔒 Security: Ctrl+Shift+L (Lock) | Ctrl+Shift+R (Record)                   ║\r\n');
      terminal.write('║  📱 Sessions: Ctrl+Shift+T (Sessions) | Ctrl+Shift+F (Fullscreen)          ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  ⚠️  Advanced security features enabled - All activities are logged          ║\r\n');
      terminal.write('╚══════════════════════════════════════════════════════════════════════════════╝\r\n');
      terminal.write('\x1b[0m');
    };

    welcomeMessage();
    writePrompt();

    // Manejar entrada de datos con funcionalidades avanzadas
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

          // Actualizar sesión
          setSessions(prev => prev.map(s => 
            s.id === activeSession 
              ? { ...s, commandCount: s.commandCount + 1, lastCommand: currentLine }
              : s
          ));

          // Enviar comando al servidor
          if (socket.current) {
            socket.current.emit('executeCommand', {
              command: currentLine,
              sessionId: activeSession
            });
          }

          // Mostrar comando en terminal
          terminal.write('\r\n');
          terminal.write('\x1b[2m' + currentLine + '\x1b[0m\r\n');

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

    // Manejar selección de texto
    terminal.onSelectionChange(() => {
      const selection = terminal.getSelection();
      if (selection) {
        // Mostrar información de selección
        console.log('Texto seleccionado:', selection);
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
  }, [user, currentLine, commandHistory, historyIndex, sessions, activeSession, settings, isLocked, isRecording, securityLevel]);

  useEffect(() => {
    const cleanup = initializeTerminal();
    return cleanup;
  }, [initializeTerminal]);

  // Conectar socket con funcionalidades avanzadas
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
        const session = sessions.find(s => s.id === activeSession);
        const timestamp = new Date().toLocaleTimeString();
        terminal.write('\r\n');
        terminal.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
        terminal.write('\x1b[1;32m' + user.username + '\x1b[0m');
        terminal.write('@');
        terminal.write('\x1b[1;34mstealer-web\x1b[0m');
        terminal.write(':');
        terminal.write('\x1b[1;33m' + (session?.workingDirectory || '~') + '\x1b[0m');
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
  }, [token, user, sessions, activeSession]);

  // Filtrar historial de comandos
  useEffect(() => {
    if (searchQuery) {
      setFilteredHistory(commandHistory.filter(cmd => 
        cmd.toLowerCase().includes(searchQuery.toLowerCase())
      ));
    } else {
      setFilteredHistory(commandHistory);
    }
  }, [searchQuery, commandHistory]);

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

  const createNewSession = () => {
    const newSession: TerminalSession = {
      id: `session_${Date.now()}`,
      name: `Terminal ${sessions.length + 1}`,
      isActive: false,
      commandCount: 0,
      lastCommand: '',
      createdAt: new Date().toISOString(),
      workingDirectory: '/home/' + user.username,
      environment: 'bash'
    };
    setSessions(prev => [...prev, newSession]);
    setActiveSession(newSession.id);
    onSessionChange?.(newSession.id);
  };

  const deleteSession = (sessionId: string) => {
    if (sessions.length <= 1) return;
    setSessions(prev => prev.filter(s => s.id !== sessionId));
    if (activeSession === sessionId) {
      const newActive = sessions.find(s => s.id !== sessionId);
      if (newActive) {
        setActiveSession(newActive.id);
        onSessionChange?.(newActive.id);
      }
    }
  };

  const exportCommands = () => {
    const data = {
      user: user.username,
      session: activeSession,
      timestamp: new Date().toISOString(),
      commands: recordedCommands.length > 0 ? recordedCommands : commandHistory,
      settings
    };
    
    const blob = new Blob([JSON.stringify(data, null, 2)], { type: 'application/json' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = `terminal-session-${activeSession}-${Date.now()}.json`;
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
            Advanced Terminal
          </Typography>
          <Chip 
            label={activeSession}
            size="small"
            sx={{ color: 'white', borderColor: 'white' }}
            variant="outlined"
          />
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
          <Tooltip title="Command Palette (Ctrl+Shift+P)">
            <IconButton onClick={() => setShowCommandPalette(true)} sx={{ color: 'white' }}>
              <Code />
            </IconButton>
          </Tooltip>
          
          <Tooltip title="Sessions (Ctrl+Shift+T)">
            <IconButton onClick={() => setShowSessions(true)} sx={{ color: 'white' }}>
              <Badge badgeContent={sessions.length} color="error">
                <Timeline />
              </Badge>
            </IconButton>
          </Tooltip>
          
          <Tooltip title="Settings (Ctrl+Shift+S)">
            <IconButton onClick={() => setShowSettings(true)} sx={{ color: 'white' }}>
              <Settings />
            </IconButton>
          </Tooltip>
          
          <Tooltip title="Export Commands">
            <IconButton onClick={exportCommands} sx={{ color: 'white' }}>
              <Download />
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
            background: settings.theme === 'dark' ? '#0d1117' : '#ffffff',
            padding: `${settings.padding}px`,
            opacity: settings.opacity,
            fontFamily: settings.fontFamily,
            fontSize: `${settings.fontSize}px`,
            lineHeight: settings.lineHeight
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

      {/* Command Palette Dialog */}
      <Dialog open={showCommandPalette} onClose={() => setShowCommandPalette(false)} maxWidth="md" fullWidth>
        <DialogTitle>Command Palette</DialogTitle>
        <DialogContent>
          <TextField
            fullWidth
            placeholder="Search commands or type to filter history..."
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            sx={{ mb: 2 }}
          />
          <List>
            {filteredHistory.map((cmd, index) => (
              <ListItem 
                key={index} 
                component="button"
                onClick={() => {
                  setCurrentLine(cmd);
                  setShowCommandPalette(false);
                }}
                sx={{ cursor: 'pointer' }}
              >
                <ListItemIcon>
                  <History />
                </ListItemIcon>
                <ListItemText primary={cmd} />
              </ListItem>
            ))}
          </List>
        </DialogContent>
      </Dialog>

      {/* Sessions Dialog */}
      <Dialog open={showSessions} onClose={() => setShowSessions(false)} maxWidth="md" fullWidth>
        <DialogTitle>Terminal Sessions</DialogTitle>
        <DialogContent>
          <Box display="flex" justifyContent="space-between" alignItems="center" mb={2}>
            <Typography variant="h6">Active Sessions</Typography>
            <Button startIcon={<Add />} onClick={createNewSession}>
              New Session
            </Button>
          </Box>
          <List>
            {sessions.map((session) => (
              <ListItem key={session.id}>
                <ListItemIcon>
                  <TerminalIcon color={session.id === activeSession ? 'primary' : 'disabled'} />
                </ListItemIcon>
                <ListItemText 
                  primary={session.name}
                  secondary={`Commands: ${session.commandCount} | Created: ${new Date(session.createdAt).toLocaleString()}`}
                />
                <Box>
                  {session.id !== activeSession && (
                    <Button 
                      onClick={() => {
                        setActiveSession(session.id);
                        onSessionChange?.(session.id);
                        setShowSessions(false);
                      }}
                    >
                      Activate
                    </Button>
                  )}
                  {sessions.length > 1 && (
                    <IconButton onClick={() => deleteSession(session.id)}>
                      <Close />
                    </IconButton>
                  )}
                </Box>
              </ListItem>
            ))}
          </List>
        </DialogContent>
      </Dialog>

      {/* Settings Dialog */}
      <Dialog open={showSettings} onClose={() => setShowSettings(false)} maxWidth="md" fullWidth>
        <DialogTitle>Terminal Settings</DialogTitle>
        <DialogContent>
          <Box sx={{ pt: 2 }}>
            <Typography variant="h6" gutterBottom>Appearance</Typography>
            <Box sx={{ mb: 3 }}>
              <Typography gutterBottom>Font Size</Typography>
              <Slider
                value={settings.fontSize}
                onChange={(_, value) => setSettings(prev => ({ ...prev, fontSize: value as number }))}
                min={8}
                max={24}
                marks
                valueLabelDisplay="auto"
              />
            </Box>
            
            <Box sx={{ mb: 3 }}>
              <FormControl fullWidth>
                <InputLabel>Font Family</InputLabel>
                <Select
                  value={settings.fontFamily}
                  onChange={(e) => setSettings(prev => ({ ...prev, fontFamily: e.target.value }))}
                >
                  <MenuItem value="Fira Code">Fira Code</MenuItem>
                  <MenuItem value="Consolas">Consolas</MenuItem>
                  <MenuItem value="Monaco">Monaco</MenuItem>
                  <MenuItem value="Courier New">Courier New</MenuItem>
                </Select>
              </FormControl>
            </Box>
            
            <Box sx={{ mb: 3 }}>
              <FormControl fullWidth>
                <InputLabel>Theme</InputLabel>
                <Select
                  value={settings.theme}
                  onChange={(e) => setSettings(prev => ({ ...prev, theme: e.target.value }))}
                >
                  <MenuItem value="dark">Dark</MenuItem>
                  <MenuItem value="light">Light</MenuItem>
                </Select>
              </FormControl>
            </Box>
            
            <Divider sx={{ my: 3 }} />
            
            <Typography variant="h6" gutterBottom>Behavior</Typography>
            <FormControlLabel
              control={<Switch checked={settings.cursorBlink} onChange={(e) => setSettings(prev => ({ ...prev, cursorBlink: e.target.checked }))} />}
              label="Cursor Blink"
            />
            <FormControlLabel
              control={<Switch checked={settings.autoWrap} onChange={(e) => setSettings(prev => ({ ...prev, autoWrap: e.target.checked }))} />}
              label="Auto Wrap"
            />
            <FormControlLabel
              control={<Switch checked={isRecording} onChange={(e) => setIsRecording(e.target.checked)} />}
              label="Command Recording"
            />
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowSettings(false)}>Cancel</Button>
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

export default AdvancedTerminal;