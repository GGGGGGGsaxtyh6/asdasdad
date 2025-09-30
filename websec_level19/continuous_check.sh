#!/bin/bash

while true; do
    if [ -f /workspace/websec_level19/FLAG.txt ]; then
        echo ""
        echo "══════════════════════════════════════════════════════════"
        echo "                  FLAG FOUND!"
        echo "══════════════════════════════════════════════════════════"
        cat /workspace/websec_level19/FLAG.txt
        echo "══════════════════════════════════════════════════════════"
        echo ""
        echo "Total processes that found it:"
        ps aux | grep php | grep -v grep | wc -l
        echo ""
        exit 0
    fi
    sleep 10
done