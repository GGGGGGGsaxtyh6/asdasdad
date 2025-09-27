#!/bin/bash
echo "Testing different detrust values..."
for val in 0 1 2 100 1000 10000; do
    echo "Testing detrust = $val"
    {
    echo "$val"
    echo "dummy"
    } | timeout 5s ./exam 2>/dev/null | head -5
    echo "---"
done