#!/bin/bash
cd /workspace/xor_ctf
echo "=== Test local ===" 
(echo "5 3 1"; echo "10 7 2"; sleep 1) | timeout 5s ./image/challenge/challenge
echo ""
echo "Exit code: $?"