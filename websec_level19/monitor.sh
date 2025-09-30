#!/bin/bash

echo "Monitoring for FLAG..."

while true; do
    if [ -f /workspace/websec_level19/FLAG.txt ]; then
        echo ""
        echo "╔════════════════════════════════════════════════════════════╗"
        echo "║                  🎉 FLAG FOUND! 🎉                        ║"
        echo "╠════════════════════════════════════════════════════════════╣"
        cat /workspace/websec_level19/FLAG.txt | while read line; do
            echo "║  $line"
        done
        echo "╚════════════════════════════════════════════════════════════╝"
        exit 0
    fi
    sleep 5
done