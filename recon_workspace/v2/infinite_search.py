import socket,json,random,sys,time
from hamiltonicity import *

N=5
G_no=[[0,0,1,0,0],[1,0,0,0,0],[0,1,0,0,0],[0,0,0,0,1],[0,0,0,1,0]]
G_yes=[[0,1,1,0,1],[1,0,0,0,0],[0,0,0,1,0],[0,1,1,0,0],[1,0,1,1,0]]
cycle=[(0,4),(4,2),(2,3),(3,1),(1,0)]
numrounds=128

print("Precalculating pool...",file=sys.stderr)
pool_no=[commit_to_graph(G_no,N)for _ in range(300)]
pool_yes=[commit_to_graph(G_yes,N)for _ in range(300)]
print(f"Pool ready",file=sys.stderr)

def gen_fast(use_yes):
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
best=0

while True:
    att+=1
    picks=[random.randint(0,1)for _ in range(numrounds)]
    ps=[(gen_fast(p),p)for p in picks]
    
    FS=b""
    for(A,z),_ in ps:
        FS=hash_committed_graph(A,FS,comm_params)
    
    cb=bin(int.from_bytes(FS,'big'))[-numrounds:]
    m=sum(int(cb[i])==picks[i]for i in range(numrounds))
    
    if m>best:
        best=m
        elapsed=time.time()-t0
        rate=att/elapsed
        print(f"[{time.strftime('%H:%M:%S')}] Att {att}: NEW BEST {m}/128, rate={rate:.1f}/s",file=sys.stderr)
    
    if att%1000==0 and att>0:
        elapsed=time.time()-t0
        rate=att/elapsed
        eta_years=(2**128)/rate/31536000
        print(f"[{time.strftime('%H:%M:%S')}] Att {att}: best={best}/128, rate={rate:.1f}/s, ETA={eta_years:.1e}y",file=sys.stderr)
    
    if m==128:
        print(f"[{time.strftime('%H:%M:%S')}] ¡¡¡MATCH PERFECTO!!!",file=sys.stderr)
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
