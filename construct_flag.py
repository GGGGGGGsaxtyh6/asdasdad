#!/usr/bin/env python3

# Script para construir la flag basándome en los strings encontrados
# La flag se construye como: "Congratulations! Flag: %s%s%s"

# Strings encontrados:
# Parte 3 (confirmada): "_fsm_validated_3}"
# Parte 2 (confirmada): "space"
# Parte 1: Necesito encontrarla

# Strings con "Tf" que podrían ser parte de la primera parte:
part1_candidates = [
    "ocTf", "nCtf", "ATfH", "ATfI", "osTf", "okTf", "STfA", 
    "tfL1", "tfI9", "tfE1", "tfdA", "tfjgvmfv"
]

# Strings adicionales que podrían ser parte de la primera parte:
additional_candidates = [
    "CTF", "ctf", "Flag", "flag", "BT", "bt"
]

part2 = "space"
part3 = "_fsm_validated_3}"

print("Construyendo posibles flags...")
print(f"Parte 2 (confirmada): {part2}")
print(f"Parte 3 (confirmada): {part3}")
print()

# Probar diferentes combinaciones
flags = []

# Combinaciones con strings "Tf"
for p1 in part1_candidates:
    flag = p1 + part2 + part3
    flags.append(flag)
    print(f"Flag posible: {flag}")

print()

# Combinaciones con strings adicionales + {
for p1 in additional_candidates:
    for bracket in ["{", ""]:
        flag = p1 + bracket + part2 + part3
        flags.append(flag)
        print(f"Flag posible: {flag}")

print()

# Intentar con formato tradicional de CTF
traditional_flags = [
    f"CTF{{{part2}{part3}",
    f"ctf{{{part2}{part3}",
    f"Flag{{{part2}{part3}",
    f"flag{{{part2}{part3}",
    f"BT{{{part2}{part3}",
    f"bt{{{part2}{part3}"
]

for flag in traditional_flags:
    print(f"Flag tradicional: {flag}")

print(f"\nTotal de combinaciones generadas: {len(flags) + len(traditional_flags)}")

# Mostrar las flags más probables
print("\n=== FLAGS MÁS PROBABLES ===")
print("1. ocTfspace_fsm_validated_3}")
print("2. nCtfspace_fsm_validated_3}")
print("3. CTF{space_fsm_validated_3}")
print("4. BT{space_fsm_validated_3}")