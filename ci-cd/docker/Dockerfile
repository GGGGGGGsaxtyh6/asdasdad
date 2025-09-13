# Dockerfile para el framework de testing ECI
FROM python:3.11-slim

# Metadatos
LABEL maintainer="ECI - CTO Team"
LABEL description="ECI Testing Automation Framework"
LABEL version="1.0.0"

# Variables de entorno
ENV PYTHONUNBUFFERED=1
ENV PYTHONDONTWRITEBYTECODE=1
ENV PIP_NO_CACHE_DIR=1
ENV PIP_DISABLE_PIP_VERSION_CHECK=1

# Instalar dependencias del sistema
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    git \
    build-essential \
    libpq-dev \
    && rm -rf /var/lib/apt/lists/*

# Crear usuario no-root
RUN groupadd -r eci && useradd -r -g eci eci

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos de dependencias
COPY requirements.txt package.json package-lock.json ./

# Instalar dependencias de Python
RUN pip install --no-cache-dir -r requirements.txt

# Instalar dependencias de Node.js
RUN curl -fsSL https://deb.nodesource.com/setup_18.x | bash - && \
    apt-get install -y nodejs && \
    npm ci --only=production

# Copiar código fuente
COPY . .

# Crear directorios necesarios
RUN mkdir -p reports logs test_data fixtures

# Configurar permisos
RUN chown -R eci:eci /app
USER eci

# Variables de entorno por defecto
ENV ENVIRONMENT=production
ENV LOG_LEVEL=INFO
ENV REPORT_OUTPUT_DIR=/app/reports

# Exponer puerto para servicios auxiliares
EXPOSE 8000

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD python -c "import requests; requests.get('http://localhost:8000/health', timeout=5)" || exit 1

# Comando por defecto
CMD ["python", "-m", "pytest", "--html=reports/test-report.html", "--self-contained-html"]