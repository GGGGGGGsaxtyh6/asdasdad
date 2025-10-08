# 🚀 Despliegue en Vercel - Guía Completa

Esta guía te llevará paso a paso para desplegar Smurf Bank en Vercel con una base de datos PostgreSQL en la nube.

## Paso 1: Preparar la Base de Datos (Neon - Recomendado)

### ¿Por qué Neon?
- ✅ Gratis hasta 10GB
- ✅ PostgreSQL serverless
- ✅ Configuración en 2 minutos
- ✅ Compatible con Prisma

### Crear Base de Datos en Neon

1. **Ve a [neon.tech](https://neon.tech)**

2. **Crea una cuenta** (puedes usar GitHub)

3. **Crea un nuevo proyecto:**
   - Click en "New Project"
   - Nombre: `smurf-bank` (o el que prefieras)
   - Región: Selecciona la más cercana a tus usuarios
   - Click "Create Project"

4. **Copia la Connection String:**
   - Ve a "Connection Details"
   - Copia el "Connection string"
   - Debería verse así: `postgresql://user:password@ep-xxx.region.aws.neon.tech/neondb`
   - **¡GUARDA ESTO! Lo necesitarás después**

## Paso 2: Preparar el Código para GitHub

1. **Inicializa Git (si no lo has hecho):**
```bash
git init
git add .
git commit -m "Initial commit: Smurf Bank application"
```

2. **Crea un repositorio en GitHub:**
   - Ve a [github.com](https://github.com)
   - Click en "New repository"
   - Nombre: `smurf-bank`
   - Público o Privado (tu elección)
   - NO inicialices con README (ya tenemos uno)
   - Click "Create repository"

3. **Sube tu código:**
```bash
git branch -M main
git remote add origin https://github.com/TU-USUARIO/smurf-bank.git
git push -u origin main
```

## Paso 3: Desplegar en Vercel

1. **Ve a [vercel.com](https://vercel.com)**

2. **Crea una cuenta** (puedes usar GitHub)

3. **Importa tu proyecto:**
   - Click en "Add New..." → "Project"
   - Selecciona tu repositorio `smurf-bank`
   - Click "Import"

4. **Configura las Variables de Entorno:**

   Click en "Environment Variables" y agrega las siguientes:

   **DATABASE_URL:**
   ```
   postgresql://user:password@ep-xxx.region.aws.neon.tech/neondb?sslmode=require
   ```
   *(La connection string que copiaste de Neon)*

   **NEXTAUTH_URL:**
   ```
   https://tu-proyecto.vercel.app
   ```
   *(Vercel te dará esta URL después del despliegue. Por ahora, déjala en blanco o usa un placeholder)*

   **NEXTAUTH_SECRET:**
   ```
   TU_SECRET_SUPER_SEGURO_AQUI
   ```
   
   Para generar uno seguro, usa:
   ```bash
   openssl rand -base64 32
   ```
   
   O usa este comando en tu terminal:
   ```bash
   node -e "console.log(require('crypto').randomBytes(32).toString('base64'))"
   ```

5. **Deploy:**
   - Click "Deploy"
   - Espera 2-3 minutos mientras Vercel construye tu aplicación
   - ¡Vercel te dará una URL cuando termine!

## Paso 4: Configurar la Base de Datos

Después del primer despliegue:

1. **Copia tu URL de Vercel** (ejemplo: `https://smurf-bank-xyz.vercel.app`)

2. **Actualiza NEXTAUTH_URL:**
   - En Vercel, ve a tu proyecto
   - Settings → Environment Variables
   - Edita `NEXTAUTH_URL` con tu URL real
   - Click "Save"

3. **Inicializa la base de datos:**

   Opción A - Desde tu computadora local:
   ```bash
   # Descarga las variables de entorno
   npm install -g vercel
   vercel login
   vercel link
   vercel env pull .env.local
   
   # Ejecuta las migraciones
   npx prisma generate
   npx prisma db push
   ```

   Opción B - Desde Vercel CLI (más fácil):
   - Modifica temporalmente tu `.env` local con la DATABASE_URL de Neon
   - Ejecuta:
   ```bash
   npx prisma generate
   npx prisma db push
   ```

4. **Redeploy (si actualizaste variables):**
   - En Vercel, ve a "Deployments"
   - Click en los tres puntos del último deployment
   - Click "Redeploy"

## Paso 5: ¡Probar tu Aplicación!

1. **Visita tu URL de Vercel**

2. **Crea una cuenta:**
   - Click en "Get Started"
   - Completa el formulario de registro
   - Recibirás 10,000 Smurf de bienvenida

3. **Explora las funcionalidades:**
   - Dashboard con gráficos 3D
   - Transferencias con animaciones
   - Historial de transacciones
   - Notificaciones
   - Configuración de tema (claro/oscuro)

## Variables de Entorno - Resumen

| Variable | Ejemplo | Dónde obtenerla |
|----------|---------|-----------------|
| `DATABASE_URL` | `postgresql://user:pass@host/db` | Neon.tech → Connection Details |
| `NEXTAUTH_URL` | `https://tu-app.vercel.app` | Vercel → Deployment URL |
| `NEXTAUTH_SECRET` | `random_base64_string` | Genera con `openssl rand -base64 32` |

## Solución de Problemas

### Error: "Prisma Client did not initialize"

**Solución:**
```bash
# En tu proyecto local
npx prisma generate
git add .
git commit -m "Add prisma client"
git push
```

### Error: "Can't reach database server"

**Solución:**
- Verifica que tu DATABASE_URL sea correcta
- Asegúrate de incluir `?sslmode=require` al final
- Revisa que la database existe en Neon

### Error: "Invalid or missing credentials"

**Solución:**
- Regenera tu NEXTAUTH_SECRET
- Asegúrate de que todas las variables estén configuradas
- Redeploy en Vercel

### La página no carga estilos correctamente

**Solución:**
- Limpia la caché del navegador (Ctrl+Shift+R)
- Verifica que el build se completó sin errores en Vercel

## Verificación Final

✅ **Checklist de Despliegue:**

- [ ] Base de datos creada en Neon
- [ ] Código subido a GitHub
- [ ] Proyecto importado en Vercel
- [ ] Las 3 variables de entorno configuradas
- [ ] Prisma migrations ejecutadas
- [ ] La aplicación carga correctamente
- [ ] Puedes registrar una cuenta nueva
- [ ] Puedes hacer login
- [ ] Las transferencias funcionan
- [ ] El tema claro/oscuro funciona

## URLs Importantes

- **Tu aplicación:** `https://tu-proyecto.vercel.app`
- **Vercel Dashboard:** `https://vercel.com/dashboard`
- **Neon Dashboard:** `https://console.neon.tech`
- **GitHub Repo:** `https://github.com/tu-usuario/smurf-bank`

## Actualizaciones Futuras

Para actualizar tu aplicación:

```bash
# Haz cambios en el código
git add .
git commit -m "Descripción de cambios"
git push

# Vercel desplegará automáticamente
```

## Monitoreo

**En Vercel puedes ver:**
- Logs en tiempo real
- Métricas de rendimiento
- Errores de la aplicación
- Analytics de usuarios

**En Neon puedes ver:**
- Uso de la base de datos
- Queries ejecutados
- Conexiones activas

## Costos

**Gratis Forever con:**
- Vercel: Hobby Plan (proyectos personales)
- Neon: Free Tier (10GB storage)
- GitHub: Repositorios públicos ilimitados

## ¡Listo! 🎉

Tu aplicación Smurf Bank está ahora en vivo y accesible desde cualquier parte del mundo.

Comparte tu URL con amigos y prueba todas las funcionalidades premium que has creado.

---

**¿Necesitas ayuda?** Revisa los logs en Vercel Dashboard o la documentación oficial:
- [Vercel Docs](https://vercel.com/docs)
- [Neon Docs](https://neon.tech/docs)
- [Next.js Docs](https://nextjs.org/docs)
