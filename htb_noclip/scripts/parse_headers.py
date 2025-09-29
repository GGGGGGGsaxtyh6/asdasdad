#!/usr/bin/env python3
import struct, os

path = '/workspace/htb_noclip/extracted/gamepwn_noclip/assets.dmp'
out_csv = '/workspace/htb_noclip/outputs/headers_type4.csv'

def u32(f):
    d=f.read(4)
    if len(d)!=4: raise EOFError
    return struct.unpack('<I', d)[0]

rows=[]
with open(path,'rb') as f:
    idx_blk=0
    idx_tex_global=0
    while True:
        h=f.read(4)
        if not h: break
        t=struct.unpack('<I',h)[0]
        if t==4:
            count=u32(f)
            for i in range(count):
                header=f.read(0x40)
                if len(header)!=0x40: raise EOFError
                w=u32(f); hgt=u32(f)
                # Interpret header as 16 little-endian u32
                vals=list(struct.unpack('<16I', header))
                # Heuristics: guess posx,posy near screen size
                # Keep all fields for inspection
                rows.append((idx_blk,i,w,hgt)+tuple(vals))
                size=w*hgt*4
                f.seek(size,1)
            idx_blk+=1
        elif t==3:
            f.seek(0x40,1)
            w=u32(f); hgt=u32(f)
            f.seek(w*hgt*4,1)
        elif t==2:
            w=u32(f); hgt=u32(f)
            f.seek(w*hgt*3,1)
        elif t==1:
            f.seek(16,1)
        else:
            # unknown: stop to avoid desync
            break

with open(out_csv,'w') as o:
    o.write('block_idx,tex_idx,w,h,'+', '.join(f'h{i}' for i in range(16))+'\n')
    for r in rows:
        o.write(','.join(str(x) for x in r)+'\n')
print('[*] wrote', out_csv, 'rows=', len(rows))
