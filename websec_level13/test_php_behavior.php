<?php
// Simulando el comportamiento del código

function test_payload($ids) {
    echo "\n=== Testing: $ids ===\n";
    
    $tmp = explode(',', $ids);
    echo "After explode: "; print_r($tmp);
    
    for ($i = 0; $i < count($tmp); $i++) {
        $tmp[$i] = (int)$tmp[$i];
        if($tmp[$i] < 1) {
            unset($tmp[$i]);
        }
    }
    echo "After filter: "; print_r($tmp);
    
    $selector = implode(',', array_unique($tmp));
    echo "Final selector: '$selector'\n";
    
    $query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
    echo "Query: $query\n";
}

test_payload("1,2,3");
test_payload("0,1,2");
test_payload("1,0,2");
test_payload("0,0,0");
test_payload("1) OR user_id=0--");
test_payload("1,1,1,1");

// Casos especiales
test_payload("-1,1,2");
test_payload("1,,2");
test_payload("1, ,2");
test_payload("1,0) OR user_id=0--,2");

?>
