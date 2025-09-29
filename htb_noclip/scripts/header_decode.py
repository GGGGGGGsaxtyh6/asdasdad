#!/usr/bin/env python3
import csv, sys
from pathlib import Path
src = Path('/workspace/htb_noclip/outputs/headers_type4.csv')
out = Path('/workspace/htb_noclip/outputs/headers_decoded.txt')
lines=[]
with src.open() as f:
    r=csv.reader(f)
    header=next(r, None)
    for row in r:
        if not row: continue
        b= int(row[0]); t=int(row[1]); w=int(row[2]); h=int(row[3])
        vals=[int(x) for x in row[4:20]]
        def as_hex(x): return f'0x{x:08x}'
        def as_ascii(x):
            b=x.to_bytes(4,'little',signed=False)
            return ''.join(chr(c) if 32<=c<=126 else '.' for c in b)
        hexs=[as_hex(x) for x in vals]
        asc =[as_ascii(x) for x in vals]
        lines.append(f'{b},{t} {w}x{h}\nHEX: {' '.join(hexs)}\nASC: {' '.join(asc)}\n')
out.write_text('\n'.join(lines))
print('[*] wrote', str(out))
