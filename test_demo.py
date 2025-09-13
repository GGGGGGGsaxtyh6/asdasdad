#!/usr/bin/env python3
"""
Test de demostración del framework ECI Testing Automation
"""

import pytest
from unittest.mock import Mock, patch
from api_testing.client import APIClient, APIResponse
from api_testing.test_base import APITestBase


class TestDemo(APITestBase):
    """Tests de demostración"""

    def setup_method(self):
        """Setup para cada test"""
        self.endpoint = "/demo"

    @pytest.mark.smoke
    def test_demo_success(self):
        """Test de demostración exitoso"""
        # Mock de la respuesta
        mock_response = APIResponse(
            status_code=200,
            headers={"Content-Type": "application/json"},
            data={"message": "Demo successful", "status": "ok"},
            response_time=0.1,
            url="https://demo.eci.com/demo",
            method="GET"
        )
        
        # Mock del cliente
        with patch.object(self.client, 'get', return_value=mock_response):
            response = self.client.get(self.endpoint)
            
            # Verificaciones
            self.assert_status_code(response, 200)
            self.assert_response_time(response, 1.0)
            assert response.data["message"] == "Demo successful"
            assert response.data["status"] == "ok"

    @pytest.mark.api
    def test_demo_validation(self):
        """Test de validación de esquema"""
        # Mock de la respuesta
        mock_response = APIResponse(
            status_code=200,
            headers={"Content-Type": "application/json"},
            data={
                "id": 1,
                "name": "Test User",
                "email": "test@eci.com",
                "active": True
            },
            response_time=0.15,
            url="https://demo.eci.com/demo",
            method="GET"
        )
        
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
        
        # Mock del cliente
        with patch.object(self.client, 'get', return_value=mock_response):
            response = self.client.get(self.endpoint)
            
            # Verificaciones
            self.assert_status_code(response, 200)
            self.assert_json_schema(response, schema)

    @pytest.mark.performance
    def test_demo_performance(self):
        """Test de rendimiento"""
        # Mock de la respuesta
        mock_response = APIResponse(
            status_code=200,
            headers={"Content-Type": "application/json"},
            data={"performance": "excellent"},
            response_time=0.05,  # 50ms - muy rápido
            url="https://demo.eci.com/demo",
            method="GET"
        )
        
        # Mock del cliente
        with patch.object(self.client, 'get', return_value=mock_response):
            response = self.client.get(self.endpoint)
            
            # Verificaciones de rendimiento
            self.assert_status_code(response, 200)
            self.assert_response_time(response, 0.1)  # Máximo 100ms
            assert response.response_time < 0.1

    def test_demo_error_handling(self):
        """Test de manejo de errores"""
        # Mock de error de conexión
        from requests.exceptions import ConnectionError
        
        with patch.object(self.client, 'get', side_effect=ConnectionError("Connection failed")):
            with pytest.raises(ConnectionError):
                self.client.get(self.endpoint)

    @pytest.mark.regression
    def test_demo_regression(self):
        """Test de regresión"""
        # Mock de la respuesta
        mock_response = APIResponse(
            status_code=200,
            headers={"Content-Type": "application/json"},
            data={"version": "1.0.0", "features": ["api", "testing", "automation"]},
            response_time=0.2,
            url="https://demo.eci.com/demo",
            method="GET"
        )
        
        # Mock del cliente
        with patch.object(self.client, 'get', return_value=mock_response):
            response = self.client.get(self.endpoint)
            
            # Verificaciones de regresión
            self.assert_status_code(response, 200)
            assert response.data["version"] == "1.0.0"
            assert "api" in response.data["features"]
            assert "testing" in response.data["features"]
            assert "automation" in response.data["features"]


if __name__ == "__main__":
    pytest.main([__file__, "-v"])