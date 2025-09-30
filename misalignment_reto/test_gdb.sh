#!/bin/bash
gdb -batch -ex "run" -ex "bt" ./image/challenge/challenge << INPUT
-6 0 181
INPUT
