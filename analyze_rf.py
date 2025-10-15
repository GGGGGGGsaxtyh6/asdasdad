#!/usr/bin/env python3
import numpy as np
import struct

# Leer capture.bin como señal IQ
with open('/workspace/capture.bin', 'rb') as f:
    data = f.read()

# Convertir a array de floats (IQ samples)
samples = np.frombuffer(data, dtype=np.float32)

print(f"[*] Total samples: {len(samples)}")
print(f"[*] Duration (assuming 2M samples/sec): {len(samples)/2e6:.2f} segundos")

# Las muestras IQ vienen en pares (I, Q)
I = samples[0::2]  # In-phase
Q = samples[1::2]  # Quadrature

print(f"[*] I samples: {len(I)}")
print(f"[*] Q samples: {len(Q)}")

# Calcular magnitud de la señal
magnitude = np.sqrt(I**2 + Q**2)

print(f"[*] Max magnitude: {magnitude.max()}")
print(f"[*] Mean magnitude: {magnitude.mean()}")

# Buscar umbrales para decodificar ASK
threshold = magnitude.mean() + 2 * magnitude.std()
print(f"[*] Threshold: {threshold}")

# Decodificar como señal digital
digital = (magnitude > threshold).astype(int)

# Buscar transiciones
transitions = np.diff(digital)
up_transitions = np.where(transitions == 1)[0]
down_transitions = np.where(transitions == -1)[0]

print(f"[*] Up transitions: {len(up_transitions)}")
print(f"[*] Down transitions: {len(down_transitions)}")

# Si hay pulsos, intentar decodificar
if len(up_transitions) > 0 and len(down_transitions) > 0:
    # Analizar duración de pulsos
    pulse_lengths = []
    for i in range(min(len(up_transitions), len(down_transitions))):
        if i < len(down_transitions):
            pulse_len = down_transitions[i] - up_transitions[i]
            pulse_lengths.append(pulse_len)
    
    if pulse_lengths:
        print(f"[*] Pulse lengths (first 20): {pulse_lengths[:20]}")
        print(f"[*] Mean pulse length: {np.mean(pulse_lengths):.2f}")

# Buscar patrones en los bits
# Agrupar bits consecutivos
bit_groups = []
current_group = []
for i, bit in enumerate(digital):
    if bit == 1:
        current_group.append(i)
    else:
        if current_group:
            bit_groups.append(len(current_group))
            current_group = []

if bit_groups:
    print(f"[*] Bit groups (first 50): {bit_groups[:50]}")
    
# Intentar decodificar como bytes hex
# Buscar secuencias que parezcan paquetes
print("\n[*] Buscando patrones de paquetes...")

# Simplificar: buscar secuencias de 1s seguidas
ones_sequences = []
i = 0
while i < len(digital):
    if digital[i] == 1:
        count = 0
        while i < len(digital) and digital[i] == 1:
            count += 1
            i += 1
        ones_sequences.append(count)
    else:
        i += 1

print(f"[*] Ones sequences (first 100): {ones_sequences[:100]}")

# Buscar patrones hex conocidos
# A1, A2, A3, A4, E1, E2, E3, F1, FF
hex_patterns = ['A1', 'A2', 'A3', 'A4', 'E1', 'E2', 'E3', 'F1', 'FF', 'HTB']
data_str = data.hex().upper()
for pattern in hex_patterns:
    count = data_str.count(pattern)
    if count > 0:
        print(f"[+] Found pattern '{pattern}': {count} times")
        # Mostrar contexto
        idx = data_str.find(pattern)
        if idx >= 0:
            print(f"    Context: ...{data_str[max(0,idx-20):idx+len(pattern)+20]}...")
