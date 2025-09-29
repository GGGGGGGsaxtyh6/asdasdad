#!/usr/bin/env python3
from PIL import Image
import os, sys
base = '/workspace/htb_noclip/outputs/textures'
out = '/workspace/htb_noclip/outputs/image_analysis'
os.makedirs(out, exist_ok=True)

big = os.path.join(base, 'tex_single_001_1024x906.png')
small = os.path.join(base, 'tex_rgb_000_43x21.png')

# Extract alpha channel from big image
im = Image.open(big).convert('RGBA')
r, g, b, a = im.split()
a.save(os.path.join(out, 'big_alpha.png'))
# Inverted alpha for visibility
inv = a.point(lambda v: 255 - v)
inv.save(os.path.join(out, 'big_alpha_invert.png'))
# Thresholded mask
mask = a.point(lambda v: 255 if v < 254 else 0)
mask.save(os.path.join(out, 'big_alpha_mask.png'))

# ASCII art from small 43x21 texture
sm = Image.open(small).convert('L')
chars = " .:-=+*#%@"
W = len(chars) - 1
w, h = sm.size
lines = []
for y in range(h):
    row = []
    for x in range(w):
        v = sm.getpixel((x, y))
        row.append(chars[int(v / 255 * W)])
    lines.append(''.join(row))
open(os.path.join(out, 'small_ascii.txt'), 'w').write('\n'.join(lines))
print('[*] Wrote', os.path.join(out, 'small_ascii.txt'))
