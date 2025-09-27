#!/bin/bash
echo "Testing connection to challs.actf.co:31322..."
timeout 10s nc -v challs.actf.co 31322 << EOF
test
EOF