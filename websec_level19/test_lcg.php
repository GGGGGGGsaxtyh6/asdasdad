<?php
// Test para verificar cómo PHP 5.6 genera números con rand()
$seed = 1234567890;
srand($seed);

echo "Seed: $seed\n";
echo "Primeros 10 valores de rand():\n";
for ($i = 0; $i < 10; $i++) {
    $val = rand();
    echo "$i: $val\n";
}
?>