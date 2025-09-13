# ECI - CTO Testing Automation Framework

## 📋 Tabla de Contenidos

- [Introducción](#introducción)
- [Arquitectura](#arquitectura)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Uso](#uso)
- [API Testing](#api-testing)
- [Contract Testing](#contract-testing)
- [Integración con Jira y Confluence](#integración-con-jira-y-confluence)
- [CI/CD Pipeline](#cicd-pipeline)
- [Reporting y Métricas](#reporting-y-métricas)
- [Mejores Prácticas](#mejores-prácticas)
- [Troubleshooting](#troubleshooting)
- [Contribución](#contribución)

## 🎯 Introducción

El **ECI Testing Automation Framework** es una solución completa para automatización de pruebas desarrollada específicamente para el área ECI - CTO. Este framework proporciona herramientas robustas para testing de APIs, contratos, integración y rendimiento, con integración completa a herramientas de gestión Agile.

### Características Principales

- ✅ **Testing de APIs**: REST y GraphQL con validación completa
- ✅ **Contract Testing**: Pact para testing de contratos entre servicios
- ✅ **Integración Agile**: Jira y Confluence para gestión y documentación
- ✅ **CI/CD**: Pipelines automatizados con GitHub Actions
- ✅ **Reporting**: Dashboards interactivos y métricas de calidad
- ✅ **Escalabilidad**: Framework modular y extensible

## 🏗️ Arquitectura

```
testing-automation/
├── api_testing/           # Testing de APIs REST y GraphQL
│   ├── client.py         # Cliente HTTP robusto
│   ├── validators.py     # Validadores de esquemas y contratos
│   ├── test_base.py      # Clases base para tests
│   ├── test_users.py     # Tests de API de usuarios
│   └── test_graphql.py   # Tests de GraphQL
├── contract_testing/      # Testing de contratos
│   ├── pact_consumer.py  # Tests de consumidor Pact
│   ├── pact_provider.py  # Tests de provider Pact
│   └── pact_broker.py    # Integración con Pact Broker
├── integration/           # Integración con herramientas
│   ├── jira_integration.py    # Integración con Jira
│   └── confluence_integration.py # Integración con Confluence
├── reporting/             # Reportes y métricas
│   ├── metrics_collector.py    # Colector de métricas
│   └── dashboard_generator.py  # Generador de dashboards
├── ci-cd/                # Pipelines de CI/CD
│   ├── docker/           # Configuración Docker
│   └── scripts/          # Scripts auxiliares
└── docs/                 # Documentación
```

## 🚀 Instalación

### Prerequisitos

- Python 3.11+
- Node.js 18+
- Docker
- Git

### Instalación Local

```bash
# Clonar el repositorio
git clone <repository-url>
cd testing-automation

# Crear entorno virtual
python -m venv venv
source venv/bin/activate  # En Windows: venv\Scripts\activate

# Instalar dependencias Python
pip install -r requirements.txt

# Instalar dependencias Node.js
npm install

# Configurar variables de entorno
cp .env.example .env
# Editar .env con tus configuraciones
```

### Instalación con Docker

```bash
# Construir imagen
docker build -t eci-testing .

# Ejecutar contenedor
docker run --rm eci-testing
```

### Instalación con Docker Compose

```bash
# Ejecutar todos los servicios
docker-compose -f ci-cd/docker/docker-compose.yml up -d

# Ver logs
docker-compose -f ci-cd/docker/docker-compose.yml logs -f
```

## ⚙️ Configuración

### Variables de Entorno

Configura las siguientes variables en tu archivo `.env`:

```bash
# Environment Configuration
ENVIRONMENT=dev
DEBUG=true
LOG_LEVEL=INFO

# API Configuration
API_BASE_URL=https://api.eci-dev.com
API_VERSION=v1
API_TIMEOUT=30
API_RETRIES=3

# Authentication
API_KEY=your_api_key_here
JWT_SECRET=your_jwt_secret_here

# Database
DATABASE_URL=postgresql://user:password@localhost:5432/eci_test
REDIS_URL=redis://localhost:6379/0

# Jira Configuration
JIRA_URL=https://eci.atlassian.net
JIRA_USERNAME=your_jira_username
JIRA_API_TOKEN=your_jira_api_token
JIRA_PROJECT_KEY=ECI

# Confluence Configuration
CONFLUENCE_URL=https://eci.atlassian.net
CONFLUENCE_USERNAME=your_confluence_username
CONFLUENCE_API_TOKEN=your_confluence_api_token
CONFLUENCE_SPACE_KEY=ECI

# Contract Testing
PACT_BROKER_URL=https://pact-broker.eci.com
PACT_BROKER_TOKEN=your_pact_broker_token
```

### Configuración de Jira

1. Crear un token de API en Jira
2. Configurar los campos personalizados necesarios
3. Crear tipos de issue para "Test Execution" y "Test Plan"

### Configuración de Confluence

1. Crear un token de API en Confluence
2. Configurar el espacio de trabajo
3. Crear plantillas para documentación de testing

## 🧪 Uso

### Ejecutar Tests

```bash
# Todos los tests
pytest

# Tests específicos
pytest api_testing/
pytest contract_testing/
pytest integration/

# Tests con reportes
pytest --html=reports/report.html --self-contained-html

# Tests con cobertura
pytest --cov=api_testing --cov-report=html
```

### Ejecutar Tests de API

```bash
# Tests de usuarios
pytest api_testing/test_users.py -v

# Tests de GraphQL
pytest api_testing/test_graphql.py -v

# Tests con marcadores
pytest -m smoke  # Solo tests de smoke
pytest -m regression  # Solo tests de regresión
```

### Ejecutar Contract Tests

```bash
# Tests de consumidor
pytest contract_testing/pact_consumer.py -v

# Tests de provider
pytest contract_testing/pact_provider.py -v

# Verificar contratos en broker
python -m contract_testing.pact_broker verify
```

## 🔌 API Testing

### Cliente HTTP

```python
from api_testing.client import APIClient

# Crear cliente
client = APIClient(base_url="https://api.eci.com")

# GET request
response = client.get("/users/1")
print(f"Status: {response.status_code}")
print(f"Data: {response.data}")

# POST request
user_data = {"name": "Juan", "email": "juan@eci.com"}
response = client.post("/users", json_data=user_data)
```

### Validadores

```python
from api_testing.validators import SchemaValidator, APIResponseValidator

# Validar esquema JSON
schema = {
    "type": "object",
    "properties": {
        "id": {"type": "integer"},
        "name": {"type": "string"}
    }
}

validator = SchemaValidator(schema)
result = validator.validate(response.data)

if not result.is_valid:
    print(f"Errores: {result.errors}")
```

### Tests Base

```python
from api_testing.test_base import APITestBase, smoke_test, api_test

class TestMyAPI(APITestBase):
    @smoke_test
    @api_test
    def test_get_user(self):
        response = self.client.get("/users/1")
        self.assert_status_code(response, 200)
        self.assert_contains(response, "id")
        self.assert_json_schema(response, USER_SCHEMA)
```

## 🤝 Contract Testing

### Consumer Tests

```python
from contract_testing.pact_consumer import TestUserServiceConsumer

class TestUserServiceConsumer(TestUserServiceConsumer):
    def test_get_user_contract(self):
        # Definir interacción
        self.add_interaction(
            description="Obtener usuario por ID",
            state="usuario existe con ID 1",
            request={
                "method": "GET",
                "path": "/api/v1/users/1",
                "headers": {"Authorization": "Bearer valid_token"}
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(1),
                    "name": Like("Juan Pérez"),
                    "email": Like("juan@eci.com")
                }
            }
        )
        
        # Ejecutar test
        response = self.client.get("/users/1")
        self.assert_status_code(response, 200)
        
        # Verificar contrato
        self.verify_contract()
```

### Provider Tests

```python
from contract_testing.pact_provider import TestUserServiceProvider

class TestUserServiceProvider(TestUserServiceProvider):
    def test_user_service_contracts(self):
        self.verify_contracts()
```

## 📊 Integración con Jira y Confluence

### Reportar a Jira

```python
from integration.jira_integration import JiraReporter, TestExecution, TestResult

# Crear ejecución de tests
execution = TestExecution(
    execution_id="exec_123",
    test_suite="API Tests",
    environment="dev",
    start_time=datetime.now(),
    end_time=datetime.now(),
    total_tests=10,
    passed_tests=8,
    failed_tests=2,
    skipped_tests=0,
    results=[
        TestResult("test_user_creation", "PASSED", 1.5, "api", "dev"),
        TestResult("test_user_validation", "FAILED", 2.0, "api", "dev", "Validation error")
    ]
)

# Reportar a Jira
reporter = JiraReporter()
result = reporter.report_test_execution(execution)
print(f"Execution issue: {result['execution_issue']}")
```

### Documentar en Confluence

```python
from integration.confluence_integration import ConfluenceReporter, TestDocumentation

# Crear documentación
doc = TestDocumentation(
    title="API Testing Guide",
    content="Guía completa para testing de APIs...",
    test_type="api",
    environment="dev",
    tags=["testing", "api", "guide"]
)

# Crear en Confluence
reporter = ConfluenceReporter()
page_id = reporter.create_test_documentation(doc)
print(f"Página creada: {page_id}")
```

## 🔄 CI/CD Pipeline

### GitHub Actions

El pipeline se ejecuta automáticamente en:

- **Push** a ramas `main` y `develop`
- **Pull Requests** hacia `main` y `develop`
- **Schedule** diario a las 2:00 AM
- **Manual** con parámetros personalizables

### Etapas del Pipeline

1. **Setup**: Preparación del entorno
2. **Lint**: Validación de código
3. **Unit Tests**: Tests unitarios
4. **API Tests**: Tests de API
5. **Contract Tests**: Tests de contratos
6. **Integration Tests**: Tests de integración
7. **Performance Tests**: Tests de rendimiento
8. **Generate Reports**: Generación de reportes
9. **Notify**: Notificaciones
10. **Deploy**: Despliegue (solo en main)

### Ejecutar Pipeline Manualmente

```bash
# Ejecutar con parámetros específicos
gh workflow run testing-pipeline.yml -f environment=staging -f test_suite=api
```

## 📈 Reporting y Métricas

### Generar Reportes

```python
from reporting.metrics_collector import metrics_collector
from reporting.dashboard_generator import dashboard_generator

# Iniciar recolección de métricas
metrics_collector.start_collection()

# Ejecutar tests...
# (las métricas se recopilan automáticamente)

# Detener recolección
metrics_collector.stop_collection()

# Generar dashboards
dashboard_generator.generate_all_dashboards("reports/dashboards/")
```

### Dashboards Disponibles

- **Overview**: Resumen general de todos los tests
- **Performance**: Métricas de rendimiento
- **Suite-specific**: Dashboards por suite de tests
- **Comparison**: Comparación entre suites

### Métricas Recopiladas

- Tasa de éxito de tests
- Tiempo de ejecución
- Uso de recursos (CPU, memoria)
- Tests fallidos y errores
- Tendencias de rendimiento

## 🏆 Mejores Prácticas

### Organización de Tests

1. **Estructura clara**: Separar tests por funcionalidad
2. **Naming conventions**: Nombres descriptivos y consistentes
3. **Test data**: Usar fixtures y factories
4. **Cleanup**: Limpiar datos después de cada test

### API Testing

1. **Validación completa**: Esquemas, headers, status codes
2. **Test data**: Datos realistas y variados
3. **Error handling**: Probar casos de error
4. **Performance**: Validar tiempos de respuesta

### Contract Testing

1. **Contratos claros**: Definir expectativas precisas
2. **Versionado**: Manejar versiones de contratos
3. **Broker**: Usar Pact Broker para gestión
4. **Documentación**: Documentar cambios en contratos

### CI/CD

1. **Fast feedback**: Tests rápidos en PRs
2. **Parallel execution**: Ejecutar tests en paralelo
3. **Artifacts**: Guardar reportes y logs
4. **Notifications**: Notificar fallos inmediatamente

### Reporting

1. **Métricas relevantes**: Enfocarse en métricas importantes
2. **Dashboards claros**: Visualizaciones comprensibles
3. **Tendencias**: Monitorear tendencias a largo plazo
4. **Acciones**: Definir acciones basadas en métricas

## 🔧 Troubleshooting

### Problemas Comunes

#### Tests Fallan Inesperadamente

```bash
# Verificar configuración
pytest --collect-only

# Ejecutar con más verbosidad
pytest -v -s

# Verificar logs
tail -f logs/testing.log
```

#### Problemas de Conectividad

```bash
# Verificar variables de entorno
echo $API_BASE_URL

# Probar conectividad
curl -I $API_BASE_URL/health

# Verificar certificados SSL
openssl s_client -connect api.eci.com:443
```

#### Problemas con Pact

```bash
# Verificar Pact Broker
curl -I $PACT_BROKER_URL

# Limpiar contratos locales
rm -rf pacts/

# Regenerar contratos
pytest contract_testing/ -v
```

### Logs y Debugging

```python
# Habilitar debug logging
import logging
logging.basicConfig(level=logging.DEBUG)

# Usar loguru para logging estructurado
from loguru import logger
logger.add("logs/testing.log", rotation="1 day")
```

### Performance Issues

```bash
# Profiling de tests
pytest --profile

# Análisis de memoria
pytest --memray

# Tests paralelos
pytest -n auto
```

## 🤝 Contribución

### Proceso de Contribución

1. Fork del repositorio
2. Crear rama feature: `git checkout -b feature/nueva-funcionalidad`
3. Hacer commits: `git commit -m "Add nueva funcionalidad"`
4. Push: `git push origin feature/nueva-funcionalidad`
5. Crear Pull Request

### Estándares de Código

```bash
# Formatear código
black .
isort .

# Linting
flake8 .
mypy .

# Tests
pytest --cov=.
```

### Documentación

- Actualizar README.md para cambios importantes
- Documentar nuevas APIs en docstrings
- Mantener ejemplos actualizados
- Actualizar changelog

## 📞 Soporte

- **Email**: testing-team@eci.com
- **Slack**: #testing-automation
- **Jira**: Proyecto ECI-TEST
- **Confluence**: Espacio ECI Testing

## 📄 Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

---

**Desarrollado con ❤️ por el equipo ECI - CTO**