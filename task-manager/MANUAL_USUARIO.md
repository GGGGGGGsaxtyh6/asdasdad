# 📖 Manual de Usuario - Task Manager

## 🎯 Introducción

Task Manager es una aplicación web diseñada para ayudarte a organizar y gestionar tus tareas diarias de manera eficiente. Con una interfaz intuitiva y moderna, podrás crear, editar y hacer seguimiento de todas tus actividades.

## 🚀 Acceso a la Aplicación

### URL de Acceso
**https://3ce02b1cb4c8.ngrok-free.app**

### Requisitos del Sistema
- Navegador web moderno (Chrome, Firefox, Safari, Edge)
- Conexión a internet
- JavaScript habilitado

## 👤 Registro e Inicio de Sesión

### Crear una Nueva Cuenta

1. **Accede a la aplicación** usando la URL proporcionada
2. **Haz clic en "create a new account"** en la pantalla de inicio de sesión
3. **Completa el formulario** con:
   - **Nombre completo**: Tu nombre real
   - **Email**: Una dirección de email válida
   - **Contraseña**: Mínimo 6 caracteres
   - **Confirmar contraseña**: Repite tu contraseña
4. **Haz clic en "Create account"**

### Iniciar Sesión

1. **Ingresa tu email** y **contraseña**
2. **Haz clic en "Sign in"**
3. Serás redirigido automáticamente al dashboard

## 🏠 Dashboard Principal

El dashboard es tu centro de control donde puedes ver:

### 📊 Estadísticas de Productividad
- **Total de Tareas**: Número total de tareas creadas
- **Completadas**: Tareas finalizadas
- **En Progreso**: Tareas actualmente en desarrollo
- **Vencidas**: Tareas que han pasado su fecha límite

### 📈 Tasa de Completitud
- Gráfico visual que muestra el porcentaje de tareas completadas
- Actualización en tiempo real

### 📋 Actividad Reciente
- Lista de las 5 tareas más recientes
- Información rápida sobre estado y prioridad

## ✅ Gestión de Tareas

### Crear una Nueva Tarea

1. **Navega a la sección "Tasks"** desde el menú lateral
2. **Haz clic en "New Task"** (botón azul con ícono +)
3. **Completa el formulario**:
   - **Título**: Nombre descriptivo de la tarea
   - **Descripción**: Detalles adicionales (opcional)
   - **Prioridad**: Selecciona entre LOW, MEDIUM, HIGH, URGENT
   - **Fecha Límite**: Establece cuándo debe completarse (opcional)
   - **Categorías**: Selecciona una o más categorías (opcional)
4. **Haz clic en "Create Task"**

### Editar una Tarea

1. **Encuentra la tarea** en la lista
2. **Haz clic en el ícono de edición** (lápiz)
3. **Modifica los campos** que necesites cambiar
4. **Guarda los cambios**

### Cambiar Estado de una Tarea

Los estados disponibles son:
- **PENDING**: Tarea pendiente (estado inicial)
- **IN_PROGRESS**: Tarea en progreso
- **COMPLETED**: Tarea completada
- **CANCELLED**: Tarea cancelada

### Eliminar una Tarea

1. **Haz clic en el ícono de eliminar** (papelera)
2. **Confirma la eliminación** en el diálogo

### Filtros y Búsqueda

- **Por Estado**: Filtra tareas por su estado actual
- **Por Prioridad**: Filtra por nivel de prioridad
- **Por Categoría**: Filtra por categoría específica
- **Búsqueda**: Busca por título o descripción

## 🏷️ Gestión de Categorías

### Crear una Categoría

1. **Navega a "Categories"** desde el menú lateral
2. **Haz clic en "New Category"**
3. **Completa el formulario**:
   - **Nombre**: Nombre descriptivo de la categoría
   - **Color**: Selecciona un color distintivo
4. **Guarda la categoría**

### Editar una Categoría

1. **Encuentra la categoría** en la lista
2. **Haz clic en el ícono de edición**
3. **Modifica el nombre o color**
4. **Guarda los cambios**

### Eliminar una Categoría

⚠️ **Importante**: Solo puedes eliminar categorías que no tengan tareas asignadas.

1. **Asegúrate de que la categoría esté vacía**
2. **Haz clic en el ícono de eliminar**
3. **Confirma la eliminación**

## 👤 Perfil de Usuario

### Ver Perfil

1. **Haz clic en "Profile"** en el menú lateral
2. **Ve tu información**:
   - Nombre
   - Email
   - Fecha de registro

### Editar Perfil

1. **En la página de perfil, haz clic en "Edit"**
2. **Modifica**:
   - Nombre
   - Email (debe ser único)
3. **Guarda los cambios**

## 🎨 Interfaz y Navegación

### Menú Lateral

- **Dashboard**: Vista principal con estadísticas
- **Tasks**: Gestión de tareas
- **Categories**: Gestión de categorías
- **Profile**: Información del usuario

### Barra Superior

- **Título de la página actual**
- **Botones de acción** (ej: "New Task")
- **Información del usuario** y botón de logout

### Colores y Estados

#### Prioridades
- 🔴 **URGENT**: Rojo - Requiere atención inmediata
- 🟠 **HIGH**: Naranja - Alta prioridad
- 🟡 **MEDIUM**: Amarillo - Prioridad media
- 🟢 **LOW**: Verde - Baja prioridad

#### Estados de Tareas
- 🟡 **PENDING**: Amarillo - Pendiente
- 🔵 **IN_PROGRESS**: Azul - En progreso
- 🟢 **COMPLETED**: Verde - Completada
- 🔴 **CANCELLED**: Rojo - Cancelada

## 🔔 Notificaciones

La aplicación muestra notificaciones para:
- ✅ **Éxito**: Operaciones completadas correctamente
- ❌ **Error**: Problemas o errores
- ℹ️ **Información**: Mensajes informativos

## 📱 Responsive Design

La aplicación se adapta a diferentes tamaños de pantalla:
- **Desktop**: Experiencia completa con menú lateral
- **Tablet**: Interfaz optimizada para pantallas medianas
- **Mobile**: Navegación adaptada para dispositivos móviles

## 🚪 Cerrar Sesión

1. **Haz clic en el ícono de logout** (esquina superior derecha)
2. **Confirma** si se solicita
3. **Serás redirigido** a la pantalla de inicio de sesión

## ❓ Preguntas Frecuentes

### ¿Puedo recuperar mi contraseña?
Actualmente no hay función de recuperación de contraseña. Contacta al administrador si necesitas ayuda.

### ¿Puedo exportar mis tareas?
Esta funcionalidad estará disponible en futuras versiones.

### ¿Mis datos están seguros?
Sí, todas las contraseñas están encriptadas y la aplicación usa autenticación JWT segura.

### ¿Puedo usar la aplicación sin conexión a internet?
No, la aplicación requiere conexión a internet para funcionar.

### ¿Hay límite en el número de tareas?
No hay límite en el número de tareas que puedes crear.

## 🆘 Soporte Técnico

Si experimentas problemas:

1. **Verifica tu conexión a internet**
2. **Actualiza la página** (F5 o Ctrl+R)
3. **Limpia la caché del navegador**
4. **Intenta con otro navegador**

### Información para Reportar Problemas

Cuando reportes un problema, incluye:
- **Navegador y versión** que estás usando
- **Descripción detallada** del problema
- **Pasos para reproducir** el error
- **Captura de pantalla** si es posible

## 🎉 ¡Disfruta usando Task Manager!

Con estas instrucciones, deberías poder usar todas las funcionalidades de Task Manager de manera efectiva. La aplicación está diseñada para ser intuitiva y fácil de usar, pero si tienes alguna pregunta, no dudes en consultar este manual.

---

**¡Que tengas una experiencia productiva con Task Manager! 🚀**