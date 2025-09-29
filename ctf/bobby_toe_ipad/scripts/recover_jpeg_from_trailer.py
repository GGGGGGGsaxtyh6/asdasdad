#!/usr/bin/env python3
import sys
from pathlib import Path

trailer_path = Path('/workspace/ctf/bobby_toe_ipad/out/trailer.bin')
output_path = Path('/workspace/ctf/bobby_toe_ipad/out/recovered.jpg')

data = trailer_path.read_bytes()
needle = b'JFIF\x00'
idx = data.find(needle)
if idx == -1:
    sys.stderr.write('JFIF not found in trailer\n')
    sys.exit(1)

# Build JPEG header: SOI + APP0 (length 0x0010) before existing 'JFIF\0'
header = b"\xFF\xD8\xFF\xE0\x00\x10"
jpg = header + data[idx:]
output_path.write_bytes(jpg)
print(str(output_path))

