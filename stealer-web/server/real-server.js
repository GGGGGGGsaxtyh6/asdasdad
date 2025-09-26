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

// Función para ejecutar comandos sin restricciones
const executeCommand = (command, callback) => {
  console.log(`Ejecutando comando: ${command}`);
  
  exec(command, { 
    timeout: 30000,
    maxBuffer: 1024 * 1024 * 10, // 10MB buffer
    encoding: 'utf8'
  }, (error, stdout, stderr) => {
    if (error) {
      console.error('Command error:', error);
      callback({
        type: 'error',
        output: `Error: ${error.message}`,
        exitCode: error.code
      });
      return;
    }

    if (stderr) {
      callback({
        type: 'stderr',
        output: stderr
      });
    }

    if (stdout) {
      callback({
        type: 'stdout',
        output: stdout
      });
    }

    callback({
      type: 'exit',
      output: '',
      exitCode: 0
    });
  });
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

  // Ejecutar comando (SIN RESTRICCIONES)
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

    // Ejecutar comando sin restricciones
    executeCommand(command, (result) => {
      socket.emit('commandResult', {
        ...result,
        sessionId: sessionId || 'default',
        timestamp: new Date().toISOString()
      });
    });
  });

  // Obtener información del sistema
  socket.on('getSystemInfo', () => {
    if (!socket.user) return;

    // CPU Info
    executeCommand('cat /proc/cpuinfo | grep "model name" | head -1', (result) => {
      socket.emit('systemInfo', {
        type: 'cpu',
        data: result.output
      });
    });

    // Memory Info
    executeCommand('free -h', (result) => {
      socket.emit('systemInfo', {
        type: 'memory',
        data: result.output
      });
    });

    // Disk Info
    executeCommand('df -h', (result) => {
      socket.emit('systemInfo', {
        type: 'disk',
        data: result.output
      });
    });

    // Uptime
    executeCommand('uptime', (result) => {
      socket.emit('systemInfo', {
        type: 'uptime',
        data: result.output
      });
    });

    // Load Average
    executeCommand('cat /proc/loadavg', (result) => {
      socket.emit('systemInfo', {
        type: 'load',
        data: result.output
      });
    });
  });

  // Obtener procesos
  socket.on('getProcesses', () => {
    if (!socket.user) return;

    executeCommand('ps aux --sort=-%cpu | head -50', (result) => {
      socket.emit('processesData', {
        type: 'processes',
        data: result.output
      });
    });
  });

  // Obtener conexiones de red
  socket.on('getNetworkConnections', () => {
    if (!socket.user) return;

    executeCommand('netstat -tulpn', (result) => {
      socket.emit('networkData', {
        type: 'connections',
        data: result.output
      });
    });

    // También obtener interfaces de red
    executeCommand('ip addr show', (result) => {
      socket.emit('networkData', {
        type: 'interfaces',
        data: result.output
      });
    });

    // Estadísticas de red
    executeCommand('cat /proc/net/dev', (result) => {
      socket.emit('networkData', {
        type: 'stats',
        data: result.output
      });
    });
  });

  // Obtener archivos
  socket.on('getFiles', (data) => {
    if (!socket.user) return;

    const directory = data.directory || '/';
    const command = `ls -la "${directory}" 2>/dev/null || echo "Error: Cannot access directory"`;
    
    executeCommand(command, (result) => {
      socket.emit('filesData', {
        type: 'files',
        directory: directory,
        data: result.output
      });
    });
  });

  // Obtener información de seguridad
  socket.on('getSecurityInfo', () => {
    if (!socket.user) return;

    // Usuarios logueados
    executeCommand('who', (result) => {
      socket.emit('securityData', {
        type: 'logged_users',
        data: result.output
      });
    });

    // Últimos logins
    executeCommand('last -10', (result) => {
      socket.emit('securityData', {
        type: 'last_logins',
        data: result.output
      });
    });

    // Procesos con permisos especiales
    executeCommand('ps aux | grep -E "(sudo|su|root)" | grep -v grep', (result) => {
      socket.emit('securityData', {
        type: 'privileged_processes',
        data: result.output
      });
    });

    // Archivos con permisos SUID
    executeCommand('find /usr -perm -4000 2>/dev/null | head -20', (result) => {
      socket.emit('securityData', {
        type: 'suid_files',
        data: result.output
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
  console.log(`🚀 Servidor REAL ejecutándose en puerto ${PORT}`);
  console.log(`📱 Interfaz web disponible en: http://localhost:${PORT}`);
  console.log(`🔐 Usuarios de prueba:`);
  console.log(`   - admin / admin123 (Administrador)`);
  console.log(`   - student / student123 (Estudiante)`);
  console.log(`⚠️  ADVERTENCIA: Sin restricciones - Comandos reales del sistema`);
});

// Manejo de errores
process.on('uncaughtException', (err) => {
  console.error('Error no capturado:', err);
});

process.on('unhandledRejection', (reason, promise) => {
  console.error('Promesa rechazada no manejada:', reason);
});