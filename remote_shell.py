#!/usr/bin/env python3
import pexpect
import sys
import time

def run_command(cmd, timeout=20):
    try:
        child = pexpect.spawn(f'nc 94.237.57.1 45927', timeout=timeout, encoding='utf-8')
        # Esperar el prompt con más tiempo
        time.sleep(5)
        child.expect(r'~ \$', timeout=15)
        # Enviar comando
        child.sendline(cmd)
        time.sleep(1)
        # Capturar salida
        child.sendline('echo "===END==="')
        child.expect('===END===', timeout=10)
        output = child.before
        child.sendline('exit')
        child.close()
        return output
    except pexpect.TIMEOUT as e:
        try:
            output = child.before if hasattr(child, 'before') else ""
            child.close()
            return f"Timeout but got: {output}"
        except:
            return f"Timeout: {str(e)}"
    except Exception as e:
        return f"Error: {str(e)}"

if __name__ == "__main__":
    if len(sys.argv) > 1:
        cmd = ' '.join(sys.argv[1:])
        print(run_command(cmd))
    else:
        print("Usage: ./remote_shell.py <command>")
