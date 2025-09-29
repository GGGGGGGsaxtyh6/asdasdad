#!/usr/bin/env python3
import os, math
from PIL import Image

tex_dir = '/workspace/htb_noclip/outputs/textures'
out_dir = '/workspace/htb_noclip/outputs/mosaic'
os.makedirs(out_dir, exist_ok=True)

# Collect group textures
group = sorted([os.path.join(tex_dir, f) for f in os.listdir(tex_dir) if f.startswith('tex_group00_') and f.endswith('.png')])
if not group:
    print('no group textures found')
    raise SystemExit

# Determine grid shape
n = len(group)
cols = int(math.ceil(math.sqrt(n)))
rows = int(math.ceil(n / cols))

# Load first to get tile size
first = Image.open(group[0]).convert('RGBA')
tw, th = first.size

# Create mosaic canvas
canvas = Image.new('RGBA', (cols * tw, rows * th), (0,0,0,0))
for idx, path in enumerate(group):
    img = Image.open(path).convert('RGBA')
    x = (idx % cols) * tw
    y = (idx // cols) * th
    canvas.paste(img, (x, y))

mosaic_path = os.path.join(out_dir, f'mosaic_{cols}x{rows}_{cols*tw}x{rows*th}.png')
canvas.save(mosaic_path)
print('[*] wrote', mosaic_path)

# Make grayscale and high-contrast versions
gray = canvas.convert('L')
gray_path = os.path.join(out_dir, 'mosaic_gray.png')
gray.save(gray_path)
inv = gray.point(lambda v: 255 - v)
inv_path = os.path.join(out_dir, 'mosaic_gray_invert.png')
inv.save(inv_path)
mask = gray.point(lambda v: 255 if v < 200 else 0)
mask_path = os.path.join(out_dir, 'mosaic_mask.png')
mask.save(mask_path)

# ASCII art helper
chars = " .:-=+*#%@"
W = len(chars) - 1

def ascii_art(image_path, cols=120):
    im = Image.open(image_path).convert('L')
    w, h = im.size
    scale = cols / float(w)
    nh = max(1, int(h * scale * 0.5))
    im = im.resize((cols, nh))
    lines = []
    for y in range(im.size[1]):
        row = []
        for x in range(im.size[0]):
            v = im.getpixel((x, y))
            row.append(chars[int(v / 255 * W)])
        lines.append(''.join(row))
    return '\n'.join(lines)

# ASCII for mosaic and big alpha mask
ascii_mosaic = ascii_art(mosaic_path, cols=120)
open(os.path.join(out_dir, 'mosaic_ascii.txt'), 'w').write(ascii_mosaic)
print('[*] wrote mosaic ASCII')

big_alpha_mask = '/workspace/htb_noclip/outputs/image_analysis/big_alpha_mask.png'
if os.path.exists(big_alpha_mask):
    ascii_alpha = ascii_art(big_alpha_mask, cols=120)
    open(os.path.join(out_dir, 'big_alpha_ascii.txt'), 'w').write(ascii_alpha)
    print('[*] wrote alpha mask ASCII')
else:
    print('[!] big_alpha_mask.png not found')
