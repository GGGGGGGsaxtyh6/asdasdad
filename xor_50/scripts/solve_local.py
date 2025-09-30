#!/usr/bin/env python3
import os
import subprocess
import sys
import time

BIN = "/workspace/xor_50/image/challenge/challenge"

def run_once(a: int, b: int, idx: int, timeout_s: float = 5.0) -> str:
    p = subprocess.Popen([BIN], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT, text=True)
    try:
        # small delay not needed; feed input immediately
        out, _ = p.communicate(f"{a} {b} {idx}\n", timeout=timeout_s)
    except subprocess.TimeoutExpired:
        p.kill()
        try:
            out, _ = p.communicate(timeout=1)
        except Exception:
            out = ""
    return out

if __name__ == "__main__":
    # Sanity: read banner and one result without crashing
    out = run_once(1, 2, 1, timeout_s=5.0)
    sys.stdout.write(out)
