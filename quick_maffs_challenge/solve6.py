#!/usr/bin/env python3

from Crypto.Util.number import *
import gmpy2
from gmpy2 import mpz
import math

# Extract parameters from output.txt
N = 5981664384988507891478572449251897296717727847212579781448791472718547112403550208352320926002397616312181279859738938646168022481824206589739320298482728968548378237391009138243024910596491172979923991673446034011260330224409794208875199561844435663744993504673450898288161482849187018770655419007178851937895764901674192425054643548670616348302447202491340266057221307744866082461604674766259695903766772980842036324667567850124019171425634526227426965833985082234968255176231124754301435374519312001547854794352023852342682220352109083558778402466358598254431167382653831478713628185748237886560605604945010671417

cts = [4064195644006411160585797813860027634920635349984344191047587061586620848352019080467087592184982883284356841385019453458842500930190512793665886381102812026066865666098391973664302897278510995945377153937248437062600080527317980210967973971371047319247120004523147629534186514628527555180736833194525516718549330721987873868571634294877416190209288629499265010822332662061001208360467692613959936438519512705706688327846470352610192922218603268096313278741647626899523312431823527174576009143724850631439559205050395629961996905961682800070679793831568617438035643749072976096500278297683944583609092132808342160168, 3972397619896893471633226994966440180689669532336298201562465946694941720775869427764056001983618377003841446300122954561092878433908258359050016399257266833626893700179430172867058140215023211349613449750819959868861260714924524414967854467488908710563470522800186889553825417008118394349306170727982570843758792622898850338954039322560740348595654863475541846505121081201633770673996898756298398831948133434844321091554344145679504115839940880338238034227536355386474785852916335583794757849746186832609785626770517073108801492522816245458992502698143396049695921044554959802743742110180934416272358039695942552488, 956566266150449406104687131427865505474798294715598448065695308619216559681163085440476088324404921175885831054464222377255942505087330963629877648302727892001779224319839877897857215091085980519442914974498275528112936281916338633178398286676523416008365096599844169979821513770606168325175652094633129536643417367820830724397070621662683223203491074814734747601002376621653739871373924630026694962642922871008486127796621355314581093953946913681152270251669050414866366693593651789709229310574005739535880988490183275291507128529820194381392682870291338920077175831052974790596134745552552808640002791037755434586]

hint = 2674558878275613295915981392537201653631411909654166620884912623530781

print(f"N = {N}")
print(f"Number of ciphertexts: {len(cts)}")
print(f"Hint (sum of plaintexts): {hint}")
print(f"N bit length: {N.bit_length()}")

# Let's try a different approach. Since this is a CTF challenge, maybe N has a special structure
# Let's check if N is a product of many small primes or has some other vulnerability

print("\nChecking if N has a special structure...")

# Check if N is a product of small primes
print("Checking if N is a product of small primes...")
small_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997]

factors = []
temp_N = N
for p in small_primes:
    while temp_N % p == 0:
        factors.append(p)
        temp_N //= p
        print(f"Found factor: {p}")

if temp_N == 1:
    print(f"N is a product of small primes! All factors: {factors}")
    print(f"Number of factors: {len(factors)}")
    print(f"Product of factors: {math.prod(factors)}")
    
    # If N is a product of small primes, we can find the plaintexts easily
    # Let's try to find e by checking if any small number raised to e gives us the ciphertexts
    print(f"\nTrying to find e and plaintexts...")
    
    # Generate all primes < 1024
    def sieve(n):
        is_prime = [True] * n
        is_prime[0] = is_prime[1] = False
        for i in range(2, int(n**0.5) + 1):
            if is_prime[i]:
                for j in range(i*i, n, i):
                    is_prime[j] = False
        return [i for i in range(n) if is_prime[i]]
    
    primes = sieve(1024)
    print(f"Found {len(primes)} primes < 1024")
    
    # Try to find e by checking if any small number raised to e gives us the ciphertexts
    for e in primes:
        if e > 100:  # Skip very large exponents for now
            break
        
        print(f"Trying e = {e}...")
        
        # Try small plaintexts
        found_solution = False
        for pt1 in range(1, 10000):  # Increased range
            if pow(pt1, e, N) == cts[0]:
                print(f"  Found pt1 = {pt1} for ct[0]")
                for pt2 in range(1, 10000):
                    if pow(pt2, e, N) == cts[1]:
                        print(f"  Found pt2 = {pt2} for ct[1]")
                        for pt3 in range(1, 10000):
                            if pow(pt3, e, N) == cts[2]:
                                print(f"  Found pt3 = {pt3} for ct[2]")
                                print(f"  Sum: {pt1 + pt2 + pt3}")
                                print(f"  Hint: {hint}")
                                if pt1 + pt2 + pt3 == hint:
                                    print(f"  *** SOLUTION FOUND! ***")
                                    print(f"  e = {e}")
                                    print(f"  pt1 = {pt1}")
                                    print(f"  pt2 = {pt2}")
                                    print(f"  pt3 = {pt3}")
                                    
                                    # Convert to bytes and try to find the flag
                                    try:
                                        pt1_bytes = long_to_bytes(pt1)
                                        pt2_bytes = long_to_bytes(pt2)
                                        pt3_bytes = long_to_bytes(pt3)
                                        print(f"  pt1 as bytes: {pt1_bytes}")
                                        print(f"  pt2 as bytes: {pt2_bytes}")
                                        print(f"  pt3 as bytes: {pt3_bytes}")
                                        
                                        # Try to find flag pattern
                                        all_bytes = pt1_bytes + pt2_bytes + pt3_bytes
                                        print(f"  All bytes: {all_bytes}")
                                        
                                        if b'HTB{' in all_bytes:
                                            print(f"  *** FLAG FOUND! ***")
                                            flag_start = all_bytes.find(b'HTB{')
                                            flag_end = all_bytes.find(b'}', flag_start) + 1
                                            flag = all_bytes[flag_start:flag_end]
                                            print(f"  FLAG: {flag}")
                                        
                                    except:
                                        pass
                                    
                                    found_solution = True
                                    break
                        if found_solution:
                            break
                if found_solution:
                    break
        
        if found_solution:
            break
    
    if not found_solution:
        print("No solution found with small plaintexts")
        
else:
    print(f"Remaining factor after small primes: {temp_N}")
    print(f"Remaining factor bit length: {temp_N.bit_length()}")
    print("N is not a product of small primes")