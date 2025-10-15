import time
from Crypto.Hash import SHA256

from Crypto.Util.number import long_to_bytes, bytes_to_long



flag = b'ictf{REDACTED}'


seed = 12289490237894271412 * (int(time.time()) | 63)

key = bytes_to_long(SHA256.new(long_to_bytes(seed)).digest())

enc = long_to_bytes(bytes_to_long(flag[:32]) ^ key) + long_to_bytes(bytes_to_long(flag[32:]) ^ key)
print(enc)
#output is b"\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1o}\x1a\xe0\x9e\xea\x08Q+\x8aX}mP\x7f\xe48\x16'\xcd\x85k\rk\xaf\x89z\x9b\x88\xe3\xa7\xf1\x06\x1en\x86\xe5\xb8M\x15j\xc9\x0c8)-"

