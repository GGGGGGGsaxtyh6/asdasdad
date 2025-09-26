const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const cors = require('cors');
const { exec, spawn, execSync } = require('child_process');
const path = require('path');
const fs = require('fs');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const os = require('os');
const { promisify } = require('util');
const rateLimit = require('express-rate-limit');
const helmet = require('helmet');
const compression = require('compression');
require('dotenv').config();

const execAsync = promisify(exec);

const app = express();
const server = http.createServer(app);

// Configuración avanzada de Socket.io con múltiples namespaces
const io = socketIo(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"],
    credentials: true
  },
  transports: ['websocket', 'polling'],
  upgrade: true,
  rememberUpgrade: true,
  pingTimeout: 60000,
  pingInterval: 25000
});

// Rate limiting
const limiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 minutos
  max: 1000, // límite de requests por IP
  message: 'Demasiadas requests desde esta IP, inténtalo de nuevo más tarde.'
});

// Middleware avanzado
app.use(helmet({
  contentSecurityPolicy: false,
  crossOriginEmbedderPolicy: false
}));
app.use(compression());
app.use(limiter);
app.use(cors({
  origin: "*",
  credentials: true,
  methods: ["GET", "POST", "PUT", "DELETE", "OPTIONS"],
  allowedHeaders: ["Content-Type", "Authorization", "X-Requested-With"]
}));
app.use(express.json({ limit: '50mb' }));
app.use(express.urlencoded({ extended: true, limit: '50mb' }));
app.use(express.static(path.join(__dirname, '../client/build')));

// Configuración JWT avanzada
const JWT_SECRET = process.env.JWT_SECRET || 'stealer-web-secret-key-2024-advanced-educational-purposes';
const JWT_EXPIRES_IN = '24h';

// Sistema de usuarios avanzado
const users = [
  {
    id: 1,
    username: 'admin',
    password: bcrypt.hashSync('admin123', 12),
    role: 'admin',
    permissions: ['all'],
    lastLogin: null,
    sessionCount: 0
  },
  {
    id: 2,
    username: 'student',
    password: bcrypt.hashSync('student123', 12),
    role: 'student',
    permissions: ['read', 'execute', 'monitor'],
    lastLogin: null,
    sessionCount: 0
  },
  {
    id: 3,
    username: 'analyst',
    password: bcrypt.hashSync('analyst123', 12),
    role: 'analyst',
    permissions: ['read', 'monitor', 'analyze'],
    lastLogin: null,
    sessionCount: 0
  }
];

// Sistema de logging avanzado
const logSystem = {
  logs: [],
  maxLogs: 10000,
  
  addLog(level, message, user = null, metadata = {}) {
    const log = {
      id: Date.now() + Math.random(),
      timestamp: new Date().toISOString(),
      level,
      message,
      user: user ? user.username : 'system',
      metadata,
      ip: metadata.ip || 'unknown'
    };
    
    this.logs.unshift(log);
    if (this.logs.length > this.maxLogs) {
      this.logs = this.logs.slice(0, this.maxLogs);
    }
    
    console.log(`[${log.level.toUpperCase()}] ${log.timestamp} - ${log.message}`);
  },
  
  getLogs(filter = {}) {
    let filteredLogs = this.logs;
    
    if (filter.level) {
      filteredLogs = filteredLogs.filter(log => log.level === filter.level);
    }
    
    if (filter.user) {
      filteredLogs = filteredLogs.filter(log => log.user === filter.user);
    }
    
    if (filter.dateFrom) {
      filteredLogs = filteredLogs.filter(log => new Date(log.timestamp) >= new Date(filter.dateFrom));
    }
    
    if (filter.dateTo) {
      filteredLogs = filteredLogs.filter(log => new Date(log.timestamp) <= new Date(filter.dateTo));
    }
    
    return filteredLogs.slice(0, filter.limit || 1000);
  }
};

// Sistema de métricas del sistema
const systemMetrics = {
  cpu: { usage: 0, cores: os.cpus().length, model: os.cpus()[0].model },
  memory: { total: os.totalmem(), free: os.freemem(), used: 0 },
  network: { interfaces: [], connections: [] },
  processes: [],
  files: { total: 0, size: 0 },
  security: { loggedUsers: [], lastLogins: [], privilegedProcesses: [], suidFiles: [] }
};

// Función para actualizar métricas del sistema
const updateSystemMetrics = async () => {
  try {
    // CPU Usage
    const cpuUsage = await execAsync("top -bn1 | grep 'Cpu(s)' | awk '{print $2}' | awk -F'%' '{print $1}'");
    systemMetrics.cpu.usage = parseFloat(cpuUsage.stdout.trim()) || 0;
    
    // Memory
    systemMetrics.memory.used = systemMetrics.memory.total - systemMetrics.memory.free;
    
    // Network interfaces
    const networkInterfaces = await execAsync("ip addr show");
    systemMetrics.network.interfaces = networkInterfaces.stdout;
    
    // Network connections
    const connections = await execAsync("netstat -tulpn");
    systemMetrics.network.connections = connections.stdout;
    
    // Processes
    const processes = await execAsync("ps aux --sort=-%cpu | head -100");
    systemMetrics.processes = processes.stdout.split('\n').slice(1).map(line => {
      const parts = line.trim().split(/\s+/);
      if (parts.length >= 11) {
        return {
          user: parts[0],
          pid: parts[1],
          cpu: parts[2],
          mem: parts[3],
          vsz: parts[4],
          rss: parts[5],
          tty: parts[6],
          stat: parts[7],
          start: parts[8],
          time: parts[9],
          command: parts.slice(10).join(' ')
        };
      }
      return null;
    }).filter(Boolean);
    
    // Security info
    const loggedUsers = await execAsync("who").catch(() => ({ stdout: '' }));
    systemMetrics.security.loggedUsers = loggedUsers.stdout;
    
    const lastLogins = await execAsync("last -10").catch(() => ({ stdout: '' }));
    systemMetrics.security.lastLogins = lastLogins.stdout;
    
    const privilegedProcesses = await execAsync("ps aux | grep -E '(sudo|su|root)' | grep -v grep").catch(() => ({ stdout: '' }));
    systemMetrics.security.privilegedProcesses = privilegedProcesses.stdout;
    
    const suidFiles = await execAsync("find /usr -perm -4000 2>/dev/null | head -20").catch(() => ({ stdout: '' }));
    systemMetrics.security.suidFiles = suidFiles.stdout;
    
  } catch (error) {
    logSystem.addLog('error', `Error updating system metrics: ${error.message}`);
  }
};

// Actualizar métricas cada 5 segundos
setInterval(updateSystemMetrics, 5000);

// Rutas de autenticación avanzadas
app.post('/api/login', async (req, res) => {
  try {
    const { username, password } = req.body;
    const clientIP = req.ip || req.connection.remoteAddress;

    const user = users.find(u => u.username === username);
    if (!user) {
      logSystem.addLog('warning', `Failed login attempt for user: ${username}`, null, { ip: clientIP });
      return res.status(401).json({ error: 'Credenciales inválidas' });
    }

    const validPassword = await bcrypt.compare(password, user.password);
    if (!validPassword) {
      logSystem.addLog('warning', `Failed login attempt for user: ${username}`, null, { ip: clientIP });
      return res.status(401).json({ error: 'Credenciales inválidas' });
    }

    // Actualizar información de usuario
    user.lastLogin = new Date().toISOString();
    user.sessionCount++;

    const token = jwt.sign(
      { 
        id: user.id, 
        username: user.username, 
        role: user.role,
        permissions: user.permissions,
        sessionId: Date.now()
      },
      JWT_SECRET,
      { expiresIn: JWT_EXPIRES_IN }
    );

    logSystem.addLog('info', `Successful login for user: ${username}`, user, { ip: clientIP });

    res.json({
      token,
      user: {
        id: user.id,
        username: user.username,
        role: user.role,
        permissions: user.permissions,
        lastLogin: user.lastLogin,
        sessionCount: user.sessionCount
      }
    });
  } catch (error) {
    logSystem.addLog('error', `Login error: ${error.message}`);
    res.status(500).json({ error: 'Error interno del servidor' });
  }
});

// Ruta para verificar token
app.get('/api/verify', (req, res) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    return res.status(401).json({ error: 'Token de acceso requerido' });
  }

  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) {
      logSystem.addLog('warning', `Invalid token verification attempt`);
      return res.status(403).json({ error: 'Token inválido' });
    }
    res.json({ user });
  });
});

// Ruta para obtener logs del sistema
app.get('/api/logs', (req, res) => {
  const { level, user, dateFrom, dateTo, limit } = req.query;
  const logs = logSystem.getLogs({ level, user, dateFrom, dateTo, limit });
  res.json({ logs, total: logSystem.logs.length });
});

// Ruta para obtener métricas del sistema
app.get('/api/metrics', (req, res) => {
  res.json({
    ...systemMetrics,
    timestamp: new Date().toISOString(),
    uptime: process.uptime(),
    platform: os.platform(),
    arch: os.arch(),
    hostname: os.hostname()
  });
});

// Ruta para obtener información detallada del sistema
app.get('/api/system/info', async (req, res) => {
  try {
    const info = {
      os: {
        platform: os.platform(),
        arch: os.arch(),
        release: os.release(),
        hostname: os.hostname(),
        uptime: os.uptime()
      },
      cpu: {
        model: os.cpus()[0].model,
        speed: os.cpus()[0].speed,
        cores: os.cpus().length,
        usage: systemMetrics.cpu.usage
      },
      memory: {
        total: os.totalmem(),
        free: os.freemem(),
        used: os.totalmem() - os.freemem(),
        percentage: ((os.totalmem() - os.freemem()) / os.totalmem() * 100).toFixed(2)
      },
      network: {
        interfaces: os.networkInterfaces()
      },
      users: {
        total: users.length,
        online: users.filter(u => u.sessionCount > 0).length
      }
    };
    
    res.json(info);
  } catch (error) {
    logSystem.addLog('error', `Error getting system info: ${error.message}`);
    res.status(500).json({ error: 'Error obteniendo información del sistema' });
  }
});

// Función avanzada para ejecutar comandos con validación y logging
const executeAdvancedCommand = (command, user, sessionId, callback) => {
  const commandId = Date.now() + Math.random();
  const startTime = Date.now();
  
  logSystem.addLog('info', `Executing command: ${command}`, user, { 
    commandId, 
    sessionId,
    command: command.substring(0, 100) // Log solo los primeros 100 caracteres por seguridad
  });

  // Validar permisos del usuario
  if (user.role !== 'admin' && command.includes('sudo') && !user.permissions.includes('sudo')) {
    const error = 'No tienes permisos para ejecutar comandos con sudo';
    logSystem.addLog('warning', error, user, { commandId });
    callback({
      type: 'error',
      output: error,
      exitCode: 1,
      commandId,
      executionTime: 0
    });
    return;
  }

  // Lista de comandos peligrosos que requieren permisos especiales
  const dangerousCommands = ['rm -rf', 'mkfs', 'dd if=', 'format', 'fdisk', 'parted'];
  const isDangerous = dangerousCommands.some(cmd => command.toLowerCase().includes(cmd));
  
  if (isDangerous && !user.permissions.includes('dangerous')) {
    const error = 'Comando peligroso detectado. Se requiere permiso especial.';
    logSystem.addLog('warning', error, user, { commandId, command: command.substring(0, 100) });
    callback({
      type: 'error',
      output: error,
      exitCode: 1,
      commandId,
      executionTime: 0
    });
    return;
  }

  const child = spawn('bash', ['-c', command], {
    stdio: 'pipe',
    timeout: 30000
  });

  let stdout = '';
  let stderr = '';
  let isCompleted = false;

  child.stdout.on('data', (data) => {
    stdout += data.toString();
    if (!isCompleted) {
      callback({
        type: 'stdout',
        output: data.toString(),
        commandId,
        sessionId
      });
    }
  });

  child.stderr.on('data', (data) => {
    stderr += data.toString();
    if (!isCompleted) {
      callback({
        type: 'stderr',
        output: data.toString(),
        commandId,
        sessionId
      });
    }
  });

  child.on('close', (code) => {
    if (!isCompleted) {
      isCompleted = true;
      const executionTime = Date.now() - startTime;
      
      logSystem.addLog('info', `Command completed with exit code: ${code}`, user, {
        commandId,
        exitCode: code,
        executionTime,
        outputLength: stdout.length + stderr.length
      });

      callback({
        type: 'exit',
        output: '',
        exitCode: code,
        commandId,
        sessionId,
        executionTime,
        fullOutput: stdout,
        fullError: stderr
      });
    }
  });

  child.on('error', (error) => {
    if (!isCompleted) {
      isCompleted = true;
      const executionTime = Date.now() - startTime;
      
      logSystem.addLog('error', `Command execution error: ${error.message}`, user, {
        commandId,
        executionTime
      });

      callback({
        type: 'error',
        output: `Error ejecutando comando: ${error.message}`,
        exitCode: 1,
        commandId,
        sessionId,
        executionTime
      });
    }
  });

  // Timeout de seguridad
  setTimeout(() => {
    if (!isCompleted) {
      isCompleted = true;
      child.kill('SIGTERM');
      
      logSystem.addLog('warning', 'Command timeout - killed', user, {
        commandId,
        executionTime: Date.now() - startTime
      });

      callback({
        type: 'error',
        output: 'Comando terminado por timeout (30 segundos)',
        exitCode: 124,
        commandId,
        sessionId,
        executionTime: Date.now() - startTime
      });
    }
  }, 30000);
};

// Namespace principal para la terminal
const terminalNamespace = io.of('/terminal');

terminalNamespace.on('connection', (socket) => {
  console.log(`Nueva conexión terminal: ${socket.id}`);
  
  let user = null;
  let sessionId = `session_${socket.id}_${Date.now()}`;

  // Autenticación
  socket.on('authenticate', (token) => {
    try {
      const decoded = jwt.verify(token, JWT_SECRET);
      user = decoded;
      socket.user = user;
      socket.emit('authenticated', { user: decoded, sessionId });
      
      logSystem.addLog('info', `Terminal session started`, user, { sessionId, socketId: socket.id });
      console.log(`Usuario autenticado en terminal: ${decoded.username}`);
    } catch (error) {
      console.error('Auth error:', error);
      socket.emit('auth_error', { error: 'Token inválido' });
      socket.disconnect();
    }
  });

  // Ejecutar comando
  socket.on('executeCommand', (data) => {
    if (!user) {
      socket.emit('commandResult', {
        type: 'error',
        output: 'No autenticado',
        sessionId
      });
      return;
    }

    const { command, sessionId: cmdSessionId } = data;
    const currentSessionId = cmdSessionId || sessionId;

    executeAdvancedCommand(command, user, currentSessionId, (result) => {
      socket.emit('commandResult', {
        ...result,
        sessionId: currentSessionId,
        timestamp: new Date().toISOString()
      });
    });
  });

  // Desconexión
  socket.on('disconnect', () => {
    if (user) {
      logSystem.addLog('info', `Terminal session ended`, user, { sessionId, socketId: socket.id });
    }
    console.log(`Terminal desconectado: ${socket.id}`);
  });
});

// Namespace para métricas en tiempo real
const metricsNamespace = io.of('/metrics');

metricsNamespace.on('connection', (socket) => {
  console.log(`Nueva conexión métricas: ${socket.id}`);
  
  let user = null;

  // Autenticación
  socket.on('authenticate', (token) => {
    try {
      const decoded = jwt.verify(token, JWT_SECRET);
      user = decoded;
      socket.user = user;
      socket.emit('authenticated', { user: decoded });
      
      // Enviar métricas iniciales
      socket.emit('systemMetrics', {
        ...systemMetrics,
        timestamp: new Date().toISOString()
      });
    } catch (error) {
      socket.emit('auth_error', { error: 'Token inválido' });
      socket.disconnect();
    }
  });

  // Solicitar métricas específicas
  socket.on('requestMetrics', (data) => {
    if (!user) return;

    const { type } = data;
    
    switch (type) {
      case 'system':
        socket.emit('metricsUpdate', {
          type: 'system',
          data: {
            cpu: systemMetrics.cpu,
            memory: systemMetrics.memory,
            timestamp: new Date().toISOString()
          }
        });
        break;
        
      case 'processes':
        socket.emit('metricsUpdate', {
          type: 'processes',
          data: systemMetrics.processes.slice(0, 50)
        });
        break;
        
      case 'network':
        socket.emit('metricsUpdate', {
          type: 'network',
          data: {
            interfaces: systemMetrics.network.interfaces,
            connections: systemMetrics.network.connections
          }
        });
        break;
        
      case 'security':
        socket.emit('metricsUpdate', {
          type: 'security',
          data: systemMetrics.security
        });
        break;
    }
  });

  socket.on('disconnect', () => {
    console.log(`Métricas desconectado: ${socket.id}`);
  });
});

// Emitir métricas actualizadas a todos los clientes conectados
setInterval(() => {
  metricsNamespace.emit('metricsUpdate', {
    type: 'system',
    data: {
      cpu: systemMetrics.cpu,
      memory: systemMetrics.memory,
      timestamp: new Date().toISOString()
    }
  });
}, 2000);

// Servir aplicación React
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, '../client/build', 'index.html'));
});

// Catch-all handler para React Router
app.use((req, res) => {
  if (req.path.startsWith('/api/')) {
    return res.status(404).json({ error: 'API endpoint not found' });
  }
  res.sendFile(path.join(__dirname, '../client/build', 'index.html'));
});

const PORT = process.env.PORT || 4000;
server.listen(PORT, () => {
  console.log(`🚀 Servidor AVANZADO ejecutándose en puerto ${PORT}`);
  console.log(`📱 Interfaz web disponible en: http://localhost:${PORT}`);
  console.log(`🔐 Usuarios disponibles:`);
  users.forEach(user => {
    console.log(`   - ${user.username} (${user.role}) - Permisos: ${user.permissions.join(', ')}`);
  });
  console.log(`⚠️  ADVERTENCIA: Sistema de acceso completo al sistema - Sin restricciones`);
  console.log(`📊 Métricas del sistema actualizándose cada 5 segundos`);
  console.log(`📝 Sistema de logging activo`);
  
  // Log inicial
  logSystem.addLog('info', 'Advanced server started', null, { port: PORT });
});

// Manejo avanzado de errores
process.on('uncaughtException', (err) => {
  logSystem.addLog('error', `Uncaught Exception: ${err.message}`, null, { stack: err.stack });
  console.error('Error no capturado:', err);
});

process.on('unhandledRejection', (reason, promise) => {
  logSystem.addLog('error', `Unhandled Rejection: ${reason}`, null, { promise });
  console.error('Promesa rechazada no manejada:', reason);
});

// Graceful shutdown
process.on('SIGTERM', () => {
  console.log('Recibida señal SIGTERM, cerrando servidor...');
  logSystem.addLog('info', 'Server shutting down');
  
  server.close(() => {
    console.log('Servidor cerrado correctamente');
    process.exit(0);
  });
});

process.on('SIGINT', () => {
  console.log('Recibida señal SIGINT, cerrando servidor...');
  logSystem.addLog('info', 'Server shutting down');
  
  server.close(() => {
    console.log('Servidor cerrado correctamente');
    process.exit(0);
  });
});