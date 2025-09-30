<?php
// Test para entender cómo funciona srand con microtime

function generate_random_text($length) {
    $chars  = "abcdefghijklmnopqrstuvwxyz";
    $chars .= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $chars .= "1234567890";

    $text = '';
    for($i = 0; $i < $length; $i++) {
        $text .= $chars[rand() % strlen($chars)];
    }
    return $text;
}

echo "Testing srand behavior:\n\n";

$mt = microtime(true);
echo "microtime(true) = " . sprintf("%.6f", $mt) . "\n";
echo "  Type: " . gettype($mt) . "\n";
echo "  As int: " . intval($mt) . "\n\n";

// Test 1: usando microtime(true) directamente
srand($mt);
$result1 = generate_random_text(10);
echo "Result with float: $result1\n";

// Test 2: usando microtime(true) convertido a entero
srand(intval($mt));
$result2 = generate_random_text(10);
echo "Result with int: $result2\n";

// Test 3: Múltiple el float por 1000000 y luego convertir a entero
srand(intval($mt * 1000000));
$result3 = generate_random_text(10);
echo "Result with int(mt*1000000): $result3\n";

echo "\n\nTesting reproducibility:\n";
$test_seed = 1759213700.123456;
echo "Test seed: " . sprintf("%.6f", $test_seed) . "\n\n";

srand($test_seed);
$t1 = generate_random_text(32);
echo "First run:  $t1\n";

srand($test_seed);
$t2 = generate_random_text(32);
echo "Second run: $t2\n";

if ($t1 === $t2) {
    echo "✓ Results are identical (reproducible)\n";
} else {
    echo "✗ Results differ (not reproducible)\n";
}
?>