# Neon Relay

- **Categoría**: reversing · hard
- **Duración estimada**: 5–6 horas
- **Archivos proporcionados**: `neon_relay` (ELF 64-bit), este `README.md`

## Narrativa

Las comunicaciones del *Neon Relay* quedaron bloqueadas en un bucle temporal. El terminal entrega respuestas crípticas y exige una secuencia triple antes de revelar la clave final. Solo un análisis profundo de sus capas internas te permitirá restaurar la sincronización y romper el bloqueo cronal.

## Objetivo

Ejecuta `./neon_relay` en un entorno Linux x86_64 y obtén la **flag final** completando las tres fases del binario:

1. **Handshake**: el programa espera una frase exacta para iniciar la sesión.
2. **Triad directives**: debes introducir tres palabras (separadas por espacios) en el orden correcto.
3. **Final key**: al superar las fases anteriores, la aplicación exige la clave definitiva.

La flag solo se mostrará si la cadena final es perfecta. El binario implementa varias capas de ofuscación, transformaciones personalizadas y un cierre anti-debug básico. Todas las verificaciones ocurren en memoria; no hay strings evidentes, y los valores objetivo están camuflados en cálculos multi-etapa.

## Pistas operativas

- El handshake, las directivas y la clave final están relacionadas, pero ninguna aparece en texto plano.
- La lógica de verificación utiliza múltiples rotaciones, mezclas estado-dependientes y constantes dinámicas. Analiza el flujo completo: cada etapa modifica el contexto para la siguiente.
- Espera encontrar estructuras tipo *state machine* y transformaciones no lineales que dependen tanto de la secuencia de entrada como de los hashes intermedios.
- El binario se ha compilado sin símbolos y con comprobaciones anti-traza simples: deberás trabajar alrededor de ellas o parchear temporalmente.

## Reglas

- No se necesita conexión a red ni archivos adicionales.
- Cualquier técnica de reversing está permitida (parcheo, emulación, lifting a IR, etc.).
- Se considera resuelto cuando recuperes la flag completa a partir del ejecutable.

¡Buena caza y cuidado con los ecos temporales del Neon Relay!
