#!/usr/bin/env python3

# Leer el archivo binario
with open('output.txt', 'rb') as f:
    data = f.read()

print(f"File size: {len(data)} bytes")

# Función para probar TODOS los shifts posibles en todo el archivo
def exhaustive_shift_analysis():
    print("=== EXHAUSTIVE SHIFT ANALYSIS ===")
    
    # Probar cada shift de 1 a 255 en todo el archivo
    for shift in range(1, 256):
        try:
            shifted_data = bytes([(b + shift) % 256 for b in data])
            shifted_str = shifted_data.decode('latin-1', errors='ignore')
            
            # Buscar patrones específicos de flag
            if 'FLAG{' in shifted_str:
                print(f"*** FOUND FLAG PATTERN with shift {shift} ***")
                # Extraer la flag completa
                start = shifted_str.find('FLAG{')
                end = shifted_str.find('}', start)
                if end > start:
                    flag = shifted_str[start:end+1]
                    print(f"FLAG: {flag}")
                    return flag
                    
            elif 'flag{' in shifted_str:
                print(f"*** FOUND flag pattern with shift {shift} ***")
                start = shifted_str.find('flag{')
                end = shifted_str.find('}', start)
                if end > start:
                    flag = shifted_str[start:end+1]
                    print(f"flag: {flag}")
                    return flag
                    
            elif 'ringzer0' in shifted_str.lower():
                print(f"*** FOUND ringzer0 with shift {shift} ***")
                # Buscar patrón completo
                pos = shifted_str.lower().find('ringzer0')
                # Buscar hacia atrás para encontrar {
                for i in range(pos, max(0, pos-100), -1):
                    if shifted_str[i] == '{':
                        # Buscar hacia adelante para encontrar }
                        for j in range(pos, min(len(shifted_str), pos+100)):
                            if shifted_str[j] == '}':
                                flag = shifted_str[i:j+1]
                                print(f"ringzer0 flag: {flag}")
                                return flag
                                break
                        break
                        
            elif 'ctf{' in shifted_str.lower():
                print(f"*** FOUND ctf pattern with shift {shift} ***")
                start = shifted_str.lower().find('ctf{')
                end = shifted_str.find('}', start)
                if end > start:
                    flag = shifted_str[start:end+1]
                    print(f"ctf flag: {flag}")
                    return flag
                    
            elif 'un1k0d3r' in shifted_str.lower():
                print(f"*** FOUND un1k0d3r with shift {shift} ***")
                pos = shifted_str.lower().find('un1k0d3r')
                for i in range(pos, max(0, pos-100), -1):
                    if shifted_str[i] == '{':
                        for j in range(pos, min(len(shifted_str), pos+100)):
                            if shifted_str[j] == '}':
                                flag = shifted_str[i:j+1]
                                print(f"un1k0d3r flag: {flag}")
                                return flag
                                break
                        break
                        
        except Exception as e:
            continue
    
    return None

# Función para buscar patrones XOR
def exhaustive_xor_analysis():
    print("\n=== EXHAUSTIVE XOR ANALYSIS ===")
    
    # Probar cada clave XOR de 1 a 255
    for key in range(1, 256):
        try:
            xor_data = bytes([b ^ key for b in data])
            xor_str = xor_data.decode('latin-1', errors='ignore')
            
            # Buscar patrones específicos de flag
            if 'FLAG{' in xor_str:
                print(f"*** FOUND FLAG PATTERN with XOR key {key} ***")
                start = xor_str.find('FLAG{')
                end = xor_str.find('}', start)
                if end > start:
                    flag = xor_str[start:end+1]
                    print(f"FLAG: {flag}")
                    return flag
                    
            elif 'flag{' in xor_str:
                print(f"*** FOUND flag pattern with XOR key {key} ***")
                start = xor_str.find('flag{')
                end = xor_str.find('}', start)
                if end > start:
                    flag = xor_str[start:end+1]
                    print(f"flag: {flag}")
                    return flag
                    
            elif 'ringzer0' in xor_str.lower():
                print(f"*** FOUND ringzer0 with XOR key {key} ***")
                pos = xor_str.lower().find('ringzer0')
                for i in range(pos, max(0, pos-100), -1):
                    if xor_str[i] == '{':
                        for j in range(pos, min(len(xor_str), pos+100)):
                            if xor_str[j] == '}':
                                flag = xor_str[i:j+1]
                                print(f"ringzer0 flag: {flag}")
                                return flag
                                break
                        break
                        
        except Exception as e:
            continue
    
    return None

# Función para buscar en ventanas específicas
def window_analysis():
    print("\n=== WINDOW ANALYSIS ===")
    
    # Analizar ventanas de diferentes tamaños
    for window_size in [50, 100, 200, 500]:
        print(f"\nAnalyzing windows of size {window_size}:")
        
        for start in range(0, len(data) - window_size, window_size // 4):
            window = data[start:start + window_size]
            
            # Probar diferentes shifts en esta ventana
            for shift in range(1, 256):
                try:
                    shifted = bytes([(b + shift) % 256 for b in window])
                    shifted_str = shifted.decode('latin-1', errors='ignore')
                    
                    # Buscar patrones de flag
                    if 'FLAG{' in shifted_str or 'flag{' in shifted_str:
                        print(f"  Found flag pattern at window {start}-{start+window_size} with shift {shift}")
                        print(f"  Content: {shifted_str}")
                        
                        # Extraer flag completa
                        flag_start = shifted_str.find('FLAG{') if 'FLAG{' in shifted_str else shifted_str.find('flag{')
                        flag_end = shifted_str.find('}', flag_start)
                        if flag_end > flag_start:
                            flag = shifted_str[flag_start:flag_end+1]
                            print(f"  *** FLAG FOUND: {flag} ***")
                            return flag
                            
                    elif 'ringzer0' in shifted_str.lower():
                        print(f"  Found ringzer0 at window {start}-{start+window_size} with shift {shift}")
                        print(f"  Content: {shifted_str}")
                        
                        # Buscar flag completa
                        pos = shifted_str.lower().find('ringzer0')
                        for i in range(pos, max(0, pos-50), -1):
                            if shifted_str[i] == '{':
                                for j in range(pos, min(len(shifted_str), pos+50)):
                                    if shifted_str[j] == '}':
                                        flag = shifted_str[i:j+1]
                                        print(f"  *** FLAG FOUND: {flag} ***")
                                        return flag
                                        break
                                break
                                
                except Exception as e:
                    continue
    
    return None

# Función para buscar patrones específicos en el archivo original
def search_original_patterns():
    print("\n=== SEARCHING ORIGINAL PATTERNS ===")
    
    # Buscar directamente en el archivo original sin decodificar
    flag_patterns = [
        b'FLAG{',
        b'flag{',
        b'Flag{',
        b'ringzer0',
        b'RingZer0',
        b'RINGZER0',
        b'un1k0d3r',
        b'Un1k0d3r',
        b'UN1K0D3R'
    ]
    
    for pattern in flag_patterns:
        pos = data.find(pattern)
        if pos != -1:
            print(f"Found '{pattern.decode('latin-1', errors='ignore')}' at position {pos}")
            # Mostrar contexto
            start = max(0, pos - 20)
            end = min(len(data), pos + len(pattern) + 50)
            context = data[start:end]
            print(f"Context: {context}")
            
            # Intentar extraer flag completa
            if b'{' in context:
                brace_start = context.find(b'{')
                brace_end = context.find(b'}', brace_start)
                if brace_end > brace_start:
                    potential_flag = context[brace_start:brace_end+1]
                    print(f"Potential flag: {potential_flag}")
    
    return None

# Función para buscar secuencias ASCII imprimibles largas
def find_ascii_sequences():
    print("\n=== FINDING ASCII SEQUENCES ===")
    
    current_ascii = b""
    ascii_sequences = []
    
    for byte in data:
        if 32 <= byte <= 126:  # ASCII imprimible
            current_ascii += bytes([byte])
        else:
            if len(current_ascii) > 10:  # Secuencias de más de 10 caracteres
                ascii_sequences.append(current_ascii)
            current_ascii = b""
    
    if len(current_ascii) > 10:
        ascii_sequences.append(current_ascii)
    
    print(f"Found {len(ascii_sequences)} ASCII sequences longer than 10 chars:")
    for i, seq in enumerate(ascii_sequences):
        seq_str = seq.decode('ascii')
        print(f"  {i+1}: {seq_str}")
        
        # Buscar patrones de flag en estas secuencias
        if any(word in seq_str.lower() for word in ['flag', 'ringzer0', 'ctf', 'un1k0d3r']):
            print(f"    *** This sequence contains flag keywords! ***")
            
            # Intentar extraer flag
            if '{' in seq_str and '}' in seq_str:
                start = seq_str.find('{')
                end = seq_str.find('}', start)
                if end > start:
                    flag = seq_str[start:end+1]
                    print(f"    *** FLAG FOUND: {flag} ***")
                    return flag
    
    return None

# Ejecutar todos los análisis
print("Starting exhaustive analysis...")

# 1. Análisis de shift exhaustivo
flag = exhaustive_shift_analysis()
if flag:
    print(f"\n*** FINAL FLAG FOUND: {flag} ***")
    exit()

# 2. Análisis XOR exhaustivo
flag = exhaustive_xor_analysis()
if flag:
    print(f"\n*** FINAL FLAG FOUND: {flag} ***")
    exit()

# 3. Análisis por ventanas
flag = window_analysis()
if flag:
    print(f"\n*** FINAL FLAG FOUND: {flag} ***")
    exit()

# 4. Buscar patrones originales
search_original_patterns()

# 5. Buscar secuencias ASCII
flag = find_ascii_sequences()
if flag:
    print(f"\n*** FINAL FLAG FOUND: {flag} ***")
    exit()

print("\n*** NO FLAG FOUND WITH STANDARD METHODS ***")
print("Trying alternative approaches...")

# 6. Buscar patrones específicos en diferentes codificaciones
print("\n=== TRYING ALTERNATIVE ENCODINGS ===")

# Probar diferentes combinaciones de shift y XOR
for shift in range(1, 256, 10):  # Probar cada 10 para ser más rápido
    for xor_key in range(1, 256, 10):
        try:
            # Aplicar shift primero, luego XOR
            shifted = bytes([(b + shift) % 256 for b in data])
            shifted_xor = bytes([b ^ xor_key for b in shifted])
            result = shifted_xor.decode('latin-1', errors='ignore')
            
            if 'FLAG{' in result or 'flag{' in result:
                print(f"Found flag with shift {shift} and XOR {xor_key}")
                start = result.find('FLAG{') if 'FLAG{' in result else result.find('flag{')
                end = result.find('}', start)
                if end > start:
                    flag = result[start:end+1]
                    print(f"*** FLAG FOUND: {flag} ***")
                    exit()
                    
        except:
            continue

print("\n*** COMPREHENSIVE ANALYSIS COMPLETE ***")
print("No standard flag patterns found. The flag might be encoded in a non-standard way.")