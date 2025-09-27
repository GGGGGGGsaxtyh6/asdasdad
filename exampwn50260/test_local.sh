#!/bin/bash
echo "Testing local execution with correct input..."
{
echo "0"  # detrust value
echo "I confirm that I am taking this exam between the dates 5/24/2024 and 5/27/2024. I will not disclose any information about any section of this exam."
} | timeout 15s ./exam