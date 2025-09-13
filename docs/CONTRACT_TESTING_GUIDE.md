# Guía de Contract Testing

## 📋 Tabla de Contenidos

- [Introducción](#introducción)
- [Conceptos Básicos](#conceptos-básicos)
- [Configuración](#configuración)
- [Consumer Tests](#consumer-tests)
- [Provider Tests](#provider-tests)
- [Pact Broker](#pact-broker)
- [Ejemplos Prácticos](#ejemplos-prácticos)
- [Mejores Prácticas](#mejores-prácticas)
- [Troubleshooting](#troubleshooting)

## 🎯 Introducción

El Contract Testing es una técnica que permite verificar que los servicios se comunican correctamente entre sí, validando que las interfaces (contratos) entre servicios se mantengan consistentes.

## 📚 Conceptos Básicos

### ¿Qué es Contract Testing?

Contract Testing verifica que:
- **Consumer** (cliente) puede comunicarse con el **Provider** (servidor)
- El Provider cumple con las expectativas del Consumer
- Los cambios en un servicio no rompan la integración

### Ventajas

- ✅ **Detección temprana** de cambios que rompen contratos
- ✅ **Desarrollo independiente** de servicios
- ✅ **Documentación viva** de las interfaces
- ✅ **Confianza** en despliegues independientes

### Pact vs Otros Frameworks

| Característica | Pact | Spring Cloud Contract | WireMock |
|----------------|------|----------------------|----------|
| Multi-lenguaje | ✅ | ❌ | ✅ |
| Consumer-driven | ✅ | ✅ | ❌ |
| Broker | ✅ | ✅ | ❌ |
| Documentación | ✅ | ✅ | ❌ |

## ⚙️ Configuración

### Instalación

```bash
# Instalar dependencias
pip install pact-python

# O usando requirements.txt
pip install -r requirements.txt
```

### Configuración Básica

```python
from pact import Consumer, Provider

# Crear contrato
pact = Consumer("user-frontend").has_pact_with(Provider("user-service"))
pact.start_service()

# Definir interacción
pact.given("usuario existe con ID 1") \
    .upon_receiving("obtener usuario por ID") \
    .with_request(method="GET", path="/api/v1/users/1") \
    .will_respond_with(status=200, body={"id": 1, "name": "Juan"})

# Ejecutar test
# ... código del test ...

# Verificar contrato
pact.verify()
pact.stop_service()
```

## 👥 Consumer Tests

### Estructura Básica

```python
from contract_testing.pact_consumer import PactConsumerTestBase

class TestUserServiceConsumer(PactConsumerTestBase):
    def setup_method(self):
        super().setup_method()
        self.consumer = "user-frontend"
        self.provider = "user-service"
        self.pact = self.create_pact(self.consumer, self.provider)
    
    def test_get_user_by_id_contract(self):
        # Definir interacción
        self.add_interaction(
            description="Obtener usuario por ID",
            state="usuario existe con ID 1",
            request={
                "method": "GET",
                "path": "/api/v1/users/1",
                "headers": {"Authorization": "Bearer valid_token"}
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(1),
                    "name": Like("Juan Pérez"),
                    "email": Like("juan@eci.com")
                }
            }
        )
        
        # Ejecutar test
        response = self.client.get("/users/1")
        
        # Verificaciones
        self.assert_status_code(response, 200)
        self.assert_contains(response, "id")
        self.assert_contains(response, "name")
        
        # Verificar contrato
        self.verify_contract()
```

### Interacciones Complejas

```python
def test_create_user_contract(self):
    user_data = {
        "name": "Nuevo Usuario",
        "email": "nuevo@eci.com",
        "password": "secure_password123"
    }
    
    self.add_interaction(
        description="Crear nuevo usuario",
        state="sistema acepta nuevos usuarios",
        request={
            "method": "POST",
            "path": "/api/v1/users",
            "headers": {"Content-Type": "application/json"},
            "body": user_data
        },
        response={
            "status": 201,
            "headers": {"Content-Type": "application/json"},
            "body": {
                "id": Like(1),
                "name": Like(user_data["name"]),
                "email": Like(user_data["email"]),
                "created_at": Like("2023-01-01T00:00:00Z")
            }
        }
    )
    
    response = self.client.post("/users", json_data=user_data)
    
    self.assert_status_code(response, 201)
    self.assert_contains(response, "id")
    self.verify_contract()
```

### Manejo de Errores

```python
def test_user_not_found_contract(self):
    self.add_interaction(
        description="Usuario no encontrado",
        state="usuario con ID 999 no existe",
        request={
            "method": "GET",
            "path": "/api/v1/users/999",
            "headers": {"Authorization": "Bearer valid_token"}
        },
        response={
            "status": 404,
            "headers": {"Content-Type": "application/json"},
            "body": {
                "error": "Not Found",
                "message": "Usuario no encontrado",
                "code": 404
            }
        }
    )
    
    response = self.client.get("/users/999")
    
    self.assert_status_code(response, 404)
    self.assert_contains(response, "error", "Not Found")
    self.verify_contract()
```

### Validaciones Avanzadas

```python
from pact import Like, EachLike, Term, Matcher

def test_get_users_list_contract(self):
    self.add_interaction(
        description="Obtener lista de usuarios",
        state="existen usuarios en el sistema",
        request={
            "method": "GET",
            "path": "/api/v1/users",
            "query": "page=1&per_page=10"
        },
        response={
            "status": 200,
            "headers": {"Content-Type": "application/json"},
            "body": {
                "users": EachLike({
                    "id": Like(1),
                    "name": Like("Usuario"),
                    "email": Like("usuario@eci.com"),
                    "status": Term("active|inactive", "active")
                }, minimum=1),
                "pagination": {
                    "page": Like(1),
                    "per_page": Like(10),
                    "total": Like(100),
                    "total_pages": Like(10)
                }
            }
        }
    )
    
    response = self.client.get("/users?page=1&per_page=10")
    
    self.assert_status_code(response, 200)
    self.assert_contains(response, "users")
    self.assert_contains(response, "pagination")
    self.verify_contract()
```

## 🏭 Provider Tests

### Estructura Básica

```python
from contract_testing.pact_provider import PactProviderTestBase, ProviderConfig

class TestUserServiceProvider(PactProviderTestBase):
    def setup_method(self):
        super().setup_method()
        self.setup_provider(ProviderConfig(
            name="user-service",
            base_url="http://localhost:8000",
            pact_url="http://localhost:9292/pacts/provider/user-service/consumer",
            version="1.0.0"
        ))
    
    def test_user_service_contracts(self):
        # Verificar todos los contratos
        self.verify_contracts()
    
    def test_user_service_consumer_contracts(self):
        # Verificar contratos específicos del consumidor
        self.verify_contracts(consumer="user-frontend")
```

### Setup de Estados del Provider

```python
class TestProviderStates:
    @staticmethod
    def setup_user_exists(user_id: int = 1):
        """Setup: Usuario existe con ID específico"""
        # Crear usuario en base de datos de prueba
        user_data = {
            "id": user_id,
            "name": "Juan Pérez",
            "email": "juan@eci.com",
            "created_at": "2023-01-01T00:00:00Z",
            "updated_at": "2023-01-01T00:00:00Z"
        }
        
        # Implementar lógica para crear usuario
        # en la base de datos de prueba
        print(f"Setting up user {user_id}: {user_data}")
    
    @staticmethod
    def setup_user_not_exists(user_id: int = 999):
        """Setup: Usuario no existe con ID específico"""
        # Asegurar que el usuario no existe
        print(f"Ensuring user {user_id} does not exist")
    
    @staticmethod
    def setup_order_exists(order_id: int = 123):
        """Setup: Orden existe con ID específico"""
        order_data = {
            "id": order_id,
            "user_id": 1,
            "status": "pending",
            "total": 99.99,
            "items": [
                {
                    "product_id": 1,
                    "quantity": 2,
                    "price": 49.99
                }
            ],
            "created_at": "2023-01-01T00:00:00Z"
        }
        
        print(f"Setting up order {order_id}: {order_data}")
```

## 🏪 Pact Broker

### Configuración

```python
from contract_testing.pact_broker import PactBrokerClient, ContractManager

# Crear cliente del broker
broker = PactBrokerClient(
    broker_url="https://pact-broker.eci.com",
    token="your_token_here"
)

# Crear gestor de contratos
contract_manager = ContractManager(broker)
```

### Publicar Contratos

```python
# Publicar contrato
success = broker.publish_pact(
    consumer="user-frontend",
    provider="user-service",
    version="1.0.0",
    pact_content=pact_data
)

if success:
    print("Contrato publicado exitosamente")
```

### Obtener Contratos

```python
# Obtener contrato
pact = broker.get_pact(
    consumer="user-frontend",
    provider="user-service",
    version="latest"
)

if pact:
    print(f"Contrato obtenido: {pact.consumer} -> {pact.provider}")
```

### Verificar Compatibilidad

```python
# Verificar compatibilidad
matrix = broker.get_matrix(
    consumer="user-frontend",
    provider="user-service"
)

for entry in matrix:
    if entry.get("verificationResult", {}).get("success"):
        print("Contrato compatible")
    else:
        print("Contrato incompatible")
```

### Gestión de Despliegues

```python
# Verificar si se puede desplegar
status = broker.can_i_deploy(
    pacticipant="user-service",
    version="1.0.0",
    to="production"
)

if status.get("can_deploy"):
    print("Se puede desplegar")
else:
    print(f"No se puede desplegar: {status.get('reason')}")

# Registrar despliegue
broker.record_deployment(
    pacticipant="user-service",
    version="1.0.0",
    environment="production"
)
```

## 📝 Ejemplos Prácticos

### E-commerce: Usuario y Orden

```python
class TestUserOrderIntegration(PactConsumerTestBase):
    def setup_method(self):
        super().setup_method()
        self.consumer = "ecommerce-frontend"
        self.provider = "user-service"
        self.pact = self.create_pact(self.consumer, self.provider)
    
    def test_user_order_flow_contract(self):
        # 1. Obtener usuario
        self.add_interaction(
            description="Obtener usuario para orden",
            state="usuario existe con ID 1",
            request={
                "method": "GET",
                "path": "/api/v1/users/1",
                "headers": {"Authorization": "Bearer valid_token"}
            },
            response={
                "status": 200,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(1),
                    "name": Like("Juan Pérez"),
                    "email": Like("juan@eci.com"),
                    "address": {
                        "street": Like("Calle Principal 123"),
                        "city": Like("Madrid"),
                        "postal_code": Like("28001")
                    }
                }
            }
        )
        
        # 2. Crear orden para usuario
        self.add_interaction(
            description="Crear orden para usuario",
            state="usuario existe con ID 1",
            request={
                "method": "POST",
                "path": "/api/v1/users/1/orders",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token"
                },
                "body": {
                    "items": [
                        {
                            "product_id": 1,
                            "quantity": 2,
                            "price": 49.99
                        }
                    ]
                }
            },
            response={
                "status": 201,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "id": Like(123),
                    "user_id": Like(1),
                    "status": Like("pending"),
                    "total": Like(99.98),
                    "created_at": Like("2023-01-01T00:00:00Z")
                }
            }
        )
        
        # Ejecutar flujo completo
        user_response = self.client.get("/users/1")
        self.assert_status_code(user_response, 200)
        
        order_data = {
            "items": [
                {
                    "product_id": 1,
                    "quantity": 2,
                    "price": 49.99
                }
            ]
        }
        order_response = self.client.post("/users/1/orders", json_data=order_data)
        self.assert_status_code(order_response, 201)
        
        self.verify_contract()
```

### Microservicios: Usuario, Orden y Pago

```python
class TestPaymentServiceConsumer(PactConsumerTestBase):
    def setup_method(self):
        super().setup_method()
        self.consumer = "payment-frontend"
        self.provider = "payment-service"
        self.pact = self.create_pact(self.consumer, self.provider)
    
    def test_payment_processing_contract(self):
        payment_data = {
            "order_id": 123,
            "amount": 99.99,
            "payment_method": "credit_card",
            "card_token": "tok_123456789"
        }
        
        self.add_interaction(
            description="Procesar pago",
            state="pago válido para orden 123",
            request={
                "method": "POST",
                "path": "/api/v1/payments",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token"
                },
                "body": payment_data
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
                    "created_at": Like("2023-01-01T00:00:00Z")
                }
            }
        )
        
        response = self.client.post("/payments", json_data=payment_data)
        
        self.assert_status_code(response, 200)
        self.assert_contains(response, "id")
        self.assert_contains(response, "status", "succeeded")
        
        self.verify_contract()
    
    def test_payment_failed_contract(self):
        payment_data = {
            "order_id": 123,
            "amount": 99.99,
            "payment_method": "credit_card",
            "card_token": "tok_invalid"
        }
        
        self.add_interaction(
            description="Pago fallido",
            state="token de tarjeta inválido",
            request={
                "method": "POST",
                "path": "/api/v1/payments",
                "headers": {
                    "Content-Type": "application/json",
                    "Authorization": "Bearer valid_token"
                },
                "body": payment_data
            },
            response={
                "status": 400,
                "headers": {"Content-Type": "application/json"},
                "body": {
                    "error": "Payment Failed",
                    "message": "Token de tarjeta inválido",
                    "code": 400,
                    "details": {
                        "decline_code": "invalid_token"
                    }
                }
            }
        )
        
        response = self.client.post("/payments", json_data=payment_data)
        
        self.assert_status_code(response, 400)
        self.assert_contains(response, "error", "Payment Failed")
        
        self.verify_contract()
```

## 🏆 Mejores Prácticas

### 1. Diseño de Contratos

```python
# ✅ Bueno: Contratos específicos y claros
def test_get_user_contract(self):
    self.add_interaction(
        description="Obtener usuario por ID",  # Descripción clara
        state="usuario existe con ID 1",       # Estado específico
        request={
            "method": "GET",
            "path": "/api/v1/users/1",
            "headers": {"Authorization": "Bearer valid_token"}
        },
        response={
            "status": 200,
            "headers": {"Content-Type": "application/json"},
            "body": {
                "id": Like(1),
                "name": Like("Juan Pérez"),
                "email": Like("juan@eci.com")
            }
        }
    )

# ❌ Malo: Contratos vagos
def test_something_contract(self):
    self.add_interaction(
        description="test",  # Descripción vaga
        state="something",   # Estado genérico
        request={"method": "GET", "path": "/api"},
        response={"status": 200, "body": {}}
    )
```

### 2. Uso de Matchers

```python
# ✅ Bueno: Usar matchers apropiados
from pact import Like, EachLike, Term, Matcher

response_body = {
    "id": Like(1),                    # Cualquier entero
    "name": Like("Juan"),             # Cualquier string
    "email": Term(r"^[^@]+@[^@]+\.[^@]+$", "juan@eci.com"),  # Regex
    "users": EachLike({               # Array con mínimo 1 elemento
        "id": Like(1),
        "name": Like("Usuario")
    }, minimum=1),
    "status": Term("active|inactive", "active")  # Enum
}

# ❌ Malo: Valores hardcodeados
response_body = {
    "id": 1,                          # Valor fijo
    "name": "Juan",                   # Valor fijo
    "email": "juan@eci.com"           # Valor fijo
}
```

### 3. Estados del Provider

```python
# ✅ Bueno: Estados específicos y reutilizables
class TestProviderStates:
    @staticmethod
    def setup_user_exists(user_id: int = 1):
        """Setup: Usuario existe con ID específico"""
        # Implementación específica
        pass
    
    @staticmethod
    def setup_user_with_orders(user_id: int = 1, order_count: int = 3):
        """Setup: Usuario con órdenes específicas"""
        # Implementación específica
        pass

# ❌ Malo: Estados genéricos
class TestProviderStates:
    @staticmethod
    def setup_data():
        """Setup: Datos de prueba"""
        # Implementación genérica
        pass
```

### 4. Versionado de Contratos

```python
# ✅ Bueno: Versionado semántico
def test_user_service_v1_contract(self):
    self.pact = self.create_pact("user-frontend", "user-service", "1.0.0")
    # ... test ...

def test_user_service_v2_contract(self):
    self.pact = self.create_pact("user-frontend", "user-service", "2.0.0")
    # ... test ...

# ❌ Malo: Sin versionado
def test_user_service_contract(self):
    self.pact = self.create_pact("user-frontend", "user-service")
    # ... test ...
```

### 5. Organización de Tests

```python
# ✅ Bueno: Tests organizados por funcionalidad
class TestUserServiceConsumer(PactConsumerTestBase):
    def test_get_user_contract(self): pass
    def test_create_user_contract(self): pass
    def test_update_user_contract(self): pass
    def test_delete_user_contract(self): pass

class TestUserServiceProvider(PactProviderTestBase):
    def test_user_service_contracts(self): pass

# ❌ Malo: Todos los tests mezclados
class TestEverything(PactConsumerTestBase):
    def test_user_get_create_update_delete_contract(self): pass
    def test_order_create_payment_contract(self): pass
```

### 6. Manejo de Errores

```python
# ✅ Bueno: Probar casos de error específicos
def test_user_not_found_contract(self):
    self.add_interaction(
        description="Usuario no encontrado",
        state="usuario con ID 999 no existe",
        request={
            "method": "GET",
            "path": "/api/v1/users/999"
        },
        response={
            "status": 404,
            "body": {
                "error": "Not Found",
                "message": "Usuario no encontrado",
                "code": 404
            }
        }
    )

# ❌ Malo: Ignorar casos de error
def test_get_user_contract(self):
    self.add_interaction(
        description="Obtener usuario",
        state="usuario existe",
        request={"method": "GET", "path": "/api/v1/users/1"},
        response={"status": 200, "body": {"id": 1}}
    )
    # ¿Qué pasa si el usuario no existe?
```

## 🔧 Troubleshooting

### Problemas Comunes

#### 1. Pact Broker No Disponible

```bash
# Verificar conectividad
curl -I https://pact-broker.eci.com

# Verificar autenticación
curl -H "Authorization: Bearer your_token" https://pact-broker.eci.com
```

#### 2. Contratos No Se Publican

```python
# Verificar configuración
broker = PactBrokerClient(
    broker_url="https://pact-broker.eci.com",
    token="your_token_here"
)

# Verificar permisos
success = broker.publish_pact(
    consumer="test-consumer",
    provider="test-provider",
    version="1.0.0",
    pact_content={"interactions": []}
)

if not success:
    print("Error publicando contrato")
```

#### 3. Provider States No Funcionan

```python
# Verificar URL del provider states
provider_config = ProviderConfig(
    name="user-service",
    base_url="http://localhost:8000",
    pact_url="http://localhost:9292/pacts/provider/user-service/consumer",
    version="1.0.0"
)

# Verificar que el endpoint existe
import requests
response = requests.get("http://localhost:8000/_pact/provider_states")
print(f"Provider states endpoint: {response.status_code}")
```

#### 4. Contratos Incompatibles

```python
# Verificar matriz de compatibilidad
matrix = broker.get_matrix(
    consumer="user-frontend",
    provider="user-service"
)

for entry in matrix:
    if not entry.get("verificationResult", {}).get("success"):
        print(f"Contrato incompatible: {entry}")
```

### Debugging

```python
# Habilitar logging detallado
import logging
logging.basicConfig(level=logging.DEBUG)

# O usar loguru
from loguru import logger
logger.add("pact.log", level="DEBUG")

# En el test
def test_debug_contract(self):
    logger.debug("Iniciando test de contrato")
    
    self.add_interaction(...)
    
    response = self.client.get("/users/1")
    logger.debug(f"Response: {response.data}")
    
    self.verify_contract()
    logger.debug("Contrato verificado exitosamente")
```

### Performance Issues

```python
# Medir tiempo de verificación
import time

def test_performance_contract(self):
    start_time = time.time()
    
    self.add_interaction(...)
    response = self.client.get("/users/1")
    self.verify_contract()
    
    end_time = time.time()
    duration = end_time - start_time
    
    print(f"Contract verification took {duration:.2f} seconds")
    assert duration < 5.0, f"Contract verification too slow: {duration:.2f}s"
```

---

Esta guía te proporciona una base sólida para implementar Contract Testing con Pact. Para más información, consulta la documentación oficial de Pact y la documentación completa del framework.