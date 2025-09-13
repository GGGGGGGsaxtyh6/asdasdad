"""
Cliente HTTP para testing de APIs con funcionalidades avanzadas
"""

import asyncio
import json
import time
from typing import Dict, Any, Optional, Union, List
from dataclasses import dataclass
from enum import Enum

import httpx
import requests
from requests.adapters import HTTPAdapter
from urllib3.util.retry import Retry
from loguru import logger

from .config import config


class HTTPMethod(Enum):
    """Métodos HTTP soportados"""
    GET = "GET"
    POST = "POST"
    PUT = "PUT"
    PATCH = "PATCH"
    DELETE = "DELETE"
    HEAD = "HEAD"
    OPTIONS = "OPTIONS"


@dataclass
class APIResponse:
    """Respuesta estandarizada de la API"""
    status_code: int
    headers: Dict[str, str]
    data: Any
    response_time: float
    url: str
    method: str
    request_data: Optional[Dict[str, Any]] = None


class APIClient:
    """Cliente HTTP robusto para testing de APIs"""
    
    def __init__(self, base_url: Optional[str] = None, timeout: Optional[int] = None):
        self.base_url = base_url or config.api.base_url
        self.timeout = timeout or config.api.timeout
        
        # Configurar retry strategy
        retry_strategy = Retry(
            total=config.api.retries,
            backoff_factor=1,
            status_forcelist=[429, 500, 502, 503, 504],
            allowed_methods=["HEAD", "GET", "PUT", "DELETE", "OPTIONS", "TRACE", "POST"]
        )
        
        # Configurar session con retry
        self.session = requests.Session()
        adapter = HTTPAdapter(max_retries=retry_strategy)
        self.session.mount("http://", adapter)
        self.session.mount("https://", adapter)
        
        # Headers por defecto
        self.session.headers.update(config.get_headers())
        
        logger.info(f"APIClient inicializado con base_url: {self.base_url}")
    
    def _make_request(
        self,
        method: HTTPMethod,
        endpoint: str,
        data: Optional[Dict[str, Any]] = None,
        params: Optional[Dict[str, Any]] = None,
        headers: Optional[Dict[str, str]] = None,
        files: Optional[Dict[str, Any]] = None,
        json_data: Optional[Dict[str, Any]] = None
    ) -> APIResponse:
        """Realiza una request HTTP"""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        
        # Merge headers
        request_headers = self.session.headers.copy()
        if headers:
            request_headers.update(headers)
        
        # Preparar datos
        request_data = None
        if json_data:
            request_data = json_data
        elif data:
            request_data = data
        
        start_time = time.time()
        
        try:
            logger.info(f"Realizando {method.value} request a {url}")
            
            response = self.session.request(
                method=method.value,
                url=url,
                data=request_data if not json_data else None,
                json=json_data,
                params=params,
                headers=request_headers,
                files=files,
                timeout=self.timeout
            )
            
            response_time = time.time() - start_time
            
            # Parsear respuesta
            try:
                response_data = response.json()
            except ValueError:
                response_data = response.text
            
            api_response = APIResponse(
                status_code=response.status_code,
                headers=dict(response.headers),
                data=response_data,
                response_time=response_time,
                url=url,
                method=method.value,
                request_data=request_data
            )
            
            logger.info(f"Response recibida: {response.status_code} en {response_time:.2f}s")
            return api_response
            
        except requests.exceptions.RequestException as e:
            logger.error(f"Error en request: {e}")
            raise
    
    def get(self, endpoint: str, **kwargs) -> APIResponse:
        """GET request"""
        return self._make_request(HTTPMethod.GET, endpoint, **kwargs)
    
    def post(self, endpoint: str, **kwargs) -> APIResponse:
        """POST request"""
        return self._make_request(HTTPMethod.POST, endpoint, **kwargs)
    
    def put(self, endpoint: str, **kwargs) -> APIResponse:
        """PUT request"""
        return self._make_request(HTTPMethod.PUT, endpoint, **kwargs)
    
    def patch(self, endpoint: str, **kwargs) -> APIResponse:
        """PATCH request"""
        return self._make_request(HTTPMethod.PATCH, endpoint, **kwargs)
    
    def delete(self, endpoint: str, **kwargs) -> APIResponse:
        """DELETE request"""
        return self._make_request(HTTPMethod.DELETE, endpoint, **kwargs)
    
    def head(self, endpoint: str, **kwargs) -> APIResponse:
        """HEAD request"""
        return self._make_request(HTTPMethod.HEAD, endpoint, **kwargs)
    
    def options(self, endpoint: str, **kwargs) -> APIResponse:
        """OPTIONS request"""
        return self._make_request(HTTPMethod.OPTIONS, endpoint, **kwargs)


class AsyncAPIClient:
    """Cliente HTTP asíncrono para testing de APIs"""
    
    def __init__(self, base_url: Optional[str] = None, timeout: Optional[int] = None):
        self.base_url = base_url or config.api.base_url
        self.timeout = timeout or config.api.timeout
        self.client = httpx.AsyncClient(
            base_url=self.base_url,
            timeout=self.timeout,
            headers=config.get_headers()
        )
        logger.info(f"AsyncAPIClient inicializado con base_url: {self.base_url}")
    
    async def _make_request(
        self,
        method: HTTPMethod,
        endpoint: str,
        data: Optional[Dict[str, Any]] = None,
        params: Optional[Dict[str, Any]] = None,
        headers: Optional[Dict[str, str]] = None,
        files: Optional[Dict[str, Any]] = None,
        json_data: Optional[Dict[str, Any]] = None
    ) -> APIResponse:
        """Realiza una request HTTP asíncrona"""
        url = f"{self.base_url}/{endpoint.lstrip('/')}"
        
        # Merge headers
        request_headers = self.client.headers.copy()
        if headers:
            request_headers.update(headers)
        
        # Preparar datos
        request_data = None
        if json_data:
            request_data = json_data
        elif data:
            request_data = data
        
        start_time = time.time()
        
        try:
            logger.info(f"Realizando {method.value} request asíncrona a {url}")
            
            response = await self.client.request(
                method=method.value,
                url=url,
                data=request_data if not json_data else None,
                json=json_data,
                params=params,
                headers=request_headers,
                files=files
            )
            
            response_time = time.time() - start_time
            
            # Parsear respuesta
            try:
                response_data = response.json()
            except ValueError:
                response_data = response.text
            
            api_response = APIResponse(
                status_code=response.status_code,
                headers=dict(response.headers),
                data=response_data,
                response_time=response_time,
                url=url,
                method=method.value,
                request_data=request_data
            )
            
            logger.info(f"Response recibida: {response.status_code} en {response_time:.2f}s")
            return api_response
            
        except httpx.RequestError as e:
            logger.error(f"Error en request asíncrona: {e}")
            raise
    
    async def get(self, endpoint: str, **kwargs) -> APIResponse:
        """GET request asíncrona"""
        return await self._make_request(HTTPMethod.GET, endpoint, **kwargs)
    
    async def post(self, endpoint: str, **kwargs) -> APIResponse:
        """POST request asíncrona"""
        return await self._make_request(HTTPMethod.POST, endpoint, **kwargs)
    
    async def put(self, endpoint: str, **kwargs) -> APIResponse:
        """PUT request asíncrona"""
        return await self._make_request(HTTPMethod.PUT, endpoint, **kwargs)
    
    async def patch(self, endpoint: str, **kwargs) -> APIResponse:
        """PATCH request asíncrona"""
        return await self._make_request(HTTPMethod.PATCH, endpoint, **kwargs)
    
    async def delete(self, endpoint: str, **kwargs) -> APIResponse:
        """DELETE request asíncrona"""
        return await self._make_request(HTTPMethod.DELETE, endpoint, **kwargs)
    
    async def close(self):
        """Cerrar cliente asíncrono"""
        await self.client.aclose()


# Instancias globales
api_client = APIClient()
async_api_client = AsyncAPIClient()