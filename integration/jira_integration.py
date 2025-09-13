"""
Integración con Jira para gestión de tickets y reportes de testing
"""

import json
from dataclasses import dataclass
from datetime import datetime, timedelta
from typing import Any, Dict, List, Optional, Union

from jira import JIRA
from loguru import logger

from ..api_testing.config import config


@dataclass
class TestResult:
    """Resultado de test para reporte en Jira"""

    test_name: str
    status: str  # PASSED, FAILED, SKIPPED
    duration: float
    error_message: Optional[str] = None
    test_type: str = "api"  # api, contract, integration, performance
    environment: str = "dev"


@dataclass
class TestExecution:
    """Ejecución de tests para reporte en Jira"""

    execution_id: str
    test_suite: str
    environment: str
    start_time: datetime
    end_time: datetime
    total_tests: int
    passed_tests: int
    failed_tests: int
    skipped_tests: int
    results: List[TestResult]


class JiraIntegration:
    """Integración con Jira para testing"""

    def __init__(
        self, jira_url: str = None, username: str = None, api_token: str = None
    ):
        self.jira_url = jira_url or config.jira.url
        self.username = username or config.jira.username
        self.api_token = api_token or config.jira.api_token
        self.project_key = config.jira.project_key

        # Inicializar cliente Jira
        self.jira = JIRA(
            server=self.jira_url, basic_auth=(self.username, self.api_token)
        )

        logger.info(f"JiraIntegration inicializado para proyecto: {self.project_key}")

    def create_test_execution_issue(
        self, execution: TestExecution, assignee: str = None
    ) -> str:
        """Crear issue de Jira para ejecución de tests"""
        issue_dict = {
            "project": {"key": self.project_key},
            "summary": f"Test Execution: {execution.test_suite} - {execution.environment}",
            "description": self._format_execution_description(execution),
            "issuetype": {"name": "Test Execution"},
            "priority": {"name": "Medium"},
            "labels": [
                "testing",
                "automation",
                execution.test_suite,
                execution.environment,
            ],
        }

        if assignee:
            issue_dict["assignee"] = {"name": assignee}

        try:
            issue = self.jira.create_issue(fields=issue_dict)
            logger.info(f"Issue de ejecución creado: {issue.key}")
            return issue.key

        except Exception as e:
            logger.error(f"Error creando issue de ejecución: {e}")
            raise

    def create_test_failure_issue(
        self, test_result: TestResult, execution_id: str, assignee: str = None
    ) -> str:
        """Crear issue de Jira para test fallido"""
        issue_dict = {
            "project": {"key": self.project_key},
            "summary": f"Test Failed: {test_result.test_name}",
            "description": self._format_failure_description(test_result, execution_id),
            "issuetype": {"name": "Bug"},
            "priority": {
                "name": "High" if test_result.test_type == "api" else "Medium"
            },
            "labels": [
                "testing",
                "failure",
                test_result.test_type,
                test_result.environment,
            ],
        }

        if assignee:
            issue_dict["assignee"] = {"name": assignee}

        try:
            issue = self.jira.create_issue(fields=issue_dict)
            logger.info(f"Issue de fallo creado: {issue.key}")
            return issue.key

        except Exception as e:
            logger.error(f"Error creando issue de fallo: {e}")
            raise

    def update_test_execution_status(
        self, issue_key: str, status: str, comment: str = None
    ):
        """Actualizar estado de ejecución de tests"""
        try:
            # Transición de estado
            transitions = self.jira.transitions(issue_key)
            transition_id = None

            for transition in transitions:
                if transition["name"].lower() == status.lower():
                    transition_id = transition["id"]
                    break

            if transition_id:
                self.jira.transition_issue(issue_key, transition_id)
                logger.info(f"Estado actualizado a {status} para {issue_key}")

            # Agregar comentario si se proporciona
            if comment:
                self.jira.add_comment(issue_key, comment)

        except Exception as e:
            logger.error(f"Error actualizando estado: {e}")
            raise

    def add_test_results_comment(self, issue_key: str, execution: TestExecution):
        """Agregar comentario con resultados de tests"""
        comment = self._format_execution_summary(execution)

        try:
            self.jira.add_comment(issue_key, comment)
            logger.info(f"Comentario de resultados agregado a {issue_key}")

        except Exception as e:
            logger.error(f"Error agregando comentario: {e}")
            raise

    def get_test_execution_issues(
        self,
        test_suite: str = None,
        environment: str = None,
        status: str = None,
        days: int = 7,
    ) -> List[Dict[str, Any]]:
        """Obtener issues de ejecución de tests"""
        jql_parts = [
            f"project = {self.project_key}",
            "issuetype = 'Test Execution'",
            f"created >= -{days}d",
        ]

        if test_suite:
            jql_parts.append(f"labels = {test_suite}")
        if environment:
            jql_parts.append(f"labels = {environment}")
        if status:
            jql_parts.append(f"status = '{status}'")

        jql = " AND ".join(jql_parts)

        try:
            issues = self.jira.search_issues(jql, expand="changelog")

            results = []
            for issue in issues:
                results.append(
                    {
                        "key": issue.key,
                        "summary": issue.fields.summary,
                        "status": issue.fields.status.name,
                        "created": issue.fields.created,
                        "assignee": (
                            issue.fields.assignee.displayName
                            if issue.fields.assignee
                            else None
                        ),
                        "labels": issue.fields.labels,
                    }
                )

            logger.info(f"Encontrados {len(results)} issues de ejecución")
            return results

        except Exception as e:
            logger.error(f"Error obteniendo issues: {e}")
            return []

    def get_test_failure_issues(
        self, test_type: str = None, environment: str = None, days: int = 7
    ) -> List[Dict[str, Any]]:
        """Obtener issues de tests fallidos"""
        jql_parts = [
            f"project = {self.project_key}",
            "issuetype = 'Bug'",
            "labels = testing",
            "labels = failure",
            f"created >= -{days}d",
        ]

        if test_type:
            jql_parts.append(f"labels = {test_type}")
        if environment:
            jql_parts.append(f"labels = {environment}")

        jql = " AND ".join(jql_parts)

        try:
            issues = self.jira.search_issues(jql, expand="changelog")

            results = []
            for issue in issues:
                results.append(
                    {
                        "key": issue.key,
                        "summary": issue.fields.summary,
                        "status": issue.fields.status.name,
                        "priority": issue.fields.priority.name,
                        "created": issue.fields.created,
                        "assignee": (
                            issue.fields.assignee.displayName
                            if issue.fields.assignee
                            else None
                        ),
                        "labels": issue.fields.labels,
                    }
                )

            logger.info(f"Encontrados {len(results)} issues de fallos")
            return results

        except Exception as e:
            logger.error(f"Error obteniendo issues de fallos: {e}")
            return []

    def create_test_plan_issue(
        self,
        test_plan_name: str,
        description: str,
        test_cases: List[str],
        assignee: str = None,
    ) -> str:
        """Crear issue de plan de testing"""
        issue_dict = {
            "project": {"key": self.project_key},
            "summary": f"Test Plan: {test_plan_name}",
            "description": self._format_test_plan_description(description, test_cases),
            "issuetype": {"name": "Test Plan"},
            "priority": {"name": "Medium"},
            "labels": ["testing", "plan", "automation"],
        }

        if assignee:
            issue_dict["assignee"] = {"name": assignee}

        try:
            issue = self.jira.create_issue(fields=issue_dict)
            logger.info(f"Plan de testing creado: {issue.key}")
            return issue.key

        except Exception as e:
            logger.error(f"Error creando plan de testing: {e}")
            raise

    def link_issues(
        self,
        source_issue_key: str,
        target_issue_key: str,
        link_type: str = "relates to",
    ):
        """Vincular issues"""
        try:
            self.jira.create_issue_link(
                type=link_type,
                inwardIssue=source_issue_key,
                outwardIssue=target_issue_key,
            )
            logger.info(f"Issues vinculados: {source_issue_key} -> {target_issue_key}")

        except Exception as e:
            logger.error(f"Error vinculando issues: {e}")
            raise

    def add_attachment(self, issue_key: str, file_path: str, filename: str = None):
        """Agregar archivo adjunto a issue"""
        try:
            self.jira.add_attachment(
                issue=issue_key, attachment=file_path, filename=filename
            )
            logger.info(f"Archivo adjunto agregado a {issue_key}")

        except Exception as e:
            logger.error(f"Error agregando archivo adjunto: {e}")
            raise

    def _format_execution_description(self, execution: TestExecution) -> str:
        """Formatear descripción de ejecución"""
        duration = execution.end_time - execution.start_time

        return f"""
h3. Test Execution Summary

*Test Suite:* {execution.test_suite}
*Environment:* {execution.environment}
*Duration:* {duration}
*Start Time:* {execution.start_time.strftime('%Y-%m-%d %H:%M:%S')}
*End Time:* {execution.end_time.strftime('%Y-%m-%d %H:%M:%S')}

h3. Results

*Total Tests:* {execution.total_tests}
*Passed:* {execution.passed_tests} ({execution.passed_tests/execution.total_tests*100:.1f}%)
*Failed:* {execution.failed_tests} ({execution.failed_tests/execution.total_tests*100:.1f}%)
*Skipped:* {execution.skipped_tests} ({execution.skipped_tests/execution.total_tests*100:.1f}%)

h3. Test Results

{self._format_test_results_table(execution.results)}
"""

    def _format_failure_description(
        self, test_result: TestResult, execution_id: str
    ) -> str:
        """Formatear descripción de fallo"""
        return f"""
h3. Test Failure Details

*Test Name:* {test_result.test_name}
*Test Type:* {test_result.test_type}
*Environment:* {test_result.environment}
*Duration:* {test_result.duration:.2f}s
*Execution ID:* {execution_id}

h3. Error Details

{{code}}
{test_result.error_message or 'No error message available'}
{{code}}

h3. Next Steps

1. Investigar la causa del fallo
2. Verificar configuración del entorno
3. Revisar logs de la aplicación
4. Ejecutar test manualmente si es necesario
"""

    def _format_execution_summary(self, execution: TestExecution) -> str:
        """Formatear resumen de ejecución"""
        duration = execution.end_time - execution.start_time
        success_rate = (
            (execution.passed_tests / execution.total_tests * 100)
            if execution.total_tests > 0
            else 0
        )

        return f"""
h3. Execution Summary

*Duration:* {duration}
*Success Rate:* {success_rate:.1f}%
*Passed:* {execution.passed_tests}/{execution.total_tests}
*Failed:* {execution.failed_tests}
*Skipped:* {execution.skipped_tests}

h3. Status

{self._get_status_emoji(success_rate)} {'All tests passed!' if success_rate == 100 else f'{execution.failed_tests} tests failed'}
"""

    def _format_test_plan_description(
        self, description: str, test_cases: List[str]
    ) -> str:
        """Formatear descripción de plan de testing"""
        test_cases_list = "\n".join([f"* {case}" for case in test_cases])

        return f"""
h3. Test Plan Description

{description}

h3. Test Cases

{test_cases_list}

h3. Acceptance Criteria

* All test cases must pass
* No critical bugs should be found
* Performance requirements must be met
* Security tests must pass
"""

    def _format_test_results_table(self, results: List[TestResult]) -> str:
        """Formatear tabla de resultados de tests"""
        if not results:
            return "No test results available"

        table = "|| Test Name || Status || Duration || Type ||\n"
        for result in results:
            status_emoji = (
                "✅"
                if result.status == "PASSED"
                else "❌" if result.status == "FAILED" else "⏭️"
            )
            table += f"| {result.test_name} | {status_emoji} {result.status} | {result.duration:.2f}s | {result.test_type} |\n"

        return table

    def _get_status_emoji(self, success_rate: float) -> str:
        """Obtener emoji de estado basado en tasa de éxito"""
        if success_rate == 100:
            return "🎉"
        elif success_rate >= 90:
            return "✅"
        elif success_rate >= 70:
            return "⚠️"
        else:
            return "❌"


class JiraReporter:
    """Reporter para enviar resultados de testing a Jira"""

    def __init__(self, jira_integration: JiraIntegration = None):
        self.jira = jira_integration or JiraIntegration()

    def report_test_execution(
        self,
        execution: TestExecution,
        create_failure_issues: bool = True,
        assignee: str = None,
    ) -> Dict[str, str]:
        """Reportar ejecución de tests a Jira"""
        # Crear issue principal de ejecución
        execution_issue_key = self.jira.create_test_execution_issue(execution, assignee)

        # Agregar comentario con resultados
        self.jira.add_test_results_comment(execution_issue_key, execution)

        # Crear issues para tests fallidos
        failure_issue_keys = []
        if create_failure_issues:
            for result in execution.results:
                if result.status == "FAILED":
                    failure_key = self.jira.create_test_failure_issue(
                        result, execution.execution_id, assignee
                    )
                    failure_issue_keys.append(failure_key)

                    # Vincular con issue de ejecución
                    self.jira.link_issues(execution_issue_key, failure_key, "contains")

        # Actualizar estado basado en resultados
        if execution.failed_tests == 0:
            self.jira.update_test_execution_status(
                execution_issue_key, "Done", "All tests passed successfully"
            )
        else:
            self.jira.update_test_execution_status(
                execution_issue_key,
                "In Progress",
                f"{execution.failed_tests} tests failed",
            )

        return {
            "execution_issue": execution_issue_key,
            "failure_issues": failure_issue_keys,
        }

    def report_test_plan(
        self,
        test_plan_name: str,
        description: str,
        test_cases: List[str],
        assignee: str = None,
    ) -> str:
        """Reportar plan de testing a Jira"""
        return self.jira.create_test_plan_issue(
            test_plan_name, description, test_cases, assignee
        )


# Instancia global
jira_integration = JiraIntegration()
jira_reporter = JiraReporter()
