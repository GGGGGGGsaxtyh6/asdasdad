<?php
// Script para encontrar una colisión parcial de SHA1
// Necesitamos que sha1($input, true) empiece con 0x7c 0x00

echo "Buscando colisión parcial de SHA1...\n";
echo "Objetivo: sha1(input, TRUE) debe empezar con 0x7c 0x00\n\n";

$target_bytes = "\x7c\x00";
$found = false;
$attempts = 0;

// Intentar con diferentes strings
for ($i = 0; $i < 1000000; $i++) {
    $test = (string)$i;
    $hash_binary = sha1($test, true);
    
    $attempts++;
    
    // Verificar si los primeros 2 bytes coinciden
    if (substr($hash_binary, 0, 2) === $target_bytes) {
        echo "¡COLISIÓN ENCONTRADA!\n";
        echo "Input: $test\n";
        echo "SHA1 binario (hex): " . bin2hex($hash_binary) . "\n";
        echo "Primeros 2 bytes: " . bin2hex(substr($hash_binary, 0, 2)) . "\n";
        echo "Intentos: $attempts\n";
        $found = true;
        break;
    }
    
    if ($i % 10000 == 0 && $i > 0) {
        echo "Intentos: $i...\n";
    }
}

if (!$found) {
    echo "\nNo se encontró en los primeros $attempts intentos.\n";
    echo "Intentando con otros patrones...\n";
}
?>