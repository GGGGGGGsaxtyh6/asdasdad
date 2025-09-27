#!/bin/bash
echo "Testing different inputs..."
echo "1" | timeout 10s ./exam
echo "---"
echo "0" | timeout 10s ./exam
echo "---"
echo "100" | timeout 10s ./exam
echo "---"
echo "I confirm that I am taking this exam between the dates 5/24/2024 and 5/27/2024. I will not disclose any information about any section of this exam." | timeout 10s ./exam