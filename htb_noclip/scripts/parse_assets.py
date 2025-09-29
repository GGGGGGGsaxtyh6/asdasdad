#!/usr/bin/env python3
import struct, sys, os
from PIL import Image

a_path = '/workspace/htb_noclip/extracted/gamepwn_noclip/assets.dmp'
out_dir = '/workspace/htb_noclip/outputs/textures'
os.makedirs(out_dir, exist_ok=True)

def read_u32le(f):
    d = f.read(4)
    if len(d) != 4:
        raise EOFError
    return struct.unpack('<I', d)[0]

with open(a_path, 'rb') as f:
    # File begins with 32-bit type ids per chunk. We loop until EOF.
    idx_tex = 0
    idx_blk = 0
    while True:
        h = f.read(4)
        if not h:
            break
        chunk_type = struct.unpack('<I', h)[0]
        if chunk_type == 2:
            # load_assets reads two dwords then mallocs width*height*3
            w = read_u32le(f)
            hgt = read_u32le(f)
            size = w * hgt * 3
            buf = f.read(size)
            if len(buf) != size:
                break
            im = Image.frombytes('RGB', (w, hgt), buf)
            p = os.path.join(out_dir, f'tex_rgb_{idx_tex:03d}_{w}x{hgt}.png')
            im.save(p)
            print(f'[+] texture RGB {idx_tex} -> {p}')
            idx_tex += 1
        elif chunk_type == 4:
            # load_assets reads count, allocs count*0x50, then for each entry
            # calls load_assets_texture which reads 0x40 header, w, h, and RGBA pixels
            count = read_u32le(f)
            for i in range(count):
                header = f.read(0x40)
                if len(header) != 0x40:
                    raise EOFError
                w = read_u32le(f)
                hgt = read_u32le(f)
                size = w * hgt * 4
                buf = f.read(size)
                if len(buf) != size:
                    raise EOFError
                im = Image.frombytes('RGBA', (w, hgt), buf)
                p = os.path.join(out_dir, f'tex_group{idx_blk:02d}_{i:03d}_{w}x{hgt}.png')
                im.save(p)
                print(f'[+] texture group {idx_blk}:{i} -> {p}')
            idx_blk += 1
        elif chunk_type == 1:
            # probably two 32-bit values
            f.seek(16, 1)
        elif chunk_type == 3:
            # single texture via load_assets_texture
            # header 0x40 then w(4) h(4) then pixel data
            header = f.read(0x40)
            if len(header) != 0x40:
                break
            w = read_u32le(f)
            hgt = read_u32le(f)
            size = w * hgt * 4
            buf = f.read(size)
            if len(buf) != size:
                break
            im = Image.frombytes('RGBA', (w, hgt), buf)
            p = os.path.join(out_dir, f'tex_single_{idx_tex:03d}_{w}x{hgt}.png')
            im.save(p)
            print(f'[+] single texture -> {p}')
            idx_tex += 1
        else:
            # unknown chunk, try to resync: read next dword
            # to avoid infinite loop, bail
            print(f'[!] unknown chunk {chunk_type}, stopping')
            break
print('[*] done')
