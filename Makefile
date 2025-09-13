# ECI Testing Automation Framework - Makefile

.PHONY: help install test test-api test-contract test-integration test-performance test-smoke test-regression test-all lint format clean setup docker-build docker-run docker-compose-up docker-compose-down reports docs

# Variables
PYTHON := python3
PIP := pip3
DOCKER := docker
DOCKER_COMPOSE := docker-compose

# Colores para output
GREEN := \033[0;32m
YELLOW := \033[1;33m
RED := \033[0;31m
NC := \033[0m # No Color

help: ## Mostrar ayuda
	@echo "$(GREEN)ECI Testing Automation Framework$(NC)"
	@echo ""
	@echo "Comandos disponibles:"
	@awk 'BEGIN {FS = ":.*?## "} /^[a-zA-Z_-]+:.*?## / {printf "  $(YELLOW)%-20s$(NC) %s\n", $$1, $$2}' $(MAKEFILE_LIST)

install: ## Instalar dependencias
	@echo "$(GREEN)Instalando dependencias...$(NC)"
	$(PIP) install -r requirements.txt
	npm install
	@echo "$(GREEN)✅ Dependencias instaladas$(NC)"

setup: ## Configurar entorno
	@echo "$(GREEN)Configurando entorno...$(NC)"
	cp .env.example .env
	mkdir -p reports logs test_data fixtures reports/dashboards
	@echo "$(GREEN)✅ Entorno configurado$(NC)"
	@echo "$(YELLOW)⚠️  Recuerda editar .env con tus configuraciones$(NC)"

test: test-all ## Alias para test-all

test-api: ## Ejecutar tests de API
	@echo "$(GREEN)Ejecutando tests de API...$(NC)"
	$(PYTHON) scripts/run_tests.py api -v --coverage --reports

test-contract: ## Ejecutar tests de contratos
	@echo "$(GREEN)Ejecutando tests de contratos...$(NC)"
	$(PYTHON) scripts/run_tests.py contract -v --reports

test-integration: ## Ejecutar tests de integración
	@echo "$(GREEN)Ejecutando tests de integración...$(NC)"
	$(PYTHON) scripts/run_tests.py integration -v --reports

test-performance: ## Ejecutar tests de rendimiento
	@echo "$(GREEN)Ejecutando tests de rendimiento...$(NC)"
	$(PYTHON) scripts/run_tests.py performance -v --reports

test-smoke: ## Ejecutar tests de smoke
	@echo "$(GREEN)Ejecutando tests de smoke...$(NC)"
	$(PYTHON) scripts/run_tests.py smoke -v

test-regression: ## Ejecutar tests de regresión
	@echo "$(GREEN)Ejecutando tests de regresión...$(NC)"
	$(PYTHON) scripts/run_tests.py regression -v --reports

test-all: ## Ejecutar todos los tests
	@echo "$(GREEN)Ejecutando todos los tests...$(NC)"
	$(PYTHON) scripts/run_tests.py all -v --coverage --reports

lint: ## Ejecutar linting
	@echo "$(GREEN)Ejecutando linting...$(NC)"
	black --check --diff .
	isort --check-only --diff .
	flake8 . --count --select=E9,F63,F7,F82 --show-source --statistics
	mypy . --ignore-missing-imports
	@echo "$(GREEN)✅ Linting completado$(NC)"

format: ## Formatear código
	@echo "$(GREEN)Formateando código...$(NC)"
	black .
	isort .
	@echo "$(GREEN)✅ Código formateado$(NC)"

clean: ## Limpiar archivos temporales
	@echo "$(GREEN)Limpiando archivos temporales...$(NC)"
	find . -type f -name "*.pyc" -delete
	find . -type d -name "__pycache__" -delete
	find . -type d -name "*.egg-info" -exec rm -rf {} +
	find . -type f -name ".coverage" -delete
	find . -type d -name "htmlcov" -exec rm -rf {} +
	find . -type d -name ".pytest_cache" -exec rm -rf {} +
	find . -type d -name ".mypy_cache" -exec rm -rf {} +
	rm -rf reports/*.html reports/*.xml reports/dashboards/*
	@echo "$(GREEN)✅ Limpieza completada$(NC)"

docker-build: ## Construir imagen Docker
	@echo "$(GREEN)Construyendo imagen Docker...$(NC)"
	$(DOCKER) build -t eci-testing .
	@echo "$(GREEN)✅ Imagen Docker construida$(NC)"

docker-run: ## Ejecutar contenedor Docker
	@echo "$(GREEN)Ejecutando contenedor Docker...$(NC)"
	$(DOCKER) run --rm -v $(PWD)/reports:/app/reports eci-testing

docker-compose-up: ## Levantar servicios con Docker Compose
	@echo "$(GREEN)Levantando servicios...$(NC)"
	$(DOCKER_COMPOSE) -f ci-cd/docker/docker-compose.yml up -d
	@echo "$(GREEN)✅ Servicios levantados$(NC)"
	@echo "$(YELLOW)Servicios disponibles:$(NC)"
	@echo "  - ECI Testing: http://localhost:8000"
	@echo "  - Pact Broker: http://localhost:9292"
	@echo "  - Report Server: http://localhost:8080"
	@echo "  - Grafana: http://localhost:3000"

docker-compose-down: ## Bajar servicios con Docker Compose
	@echo "$(GREEN)Bajando servicios...$(NC)"
	$(DOCKER_COMPOSE) -f ci-cd/docker/docker-compose.yml down
	@echo "$(GREEN)✅ Servicios bajados$(NC)"

reports: ## Generar reportes
	@echo "$(GREEN)Generando reportes...$(NC)"
	$(PYTHON) ci-cd/scripts/generate_consolidated_report.py
	@echo "$(GREEN)✅ Reportes generados en reports/$(NC)"

docs: ## Generar documentación
	@echo "$(GREEN)Generando documentación...$(NC)"
	@echo "$(YELLOW)La documentación está disponible en docs/$(NC)"
	@echo "$(YELLOW)Guías disponibles:$(NC)"
	@echo "  - README.md: Documentación principal"
	@echo "  - API_TESTING_GUIDE.md: Guía de testing de APIs"
	@echo "  - CONTRACT_TESTING_GUIDE.md: Guía de contract testing"
	@echo "  - CI_CD_GUIDE.md: Guía de CI/CD"

ci: ## Ejecutar pipeline de CI local
	@echo "$(GREEN)Ejecutando pipeline de CI local...$(NC)"
	$(MAKE) lint
	$(MAKE) test-smoke
	$(MAKE) test-api
	$(MAKE) test-contract
	$(MAKE) test-integration
	$(MAKE) reports
	@echo "$(GREEN)✅ Pipeline de CI completado$(NC)"

dev: ## Configurar entorno de desarrollo
	@echo "$(GREEN)Configurando entorno de desarrollo...$(NC)"
	$(MAKE) install
	$(MAKE) setup
	$(MAKE) docker-compose-up
	@echo "$(GREEN)✅ Entorno de desarrollo listo$(NC)"
	@echo "$(YELLOW)Próximos pasos:$(NC)"
	@echo "  1. Editar .env con tus configuraciones"
	@echo "  2. Ejecutar: make test-smoke"
	@echo "  3. Ver reportes en: http://localhost:8080"

# Comandos de desarrollo
watch: ## Ejecutar tests en modo watch
	@echo "$(GREEN)Ejecutando tests en modo watch...$(NC)"
	$(PYTHON) scripts/run_tests.py all -v --coverage --reports
	@echo "$(YELLOW)Usa Ctrl+C para detener$(NC)"

debug: ## Ejecutar tests en modo debug
	@echo "$(GREEN)Ejecutando tests en modo debug...$(NC)"
	PYTHONPATH=. $(PYTHON) -m pytest api_testing/ -v -s --tb=long

profile: ## Ejecutar tests con profiling
	@echo "$(GREEN)Ejecutando tests con profiling...$(NC)"
	$(PYTHON) -m pytest api_testing/ --profile

# Comandos de utilidad
check-deps: ## Verificar dependencias
	@echo "$(GREEN)Verificando dependencias...$(NC)"
	$(PIP) check
	npm audit
	@echo "$(GREEN)✅ Dependencias verificadas$(NC)"

update-deps: ## Actualizar dependencias
	@echo "$(GREEN)Actualizando dependencias...$(NC)"
	$(PIP) install --upgrade -r requirements.txt
	npm update
	@echo "$(GREEN)✅ Dependencias actualizadas$(NC)"

security-check: ## Ejecutar verificación de seguridad
	@echo "$(GREEN)Ejecutando verificación de seguridad...$(NC)"
	bandit -r . -f json -o security-report.json
	safety check
	@echo "$(GREEN)✅ Verificación de seguridad completada$(NC)"

# Comandos de información
status: ## Mostrar estado del proyecto
	@echo "$(GREEN)Estado del proyecto ECI Testing Automation$(NC)"
	@echo ""
	@echo "$(YELLOW)Configuración:$(NC)"
	@echo "  Python: $(shell $(PYTHON) --version)"
	@echo "  Pip: $(shell $(PIP) --version)"
	@echo "  Node: $(shell node --version 2>/dev/null || echo 'No instalado')"
	@echo "  Docker: $(shell $(DOCKER) --version 2>/dev/null || echo 'No instalado')"
	@echo ""
	@echo "$(YELLOW)Archivos de configuración:$(NC)"
	@echo "  .env: $(shell test -f .env && echo '✅ Existe' || echo '❌ Faltante')"
	@echo "  requirements.txt: $(shell test -f requirements.txt && echo '✅ Existe' || echo '❌ Faltante')"
	@echo "  package.json: $(shell test -f package.json && echo '✅ Existe' || echo '❌ Faltante')"
	@echo ""
	@echo "$(YELLOW)Directorios:$(NC)"
	@echo "  reports/: $(shell test -d reports && echo '✅ Existe' || echo '❌ Faltante')"
	@echo "  logs/: $(shell test -d logs && echo '✅ Existe' || echo '❌ Faltante')"
	@echo "  test_data/: $(shell test -d test_data && echo '✅ Existe' || echo '❌ Faltante')"

# Comandos de limpieza específicos
clean-reports: ## Limpiar solo reportes
	@echo "$(GREEN)Limpiando reportes...$(NC)"
	rm -rf reports/*.html reports/*.xml reports/dashboards/*
	@echo "$(GREEN)✅ Reportes limpiados$(NC)"

clean-logs: ## Limpiar solo logs
	@echo "$(GREEN)Limpiando logs...$(NC)"
	rm -rf logs/*
	@echo "$(GREEN)✅ Logs limpiados$(NC)"

clean-cache: ## Limpiar solo cache
	@echo "$(GREEN)Limpiando cache...$(NC)"
	find . -type f -name "*.pyc" -delete
	find . -type d -name "__pycache__" -delete
	find . -type d -name ".pytest_cache" -exec rm -rf {} +
	find . -type d -name ".mypy_cache" -exec rm -rf {} +
	@echo "$(GREEN)✅ Cache limpiado$(NC)"