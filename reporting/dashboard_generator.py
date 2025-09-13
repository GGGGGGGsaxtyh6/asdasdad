"""
Generador de dashboards para métricas de testing
"""

import json
from typing import Dict, Any, List, Optional
from datetime import datetime, timedelta
from dataclasses import dataclass
import plotly.graph_objects as go
import plotly.express as px
from plotly.subplots import make_subplots
import pandas as pd
from loguru import logger

from .metrics_collector import MetricsCollector


@dataclass
class DashboardConfig:
    """Configuración del dashboard"""
    title: str
    theme: str = "light"
    width: int = 1200
    height: int = 800
    auto_open: bool = False


class DashboardGenerator:
    """Generador de dashboards interactivos"""
    
    def __init__(self, metrics_collector: MetricsCollector):
        self.metrics_collector = metrics_collector
        self.config = DashboardConfig(
            title="ECI Testing Dashboard",
            theme="light"
        )
    
    def generate_overview_dashboard(self, output_path: str = "dashboard_overview.html"):
        """Generar dashboard de resumen general"""
        metrics = self.metrics_collector.get_overall_metrics()
        
        # Crear subplots
        fig = make_subplots(
            rows=3, cols=2,
            subplot_titles=[
                "Test Results Overview",
                "Success Rate by Suite",
                "Test Duration Distribution",
                "Resource Usage",
                "Failed Tests Timeline",
                "Performance Trends"
            ],
            specs=[
                [{"type": "pie"}, {"type": "bar"}],
                [{"type": "histogram"}, {"type": "bar"}],
                [{"type": "scatter"}, {"type": "line"}]
            ]
        )
        
        # 1. Test Results Overview (Pie Chart)
        if metrics.get("overall"):
            overall = metrics["overall"]
            fig.add_trace(
                go.Pie(
                    labels=["Passed", "Failed", "Skipped"],
                    values=[overall["passed_tests"], overall["failed_tests"], overall["skipped_tests"]],
                    name="Test Results",
                    marker_colors=["#28a745", "#dc3545", "#ffc107"]
                ),
                row=1, col=1
            )
        
        # 2. Success Rate by Suite (Bar Chart)
        if metrics.get("suites"):
            suite_names = list(metrics["suites"].keys())
            success_rates = [metrics["suites"][suite]["success_rate"] for suite in suite_names]
            
            fig.add_trace(
                go.Bar(
                    x=suite_names,
                    y=success_rates,
                    name="Success Rate %",
                    marker_color=["#28a745" if rate >= 90 else "#ffc107" if rate >= 70 else "#dc3545" for rate in success_rates]
                ),
                row=1, col=2
            )
        
        # 3. Test Duration Distribution (Histogram)
        if self.metrics_collector.test_metrics:
            durations = [t.duration for t in self.metrics_collector.test_metrics]
            fig.add_trace(
                go.Histogram(
                    x=durations,
                    name="Duration Distribution",
                    nbinsx=20,
                    marker_color="#007bff"
                ),
                row=2, col=1
            )
        
        # 4. Resource Usage (Bar Chart)
        if metrics.get("performance"):
            perf = metrics["performance"]
            fig.add_trace(
                go.Bar(
                    x=["Memory Peak", "CPU Peak"],
                    y=[perf["memory_peak"], perf["cpu_peak"]],
                    name="Resource Usage %",
                    marker_color=["#17a2b8", "#6f42c1"]
                ),
                row=2, col=2
            )
        
        # 5. Failed Tests Timeline (Scatter)
        if metrics.get("failed_tests"):
            failed_tests = metrics["failed_tests"]
            fig.add_trace(
                go.Scatter(
                    x=list(range(len(failed_tests))),
                    y=[1] * len(failed_tests),
                    mode="markers",
                    name="Failed Tests",
                    marker=dict(size=10, color="#dc3545"),
                    text=[f"{test['name']} ({test['suite']})" for test in failed_tests],
                    hovertemplate="%{text}<br>Duration: %{customdata}s<extra></extra>",
                    customdata=[test["duration"] for test in failed_tests]
                ),
                row=3, col=1
            )
        
        # 6. Performance Trends (Line Chart)
        trend_data = self.metrics_collector.get_trend_metrics()
        if trend_data.get("success_rate_trend"):
            trend = trend_data["success_rate_trend"]
            dates = [item["date"] for item in trend]
            values = [item["value"] for item in trend]
            
            fig.add_trace(
                go.Scatter(
                    x=dates,
                    y=values,
                    mode="lines+markers",
                    name="Success Rate Trend",
                    line=dict(color="#28a745", width=3)
                ),
                row=3, col=2
            )
        
        # Actualizar layout
        fig.update_layout(
            title=f"{self.config.title} - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}",
            showlegend=True,
            height=1200,
            width=1400,
            template="plotly_white"
        )
        
        # Guardar dashboard
        fig.write_html(output_path, config={'displayModeBar': True})
        logger.info(f"Dashboard de resumen generado: {output_path}")
        
        return output_path
    
    def generate_suite_dashboard(self, suite_name: str, output_path: str = None):
        """Generar dashboard específico para una suite"""
        if not output_path:
            output_path = f"dashboard_{suite_name.lower().replace(' ', '_')}.html"
        
        suite_metrics = self.metrics_collector.get_suite_metrics(suite_name)
        if not suite_metrics:
            logger.warning(f"No se encontraron métricas para la suite: {suite_name}")
            return None
        
        # Filtrar tests de la suite
        suite_tests = [t for t in self.metrics_collector.test_metrics if t.suite == suite_name]
        
        # Crear subplots
        fig = make_subplots(
            rows=2, cols=2,
            subplot_titles=[
                f"{suite_name} - Test Results",
                "Test Duration by Status",
                "Resource Usage Over Time",
                "Error Analysis"
            ],
            specs=[
                [{"type": "pie"}, {"type": "box"}],
                [{"type": "scatter"}, {"type": "bar"}]
            ]
        )
        
        # 1. Test Results (Pie Chart)
        status_counts = {}
        for test in suite_tests:
            status_counts[test.status] = status_counts.get(test.status, 0) + 1
        
        fig.add_trace(
            go.Pie(
                labels=list(status_counts.keys()),
                values=list(status_counts.values()),
                name="Test Results",
                marker_colors=["#28a745", "#dc3545", "#ffc107"]
            ),
            row=1, col=1
        )
        
        # 2. Test Duration by Status (Box Plot)
        status_durations = {}
        for test in suite_tests:
            if test.status not in status_durations:
                status_durations[test.status] = []
            status_durations[test.status].append(test.duration)
        
        for status, durations in status_durations.items():
            fig.add_trace(
                go.Box(
                    y=durations,
                    name=status,
                    boxpoints="outliers",
                    marker_color="#28a745" if status == "PASSED" else "#dc3545" if status == "FAILED" else "#ffc107"
                ),
                row=1, col=2
            )
        
        # 3. Resource Usage Over Time (Scatter)
        if suite_tests:
            timestamps = [t.timestamp for t in suite_tests]
            memory_usage = [t.memory_usage for t in suite_tests]
            cpu_usage = [t.cpu_usage for t in suite_tests]
            
            fig.add_trace(
                go.Scatter(
                    x=timestamps,
                    y=memory_usage,
                    mode="markers+lines",
                    name="Memory Usage %",
                    line=dict(color="#17a2b8")
                ),
                row=2, col=1
            )
            
            fig.add_trace(
                go.Scatter(
                    x=timestamps,
                    y=cpu_usage,
                    mode="markers+lines",
                    name="CPU Usage %",
                    line=dict(color="#6f42c1"),
                    yaxis="y2"
                ),
                row=2, col=1
            )
        
        # 4. Error Analysis (Bar Chart)
        if suite_metrics["failed_tests"] > 0:
            failed_tests = [t for t in suite_tests if t.status == "FAILED"]
            error_types = {}
            for test in failed_tests:
                if test.error_message:
                    # Simplificar mensaje de error para agrupar
                    error_type = test.error_message.split(":")[0] if ":" in test.error_message else "Unknown Error"
                    error_types[error_type] = error_types.get(error_type, 0) + 1
            
            if error_types:
                fig.add_trace(
                    go.Bar(
                        x=list(error_types.keys()),
                        y=list(error_types.values()),
                        name="Error Types",
                        marker_color="#dc3545"
                    ),
                    row=2, col=2
                )
        
        # Actualizar layout
        fig.update_layout(
            title=f"{suite_name} Dashboard - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}",
            showlegend=True,
            height=800,
            width=1200,
            template="plotly_white"
        )
        
        # Configurar ejes secundarios para CPU
        fig.update_layout(
            yaxis2=dict(
                title="CPU Usage %",
                overlaying="y",
                side="right"
            )
        )
        
        # Guardar dashboard
        fig.write_html(output_path, config={'displayModeBar': True})
        logger.info(f"Dashboard de suite generado: {output_path}")
        
        return output_path
    
    def generate_performance_dashboard(self, output_path: str = "dashboard_performance.html"):
        """Generar dashboard de rendimiento"""
        metrics = self.metrics_collector.get_overall_metrics()
        
        # Crear subplots
        fig = make_subplots(
            rows=2, cols=2,
            subplot_titles=[
                "Test Duration Trends",
                "Resource Usage Over Time",
                "Slowest Tests",
                "Performance Metrics"
            ],
            specs=[
                [{"type": "scatter"}, {"type": "scatter"}],
                [{"type": "bar"}, {"type": "indicator"}]
            ]
        )
        
        # 1. Test Duration Trends
        if self.metrics_collector.test_metrics:
            durations = [t.duration for t in self.metrics_collector.test_metrics]
            timestamps = [t.timestamp for t in self.metrics_collector.test_metrics]
            
            fig.add_trace(
                go.Scatter(
                    x=timestamps,
                    y=durations,
                    mode="markers+lines",
                    name="Test Duration",
                    line=dict(color="#007bff"),
                    marker=dict(size=6)
                ),
                row=1, col=1
            )
        
        # 2. Resource Usage Over Time
        if self.metrics_collector.system_metrics:
            timestamps = [m.timestamp for m in self.metrics_collector.system_metrics]
            cpu_usage = [m.cpu_percent for m in self.metrics_collector.system_metrics]
            memory_usage = [m.memory_percent for m in self.metrics_collector.system_metrics]
            
            fig.add_trace(
                go.Scatter(
                    x=timestamps,
                    y=cpu_usage,
                    mode="lines",
                    name="CPU Usage %",
                    line=dict(color="#6f42c1")
                ),
                row=1, col=2
            )
            
            fig.add_trace(
                go.Scatter(
                    x=timestamps,
                    y=memory_usage,
                    mode="lines",
                    name="Memory Usage %",
                    line=dict(color="#17a2b8")
                ),
                row=1, col=2
            )
        
        # 3. Slowest Tests
        if metrics.get("slowest_tests"):
            slowest = metrics["slowest_tests"][:10]  # Top 10
            test_names = [t["name"] for t in slowest]
            durations = [t["duration"] for t in slowest]
            
            fig.add_trace(
                go.Bar(
                    x=durations,
                    y=test_names,
                    orientation="h",
                    name="Duration (s)",
                    marker_color="#ffc107"
                ),
                row=2, col=1
            )
        
        # 4. Performance Metrics (Indicators)
        if metrics.get("overall"):
            overall = metrics["overall"]
            
            # Success Rate Indicator
            fig.add_trace(
                go.Indicator(
                    mode="gauge+number",
                    value=overall["success_rate"],
                    title={"text": "Success Rate %"},
                    domain={"x": [0, 0.5], "y": [0, 0.5]},
                    gauge={
                        "axis": {"range": [None, 100]},
                        "bar": {"color": "darkblue"},
                        "steps": [
                            {"range": [0, 70], "color": "lightgray"},
                            {"range": [70, 90], "color": "yellow"},
                            {"range": [90, 100], "color": "green"}
                        ],
                        "threshold": {
                            "line": {"color": "red", "width": 4},
                            "thickness": 0.75,
                            "value": 90
                        }
                    }
                ),
                row=2, col=2
            )
        
        # Actualizar layout
        fig.update_layout(
            title=f"Performance Dashboard - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}",
            showlegend=True,
            height=800,
            width=1200,
            template="plotly_white"
        )
        
        # Guardar dashboard
        fig.write_html(output_path, config={'displayModeBar': True})
        logger.info(f"Dashboard de rendimiento generado: {output_path}")
        
        return output_path
    
    def generate_comparison_dashboard(self, suites: List[str], output_path: str = "dashboard_comparison.html"):
        """Generar dashboard de comparación entre suites"""
        fig = make_subplots(
            rows=2, cols=2,
            subplot_titles=[
                "Success Rate Comparison",
                "Duration Comparison",
                "Resource Usage Comparison",
                "Test Count Comparison"
            ],
            specs=[
                [{"type": "bar"}, {"type": "box"}],
                [{"type": "bar"}, {"type": "bar"}]
            ]
        )
        
        suite_data = {}
        for suite_name in suites:
            suite_metrics = self.metrics_collector.get_suite_metrics(suite_name)
            if suite_metrics:
                suite_data[suite_name] = suite_metrics
        
        if not suite_data:
            logger.warning("No se encontraron datos para comparar")
            return None
        
        # 1. Success Rate Comparison
        suite_names = list(suite_data.keys())
        success_rates = [suite_data[suite]["success_rate"] for suite in suite_names]
        
        fig.add_trace(
            go.Bar(
                x=suite_names,
                y=success_rates,
                name="Success Rate %",
                marker_color=["#28a745" if rate >= 90 else "#ffc107" if rate >= 70 else "#dc3545" for rate in success_rates]
            ),
            row=1, col=1
        )
        
        # 2. Duration Comparison (Box Plot)
        for suite_name in suite_names:
            suite_tests = [t for t in self.metrics_collector.test_metrics if t.suite == suite_name]
            durations = [t.duration for t in suite_tests]
            
            if durations:
                fig.add_trace(
                    go.Box(
                        y=durations,
                        name=suite_name,
                        boxpoints="outliers"
                    ),
                    row=1, col=2
                )
        
        # 3. Resource Usage Comparison
        memory_peaks = [suite_data[suite]["memory_peak"] for suite in suite_names]
        cpu_peaks = [suite_data[suite]["cpu_peak"] for suite in suite_names]
        
        fig.add_trace(
            go.Bar(
                x=suite_names,
                y=memory_peaks,
                name="Memory Peak %",
                marker_color="#17a2b8"
            ),
            row=2, col=1
        )
        
        fig.add_trace(
            go.Bar(
                x=suite_names,
                y=cpu_peaks,
                name="CPU Peak %",
                marker_color="#6f42c1"
            ),
            row=2, col=1
        )
        
        # 4. Test Count Comparison
        total_tests = [suite_data[suite]["total_tests"] for suite in suite_names]
        passed_tests = [suite_data[suite]["passed_tests"] for suite in suite_names]
        failed_tests = [suite_data[suite]["failed_tests"] for suite in suite_names]
        
        fig.add_trace(
            go.Bar(
                x=suite_names,
                y=total_tests,
                name="Total Tests",
                marker_color="#007bff"
            ),
            row=2, col=2
        )
        
        fig.add_trace(
            go.Bar(
                x=suite_names,
                y=passed_tests,
                name="Passed Tests",
                marker_color="#28a745"
            ),
            row=2, col=2
        )
        
        fig.add_trace(
            go.Bar(
                x=suite_names,
                y=failed_tests,
                name="Failed Tests",
                marker_color="#dc3545"
            ),
            row=2, col=2
        )
        
        # Actualizar layout
        fig.update_layout(
            title=f"Suite Comparison Dashboard - {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}",
            showlegend=True,
            height=800,
            width=1200,
            template="plotly_white",
            barmode="group"
        )
        
        # Guardar dashboard
        fig.write_html(output_path, config={'displayModeBar': True})
        logger.info(f"Dashboard de comparación generado: {output_path}")
        
        return output_path
    
    def generate_all_dashboards(self, output_dir: str = "dashboards"):
        """Generar todos los dashboards"""
        import os
        
        os.makedirs(output_dir, exist_ok=True)
        
        dashboards = []
        
        # Dashboard general
        overview_path = os.path.join(output_dir, "overview.html")
        dashboards.append(self.generate_overview_dashboard(overview_path))
        
        # Dashboard de rendimiento
        performance_path = os.path.join(output_dir, "performance.html")
        dashboards.append(self.generate_performance_dashboard(performance_path))
        
        # Dashboards por suite
        suite_names = set(t.suite for t in self.metrics_collector.test_metrics)
        for suite_name in suite_names:
            suite_path = os.path.join(output_dir, f"{suite_name.lower().replace(' ', '_')}.html")
            dashboards.append(self.generate_suite_dashboard(suite_name, suite_path))
        
        # Dashboard de comparación
        if len(suite_names) > 1:
            comparison_path = os.path.join(output_dir, "comparison.html")
            dashboards.append(self.generate_comparison_dashboard(list(suite_names), comparison_path))
        
        logger.info(f"Todos los dashboards generados en: {output_dir}")
        return dashboards


# Instancia global
dashboard_generator = DashboardGenerator(metrics_collector)