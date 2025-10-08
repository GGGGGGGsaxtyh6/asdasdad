# 🏦 Smurf Bank - Banco Digital Premium

Aplicación bancaria web de lujo con moneda interna **Smurf**. Experiencia visual exclusiva con elementos 3D de euros de alta fidelidad, interactivos y con animaciones premium.

## 🌐 Acceso a la Aplicación

**URL Pública:** https://97cad18477b4.ngrok-free.app

## ✨ Características

### 🎨 Diseño Premium
- **Identidad visual**: Lujo moderno, minimalismo cálido, vidrio esmerilado
- **Paleta de colores**: Tonos oscuros profundos + acentos metálicos dorado/platino + marca Smurf
- **Tipografía elegante**: Pesos variables, numerales tabulares
- **Animaciones pulidas**: 180-240ms timing, cubic bezier suaves

### 💰 Funcionalidades Core
- ✅ **Registro e inicio de sesión** con validaciones en tiempo real
- ✅ **Dashboard privado** con balance Smurf y estadísticas
- ✅ **Transferencias** con animación 3D de euros
- ✅ **Historial completo** con filtros y exportación CSV
- ✅ **Centro de notificaciones** en tiempo real
- ✅ **Ajustes personalizados** (perfil, seguridad, tema, accesibilidad)

### 🎭 Elementos 3D
- **Monedas de euro** con materiales PBR (metallic/roughness), canto estriado
- **Billetes de euro** con hologramas animados y marcas de agua
- **Hero 3D** en landing con esfera Smurf y euros orbitando
- **Interactividad**: Hover parallax, tilt, micro-rotación, specular highlights
- **Optimización**: LOD (3 niveles), frustum culling, instancing

### ♿ Accesibilidad
- ✅ Navegación completa por teclado
- ✅ ARIA labels y roles semánticos
- ✅ Contraste AA/AAA en UI crítica
- ✅ Reducción de movimiento (prefers-reduced-motion)
- ✅ Focus visible y orden lógico

## 🚀 Tecnologías

- **Framework**: Next.js 14 (React 18, TypeScript)
- **Estilos**: Tailwind CSS con sistema de diseño custom
- **3D**: Three.js + React Three Fiber + Drei
- **Animaciones**: Framer Motion
- **Backend**: API Routes (Next.js) + JSON file database
- **Auth**: JWT + bcrypt
- **Validación**: Zod + React Hook Form

## 📦 Instalación Local

```bash
# Clonar e instalar
npm install

# Variables de entorno
cp .env.local.example .env.local

# Desarrollo
npm run dev

# Producción
npm run build
npm start
```

## 🎯 Flujos Principales

### 1. Registro
- Validaciones en vivo (fortaleza contraseña, formato email)
- Indicadores visuales de progreso
- Balance inicial de 1,000 Smurf

### 2. Dashboard
- Balance con gráfico de cambios (7 días)
- Estadísticas: recibido, enviado, transacciones
- Actividad reciente con detalles

### 3. Transferencias
- Búsqueda de destinatarios en tiempo real
- Confirmación con desglose completo
- Animación 3D de envío exitoso
- Validación de saldo

### 4. Historial
- Filtros por tipo y estado
- Exportación a CSV
- Vista detallada de transacciones

### 5. Notificaciones
- Centro unificado
- Marcar como leído/no leído
- Agrupación por tipo

### 6. Ajustes
- Perfil (nombre, avatar)
- Tema (claro/oscuro/auto)
- Idioma (ES/EN)
- Accesibilidad (reducir movimiento)
- Seguridad (2FA toggle)

## 🎨 Sistema de Diseño

### Colores
- **Dark**: #0a0a0b → #35353a
- **Smurf**: #0084ff (primario)
- **Gold**: #d4a574 (acentos premium)
- **Platinum**: #f8fafc → #0f172a

### Componentes
- `Button`: primary, secondary, ghost, danger
- `Input`: con validación inline, iconos, password toggle
- `Card`: default, glass, premium
- `Navbar`: responsive, notificaciones, perfil

### Animaciones
- `float`: objetos flotantes 6s
- `glow`: brillos pulsantes 2s
- `shimmer`: efectos metálicos 2.5s
- `coin-flip`: rotación monedas 1.5s
- `slide-up`: entradas suaves 0.4s

## 📊 Estructura del Proyecto

```
/workspace
├── app/                    # Pages & API Routes
│   ├── api/               # Backend endpoints
│   ├── (pages)/           # Frontend routes
│   ├── globals.css        # Estilos globales
│   └── layout.tsx         # Layout principal
├── components/
│   ├── 3d/               # Elementos Three.js
│   ├── layout/           # Navbar, Footer
│   └── ui/               # Button, Input, Card
├── lib/
│   ├── auth.ts           # JWT & bcrypt
│   ├── database.ts       # File-based DB
│   └── utils.ts          # Helpers
├── hooks/
│   └── useAccessibility.ts
├── data/                  # JSON databases
│   ├── users.json
│   ├── transactions.json
│   └── notifications.json
└── public/               # Assets estáticos
```

## 🔒 Seguridad

- Contraseñas hasheadas con bcrypt
- JWT para autenticación
- Validación server-side
- Sanitización de inputs
- HTTPS en producción (ngrok)

## 🌐 Despliegue

La aplicación está desplegada en:
**https://97cad18477b4.ngrok-free.app**

Ngrok expone el puerto 3000 con SSL/TLS automático.

## 📝 Licencia

Proyecto creado para demostración de capacidades de desarrollo fullstack con diseño premium.

---

**Desarrollado con ❤️ usando Next.js, Three.js y Tailwind CSS**
