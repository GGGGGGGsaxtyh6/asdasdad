"""
Clase base para tests de API con funcionalidades comunes
"""

from dataclasses import dataclass
from typing import Any, Dict, List, Optional
from unittest.mock import Mock, patch

import pytest

from .client import APIClient, APIResponse, AsyncAPIClient
from .config import config
from .validators import (APIResponseValidator, ContractValidator,
                         PerformanceValidator)


@dataclass
class TestData:
    """Datos de prueba reutilizables"""

    user_id: int
    username: str
    email: str
    api_key: str


class APITestBase:
    """Clase base para tests de API"""

    @pytest.fixture(autouse=True)
    def setup_test(self):
        """Setup automático para cada test"""
        self.client = APIClient()
        self.async_client = AsyncAPIClient()
        self.validator = APIResponseValidator()
        self.contract_validator = ContractValidator()
        self.performance_validator = PerformanceValidator()

        # Datos de prueba
        self.test_data = TestData(
            user_id=1,
            username="test_user",
            email="test@eci.com",
            api_key="test_api_key",
        )

        yield

        # Cleanup
        if hasattr(self, "async_client"):
            import asyncio

            asyncio.run(self.async_client.close())

    def assert_status_code(self, response: APIResponse, expected: int):
        """Assert para status code"""
        assert (
            response.status_code == expected
        ), f"Expected status {expected}, got {response.status_code}. Response: {response.data}"

    def assert_response_time(self, response: APIResponse, max_time: float = 2.0):
        """Assert para tiempo de respuesta"""
        assert (
            response.response_time <= max_time
        ), f"Response time {response.response_time:.2f}s exceeds maximum {max_time}s"

    def assert_json_schema(self, response: APIResponse, schema: Dict[str, Any]):
        """Assert para esquema JSON"""
        from jsonschema import ValidationError, validate

        try:
            validate(instance=response.data, schema=schema)
        except ValidationError as e:
            pytest.fail(f"JSON schema validation failed: {e.message}")

    def assert_contains(self, response: APIResponse, key: str, value: Any = None):
        """Assert para contenido de respuesta"""
        if isinstance(response.data, dict):
            assert key in response.data, f"Key '{key}' not found in response"
            if value is not None:
                assert (
                    response.data[key] == value
                ), f"Expected {key}={value}, got {response.data[key]}"
        else:
            pytest.fail("Response data is not a dictionary")

    def assert_headers(self, response: APIResponse, headers: Dict[str, str]):
        """Assert para headers de respuesta"""
        for key, expected_value in headers.items():
            assert key in response.headers, f"Header '{key}' not found"
            assert (
                response.headers[key] == expected_value
            ), f"Expected header {key}={expected_value}, got {response.headers[key]}"

    def create_mock_response(
        self,
        status_code: int = 200,
        data: Any = None,
        headers: Optional[Dict[str, str]] = None,
        response_time: float = 0.1,
    ) -> APIResponse:
        """Crear respuesta mock para testing"""
        return APIResponse(
            status_code=status_code,
            headers=headers or {},
            data=data or {},
            response_time=response_time,
            url="http://mock.test",
            method="GET",
        )


class AsyncAPITestBase(APITestBase):
    """Clase base para tests asíncronos de API"""

    @pytest.fixture(autouse=True)
    async def async_setup_test(self):
        """Setup asíncrono para cada test"""
        await super().setup_test()
        yield
        await self.async_client.close()


class ContractTestBase(APITestBase):
    """Clase base para tests de contratos"""

    def setup_contract(self, service_name: str, contract: Dict[str, Any]):
        """Configurar contrato para testing"""
        self.contract_validator.add_contract(service_name, contract)

    def assert_contract_compliance(self, service_name: str, response: APIResponse):
        """Assert para cumplimiento de contrato"""
        result = self.contract_validator.validate_contract(service_name, response)
        assert result.is_valid, f"Contract validation failed: {result.errors}"


class PerformanceTestBase(APITestBase):
    """Clase base para tests de rendimiento"""

    def assert_performance(self, response: APIResponse, max_time: float = 2.0):
        """Assert para rendimiento"""
        result = self.performance_validator.validate_performance(response)
        assert result.is_valid, f"Performance validation failed: {result.errors}"
        self.assert_response_time(response, max_time)


class SecurityTestBase(APITestBase):
    """Clase base para tests de seguridad"""

    def assert_authentication_required(self, response: APIResponse):
        """Assert que se requiere autenticación"""
        assert response.status_code in [
            401,
            403,
        ], f"Expected authentication error, got {response.status_code}"

    def assert_authorization_required(self, response: APIResponse):
        """Assert que se requiere autorización"""
        assert (
            response.status_code == 403
        ), f"Expected authorization error, got {response.status_code}"

    def assert_https_required(self, response: APIResponse):
        """Assert que se requiere HTTPS"""
        assert response.url.startswith(
            "https://"
        ), f"HTTPS required, got {response.url}"

    def assert_security_headers(self, response: APIResponse):
        """Assert para headers de seguridad"""
        security_headers = [
            "X-Content-Type-Options",
            "X-Frame-Options",
            "X-XSS-Protection",
            "Strict-Transport-Security",
        ]

        for header in security_headers:
            assert header in response.headers, f"Security header '{header}' missing"


# Fixtures comunes para pytest
@pytest.fixture
def api_client():
    """Fixture para cliente de API"""
    return APIClient()


@pytest.fixture
async def async_api_client():
    """Fixture para cliente asíncrono de API"""
    client = AsyncAPIClient()
    yield client
    await client.close()


@pytest.fixture
def test_data():
    """Fixture para datos de prueba"""
    return TestData(
        user_id=1, username="test_user", email="test@eci.com", api_key="test_api_key"
    )


@pytest.fixture
def mock_response():
    """Fixture para respuesta mock"""

    def _mock_response(status_code=200, data=None, headers=None):
        return APIResponse(
            status_code=status_code,
            headers=headers or {},
            data=data or {},
            response_time=0.1,
            url="http://mock.test",
            method="GET",
        )

    return _mock_response


# Decoradores para marcar tests
def smoke_test(func):
    """Decorador para tests de smoke"""
    func._pytest_mark = getattr(func, "_pytest_mark", []) + [pytest.mark.smoke]
    return func


def regression_test(func):
    """Decorador para tests de regresión"""
    func._pytest_mark = getattr(func, "_pytest_mark", []) + [pytest.mark.regression]
    return func


def api_test(func):
    """Decorador para tests de API"""
    func._pytest_mark = getattr(func, "_pytest_mark", []) + [pytest.mark.api]
    return func


def performance_test(func):
    """Decorador para tests de rendimiento"""
    func._pytest_mark = getattr(func, "_pytest_mark", []) + [pytest.mark.performance]
    return func


def slow_test(func):
    """Decorador para tests lentos"""
    func._pytest_mark = getattr(func, "_pytest_mark", []) + [pytest.mark.slow]
    return func
