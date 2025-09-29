<?php
// Script para entender el comportamiento de preg_replace con /e

error_reporting(E_ALL);

function correct($word) {
    return "CORRECTED: " . $word;
}

echo "=== TEST 1: Comportamiento básico ===\n";
$blacklist = implode(["'", '"', '(', ')', ' ', '`']);
echo "Blacklist: $blacklist\n\n";

$test1 = "hello world";
echo "Input: $test1\n";
try {
    $result1 = preg_replace("/([^$blacklist]{2,})/ie", 'correct("\\1")', $test1);
    echo "Output: $result1\n\n";
} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n\n";
}

echo "=== TEST 2: Intentar inyección con \${} ===\n";
$test2 = 'test${phpinfo()}test';
echo "Input: $test2\n";
try {
    $result2 = preg_replace("/([^$blacklist]{2,})/ie", 'correct("\\1")', $test2);
    echo "Output: $result2\n\n";
} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n\n";
}

echo "=== TEST 3: Usando variables globales ===\n";
$_GET['cmd'] = 'ls';
$test3 = 'test$_GET[cmd]test';
echo "Input: $test3\n";
try {
    $result3 = preg_replace("/([^$blacklist]{2,})/ie", 'correct("\\1")', $test3);
    echo "Output: $result3\n\n";
} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n\n";
}

echo "=== TEST 4: Usando {} para bypass ===\n";
$test4 = '{${phpinfo}}';
echo "Input: $test4\n";
try {
    $result4 = preg_replace("/([^$blacklist]{2,})/ie", 'correct("\\1")', $test4);
    echo "Output: $result4\n\n";
} catch (Exception $e) {
    echo "Error: " . $e->getMessage() . "\n\n";
}

echo "PHP Version: " . PHP_VERSION . "\n";
echo "Nota: El modificador /e está deprecado desde PHP 5.5 y removido en PHP 7\n";

?>