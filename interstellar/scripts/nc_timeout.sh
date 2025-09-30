#!/usr/bin/env bash
# Wrapper: timeout with nc
if [ $# -lt 2 ]; then echo "Usage: $0 <seconds> <nc args...>"; exit 1; fi; secs=$1; shift; timeout ${secs}s nc "$@"