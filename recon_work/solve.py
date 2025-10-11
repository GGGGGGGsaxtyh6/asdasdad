#!/usr/bin/env python3
import socket
import time
from Crypto.Util.number import bytes_to_long, long_to_bytes

def extract_base_key(new_key):
    """Extract base_key from position 2, 5, or 8 (they're all the same)"""
    return (new_key >> 350) & ((1 << 50) - 1)

def collect_base_keys(host, port, count=50):
    """Collect base_keys from the server"""
    print(f"Connecting to {host}:{port}...")
    
    for attempt in range(30):
        try:
            s = socket.socket()
            s.settimeout(5)
            s.connect((host, port))
            print(f"✓ Connected!")
            break
        except Exception as e:
            print(f"Attempt {attempt + 1}/30 failed, retrying...")
            time.sleep(2)
    else:
        print("Failed to connect after 30 attempts")
        return None
    
    banner = s.recv(4096)
    
    base_keys = []
    msg = "A" * 62
    msg_long = bytes_to_long(msg.encode())
    
    print(f"Collecting {count} base_keys...")
    for i in range(count):
        try:
            s.sendall((msg + "\n").encode())
            data = s.recv(4096).decode(errors='ignore')
            
            if "Encrypted Message:" in data:
                for line in data.split("\n"):
                    if "Encrypted Message:" in line:
                        encrypted_hex = line.split("Encrypted Message: ")[1].strip().split()[0]
                        encrypted_long = int(encrypted_hex, 16)
                        key = msg_long ^ encrypted_long
                        base_key = extract_base_key(key)
                        base_keys.append(base_key)
                        if (i + 1) % 10 == 0:
                            print(f"  Collected {i + 1}/{count}")
                        break
        except Exception as e:
            print(f"Error at {i}: {e}")
            break
    
    s.sendall(b"exit\n")
    s.close()
    
    print(f"✓ Collected {len(base_keys)} base_keys")
    return base_keys

def search_generator(base_keys, k=0x13373):
    """Exhaustive search for generator"""
    print("\nSearching for generator...")
    print(f"Base keys count: {len(base_keys)}")
    print(f"First 5 base_keys: {[hex(bk) for bk in base_keys[:5]]}")
    
    t_now = int(time.time())
    
    # Search strategy: try multiple generator sizes and nonce ranges
    generator_bit_ranges = [
        (240, 260), (260, 280), (280, 300), (300, 320), 
        (320, 340), (340, 360), (360, 380), (380, 400)
    ]
    
    nonce_ranges = [
        # Small nonces (testing values)
        (1, 10000, 100),
        (10000, 100000, 1000),
        (100000, 1000000, 10000),
        # Recent timestamps (last 7 days)
        (t_now - 7*24*3600, t_now, 3600),
        # Older timestamps (up to 30 days)
        (t_now - 30*24*3600, t_now - 7*24*3600, 86400),
    ]
    
    for gen_start, gen_end in generator_bit_ranges:
        print(f"\nSearching generator size: {gen_start}-{gen_end} bits")
        
        for nonce_start, nonce_end, nonce_step in nonce_ranges:
            print(f"  Nonce range: {nonce_start} to {nonce_end} (step={nonce_step})")
            
            for nonce in range(nonce_start, nonce_end, nonce_step):
                if nonce <= 0:
                    continue
                
                nk = nonce * k
                
                # Try different generator sizes in this range
                for gen_bits in range(gen_start, gen_end, 5):
                    estimated_q = (1 << gen_bits) // nk if nk > 0 else 0
                    
                    # Fine-grained search around estimated q
                    q_range = 10000
                    for q in range(max(1, estimated_q - q_range), estimated_q + q_range, 100):
                        gen = base_keys[0] + q * nk
                        
                        # Quick validation with first 3 base_keys
                        if (gen % (nonce * k) == base_keys[0] and
                            gen % ((nonce + 1) * k) == base_keys[1] and
                            gen % ((nonce + 2) * k) == base_keys[2]):
                            
                            # Full validation
                            matches = sum(1 for i in range(len(base_keys)) 
                                        if gen % ((nonce + i) * k) == base_keys[i])
                            
                            if matches == len(base_keys):
                                print(f"\n{'='*60}")
                                print(f"✓✓✓ FOUND GENERATOR!")
                                print(f"{'='*60}")
                                print(f"Nonce: {nonce}")
                                print(f"q: {q}")
                                print(f"Generator bits: {gen.bit_length()}")
                                print(f"Matches: {matches}/{len(base_keys)}")
                                
                                try:
                                    flag = long_to_bytes(gen).decode('utf-8')
                                    print(f"\nFLAG: {flag}")
                                    return flag
                                except Exception as e:
                                    print(f"Decode error: {e}")
                                    print(f"Generator hex: {hex(gen)[:100]}...")
                            
                            elif matches >= len(base_keys) * 0.95:
                                print(f"    Close match: nonce={nonce}, q={q}, bits={gen.bit_length()}, matches={matches}/{len(base_keys)}")
    
    print("\nNo solution found")
    return None

if __name__ == "__main__":
    HOST = "94.237.56.254"
    PORT = 59287
    
    base_keys = collect_base_keys(HOST, PORT, count=50)
    
    if base_keys and len(base_keys) >= 20:
        flag = search_generator(base_keys)
        if flag:
            print(f"\n{'='*60}")
            print(f"FINAL FLAG: {flag}")
            print(f"{'='*60}")
    else:
        print("Not enough base_keys collected")
