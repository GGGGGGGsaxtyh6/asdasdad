#!/bin/bash
(echo "y"; python3 -c "import sys; sys.stdout.buffer.write(b'G' * 150)") | timeout 10s gdb -batch \
  -ex "break *0x400922" \
  -ex "run" \
  -ex "x/50xb \$rsi" \
  -ex "continue" \
  -ex "quit" ./GrownUpRedist 2>&1
