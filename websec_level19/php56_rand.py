#!/usr/bin/env python3
"""
Implementación del LCG de PHP 5.6 para generar los mismos valores que rand()
"""

class PHP56Random:
    def __init__(self, seed):
        """Inicializa el generador con un seed"""
        self.state = int(seed) & 0x7FFFFFFF
    
    def rand(self):
        """
        Implementa el LCG de PHP 5.6
        PHP usa: state = (state * 1103515245 + 12345) % 2^31
        """
        self.state = (self.state * 1103515245 + 12345) & 0x7FFFFFFF
        # PHP retorna (state >> 16) & 0x7FFF para rand()
        return (self.state >> 16) & 0x7FFF

def generate_captcha(seed):
    """
    Genera el captcha usando el algoritmo de PHP 5.6
    """
    chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
    
    rng = PHP56Random(seed)
    text = ''
    
    for i in range(25):  # 255 / 10.0 = 25.5, truncado a 25
        rand_val = rng.rand()
        text += chars[rand_val % len(chars)]
    
    return text

if __name__ == "__main__":
    import sys
    if len(sys.argv) != 2:
        print("Uso: python3 php56_rand.py <seed>")
        sys.exit(1)
    
    seed = int(sys.argv[1])
    captcha = generate_captcha(seed)
    print(captcha)