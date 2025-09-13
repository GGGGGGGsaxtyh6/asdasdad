"""
Consumer tests usando Pact para validar contratos de servicios
"""

from dataclasses import dataclass
from typing import Any, Dict, List

import pytest
from pact import Consumer, EachLike, Like, Provider, Term

from ..api_testing.test_base import APITestBase


@dataclass
class ServiceContract:
    """Contrato de servicio"""

    consumer: str
    provider: str
    version: str
    interactions: List[Dict[str, Any]]


class PactConsumerTestBase(APITestBase):
    """Clase base para tests de consumidor Pact"""

    def setup_method(self):
        """Setup para cada test"""
        super().setup_method()
        self.pact = None
        self.mock_service = None

    def teardown_method(self):
        """Cleanup después de cada test"""
        if self.pact:
            self.pact.stop()

    def create_pact(self, consumer: str, provider: str, version: str = "1.0.0"):
        """Crear instancia de Pact"""
        self.pact = Consumer(consumer).has_pact_with(
            Provider(provider), version=version
        )
        self.pact.start_service()
        return self.pact

    def add_interaction(
        self,
        description: str,
        state: str,
        request: Dict[str, Any],
        response: Dict[str, Any],
    ):
        """Agregar interacción al contrato"""
        if not self.pact:
            raise ValueError("Pact no inicializado. Llama a create_pact() primero.")

        self.pact.given(state).upon_receiving(description).with_request(
            **request
        ).will_respond_with(**response)

    def verify_contract(self):
        """Verificar contrato"""
        if not self.pact:
            raise ValueError("Pact no inicializado")

        self.pact.verify()


class TestUserServiceConsumer(PactConsumerTestBase):
    """Tests de consumidor para el servicio de usuarios"""

    def setup_method(self):
        """Setup para cada test"""
        super().setup_method()
        self.consumer = "user-frontend"
        self.provider = "user-service"
        self.pact = self.create_pact(self.consumer, self.provider)

    def test_get_user_by_id_contract(self):
        """Test: Contrato para obtener usuario por ID"""
        # Definir interacción
        self.add_interaction(
            description="Obtener usuario por ID",
            state="usuario existe con ID 1",
            request={
                "method": "GET",
                "path": "/api/v1/users/1",
                "headers": {"Authorization": "Bearer valid_token"},
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(1),
                    "name": Like("Juan Pérez"),
                    "email": Like("juan@eci.com"),
                    "created_at": Like("2023-01-01T00:00:00Z"),
                    "updated_at": Like("2023-01-01T00:00:00Z"),
                },
            },
        )

        # Ejecutar test
        response = self.client.get("/users/1")

        # Verificaciones
        self.assert_status_code(response, 200)
        self.assert_contains(response, "id")
        self.assert_contains(response, "name")
        self.assert_contains(response, "email")

        # Verificar contrato
        self.verify_contract()

    def test_get_users_list_contract(self):
        """Test: Contrato para obtener lista de usuarios"""
        self.add_interaction(
            description="Obtener lista de usuarios",
            state="existen usuarios en el sistema",
            request={
                "method": "GET",
                "path": "/api/v1/users",
                "query": "page=1&per_page=10",
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "users": EachLike(
                        {
                            "id": Like(1),
                            "name": Like("Usuario"),
                            "email": Like("usuario@eci.com"),
                        },
                        minimum=1,
                    ),
                    "pagination": {
                        "page": Like(1),
                        "per_page": Like(10),
                        "total": Like(100),
                        "total_pages": Like(10),
                    },
                },
            },
        )

        response = self.client.get("/users?page=1&per_page=10")

        self.assert_status_code(response, 200)
        self.assert_contains(response, "users")
        self.assert_contains(response, "pagination")

        self.verify_contract()

    def test_create_user_contract(self):
        """Test: Contrato para crear usuario"""
        user_data = {
            "name": "Nuevo Usuario",
            "email": "nuevo@eci.com",
            "password": "secure_password123",
        }

        self.add_interaction(
            description="Crear nuevo usuario",
            state="sistema acepta nuevos usuarios",
            request={
                "method": "POST",
                "path": "/api/v1/users",
                "headers": {"Content-Type": "application/json"},
                "body": user_data,
            },
            response={
                "status": 201,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(1),
                    "name": Like(user_data["name"]),
                    "email": Like(user_data["email"]),
                    "created_at": Like("2023-01-01T00:00:00Z"),
                },
            },
        )

        response = self.client.post("/users", json_data=user_data)

        self.assert_status_code(response, 201)
        self.assert_contains(response, "id")
        self.assert_contains(response, "name", user_data["name"])
        self.assert_contains(response, "email", user_data["email"])

        self.verify_contract()

    def test_update_user_contract(self):
        """Test: Contrato para actualizar usuario"""
        update_data = {"name": "Usuario Actualizado", "email": "actualizado@eci.com"}

        self.add_interaction(
            description="Actualizar usuario existente",
            state="usuario existe con ID 1",
            request={
                "method": "PUT",
                "path": "/api/v1/users/1",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token",
                },
                "body": update_data,
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(1),
                    "name": Like(update_data["name"]),
                    "email": Like(update_data["email"]),
                    "updated_at": Like("2023-01-01T00:00:00Z"),
                },
            },
        )

        response = self.client.put("/users/1", json_data=update_data)

        self.assert_status_code(response, 200)
        self.assert_contains(response, "id", 1)
        self.assert_contains(response, "name", update_data["name"])
        self.assert_contains(response, "email", update_data["email"])

        self.verify_contract()

    def test_delete_user_contract(self):
        """Test: Contrato para eliminar usuario"""
        self.add_interaction(
            description="Eliminar usuario",
            state="usuario existe con ID 1",
            request={
                "method": "DELETE",
                "path": "/api/v1/users/1",
                "headers": {"Authorization": "Bearer valid_token"},
            },
            response={"status": 204, "headers": {}},
        )

        response = self.client.delete("/users/1")

        self.assert_status_code(response, 204)

        self.verify_contract()

    def test_user_not_found_contract(self):
        """Test: Contrato para usuario no encontrado"""
        self.add_interaction(
            description="Usuario no encontrado",
            state="usuario con ID 999 no existe",
            request={
                "method": "GET",
                "path": "/api/v1/users/999",
                "headers": {"Authorization": "Bearer valid_token"},
            },
            response={
                "status": 404,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "error": "Not Found",
                    "message": "Usuario no encontrado",
                    "code": 404,
                },
            },
        )

        response = self.client.get("/users/999")

        self.assert_status_code(response, 404)
        self.assert_contains(response, "error", "Not Found")
        self.assert_contains(response, "message")

        self.verify_contract()

    def test_authentication_required_contract(self):
        """Test: Contrato para autenticación requerida"""
        self.add_interaction(
            description="Autenticación requerida",
            state="endpoint requiere autenticación",
            request={"method": "GET", "path": "/api/v1/users/1"},
            response={
                "status": 401,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "error": "Unauthorized",
                    "message": "Token de autenticación requerido",
                    "code": 401,
                },
            },
        )

        # Crear cliente sin autenticación
        from ..api_testing.client import APIClient

        unauthenticated_client = APIClient()
        unauthenticated_client.session.headers.pop("Authorization", None)

        response = unauthenticated_client.get("/users/1")

        self.assert_status_code(response, 401)
        self.assert_contains(response, "error", "Unauthorized")

        self.verify_contract()


class TestOrderServiceConsumer(PactConsumerTestBase):
    """Tests de consumidor para el servicio de órdenes"""

    def setup_method(self):
        """Setup para cada test"""
        super().setup_method()
        self.consumer = "order-frontend"
        self.provider = "order-service"
        self.pact = self.create_pact(self.consumer, self.provider)

    def test_get_order_contract(self):
        """Test: Contrato para obtener orden"""
        self.add_interaction(
            description="Obtener orden por ID",
            state="orden existe con ID 123",
            request={
                "method": "GET",
                "path": "/api/v1/orders/123",
                "headers": {"Authorization": "Bearer valid_token"},
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(123),
                    "user_id": Like(1),
                    "status": Like("pending"),
                    "total": Like(99.99),
                    "items": EachLike(
                        {
                            "product_id": Like(1),
                            "quantity": Like(2),
                            "price": Like(49.99),
                        },
                        minimum=1,
                    ),
                    "created_at": Like("2023-01-01T00:00:00Z"),
                },
            },
        )

        response = self.client.get("/orders/123")

        self.assert_status_code(response, 200)
        self.assert_contains(response, "id", 123)
        self.assert_contains(response, "user_id")
        self.assert_contains(response, "status")
        self.assert_contains(response, "total")
        self.assert_contains(response, "items")

        self.verify_contract()

    def test_create_order_contract(self):
        """Test: Contrato para crear orden"""
        order_data = {
            "user_id": 1,
            "items": [{"product_id": 1, "quantity": 2, "price": 49.99}],
        }

        self.add_interaction(
            description="Crear nueva orden",
            state="sistema acepta nuevas órdenes",
            request={
                "method": "POST",
                "path": "/api/v1/orders",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token",
                },
                "body": order_data,
            },
            response={
                "status": 201,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(123),
                    "user_id": Like(order_data["user_id"]),
                    "status": Like("pending"),
                    "total": Like(99.98),
                    "created_at": Like("2023-01-01T00:00:00Z"),
                },
            },
        )

        response = self.client.post("/orders", json_data=order_data)

        self.assert_status_code(response, 201)
        self.assert_contains(response, "id")
        self.assert_contains(response, "user_id", order_data["user_id"])
        self.assert_contains(response, "status", "pending")

        self.verify_contract()


class TestPaymentServiceConsumer(PactConsumerTestBase):
    """Tests de consumidor para el servicio de pagos"""

    def setup_method(self):
        """Setup para cada test"""
        super().setup_method()
        self.consumer = "payment-frontend"
        self.provider = "payment-service"
        self.pact = self.create_pact(self.consumer, self.provider)

    def test_process_payment_contract(self):
        """Test: Contrato para procesar pago"""
        payment_data = {
            "order_id": 123,
            "amount": 99.99,
            "payment_method": "credit_card",
            "card_token": "tok_123456789",
        }

        self.add_interaction(
            description="Procesar pago",
            state="pago válido para orden 123",
            request={
                "method": "POST",
                "path": "/api/v1/payments",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token",
                },
                "body": payment_data,
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like("pay_123456789"),
                    "order_id": Like(payment_data["order_id"]),
                    "status": Like("succeeded"),
                    "amount": Like(payment_data["amount"]),
                    "transaction_id": Like("txn_123456789"),
                    "created_at": Like("2023-01-01T00:00:00Z"),
                },
            },
        )

        response = self.client.post("/payments", json_data=payment_data)

        self.assert_status_code(response, 200)
        self.assert_contains(response, "id")
        self.assert_contains(response, "order_id", payment_data["order_id"])
        self.assert_contains(response, "status", "succeeded")

        self.verify_contract()

    def test_payment_failed_contract(self):
        """Test: Contrato para pago fallido"""
        payment_data = {
            "order_id": 123,
            "amount": 99.99,
            "payment_method": "credit_card",
            "card_token": "tok_invalid",
        }

        self.add_interaction(
            description="Pago fallido",
            state="token de tarjeta inválido",
            request={
                "method": "POST",
                "path": "/api/v1/payments",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token",
                },
                "body": payment_data,
            },
            response={
                "status": 400,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "error": "Payment Failed",
                    "message": "Token de tarjeta inválido",
                    "code": 400,
                    "details": {"decline_code": "invalid_token"},
                },
            },
        )

        response = self.client.post("/payments", json_data=payment_data)

        self.assert_status_code(response, 400)
        self.assert_contains(response, "error", "Payment Failed")
        self.assert_contains(response, "message")

        self.verify_contract()
