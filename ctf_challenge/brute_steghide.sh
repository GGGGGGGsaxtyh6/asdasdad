#!/bin/bash

# Lista de contraseñas a probar
passwords=(
    ""
    "a"
    "If"
    "you"
    "are"
    "gentle"
    "hacker"
    "welcome"
    "to"
    "download"
    "files"
    "fucking"
    "bot"
    "that"
    "scan"
    "the"
    "Internet"
    "using"
    "stupid"
    "heuristic"
    "rules"
    "sucks"
    "then"
    "not"
    "Sincerely"
    "Mr"
    "Un1k0d3r"
    "password"
    "flag"
    "ctf"
    "ringzer0"
    "123456"
    "123"
    "2020"
    "0316"
    "1800"
    "8c80cb5cc7b85aee2ecc5c613af111c8"
    "2033bb1b194adace86f99c7bb7d72e81"
    "un1k0d3r"
    "UN1K0D3R"
    "center"
    "authorized"
    "mr.un1k0d3r"
    "Mr.Un1k0d3r"
    "admin"
    "root"
    "test"
    "hidden"
    "secret"
    "key"
)

for password in "${passwords[@]}"; do
    echo "Trying password: '$password'"
    if timeout 10 steghide extract -sf 2033bb1b194adace86f99c7bb7d72e81.jpg -p "$password" 2>/dev/null; then
        echo "SUCCESS! Password found: '$password'"
        ls -la
        exit 0
    fi
done

echo "No password found in the list"