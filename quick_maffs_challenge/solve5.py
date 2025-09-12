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

# The ciphertexts have common factors:
# gcd(ct[0], ct[1]) = 8
# gcd(ct[0], ct[2]) = 2  
# gcd(ct[1], ct[2]) = 2
# gcd(all cts) = 2

print("\nAnalyzing common factors...")
print(f"ct[0] = {cts[0]}")
print(f"ct[1] = {cts[1]}")
print(f"ct[2] = {cts[2]}")

# Let's divide by the common factors
ct0_div2 = cts[0] // 2
ct1_div2 = cts[1] // 2
ct2_div2 = cts[2] // 2

print(f"\nAfter dividing by 2:")
print(f"ct[0]/2 = {ct0_div2}")
print(f"ct[1]/2 = {ct1_div2}")
print(f"ct[2]/2 = {ct2_div2}")

# Check gcd of the divided values
gcd_01_div2 = math.gcd(ct0_div2, ct1_div2)
gcd_02_div2 = math.gcd(ct0_div2, ct2_div2)
gcd_12_div2 = math.gcd(ct1_div2, ct2_div2)
gcd_all_div2 = math.gcd(gcd_01_div2, ct2_div2)

print(f"\nAfter dividing by 2:")
print(f"gcd(ct[0]/2, ct[1]/2) = {gcd_01_div2}")
print(f"gcd(ct[0]/2, ct[2]/2) = {gcd_02_div2}")
print(f"gcd(ct[1]/2, ct[2]/2) = {gcd_12_div2}")
print(f"gcd(all cts/2) = {gcd_all_div2}")

# Let's try to find the exponent e by checking if the divided values are perfect powers
print(f"\nChecking if divided values are perfect powers...")
for i, ct in enumerate([ct0_div2, ct1_div2, ct2_div2]):
    print(f"\nct[{i}]/2 = {ct}")
    for exp in range(2, 20):
        root = int(gmpy2.root(ct, exp))
        if root ** exp == ct:
            print(f"  ct[{i}]/2 = {root}^{exp}")
            break
    else:
        print(f"  ct[{i}]/2 is not a perfect power")

# Let's try a different approach - maybe the plaintexts are small and we can find them
# by checking if any small number raised to some power gives us the ciphertexts
print(f"\nTrying to find small plaintexts...")

# Since e = random_prime(2^10), let's try all primes < 1024
def sieve(n):
    """Generate all primes less than n"""
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
print(f"\nTrying to find e by checking small numbers...")
for e in primes:
    if e > 100:  # Skip very large exponents for now
        break
    
    print(f"Trying e = {e}...")
    
    # Try small plaintexts
    found_solution = False
    for pt1 in range(1, 1000):
        if pow(pt1, e, N) == cts[0]:
            print(f"  Found pt1 = {pt1} for ct[0]")
            for pt2 in range(1, 1000):
                if pow(pt2, e, N) == cts[1]:
                    print(f"  Found pt2 = {pt2} for ct[1]")
                    for pt3 in range(1, 1000):
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