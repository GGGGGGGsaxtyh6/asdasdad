#!/usr/bin/env python3
import os
import sys
import subprocess
import shlex


CHALL_BIN = "/workspace/reto_xor_50/challenge/image/challenge/challenge"


def run_local(payload_lines, timeout_sec=5):
    proc = subprocess.Popen(
        [CHALL_BIN],
        stdin=subprocess.PIPE,
        stdout=subprocess.PIPE,
        stderr=subprocess.STDOUT,
        text=True,
    )
    try:
        data = "\n".join(payload_lines) + "\n"
        out, _ = proc.communicate(data, timeout=timeout_sec)
    except subprocess.TimeoutExpired:
        proc.kill()
        out = "[timeout]"
    return out


def demo():
    # Minimal interaction: show banner, then write 1^2 to result[0]
    lines = ["1 2 0"]
    print(run_local(lines))


if __name__ == "__main__":
    demo()

