"""
Integración con Confluence para documentación de testing
"""

import json
from dataclasses import dataclass
from datetime import datetime, timedelta
from typing import Any, Dict, List, Optional, Union

from atlassian import Confluence
from loguru import logger

from ..api_testing.config import config


@dataclass
class TestDocumentation:
    """Documentación de testing para Confluence"""

    title: str
    content: str
    test_type: str  # api, contract, integration, performance
    environment: str
    version: str = "1.0.0"
    tags: List[str] = None


@dataclass
class TestReport:
    """Reporte de testing para Confluence"""

    title: str
    summary: str
    test_results: Dict[str, Any]
    metrics: Dict[str, Any]
    recommendations: List[str]
    environment: str
    execution_date: datetime


class ConfluenceIntegration:
    """Integración con Confluence para documentación de testing"""

    def __init__(
        self, confluence_url: str = None, username: str = None, api_token: str = None
    ):
        self.confluence_url = confluence_url or config.confluence.url
        self.username = username or config.confluence.username
        self.api_token = api_token or config.confluence.api_token
        self.space_key = config.confluence.space_key

        # Inicializar cliente Confluence
        self.confluence = Confluence(
            url=self.confluence_url, username=self.username, password=self.api_token
        )

        logger.info(
            f"ConfluenceIntegration inicializado para espacio: {self.space_key}"
        )

    def create_test_documentation_page(
        self, documentation: TestDocumentation, parent_page_id: str = None
    ) -> str:
        """Crear página de documentación de testing"""
        try:
            # Crear contenido de la página
            content = self._format_documentation_content(documentation)

            # Crear página
            page = self.confluence.create_page(
                space=self.space_key,
                title=documentation.title,
                body=content,
                parent_id=parent_page_id,
                type="page",
            )

            # Agregar labels
            if documentation.tags:
                self.confluence.add_page_labels(page["id"], documentation.tags)

            logger.info(f"Página de documentación creada: {page['id']}")
            return page["id"]

        except Exception as e:
            logger.error(f"Error creando página de documentación: {e}")
            raise

    def create_test_report_page(
        self, report: TestReport, parent_page_id: str = None
    ) -> str:
        """Crear página de reporte de testing"""
        try:
            # Crear contenido del reporte
            content = self._format_report_content(report)

            # Crear página
            page = self.confluence.create_page(
                space=self.space_key,
                title=report.title,
                body=content,
                parent_id=parent_page_id,
                type="page",
            )

            # Agregar labels
            labels = [
                "testing",
                "report",
                report.environment,
                report.execution_date.strftime("%Y-%m"),
            ]
            self.confluence.add_page_labels(page["id"], labels)

            logger.info(f"Página de reporte creada: {page['id']}")
            return page["id"]

        except Exception as e:
            logger.error(f"Error creando página de reporte: {e}")
            raise

    def update_test_documentation(self, page_id: str, documentation: TestDocumentation):
        """Actualizar documentación de testing existente"""
        try:
            # Obtener página actual
            page = self.confluence.get_page_by_id(page_id, expand="body.storage")
            current_content = page["body"]["storage"]["value"]

            # Crear nuevo contenido
            new_content = self._format_documentation_content(documentation)

            # Actualizar página
            self.confluence.update_page(
                page_id=page_id, title=documentation.title, body=new_content
            )

            logger.info(f"Documentación actualizada: {page_id}")

        except Exception as e:
            logger.error(f"Error actualizando documentación: {e}")
            raise

    def create_test_dashboard(
        self,
        dashboard_title: str,
        test_metrics: Dict[str, Any],
        parent_page_id: str = None,
    ) -> str:
        """Crear dashboard de testing"""
        try:
            content = self._format_dashboard_content(dashboard_title, test_metrics)

            page = self.confluence.create_page(
                space=self.space_key,
                title=dashboard_title,
                body=content,
                parent_id=parent_page_id,
                type="page",
            )

            # Agregar labels
            labels = ["testing", "dashboard", "metrics"]
            self.confluence.add_page_labels(page["id"], labels)

            logger.info(f"Dashboard de testing creado: {page['id']}")
            return page["id"]

        except Exception as e:
            logger.error(f"Error creando dashboard: {e}")
            raise

    def create_test_plan_page(
        self,
        plan_title: str,
        plan_description: str,
        test_cases: List[Dict[str, Any]],
        parent_page_id: str = None,
    ) -> str:
        """Crear página de plan de testing"""
        try:
            content = self._format_test_plan_content(
                plan_title, plan_description, test_cases
            )

            page = self.confluence.create_page(
                space=self.space_key,
                title=plan_title,
                body=content,
                parent_id=parent_page_id,
                type="page",
            )

            # Agregar labels
            labels = ["testing", "plan", "test-cases"]
            self.confluence.add_page_labels(page["id"], labels)

            logger.info(f"Plan de testing creado: {page['id']}")
            return page["id"]

        except Exception as e:
            logger.error(f"Error creando plan de testing: {e}")
            raise

    def create_api_documentation_page(
        self,
        api_name: str,
        api_description: str,
        endpoints: List[Dict[str, Any]],
        parent_page_id: str = None,
    ) -> str:
        """Crear página de documentación de API"""
        try:
            content = self._format_api_documentation_content(
                api_name, api_description, endpoints
            )

            page = self.confluence.create_page(
                space=self.space_key,
                title=f"API Documentation: {api_name}",
                body=content,
                parent_id=parent_page_id,
                type="page",
            )

            # Agregar labels
            labels = ["api", "documentation", "testing", api_name.lower()]
            self.confluence.add_page_labels(page["id"], labels)

            logger.info(f"Documentación de API creada: {page['id']}")
            return page["id"]

        except Exception as e:
            logger.error(f"Error creando documentación de API: {e}")
            raise

    def create_contract_documentation_page(
        self,
        consumer: str,
        provider: str,
        contract_details: Dict[str, Any],
        parent_page_id: str = None,
    ) -> str:
        """Crear página de documentación de contratos"""
        try:
            content = self._format_contract_documentation_content(
                consumer, provider, contract_details
            )

            page = self.confluence.create_page(
                space=self.space_key,
                title=f"Contract: {consumer} -> {provider}",
                body=content,
                parent_id=parent_page_id,
                type="page",
            )

            # Agregar labels
            labels = ["contract", "documentation", "testing", consumer, provider]
            self.confluence.add_page_labels(page["id"], labels)

            logger.info(f"Documentación de contrato creada: {page['id']}")
            return page["id"]

        except Exception as e:
            logger.error(f"Error creando documentación de contrato: {e}")
            raise

    def search_test_documentation(
        self, query: str, test_type: str = None, environment: str = None
    ) -> List[Dict[str, Any]]:
        """Buscar documentación de testing"""
        try:
            # Construir CQL (Confluence Query Language)
            cql_parts = [f'text ~ "{query}"', f"space = {self.space_key}"]

            if test_type:
                cql_parts.append(f"label = {test_type}")
            if environment:
                cql_parts.append(f"label = {environment}")

            cql = " AND ".join(cql_parts)

            results = self.confluence.cql(cql, limit=50)

            pages = []
            for result in results["results"]:
                pages.append(
                    {
                        "id": result["content"]["id"],
                        "title": result["content"]["title"],
                        "type": result["content"]["type"],
                        "created": result["content"]["created"],
                        "labels": [
                            label["name"]
                            for label in result["content"].get("labels", [])
                        ],
                    }
                )

            logger.info(f"Encontradas {len(pages)} páginas de documentación")
            return pages

        except Exception as e:
            logger.error(f"Error buscando documentación: {e}")
            return []

    def get_page_content(self, page_id: str) -> str:
        """Obtener contenido de una página"""
        try:
            page = self.confluence.get_page_by_id(page_id, expand="body.storage")
            return page["body"]["storage"]["value"]

        except Exception as e:
            logger.error(f"Error obteniendo contenido de página: {e}")
            return ""

    def add_comment_to_page(self, page_id: str, comment: str):
        """Agregar comentario a una página"""
        try:
            self.confluence.add_comment(page_id, comment)
            logger.info(f"Comentario agregado a página {page_id}")

        except Exception as e:
            logger.error(f"Error agregando comentario: {e}")
            raise

    def _format_documentation_content(self, documentation: TestDocumentation) -> str:
        """Formatear contenido de documentación"""
        return f"""
<h1>{documentation.title}</h1>

<div class="confluence-information-macro confluence-information-macro-information">
    <span class="aui-icon aui-icon-small aui-iconfont-info confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>Test Type:</strong> {documentation.test_type}</p>
        <p><strong>Environment:</strong> {documentation.environment}</p>
        <p><strong>Version:</strong> {documentation.version}</p>
    </div>
</div>

{documentation.content}

<h2>Related Documentation</h2>
<ul>
    <li><a href="/display/{self.space_key}/Test+Execution+Guide">Test Execution Guide</a></li>
    <li><a href="/display/{self.space_key}/API+Testing+Standards">API Testing Standards</a></li>
    <li><a href="/display/{self.space_key}/Contract+Testing+Guide">Contract Testing Guide</a></li>
</ul>
"""

    def _format_report_content(self, report: TestReport) -> str:
        """Formatear contenido de reporte"""
        return f"""
<h1>{report.title}</h1>

<div class="confluence-information-macro confluence-information-macro-information">
    <span class="aui-icon aui-icon-small aui-iconfont-info confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>Execution Date:</strong> {report.execution_date.strftime('%Y-%m-%d %H:%M:%S')}</p>
        <p><strong>Environment:</strong> {report.environment}</p>
    </div>
</div>

<h2>Executive Summary</h2>
<p>{report.summary}</p>

<h2>Test Results</h2>
{self._format_test_results_table(report.test_results)}

<h2>Metrics</h2>
{self._format_metrics_table(report.metrics)}

<h2>Recommendations</h2>
<ul>
{''.join([f'<li>{rec}</li>' for rec in report.recommendations])}
</ul>

<h2>Next Steps</h2>
<ol>
    <li>Review failed tests and address issues</li>
    <li>Update test cases based on findings</li>
    <li>Schedule follow-up testing if needed</li>
    <li>Update documentation as required</li>
</ol>
"""

    def _format_dashboard_content(self, title: str, metrics: Dict[str, Any]) -> str:
        """Formatear contenido de dashboard"""
        return f"""
<h1>{title}</h1>

<div class="confluence-information-macro confluence-information-macro-information">
    <span class="aui-icon aui-icon-small aui-iconfont-info confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>Last Updated:</strong> {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}</p>
    </div>
</div>

<h2>Test Metrics Overview</h2>
{self._format_metrics_dashboard(metrics)}

<h2>Recent Test Executions</h2>
<p>Recent test execution data will be displayed here.</p>

<h2>Test Coverage</h2>
<p>Test coverage information will be displayed here.</p>

<h2>Performance Metrics</h2>
<p>Performance testing metrics will be displayed here.</p>
"""

    def _format_test_plan_content(
        self, title: str, description: str, test_cases: List[Dict[str, Any]]
    ) -> str:
        """Formatear contenido de plan de testing"""
        test_cases_table = (
            "|| Test Case ID || Description || Type || Priority || Status ||\n"
        )
        for case in test_cases:
            test_cases_table += f"| {case.get('id', 'N/A')} | {case.get('description', 'N/A')} | {case.get('type', 'N/A')} | {case.get('priority', 'N/A')} | {case.get('status', 'Not Started')} |\n"

        return f"""
<h1>{title}</h1>

<div class="confluence-information-macro confluence-information-macro-information">
    <span class="aui-icon aui-icon-small aui-iconfont-info confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>Created:</strong> {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}</p>
        <p><strong>Total Test Cases:</strong> {len(test_cases)}</p>
    </div>
</div>

<h2>Plan Description</h2>
<p>{description}</p>

<h2>Test Cases</h2>
{test_cases_table}

<h2>Test Environment</h2>
<ul>
    <li>Development Environment</li>
    <li>Staging Environment</li>
    <li>Production Environment (if applicable)</li>
</ul>

<h2>Acceptance Criteria</h2>
<ul>
    <li>All test cases must pass</li>
    <li>No critical bugs should be found</li>
    <li>Performance requirements must be met</li>
    <li>Security tests must pass</li>
</ul>
"""

    def _format_api_documentation_content(
        self, api_name: str, description: str, endpoints: List[Dict[str, Any]]
    ) -> str:
        """Formatear contenido de documentación de API"""
        endpoints_section = ""
        for endpoint in endpoints:
            endpoints_section += f"""
<h3>{endpoint.get('method', 'GET')} {endpoint.get('path', '/')}</h3>
<p><strong>Description:</strong> {endpoint.get('description', 'No description available')}</p>
<p><strong>Parameters:</strong></p>
<ul>
{''.join([f'<li><strong>{param.get("name", "N/A")}:</strong> {param.get("type", "N/A")} - {param.get("description", "No description")}</li>' for param in endpoint.get('parameters', [])])}
</ul>
<p><strong>Response:</strong></p>
<pre><code>{json.dumps(endpoint.get('response', {}), indent=2)}</code></pre>
<hr>
"""

        return f"""
<h1>API Documentation: {api_name}</h1>

<div class="confluence-information-macro confluence-information-macro-information">
    <span class="aui-icon aui-icon-small aui-iconfont-info confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>API Name:</strong> {api_name}</p>
        <p><strong>Version:</strong> 1.0.0</p>
        <p><strong>Base URL:</strong> https://api.eci.com/v1</p>
    </div>
</div>

<h2>Overview</h2>
<p>{description}</p>

<h2>Authentication</h2>
<p>This API uses Bearer token authentication. Include the token in the Authorization header:</p>
<pre><code>Authorization: Bearer your_token_here</code></pre>

<h2>Endpoints</h2>
{endpoints_section}

<h2>Error Handling</h2>
<p>All errors follow a consistent format:</p>
<pre><code>{{
    "error": "Error Type",
    "message": "Human readable error message",
    "code": 400,
    "details": {{}}
}}</code></pre>
"""

    def _format_contract_documentation_content(
        self, consumer: str, provider: str, contract_details: Dict[str, Any]
    ) -> str:
        """Formatear contenido de documentación de contrato"""
        return f"""
<h1>Contract Documentation: {consumer} -> {provider}</h1>

<div class="confluence-information-macro confluence-information-macro-information">
    <span class="aui-icon aui-icon-small aui-iconfont-info confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>Consumer:</strong> {consumer}</p>
        <p><strong>Provider:</strong> {provider}</p>
        <p><strong>Version:</strong> {contract_details.get('version', '1.0.0')}</p>
        <p><strong>Last Updated:</strong> {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}</p>
    </div>
</div>

<h2>Contract Overview</h2>
<p>{contract_details.get('description', 'No description available')}</p>

<h2>Interactions</h2>
{self._format_contract_interactions(contract_details.get('interactions', []))}

<h2>Data Models</h2>
{self._format_contract_data_models(contract_details.get('data_models', {}))}

<h2>Testing</h2>
<p>This contract is automatically tested using Pact. Tests are run on every build and deployment.</p>

<h2>Version History</h2>
<p>Contract version history and changes will be tracked here.</p>
"""

    def _format_test_results_table(self, results: Dict[str, Any]) -> str:
        """Formatear tabla de resultados de tests"""
        if not results:
            return "<p>No test results available</p>"

        table = (
            "|| Test Suite || Passed || Failed || Skipped || Total || Success Rate ||\n"
        )
        for suite, data in results.items():
            total = data.get("total", 0)
            passed = data.get("passed", 0)
            failed = data.get("failed", 0)
            skipped = data.get("skipped", 0)
            success_rate = (passed / total * 100) if total > 0 else 0

            table += f"| {suite} | {passed} | {failed} | {skipped} | {total} | {success_rate:.1f}% |\n"

        return table

    def _format_metrics_table(self, metrics: Dict[str, Any]) -> str:
        """Formatear tabla de métricas"""
        if not metrics:
            return "<p>No metrics available</p>"

        table = "|| Metric || Value || Target || Status ||\n"
        for metric, value in metrics.items():
            target = value.get("target", "N/A")
            actual = value.get("value", "N/A")
            status = (
                "✅"
                if value.get("status") == "pass"
                else "❌" if value.get("status") == "fail" else "⚠️"
            )

            table += f"| {metric} | {actual} | {target} | {status} |\n"

        return table

    def _format_metrics_dashboard(self, metrics: Dict[str, Any]) -> str:
        """Formatear dashboard de métricas"""
        return f"""
<div class="confluence-information-macro confluence-information-macro-note">
    <span class="aui-icon aui-icon-small aui-iconfont-approve confluence-information-macro-icon"></span>
    <div class="confluence-information-macro-body">
        <p><strong>Overall Test Health:</strong> {metrics.get('overall_health', 'Good')}</p>
        <p><strong>Last Test Run:</strong> {metrics.get('last_run', 'N/A')}</p>
        <p><strong>Success Rate:</strong> {metrics.get('success_rate', 'N/A')}%</p>
    </div>
</div>
"""

    def _format_contract_interactions(self, interactions: List[Dict[str, Any]]) -> str:
        """Formatear interacciones de contrato"""
        if not interactions:
            return "<p>No interactions defined</p>"

        content = ""
        for i, interaction in enumerate(interactions, 1):
            content += f"""
<h3>Interaction {i}: {interaction.get('description', 'N/A')}</h3>
<p><strong>State:</strong> {interaction.get('state', 'N/A')}</p>
<p><strong>Request:</strong></p>
<pre><code>{json.dumps(interaction.get('request', {}), indent=2)}</code></pre>
<p><strong>Response:</strong></p>
<pre><code>{json.dumps(interaction.get('response', {}), indent=2)}</code></pre>
<hr>
"""

        return content

    def _format_contract_data_models(self, data_models: Dict[str, Any]) -> str:
        """Formatear modelos de datos de contrato"""
        if not data_models:
            return "<p>No data models defined</p>"

        content = ""
        for model_name, model_schema in data_models.items():
            content += f"""
<h3>{model_name}</h3>
<pre><code>{json.dumps(model_schema, indent=2)}</code></pre>
"""

        return content


class ConfluenceReporter:
    """Reporter para enviar documentación de testing a Confluence"""

    def __init__(self, confluence_integration: ConfluenceIntegration = None):
        self.confluence = confluence_integration or ConfluenceIntegration()

    def report_test_execution(
        self,
        execution_id: str,
        test_suite: str,
        environment: str,
        results: Dict[str, Any],
        metrics: Dict[str, Any],
    ) -> str:
        """Reportar ejecución de tests a Confluence"""
        report = TestReport(
            title=f"Test Execution Report: {test_suite} - {environment}",
            summary=f"Test execution completed for {test_suite} in {environment} environment",
            test_results=results,
            metrics=metrics,
            recommendations=self._generate_recommendations(results, metrics),
            environment=environment,
            execution_date=datetime.now(),
        )

        return self.confluence.create_test_report_page(report)

    def create_test_documentation(
        self,
        title: str,
        content: str,
        test_type: str,
        environment: str,
        tags: List[str] = None,
    ) -> str:
        """Crear documentación de testing en Confluence"""
        documentation = TestDocumentation(
            title=title,
            content=content,
            test_type=test_type,
            environment=environment,
            tags=tags or [],
        )

        return self.confluence.create_test_documentation_page(documentation)

    def _generate_recommendations(
        self, results: Dict[str, Any], metrics: Dict[str, Any]
    ) -> List[str]:
        """Generar recomendaciones basadas en resultados y métricas"""
        recommendations = []

        # Analizar resultados de tests
        for suite, data in results.items():
            success_rate = (data.get("passed", 0) / data.get("total", 1)) * 100
            if success_rate < 90:
                recommendations.append(
                    f"Improve test success rate for {suite} (currently {success_rate:.1f}%)"
                )

        # Analizar métricas
        if metrics.get("response_time", {}).get("value", 0) > metrics.get(
            "response_time", {}
        ).get("target", 5):
            recommendations.append("Optimize API response times")

        if metrics.get("coverage", {}).get("value", 0) < metrics.get(
            "coverage", {}
        ).get("target", 80):
            recommendations.append("Increase test coverage")

        if not recommendations:
            recommendations.append("Continue monitoring test performance and quality")

        return recommendations


# Instancia global
confluence_integration = ConfluenceIntegration()
confluence_reporter = ConfluenceReporter()
