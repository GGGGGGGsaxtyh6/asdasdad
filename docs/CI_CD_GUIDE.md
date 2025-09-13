# Guía de CI/CD Pipeline

## 📋 Tabla de Contenidos

- [Introducción](#introducción)
- [Arquitectura del Pipeline](#arquitectura-del-pipeline)
- [Configuración](#configuración)
- [Etapas del Pipeline](#etapas-del-pipeline)
- [Docker y Containerización](#docker-y-containerización)
- [Monitoreo y Alertas](#monitoreo-y-alertas)
- [Mejores Prácticas](#mejores-prácticas)
- [Troubleshooting](#troubleshooting)

## 🎯 Introducción

El pipeline de CI/CD del framework ECI Testing Automation está diseñado para proporcionar feedback rápido, confiable y automatizado sobre la calidad del código y los tests.

## 🏗️ Arquitectura del Pipeline

### Flujo General

```mermaid
graph TD
    A[Push/PR] --> B[Setup Environment]
    B --> C[Lint & Code Quality]
    C --> D[Unit Tests]
    D --> E[API Tests]
    E --> F[Contract Tests]
    F --> G[Integration Tests]
    G --> H[Performance Tests]
    H --> I[Generate Reports]
    I --> J[Notifications]
    J --> K{Main Branch?}
    K -->|Yes| L[Deploy to Production]
    K -->|No| M[Complete]
    L --> M
```

### Tecnologías Utilizadas

- **GitHub Actions**: Orquestación del pipeline
- **Docker**: Containerización
- **PostgreSQL**: Base de datos de testing
- **Redis**: Cache para tests
- **Pact Broker**: Gestión de contratos
- **Allure**: Reportes de testing
- **Slack**: Notificaciones

## ⚙️ Configuración

### Variables de Entorno Requeridas

```yaml
# GitHub Secrets
JIRA_URL: https://eci.atlassian.net
JIRA_USERNAME: your_username
JIRA_API_TOKEN: your_token
CONFLUENCE_URL: https://eci.atlassian.net
CONFLUENCE_USERNAME: your_username
CONFLUENCE_API_TOKEN: your_token
SLACK_WEBHOOK_URL: https://hooks.slack.com/...
DOCKER_USERNAME: your_docker_username
DOCKER_PASSWORD: your_docker_password
```

### Configuración del Repositorio

```yaml
# .github/workflows/testing-pipeline.yml
name: ECI Testing Automation Pipeline

on:
  push:
    branches: [ main, develop, feature/* ]
  pull_request:
    branches: [ main, develop ]
  schedule:
    - cron: '0 2 * * *'  # Daily at 2 AM
  workflow_dispatch:
    inputs:
      environment:
        description: 'Environment to test'
        required: true
        default: 'dev'
        type: choice
        options:
        - dev
        - staging
        - production
```

## 🔄 Etapas del Pipeline

### 1. Setup Environment

```yaml
setup:
  runs-on: ubuntu-latest
  steps:
    - name: Checkout code
      uses: actions/checkout@v4
      with:
        fetch-depth: 0

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Set up Node.js
      uses: actions/setup-node@v4
      with:
        node-version: '18'

    - name: Cache dependencies
      uses: actions/cache@v3
      with:
        path: ~/.cache/pip
        key: ${{ runner.os }}-pip-${{ hashFiles('**/requirements.txt') }}

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt
        npm ci
```

### 2. Lint & Code Quality

```yaml
lint:
  runs-on: ubuntu-latest
  needs: setup
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Run Black formatter check
      run: black --check --diff .

    - name: Run isort import sorting check
      run: isort --check-only --diff .

    - name: Run Flake8 linter
      run: flake8 . --count --select=E9,F63,F7,F82 --show-source --statistics

    - name: Run MyPy type checker
      run: mypy . --ignore-missing-imports

    - name: Run security check with bandit
      run: |
        pip install bandit
        bandit -r . -f json -o bandit-report.json || true
```

### 3. Unit Tests

```yaml
unit-tests:
  runs-on: ubuntu-latest
  needs: [setup, lint]
  strategy:
    matrix:
      python-version: ['3.11', '3.12']
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python ${{ matrix.python-version }}
      uses: actions/setup-python@v4
      with:
        python-version: ${{ matrix.python-version }}

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Run unit tests
      run: |
        pytest api_testing/ -v --cov=api_testing --cov-report=xml --cov-report=html --junitxml=unit-test-results.xml

    - name: Upload unit test results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: unit-test-results-${{ matrix.python-version }}
        path: |
          unit-test-results.xml
          htmlcov/
          coverage.xml
```

### 4. API Tests

```yaml
api-tests:
  runs-on: ubuntu-latest
  needs: [setup, lint]
  services:
    postgres:
      image: postgres:15
      env:
        POSTGRES_PASSWORD: postgres
        POSTGRES_DB: eci_test
      options: >-
        --health-cmd pg_isready
        --health-interval 10s
        --health-timeout 5s
        --health-retries 5
      ports:
        - 5432:5432

    redis:
      image: redis:7
      options: >-
        --health-cmd "redis-cli ping"
        --health-interval 10s
        --health-timeout 5s
        --health-retries 5
      ports:
        - 6379:6379

  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Wait for services
      run: |
        sleep 10
        python -c "import psycopg2; psycopg2.connect(host='localhost', port=5432, database='eci_test', user='postgres', password='postgres')"
        python -c "import redis; r = redis.Redis(host='localhost', port=6379); r.ping()"

    - name: Run API tests
      run: |
        pytest api_testing/ -v --html=api-test-results.html --self-contained-html --junitxml=api-test-results.xml
      env:
        DATABASE_URL: postgresql://postgres:postgres@localhost:5432/eci_test
        REDIS_URL: redis://localhost:6379/0

    - name: Upload API test results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: api-test-results
        path: |
          api-test-results.html
          api-test-results.xml
```

### 5. Contract Tests

```yaml
contract-tests:
  runs-on: ubuntu-latest
  needs: [setup, lint]
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Start Pact Broker (Mock)
      run: |
        docker run -d --name pact-broker -p 9292:9292 pactfoundation/pact-broker:latest
        sleep 10

    - name: Run contract tests
      run: |
        pytest contract_testing/ -v --html=contract-test-results.html --self-contained-html --junitxml=contract-test-results.xml

    - name: Upload contract test results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: contract-test-results
        path: |
          contract-test-results.html
          contract-test-results.xml

    - name: Cleanup Pact Broker
      if: always()
      run: |
        docker stop pact-broker
        docker rm pact-broker
```

### 6. Integration Tests

```yaml
integration-tests:
  runs-on: ubuntu-latest
  needs: [setup, lint]
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Run integration tests
      run: |
        pytest integration/ -v --html=integration-test-results.html --self-contained-html --junitxml=integration-test-results.xml

    - name: Upload integration test results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: integration-test-results
        path: |
          integration-test-results.html
          integration-test-results.xml
```

### 7. Performance Tests

```yaml
performance-tests:
  runs-on: ubuntu-latest
  needs: [setup, lint]
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Run performance tests
      run: |
        pytest api_testing/ -k "performance" -v --html=performance-test-results.html --self-contained-html --junitxml=performance-test-results.xml

    - name: Upload performance test results
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: performance-test-results
        path: |
          performance-test-results.html
          performance-test-results.xml
```

### 8. Generate Reports

```yaml
generate-reports:
  runs-on: ubuntu-latest
  needs: [setup, unit-tests, api-tests, contract-tests, integration-tests, performance-tests]
  if: always()
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Download all test results
      uses: actions/download-artifact@v3

    - name: Generate consolidated report
      run: |
        python scripts/generate_consolidated_report.py

    - name: Upload consolidated report
      uses: actions/upload-artifact@v3
      with:
        name: consolidated-test-report
        path: |
          reports/
          consolidated-report.html
```

### 9. Notifications

```yaml
notify:
  runs-on: ubuntu-latest
  needs: [setup, generate-reports]
  if: always()
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Python
      uses: actions/setup-python@v4
      with:
        python-version: '3.11'

    - name: Install dependencies
      run: |
        python -m pip install --upgrade pip
        pip install -r requirements.txt

    - name: Send Slack notification
      if: env.SLACK_WEBHOOK_URL
      run: |
        python scripts/send_slack_notification.py
      env:
        SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK_URL }}

    - name: Create Jira issues for failures
      if: failure()
      run: |
        python scripts/create_jira_issues.py
      env:
        JIRA_URL: ${{ secrets.JIRA_URL }}
        JIRA_USERNAME: ${{ secrets.JIRA_USERNAME }}
        JIRA_API_TOKEN: ${{ secrets.JIRA_API_TOKEN }}

    - name: Update Confluence documentation
      if: success()
      run: |
        python scripts/update_confluence_docs.py
      env:
        CONFLUENCE_URL: ${{ secrets.CONFLUENCE_URL }}
        CONFLUENCE_USERNAME: ${{ secrets.CONFLUENCE_USERNAME }}
        CONFLUENCE_API_TOKEN: ${{ secrets.CONFLUENCE_API_TOKEN }}
```

### 10. Deploy

```yaml
deploy:
  runs-on: ubuntu-latest
  needs: [setup, unit-tests, api-tests, contract-tests, integration-tests, performance-tests]
  if: github.ref == 'refs/heads/main' && github.event_name == 'push'
  environment: production
  steps:
    - name: Checkout code
      uses: actions/checkout@v4

    - name: Set up Docker Buildx
      uses: docker/setup-buildx-action@v3

    - name: Login to Docker Registry
      uses: docker/login@v3
      with:
        username: ${{ secrets.DOCKER_USERNAME }}
        password: ${{ secrets.DOCKER_PASSWORD }}

    - name: Build and push Docker image
      uses: docker/build-push-action@v5
      with:
        context: .
        push: true
        tags: |
          eci-testing:${{ github.sha }}
          eci-testing:latest
        cache-from: type=gha
        cache-to: type=gha,mode=max

    - name: Deploy to production
      run: |
        echo "Deploying to production environment"
        # Aquí se implementaría la lógica de despliegue real
```

## 🐳 Docker y Containerización

### Dockerfile

```dockerfile
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
```

### Docker Compose

```yaml
version: '3.8'

services:
  # Servicio principal de testing
  eci-testing:
    build:
      context: ../..
      dockerfile: ci-cd/docker/Dockerfile
    container_name: eci-testing
    environment:
      - ENVIRONMENT=dev
      - LOG_LEVEL=DEBUG
      - DATABASE_URL=postgresql://postgres:postgres@postgres:5432/eci_test
      - REDIS_URL=redis://redis:6379/0
    volumes:
      - ./reports:/app/reports
      - ./logs:/app/logs
      - ./test_data:/app/test_data
    depends_on:
      - postgres
      - redis
      - pact-broker
    networks:
      - eci-testing-network

  # Base de datos PostgreSQL
  postgres:
    image: postgres:15-alpine
    container_name: eci-postgres
    environment:
      - POSTGRES_DB=eci_test
      - POSTGRES_USER=postgres
      - POSTGRES_PASSWORD=postgres
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"
    networks:
      - eci-testing-network

  # Redis para cache
  redis:
    image: redis:7-alpine
    container_name: eci-redis
    ports:
      - "6379:6379"
    volumes:
      - redis_data:/data
    networks:
      - eci-testing-network

  # Pact Broker para contract testing
  pact-broker:
    image: pactfoundation/pact-broker:latest
    container_name: eci-pact-broker
    environment:
      - PACT_BROKER_DATABASE_URL=postgresql://postgres:postgres@postgres:5432/pact_broker
    ports:
      - "9292:9292"
    depends_on:
      - postgres
    networks:
      - eci-testing-network

volumes:
  postgres_data:
  redis_data:

networks:
  eci-testing-network:
    driver: bridge
```

## 📊 Monitoreo y Alertas

### Métricas del Pipeline

```yaml
# .github/workflows/metrics.yml
name: Pipeline Metrics

on:
  workflow_run:
    workflows: ["ECI Testing Automation Pipeline"]
    types: [completed]

jobs:
  collect-metrics:
    runs-on: ubuntu-latest
    steps:
      - name: Collect pipeline metrics
        run: |
          python scripts/collect_pipeline_metrics.py
```

### Alertas de Slack

```python
# scripts/send_slack_notification.py
import os
import json
import requests
from datetime import datetime

def send_slack_notification():
    webhook_url = os.getenv("SLACK_WEBHOOK_URL")
    if not webhook_url:
        return
    
    # Determinar estado del pipeline
    status = "success" if os.getenv("GITHUB_ACTIONS_RESULT") == "success" else "failure"
    
    # Crear mensaje
    message = {
        "text": f"ECI Testing Pipeline - {status.upper()}",
        "attachments": [
            {
                "color": "good" if status == "success" else "danger",
                "fields": [
                    {
                        "title": "Repository",
                        "value": os.getenv("GITHUB_REPOSITORY"),
                        "short": True
                    },
                    {
                        "title": "Branch",
                        "value": os.getenv("GITHUB_REF_NAME"),
                        "short": True
                    },
                    {
                        "title": "Commit",
                        "value": os.getenv("GITHUB_SHA")[:8],
                        "short": True
                    },
                    {
                        "title": "Author",
                        "value": os.getenv("GITHUB_ACTOR"),
                        "short": True
                    }
                ],
                "footer": "ECI Testing Automation",
                "ts": int(datetime.now().timestamp())
            }
        ]
    }
    
    # Enviar notificación
    response = requests.post(webhook_url, json=message)
    response.raise_for_status()

if __name__ == "__main__":
    send_slack_notification()
```

### Dashboard de Métricas

```python
# scripts/generate_metrics_dashboard.py
import json
import requests
from datetime import datetime, timedelta

def generate_metrics_dashboard():
    # Obtener métricas del pipeline
    metrics = {
        "pipeline_runs": get_pipeline_runs(),
        "test_results": get_test_results(),
        "performance_metrics": get_performance_metrics(),
        "deployment_frequency": get_deployment_frequency()
    }
    
    # Generar dashboard
    dashboard = {
        "title": "ECI Testing Pipeline Metrics",
        "generated_at": datetime.now().isoformat(),
        "metrics": metrics
    }
    
    # Guardar dashboard
    with open("reports/pipeline-metrics.json", "w") as f:
        json.dump(dashboard, f, indent=2)

def get_pipeline_runs():
    # Implementar lógica para obtener métricas del pipeline
    return {
        "total_runs": 100,
        "success_rate": 95.5,
        "average_duration": 15.5,
        "failure_rate": 4.5
    }

def get_test_results():
    # Implementar lógica para obtener resultados de tests
    return {
        "total_tests": 500,
        "passed_tests": 480,
        "failed_tests": 20,
        "coverage": 85.5
    }

def get_performance_metrics():
    # Implementar lógica para obtener métricas de rendimiento
    return {
        "average_response_time": 1.2,
        "p95_response_time": 2.5,
        "throughput": 1000
    }

def get_deployment_frequency():
    # Implementar lógica para obtener frecuencia de despliegues
    return {
        "daily_deployments": 5,
        "weekly_deployments": 35,
        "monthly_deployments": 150
    }

if __name__ == "__main__":
    generate_metrics_dashboard()
```

## 🏆 Mejores Prácticas

### 1. Organización del Pipeline

```yaml
# ✅ Bueno: Pipeline modular y reutilizable
name: ECI Testing Pipeline

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main, develop ]

jobs:
  setup:
    runs-on: ubuntu-latest
    outputs:
      environment: ${{ steps.set-env.outputs.environment }}
  
  lint:
    runs-on: ubuntu-latest
    needs: setup
    # ... configuración de linting
  
  test:
    runs-on: ubuntu-latest
    needs: [setup, lint]
    # ... configuración de tests

# ❌ Malo: Pipeline monolítico
name: Everything Pipeline

on: push

jobs:
  everything:
    runs-on: ubuntu-latest
    steps:
      - name: Do everything
        run: |
          # 100 líneas de comandos mezclados
```

### 2. Manejo de Dependencias

```yaml
# ✅ Bueno: Cache de dependencias
- name: Cache Python dependencies
  uses: actions/cache@v3
  with:
    path: ~/.cache/pip
    key: ${{ runner.os }}-pip-${{ hashFiles('**/requirements.txt') }}
    restore-keys: |
      ${{ runner.os }}-pip-

# ❌ Malo: Sin cache
- name: Install dependencies
  run: pip install -r requirements.txt
```

### 3. Manejo de Errores

```yaml
# ✅ Bueno: Manejo robusto de errores
- name: Run tests
  run: pytest
  continue-on-error: false

- name: Upload results
  uses: actions/upload-artifact@v3
  if: always()  # Siempre ejecutar, incluso si falla
  with:
    name: test-results
    path: reports/

# ❌ Malo: Sin manejo de errores
- name: Run tests
  run: pytest
- name: Upload results
  uses: actions/upload-artifact@v3
  with:
    name: test-results
    path: reports/
```

### 4. Seguridad

```yaml
# ✅ Bueno: Uso de secrets
- name: Deploy
  run: |
    echo "Deploying to ${{ secrets.ENVIRONMENT }}"
  env:
    API_KEY: ${{ secrets.API_KEY }}
    DATABASE_URL: ${{ secrets.DATABASE_URL }}

# ❌ Malo: Credenciales hardcodeadas
- name: Deploy
  run: |
    echo "Deploying to production"
  env:
    API_KEY: "hardcoded_key"
    DATABASE_URL: "postgresql://user:pass@host:5432/db"
```

### 5. Performance

```yaml
# ✅ Bueno: Ejecución paralela
strategy:
  matrix:
    python-version: ['3.11', '3.12']
    os: [ubuntu-latest, windows-latest]

# ❌ Malo: Ejecución secuencial
- name: Test Python 3.11
  run: pytest
- name: Test Python 3.12
  run: pytest
```

### 6. Notificaciones

```yaml
# ✅ Bueno: Notificaciones contextuales
- name: Notify on failure
  if: failure()
  run: |
    python scripts/notify_failure.py
  env:
    SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK_URL }}

- name: Notify on success
  if: success()
  run: |
    python scripts/notify_success.py
  env:
    SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK_URL }}

# ❌ Malo: Notificaciones genéricas
- name: Notify
  run: |
    echo "Pipeline completed"
```

## 🔧 Troubleshooting

### Problemas Comunes

#### 1. Pipeline Falla en Setup

```bash
# Verificar logs
gh run view --log

# Verificar variables de entorno
echo $GITHUB_ENV

# Verificar permisos
gh auth status
```

#### 2. Tests Fallan Inesperadamente

```yaml
# Agregar debugging
- name: Debug test environment
  run: |
    echo "Python version: $(python --version)"
    echo "Pip version: $(pip --version)"
    echo "Installed packages:"
    pip list
    echo "Environment variables:"
    env | grep -E "(API|DATABASE|REDIS)"
```

#### 3. Docker Build Falla

```bash
# Verificar Dockerfile
docker build -t eci-testing .

# Verificar contexto
docker build -t eci-testing . --no-cache

# Verificar logs detallados
docker build -t eci-testing . --progress=plain
```

#### 4. Artifacts No Se Suben

```yaml
# Verificar que el job anterior completó
- name: Upload results
  uses: actions/upload-artifact@v3
  if: always()  # Siempre ejecutar
  with:
    name: test-results
    path: reports/
    retention-days: 30
```

### Debugging Avanzado

```yaml
# Habilitar debugging detallado
- name: Debug environment
  run: |
    echo "GitHub context:"
    echo "Repository: ${{ github.repository }}"
    echo "Ref: ${{ github.ref }}"
    echo "SHA: ${{ github.sha }}"
    echo "Actor: ${{ github.actor }}"
    echo "Event: ${{ github.event_name }}"
    
    echo "Runner environment:"
    echo "OS: ${{ runner.os }}"
    echo "Arch: ${{ runner.arch }}"
    echo "Temp: ${{ runner.temp }}"
```

### Performance Issues

```yaml
# Optimizar para performance
- name: Run tests in parallel
  run: |
    pytest -n auto --dist=loadfile

- name: Use faster test discovery
  run: |
    pytest --collect-only -q

- name: Skip slow tests in PRs
  run: |
    if [ "${{ github.event_name }}" = "pull_request" ]; then
      pytest -m "not slow"
    else
      pytest
    fi
```

---

Esta guía te proporciona una base sólida para implementar y mantener un pipeline de CI/CD robusto. Para más información, consulta la documentación de GitHub Actions y las mejores prácticas de DevOps.