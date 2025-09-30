#!/usr/bin/env bash
# Wrapper: timeout with any cmd
if [ $# -lt 2 ]; then echo "Usage: $0 <seconds> <cmd...>"; exit 1; fi; secs=$1; shift; exec timeout ${secs}s "$@"