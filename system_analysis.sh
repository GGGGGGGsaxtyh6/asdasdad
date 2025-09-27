#!/bin/bash

echo "=== SYSTEM ANALYSIS ==="
echo "Date: $(date)"
echo "User: $(whoami)"
echo "Hostname: $(hostname)"
echo "Uptime: $(uptime)"

echo -e "\n=== RUNNING PROCESSES ==="
ps aux | head -20

echo -e "\n=== NETWORK CONNECTIONS ==="
netstat -tuln | head -10

echo -e "\n=== MEMORY USAGE ==="
free -h

echo -e "\n=== DISK USAGE ==="
df -h

echo -e "\n=== RECENT LOGIN ACTIVITY ==="
last | head -5

echo -e "\n=== SYSTEM LOGS (last 10 lines) ==="
tail -10 /var/log/syslog

echo -e "\n=== CHECKING FOR SUSPICIOUS PROCESSES ==="
ps aux | grep -E "(nc|netcat|reverse|shell|backdoor)" | grep -v grep

echo -e "\n=== CHECKING FOR SUSPICIOUS FILES ==="
find /tmp /var/tmp -type f -name "*.sh" -o -name "*.py" -o -name "*backdoor*" 2>/dev/null | head -5

echo -e "\n=== ANALYSIS COMPLETE ==="