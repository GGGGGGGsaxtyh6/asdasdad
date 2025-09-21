#!/bin/bash
FLAG="FLAG-4092849uio2jfklsj4kl"

echo "Testing flag: $FLAG"
echo "Flag length: ${#FLAG}"

echo -e "\nTrying to run crackme1..."
echo "$FLAG" | timeout 3 ./crackme1

echo -e "\nTrying direct execution..."
timeout 3 ./crackme1 <<< "$FLAG"

echo -e "\nChecking if crackme1 expects input..."
timeout 3 ./crackme1 << EOF
$FLAG
EOF