# Guía de API Testing

## 📋 Tabla de Contenidos

- [Introducción](#introducción)
- [Configuración](#configuración)
- [Cliente HTTP](#cliente-http)
- [Validadores](#validadores)
- [Tests Base](#tests-base)
- [Ejemplos Prácticos](#ejemplos-prácticos)
- [Mejores Prácticas](#mejores-prácticas)
- [Troubleshooting](#troubleshooting)

## 🎯 Introducción

Esta guía te ayudará a implementar testing automatizado de APIs usando el framework ECI. Cubriremos desde la configuración básica hasta casos de uso avanzados.

## ⚙️ Configuración

### Configuración Básica

```python
from api_testing.config import config

# La configuración se carga automáticamente desde .env
print(f"API Base URL: {config.api.base_url}")
print(f"Timeout: {config.api.timeout}")
```

### Configuración Personalizada

```python
from api_testing.client import APIClient

# Cliente con configuración personalizada
client = APIClient(
    base_url="https://api.custom.com",
    timeout=60
)
```

## 🌐 Cliente HTTP

### Uso Básico

```python
from api_testing.client import APIClient

client = APIClient()

# GET request
response = client.get("/users")
print(f"Status: {response.status_code}")
print(f"Data: {response.data}")

# POST request
user_data = {
    "name": "Juan Pérez",
    "email": "juan@eci.com"
}
response = client.post("/users", json_data=user_data)

# PUT request
update_data = {"name": "Juan Carlos"}
response = client.put("/users/1", json_data=update_data)

# DELETE request
response = client.delete("/users/1")
```

### Headers Personalizados

```python
# Headers específicos para una request
headers = {
    "Authorization": "Bearer custom_token",
    "X-Custom-Header": "custom_value"
}

response = client.get("/protected", headers=headers)
```

### Parámetros de Query

```python
# Parámetros de query
params = {
    "page": 1,
    "per_page": 10,
    "filter": "active"
}

response = client.get("/users", params=params)
```

### Manejo de Archivos

```python
# Upload de archivo
files = {
    "avatar": ("avatar.jpg", open("avatar.jpg", "rb"), "image/jpeg")
}

response = client.post("/users/1/avatar", files=files)
```

### Cliente Asíncrono

```python
from api_testing.client import AsyncAPIClient
import asyncio

async def test_async():
    client = AsyncAPIClient()
    
    # Múltiples requests concurrentes
    tasks = [
        client.get("/users/1"),
        client.get("/users/2"),
        client.get("/users/3")
    ]
    
    responses = await asyncio.gather(*tasks)
    
    for response in responses:
        print(f"Status: {response.status_code}")
    
    await client.close()

# Ejecutar
asyncio.run(test_async())
```

## ✅ Validadores

### Validación de Esquemas JSON

```python
from api_testing.validators import SchemaValidator

# Definir esquema
user_schema = {
    "type": "object",
    "properties": {
        "id": {"type": "integer"},
        "name": {"type": "string"},
        "email": {"type": "string", "format": "email"},
        "created_at": {"type": "string", "format": "date-time"}
    },
    "required": ["id", "name", "email"],
    "additionalProperties": False
}

# Validar respuesta
validator = SchemaValidator(user_schema)
result = validator.validate(response.data)

if not result.is_valid:
    print(f"Errores de validación: {result.errors}")
```

### Validación con Pydantic

```python
from pydantic import BaseModel, EmailStr
from api_testing.validators import PydanticValidator

class UserModel(BaseModel):
    id: int
    name: str
    email: EmailStr
    created_at: str

# Validar con Pydantic
validator = PydanticValidator(UserModel)
result = validator.validate(response.data)
```

### Validación de Respuesta Completa

```python
from api_testing.validators import APIResponseValidator

validator = APIResponseValidator()

# Agregar validadores
validator.add_schema_validator(user_schema)
validator.add_pydantic_validator(UserModel)

# Validar respuesta completa
result = validator.validate_response(response)

if not result.is_valid:
    print(f"Errores: {result.errors}")
    print(f"Advertencias: {result.warnings}")
```

### Validación de Contratos

```python
from api_testing.validators import ContractValidator

# Definir contrato
contract = {
    "expected_status": 200,
    "required_headers": ["Content-Type", "X-Rate-Limit"],
    "response_schema": user_schema
}

contract_validator = ContractValidator()
contract_validator.add_contract("user-service", contract)

# Validar contra contrato
result = contract_validator.validate_contract("user-service", response)
```

### Validación de Rendimiento

```python
from api_testing.validators import PerformanceValidator

# Validar rendimiento
perf_validator = PerformanceValidator(max_response_time=2.0, max_memory_usage=100)
result = perf_validator.validate_performance(response)
```

## 🧪 Tests Base

### Clase Base para Tests

```python
from api_testing.test_base import APITestBase, smoke_test, api_test

class TestUsersAPI(APITestBase):
    def setup_method(self):
        super().setup_method()
        self.endpoint = "/users"
    
    @smoke_test
    @api_test
    def test_get_users_success(self):
        response = self.client.get(self.endpoint)
        
        # Validaciones básicas
        self.assert_status_code(response, 200)
        self.assert_response_time(response, 2.0)
        
        # Validar estructura
        assert isinstance(response.data, dict)
        assert "users" in response.data
```

### Tests Asíncronos

```python
from api_testing.test_base import AsyncAPITestBase

class TestAsyncAPI(AsyncAPITestBase):
    async def test_concurrent_requests(self):
        # Múltiples requests concurrentes
        tasks = [
            self.async_client.get("/users/1"),
            self.async_client.get("/users/2"),
            self.async_client.get("/users/3")
        ]
        
        responses = await asyncio.gather(*tasks)
        
        for response in responses:
            self.assert_status_code(response, 200)
```

### Tests de Contratos

```python
from api_testing.test_base import ContractTestBase

class TestUserServiceContract(ContractTestBase):
    def setup_method(self):
        super().setup_method()
        
        # Configurar contrato
        contract = {
            "expected_status": 200,
            "required_headers": ["Content-Type"],
            "response_schema": user_schema
        }
        self.setup_contract("user-service", contract)
    
    def test_user_service_contract(self):
        response = self.client.get("/users/1")
        self.assert_contract_compliance("user-service", response)
```

### Tests de Rendimiento

```python
from api_testing.test_base import PerformanceTestBase

class TestUserPerformance(PerformanceTestBase):
    def test_user_api_performance(self):
        response = self.client.get("/users")
        self.assert_performance(response, max_time=1.0)
```

### Tests de Seguridad

```python
from api_testing.test_base import SecurityTestBase

class TestUserSecurity(SecurityTestBase):
    def test_authentication_required(self):
        # Cliente sin autenticación
        unauthenticated_client = APIClient()
        unauthenticated_client.session.headers.pop("Authorization", None)
        
        response = unauthenticated_client.get("/users/1")
        self.assert_authentication_required(response)
    
    def test_https_required(self):
        response = self.client.get("/users")
        self.assert_https_required(response)
    
    def test_security_headers(self):
        response = self.client.get("/users")
        self.assert_security_headers(response)
```

## 📝 Ejemplos Prácticos

### Test de API REST Completo

```python
class TestUserAPI(APITestBase):
    def setup_method(self):
        super().setup_method()
        self.base_url = "/api/v1/users"
    
    @smoke_test
    def test_user_crud_operations(self):
        # 1. Crear usuario
        user_data = {
            "name": "Test User",
            "email": "test@eci.com",
            "password": "secure123"
        }
        
        create_response = self.client.post(self.base_url, json_data=user_data)
        self.assert_status_code(create_response, 201)
        self.assert_contains(create_response, "id")
        
        user_id = create_response.data["id"]
        
        # 2. Obtener usuario
        get_response = self.client.get(f"{self.base_url}/{user_id}")
        self.assert_status_code(get_response, 200)
        self.assert_contains(get_response, "name", user_data["name"])
        
        # 3. Actualizar usuario
        update_data = {"name": "Updated User"}
        update_response = self.client.put(f"{self.base_url}/{user_id}", json_data=update_data)
        self.assert_status_code(update_response, 200)
        self.assert_contains(update_response, "name", update_data["name"])
        
        # 4. Eliminar usuario
        delete_response = self.client.delete(f"{self.base_url}/{user_id}")
        self.assert_status_code(delete_response, 204)
        
        # 5. Verificar eliminación
        get_deleted_response = self.client.get(f"{self.base_url}/{user_id}")
        self.assert_status_code(get_deleted_response, 404)
    
    def test_user_validation_errors(self):
        # Test con datos inválidos
        invalid_data = {
            "name": "",  # Nombre vacío
            "email": "invalid-email",  # Email inválido
            "password": "123"  # Password muy corto
        }
        
        response = self.client.post(self.base_url, json_data=invalid_data)
        self.assert_status_code(response, 400)
        
        # Validar estructura de error
        assert "errors" in response.data
        assert len(response.data["errors"]) > 0
    
    def test_user_pagination(self):
        # Test de paginación
        params = {"page": 1, "per_page": 5}
        response = self.client.get(self.base_url, params=params)
        
        self.assert_status_code(response, 200)
        self.assert_contains(response, "pagination")
        
        pagination = response.data["pagination"]
        assert pagination["page"] == 1
        assert pagination["per_page"] == 5
        assert "total" in pagination
        assert "total_pages" in pagination
    
    def test_user_search(self):
        # Test de búsqueda
        params = {"search": "test", "status": "active"}
        response = self.client.get(self.base_url, params=params)
        
        self.assert_status_code(response, 200)
        # Verificar que los resultados contienen el término de búsqueda
        for user in response.data.get("users", []):
            assert "test" in user["name"].lower() or "test" in user["email"].lower()
```

### Test de GraphQL

```python
class TestGraphQLAPI(APITestBase):
    def setup_method(self):
        super().setup_method()
        self.endpoint = "/graphql"
    
    def _make_graphql_request(self, query, variables=None):
        payload = {
            "query": query,
            "variables": variables or {}
        }
        return self.client.post(self.endpoint, json_data=payload)
    
    def test_user_query(self):
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
        
        variables = {"id": "1"}
        response = self._make_graphql_request(query, variables)
        
        self.assert_status_code(response, 200)
        assert "data" in response.data
        assert "user" in response.data["data"]
        
        user = response.data["data"]["user"]
        assert user["id"] == "1"
        assert "name" in user
        assert "email" in user
    
    def test_user_mutation(self):
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
                "name": "GraphQL User",
                "email": "graphql@eci.com",
                "password": "secure123"
            }
        }
        
        response = self._make_graphql_request(mutation, variables)
        
        self.assert_status_code(response, 200)
        assert "data" in response.data
        assert "createUser" in response.data["data"]
        
        user = response.data["data"]["createUser"]
        assert "id" in user
        assert user["name"] == variables["input"]["name"]
        assert user["email"] == variables["input"]["email"]
    
    def test_graphql_errors(self):
        # Query con campo inexistente
        invalid_query = """
        query {
            user(id: "1") {
                id
                nonExistentField
            }
        }
        """
        
        response = self._make_graphql_request(invalid_query)
        
        # GraphQL puede devolver 200 con errores
        assert response.status_code == 200
        assert "errors" in response.data
        
        errors = response.data["errors"]
        assert len(errors) > 0
        assert "nonExistentField" in errors[0]["message"]
```

### Test de Rate Limiting

```python
class TestRateLimiting(APITestBase):
    def test_rate_limiting(self):
        # Hacer muchas requests rápidamente
        responses = []
        for i in range(100):
            response = self.client.get("/users")
            responses.append(response)
            
            if response.status_code == 429:  # Rate limited
                break
        
        # Debe haber al menos una respuesta con rate limiting
        rate_limited = [r for r in responses if r.status_code == 429]
        assert len(rate_limited) > 0, "Rate limiting not activated"
        
        # Verificar headers de rate limiting
        if rate_limited:
            error_response = rate_limited[0]
            assert "Retry-After" in error_response.headers
```

### Test de Concurrencia

```python
import concurrent.futures
import threading

class TestConcurrency(APITestBase):
    def test_concurrent_requests(self):
        def make_request():
            return self.client.get("/users/1")
        
        # Ejecutar 10 requests concurrentes
        with concurrent.futures.ThreadPoolExecutor(max_workers=10) as executor:
            futures = [executor.submit(make_request) for _ in range(10)]
            responses = [future.result() for future in futures]
        
        # Todas las respuestas deben ser exitosas
        for response in responses:
            self.assert_status_code(response, 200)
            self.assert_response_time(response, 5.0)  # Tiempo más generoso
```

## 🏆 Mejores Prácticas

### 1. Organización de Tests

```python
# ✅ Bueno: Tests organizados por funcionalidad
class TestUserAPI(APITestBase):
    def test_create_user(self): pass
    def test_get_user(self): pass
    def test_update_user(self): pass
    def test_delete_user(self): pass

class TestUserValidation(APITestBase):
    def test_email_validation(self): pass
    def test_password_validation(self): pass
    def test_required_fields(self): pass

# ❌ Malo: Todos los tests en una clase
class TestEverything(APITestBase):
    def test_user_create_get_update_delete_validation(self): pass
```

### 2. Naming Conventions

```python
# ✅ Bueno: Nombres descriptivos
def test_create_user_with_valid_data_returns_201(self): pass
def test_create_user_with_invalid_email_returns_400(self): pass
def test_get_user_with_nonexistent_id_returns_404(self): pass

# ❌ Malo: Nombres vagos
def test_user(self): pass
def test_error(self): pass
def test_ok(self): pass
```

### 3. Test Data Management

```python
# ✅ Bueno: Usar factories para datos de prueba
from factory import Factory, Faker

class UserFactory(Factory):
    class Meta:
        model = dict
    
    name = Faker('name')
    email = Faker('email')
    password = Faker('password')

def test_create_user(self):
    user_data = UserFactory.build()
    response = self.client.post("/users", json_data=user_data)
    # ...

# ❌ Malo: Datos hardcodeados
def test_create_user(self):
    user_data = {
        "name": "Juan",
        "email": "juan@test.com",
        "password": "123456"
    }
```

### 4. Assertions Específicas

```python
# ✅ Bueno: Assertions específicas y claras
def test_get_user(self):
    response = self.client.get("/users/1")
    
    self.assert_status_code(response, 200)
    self.assert_contains(response, "id", 1)
    self.assert_contains(response, "name")
    self.assert_json_schema(response, USER_SCHEMA)

# ❌ Malo: Assertions genéricas
def test_get_user(self):
    response = self.client.get("/users/1")
    assert response.status_code == 200
    assert "id" in response.data
```

### 5. Manejo de Errores

```python
# ✅ Bueno: Probar casos de error específicos
def test_get_nonexistent_user(self):
    response = self.client.get("/users/99999")
    
    self.assert_status_code(response, 404)
    self.assert_contains(response, "error", "Not Found")
    self.assert_contains(response, "message")

# ❌ Malo: Ignorar casos de error
def test_get_user(self):
    response = self.client.get("/users/1")
    assert response.status_code == 200
    # ¿Qué pasa si el usuario no existe?
```

### 6. Cleanup

```python
# ✅ Bueno: Limpiar datos después de cada test
class TestUserAPI(APITestBase):
    def teardown_method(self):
        # Limpiar usuarios creados durante el test
        self.client.delete(f"/users/{self.created_user_id}")
    
    def test_create_user(self):
        response = self.client.post("/users", json_data=user_data)
        self.created_user_id = response.data["id"]

# ❌ Malo: Dejar datos residuales
def test_create_user(self):
    response = self.client.post("/users", json_data=user_data)
    # No limpiar - puede afectar otros tests
```

## 🔧 Troubleshooting

### Problemas Comunes

#### 1. Tests Fallan por Timeout

```python
# Solución: Aumentar timeout
client = APIClient(timeout=60)  # 60 segundos

# O configurar globalmente
config.api.timeout = 60
```

#### 2. Tests Fallan por SSL

```python
# Solución: Deshabilitar verificación SSL (solo para testing)
import ssl
import requests

# Crear sesión con SSL deshabilitado
session = requests.Session()
session.verify = False
ssl._create_default_https_context = ssl._create_unverified_context
```

#### 3. Tests Fallan por Headers

```python
# Solución: Verificar headers requeridos
response = self.client.get("/users")
print(f"Headers: {response.headers}")

# Agregar headers faltantes
headers = {"Accept": "application/json"}
response = self.client.get("/users", headers=headers)
```

#### 4. Tests Fallan por Autenticación

```python
# Solución: Configurar autenticación correcta
client = APIClient()
client.session.headers.update({
    "Authorization": "Bearer valid_token"
})
```

### Debugging

```python
# Habilitar logging detallado
import logging
logging.basicConfig(level=logging.DEBUG)

# O usar loguru
from loguru import logger
logger.add("debug.log", level="DEBUG")

# En el test
def test_debug(self):
    response = self.client.get("/users")
    logger.debug(f"Response: {response.data}")
    logger.debug(f"Headers: {response.headers}")
```

### Performance Debugging

```python
# Medir tiempo de ejecución
import time

def test_performance(self):
    start_time = time.time()
    response = self.client.get("/users")
    end_time = time.time()
    
    duration = end_time - start_time
    print(f"Request took {duration:.2f} seconds")
    
    assert duration < 2.0, f"Request too slow: {duration:.2f}s"
```

---

Esta guía te proporciona una base sólida para implementar testing automatizado de APIs. Para más información, consulta la documentación completa del framework.