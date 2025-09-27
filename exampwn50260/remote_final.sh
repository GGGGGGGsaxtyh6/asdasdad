#!/bin/bash
echo "Final attempt to connect to remote service..."
{
echo "2147483646"
echo "I confirm that I am taking this exam between the dates 5/24/2024 and 5/27/2024. I will not disclose any information about any section of this exam."
sleep 1
} | timeout 25s nc challs.actf.co 31322