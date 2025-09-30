<?php
$ids = "0,0,0";
$tmp = explode(',', $ids);
echo "After explode:\n"; print_r($tmp);

for ($i = 0; $i < count($tmp); $i++) {
    echo "i=$i, tmp[$i]=" . $tmp[$i] . " => ";
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
echo "Final selector: '$selector'\n";
?>
