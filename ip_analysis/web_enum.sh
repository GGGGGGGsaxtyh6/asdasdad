#!/bin/bash

echo "Enumerating web directories and files..."

BASE_URL="https://us2.fsho.st"

# Common directories to test
DIRS=(
    "/admin"
    "/login"
    "/encrypted"
    "/cipher"
    "/crypto"
    "/secure"
    "/private"
    "/hidden"
    "/secret"
    "/flag"
    "/challenge"
    "/ctf"
    "/key"
    "/decrypt"
    "/encode"
    "/decode"
    "/base64"
    "/hex"
    "/binary"
    "/.htaccess"
    "/.htpasswd"
    "/robots.txt"
    "/sitemap.xml"
    "/config"
    "/backup"
    "/test"
    "/dev"
    "/api"
    "/v1"
    "/v2"
)

# Common files to test
FILES=(
    "/index.php"
    "/index.html"
    "/admin.php"
    "/login.php"
    "/config.php"
    "/backup.sql"
    "/readme.txt"
    "/flag.txt"
    "/key.txt"
    "/secret.txt"
    "/encrypted.txt"
    "/cipher.txt"
    "/challenge.txt"
    "/.env"
    "/.git/config"
    "/package.json"
    "/composer.json"
)

echo "Testing directories..."
for dir in "${DIRS[@]}"; do
    echo -n "Testing $dir: "
    response=$(timeout 3 curl -s -o /dev/null -w "%{http_code}" -k "$BASE_URL$dir")
    if [ "$response" != "404" ] && [ "$response" != "000" ]; then
        echo "FOUND ($response)"
        timeout 3 curl -s -k "$BASE_URL$dir" | head -10
        echo "---"
    else
        echo "Not found"
    fi
done

echo
echo "Testing files..."
for file in "${FILES[@]}"; do
    echo -n "Testing $file: "
    response=$(timeout 3 curl -s -o /dev/null -w "%{http_code}" -k "$BASE_URL$file")
    if [ "$response" != "404" ] && [ "$response" != "000" ]; then
        echo "FOUND ($response)"
        timeout 3 curl -s -k "$BASE_URL$file" | head -10
        echo "---"
    else
        echo "Not found"
    fi
done

echo "Web enumeration completed."