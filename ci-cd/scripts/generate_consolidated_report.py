#!/usr/bin/env python3
"""
Script para generar reporte consolidado de testing
"""

import os
import json
import glob
from datetime import datetime
from typing import Dict, Any, List
from pathlib import Path
import xml.etree.ElementTree as ET
from dataclasses import dataclass

from api_testing.config import config


@dataclass
class TestResult:
    """Resultado de test individual"""
    name: str
    status: str
    duration: float
    suite: str
    error_message: str = None


@dataclass
class TestSuiteResult:
    """Resultado de suite de tests"""
    name: str
    total_tests: int
    passed_tests: int
    failed_tests: int
    skipped_tests: int
    duration: float
    success_rate: float
    results: List[TestResult]


class ConsolidatedReportGenerator:
    """Generador de reporte consolidado"""
    
    def __init__(self):
        self.reports_dir = Path(config.report_output_dir)
        self.reports_dir.mkdir(exist_ok=True)
        self.suites = []
    
    def generate_report(self):
        """Generar reporte consolidado"""
        print("Generando reporte consolidado de testing...")
        
        # Recopilar resultados de todas las suites
        self._collect_api_results()
        self._collect_contract_results()
        self._collect_integration_results()
        self._collect_performance_results()
        
        # Generar métricas consolidadas
        metrics = self._calculate_metrics()
        
        # Generar reporte HTML
        self._generate_html_report(metrics)
        
        # Generar reporte JSON
        self._generate_json_report(metrics)
        
        # Generar reporte para Jira
        self._generate_jira_report(metrics)
        
        print(f"Reporte consolidado generado en: {self.reports_dir}")
    
    def _collect_api_results(self):
        """Recopilar resultados de tests de API"""
        suite = self._parse_junit_xml("api-test-results.xml")
        if suite:
            suite.name = "API Tests"
            self.suites.append(suite)
    
    def _collect_contract_results(self):
        """Recopilar resultados de tests de contratos"""
        suite = self._parse_junit_xml("contract-test-results.xml")
        if suite:
            suite.name = "Contract Tests"
            self.suites.append(suite)
    
    def _collect_integration_results(self):
        """Recopilar resultados de tests de integración"""
        suite = self._parse_junit_xml("integration-test-results.xml")
        if suite:
            suite.name = "Integration Tests"
            self.suites.append(suite)
    
    def _collect_performance_results(self):
        """Recopilar resultados de tests de rendimiento"""
        suite = self._parse_junit_xml("performance-test-results.xml")
        if suite:
            suite.name = "Performance Tests"
            self.suites.append(suite)
    
    def _parse_junit_xml(self, filename: str) -> TestSuiteResult:
        """Parsear archivo JUnit XML"""
        file_path = self.reports_dir / filename
        if not file_path.exists():
            return None
        
        try:
            tree = ET.parse(file_path)
            root = tree.getroot()
            
            total_tests = int(root.get("tests", 0))
            failures = int(root.get("failures", 0))
            skipped = int(root.get("skipped", 0))
            time = float(root.get("time", 0))
            
            passed = total_tests - failures - skipped
            success_rate = (passed / total_tests * 100) if total_tests > 0 else 0
            
            results = []
            for testcase in root.findall(".//testcase"):
                name = testcase.get("name", "Unknown")
                duration = float(testcase.get("time", 0))
                
                # Determinar estado
                if testcase.find("failure") is not None:
                    status = "FAILED"
                    error_message = testcase.find("failure").get("message", "")
                elif testcase.find("skipped") is not None:
                    status = "SKIPPED"
                    error_message = ""
                else:
                    status = "PASSED"
                    error_message = ""
                
                results.append(TestResult(
                    name=name,
                    status=status,
                    duration=duration,
                    suite=filename.replace("-test-results.xml", ""),
                    error_message=error_message
                ))
            
            return TestSuiteResult(
                name=filename.replace("-test-results.xml", ""),
                total_tests=total_tests,
                passed_tests=passed,
                failed_tests=failures,
                skipped_tests=skipped,
                duration=time,
                success_rate=success_rate,
                results=results
            )
            
        except Exception as e:
            print(f"Error parseando {filename}: {e}")
            return None
    
    def _calculate_metrics(self) -> Dict[str, Any]:
        """Calcular métricas consolidadas"""
        if not self.suites:
            return {}
        
        total_tests = sum(suite.total_tests for suite in self.suites)
        total_passed = sum(suite.passed_tests for suite in self.suites)
        total_failed = sum(suite.failed_tests for suite in self.suites)
        total_skipped = sum(suite.skipped_tests for suite in self.suites)
        total_duration = sum(suite.duration for suite in self.suites)
        
        overall_success_rate = (total_passed / total_tests * 100) if total_tests > 0 else 0
        
        # Métricas por suite
        suite_metrics = {}
        for suite in self.suites:
            suite_metrics[suite.name] = {
                "total": suite.total_tests,
                "passed": suite.passed_tests,
                "failed": suite.failed_tests,
                "skipped": suite.skipped_tests,
                "duration": suite.duration,
                "success_rate": suite.success_rate
            }
        
        # Tests fallidos
        failed_tests = []
        for suite in self.suites:
            for result in suite.results:
                if result.status == "FAILED":
                    failed_tests.append({
                        "name": result.name,
                        "suite": result.suite,
                        "error": result.error_message,
                        "duration": result.duration
                    })
        
        # Tests más lentos
        all_results = []
        for suite in self.suites:
            all_results.extend(suite.results)
        
        slowest_tests = sorted(
            [r for r in all_results if r.status == "PASSED"],
            key=lambda x: x.duration,
            reverse=True
        )[:10]
        
        return {
            "overall": {
                "total_tests": total_tests,
                "passed_tests": total_passed,
                "failed_tests": total_failed,
                "skipped_tests": total_skipped,
                "duration": total_duration,
                "success_rate": overall_success_rate
            },
            "suites": suite_metrics,
            "failed_tests": failed_tests,
            "slowest_tests": [
                {
                    "name": test.name,
                    "suite": test.suite,
                    "duration": test.duration
                }
                for test in slowest_tests
            ],
            "generated_at": datetime.now().isoformat(),
            "environment": config.environment
        }
    
    def _generate_html_report(self, metrics: Dict[str, Any]):
        """Generar reporte HTML consolidado"""
        html_content = f"""
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ECI Testing Report - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}</title>
    <style>
        body {{ font-family: Arial, sans-serif; margin: 20px; background-color: #f5f5f5; }}
        .container {{ max-width: 1200px; margin: 0 auto; background: white; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }}
        .header {{ text-align: center; margin-bottom: 30px; padding-bottom: 20px; border-bottom: 2px solid #e0e0e0; }}
        .summary {{ display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 20px; margin-bottom: 30px; }}
        .metric-card {{ background: #f8f9fa; padding: 20px; border-radius: 8px; text-align: center; border-left: 4px solid #007bff; }}
        .metric-value {{ font-size: 2em; font-weight: bold; color: #007bff; }}
        .metric-label {{ color: #666; margin-top: 5px; }}
        .success {{ border-left-color: #28a745; }}
        .success .metric-value {{ color: #28a745; }}
        .warning {{ border-left-color: #ffc107; }}
        .warning .metric-value {{ color: #ffc107; }}
        .danger {{ border-left-color: #dc3545; }}
        .danger .metric-value {{ color: #dc3545; }}
        .section {{ margin-bottom: 30px; }}
        .section h2 {{ color: #333; border-bottom: 1px solid #e0e0e0; padding-bottom: 10px; }}
        table {{ width: 100%; border-collapse: collapse; margin-top: 15px; }}
        th, td {{ padding: 12px; text-align: left; border-bottom: 1px solid #e0e0e0; }}
        th {{ background-color: #f8f9fa; font-weight: bold; }}
        .status-passed {{ color: #28a745; font-weight: bold; }}
        .status-failed {{ color: #dc3545; font-weight: bold; }}
        .status-skipped {{ color: #ffc107; font-weight: bold; }}
        .error-message {{ background: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; margin-top: 5px; font-family: monospace; font-size: 0.9em; }}
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🧪 ECI Testing Report</h1>
            <p>Generado el {datetime.now().strftime('%Y-%m-%d %H:%M:%S')} - Entorno: {config.environment}</p>
        </div>
        
        <div class="summary">
            <div class="metric-card {'success' if metrics['overall']['success_rate'] == 100 else 'warning' if metrics['overall']['success_rate'] >= 90 else 'danger'}">
                <div class="metric-value">{metrics['overall']['success_rate']:.1f}%</div>
                <div class="metric-label">Tasa de Éxito</div>
            </div>
            <div class="metric-card">
                <div class="metric-value">{metrics['overall']['total_tests']}</div>
                <div class="metric-label">Total Tests</div>
            </div>
            <div class="metric-card success">
                <div class="metric-value">{metrics['overall']['passed_tests']}</div>
                <div class="metric-label">Exitosos</div>
            </div>
            <div class="metric-card {'danger' if metrics['overall']['failed_tests'] > 0 else 'success'}">
                <div class="metric-value">{metrics['overall']['failed_tests']}</div>
                <div class="metric-label">Fallidos</div>
            </div>
            <div class="metric-card">
                <div class="metric-value">{metrics['overall']['duration']:.1f}s</div>
                <div class="metric-label">Duración Total</div>
            </div>
        </div>
        
        <div class="section">
            <h2>📊 Resumen por Suite</h2>
            <table>
                <thead>
                    <tr>
                        <th>Suite</th>
                        <th>Total</th>
                        <th>Exitosos</th>
                        <th>Fallidos</th>
                        <th>Omitidos</th>
                        <th>Duración</th>
                        <th>Tasa de Éxito</th>
                    </tr>
                </thead>
                <tbody>
                    {self._generate_suites_table(metrics['suites'])}
                </tbody>
            </table>
        </div>
        
        {self._generate_failed_tests_section(metrics['failed_tests'])}
        {self._generate_slowest_tests_section(metrics['slowest_tests'])}
    </div>
</body>
</html>
"""
        
        with open(self.reports_dir / "consolidated-report.html", "w", encoding="utf-8") as f:
            f.write(html_content)
    
    def _generate_suites_table(self, suites: Dict[str, Any]) -> str:
        """Generar tabla de suites"""
        rows = []
        for name, data in suites.items():
            status_class = "success" if data["success_rate"] == 100 else "warning" if data["success_rate"] >= 90 else "danger"
            rows.append(f"""
                <tr>
                    <td>{name}</td>
                    <td>{data['total']}</td>
                    <td class="status-passed">{data['passed']}</td>
                    <td class="status-failed">{data['failed']}</td>
                    <td class="status-skipped">{data['skipped']}</td>
                    <td>{data['duration']:.1f}s</td>
                    <td class="{status_class}">{data['success_rate']:.1f}%</td>
                </tr>
            """)
        return "".join(rows)
    
    def _generate_failed_tests_section(self, failed_tests: List[Dict[str, Any]]) -> str:
        """Generar sección de tests fallidos"""
        if not failed_tests:
            return """
        <div class="section">
            <h2>✅ Tests Fallidos</h2>
            <p>¡Excelente! No hay tests fallidos.</p>
        </div>
            """
        
        rows = []
        for test in failed_tests:
            rows.append(f"""
                <tr>
                    <td>{test['name']}</td>
                    <td>{test['suite']}</td>
                    <td>{test['duration']:.2f}s</td>
                    <td><div class="error-message">{test['error']}</div></td>
                </tr>
            """)
        
        return f"""
        <div class="section">
            <h2>❌ Tests Fallidos ({len(failed_tests)})</h2>
            <table>
                <thead>
                    <tr>
                        <th>Test</th>
                        <th>Suite</th>
                        <th>Duración</th>
                        <th>Error</th>
                    </tr>
                </thead>
                <tbody>
                    {"".join(rows)}
                </tbody>
            </table>
        </div>
        """
    
    def _generate_slowest_tests_section(self, slowest_tests: List[Dict[str, Any]]) -> str:
        """Generar sección de tests más lentos"""
        if not slowest_tests:
            return ""
        
        rows = []
        for test in slowest_tests:
            rows.append(f"""
                <tr>
                    <td>{test['name']}</td>
                    <td>{test['suite']}</td>
                    <td>{test['duration']:.2f}s</td>
                </tr>
            """)
        
        return f"""
        <div class="section">
            <h2>⏱️ Tests Más Lentos</h2>
            <table>
                <thead>
                    <tr>
                        <th>Test</th>
                        <th>Suite</th>
                        <th>Duración</th>
                    </tr>
                </thead>
                <tbody>
                    {"".join(rows)}
                </tbody>
            </table>
        </div>
        """
    
    def _generate_json_report(self, metrics: Dict[str, Any]):
        """Generar reporte JSON"""
        with open(self.reports_dir / "consolidated-report.json", "w", encoding="utf-8") as f:
            json.dump(metrics, f, indent=2, ensure_ascii=False)
    
    def _generate_jira_report(self, metrics: Dict[str, Any]):
        """Generar reporte para Jira"""
        jira_content = f"""
h2. ECI Testing Report - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}

*Environment:* {config.environment}
*Generated:* {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}

h3. Overall Summary

*Total Tests:* {metrics['overall']['total_tests']}
*Passed:* {metrics['overall']['passed_tests']} ({metrics['overall']['success_rate']:.1f}%)
*Failed:* {metrics['overall']['failed_tests']}
*Skipped:* {metrics['overall']['skipped_tests']}
*Duration:* {metrics['overall']['duration']:.1f}s

h3. Suite Results

{self._generate_jira_suites_table(metrics['suites'])}

h3. Failed Tests

{self._generate_jira_failed_tests(metrics['failed_tests'])}

h3. Recommendations

{self._generate_recommendations(metrics)}
"""
        
        with open(self.reports_dir / "jira-report.txt", "w", encoding="utf-8") as f:
            f.write(jira_content)
    
    def _generate_jira_suites_table(self, suites: Dict[str, Any]) -> str:
        """Generar tabla de suites para Jira"""
        table = "|| Suite || Total || Passed || Failed || Skipped || Duration || Success Rate ||\n"
        for name, data in suites.items():
            table += f"| {name} | {data['total']} | {data['passed']} | {data['failed']} | {data['skipped']} | {data['duration']:.1f}s | {data['success_rate']:.1f}% |\n"
        return table
    
    def _generate_jira_failed_tests(self, failed_tests: List[Dict[str, Any]]) -> str:
        """Generar tests fallidos para Jira"""
        if not failed_tests:
            return "No failed tests."
        
        content = "|| Test || Suite || Duration || Error ||\n"
        for test in failed_tests:
            content += f"| {test['name']} | {test['suite']} | {test['duration']:.2f}s | {test['error']} |\n"
        return content
    
    def _generate_recommendations(self, metrics: Dict[str, Any]) -> str:
        """Generar recomendaciones"""
        recommendations = []
        
        if metrics['overall']['success_rate'] < 90:
            recommendations.append("Investigate and fix failed tests to improve success rate")
        
        if metrics['overall']['failed_tests'] > 0:
            recommendations.append("Review failed tests and update test cases if needed")
        
        if metrics['overall']['duration'] > 300:  # 5 minutes
            recommendations.append("Optimize test execution time")
        
        if not recommendations:
            recommendations.append("Continue monitoring test performance and quality")
        
        return "\n".join([f"* {rec}" for rec in recommendations])


if __name__ == "__main__":
    generator = ConsolidatedReportGenerator()
    generator.generate_report()