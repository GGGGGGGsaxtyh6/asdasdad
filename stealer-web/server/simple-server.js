const express = require('express');
const http = require('http');
const socketIo = require('socket.io');
const cors = require('cors');
const { exec } = require('child_process');
const path = require('path');
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
require('dotenv').config();

const app = express();
const server = http.createServer(app);

// Configuración de Socket.io
const io = socketIo(server, {
  cors: {
    origin: "*",
    methods: ["GET", "POST"],
    credentials: true
  }
});

// Middleware
app.use(cors());
app.use(express.json());
app.use(express.static(path.join(__dirname, '../client/build')));

// Configuración JWT
const JWT_SECRET = process.env.JWT_SECRET || 'stealer-web-secret-key-2024';
const JWT_EXPIRES_IN = '24h';

// Usuarios
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
    console.error('Login error:', error);
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
      return res.status(403).json({ error: 'Token inválido' });
    }
    res.json({ user });
  });
});

// Comandos permitidos
const allowedCommands = [
  'ls', 'dir', 'pwd', 'cd', 'cat', 'echo', 'whoami', 'uname',
  'ps', 'top', 'htop', 'df', 'du', 'free', 'uptime', 'date',
  'history', 'env', 'which', 'whereis', 'find', 'grep',
  'tail', 'head', 'wc', 'sort', 'uniq', 'cut', 'awk', 'sed',
  'touch', 'mkdir', 'rmdir', 'cp', 'mv', 'chmod', 'chown'
];

// Validar comando
const isValidCommand = (command) => {
  const trimmedCommand = command.trim().toLowerCase();
  
  // Comandos peligrosos
  const dangerousCommands = [
    'rm -rf', 'format', 'del /s', 'sudo rm', 'mkfs', 'dd if=/dev/zero',
    'shutdown', 'reboot', 'halt', 'poweroff', 'init 0', 'init 6'
  ];

  for (const dangerous of dangerousCommands) {
    if (trimmedCommand.includes(dangerous.toLowerCase())) {
      return { valid: false, reason: 'Comando peligroso detectado' };
    }
  }

  const commandParts = trimmedCommand.split(' ');
  const baseCommand = commandParts[0];

  if (!allowedCommands.includes(baseCommand)) {
    return { valid: false, reason: 'Comando no permitido para fines educativos' };
  }

  return { valid: true };
};

// Socket.io
io.on('connection', (socket) => {
  console.log(`Nueva conexión: ${socket.id}`);

  // Autenticación
  socket.on('authenticate', (token) => {
    try {
      const decoded = jwt.verify(token, JWT_SECRET);
      socket.user = decoded;
      socket.emit('authenticated', { user: decoded });
      console.log(`Usuario autenticado: ${decoded.username}`);
    } catch (error) {
      console.error('Auth error:', error);
      socket.emit('auth_error', { error: 'Token inválido' });
      socket.disconnect();
    }
  });

  // Ejecutar comando
  socket.on('executeCommand', (data) => {
    if (!socket.user) {
      socket.emit('commandResult', {
        type: 'error',
        output: 'No autenticado',
        sessionId: 'default'
      });
      return;
    }

    const { command, sessionId } = data;
    console.log(`Comando recibido: ${command} de ${socket.user.username}`);

    // Validar comando
    const validation = isValidCommand(command);
    if (!validation.valid) {
      socket.emit('commandResult', {
        type: 'error',
        output: `Error: ${validation.reason}`,
        sessionId: sessionId || 'default'
      });
      return;
    }

    // Ejecutar comando
    exec(command, { timeout: 10000 }, (error, stdout, stderr) => {
      if (error) {
        console.error('Command error:', error);
        socket.emit('commandResult', {
          type: 'error',
          output: `Error: ${error.message}`,
          sessionId: sessionId || 'default'
        });
        return;
      }

      if (stderr) {
        socket.emit('commandResult', {
          type: 'stderr',
          output: stderr,
          sessionId: sessionId || 'default'
        });
      }

      if (stdout) {
        socket.emit('commandResult', {
          type: 'stdout',
          output: stdout,
          sessionId: sessionId || 'default'
        });
      }

      // Enviar comando completado
      socket.emit('commandResult', {
        type: 'exit',
        output: '',
        sessionId: sessionId || 'default'
      });
    });
  });

  // Desconexión
  socket.on('disconnect', () => {
    console.log(`Cliente desconectado: ${socket.id}`);
  });
});

// Servir aplicación React
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, '../client/build', 'index.html'));
});

// Catch-all handler
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

// Manejo de errores
process.on('uncaughtException', (err) => {
  console.error('Error no capturado:', err);
});

process.on('unhandledRejection', (reason, promise) => {
  console.error('Promesa rechazada no manejada:', reason);
});