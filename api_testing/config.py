"""
Configuración centralizada para el framework de testing de APIs
"""

import os
from dataclasses import dataclass
from typing import Any, Dict

from dotenv import load_dotenv

load_dotenv()


@dataclass
class APIConfig:
    """Configuración para APIs"""

    base_url: str
    version: str
    timeout: int
    retries: int
    api_key: str
    jwt_secret: str


@dataclass
class DatabaseConfig:
    """Configuración para base de datos"""

    url: str
    pool_size: int
    max_overflow: int


@dataclass
class JiraConfig:
    """Configuración para Jira"""

    url: str
    username: str
    api_token: str
    project_key: str


@dataclass
class ConfluenceConfig:
    """Configuración para Confluence"""

    url: str
    username: str
    api_token: str
    space_key: str


class Config:
    """Configuración principal del framework"""

    def __init__(self):
        self.environment = os.getenv("ENVIRONMENT", "dev")
        self.debug = os.getenv("DEBUG", "false").lower() == "true"
        self.log_level = os.getenv("LOG_LEVEL", "INFO")

        # API Configuration
        self.api = APIConfig(
            base_url=os.getenv("API_BASE_URL", "https://api.eci-dev.com"),
            version=os.getenv("API_VERSION", "v1"),
            timeout=int(os.getenv("API_TIMEOUT", "30")),
            retries=int(os.getenv("API_RETRIES", "3")),
            api_key=os.getenv("API_KEY", ""),
            jwt_secret=os.getenv("JWT_SECRET", ""),
        )

        # Database Configuration
        self.database = DatabaseConfig(
            url=os.getenv("DATABASE_URL", "postgresql://localhost:5432/eci_test"),
            pool_size=int(os.getenv("DB_POOL_SIZE", "10")),
            max_overflow=int(os.getenv("DB_MAX_OVERFLOW", "20")),
        )

        # Jira Configuration
        self.jira = JiraConfig(
            url=os.getenv("JIRA_URL", "https://eci.atlassian.net"),
            username=os.getenv("JIRA_USERNAME", ""),
            api_token=os.getenv("JIRA_API_TOKEN", ""),
            project_key=os.getenv("JIRA_PROJECT_KEY", "ECI"),
        )

        # Confluence Configuration
        self.confluence = ConfluenceConfig(
            url=os.getenv("CONFLUENCE_URL", "https://eci.atlassian.net"),
            username=os.getenv("CONFLUENCE_USERNAME", ""),
            api_token=os.getenv("CONFLUENCE_API_TOKEN", ""),
            space_key=os.getenv("CONFLUENCE_SPACE_KEY", "ECI"),
        )

        # Test Data Paths
        self.test_data_dir = os.getenv("TEST_DATA_DIR", "test_data")
        self.fixtures_dir = os.getenv("FIXTURES_DIR", "fixtures")

        # Reporting
        self.report_output_dir = os.getenv("REPORT_OUTPUT_DIR", "reports")
        self.allure_results_dir = os.getenv(
            "ALLURE_RESULTS_DIR", "reports/allure-results"
        )

    def get_api_url(self, endpoint: str = "") -> str:
        """Construye la URL completa de la API"""
        base = f"{self.api.base_url}/{self.api.version}"
        return f"{base}/{endpoint.lstrip('/')}" if endpoint else base

    def get_headers(self, content_type: str = "application/json") -> Dict[str, str]:
        """Obtiene headers por defecto para las requests"""
        headers = {
            "Content-Type": content_type,
            "User-Agent": "ECI-Testing-Framework/1.0.0",
        }

        if self.api.api_key:
            headers["Authorization"] = f"Bearer {self.api.api_key}"

        return headers


# Instancia global de configuración
config = Config()
