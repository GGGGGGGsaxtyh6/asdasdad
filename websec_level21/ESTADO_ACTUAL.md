# WEBSEC LEVEL21 - Estado Actual

## DESCUBRIMIENTOS CLAVE:

### ✅ Lo que funciona:
1. Username de 6 caracteres alinea perfectamente con bloque AES (16 bytes)
2. Cookie truncada a solo IV + 2 bloques pasa validaciones iniciales
3. Modificar IV permite cambiar el bloque 0 completamente
4. Puedo cambiar username de "testxx" a "admin " y llegar a verify_credentials
5. SQL injection existe en verify_credentials

### ❌ El problema:
- Usuario "admin " (con espacio) no existe en la DB
- Usuario "admin" (sin espacio) requiere solo 5 chars, no alinea con bloque
- No puedo hacer que SQLite trunque o ignore el espacio
- No puedo modificar el password sin corromper bloques anteriores

### 🤔 Posibles soluciones que faltan:
1. **¿El usuario en la DB NO es "admin" sino otra cosa?** 
   - Tal vez es "Admin" o "ADMIN" o "administrator"
   
2. **¿Hay alguna característica de SQLite COLLATE que ignora espacios?**
   - COLLATE NOCASE no ignora espacios
   - Pero tal vez hay COLLATE que sí?

3. **¿Hay un payload SQL específico que funcione con el espacio?**
   - `admin '` → ¿alguna forma de que coincida?

4. **¿Necesito usar UNION SELECT para crear un usuario virtual?**
   - UNION SELECT 'admin', 'hash', 'ip'

5. **¿El password vacío tiene alguna propiedad especial en verify_credentials?**

## PRÓXIMOS PASOS A INTENTAR:

1. Probar usernames alternativos: Admin, ADMIN, root, administrator
2. Intentar UNION SELECT en el username
3. Revisar si hay alguna característica de COLLATE en SQLite
4. Implementar padding oracle completo si todo lo demás falla
