"""
Integración con Pact Broker para gestión de contratos
"""

import json
from dataclasses import dataclass
from typing import Any, Dict, List, Optional

import requests
from loguru import logger

from ..api_testing.config import config


@dataclass
class PactInfo:
    """Información de un contrato Pact"""

    consumer: str
    provider: str
    version: str
    content: Dict[str, Any]
    created_at: str
    updated_at: str


@dataclass
class PactVersion:
    """Versión de un contrato Pact"""

    number: str
    created_at: str
    updated_at: str
    content: Dict[str, Any]


class PactBrokerClient:
    """Cliente para interactuar con Pact Broker"""

    def __init__(self, broker_url: str = None, token: str = None):
        self.broker_url = broker_url or config.pact_broker_url
        self.token = token or config.pact_broker_token
        self.session = requests.Session()

        if self.token:
            self.session.headers.update({"Authorization": f"Bearer {self.token}"})

        logger.info(f"PactBrokerClient inicializado con URL: {self.broker_url}")

    def publish_pact(
        self, consumer: str, provider: str, version: str, pact_content: Dict[str, Any]
    ) -> bool:
        """Publicar contrato en el broker"""
        url = f"{self.broker_url}/pacts/provider/{provider}/consumer/{consumer}/version/{version}"

        try:
            response = self.session.put(
                url, json=pact_content, headers={"Content-Type": "application/json"}
            )
            response.raise_for_status()

            logger.info(f"Contrato publicado: {consumer} -> {provider} v{version}")
            return True

        except requests.exceptions.RequestException as e:
            logger.error(f"Error publicando contrato: {e}")
            return False

    def get_pact(
        self, consumer: str, provider: str, version: str = "latest"
    ) -> Optional[PactInfo]:
        """Obtener contrato del broker"""
        url = f"{self.broker_url}/pacts/provider/{provider}/consumer/{consumer}/version/{version}"

        try:
            response = self.session.get(url)
            response.raise_for_status()

            data = response.json()
            return PactInfo(
                consumer=data["consumer"]["name"],
                provider=data["provider"]["name"],
                version=data["consumer"]["version"]["number"],
                content=data,
                created_at=data["createdAt"],
                updated_at=data["updatedAt"],
            )

        except requests.exceptions.RequestException as e:
            logger.error(f"Error obteniendo contrato: {e}")
            return None

    def get_pact_versions(self, consumer: str, provider: str) -> List[PactVersion]:
        """Obtener todas las versiones de un contrato"""
        url = (
            f"{self.broker_url}/pacts/provider/{provider}/consumer/{consumer}/versions"
        )

        try:
            response = self.session.get(url)
            response.raise_for_status()

            data = response.json()
            versions = []

            for version_data in data.get("_embedded", {}).get("versions", []):
                versions.append(
                    PactVersion(
                        number=version_data["number"],
                        created_at=version_data["createdAt"],
                        updated_at=version_data["updatedAt"],
                        content=version_data,
                    )
                )

            return versions

        except requests.exceptions.RequestException as e:
            logger.error(f"Error obteniendo versiones: {e}")
            return []

    def get_matrix(
        self, consumer: str = None, provider: str = None, latest: bool = True
    ) -> List[Dict[str, Any]]:
        """Obtener matriz de compatibilidad"""
        url = f"{self.broker_url}/matrix"
        params = {}

        if consumer:
            params["q[][pacticipant]"] = consumer
        if provider:
            params["q[][pacticipant]"] = provider
        if latest:
            params["latestby"] = "cvp"

        try:
            response = self.session.get(url, params=params)
            response.raise_for_status()

            return response.json().get("matrix", [])

        except requests.exceptions.RequestException as e:
            logger.error(f"Error obteniendo matriz: {e}")
            return []

    def can_i_deploy(
        self, pacticipant: str, version: str, to: str = "production"
    ) -> Dict[str, Any]:
        """Verificar si se puede desplegar una versión"""
        url = f"{self.broker_url}/pacticipants/{pacticipant}/versions/{version}/deployed/{to}"

        try:
            response = self.session.get(url)
            response.raise_for_status()

            return response.json()

        except requests.exceptions.RequestException as e:
            logger.error(f"Error verificando despliegue: {e}")
            return {"can_deploy": False, "reason": str(e)}

    def record_deployment(
        self, pacticipant: str, version: str, environment: str = "production"
    ) -> bool:
        """Registrar despliegue de una versión"""
        url = f"{self.broker_url}/deployments/record"

        data = {
            "pacticipant": pacticipant,
            "version": version,
            "environment": environment,
        }

        try:
            response = self.session.post(url, json=data)
            response.raise_for_status()

            logger.info(
                f"Despliegue registrado: {pacticipant} v{version} en {environment}"
            )
            return True

        except requests.exceptions.RequestException as e:
            logger.error(f"Error registrando despliegue: {e}")
            return False

    def get_webhooks(self) -> List[Dict[str, Any]]:
        """Obtener webhooks configurados"""
        url = f"{self.broker_url}/webhooks"

        try:
            response = self.session.get(url)
            response.raise_for_status()

            return response.json().get("_embedded", {}).get("webhooks", [])

        except requests.exceptions.RequestException as e:
            logger.error(f"Error obteniendo webhooks: {e}")
            return []

    def create_webhook(
        self, description: str, events: List[str], request: Dict[str, Any]
    ) -> bool:
        """Crear webhook"""
        url = f"{self.broker_url}/webhooks"

        data = {"description": description, "events": events, "request": request}

        try:
            response = self.session.post(url, json=data)
            response.raise_for_status()

            logger.info(f"Webhook creado: {description}")
            return True

        except requests.exceptions.RequestException as e:
            logger.error(f"Error creando webhook: {e}")
            return False

    def trigger_webhook(
        self, webhook_uuid: str, consumer: str, provider: str, version: str
    ) -> bool:
        """Disparar webhook"""
        url = f"{self.broker_url}/webhooks/{webhook_uuid}/trigger"

        data = {"consumer": consumer, "provider": provider, "version": version}

        try:
            response = self.session.post(url, json=data)
            response.raise_for_status()

            logger.info(f"Webhook disparado: {webhook_uuid}")
            return True

        except requests.exceptions.RequestException as e:
            logger.error(f"Error disparando webhook: {e}")
            return False


class ContractManager:
    """Gestor de contratos con integración al broker"""

    def __init__(self, broker_client: PactBrokerClient = None):
        self.broker = broker_client or PactBrokerClient()
        self.contracts = {}

    def register_contract(
        self, consumer: str, provider: str, version: str, pact_content: Dict[str, Any]
    ):
        """Registrar contrato localmente"""
        key = f"{consumer}-{provider}-{version}"
        self.contracts[key] = {
            "consumer": consumer,
            "provider": provider,
            "version": version,
            "content": pact_content,
        }

        logger.info(f"Contrato registrado localmente: {key}")

    def publish_contract(
        self,
        consumer: str,
        provider: str,
        version: str,
        pact_content: Dict[str, Any] = None,
    ) -> bool:
        """Publicar contrato al broker"""
        if not pact_content:
            key = f"{consumer}-{provider}-{version}"
            if key not in self.contracts:
                logger.error(f"Contrato no encontrado: {key}")
                return False
            pact_content = self.contracts[key]["content"]

        return self.broker.publish_pact(consumer, provider, version, pact_content)

    def verify_compatibility(
        self, consumer: str, provider: str, version: str = "latest"
    ) -> bool:
        """Verificar compatibilidad de contratos"""
        matrix = self.broker.get_matrix(consumer=consumer, provider=provider)

        for entry in matrix:
            if (
                entry.get("consumer", {}).get("name") == consumer
                and entry.get("provider", {}).get("name") == provider
            ):
                return entry.get("verificationResult", {}).get("success", False)

        return False

    def get_deployment_status(
        self, pacticipant: str, version: str, environment: str = "production"
    ) -> Dict[str, Any]:
        """Obtener estado de despliegue"""
        return self.broker.can_i_deploy(pacticipant, version, environment)

    def deploy_version(
        self, pacticipant: str, version: str, environment: str = "production"
    ) -> bool:
        """Desplegar versión y registrar en broker"""
        # Verificar que se puede desplegar
        status = self.get_deployment_status(pacticipant, version, environment)
        if not status.get("can_deploy", False):
            logger.error(f"No se puede desplegar: {status.get('reason', 'Unknown')}")
            return False

        # Aquí se implementaría la lógica de despliegue real
        logger.info(f"Desplegando {pacticipant} v{version} en {environment}")

        # Registrar despliegue en broker
        return self.broker.record_deployment(pacticipant, version, environment)


# Instancia global del gestor de contratos
contract_manager = ContractManager()
