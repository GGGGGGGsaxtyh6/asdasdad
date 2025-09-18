#!/usr/bin/env python3

# Script para intentar construir la flag basándome en los strings encontrados
# La flag se construye como: "Congratulations! Flag: %s%s%s"

# Strings encontrados que podrían ser partes de la flag:
part1_candidates = ["ocTf", "nCtf", "okTf", "osTf", "STfA", "tfdA", "tfE1", "tfI9", "tfL1"]
part2_candidates = ["space", "Space", "SPACE", "_", ""]
part3_candidates = ["CtFH", "ATfH", "ATfI"]

print("Intentando construir la flag...")
print("Strings encontrados:")
print("Parte 1:", part1_candidates)
print("Parte 2:", part2_candidates)
print("Parte 3:", part3_candidates)
print()

# Intentar diferentes combinaciones
possible_flags = []

for p1 in part1_candidates:
    for p2 in part2_candidates:
        for p3 in part3_candidates:
            flag = p1 + p2 + p3
            possible_flags.append(flag)
            print(f"Posible flag: {flag}")

print(f"\nTotal de combinaciones: {len(possible_flags)}")

# Basándome en el patrón "ocTfspace" que encontré, la flag más probable podría ser:
print("\nFlags más probables basadas en patrones encontrados:")
print("1. ocTfspaceCtFH")
print("2. nCtfspaceCtFH") 
print("3. ocTf_CtFH")
print("4. nCtf_CtFH")