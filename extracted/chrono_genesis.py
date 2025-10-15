import time
from Crypto.Hash import SHA256

from Crypto.Util.number import long_to_bytes, bytes_to_long



flag = b'ictf{REDACTED}'


seed = 12289490237894271412 * (int(time.time()) | 63)

key = bytes_to_long(SHA256.new(long_to_bytes(seed)).digest())

enc = long_to_bytes(bytes_to_long(flag[:32]) ^ key) + long_to_bytes(bytes_to_long(flag[32:]) ^ key)
print(enc)
#output is b'd%\xc9KFj\xdd\xc4\xe5\\K\xa7\xb0\x9d\xdb\x13\x90&R\xf6\xf8\x0f\xb8\xd3\x9f]>\x1f]\xf9\xdeG\rF\xbd-=\x1f\xb3\xad\x9d\x03?\xce\xdd\x94\xdc\x06\xae(J\xf7\xc8\x00\xa5\xe9\xa7R%\tZ\xcf\xc9_'

