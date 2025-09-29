<?php
$text = 'Niw0OgIsEykABg8qESRRCg4XNkEHNg0XCls4BwZaAVBbLU4EC2VFBTooPi0qLFUELQ==';

// El user_agent de php.ini por defecto es vacío, pero probemos diferentes valores
$possible_keys = [
    '',  // vacío por defecto
    'PHP',
    ini_get('user_agent'),  // valor actual
];

echo "Intentando descifrar con diferentes keys:\n\n";

foreach ($possible_keys as $key) {
    echo "Key: '" . ($key ? $key : '(empty)') . "'\n";
    
    if (empty($key)) {
        echo "Skip - empty key\n\n";
        continue;
    }
    
    $i = 0;
    $flag = '';
    $decoded = base64_decode($text);
    
    foreach (str_split($decoded) as $letter) {
        if (isset($key[$i])) {
            $flag .= chr(ord($key[$i]) ^ ord($letter));
        } else {
            // Si la key es más corta, reutilizarla
            $flag .= chr(ord($key[$i % strlen($key)]) ^ ord($letter));
        }
        $i++;
    }
    
    echo "Resultado: $flag\n";
    
    if (strpos($flag, 'WEBSEC') !== false) {
        echo "*** ¡ENCONTRADA! ***\n";
    }
    echo "\n";
}

// Probar también como si el user_agent fuera el valor por defecto de PHP
echo "=== Buscando el user_agent correcto por fuerza bruta ===\n\n";

// El hint dice que user_agent está en ini, probemos leerlo del servidor
echo "Valor actual de ini_get('user_agent'): " . (ini_get('user_agent') ?: '(empty)') . "\n";

?>