#!/usr/bin/env python3

import subprocess
import tempfile
import os

def create_gdb_script():
    """Crea un script de GDB para debugging dinámico"""
    
    gdb_commands = """
# Cargar el binario
file neon_relay_patched

# Establecer breakpoint en la comparación memcmp
break *0x1aac

# Establecer breakpoint en la comparación de longitud
break *0x1a95

# Ejecutar con input
run

# Cuando llegue al breakpoint de longitud, mostrar registros
commands 1
    info registers
    x/6c $rsi
    continue
end

# Cuando llegue al breakpoint de memcmp, mostrar datos
commands 2
    info registers
    x/24c $rsi
    x/24c $rdi
    continue
end

# Continuar ejecución
continue
"""
    
    with tempfile.NamedTemporaryFile(mode='w', suffix='.gdb', delete=False) as f:
        f.write(gdb_commands)
        return f.name

def run_gdb_debug():
    """Ejecuta GDB con el script de debugging"""
    
    script_file = create_gdb_script()
    
    try:
        # Ejecutar GDB con el script
        cmd = f"echo 'test123' | gdb -batch -x {script_file}"
        result = subprocess.run(cmd, shell=True, capture_output=True, text=True, timeout=30)
        
        print("=== OUTPUT DE GDB ===")
        print(result.stdout)
        print("=== ERROR DE GDB ===")
        print(result.stderr)
        
    except subprocess.TimeoutExpired:
        print("GDB timeout")
    except Exception as e:
        print(f"Error ejecutando GDB: {e}")
    finally:
        # Limpiar archivo temporal
        os.unlink(script_file)

def simple_gdb_test():
    """Prueba simple con GDB"""
    
    print("=== PRUEBA SIMPLE CON GDB ===")
    
    # Crear input temporal
    with tempfile.NamedTemporaryFile(mode='w', delete=False) as f:
        f.write("test123\n")
        input_file = f.name
    
    try:
        # Ejecutar GDB con comandos básicos
        gdb_cmd = f"""
file neon_relay_patched
break *0x1aac
run < {input_file}
info registers
x/24c $rsi
x/24c $rdi
quit
"""
        
        with tempfile.NamedTemporaryFile(mode='w', suffix='.gdb', delete=False) as f:
            f.write(gdb_cmd)
            script_file = f.name
        
        result = subprocess.run(f"gdb -batch -x {script_file}", 
                              shell=True, capture_output=True, text=True, timeout=20)
        
        print("STDOUT:")
        print(result.stdout)
        print("STDERR:")
        print(result.stderr)
        
        os.unlink(script_file)
        
    except Exception as e:
        print(f"Error: {e}")
    finally:
        os.unlink(input_file)

if __name__ == "__main__":
    simple_gdb_test()