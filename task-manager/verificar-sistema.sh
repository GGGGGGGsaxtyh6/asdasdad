#!/bin/bash

echo "🔍 Verificando Sistema Task Manager..."
echo "=================================="

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Función para verificar si un puerto está en uso
check_port() {
    local port=$1
    local service=$2
    if lsof -Pi :$port -sTCP:LISTEN -t >/dev/null 2>&1; then
        echo -e "${GREEN}✅ $service está ejecutándose en puerto $port${NC}"
        return 0
    else
        echo -e "${RED}❌ $service NO está ejecutándose en puerto $port${NC}"
        return 1
    fi
}

# Función para verificar URL
check_url() {
    local url=$1
    local service=$2
    if curl -s --head "$url" | head -n 1 | grep -q "200 OK"; then
        echo -e "${GREEN}✅ $service responde correctamente en $url${NC}"
        return 0
    else
        echo -e "${RED}❌ $service NO responde en $url${NC}"
        return 1
    fi
}

echo -e "${BLUE}📡 Verificando servicios...${NC}"
echo ""

# Verificar Backend
check_port 3001 "Backend API"
if [ $? -eq 0 ]; then
    check_url "http://localhost:3001/health" "Health Check"
fi

echo ""

# Verificar Frontend
check_port 5173 "Frontend React"
if [ $? -eq 0 ]; then
    check_url "http://localhost:5173" "Frontend App"
fi

echo ""

# Verificar Ngrok
check_port 4040 "Ngrok Dashboard"
if [ $? -eq 0 ]; then
    # Obtener URL pública de ngrok
    NGROK_URL=$(curl -s http://localhost:4040/api/tunnels | grep -o '"public_url":"[^"]*"' | head -1 | cut -d'"' -f4)
    if [ ! -z "$NGROK_URL" ]; then
        echo -e "${GREEN}✅ Ngrok está exponiendo: $NGROK_URL${NC}"
        check_url "$NGROK_URL" "Aplicación Pública"
    else
        echo -e "${RED}❌ No se pudo obtener la URL pública de ngrok${NC}"
    fi
fi

echo ""
echo -e "${BLUE}🗄️ Verificando base de datos...${NC}"

# Verificar si existe la base de datos
if [ -f "/workspace/task-manager/backend/dev.db" ]; then
    echo -e "${GREEN}✅ Base de datos SQLite existe${NC}"
    
    # Verificar tablas
    TABLES=$(sqlite3 /workspace/task-manager/backend/dev.db ".tables" 2>/dev/null)
    if [ ! -z "$TABLES" ]; then
        echo -e "${GREEN}✅ Tablas creadas correctamente${NC}"
        echo -e "${YELLOW}   Tablas: $TABLES${NC}"
    else
        echo -e "${RED}❌ No se encontraron tablas en la base de datos${NC}"
    fi
else
    echo -e "${RED}❌ Base de datos SQLite no existe${NC}"
fi

echo ""
echo -e "${BLUE}🧪 Ejecutando pruebas de API...${NC}"

# Ejecutar pruebas de API
cd /workspace/task-manager
if [ -f "test-api.js" ]; then
    echo -e "${YELLOW}Ejecutando pruebas...${NC}"
    node test-api.js > /tmp/api-test-results.log 2>&1
    if [ $? -eq 0 ]; then
        echo -e "${GREEN}✅ Todas las pruebas de API pasaron exitosamente${NC}"
    else
        echo -e "${RED}❌ Algunas pruebas de API fallaron${NC}"
        echo -e "${YELLOW}Ver logs en /tmp/api-test-results.log${NC}"
    fi
else
    echo -e "${RED}❌ Archivo de pruebas no encontrado${NC}"
fi

echo ""
echo -e "${BLUE}📊 Resumen del Sistema${NC}"
echo "========================"

# Contar procesos
BACKEND_PROCESSES=$(ps aux | grep -c "npm run dev.*backend" || echo "0")
FRONTEND_PROCESSES=$(ps aux | grep -c "npm run dev.*frontend" || echo "0")
NGROK_PROCESSES=$(ps aux | grep -c "ngrok" || echo "0")

echo -e "Backend processes: ${GREEN}$BACKEND_PROCESSES${NC}"
echo -e "Frontend processes: ${GREEN}$FRONTEND_PROCESSES${NC}"
echo -e "Ngrok processes: ${GREEN}$NGROK_PROCESSES${NC}"

echo ""
echo -e "${BLUE}🌐 URLs de Acceso${NC}"
echo "=================="
echo -e "Backend API: ${YELLOW}http://localhost:3001${NC}"
echo -e "Frontend App: ${YELLOW}http://localhost:5173${NC}"
echo -e "Ngrok Public: ${YELLOW}$NGROK_URL${NC}"
echo -e "Health Check: ${YELLOW}http://localhost:3001/health${NC}"

echo ""
echo -e "${GREEN}🎉 Verificación completada!${NC}"
echo -e "${BLUE}La aplicación Task Manager está lista para usar.${NC}"