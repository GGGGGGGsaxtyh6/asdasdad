#!/bin/bash
set -e

echo "Reading factors..."
P=$(grep "^p = " factors_found.txt | cut -d' ' -f3)
Q=$(grep "^q = " factors_found.txt | cut -d' ' -f3)

echo "p = $P"
echo "q = $Q"

# Crear la clave privada CA con Python
python3 << 'EOFPY'
from asn1crypto import keys
import base64
from gmpy2 import mpz, invert
import sys

p_str = sys.argv[1]
q_str = sys.argv[2]

p = mpz(p_str)
q = mpz(q_str)
n = p * q
e = 65537

phi = (p - 1) * (q - 1)
d = invert(e, phi)

dmp1 = d % (p - 1)
dmq1 = d % (q - 1)
iqmp = int(invert(q, p))

rsa_key = keys.RSAPrivateKey({
    'version': 'two-prime',
    'modulus': int(n),
    'public_exponent': e,
    'private_exponent': int(d),
    'prime1': int(p),
    'prime2': int(q),
    'exponent1': int(dmp1),
    'exponent2': int(dmq1),
    'coefficient': iqmp,
})

der = rsa_key.dump()
pem_lines = [b'-----BEGIN RSA PRIVATE KEY-----']
b64_data = base64.b64encode(der)
for i in range(0, len(b64_data), 64):
    pem_lines.append(b64_data[i:i+64])
pem_lines.append(b'-----END RSA PRIVATE KEY-----')
pem = b'\n'.join(pem_lines) + b'\n'

with open('ca_key_final.pem', 'wb') as f:
    f.write(pem)

print("✓ CA key created!")
EOFPY

python3 - "$P" "$Q"

# Generar CSR de cliente si no existe
if [ ! -f client.csr ]; then
    openssl genrsa -out client_key.pem 2048
    openssl req -new -key client_key.pem -out client.csr -subj "/C=US/ST=California/L=Mountain View/O=Google/OU=CTF/CN=client"
fi

# Firmar certificado de cliente
openssl x509 -req -in client.csr -CA ca_cert.pem -CAkey ca_key_final.pem -CAcreateserial -out client_cert_final.pem -days 365 -sha256

echo "✓ Client certificate signed!"

# Conectarse y obtener la flag
echo "Connecting to server..."
curl -k --cert client_cert_final.pem --key client_key.pem https://tls.2024-bq.ctfcompetition.com:1337/ -v

echo "Done!"
