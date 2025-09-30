#!/bin/bash
(echo "y"; python3 -c "import sys; sys.stdout.buffer.write(b'H' * 140)") | timeout 10s gdb -batch \
  -ex "break *0x40094e" \
  -ex "run" \
  -ex "x/20xg 0x6010e0" \
  -ex "x/s 0x601160" \
  -ex "x/xg 0x601160" \
  -ex "continue" \
  -ex "quit" ./GrownUpRedist 2>&1
