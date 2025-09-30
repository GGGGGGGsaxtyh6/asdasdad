#!/bin/bash

# Script para explorar memoria con GDB

gdb -batch -ex "set disable-randomization off" -ex "run" -ex "info proc mappings" ./image/challenge/challenge <<< "1 2 1"