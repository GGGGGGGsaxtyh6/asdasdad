# 🚀 Stealer Web - Terminal Educativa

Una interfaz web moderna y segura para ejecutar comandos del sistema, diseñada específicamente para fines educativos en el ámbito de la seguridad informática.

## ⚠️ Advertencia Importante

**ESTA HERRAMIENTA ES SOLO PARA FINES EDUCATIVOS**

- ✅ Usar únicamente en entornos controlados y aislados
- ✅ Obtener autorización explícita antes de usar
- ✅ No usar en sistemas de producción
- ❌ No usar para actividades maliciosas
- ❌ No usar sin supervisión adecuada

## 🎯 Características

### 🔐 Seguridad
- Autenticación JWT robusta
- Validación de comandos peligrosos
- Lista blanca de comandos permitidos
- Registro de todas las actividades
- Protección contra inyección de comandos

### 🎨 Interfaz Moderna
- Terminal web interactiva con XTerm.js
- Tema oscuro profesional
- Fuente Fira Code para mejor legibilidad
- Diseño responsivo con Material-UI
- Animaciones suaves y transiciones

### ⚡ Funcionalidades
- Ejecución de comandos en tiempo real
- Historial de comandos con navegación
- Múltiples sesiones de terminal
- Autenticación persistente
- Gestión de procesos activos

## 🛠️ Tecnologías Utilizadas

### Backend
- **Node.js** - Runtime de JavaScript
- **Express.js** - Framework web
- **Socket.io** - Comunicación en tiempo real
- **JWT** - Autenticación segura
- **bcryptjs** - Encriptación de contraseñas
- **Helmet** - Seguridad HTTP

### Frontend
- **React** - Biblioteca de UI
- **TypeScript** - Tipado estático
- **Material-UI** - Componentes de interfaz
- **XTerm.js** - Terminal web
- **Socket.io Client** - Comunicación en tiempo real

## 📦 Instalación

### Prerrequisitos
- Node.js (v16 o superior)
- npm o yarn

### Pasos de Instalación

1. **Clonar el repositorio**
   ```bash
   git clone <repository-url>
   cd stealer-web
   ```

2. **Instalar dependencias del backend**
   ```bash
   cd server
   npm install
   ```

3. **Instalar dependencias del frontend**
   ```bash
   cd ../client
   npm install
   ```

4. **Configurar variables de entorno**
   ```bash
   cd ../server
   cp .env.example .env
   # Editar .env con tus configuraciones
   ```

## 🚀 Ejecución

### Desarrollo

1. **Iniciar el servidor backend**
   ```bash
   cd server
   npm run dev
   # o
   npm start
   ```

2. **Iniciar el cliente React** (en otra terminal)
   ```bash
   cd client
   npm start
   ```

3. **Acceder a la aplicación**
   - Abrir navegador en `http://localhost:3000`

### Producción

1. **Construir el cliente**
   ```bash
   cd client
   npm run build
   ```

2. **Iniciar el servidor**
   ```bash
   cd server
   npm start
   ```

## 👥 Usuarios por Defecto

| Usuario  | Contraseña  | Rol         |
|----------|-------------|-------------|
| admin    | admin123    | Administrador |
| student  | student123  | Estudiante    |

## 🎮 Uso

### Autenticación
1. Acceder a la aplicación web
2. Introducir credenciales de usuario
3. Hacer clic en "Iniciar Sesión"

### Terminal
1. Una vez autenticado, se abrirá la terminal
2. Escribir comandos y presionar Enter
3. Usar flechas ↑/↓ para navegar historial
4. Usar Ctrl+C para cancelar comandos

### Comandos Permitidos
- `ls`, `dir` - Listar archivos
- `pwd` - Directorio actual
- `cd` - Cambiar directorio
- `cat` - Mostrar contenido
- `echo` - Imprimir texto
- `whoami` - Usuario actual
- `ps` - Procesos activos
- `top`, `htop` - Monitor de sistema
- `df`, `du` - Espacio en disco
- `free` - Memoria disponible
- Y muchos más...

## 🔧 Configuración

### Variables de Entorno

Crear archivo `.env` en la carpeta `server/`:

```env
PORT=4000
CLIENT_URL=http://localhost:3000
JWT_SECRET=tu-secreto-jwt-super-seguro
NODE_ENV=development
```

### Personalización

- **Comandos permitidos**: Editar array `allowedCommands` en `server.js`
- **Comandos peligrosos**: Editar array `dangerousCommands` en `server.js`
- **Tema**: Modificar configuración en `App.tsx`

## 🛡️ Medidas de Seguridad

### Validación de Comandos
- Lista blanca de comandos permitidos
- Bloqueo de comandos peligrosos
- Sanitización de entrada

### Autenticación
- Tokens JWT con expiración
- Contraseñas encriptadas con bcrypt
- Verificación de tokens en cada request

### Comunicación
- WebSockets seguros
- Validación de origen (CORS)
- Headers de seguridad (Helmet)

## 📊 Monitoreo

### Logs
- Todas las conexiones se registran
- Comandos ejecutados se logean
- Errores y excepciones se capturan

### Sesiones
- Gestión de sesiones activas
- Limpieza automática al desconectar
- Control de procesos hijos

## 🚨 Solución de Problemas

### Error de Conexión
- Verificar que el servidor esté ejecutándose
- Comprobar puerto 4000 disponible
- Revisar configuración de CORS

### Error de Autenticación
- Verificar credenciales
- Comprobar token JWT válido
- Revisar configuración de JWT_SECRET

### Terminal No Responde
- Refrescar página web
- Verificar conexión WebSocket
- Revisar consola del navegador

## 📝 Licencia

Este proyecto es solo para fines educativos. No usar para actividades maliciosas.

## 🤝 Contribuciones

Las contribuciones son bienvenidas para mejorar la seguridad y funcionalidad educativa.

## 📞 Soporte

Para soporte técnico o preguntas sobre uso educativo, contactar al equipo de desarrollo.

---

**Recuerda: Esta herramienta es solo para fines educativos en entornos controlados y autorizados.**