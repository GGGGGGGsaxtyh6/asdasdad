#!/bin/bash

echo "Monitoring ECM processes..."

while true; do
    # Check all ECM log files for factors
    for logfile in ecm_*.log; do
        if [ -f "$logfile" ]; then
            if grep -q "Factor found\|^\*\*\*\*\*\*\*\*\*\*" "$logfile"; then
                echo "✓✓✓ FACTOR FOUND in $logfile!"
                grep -A 5 "Factor found\|^\*\*\*\*\*\*\*\*\*\*" "$logfile" | head -20
                
                # Extract the factor
                FACTOR=$(grep "Found.*factor of" "$logfile" | grep -oP '(?<=: )[0-9]+' | head -1)
                if [ ! -z "$FACTOR" ]; then
                    echo "Factor: $FACTOR"
                    echo "Calculating other factor..."
                    
                    python3 << EOFPY
from gmpy2 import mpz
n = mpz(0x4040000000000000000000000000000000000000000000000000000000000000004040000002020000000080800000000000000000000000000000000000000000000040000000000000000000000000000000002000000000000400000000000000000040000000020000000040c020000000000020000000010004000000513080000000080000000000000000000020000000010000000000400000000000008000000004000000000100000000004000000000000000000000000000000000201080000000040000000000000000000000084000000000010800200000000000000000000000000000000080084000000000000000000000000000000021)
factor = mpz($FACTOR)
other = n // factor
print(f"p = {factor}")
print(f"q = {other}")

with open('factors_found.txt', 'w') as f:
    f.write(f"p = {factor}\n")
    f.write(f"q = {other}\n")
    
print("\\n✓ Factors saved!")
EOFPY
                    
                    # Run the certificate creation script
                    ./create_cert_and_get_flag.sh
                fi
                
                exit 0
            fi
        fi
    done
    
    # Show progress
    echo -n "."
    sleep 10
done
