#!/bin/bash
{
    sleep 1
    echo "1"
    sleep 3
} | timeout 15s nc 94.237.57.211 55233 2>&1 | tee manual_output.txt
