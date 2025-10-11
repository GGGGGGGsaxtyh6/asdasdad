#!/bin/bash
# Monitor ECM and automatically process when factors are found

check_interval=30
max_checks=1000

for ((i=1; i<=max_checks; i++)); do
    echo "Check $i at $(date)"
    
    for logfile in ecm_*.log; do
        if [ -f "$logfile" ]; then
            if grep -q "Factor found" "$logfile" 2>/dev/null; then
                echo "✓✓✓ FACTOR FOUND in $logfile!"
                grep -B2 -A10 "Factor found" "$logfile"
                
                # Extract and process
                python3 << 'EOFPY'
import re
import subprocess
from gmpy2 import mpz

n = mpz(0x4040000000000000000000000000000000000000000000000000000000000000004040000002020000000080800000000000000000000000000000000000000000000040000000000000000000000000000000002000000000000400000000000000000040000000020000000040c020000000000020000000010004000000513080000000080000000000000000000020000000010000000000400000000000008000000004000000000100000000004000000000000000000000000000000000201080000000040000000000000000000000084000000000010800200000000000000000000000000000000080084000000000000000000000000000000021)

# Read all ECM logs
import glob
for logfile in glob.glob('ecm_*.log'):
    with open(logfile, 'r') as f:
        content = f.read()
        
    # Look for factor
    match = re.search(r'Factor found.*?:.*?(\d+)', content, re.DOTALL)
    if match:
        factor_str = match.group(1)
        factor = mpz(factor_str)
        
        print(f"Found factor: {factor}")
        
        if n % factor == 0:
            other = n // factor
            print(f"✓ Verified! Other factor: {other}")
            
            with open('factors_found.txt', 'w') as f:
                f.write(f"p = {factor}\n")
                f.write(f"q = {other}\n")
            
            print("Running certificate script...")
            subprocess.run(['./create_cert_and_get_flag.sh'])
            exit(0)
EOFPY
                
                exit 0
            fi
        fi
    done
    
    # Show status
    running=$(ps aux | grep -c "[e]cm -c")
    echo "  $running ECM processes still running"
    
    sleep $check_interval
done

echo "Monitoring complete"
