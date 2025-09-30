<?php
// Script para generar captchas con un seed dado
// Replica la lógica de random.php

if ($argc != 2) {
    echo "Uso: php gen_captcha.php <seed>\n";
    exit(1);
}

$seed = floatval($argv[1]);

srand($seed);

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

$captcha = generate_random_text(255 / 10.0);
echo $captcha;
?>