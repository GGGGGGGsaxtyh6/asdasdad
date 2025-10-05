#!/usr/bin/env python3
from PIL import Image

# Abrir la puta imagen
img = Image.open('msb_image.png')
pixels = img.load()
width, height = img.size

# Crear una nueva imagen para ver los MSB extraídos
msb_img = Image.new('RGB', (width, height))
msb_pixels = msb_img.load()

# Extraer el MSB (bit más significativo) de cada canal
for y in range(height):
    for x in range(width):
        r, g, b = pixels[x, y][:3]  # Ignorar alpha si existe
        
        # Extraer el MSB (bit 7, el más significativo)
        # Si el bit está en 1, ponerlo en 255 (blanco), si no en 0 (negro)
        msb_r = 255 if (r & 0x80) else 0
        msb_g = 255 if (g & 0x80) else 0
        msb_b = 255 if (b & 0x80) else 0
        
        msb_pixels[x, y] = (msb_r, msb_g, msb_b)

# Guardar la imagen con los MSB
msb_img.save('msb_extracted.png')
print("¡MSB extraído y guardado en msb_extracted.png!")
