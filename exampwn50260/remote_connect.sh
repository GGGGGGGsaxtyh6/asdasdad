#!/bin/bash
echo "Connecting to challs.actf.co:31322..."
{
echo "I confirm that I am taking this exam between the dates 5/24/2024 and 5/27/2024. I will not disclose any information about any section of this exam."
sleep 1
} | timeout 15s nc challs.actf.co 31322