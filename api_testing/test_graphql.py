"""
Tests para API GraphQL - Ejemplo de testing de GraphQL
"""

import json
from typing import Any, Dict

import pytest

from .client import APIResponse
from .test_base import APITestBase, api_test, smoke_test


class TestGraphQLAPI(APITestBase):
    """Tests para la API GraphQL"""

    def setup_method(self):
        """Setup para cada test"""
        super().setup_method()
        self.endpoint = "/graphql"

    def _make_graphql_request(
        self, query: str, variables: Dict[str, Any] = None
    ) -> APIResponse:
        """Hacer request GraphQL"""
        payload = {"query": query, "variables": variables or {}}

        return self.client.post(
            self.endpoint,
            json_data=payload,
            headers={"Content-Type": "application/json"},
        )

    @smoke_test
    @api_test
    def test_graphql_introspection(self):
        """Test: Introspection de GraphQL"""
        introspection_query = """
        query IntrospectionQuery {
            __schema {
                queryType {
                    name
                }
                mutationType {
                    name
                }
                subscriptionType {
                    name
                }
            }
        }
        """

        response = self._make_graphql_request(introspection_query)

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 2.0)

        # Validar estructura de respuesta GraphQL
        assert "data" in response.data
        assert "__schema" in response.data["data"]
        assert "queryType" in response.data["data"]["__schema"]

    @api_test
    def test_get_users_query(self):
        """Test: Query para obtener usuarios"""
        query = """
        query GetUsers($limit: Int, $offset: Int) {
            users(limit: $limit, offset: $offset) {
                id
                name
                email
                createdAt
            }
        }
        """

        variables = {"limit": 10, "offset": 0}

        response = self._make_graphql_request(query, variables)

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 1.5)

        # Validar estructura de respuesta
        assert "data" in response.data
        assert "users" in response.data["data"]
        assert isinstance(response.data["data"]["users"], list)

        # Validar estructura de cada usuario
        for user in response.data["data"]["users"]:
            assert "id" in user
            assert "name" in user
            assert "email" in user
            assert "createdAt" in user

    @api_test
    def test_get_user_by_id_query(self):
        """Test: Query para obtener usuario por ID"""
        query = """
        query GetUser($id: ID!) {
            user(id: $id) {
                id
                name
                email
                profile {
                    bio
                    avatar
                }
            }
        }
        """

        variables = {"id": str(self.test_data.user_id)}

        response = self._make_graphql_request(query, variables)

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 1.0)

        # Validar respuesta
        assert "data" in response.data
        assert "user" in response.data["data"]
        user = response.data["data"]["user"]

        assert user["id"] == str(self.test_data.user_id)
        assert "name" in user
        assert "email" in user
        assert "profile" in user

    @api_test
    def test_create_user_mutation(self):
        """Test: Mutation para crear usuario"""
        mutation = """
        mutation CreateUser($input: CreateUserInput!) {
            createUser(input: $input) {
                id
                name
                email
                createdAt
            }
        }
        """

        variables = {
            "input": {
                "name": "Usuario GraphQL",
                "email": "graphql@eci.com",
                "password": "secure_password123",
            }
        }

        response = self._make_graphql_request(mutation, variables)

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 2.0)

        # Validar respuesta
        assert "data" in response.data
        assert "createUser" in response.data["data"]
        user = response.data["data"]["createUser"]

        assert "id" in user
        assert user["name"] == variables["input"]["name"]
        assert user["email"] == variables["input"]["email"]
        assert "createdAt" in user

    @api_test
    def test_graphql_validation_error(self):
        """Test: Error de validación en GraphQL"""
        invalid_query = """
        query GetUser($id: ID!) {
            user(id: $id) {
                id
                nonExistentField
            }
        }
        """

        variables = {"id": str(self.test_data.user_id)}

        response = self._make_graphql_request(invalid_query, variables)

        # GraphQL puede devolver 200 con errores en el campo "errors"
        assert response.status_code == 200
        assert "errors" in response.data

        # Validar estructura de error
        errors = response.data["errors"]
        assert isinstance(errors, list)
        assert len(errors) > 0

        error = errors[0]
        assert "message" in error
        assert "locations" in error
        assert "path" in error

    @api_test
    def test_graphql_syntax_error(self):
        """Test: Error de sintaxis en GraphQL"""
        invalid_query = """
        query GetUser($id: ID!) {
            user(id: $id {
                id
                name
            }
        }
        """

        response = self._make_graphql_request(invalid_query)

        self.assert_status_code(response, 400)
        assert "errors" in response.data

    @api_test
    def test_graphql_variables_validation(self):
        """Test: Validación de variables en GraphQL"""
        query = """
        query GetUser($id: ID!) {
            user(id: $id) {
                id
                name
            }
        }
        """

        # Variables faltantes
        response = self._make_graphql_request(query)

        self.assert_status_code(response, 400)
        assert "errors" in response.data

    @api_test
    def test_graphql_fragments(self):
        """Test: Uso de fragments en GraphQL"""
        query = """
        fragment UserFields on User {
            id
            name
            email
        }
        
        query GetUsers {
            users {
                ...UserFields
            }
        }
        """

        response = self._make_graphql_request(query)

        self.assert_status_code(response, 200)
        self.assert_response_time(response, 1.5)

        # Validar respuesta
        assert "data" in response.data
        assert "users" in response.data["data"]

    @api_test
    def test_graphql_subscriptions(self):
        """Test: Subscriptions en GraphQL (WebSocket)"""
        # Nota: Este test requeriría implementación de WebSocket
        # Por ahora, validamos que el endpoint soporte subscriptions
        introspection_query = """
        query {
            __schema {
                subscriptionType {
                    name
                    fields {
                        name
                        type {
                            name
                        }
                    }
                }
            }
        }
        """

        response = self._make_graphql_request(introspection_query)

        self.assert_status_code(response, 200)

        # Verificar que hay subscription type
        schema = response.data["data"]["__schema"]
        if schema.get("subscriptionType"):
            assert "name" in schema["subscriptionType"]

    @api_test
    def test_graphql_directives(self):
        """Test: Uso de directivas en GraphQL"""
        query = """
        query GetUsers {
            users {
                id
                name
                email @include(if: true)
                password @skip(if: true)
            }
        }
        """

        response = self._make_graphql_request(query)

        self.assert_status_code(response, 200)

        # Validar que los campos incluidos están presentes
        # y los excluidos no están
        users = response.data["data"]["users"]
        if users:
            user = users[0]
            assert "id" in user
            assert "name" in user
            assert "email" in user
            assert "password" not in user

    @api_test
    def test_graphql_aliases(self):
        """Test: Uso de aliases en GraphQL"""
        query = """
        query GetUsers {
            allUsers: users {
                userId: id
                fullName: name
                emailAddress: email
            }
        }
        """

        response = self._make_graphql_request(query)

        self.assert_status_code(response, 200)

        # Validar que los aliases funcionan
        users = response.data["data"]["allUsers"]
        if users:
            user = users[0]
            assert "userId" in user
            assert "fullName" in user
            assert "emailAddress" in user

    @api_test
    def test_graphql_performance(self):
        """Test: Rendimiento de queries GraphQL"""
        complex_query = """
        query GetUsersWithDetails {
            users {
                id
                name
                email
                posts {
                    id
                    title
                    content
                    comments {
                        id
                        content
                        author {
                            name
                        }
                    }
                }
                profile {
                    bio
                    avatar
                    socialLinks {
                        platform
                        url
                    }
                }
            }
        }
        """

        response = self._make_graphql_request(complex_query)

        self.assert_status_code(response, 200)
        self.assert_response_time(
            response, 3.0
        )  # Tiempo más generoso para query compleja

        # Validar estructura de respuesta compleja
        assert "data" in response.data
        users = response.data["data"]["users"]

        if users:
            user = users[0]
            assert "posts" in user
            assert "profile" in user
