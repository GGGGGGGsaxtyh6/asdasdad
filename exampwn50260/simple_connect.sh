#!/bin/bash
echo "Simple connection test..."
timeout 10s nc challs.actf.co 31322 << EOF
0
I confirm that I am taking this exam between the dates 5/24/2024 and 5/27/2024. I will not disclose any information about any section of this exam.
EOF