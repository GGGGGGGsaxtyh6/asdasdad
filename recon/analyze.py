import subprocess

# Primer intento: llamar create_user DOS veces
print("=== Test: llamar create_user dos veces ===")
payload = b"1\nUSER1\n100\n1\nUSER2\n200\n2\n4\n"
proc = subprocess.Popen(["./image/challenge/challenge"], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
stdout, stderr = proc.communicate(payload, timeout=5)
print(stdout.decode())

# Segundo intento: create, edit varias veces
print("\n=== Test: create, luego edit múltiple ===")
payload = b"1\nUSER1\n100\n3\nEDIT1\n111\n3\nEDIT2\n222\n2\n4\n"
proc = subprocess.Popen(["./image/challenge/challenge"], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
stdout, stderr = proc.communicate(payload, timeout=5)
print(stdout.decode())

# Tercer intento: print después de create
print("\n=== Test: create y print ===")
payload = b"1\nUSER1\n100\n2\n4\n"
proc = subprocess.Popen(["./image/challenge/challenge"], stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.PIPE)
stdout, stderr = proc.communicate(payload, timeout=5)
print(stdout.decode())
