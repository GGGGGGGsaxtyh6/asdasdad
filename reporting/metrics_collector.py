"""
Colector de métricas para testing automatizado
"""

import json
import time
from collections import defaultdict
from dataclasses import asdict, dataclass
from datetime import datetime, timedelta
from typing import Any, Dict, List, Optional

import psutil
import requests
from loguru import logger

from ..api_testing.config import config


@dataclass
class TestMetrics:
    """Métricas de un test individual"""

    test_name: str
    suite: str
    status: str  # PASSED, FAILED, SKIPPED
    duration: float
    memory_usage: float
    cpu_usage: float
    timestamp: datetime
    error_message: Optional[str] = None


@dataclass
class SuiteMetrics:
    """Métricas de una suite de tests"""

    suite_name: str
    total_tests: int
    passed_tests: int
    failed_tests: int
    skipped_tests: int
    total_duration: float
    success_rate: float
    average_duration: float
    memory_peak: float
    cpu_peak: float
    timestamp: datetime


@dataclass
class SystemMetrics:
    """Métricas del sistema durante testing"""

    cpu_percent: float
    memory_percent: float
    disk_usage: float
    network_io: Dict[str, int]
    timestamp: datetime


class MetricsCollector:
    """Colector de métricas de testing"""

    def __init__(self):
        self.test_metrics: List[TestMetrics] = []
        self.suite_metrics: List[SuiteMetrics] = []
        self.system_metrics: List[SystemMetrics] = []
        self.start_time = None
        self.end_time = None

        logger.info("MetricsCollector inicializado")

    def start_collection(self):
        """Iniciar recolección de métricas"""
        self.start_time = datetime.now()
        self.test_metrics.clear()
        self.suite_metrics.clear()
        self.system_metrics.clear()

        logger.info("Recolección de métricas iniciada")

    def stop_collection(self):
        """Detener recolección de métricas"""
        self.end_time = datetime.now()
        logger.info(
            f"Recolección de métricas detenida. Duración: {self.end_time - self.start_time}"
        )

    def collect_test_metrics(
        self,
        test_name: str,
        suite: str,
        status: str,
        duration: float,
        error_message: Optional[str] = None,
    ):
        """Recopilar métricas de un test individual"""
        # Obtener métricas del sistema
        memory_usage = psutil.virtual_memory().percent
        cpu_usage = psutil.cpu_percent()

        test_metric = TestMetrics(
            test_name=test_name,
            suite=suite,
            status=status,
            duration=duration,
            memory_usage=memory_usage,
            cpu_usage=cpu_usage,
            timestamp=datetime.now(),
            error_message=error_message,
        )

        self.test_metrics.append(test_metric)
        logger.debug(f"Métricas de test recopiladas: {test_name}")

    def collect_suite_metrics(self, suite_name: str):
        """Recopilar métricas de una suite de tests"""
        # Filtrar métricas de la suite
        suite_tests = [t for t in self.test_metrics if t.suite == suite_name]

        if not suite_tests:
            return

        total_tests = len(suite_tests)
        passed_tests = len([t for t in suite_tests if t.status == "PASSED"])
        failed_tests = len([t for t in suite_tests if t.status == "FAILED"])
        skipped_tests = len([t for t in suite_tests if t.status == "SKIPPED"])

        total_duration = sum(t.duration for t in suite_tests)
        success_rate = (passed_tests / total_tests * 100) if total_tests > 0 else 0
        average_duration = total_duration / total_tests if total_tests > 0 else 0

        memory_peak = max(t.memory_usage for t in suite_tests) if suite_tests else 0
        cpu_peak = max(t.cpu_usage for t in suite_tests) if suite_tests else 0

        suite_metric = SuiteMetrics(
            suite_name=suite_name,
            total_tests=total_tests,
            passed_tests=passed_tests,
            failed_tests=failed_tests,
            skipped_tests=skipped_tests,
            total_duration=total_duration,
            success_rate=success_rate,
            average_duration=average_duration,
            memory_peak=memory_peak,
            cpu_peak=cpu_peak,
            timestamp=datetime.now(),
        )

        self.suite_metrics.append(suite_metric)
        logger.info(f"Métricas de suite recopiladas: {suite_name}")

    def collect_system_metrics(self):
        """Recopilar métricas del sistema"""
        try:
            # Métricas de CPU y memoria
            cpu_percent = psutil.cpu_percent(interval=1)
            memory = psutil.virtual_memory()
            memory_percent = memory.percent

            # Métricas de disco
            disk = psutil.disk_usage("/")
            disk_usage = (disk.used / disk.total) * 100

            # Métricas de red
            network = psutil.net_io_counters()
            network_io = {
                "bytes_sent": network.bytes_sent,
                "bytes_recv": network.bytes_recv,
                "packets_sent": network.packets_sent,
                "packets_recv": network.packets_recv,
            }

            system_metric = SystemMetrics(
                cpu_percent=cpu_percent,
                memory_percent=memory_percent,
                disk_usage=disk_usage,
                network_io=network_io,
                timestamp=datetime.now(),
            )

            self.system_metrics.append(system_metric)

        except Exception as e:
            logger.error(f"Error recopilando métricas del sistema: {e}")

    def get_overall_metrics(self) -> Dict[str, Any]:
        """Obtener métricas generales"""
        if not self.test_metrics:
            return {}

        total_tests = len(self.test_metrics)
        passed_tests = len([t for t in self.test_metrics if t.status == "PASSED"])
        failed_tests = len([t for t in self.test_metrics if t.status == "FAILED"])
        skipped_tests = len([t for t in self.test_metrics if t.status == "SKIPPED"])

        total_duration = sum(t.duration for t in self.test_metrics)
        success_rate = (passed_tests / total_tests * 100) if total_tests > 0 else 0

        # Métricas de rendimiento
        average_duration = total_duration / total_tests if total_tests > 0 else 0
        slowest_test = (
            max(self.test_metrics, key=lambda x: x.duration)
            if self.test_metrics
            else None
        )

        # Métricas de recursos
        memory_peak = (
            max(t.memory_usage for t in self.test_metrics) if self.test_metrics else 0
        )
        cpu_peak = (
            max(t.cpu_usage for t in self.test_metrics) if self.test_metrics else 0
        )

        # Tests más problemáticos
        failed_tests_list = [t for t in self.test_metrics if t.status == "FAILED"]
        slowest_tests = sorted(
            self.test_metrics, key=lambda x: x.duration, reverse=True
        )[:10]

        return {
            "overall": {
                "total_tests": total_tests,
                "passed_tests": passed_tests,
                "failed_tests": failed_tests,
                "skipped_tests": skipped_tests,
                "success_rate": success_rate,
                "total_duration": total_duration,
                "average_duration": average_duration,
            },
            "performance": {
                "slowest_test": (
                    {
                        "name": slowest_test.test_name,
                        "suite": slowest_test.suite,
                        "duration": slowest_test.duration,
                    }
                    if slowest_test
                    else None
                ),
                "memory_peak": memory_peak,
                "cpu_peak": cpu_peak,
            },
            "failed_tests": [
                {
                    "name": t.test_name,
                    "suite": t.suite,
                    "duration": t.duration,
                    "error": t.error_message,
                }
                for t in failed_tests_list
            ],
            "slowest_tests": [
                {"name": t.test_name, "suite": t.suite, "duration": t.duration}
                for t in slowest_tests
            ],
            "execution_time": {
                "start_time": self.start_time.isoformat() if self.start_time else None,
                "end_time": self.end_time.isoformat() if self.end_time else None,
                "duration": (
                    (self.end_time - self.start_time).total_seconds()
                    if self.start_time and self.end_time
                    else None
                ),
            },
        }

    def get_suite_metrics(self, suite_name: str) -> Optional[Dict[str, Any]]:
        """Obtener métricas de una suite específica"""
        suite_metric = next(
            (s for s in self.suite_metrics if s.suite_name == suite_name), None
        )
        if not suite_metric:
            return None

        return asdict(suite_metric)

    def get_trend_metrics(self, days: int = 7) -> Dict[str, Any]:
        """Obtener métricas de tendencia"""
        # En una implementación real, esto consultaría una base de datos
        # Por ahora, simulamos datos de tendencia
        return {
            "success_rate_trend": self._calculate_trend("success_rate", days),
            "duration_trend": self._calculate_trend("duration", days),
            "failure_trend": self._calculate_trend("failures", days),
        }

    def _calculate_trend(self, metric: str, days: int) -> List[Dict[str, Any]]:
        """Calcular tendencia de una métrica"""
        # Simulación de datos de tendencia
        trend_data = []
        base_date = datetime.now() - timedelta(days=days)

        for i in range(days):
            date = base_date + timedelta(days=i)
            # En una implementación real, estos valores vendrían de la base de datos
            value = 85 + (i * 2) + (i % 3)  # Simulación de mejora gradual
            trend_data.append({"date": date.isoformat(), "value": value})

        return trend_data

    def export_metrics(self, format: str = "json") -> str:
        """Exportar métricas en formato específico"""
        metrics = self.get_overall_metrics()

        if format == "json":
            return json.dumps(metrics, indent=2, default=str)
        elif format == "csv":
            return self._export_csv()
        else:
            raise ValueError(f"Formato no soportado: {format}")

    def _export_csv(self) -> str:
        """Exportar métricas en formato CSV"""
        import csv
        import io

        output = io.StringIO()
        writer = csv.writer(output)

        # Escribir encabezados
        writer.writerow(
            [
                "test_name",
                "suite",
                "status",
                "duration",
                "memory_usage",
                "cpu_usage",
                "timestamp",
                "error_message",
            ]
        )

        # Escribir datos
        for metric in self.test_metrics:
            writer.writerow(
                [
                    metric.test_name,
                    metric.suite,
                    metric.status,
                    metric.duration,
                    metric.memory_usage,
                    metric.cpu_usage,
                    metric.timestamp.isoformat(),
                    metric.error_message or "",
                ]
            )

        return output.getvalue()

    def save_metrics(self, filepath: str):
        """Guardar métricas en archivo"""
        metrics = self.get_overall_metrics()

        with open(filepath, "w", encoding="utf-8") as f:
            json.dump(metrics, f, indent=2, default=str, ensure_ascii=False)

        logger.info(f"Métricas guardadas en: {filepath}")

    def load_metrics(self, filepath: str):
        """Cargar métricas desde archivo"""
        try:
            with open(filepath, "r", encoding="utf-8") as f:
                metrics = json.load(f)

            logger.info(f"Métricas cargadas desde: {filepath}")
            return metrics

        except Exception as e:
            logger.error(f"Error cargando métricas: {e}")
            return None


class PerformanceMonitor:
    """Monitor de rendimiento en tiempo real"""

    def __init__(self, metrics_collector: MetricsCollector):
        self.metrics_collector = metrics_collector
        self.monitoring = False
        self.monitor_thread = None

    def start_monitoring(self, interval: float = 5.0):
        """Iniciar monitoreo en tiempo real"""
        import threading

        self.monitoring = True
        self.monitor_thread = threading.Thread(
            target=self._monitor_loop, args=(interval,), daemon=True
        )
        self.monitor_thread.start()

        logger.info("Monitoreo de rendimiento iniciado")

    def stop_monitoring(self):
        """Detener monitoreo"""
        self.monitoring = False
        if self.monitor_thread:
            self.monitor_thread.join()

        logger.info("Monitoreo de rendimiento detenido")

    def _monitor_loop(self, interval: float):
        """Loop de monitoreo"""
        while self.monitoring:
            try:
                self.metrics_collector.collect_system_metrics()
                time.sleep(interval)
            except Exception as e:
                logger.error(f"Error en monitoreo: {e}")
                time.sleep(interval)


# Instancia global
metrics_collector = MetricsCollector()
performance_monitor = PerformanceMonitor(metrics_collector)
