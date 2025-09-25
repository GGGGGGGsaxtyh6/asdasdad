#!/usr/bin/env python3
import subprocess
import sys

def bypass_hash():
    """Bypassea la verificación de hash usando GDB"""
    
    # Crear un script de GDB
    gdb_script = """
set confirm off
set pagination off
break *0x2909
run
set $rax=0x155318c9c24e38eb
continue
quit
"""
    
    with open('/tmp/gdb_script.gdb', 'w') as f:
        f.write(gdb_script)
    
    # Ejecutar GDB con el script
    try:
        result = subprocess.run([
            'gdb', '-batch', '-x', '/tmp/gdb_script.gdb', 
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
    bypass_hash()