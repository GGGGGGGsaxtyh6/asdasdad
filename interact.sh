#!/bin/bash
# Script para interactuar con el host remoto
echo "$1" | timeout 10 nc 94.237.57.1 45927
