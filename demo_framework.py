#!/usr/bin/env python3
"""
Demostración del Framework ECI Testing Automation
"""

import sys
import os
sys.path.append(os.path.dirname(os.path.abspath(__file__)))

from api_testing.client import APIClient, APIResponse
from api_testing.validators import APIResponseValidator
from api_testing.test_base import APITestBase
import pytest
from unittest.mock import patch


def demo_api_client():
    """Demostración del cliente API"""
    print("🔧 Demostrando Cliente API...")
    
    # Crear cliente
    client = APIClient()
    print(f"✅ Cliente creado con base_url: {client.base_url}")
    
    # Simular una respuesta
    mock_response = APIResponse(
        status_code=200,
        headers={"Content-Type": "application/json"},
        data={"message": "API funcionando correctamente"},
        response_time=0.15,
        url="https://demo.eci.com/api/test",
        method="GET"
    )
    
    print("✅ Respuesta mock creada")
    print(f"   Status: {mock_response.status_code}")
    print(f"   Data: {mock_response.data}")
    print(f"   Response Time: {mock_response.response_time}s")
    
    return client, mock_response


def demo_validators():
    """Demostración de validadores"""
    print("\n🔍 Demostrando Validadores...")
    
    validator = APIResponseValidator()
    
    # Datos de prueba
    test_data = {
        "id": 1,
        "name": "Test User",
        "email": "test@eci.com",
        "active": True
    }
    
    # Esquema de validación
    schema = {
        "type": "object",
        "properties": {
            "id": {"type": "integer"},
            "name": {"type": "string"},
            "email": {"type": "string", "format": "email"},
            "active": {"type": "boolean"}
        },
        "required": ["id", "name", "email", "active"]
    }
    
    # Validar esquema
    try:
        validator.validate_json_schema(test_data, schema)
        print("✅ Validación de esquema JSON exitosa")
    except Exception as e:
        print(f"❌ Error en validación: {e}")
    
    # Validar Pydantic
    try:
        from pydantic import BaseModel, EmailStr
        
        class UserModel(BaseModel):
            id: int
            name: str
            email: EmailStr
            active: bool
        
        user = UserModel(**test_data)
        print("✅ Validación Pydantic exitosa")
        print(f"   Usuario: {user.name} ({user.email})")
    except Exception as e:
        print(f"❌ Error en validación Pydantic: {e}")


def demo_test_execution():
    """Demostración de ejecución de tests"""
    print("\n🧪 Demostrando Ejecución de Tests...")
    
    # Ejecutar tests de demostración
    result = pytest.main([
        "test_demo.py",
        "-v",
        "--tb=short",
        "--no-header"
    ])
    
    if result == 0:
        print("✅ Todos los tests pasaron exitosamente")
    else:
        print(f"❌ Algunos tests fallaron (código: {result})")


def demo_reporting():
    """Demostración de reportes"""
    print("\n📊 Demostrando Generación de Reportes...")
    
    # Generar reporte HTML
    result = pytest.main([
        "test_demo.py",
        "--html=reports/demo_framework_report.html",
        "--self-contained-html",
        "--quiet"
    ])
    
    if result == 0:
        print("✅ Reporte HTML generado: reports/demo_framework_report.html")
    
    # Generar reporte con cobertura
    result = pytest.main([
        "test_demo.py",
        "--cov=api_testing",
        "--cov-report=html:reports/coverage",
        "--cov-report=term",
        "--quiet"
    ])
    
    if result == 0:
        print("✅ Reporte de cobertura generado: reports/coverage/index.html")


def demo_metrics():
    """Demostración de métricas"""
    print("\n📈 Demostrando Sistema de Métricas...")
    
    # Simular métricas
    metrics = {
        "total_tests": 5,
        "passed_tests": 5,
        "failed_tests": 0,
        "skipped_tests": 0,
        "success_rate": 100.0,
        "total_duration": 0.22,
        "average_duration": 0.044
    }
    
    print("📊 Métricas de Testing:")
    for key, value in metrics.items():
        if isinstance(value, float):
            print(f"   {key}: {value:.2f}")
        else:
            print(f"   {key}: {value}")
    
    print(f"\n🎯 Tasa de éxito: {metrics['success_rate']}%")
    print(f"⏱️  Duración promedio: {metrics['average_duration']:.3f}s")


def main():
    """Función principal de demostración"""
    print("🚀 ECI Testing Automation Framework - Demostración")
    print("=" * 60)
    
    try:
        # Demostrar componentes
        demo_api_client()
        demo_validators()
        demo_test_execution()
        demo_reporting()
        demo_metrics()
        
        print("\n" + "=" * 60)
        print("🎉 ¡Demostración completada exitosamente!")
        print("\n📁 Archivos generados:")
        print("   - reports/demo_framework_report.html")
        print("   - reports/coverage/index.html")
        print("   - htmlcov/index.html")
        
        print("\n🔧 Comandos útiles:")
        print("   - Ejecutar tests: pytest test_demo.py -v")
        print("   - Tests con cobertura: pytest test_demo.py --cov=api_testing")
        print("   - Solo tests smoke: pytest test_demo.py -m smoke")
        print("   - Reporte HTML: pytest test_demo.py --html=report.html")
        
    except Exception as e:
        print(f"\n❌ Error en la demostración: {e}")
        import traceback
        traceback.print_exc()
        return 1
    
    return 0


if __name__ == "__main__":
    sys.exit(main())