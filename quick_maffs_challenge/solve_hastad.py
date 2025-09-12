#!/usr/bin/env python3

from Crypto.Util.number import *
import gmpy2
from gmpy2 import mpz, gcd, isqrt, iroot
import math

# Extract parameters from output.txt
N = 5981664384988507891478572449251897296717727847212579781448791472718547112403550208352320926002397616312181279859738938646168022481824206589739320298482728968548378237391009138243024910596491172979923991673446034011260330224409794208875199561844435663744993504673450898288161482849187018770655419007178851937895764901674192425054643548670616348302447202491340266057221307744866082461604674766259695903766772980842036324667567850124019171425634526227426965833985082234968255176231124754301435374519312001547854794352023852342682220352109083558778402466358598254431167382653831478713628185748237886560605604945010671417

cts = [4064195644006411160585797813860027634920635349984344191047587061586620848352019080467087592184982883284356841385019453458842500930190512793665886381102812026066865666098391973664302897278510995945377153937248437062600080527317980210967973971371047319247120004523147629534186514628527555180736833194525516718549330721987873868571634294877416190209288629499265010822332662061001208360467692613959936438519512705706688327846470352610192922218603268096313278741647626899523312431823527174576009143724850631439559205050395629961996905961682800070679793831568617438035643749072976096500278297683944583609092132808342160168, 3972397619896893471633226994966440180689669532336298201562465946694941720775869427764056001983618377003841446300122954561092878433908258359050016399257266833626893700179430172867058140215023211349613449750819959868861260714924524414967854467488908710563470522800186889553825417008118394349306170727982570843758792622898850338954039322560740348595654863475541846505121081201633770673996898756298398831948133434844321091554344145679504115839940880338238034227536355386474785852916335583794757849746186832609785626770517073108801492522816245458992502698143396049695921044554959802743742110180934416272358039695942552488, 956566266150449406104687131427865505474798294715598448065695308619216559681163085440476088324404921175885831054464222377255942505087330963629877648302727892001779224319839877897857215091085980519442914974498275528112936281916338633178398286676523416008365096599844169979821513770606168325175652094633129536643417367820830724397070621662683223203491074814734747601002376621653739871373924630026694962642922871008486127796621355314581093953946913681152270251669050414866366693593651789709229310574005739535880988490183275291507128529820194381392682870291338920077175831052974790596134745552552808640002791037755434586]

hint = 2674558878275613295915981392537201653631411909654166620884912623530781

print(f"N = {N}")
print(f"Number of ciphertexts: {len(cts)}")
print(f"Hint (sum of plaintexts): {hint}")
print(f"N bit length: {N.bit_length()}")

# Let me try Håstad's attack or a variant
# The idea is that if we have the same small exponent e used for multiple plaintexts,
# we might be able to recover the plaintexts

print(f"\nTrying Håstad's attack approach...")

# First, let's try to find the exponent e by checking if small e values work
def chinese_remainder_theorem(remainders, moduli):
    """Solve system of congruences using CRT"""
    total = 0
    prod = 1
    for m in moduli:
        prod *= m
    
    for r, m in zip(remainders, moduli):
        p = prod // m
        total += r * mul_inv(p, m) * p
    
    return total % prod

def mul_inv(a, b):
    """Multiplicative inverse of a mod b"""
    return pow(a, -1, b)

# Try small exponent attack
for e in range(2, 100):
    print(f"Trying e = {e}...")
    
    # If e is small and we have 3 ciphertexts, we might be able to use CRT
    # to combine them and then take the e-th root
    
    # For Håstad's attack, we need different moduli, but we only have one N
    # So let's try a different approach - maybe the plaintexts are small enough
    # that pt^e < N, so we can take direct e-th roots
    
    for i, ct in enumerate(cts):
        # Try to take e-th root directly
        try:
            root_test = iroot(ct, e)
            if root_test[1]:  # If it's a perfect e-th root
                candidate = root_test[0]
                if pow(candidate, e, N) == ct:
                    print(f"  Found candidate for ct[{i}]: {candidate}")
                    
                    # Check if this could be part of our solution
                    # We need to find the other plaintexts such that their sum equals hint
                    if i == 0:
                        pt1 = candidate
                        # Try to find pt2 and pt3
                        for j, ct2 in enumerate(cts[1:], 1):
                            root_test2 = iroot(ct2, e)
                            if root_test2[1]:
                                candidate2 = root_test2[0]
                                if pow(candidate2, e, N) == ct2:
                                    pt2 = candidate2
                                    remaining = hint - pt1 - pt2
                                    if remaining > 0:
                                        # Check if remaining is the third plaintext
                                        remaining_ct_idx = 3 - i - j  # The remaining index
                                        if pow(remaining, e, N) == cts[remaining_ct_idx]:
                                            print(f"  *** SOLUTION FOUND! ***")
                                            print(f"  e = {e}")
                                            print(f"  pt1 = {pt1}")
                                            print(f"  pt2 = {pt2}")
                                            print(f"  pt3 = {remaining}")
                                            print(f"  Sum: {pt1 + pt2 + remaining}")
                                            print(f"  Hint: {hint}")
                                            
                                            if pt1 + pt2 + remaining == hint:
                                                # Convert to bytes and look for flag
                                                try:
                                                    pt1_bytes = long_to_bytes(pt1)
                                                    pt2_bytes = long_to_bytes(pt2)
                                                    pt3_bytes = long_to_bytes(remaining)
                                                    print(f"  pt1 as bytes: {pt1_bytes}")
                                                    print(f"  pt2 as bytes: {pt2_bytes}")
                                                    print(f"  pt3 as bytes: {pt3_bytes}")
                                                    
                                                    # Look for flag pattern
                                                    all_bytes = pt1_bytes + pt2_bytes + pt3_bytes
                                                    print(f"  All bytes: {all_bytes}")
                                                    
                                                    if b'HTB{' in all_bytes:
                                                        print(f"  *** FLAG FOUND! ***")
                                                        flag_start = all_bytes.find(b'HTB{')
                                                        flag_end = all_bytes.find(b'}', flag_start) + 1
                                                        flag = all_bytes[flag_start:flag_end]
                                                        print(f"  FLAG: {flag}")
                                                        exit()
                                                    else:
                                                        # Maybe flag is in one of the individual parts
                                                        for k, pt_bytes in enumerate([pt1_bytes, pt2_bytes, pt3_bytes]):
                                                            if b'HTB{' in pt_bytes:
                                                                flag_start = pt_bytes.find(b'HTB{')
                                                                flag_end = pt_bytes.find(b'}', flag_start) + 1
                                                                flag = pt_bytes[flag_start:flag_end]
                                                                print(f"  *** FLAG FOUND in pt{k+1}! ***")
                                                                print(f"  FLAG: {flag}")
                                                                exit()
                                                                
                                                except Exception as ex:
                                                    print(f"  Error converting to bytes: {ex}")
                                                
                                                exit()
                                            else:
                                                print(f"  Sum doesn't match hint")
        except Exception as ex:
            continue

print("No solution found with Håstad's attack approach")

# Let me try one more approach - maybe the vulnerability is in the modulus N itself
print(f"\nTrying to factor N using different methods...")

# Try Fermat's factorization method
def fermat_factor(n):
    """Try to factor n using Fermat's method"""
    a = isqrt(n)
    if a * a == n:
        return a, a
    
    a += 1
    for i in range(1000000):  # Try up to 1M iterations
        b2 = a * a - n
        b = isqrt(b2)
        if b * b == b2:
            return a + b, a - b
        a += 1
    return None, None

print("Trying Fermat factorization...")
p, q = fermat_factor(N)
if p and q and p != 1 and q != 1 and p * q == N:
    print(f"*** FACTORED N! ***")
    print(f"p = {p}")
    print(f"q = {q}")
    print(f"p * q = {p * q}")
    print(f"p * q == N: {p * q == N}")
    
    # Now we can decrypt the ciphertexts
    phi_n = (p - 1) * (q - 1)
    
    # Try different small values of e
    for e in range(2, 1000):
        try:
            if gcd(e, phi_n) == 1:
                d = pow(e, -1, phi_n)
                
                # Decrypt all ciphertexts
                pts = [pow(ct, d, N) for ct in cts]
                
                if sum(pts) == hint:
                    print(f"*** DECRYPTION SUCCESSFUL! ***")
                    print(f"e = {e}")
                    print(f"d = {d}")
                    print(f"Plaintexts: {pts}")
                    print(f"Sum: {sum(pts)}")
                    print(f"Hint: {hint}")
                    
                    # Convert to bytes and look for flag
                    try:
                        pt_bytes_list = [long_to_bytes(pt) for pt in pts]
                        for i, pt_bytes in enumerate(pt_bytes_list):
                            print(f"pt{i+1} as bytes: {pt_bytes}")
                        
                        # Look for flag pattern
                        all_bytes = b''.join(pt_bytes_list)
                        print(f"All bytes: {all_bytes}")
                        
                        if b'HTB{' in all_bytes:
                            print(f"*** FLAG FOUND! ***")
                            flag_start = all_bytes.find(b'HTB{')
                            flag_end = all_bytes.find(b'}', flag_start) + 1
                            flag = all_bytes[flag_start:flag_end]
                            print(f"FLAG: {flag}")
                        else:
                            # Maybe flag is in one of the individual parts
                            for i, pt_bytes in enumerate(pt_bytes_list):
                                if b'HTB{' in pt_bytes:
                                    flag_start = pt_bytes.find(b'HTB{')
                                    flag_end = pt_bytes.find(b'}', flag_start) + 1
                                    flag = pt_bytes[flag_start:flag_end]
                                    print(f"*** FLAG FOUND in pt{i+1}! ***")
                                    print(f"FLAG: {flag}")
                                    break
                                    
                    except Exception as ex:
                        print(f"Error converting to bytes: {ex}")
                    
                    exit()
        except:
            continue
else:
    print("Fermat factorization failed")

print("No solution found with any approach")