#!/usr/bin/env python3

from PIL import Image
import numpy as np

def try_create_image():
    with open('flag.txt', 'rb') as f:
        data = f.read()

    print(f"Data length: {len(data)}")

    # Try different dimensions (width, height)
    dimensions = [
        (179, 32), (89, 64), (44, 128), (22, 256)
    ]

    for width, height in dimensions:
        if len(data) >= width * height * 3:
            try:
                # Create RGB image
                img_array = np.zeros((height, width, 3), dtype=np.uint8)

                for i in range(min(len(data), width * height * 3)):
                    row = (i // 3) // width
                    col = (i // 3) % width
                    channel = i % 3

                    if row < height:
                        img_array[row, col, channel] = data[i]

                # Save the image
                img = Image.fromarray(img_array)
                filename = f"flag_image_{width}x{height}.png"
                img.save(filename)
                print(f"Created image: {filename}")

            except Exception as e:
                print(f"Error creating {width}x{height}: {e}")

if __name__ == "__main__":
    try_create_image()