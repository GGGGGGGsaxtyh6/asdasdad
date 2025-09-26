const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');
const { exec, spawn } = require('child_process');
const path = require('path');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
require('dotenv').config();

const app = express();
const server = http.createServer(app);

// Configuración de Socket.io con CORS
const io = socketIo(server, {
  cors: {
    origin: process.env.CLIENT_URL || "http://localhost:3000",
    methods: ["GET", "POST"],
    credentials: true
  }
});

// Middleware de seguridad
app.use(helmet({
  contentSecurityPolicy: false, // Deshabilitado para desarrollo
  crossOriginEmbedderPolicy: false
}));

app.use(cors({
  origin: process.env.CLIENT_URL || "http://localhost:3000",
  credentials: true
}));

app.use(morgan('combined'));
app.use(express.json());
app.use(express.static(path.join(__dirname, '../client/build')));

// Configuración JWT
const JWT_SECRET = process.env.JWT_SECRET || 'stealer-web-secret-key-2024';
const JWT_EXPIRES_IN = '24h';

// Usuarios en memoria (en producción usar base de datos)
const users = [
  {
    id: 1,
    username: 'admin',
    password: bcrypt.hashSync('admin123', 10),
    role: 'admin'
  },
  {
    id: 2,
    username: 'student',
    password: bcrypt.hashSync('student123', 10),
    role: 'student'
  }
];

// Middleware de autenticación JWT
const authenticateToken = (req, res, next) => {
  const authHeader = req.headers['authorization'];
  const token = authHeader && authHeader.split(' ')[1];

  if (!token) {
    return res.status(401).json({ error: 'Token de acceso requerido' });
  }

  jwt.verify(token, JWT_SECRET, (err, user) => {
    if (err) {
      return res.status(403).json({ error: 'Token inválido' });
    }
    req.user = user;
    next();
  });
};

// Rutas de autenticación
app.post('/api/login', async (req, res) => {
  try {
    const { username, password } = req.body;

    const user = users.find(u => u.username === username);
    if (!user) {
      return res.status(401).json({ error: 'Credenciales inválidas' });
    }

    const validPassword = await bcrypt.compare(password, user.password);
    if (!validPassword) {
      return res.status(401).json({ error: 'Credenciales inválidas' });
    }

    const token = jwt.sign(
      { id: user.id, username: user.username, role: user.role },
      JWT_SECRET,
      { expiresIn: JWT_EXPIRES_IN }
    );

    res.json({
      token,
      user: {
        id: user.id,
        username: user.username,
        role: user.role
      }
    });
  } catch (error) {
    res.status(500).json({ error: 'Error interno del servidor' });
  }
});

// Ruta para verificar token
app.get('/api/verify', authenticateToken, (req, res) => {
  res.json({ user: req.user });
});

// Lista de comandos peligrosos (para fines educativos)
const dangerousCommands = [
  'rm -rf /',
  'format c:',
  'del /s /q c:\\*',
  'sudo rm -rf /',
  'mkfs.ext4 /dev/sda',
  'dd if=/dev/zero of=/dev/sda',
  'shutdown',
  'reboot',
  'halt',
  'poweroff'
];

// Función para validar comandos
const isValidCommand = (command) => {
  const trimmedCommand = command.trim().toLowerCase();
  
  // Verificar comandos peligrosos
  for (const dangerous of dangerousCommands) {
    if (trimmedCommand.includes(dangerous.toLowerCase())) {
      return { valid: false, reason: 'Comando peligroso detectado' };
    }
  }

  // Permitir comandos básicos para fines educativos
  const allowedCommands = [
    'ls', 'dir', 'pwd', 'cd', 'cat', 'echo', 'whoami', 'uname',
    'ps', 'top', 'htop', 'df', 'du', 'free', 'uptime', 'date',
    'history', 'env', 'which', 'whereis', 'find', 'grep',
    'tail', 'head', 'wc', 'sort', 'uniq', 'cut', 'awk', 'sed'
  ];

  const commandParts = trimmedCommand.split(' ');
  const baseCommand = commandParts[0];

  if (!allowedCommands.includes(baseCommand)) {
    return { valid: false, reason: 'Comando no permitido para fines educativos' };
  }

  return { valid: true };
};

// Almacenamiento de sesiones activas
const activeSessions = new Map();

// Configuración de Socket.io
io.on('connection', (socket) => {
  console.log(`Nueva conexión: ${socket.id}`);

  // Autenticación de socket
  socket.on('authenticate', (token) => {
    try {
      const decoded = jwt.verify(token, JWT_SECRET);
      socket.user = decoded;
      socket.emit('authenticated', { user: decoded });
      console.log(`Usuario autenticado: ${decoded.username}`);
    } catch (error) {
      socket.emit('auth_error', { error: 'Token inválido' });
      socket.disconnect();
    }
  });

  // Ejecutar comando
  socket.on('executeCommand', async (data) => {
    if (!socket.user) {
      socket.emit('commandResult', {
        type: 'error',
        output: 'No autenticado'
      });
      return;
    }

    const { command, sessionId } = data;
    const session = sessionId || 'default';

    // Validar comando
    const validation = isValidCommand(command);
    if (!validation.valid) {
      socket.emit('commandResult', {
        type: 'error',
        output: `Error: ${validation.reason}`,
        sessionId: session
      });
      return;
    }

    console.log(`Comando ejecutado por ${socket.user.username}: ${command}`);

    // Ejecutar comando
    const child = spawn('bash', ['-c', command], {
      stdio: 'pipe',
      shell: true
    });

    // Manejar salida estándar
    child.stdout.on('data', (data) => {
      socket.emit('commandResult', {
        type: 'stdout',
        output: data.toString(),
        sessionId: session,
        timestamp: new Date().toISOString()
      });
    });

    // Manejar errores
    child.stderr.on('data', (data) => {
      socket.emit('commandResult', {
        type: 'stderr',
        output: data.toString(),
        sessionId: session,
        timestamp: new Date().toISOString()
      });
    });

    // Manejar finalización
    child.on('close', (code) => {
      socket.emit('commandResult', {
        type: 'exit',
        output: `\n[Proceso terminado con código: ${code}]`,
        sessionId: session,
        timestamp: new Date().toISOString()
      });
    });

    // Almacenar proceso para posible terminación
    if (!activeSessions.has(session)) {
      activeSessions.set(session, new Map());
    }
    activeSessions.get(session).set(child.pid, child);
  });

  // Terminar proceso
  socket.on('killProcess', (data) => {
    const { sessionId, pid } = data;
    const session = sessionId || 'default';

    if (activeSessions.has(session) && activeSessions.get(session).has(pid)) {
      const process = activeSessions.get(session).get(pid);
      process.kill();
      activeSessions.get(session).delete(pid);
      socket.emit('commandResult', {
        type: 'info',
        output: `Proceso ${pid} terminado`,
        sessionId: session
      });
    }
  });

  // Limpiar sesión
  socket.on('clearSession', (data) => {
    const sessionId = data.sessionId || 'default';
    if (activeSessions.has(sessionId)) {
      const processes = activeSessions.get(sessionId);
      processes.forEach((process) => {
        process.kill();
      });
      activeSessions.delete(sessionId);
    }
  });

  // Manejar desconexión
  socket.on('disconnect', () => {
    console.log(`Cliente desconectado: ${socket.id}`);
    // Limpiar procesos activos del usuario
    activeSessions.forEach((session) => {
      session.forEach((process) => {
        process.kill();
      });
    });
    activeSessions.clear();
  });
});

// Servir aplicación React
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, '../client/build', 'index.html'));
});

// Catch-all handler para rutas de React Router
app.use((req, res) => {
  if (req.path.startsWith('/api/')) {
    return res.status(404).json({ error: 'API endpoint not found' });
  }
  res.sendFile(path.join(__dirname, '../client/build', 'index.html'));
});

const PORT = process.env.PORT || 4000;
server.listen(PORT, () => {
  console.log(`🚀 Servidor ejecutándose en puerto ${PORT}`);
  console.log(`📱 Interfaz web disponible en: http://localhost:${PORT}`);
  console.log(`🔐 Usuarios de prueba:`);
  console.log(`   - admin / admin123 (Administrador)`);
  console.log(`   - student / student123 (Estudiante)`);
});

// Manejo de errores no capturados
process.on('uncaughtException', (err) => {
  console.error('Error no capturado:', err);
});

process.on('unhandledRejection', (reason, promise) => {
  console.error('Promesa rechazada no manejada:', reason);
});