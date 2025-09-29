<?php
$text = 'Niw0OgIsEykABg8qESRRCg4XNkEHNg0XCls4BwZaAVBbLU4EC2VFBTooPi0qLFUELQ==';
$decoded = base64_decode($text);

echo "Decoded length: " . strlen($decoded) . " bytes\n\n";

// Asumir que la flag tiene formato WEBSEC{...} donde el contenido son caracteres imprimibles
// Rango ASCII imprimible: 32-126

// Comenzar con lo que sabemos
$known_flag = "WEBSEC{";
$key = '';

// Calcular key a partir de known_flag
for ($i = 0; $i < strlen($known_flag); $i++) {
    $key .= chr(ord($decoded[$i]) ^ ord($known_flag[$i]));
}

echo "Key conocida hasta ahora: $key\n";
echo "Longitud: " . strlen($key) . "\n\n";

// La flag probablemente termina con }
// El último caracter del decoded debe hacer XOR con } para dar el último caracter de la key
$last_char_key = chr(ord($decoded[strlen($decoded)-1]) ^ ord('}'));
echo "Último caracter de key (si flag termina con }): " . $last_char_key . " (0x" . dechex(ord($last_char_key)) . ")\n\n";

// La key parece ser una cadena legible. Veamos si podemos adivinar el patrón
// "aiviGoh" parece el inicio de alguna palabra

// Probemos diferentes longitudes de key esperando que sea ASCII legible
echo "=== Intentando deducir key completa ===\n\n";

// Una estrategia: asumir que todos los caracteres de la flag son imprimibles ASCII
// y ver qué keys posibles producen eso

$possible_keys = [];
$key_length = strlen($decoded);  // Máxima longitud posible

for ($pos = strlen($key); $pos < $key_length; $pos++) {
    $candidates = [];
    
    // Probar cada posible caracter de key (ASCII imprimible)
    for ($k = 32; $k <= 126; $k++) {
        $flag_char = chr(ord($decoded[$pos]) ^ $k);
        
        // Verificar si el caracter resultante de la flag es imprimible o }
        if ((ord($flag_char) >= 32 && ord($flag_char) <= 126) || $flag_char == "\n") {
            $candidates[] = [
                'key_char' => chr($k),
                'flag_char' => $flag_char,
                'printable' => ctype_print($flag_char) || $flag_char == "\n"
            ];
        }
    }
    
    // Si hay muy pocos candidatos, es más fácil deducir
    if (count($candidates) > 0 && count($candidates) < 10) {
        echo "Posición $pos: " . count($candidates) . " candidatos:\n";
        foreach ($candidates as $c) {
            echo "  Key: '{$c['key_char']}' → Flag: '{$c['flag_char']}'\n";
        }
        echo "\n";
    }
}

// Intentemos con una key que sea "aiviGoh" repetida o extendida
echo "=== Probando variaciones de key ===\n\n";

// Palabras comunes que empiezan con "aivi"
$test_keys = [
    'aiviGoh',
    'aivi',
];

// O tal vez "aiviGoh" es parte de User-Agent común
// Intentemos con user agents comunes de PHP
$common_agents = [
    'PHP',
    'Mozilla',
    'aiviGohPHP',
];

foreach (array_merge($test_keys, $common_agents) as $test_key) {
    if (empty($test_key)) continue;
    
    $flag = '';
    for ($i = 0; $i < strlen($decoded); $i++) {
        $flag .= chr(ord($decoded[$i]) ^ ord($test_key[$i % strlen($test_key)]));
    }
    
    echo "Key: '$test_key'\n";
    echo "Flag: $flag\n";
    
    if (substr($flag, 0, 7) == 'WEBSEC{' && strpos($flag, '}') !== false) {
        echo "*** POSIBLE FLAG VÁLIDA ***\n";
    }
    echo "\n";
}

?>