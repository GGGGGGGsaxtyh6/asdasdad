# ECI - CTO Testing Automation Framework

## 🎯 Objetivo
Framework completo de automatización de pruebas para el área ECI - CTO, enfocado en testing de APIs, contratos y calidad de software en un entorno Agile.

## 🏗️ Arquitectura del Proyecto

```
testing-automation/
├── api-testing/           # Testing de APIs REST y GraphQL
├── contract-testing/      # Testing de contratos entre servicios
├── integration/           # Integración con Jira y Confluence
├── ci-cd/                # Pipelines de CI/CD
├── reporting/            # Reportes y métricas
├── docs/                 # Documentación
└── tools/                # Herramientas auxiliares
```

## 🚀 Características Principales

- **Testing de APIs**: REST y GraphQL con validación completa
- **Contract Testing**: Pact para testing de contratos entre servicios
- **Integración Agile**: Jira y Confluence para gestión y documentación
- **CI/CD**: Pipelines automatizados con GitHub Actions
- **Reporting**: Dashboards y métricas de calidad
- **Escalabilidad**: Framework modular y extensible

## 🛠️ Tecnologías

- **Python 3.11+** con pytest
- **Postman/Newman** para colecciones de API
- **Pact** para contract testing
- **Docker** para containerización
- **GitHub Actions** para CI/CD
- **Allure** para reporting
- **Jira API** para integración

## 📋 Prerequisitos

- Python 3.11+
- Docker
- Node.js 18+
- Git

## 🚀 Inicio Rápido

```bash
# Clonar el repositorio
git clone <repository-url>
cd testing-automation

# Instalar dependencias
pip install -r requirements.txt
npm install

# Ejecutar tests
pytest api-testing/
```

## 📊 Métricas de Calidad

- Cobertura de testing > 80%
- Tiempo de ejecución < 10 minutos
- Tasa de fallos < 5%
- Integración continua con feedback inmediato

## 🤝 Contribución

Ver [CONTRIBUTING.md](docs/CONTRIBUTING.md) para guías de contribución.

## 📞 Contacto

Equipo ECI - CTO Testing Automation