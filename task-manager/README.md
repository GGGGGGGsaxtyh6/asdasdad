# 📋 Task Manager - Aplicación Web de Gestión de Tareas

Una aplicación web completa para la gestión de tareas personales, desarrollada con React, Node.js, Express y SQLite.

## 🚀 Características

### ✨ Funcionalidades Principales
- **Autenticación Segura**: Registro e inicio de sesión con JWT
- **Gestión de Tareas**: Crear, editar, eliminar y marcar tareas como completadas
- **Categorías**: Organizar tareas por categorías personalizables
- **Prioridades**: Sistema de prioridades (LOW, MEDIUM, HIGH, URGENT)
- **Fechas Límite**: Establecer y gestionar fechas de vencimiento
- **Dashboard**: Vista general con estadísticas de productividad
- **Interfaz Moderna**: Diseño responsive con Tailwind CSS

### 🔧 Tecnologías Utilizadas

#### Backend
- **Node.js** + **Express.js** - Servidor web
- **TypeScript** - Tipado estático
- **Prisma** - ORM para base de datos
- **SQLite** - Base de datos
- **JWT** - Autenticación
- **bcryptjs** - Encriptación de contraseñas
- **Joi** - Validación de datos
- **Helmet** - Seguridad HTTP
- **CORS** - Control de acceso

#### Frontend
- **React 18** - Framework de UI
- **TypeScript** - Tipado estático
- **Vite** - Herramienta de construcción
- **React Router** - Navegación
- **React Query** - Gestión de estado del servidor
- **React Hook Form** - Formularios
- **Tailwind CSS** - Estilos
- **Lucide React** - Iconos
- **React Hot Toast** - Notificaciones

## 📁 Estructura del Proyecto

```
task-manager/
├── backend/                 # API Backend
│   ├── src/
│   │   ├── controllers/     # Controladores
│   │   ├── middleware/      # Middleware personalizado
│   │   ├── routes/          # Rutas de la API
│   │   ├── services/        # Lógica de negocio
│   │   ├── utils/           # Utilidades
│   │   └── index.ts         # Punto de entrada
│   ├── prisma/
│   │   └── schema.prisma    # Esquema de base de datos
│   └── package.json
├── frontend/                # Aplicación React
│   ├── src/
│   │   ├── components/      # Componentes reutilizables
│   │   ├── pages/           # Páginas de la aplicación
│   │   ├── hooks/           # Hooks personalizados
│   │   ├── services/        # Servicios de API
│   │   ├── types/           # Tipos TypeScript
│   │   └── utils/           # Utilidades
│   └── package.json
└── README.md
```

## 🗄️ Esquema de Base de Datos

### Modelos Principales

#### User
- `id`: Identificador único
- `email`: Email único del usuario
- `password`: Contraseña encriptada
- `name`: Nombre del usuario
- `createdAt`, `updatedAt`: Timestamps

#### Task
- `id`: Identificador único
- `title`: Título de la tarea
- `description`: Descripción opcional
- `status`: Estado (PENDING, IN_PROGRESS, COMPLETED, CANCELLED)
- `priority`: Prioridad (LOW, MEDIUM, HIGH, URGENT)
- `dueDate`: Fecha límite opcional
- `userId`: Referencia al usuario
- `createdAt`, `updatedAt`: Timestamps

#### Category
- `id`: Identificador único
- `name`: Nombre de la categoría
- `color`: Color hexadecimal
- `userId`: Referencia al usuario
- `createdAt`: Timestamp

#### TaskCategory (Relación Many-to-Many)
- `taskId`: Referencia a la tarea
- `categoryId`: Referencia a la categoría

## 🚀 Instalación y Configuración

### Prerrequisitos
- Node.js (v18 o superior)
- npm o yarn

### 1. Clonar el Repositorio
```bash
git clone <repository-url>
cd task-manager
```

### 2. Configurar Backend
```bash
cd backend
npm install
```

#### Configurar Variables de Entorno
```bash
cp .env.example .env
```

Editar `.env`:
```env
DATABASE_URL="file:./dev.db"
JWT_SECRET="your-super-secret-jwt-key-here"
JWT_EXPIRES_IN="7d"
PORT=3001
NODE_ENV="development"
FRONTEND_URL="http://localhost:5173"
```

#### Configurar Base de Datos
```bash
npx prisma generate
npx prisma db push
```

#### Iniciar Backend
```bash
npm run dev
```

### 3. Configurar Frontend
```bash
cd frontend
npm install
```

#### Configurar Variables de Entorno
```bash
cp .env.example .env
```

Editar `.env`:
```env
VITE_API_URL=http://localhost:3001/api
```

#### Iniciar Frontend
```bash
npm run dev
```

## 📡 API Endpoints

### Autenticación
- `POST /api/auth/register` - Registro de usuario
- `POST /api/auth/login` - Inicio de sesión
- `GET /api/auth/me` - Obtener usuario actual

### Tareas
- `GET /api/tasks` - Obtener todas las tareas
- `GET /api/tasks/:id` - Obtener tarea por ID
- `POST /api/tasks` - Crear nueva tarea
- `PUT /api/tasks/:id` - Actualizar tarea
- `DELETE /api/tasks/:id` - Eliminar tarea
- `GET /api/tasks/stats/overview` - Estadísticas de tareas

### Categorías
- `GET /api/categories` - Obtener todas las categorías
- `GET /api/categories/:id` - Obtener categoría por ID
- `POST /api/categories` - Crear nueva categoría
- `PUT /api/categories/:id` - Actualizar categoría
- `DELETE /api/categories/:id` - Eliminar categoría

### Usuario
- `GET /api/users/profile` - Obtener perfil
- `PUT /api/users/profile` - Actualizar perfil
- `GET /api/users/dashboard` - Dashboard con estadísticas

## 🧪 Pruebas

### Ejecutar Pruebas de API
```bash
cd task-manager
node test-api.js
```

Las pruebas verifican:
- ✅ Health Check
- ✅ Registro e inicio de sesión
- ✅ Autenticación JWT
- ✅ CRUD de categorías
- ✅ CRUD de tareas
- ✅ Dashboard y estadísticas
- ✅ Gestión de perfil

## 🌐 Despliegue

### Acceso en Vivo
La aplicación está disponible en: **https://3ce02b1cb4c8.ngrok-free.app**

### Servicios en Ejecución
- **Backend**: http://localhost:3001
- **Frontend**: http://localhost:5173
- **Ngrok**: https://3ce02b1cb4c8.ngrok-free.app

## 📱 Uso de la Aplicación

### 1. Registro/Inicio de Sesión
- Accede a la aplicación
- Regístrate con nombre, email y contraseña
- O inicia sesión si ya tienes una cuenta

### 2. Dashboard
- Ve un resumen de tus tareas
- Estadísticas de productividad
- Tareas recientes

### 3. Gestión de Tareas
- Crear nuevas tareas con título, descripción y fecha límite
- Asignar prioridades y categorías
- Marcar como completadas
- Editar o eliminar tareas

### 4. Categorías
- Crear categorías personalizadas
- Asignar colores a las categorías
- Organizar tareas por categorías

## 🔒 Seguridad

- **Autenticación JWT**: Tokens seguros con expiración
- **Encriptación**: Contraseñas hasheadas con bcrypt
- **Validación**: Validación de datos en frontend y backend
- **CORS**: Configuración de acceso cruzado
- **Helmet**: Headers de seguridad HTTP
- **Rate Limiting**: Límite de requests por IP

## 🚀 Características Futuras

- [ ] Notificaciones push
- [ ] Sincronización en tiempo real
- [ ] Exportación de tareas
- [ ] Integración con calendarios
- [ ] Colaboración en equipo
- [ ] Aplicación móvil

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.

## 👥 Autores

- **Task Manager Team** - Desarrollo completo

## 📞 Soporte

Si tienes preguntas o necesitas ayuda, por favor:
1. Revisa la documentación
2. Verifica las issues existentes
3. Crea una nueva issue si es necesario

---

**¡Gracias por usar Task Manager! 🎉**