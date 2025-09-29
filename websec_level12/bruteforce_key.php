<?php
$text = 'Niw0OgIsEykABg8qESRRCg4XNkEHNg0XCls4BwZaAVBbLU4EC2VFBTooPi0qLFUELQ==';
$decoded = base64_decode($text);

// Sabemos que el resultado debe empezar con "WEBSEC{"
$known_prefix = "WEBSEC{";

echo "Decoded length: " . strlen($decoded) . " bytes\n";
echo "Known prefix: $known_prefix\n\n";

// Calcular qué key produciría "WEBSEC{" al hacer XOR
$key_prefix = '';
for ($i = 0; $i < strlen($known_prefix); $i++) {
    $key_char = chr(ord($decoded[$i]) ^ ord($known_prefix[$i]));
    $key_prefix .= $key_char;
    printf("Key[%d] = %s (0x%02x)\n", $i, $key_char, ord($key_char));
}

echo "\nKey prefix: $key_prefix\n";
echo "Key prefix (hex): " . bin2hex($key_prefix) . "\n\n";

// Ahora intentar descifrar todo con esta key (asumiendo que se repite cíclicamente)
echo "=== Intentando descifrar con key cíclica ===\n";
$key = $key_prefix;
$i = 0;
$flag = '';
foreach (str_split($decoded) as $letter) {
    $flag .= chr(ord($key[$i % strlen($key)]) ^ ord($letter));
    $i++;
}

echo "Flag: $flag\n";

if (strpos($flag, 'WEBSEC{') === 0) {
    echo "\n¡ÉXITO! La flag es válida.\n";
}

?>