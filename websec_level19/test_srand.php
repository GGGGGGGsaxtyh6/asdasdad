<?php
// Test para entender cómo srand() maneja microtime(true)

echo "microtime(true) actual: " . microtime(true) . "\n";
echo "Convertido a int: " . intval(microtime(true)) . "\n\n";

// Probar si decimales afectan
$test_seeds = [
    1234567890.1,
    1234567890.5,
    1234567890.9,
    1234567891.0,
];

foreach ($test_seeds as $seed) {
    srand($seed);
    echo "Seed $seed -> primer rand(): " . rand() . "\n";
}

echo "\n---\n\n";

// El problema real: ¿microtime(true) se convierte a int en srand()?
// Vamos a probar

$mt = microtime(true);
echo "microtime(true) = $mt\n";

srand($mt);
$chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
$text = '';
for($i = 0; $i < 10; $i++) {
    $text .= $chars[rand() % strlen($chars)];
}
echo "Captcha con microtime: $text\n";

// Probar con la parte entera
srand(intval($mt));
$text2 = '';
for($i = 0; $i < 10; $i++) {
    $text2 .= $chars[rand() % strlen($chars)];
}
echo "Captcha con intval: $text2\n";

if ($text === $text2) {
    echo "\n¡SON IGUALES! srand() convierte el float a int\n";
} else {
    echo "\nSON DIFERENTES - srand() usa el float completo\n";
}
?>