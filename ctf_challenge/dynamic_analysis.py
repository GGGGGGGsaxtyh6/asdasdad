#!/usr/bin/env python3

import subprocess
import tempfile
import os

def create_gdb_script_with_input(input_text):
    """Crea un script de GDB con input específico"""
    
    gdb_commands = f"""
# Cargar el binario
file neon_relay_patched

# Establecer breakpoint en la comparación memcmp
break *0x1aac

# Ejecutar con input
run

# Cuando llegue al breakpoint, mostrar datos
commands 1
    echo "=== BREAKPOINT EN MEMCMP ===\\n"
    info registers
    echo "\\n=== DATOS EN RSI (objetivo) ==="
    x/24c $rsi
    echo "\\n=== DATOS EN RDI (entrada) ==="
    x/24c $rdi
    echo "\\n=== COMPARACIÓN ==="
    x/6i $pc
    continue
end

# Continuar ejecución
continue
"""
    
    with tempfile.NamedTemporaryFile(mode='w', suffix='.gdb', delete=False) as f:
        f.write(gdb_commands)
        return f.name

def test_with_gdb(input_text):
    """Prueba un input específico con GDB"""
    
    print(f"=== PROBANDO: '{input_text}' ===")
    
    # Crear archivo de input
    with tempfile.NamedTemporaryFile(mode='w', delete=False) as f:
        f.write(input_text + "\n")
        input_file = f.name
    
    # Crear script de GDB
    script_file = create_gdb_script_with_input(input_text)
    
    try:
        # Ejecutar GDB
        cmd = f"gdb -batch -x {script_file} < {input_file}"
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=15)
        
        print("STDOUT:")
        print(result.stdout)
        print("STDERR:")
        print(result.stderr)
        
        # Verificar si fue exitoso
        if "Handshake synchronized" in result.stdout:
            print("✓ ¡ÉXITO!")
            return True
        else:
            print("✗ Falló")
            return False
            
    except subprocess.TimeoutExpired:
        print("Timeout")
        return False
    except Exception as e:
        print(f"Error: {e}")
        return False
    finally:
        # Limpiar archivos temporales
        os.unlink(input_file)
        os.unlink(script_file)

def test_specific_inputs():
    """Prueba inputs específicos"""
    
    test_inputs = [
        "NEON01", "RELAY1", "SYNC01", "CHRON1", "TEMPO1", "PHASE1",
        "LOCK01", "KEY001", "CODE01", "AUTH01", "LOGIN1", "ACCESS",
        "SECRET", "TOKEN1", "FLAG01", "CRONO1", "TEMPOR", "PHASER",
        "NEON!!", "RELAY!", "SYNC!!", "CHRON!", "TEMPO!", "PHASE!",
        "NEON**", "RELAY*", "SYNC**", "CHRON*", "TEMPO*", "PHASE*",
        "DEADBE", "CAFEBE", "BEEFCA", "FEEDBA", "BABECA", "CABEBA",
        "000000", "111111", "123456", "654321", "000001", "000010",
        "AAAAAA", "BBBBBB", "CCCCCC", "DDDDDD", "EEEEEE", "FFFFFF"
    ]
    
    for input_text in test_inputs:
        if test_with_gdb(input_text):
            print(f"\n🎉 HANDSHAKE ENCONTRADO: '{input_text}'")
            return input_text
        print()
    
    print("No se encontró el handshake con los inputs probados.")
    return None

if __name__ == "__main__":
    result = test_specific_inputs()
    if result:
        print(f"\n✅ RESULTADO FINAL: '{result}'")
    else:
        print("\n❌ No se pudo encontrar el handshake.")