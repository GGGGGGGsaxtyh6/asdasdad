#!/usr/bin/env python3
import subprocess
import sys
import os

def memory_patch():
    """Modifica la memoria directamente para bypassear la verificación"""
    
    # Crear un script de GDB que modifique la comparación
    gdb_script = """
set confirm off
set pagination off
break main
run
break *0x2909
continue
set $rax=0x155318c9c24e38eb
continue
quit
"""
    
    with open('/tmp/gdb_script3.gdb', 'w') as f:
        f.write(gdb_script)
    
    # Ejecutar GDB
    try:
        result = subprocess.run([
            'gdb', '-batch', '-x', '/tmp/gdb_script3.gdb', 
            './ghostrace_node'
        ], input='test\n', text=True, capture_output=True, timeout=30)
        
        print("STDOUT:", result.stdout)
        print("STDERR:", result.stderr)
        print("Return code:", result.returncode)
        
    except subprocess.TimeoutExpired:
        print("Timeout expired")
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    memory_patch()