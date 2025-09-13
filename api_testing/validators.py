"""
Validadores para testing de APIs con validación de esquemas y contratos
"""

import json
from dataclasses import dataclass
from typing import Any, Dict, List, Optional, Union

from jsonschema import Draft7Validator, ValidationError, validate
from loguru import logger
from pydantic import BaseModel
from pydantic import ValidationError as PydanticValidationError

from .client import APIResponse


@dataclass
class ValidationResult:
    """Resultado de validación"""

    is_valid: bool
    errors: List[str]
    warnings: List[str]


class SchemaValidator:
    """Validador de esquemas JSON"""

    def __init__(self, schema: Dict[str, Any]):
        self.schema = schema
        self.validator = Draft7Validator(schema)

    def validate(self, data: Any) -> ValidationResult:
        """Valida datos contra el esquema"""
        errors = []
        warnings = []

        try:
            self.validator.validate(data)
            logger.debug("Validación de esquema exitosa")
        except ValidationError as e:
            errors.append(f"Error de validación: {e.message}")
            logger.error(f"Error de validación de esquema: {e}")

        return ValidationResult(
            is_valid=len(errors) == 0, errors=errors, warnings=warnings
        )


class PydanticValidator:
    """Validador usando Pydantic"""

    def __init__(self, model_class: BaseModel):
        self.model_class = model_class

    def validate(self, data: Any) -> ValidationResult:
        """Valida datos usando Pydantic"""
        errors = []
        warnings = []

        try:
            self.model_class(**data)
            logger.debug("Validación Pydantic exitosa")
        except PydanticValidationError as e:
            for error in e.errors():
                errors.append(f"{error['loc']}: {error['msg']}")
            logger.error(f"Error de validación Pydantic: {e}")

        return ValidationResult(
            is_valid=len(errors) == 0, errors=errors, warnings=warnings
        )


class APIResponseValidator:
    """Validador específico para respuestas de API"""

    def __init__(self):
        self.validators = []

    def add_schema_validator(self, schema: Dict[str, Any]):
        """Agregar validador de esquema"""
        self.validators.append(SchemaValidator(schema))

    def add_pydantic_validator(self, model_class: BaseModel):
        """Agregar validador Pydantic"""
        self.validators.append(PydanticValidator(model_class))

    def validate_response(self, response: APIResponse) -> ValidationResult:
        """Valida una respuesta completa de API"""
        all_errors = []
        all_warnings = []

        # Validar status code
        if not (200 <= response.status_code < 300):
            all_errors.append(f"Status code inválido: {response.status_code}")

        # Validar tiempo de respuesta
        if response.response_time > 5.0:  # 5 segundos
            all_warnings.append(
                f"Tiempo de respuesta lento: {response.response_time:.2f}s"
            )

        # Validar headers
        if "content-type" not in response.headers:
            all_warnings.append("Header Content-Type faltante")

        # Validar datos con validadores registrados
        for validator in self.validators:
            result = validator.validate(response.data)
            all_errors.extend(result.errors)
            all_warnings.extend(result.warnings)

        return ValidationResult(
            is_valid=len(all_errors) == 0, errors=all_errors, warnings=all_warnings
        )


class ContractValidator:
    """Validador de contratos entre servicios"""

    def __init__(self):
        self.contracts = {}

    def add_contract(self, service_name: str, contract: Dict[str, Any]):
        """Agregar contrato de servicio"""
        self.contracts[service_name] = contract
        logger.info(f"Contrato agregado para servicio: {service_name}")

    def validate_contract(
        self, service_name: str, response: APIResponse
    ) -> ValidationResult:
        """Valida respuesta contra contrato del servicio"""
        if service_name not in self.contracts:
            return ValidationResult(
                is_valid=False,
                errors=[f"Contrato no encontrado para servicio: {service_name}"],
                warnings=[],
            )

        contract = self.contracts[service_name]
        errors = []
        warnings = []

        # Validar estructura de respuesta
        if "expected_status" in contract:
            if response.status_code != contract["expected_status"]:
                errors.append(
                    f"Status code esperado {contract['expected_status']}, obtenido {response.status_code}"
                )

        # Validar headers requeridos
        if "required_headers" in contract:
            for header in contract["required_headers"]:
                if header not in response.headers:
                    errors.append(f"Header requerido faltante: {header}")

        # Validar esquema de datos
        if "response_schema" in contract:
            schema_validator = SchemaValidator(contract["response_schema"])
            schema_result = schema_validator.validate(response.data)
            errors.extend(schema_result.errors)
            warnings.extend(schema_result.warnings)

        return ValidationResult(
            is_valid=len(errors) == 0, errors=errors, warnings=warnings
        )


class PerformanceValidator:
    """Validador de rendimiento"""

    def __init__(self, max_response_time: float = 2.0, max_memory_usage: int = 100):
        self.max_response_time = max_response_time
        self.max_memory_usage = max_memory_usage

    def validate_performance(self, response: APIResponse) -> ValidationResult:
        """Valida métricas de rendimiento"""
        errors = []
        warnings = []

        # Validar tiempo de respuesta
        if response.response_time > self.max_response_time:
            errors.append(
                f"Tiempo de respuesta excedido: {response.response_time:.2f}s > {self.max_response_time}s"
            )

        # Validar tamaño de respuesta
        if isinstance(response.data, str):
            response_size = len(response.data.encode("utf-8"))
        else:
            response_size = len(json.dumps(response.data).encode("utf-8"))

        if response_size > self.max_memory_usage * 1024:  # Convertir a KB
            warnings.append(f"Respuesta grande: {response_size / 1024:.2f}KB")

        return ValidationResult(
            is_valid=len(errors) == 0, errors=errors, warnings=warnings
        )


# Esquemas de ejemplo para APIs comunes
USER_SCHEMA = {
    "type": "object",
    "properties": {
        "id": {"type": "integer"},
        "name": {"type": "string"},
        "email": {"type": "string", "format": "email"},
        "created_at": {"type": "string", "format": "date-time"},
        "updated_at": {"type": "string", "format": "date-time"},
    },
    "required": ["id", "name", "email"],
    "additionalProperties": False,
}

API_ERROR_SCHEMA = {
    "type": "object",
    "properties": {
        "error": {"type": "string"},
        "message": {"type": "string"},
        "code": {"type": "integer"},
        "details": {"type": "object"},
    },
    "required": ["error", "message", "code"],
    "additionalProperties": False,
}

PAGINATION_SCHEMA = {
    "type": "object",
    "properties": {
        "page": {"type": "integer", "minimum": 1},
        "per_page": {"type": "integer", "minimum": 1, "maximum": 100},
        "total": {"type": "integer", "minimum": 0},
        "total_pages": {"type": "integer", "minimum": 0},
    },
    "required": ["page", "per_page", "total", "total_pages"],
    "additionalProperties": False,
}
