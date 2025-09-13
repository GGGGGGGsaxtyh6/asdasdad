#!/usr/bin/env python3
"""
Script principal para ejecutar tests del framework ECI Testing Automation
"""

import argparse
import sys
import os
from pathlib import Path
from typing import List, Optional
import subprocess
import json
from datetime import datetime

# Agregar el directorio raíz al path
sys.path.insert(0, str(Path(__file__).parent.parent))

from api_testing.config import config
from reporting.metrics_collector import metrics_collector
from reporting.dashboard_generator import dashboard_generator


def run_command(command: List[str], capture_output: bool = False) -> subprocess.CompletedProcess:
    """Ejecutar comando y retornar resultado"""
    print(f"Ejecutando: {' '.join(command)}")
    
    try:
        result = subprocess.run(
            command,
            capture_output=capture_output,
            text=True,
            check=True
        )
        return result
    except subprocess.CalledProcessError as e:
        print(f"Error ejecutando comando: {e}")
        if e.stdout:
            print(f"STDOUT: {e.stdout}")
        if e.stderr:
            print(f"STDERR: {e.stderr}")
        sys.exit(1)


def run_api_tests(verbose: bool = False, coverage: bool = False) -> bool:
    """Ejecutar tests de API"""
    print("🧪 Ejecutando tests de API...")
    
    command = ["pytest", "api_testing/"]
    
    if verbose:
        command.append("-v")
    
    if coverage:
        command.extend(["--cov=api_testing", "--cov-report=html", "--cov-report=term"])
    
    command.extend([
        "--html=reports/api-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/api-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Tests de API completados exitosamente")
        return True
    except SystemExit:
        print("❌ Tests de API fallaron")
        return False


def run_contract_tests(verbose: bool = False) -> bool:
    """Ejecutar tests de contratos"""
    print("🤝 Ejecutando tests de contratos...")
    
    command = ["pytest", "contract_testing/"]
    
    if verbose:
        command.append("-v")
    
    command.extend([
        "--html=reports/contract-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/contract-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Tests de contratos completados exitosamente")
        return True
    except SystemExit:
        print("❌ Tests de contratos fallaron")
        return False


def run_integration_tests(verbose: bool = False) -> bool:
    """Ejecutar tests de integración"""
    print("🔗 Ejecutando tests de integración...")
    
    command = ["pytest", "integration/"]
    
    if verbose:
        command.append("-v")
    
    command.extend([
        "--html=reports/integration-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/integration-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Tests de integración completados exitosamente")
        return True
    except SystemExit:
        print("❌ Tests de integración fallaron")
        return False


def run_performance_tests(verbose: bool = False) -> bool:
    """Ejecutar tests de rendimiento"""
    print("⚡ Ejecutando tests de rendimiento...")
    
    command = ["pytest", "api_testing/", "-k", "performance"]
    
    if verbose:
        command.append("-v")
    
    command.extend([
        "--html=reports/performance-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/performance-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Tests de rendimiento completados exitosamente")
        return True
    except SystemExit:
        print("❌ Tests de rendimiento fallaron")
        return False


def run_all_tests(verbose: bool = False, coverage: bool = False) -> bool:
    """Ejecutar todos los tests"""
    print("🚀 Ejecutando todos los tests...")
    
    command = ["pytest"]
    
    if verbose:
        command.append("-v")
    
    if coverage:
        command.extend(["--cov=api_testing", "--cov=contract_testing", "--cov=integration"])
    
    command.extend([
        "--html=reports/all-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/all-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Todos los tests completados exitosamente")
        return True
    except SystemExit:
        print("❌ Algunos tests fallaron")
        return False


def run_smoke_tests(verbose: bool = False) -> bool:
    """Ejecutar tests de smoke"""
    print("💨 Ejecutando tests de smoke...")
    
    command = ["pytest", "-m", "smoke"]
    
    if verbose:
        command.append("-v")
    
    command.extend([
        "--html=reports/smoke-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/smoke-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Tests de smoke completados exitosamente")
        return True
    except SystemExit:
        print("❌ Tests de smoke fallaron")
        return False


def run_regression_tests(verbose: bool = False) -> bool:
    """Ejecutar tests de regresión"""
    print("🔄 Ejecutando tests de regresión...")
    
    command = ["pytest", "-m", "regression"]
    
    if verbose:
        command.append("-v")
    
    command.extend([
        "--html=reports/regression-test-results.html",
        "--self-contained-html",
        "--junitxml=reports/regression-test-results.xml"
    ])
    
    try:
        run_command(command)
        print("✅ Tests de regresión completados exitosamente")
        return True
    except SystemExit:
        print("❌ Tests de regresión fallaron")
        return False


def generate_reports() -> None:
    """Generar reportes consolidados"""
    print("📊 Generando reportes...")
    
    # Crear directorio de reportes
    reports_dir = Path("reports")
    reports_dir.mkdir(exist_ok=True)
    
    # Generar reporte consolidado
    try:
        from ci_cd.scripts.generate_consolidated_report import ConsolidatedReportGenerator
        generator = ConsolidatedReportGenerator()
        generator.generate_report()
        print("✅ Reporte consolidado generado")
    except Exception as e:
        print(f"❌ Error generando reporte consolidado: {e}")
    
    # Generar dashboards
    try:
        dashboard_generator.generate_all_dashboards("reports/dashboards/")
        print("✅ Dashboards generados")
    except Exception as e:
        print(f"❌ Error generando dashboards: {e}")


def setup_environment() -> None:
    """Configurar entorno de testing"""
    print("⚙️ Configurando entorno...")
    
    # Crear directorios necesarios
    directories = ["reports", "logs", "test_data", "fixtures", "reports/dashboards"]
    for directory in directories:
        Path(directory).mkdir(exist_ok=True)
    
    # Verificar variables de entorno
    required_vars = ["API_BASE_URL", "ENVIRONMENT"]
    missing_vars = [var for var in required_vars if not os.getenv(var)]
    
    if missing_vars:
        print(f"⚠️ Variables de entorno faltantes: {missing_vars}")
        print("Usando valores por defecto...")
    
    print("✅ Entorno configurado")


def main():
    """Función principal"""
    parser = argparse.ArgumentParser(description="ECI Testing Automation Framework")
    
    # Argumentos principales
    parser.add_argument(
        "test_type",
        choices=["api", "contract", "integration", "performance", "all", "smoke", "regression"],
        help="Tipo de tests a ejecutar"
    )
    
    # Opciones
    parser.add_argument("-v", "--verbose", action="store_true", help="Salida verbosa")
    parser.add_argument("--coverage", action="store_true", help="Incluir cobertura de código")
    parser.add_argument("--reports", action="store_true", help="Generar reportes después de los tests")
    parser.add_argument("--setup-only", action="store_true", help="Solo configurar entorno")
    
    args = parser.parse_args()
    
    # Configurar entorno
    setup_environment()
    
    if args.setup_only:
        print("✅ Configuración completada")
        return
    
    # Iniciar recolección de métricas
    metrics_collector.start_collection()
    
    # Ejecutar tests según el tipo
    success = False
    
    try:
        if args.test_type == "api":
            success = run_api_tests(args.verbose, args.coverage)
        elif args.test_type == "contract":
            success = run_contract_tests(args.verbose)
        elif args.test_type == "integration":
            success = run_integration_tests(args.verbose)
        elif args.test_type == "performance":
            success = run_performance_tests(args.verbose)
        elif args.test_type == "all":
            success = run_all_tests(args.verbose, args.coverage)
        elif args.test_type == "smoke":
            success = run_smoke_tests(args.verbose)
        elif args.test_type == "regression":
            success = run_regression_tests(args.verbose)
        
        # Detener recolección de métricas
        metrics_collector.stop_collection()
        
        # Generar reportes si se solicita
        if args.reports or args.test_type == "all":
            generate_reports()
        
        # Mostrar resumen
        if success:
            print("\n🎉 ¡Todos los tests completados exitosamente!")
        else:
            print("\n💥 Algunos tests fallaron")
            sys.exit(1)
            
    except KeyboardInterrupt:
        print("\n⏹️ Tests interrumpidos por el usuario")
        metrics_collector.stop_collection()
        sys.exit(1)
    except Exception as e:
        print(f"\n❌ Error inesperado: {e}")
        metrics_collector.stop_collection()
        sys.exit(1)


if __name__ == "__main__":
    main()