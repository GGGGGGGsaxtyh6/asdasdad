// Cloudflare Worker para ocultar GitHub Pages
addEventListener('fetch', event => {
  event.respondWith(handleRequest(event.request))
})

async function handleRequest(request) {
  // Hacer fetch al sitio original
  const response = await fetch('https://tu-usuario.github.io', {
    headers: request.headers
  })
  
  // Crear nueva respuesta sin headers de GitHub
  const newResponse = new Response(response.body, {
    status: response.status,
    statusText: response.statusText,
    headers: {
      ...response.headers,
      // Eliminar headers que revelan GitHub
      'x-github-request-id': undefined,
      'x-served-by': undefined,
      'x-cache': undefined,
      'x-timer': undefined,
      'x-fastly-request-id': undefined,
      // Agregar headers genéricos
      'server': 'nginx/1.18.0',
      'x-powered-by': 'PHP/8.1.0'
    }
  })
  
  return newResponse
}