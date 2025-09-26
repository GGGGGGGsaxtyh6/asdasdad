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
  Alert,
  Snackbar,
  Menu,
  MenuItem,
  ListItemIcon,
  ListItemText,
  Divider,
  Slider,
  Select,
  FormControl,
  InputLabel,
  Badge,
  CircularProgress,
  LinearProgress,
  Tabs,
  Tab,
  Accordion,
  AccordionSummary,
  AccordionDetails,
  Card,
  CardContent,
  Grid,
  InputAdornment
} from '@mui/material';
import {
  Terminal as TerminalIcon,
  Settings,
  Fullscreen,
  FullscreenExit,
  Security,
  PlayArrow,
  Stop,
  Download,
  Upload,
  Lock,
  LockOpen,
  Warning,
  CheckCircle,
  Error,
  Info,
  Refresh,
  Clear,
  Search,
  Close,
  Save,
  History,
  Bookmark,
  BookmarkBorder,
  Palette,
  Speed,
  Memory,
  Storage,
  NetworkCheck,
  Timeline,
  Assessment,
  BugReport,
  Code,
  FileCopy,
  ContentPaste,
  ZoomIn,
  ZoomOut,
  Visibility,
  VisibilityOff,
  Keyboard,
  Mouse,
  TouchApp,
  ExpandMore as ExpandMoreIcon
} from '@mui/icons-material';
import { useHotkeys } from 'react-hotkeys-hook';

interface ProfessionalTerminalProps {
  token: string;
  user: any;
  isFullscreen?: boolean;
  onToggleFullscreen?: () => void;
}

const ProfessionalTerminal: React.FC<ProfessionalTerminalProps> = ({ 
  token, 
  user, 
  isFullscreen = false,
  onToggleFullscreen
}) => {
  const terminalRef = useRef<HTMLDivElement>(null);
  const terminalInstance = useRef<XTerminal | null>(null);
  const fitAddon = useRef<FitAddon | null>(null);
  const socket = useRef<Socket | null>(null);
  
  // Estados principales
  const [currentLine, setCurrentLine] = useState('');
  const [isConnected, setIsConnected] = useState(false);
  const [commandHistory, setCommandHistory] = useState<string[]>([]);
  const [historyIndex, setHistoryIndex] = useState(-1);
  
  // Estados de configuración
  const [showSettings, setShowSettings] = useState(false);
  const [showBookmarks, setShowBookmarks] = useState(false);
  const [showSessions, setShowSessions] = useState(false);
  const [showSearch, setShowSearch] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  
  // Estados de funcionalidad
  const [isRecording, setIsRecording] = useState(false);
  const [recordedCommands, setRecordedCommands] = useState<string[]>([]);
  const [notifications, setNotifications] = useState<any[]>([]);
  const [showNotifications, setShowNotifications] = useState(false);
  const [isLocked, setIsLocked] = useState(false);
  const [lockPassword, setLockPassword] = useState('');
  const [currentDir, setCurrentDir] = useState('/home/' + user.username);
  const [isExecuting, setIsExecuting] = useState(false);
  
  // Estados de configuración del terminal
  const [fontSize, setFontSize] = useState(14);
  const [fontFamily, setFontFamily] = useState('Fira Code');
  const [theme, setTheme] = useState('dark');
  const [cursorBlink, setCursorBlink] = useState(true);
  const [scrollback, setScrollback] = useState(10000);
  const [showTimestamps, setShowTimestamps] = useState(true);
  const [showUserInfo, setShowUserInfo] = useState(true);
  
  // Estados de métricas
  const [sessionStats, setSessionStats] = useState({
    commandsExecuted: 0,
    totalExecutionTime: 0,
    averageExecutionTime: 0,
    errorsCount: 0,
    lastCommand: '',
    uptime: 0
  });

  // Hotkeys avanzados
  useHotkeys('ctrl+shift+s', () => setShowSettings(true));
  useHotkeys('ctrl+shift+f', () => onToggleFullscreen?.());
  useHotkeys('ctrl+shift+r', () => setIsRecording(!isRecording));
  useHotkeys('ctrl+shift+l', () => setIsLocked(true));
  useHotkeys('ctrl+shift+c', () => clearTerminal());
  useHotkeys('ctrl+shift+b', () => setShowBookmarks(true));
  useHotkeys('ctrl+shift+t', () => setShowSessions(true));
  useHotkeys('ctrl+f', () => setShowSearch(true));
  useHotkeys('ctrl+shift+plus', () => setFontSize(prev => Math.min(24, prev + 1)));
  useHotkeys('ctrl+shift+minus', () => setFontSize(prev => Math.max(8, prev - 1)));
  useHotkeys('ctrl+shift+0', () => setFontSize(14));

  const initializeTerminal = useCallback(() => {
    if (!terminalRef.current) return;

    // Destruir terminal existente
    if (terminalInstance.current) {
      terminalInstance.current.dispose();
    }

    // Crear nueva instancia con configuración avanzada
    const terminal = new XTerminal({
      theme: getThemeConfig(theme),
      fontFamily: `${fontFamily}, Consolas, Monaco, monospace`,
      fontSize: fontSize,
      lineHeight: 1.2,
      cursorBlink: cursorBlink,
      cursorStyle: 'block',
      scrollback: scrollback,
      bellStyle: 'none',
      convertEol: true,
      disableStdin: false,
      allowTransparency: true
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
      if (showTimestamps) {
        const timestamp = new Date().toLocaleTimeString();
        terminal.write('\r\n');
        terminal.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
      }
      
      if (showUserInfo) {
        terminal.write('\x1b[1;32m' + user.username + '\x1b[0m');
        terminal.write('@');
        terminal.write('\x1b[1;34mstealer-web\x1b[0m');
        terminal.write(':');
        terminal.write('\x1b[1;33m' + currentDir + '\x1b[0m');
      }
      terminal.write('$ ');
    };

    // Mensaje de bienvenida avanzado
    const welcomeMessage = () => {
      terminal.write('\x1b[1;36m');
      terminal.write('╔══════════════════════════════════════════════════════════════════════════════╗\r\n');
      terminal.write('║                    🚀 STEALER WEB - PROFESSIONAL TERMINAL 🚀                ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  Professional Security Terminal Interface - REAL SYSTEM ACCESS               ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  User: ' + user.username.padEnd(67) + ' ║\r\n');
      terminal.write('║  Role: ' + user.role.padEnd(67) + ' ║\r\n');
      terminal.write('║  Permissions: ' + user.permissions.join(', ').padEnd(61) + ' ║\r\n');
      terminal.write('║  Date: ' + new Date().toLocaleString().padEnd(66) + ' ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  🔧 Advanced Hotkeys:                                                         ║\r\n');
      terminal.write('║     Ctrl+Shift+S (Settings) | Ctrl+Shift+F (Fullscreen)                     ║\r\n');
      terminal.write('║     Ctrl+Shift+L (Lock) | Ctrl+Shift+R (Record) | Ctrl+Shift+B (Bookmarks)  ║\r\n');
      terminal.write('║     Ctrl+F (Search) | Ctrl+Shift+T (Sessions) | Ctrl+Shift+C (Clear)        ║\r\n');
      terminal.write('║     Ctrl+Shift+Plus/Minus (Font Size) | Ctrl+Shift+0 (Reset Font)           ║\r\n');
      terminal.write('║                                                                              ║\r\n');
      terminal.write('║  ⚠️  REAL SYSTEM ACCESS - All commands are executed on the actual system     ║\r\n');
      terminal.write('║  🎯 Advanced Commands: ls -la, ps aux, netstat -tulpn, df -h, free -h       ║\r\n');
      terminal.write('║  🔍 Try: whoami, pwd, cat /etc/passwd, ss -tulpn, lsof -i                    ║\r\n');
      terminal.write('║  📊 System: htop, iotop, nethogs, strace, tcpdump, wireshark                 ║\r\n');
      terminal.write('╚══════════════════════════════════════════════════════════════════════════════╝\r\n');
      terminal.write('\x1b[0m');
    };

    welcomeMessage();
    writePrompt();

    // Manejar entrada de datos avanzada
    terminal.onData((data) => {
      if (isLocked || isExecuting) return;

      const code = data.charCodeAt(0);
      
      // Enter
      if (code === 13) {
        if (currentLine.trim()) {
          handleCommandExecution(currentLine);
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
        navigateHistory('up');
      }
      // Flecha abajo (historial)
      else if (code === 27 && data.charCodeAt(1) === 91 && data.charCodeAt(2) === 66) {
        navigateHistory('down');
      }
      // Ctrl+C
      else if (code === 3) {
        terminal.write('^C\r\n');
        writePrompt();
        setCurrentLine('');
        setIsExecuting(false);
      }
      // Ctrl+L (limpiar pantalla)
      else if (code === 12) {
        clearTerminal();
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
  }, [user, currentLine, commandHistory, historyIndex, isLocked, isRecording, currentDir, isExecuting, showTimestamps, showUserInfo, theme, fontSize, fontFamily, cursorBlink, scrollback]);

  const getThemeConfig = (themeName: string) => {
    const themes = {
      dark: {
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
      light: {
        background: '#ffffff',
        foreground: '#24292f',
        cursor: '#0969da',
        black: '#656d76',
        red: '#cf222e',
        green: '#1a7f37',
        yellow: '#9a6700',
        blue: '#0969da',
        magenta: '#8250df',
        cyan: '#1b7c83',
        white: '#6e7781',
        brightBlack: '#8c959f',
        brightRed: '#a40e26',
        brightGreen: '#1a7f37',
        brightYellow: '#633c01',
        brightBlue: '#0969da',
        brightMagenta: '#8250df',
        brightCyan: '#1b7c83',
        brightWhite: '#24292f',
      },
      neon: {
        background: '#000000',
        foreground: '#00ff00',
        cursor: '#00ffff',
        black: '#000000',
        red: '#ff0000',
        green: '#00ff00',
        yellow: '#ffff00',
        blue: '#0000ff',
        magenta: '#ff00ff',
        cyan: '#00ffff',
        white: '#ffffff',
        brightBlack: '#808080',
        brightRed: '#ff8080',
        brightGreen: '#80ff80',
        brightYellow: '#ffff80',
        brightBlue: '#8080ff',
        brightMagenta: '#ff80ff',
        brightCyan: '#80ffff',
        brightWhite: '#ffffff',
      }
    };
    return themes[themeName as keyof typeof themes] || themes.dark;
  };

  const handleCommandExecution = (command: string) => {
    // Agregar al historial
    const newHistory = [...commandHistory, command];
    setCommandHistory(newHistory);
    setHistoryIndex(-1);

    // Actualizar estadísticas
    setSessionStats(prev => ({
      ...prev,
      commandsExecuted: prev.commandsExecuted + 1,
      lastCommand: command
    }));

    // Grabar comando si está en modo grabación
    if (isRecording) {
      setRecordedCommands(prev => [...prev, command]);
    }

    // Mostrar comando en terminal
    if (terminalInstance.current) {
      terminalInstance.current.write('\r\n');
      terminalInstance.current.write('\x1b[2m' + command + '\x1b[0m\r\n');
    }

    // Marcar como ejecutando
    setIsExecuting(true);

    // Enviar comando al servidor
    if (socket.current) {
      socket.current.emit('executeCommand', {
        command: command,
        sessionId: 'default'
      });
    }
  };

  const navigateHistory = (direction: 'up' | 'down') => {
    if (commandHistory.length === 0) return;

    let newIndex;
    if (direction === 'up') {
      newIndex = historyIndex === -1 ? commandHistory.length - 1 : Math.max(0, historyIndex - 1);
    } else {
      newIndex = historyIndex + 1;
      if (newIndex >= commandHistory.length) {
        newIndex = -1;
      }
    }
    
    setHistoryIndex(newIndex);
    
    if (terminalInstance.current) {
      terminalInstance.current.write('\r\x1b[K');
      const timestamp = new Date().toLocaleTimeString();
      terminalInstance.current.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
      terminalInstance.current.write('\x1b[1;32m' + user.username + '\x1b[0m');
      terminalInstance.current.write('@');
      terminalInstance.current.write('\x1b[1;34mstealer-web\x1b[0m');
      terminalInstance.current.write(':');
      terminalInstance.current.write('\x1b[1;33m' + currentDir + '\x1b[0m');
      terminalInstance.current.write('$ ');
      
      if (newIndex !== -1) {
        const historyCommand = commandHistory[newIndex];
        terminalInstance.current.write(historyCommand);
        setCurrentLine(historyCommand);
      } else {
        setCurrentLine('');
      }
    }
  };

  const clearTerminal = () => {
    if (terminalInstance.current) {
      terminalInstance.current.clear();
      const timestamp = new Date().toLocaleTimeString();
      terminalInstance.current.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
      terminalInstance.current.write('\x1b[1;32m' + user.username + '\x1b[0m');
      terminalInstance.current.write('@');
      terminalInstance.current.write('\x1b[1;34mstealer-web\x1b[0m');
      terminalInstance.current.write(':');
      terminalInstance.current.write('\x1b[1;33m' + currentDir + '\x1b[0m');
      terminalInstance.current.write('$ ');
    }
  };

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
    
    addNotification('success', 'Exportado', 'Sesión exportada exitosamente');
  };

  // Conectar socket
  useEffect(() => {
    const socketInstance = io('https://7c2def2c86ac.ngrok-free.app/terminal', {
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

    socketInstance.on('commandResult', (result) => {
      if (!terminalInstance.current) return;

      const terminal = terminalInstance.current;
      
      switch (result.type) {
        case 'stdout':
          terminal.write('\x1b[0m' + result.output);
          break;
        case 'stderr':
          terminal.write('\r\n\x1b[1;31m' + result.output + '\x1b[0m');
          break;
        case 'error':
          terminal.write('\r\n\x1b[1;31m❌ Error: ' + result.output + '\x1b[0m\r\n');
          addNotification('error', 'Error de comando', result.output);
          break;
        case 'exit':
          setIsExecuting(false);
          break;
      }

      // Escribir nuevo prompt cuando el comando termine
      if (result.type === 'exit' || result.type === 'error') {
        setTimeout(() => {
          const timestamp = new Date().toLocaleTimeString();
          terminal.write('\r\n');
          if (showTimestamps) {
            terminal.write('\x1b[1;36m[' + timestamp + ']\x1b[0m ');
          }
          if (showUserInfo) {
            terminal.write('\x1b[1;32m' + user.username + '\x1b[0m');
            terminal.write('@');
            terminal.write('\x1b[1;34mstealer-web\x1b[0m');
            terminal.write(':');
            terminal.write('\x1b[1;33m' + currentDir + '\x1b[0m');
          }
          terminal.write('$ ');
        }, 50);
      }
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
  }, [token, user, currentDir, showTimestamps, showUserInfo]);

  useEffect(() => {
    const cleanup = initializeTerminal();
    return cleanup;
  }, [initializeTerminal]);

  return (
    <Box sx={{ height: '100%', display: 'flex', flexDirection: 'column' }}>
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
            Professional System Terminal
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
          {isExecuting && (
            <Chip 
              icon={<Refresh />}
              label="Executing..."
              color="info"
              size="small"
            />
          )}
        </Box>

        <Box display="flex" alignItems="center" gap={1}>
          <Tooltip title="Clear Terminal (Ctrl+Shift+C)">
            <IconButton onClick={clearTerminal} sx={{ color: 'white' }}>
              <Clear />
            </IconButton>
          </Tooltip>
          
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
            background: getThemeConfig(theme).background,
            padding: '8px',
            fontFamily: `${fontFamily}, Consolas, Monaco, monospace`,
            fontSize: `${fontSize}px`,
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
              background: 'rgba(0,0,0,0.95)',
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
            <FormControlLabel
              control={<Switch checked={showTimestamps} onChange={(e) => setShowTimestamps(e.target.checked)} />}
              label="Show Timestamps"
            />
            <FormControlLabel
              control={<Switch checked={showUserInfo} onChange={(e) => setShowUserInfo(e.target.checked)} />}
              label="Show User Info"
            />
            <FormControlLabel
              control={<Switch checked={cursorBlink} onChange={(e) => setCursorBlink(e.target.checked)} />}
              label="Cursor Blink"
            />
            
            <Box sx={{ mt: 2 }}>
              <Typography gutterBottom>Font Size: {fontSize}px</Typography>
              <Slider
                value={fontSize}
                onChange={(_, value) => setFontSize(value as number)}
                min={8}
                max={24}
                step={1}
                marks
              />
            </Box>
            
            <Box sx={{ mt: 2 }}>
              <Alert severity="info">
                <Typography variant="body2">
                  <strong>Professional System Access:</strong> All commands are executed on the actual system.
                  Advanced features include session recording, bookmarks, and real-time statistics.
                </Typography>
              </Alert>
            </Box>
          </Box>
        </DialogContent>
        <DialogActions>
          <Button onClick={() => setShowSettings(false)}>Close</Button>
          <Button variant="contained" onClick={() => setShowSettings(false)}>Save</Button>
        </DialogActions>
      </Dialog>
    </Box>
  );
};

export default ProfessionalTerminal;