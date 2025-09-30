#!/bin/bash
(echo "y"; echo "test") | timeout 10s gdb -batch -ex "break *0x400938" -ex "run" -ex "x/xg 0x601160" -ex "x/s 0x601160" -ex "x/xg *0x601160" -ex "quit" ./GrownUpRedist 2>&1
