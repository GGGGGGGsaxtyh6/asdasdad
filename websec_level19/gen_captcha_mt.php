<?php
// Generador de captcha usando mt_rand en lugar de rand
// Por si el servidor usa una versión antigua de PHP

if ($argc != 2) {
    echo "Uso: php gen_captcha_mt.php <seed>\n";
    exit(1);
}

$seed = intval($argv[1]);  // Convertir a int directamente

// Usar mt_srand en lugar de srand
mt_srand($seed);

function generate_random_text($length) {
    $chars  = "abcdefghijklmnopqrstuvwxyz";
    $chars .= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $chars .= "1234567890";

    $text = '';
    for($i = 0; $i < $length; $i++) {
        $text .= $chars[mt_rand() % strlen($chars)];
    }
    return $text;
}

$captcha = generate_random_text(intval(255 / 10.0));
echo $captcha;
?>