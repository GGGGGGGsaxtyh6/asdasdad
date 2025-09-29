#!/usr/bin/env python3
import os, subprocess
from PIL import Image, ImageChops

inputs = [
  '/workspace/htb_noclip/outputs/textures/tex_single_001_1024x906.png',
  '/workspace/htb_noclip/outputs/mosaic/mosaic_5x4_640x512.png'
]
base_out = '/workspace/htb_noclip/outputs/ocr'
os.makedirs(base_out, exist_ok=True)

variants = []

def save(img, name):
    p = os.path.join(base_out, name)
    img.save(p)
    return p

for path in inputs:
    if not os.path.exists(path):
        continue
    name = os.path.splitext(os.path.basename(path))[0]
    im = Image.open(path).convert('RGBA')
    r,g,b,a = im.split()
    # raw channels
    variants.append(save(r, f'{name}_R.png'))
    variants.append(save(g, f'{name}_G.png'))
    variants.append(save(b, f'{name}_B.png'))
    variants.append(save(a, f'{name}_A.png'))
    # diffs
    rg = Image.eval(ImageChops.difference(r,g), lambda v: v)
    gb = Image.eval(ImageChops.difference(g,b), lambda v: v)
    rb = Image.eval(ImageChops.difference(r,b), lambda v: v)
    variants.append(save(rg, f'{name}_RminusG.png'))
    variants.append(save(gb, f'{name}_GminusB.png'))
    variants.append(save(rb, f'{name}_RminusB.png'))
    # invert and threshold per channel
    for layer, lname in [(r,'R'),(g,'G'),(b,'B'),(a,'A')]:
        inv = layer.point(lambda v: 255 - v)
        variants.append(save(inv, f'{name}_{lname}_inv.png'))
        for th in (32,64,96,128,160,192,224):
            bw = layer.point(lambda v, t=th: 255 if v < t else 0)
            variants.append(save(bw, f'{name}_{lname}_th{th}.png'))

# LSB planes
for path in inputs:
    if not os.path.exists(path):
        continue
    name = os.path.splitext(os.path.basename(path))[0]
    im = Image.open(path).convert('RGB')
    for bit in range(1,3):
        scale = 255 // (2**bit)
        for ci, cname in enumerate('RGB'):
            chan = im.split()[ci]
            plane = chan.point(lambda v, b=bit: 255 if (v >> 0) & ((1<<b)-1) else 0)
            variants.append(save(plane, f'{name}_{cname}_LSB{bit}.png'))

# OCR on all variants
for img_path in variants:
    out_txt = img_path + '.txt'
    try:
        subprocess.run(['tesseract', img_path, img_path], stdout=subprocess.DEVNULL, stderr=subprocess.DEVNULL, check=False)
    except Exception:
        pass

print('[*] OCR complete over', len(variants), 'variants')
