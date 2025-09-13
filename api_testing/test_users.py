"""
Tests para API de usuarios - Ejemplo práctico de testing de APIs
"""

from typing import Any, Dict
from unittest.mock import Mock, patch

import pytest

from .client import APIResponse
from .test_base import APITestBase, api_test, regression_test, smoke_test
from .validators import API_ERROR_SCHEMA, USER_SCHEMA


class TestUsersAPI(APITestBase):
    """Tests para la API de usuarios"""

    def setup_method(self):
        """Setup para cada test"""
        # Configurar endpoint
        self.endpoint = "/users"
        # El user_id se obtendrá del test_data cuando esté disponible

    @smoke_test
    @api_test
    def test_get_users_success(self):
        """Test: Obtener lista de usuarios exitosamente"""
        response = self.client.get(self.endpoint)

        # Validaciones básicas
        self.assert_status_code(response, 200)
        self.assert_response_time(response, 2.0)

        # Validar estructura de respuesta
        assert isinstance(response.data, dict)
        assert "users" in response.data
        assert isinstance(response.data["users"], list)

        # Validar esquema de cada usuario
        for user in response.data["users"]:
            self.assert_json_schema(APIResponse(200, {}, user, 0, "", ""), USER_SCHEMA)

    @api_test
    def test_get_user_by_id_success(self):
        """Test: Obtener usuario por ID exitosamente"""
        response = self.client.get(f"{self.endpoint}/{self.user_id}")

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 1.0)

        # Validar datos del usuario
        self.assert_contains(response, "id", self.user_id)
        self.assert_contains(response, "name")
        self.assert_contains(response, "email")

        # Validar esquema
        self.assert_json_schema(response, USER_SCHEMA)

    @api_test
    def test_get_user_not_found(self):
        """Test: Usuario no encontrado"""
        non_existent_id = 99999
        response = self.client.get(f"{self.endpoint}/{non_existent_id}")

        self.assert_status_code(response, 404)

        # Validar estructura de error
        self.assert_json_schema(response, API_ERROR_SCHEMA)
        self.assert_contains(response, "error")
        self.assert_contains(response, "message")

    @api_test
    def test_create_user_success(self):
        """Test: Crear usuario exitosamente"""
        user_data = {
            "name": "Nuevo Usuario",
            "email": "nuevo@eci.com",
            "password": "secure_password123",
        }

        response = self.client.post(self.endpoint, json_data=user_data)

        self.assert_status_code(response, 201)
        self.assert_response_time(response, 2.0)

        # Validar respuesta
        self.assert_contains(response, "id")
        self.assert_contains(response, "name", user_data["name"])
        self.assert_contains(response, "email", user_data["email"])

        # No debe incluir password en la respuesta
        assert "password" not in response.data

        # Validar esquema
        self.assert_json_schema(response, USER_SCHEMA)

    @api_test
    def test_create_user_validation_error(self):
        """Test: Error de validación al crear usuario"""
        invalid_data = {
            "name": "",  # Nombre vacío
            "email": "invalid_email",  # Email inválido
            "password": "123",  # Password muy corto
        }

        response = self.client.post(self.endpoint, json_data=invalid_data)

        self.assert_status_code(response, 400)

        # Validar estructura de error
        self.assert_json_schema(response, API_ERROR_SCHEMA)
        self.assert_contains(response, "error")
        self.assert_contains(response, "message")

    @api_test
    def test_update_user_success(self):
        """Test: Actualizar usuario exitosamente"""
        update_data = {"name": "Usuario Actualizado", "email": "actualizado@eci.com"}

        response = self.client.put(
            f"{self.endpoint}/{self.user_id}", json_data=update_data
        )

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 1.5)

        # Validar datos actualizados
        self.assert_contains(response, "id", self.user_id)
        self.assert_contains(response, "name", update_data["name"])
        self.assert_contains(response, "email", update_data["email"])

        # Validar esquema
        self.assert_json_schema(response, USER_SCHEMA)

    @api_test
    def test_delete_user_success(self):
        """Test: Eliminar usuario exitosamente"""
        response = self.client.delete(f"{self.endpoint}/{self.user_id}")

        self.assert_status_code(response, 204)
        self.assert_response_time(response, 1.0)

        # Verificar que el usuario fue eliminado
        get_response = self.client.get(f"{self.endpoint}/{self.user_id}")
        self.assert_status_code(get_response, 404)

    @api_test
    def test_authentication_required(self):
        """Test: Autenticación requerida para operaciones protegidas"""
        # Crear cliente sin autenticación
        from .client import APIClient

        unauthenticated_client = APIClient()
        unauthenticated_client.session.headers.pop("Authorization", None)

        response = unauthenticated_client.get(f"{self.endpoint}/{self.user_id}")

        self.assert_status_code(response, 401)
        self.assert_json_schema(response, API_ERROR_SCHEMA)

    @api_test
    def test_pagination(self):
        """Test: Paginación en lista de usuarios"""
        params = {"page": 1, "per_page": 10}

        response = self.client.get(self.endpoint, params=params)

        self.assert_status_code(response, 200)

        # Validar estructura de paginación
        assert "pagination" in response.data
        pagination = response.data["pagination"]

        self.assert_contains(APIResponse(200, {}, pagination, 0, "", ""), "page", 1)
        self.assert_contains(
            APIResponse(200, {}, pagination, 0, "", ""), "per_page", 10
        )
        self.assert_contains(APIResponse(200, {}, pagination, 0, "", ""), "total")
        self.assert_contains(APIResponse(200, {}, pagination, 0, "", ""), "total_pages")

    @regression_test
    @api_test
    def test_concurrent_requests(self):
        """Test: Múltiples requests concurrentes"""
        import concurrent.futures
        import threading

        def make_request():
            return self.client.get(f"{self.endpoint}/{self.user_id}")

        # Ejecutar 10 requests concurrentes
        with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
            futures = [executor.submit(make_request) for _ in range(10)]
            responses = [future.result() for future in futures]

        # Todas las respuestas deben ser exitosas
        for response in responses:
            self.assert_status_code(response, 200)
            self.assert_response_time(
                response, 3.0
            )  # Tiempo más generoso para concurrencia

    @api_test
    def test_rate_limiting(self):
        """Test: Rate limiting"""
        # Hacer muchas requests rápidamente
        responses = []
        for _ in range(100):  # Número alto para activar rate limiting
            response = self.client.get(self.endpoint)
            responses.append(response)

            if response.status_code == 429:  # Rate limited
                break

        # Debe haber al menos una respuesta con rate limiting
        rate_limited_responses = [r for r in responses if r.status_code == 429]
        assert len(rate_limited_responses) > 0, "Rate limiting not activated"

        # Validar estructura de error de rate limiting
        if rate_limited_responses:
            error_response = rate_limited_responses[0]
            self.assert_json_schema(error_response, API_ERROR_SCHEMA)
            self.assert_contains(error_response, "error")

    @api_test
    def test_content_type_headers(self):
        """Test: Headers de Content-Type correctos"""
        response = self.client.get(self.endpoint)

        self.assert_status_code(response, 200)
        self.assert_headers(response, {"Content-Type": "application/json"})

    @api_test
    def test_cors_headers(self):
        """Test: Headers CORS"""
        response = self.client.options(self.endpoint)

        self.assert_status_code(response, 200)

        # Verificar headers CORS
        cors_headers = [
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Methods",
            "Access-Control-Allow-Headers",
        ]

        for header in cors_headers:
            assert header in response.headers, f"CORS header '{header}' missing"
