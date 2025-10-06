import socket,json,random,sys,time,os
from hamiltonicity import *

N=5
G_no=[[0,0,1,0,0],[1,0,0,0,0],[0,1,0,0,0],[0,0,0,0,1],[0,0,0,1,0]]
G_yes=[[0,1,1,0,1],[1,0,0,0,0],[0,0,0,1,0],[0,1,1,0,0],[1,0,1,1,0]]
cycle=[(0,4),(4,2),(2,3),(3,1),(1,0)]
numrounds=128

# Precalcular algunos commits
print("Precalculating pool...",file=sys.stderr)
pool_no=[]
pool_yes=[]
for _ in range(200):
    A,op=commit_to_graph(G_no,N)
    pool_no.append((A,op))
    A,op=commit_to_graph(G_yes,N)
    pool_yes.append((A,op))

print(f"Pool ready: {len(pool_no)} no, {len(pool_yes)} yes",file=sys.stderr)

def gen_from_pool(use_yes):
    pool=pool_yes if use_yes else pool_no
    A,op=random.choice(pool)
    p=list(range(N))
    if use_yes:
        pc=[[p.index(x[0]),p.index(x[1])]for x in cycle]
        z=[pc,get_r_vals(op,N,cycle)]
    else:
        z=[p,op]
    return A,z

att=0
t0=time.time()
best_match=0

while True:
    att+=1
    picks=[random.randint(0,1)for _ in range(numrounds)]
    ps=[(gen_from_pool(p),p)for p in picks]
    
    FS=b""
    for(A,z),_ in ps:
        FS=hash_committed_graph(A,FS,comm_params)
    
    cb=bin(int.from_bytes(FS,'big'))[-numrounds:]
    m=sum(int(cb[i])==picks[i]for i in range(numrounds))
    
    if m>best_match:
        best_match=m
        elapsed=time.time()-t0
        rate=att/elapsed
        print(f"Att {att}: NEW BEST {m}/128, rate={rate:.2f}/s",file=sys.stderr)
    
    if att%20==0:
        elapsed=time.time()-t0
        rate=att/elapsed
        print(f"Att {att}: best={best_match}/128, rate={rate:.2f}/s",file=sys.stderr)
    
    if m==128:
        print("¡¡¡MATCH!!!",file=sys.stderr)
        sock=socket.socket()
        sock.settimeout(20)
        sock.connect(("archive.cryptohack.org",34597))
        sock.recv(4096)
        for(A,z),_ in ps:
            sock.sendall((json.dumps({"A":A,"z":z})+"\n").encode())
            sock.recv(4096)
        final=b""
        while b"crypto{" not in final:
            final+=sock.recv(4096)
        print(final.decode())
        sys.exit(0)
