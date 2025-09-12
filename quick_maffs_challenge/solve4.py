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

# Let's try a different approach. Since we have the hint (sum of plaintexts),
# and we know that e = random_prime(2^10), let's try to find e by brute force
# and see if we can use the hint to verify our solution

print("\nTrying to find e by brute force...")
print("e is a random prime < 1024, so let's try all primes in that range")

# Generate all primes < 1024
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

# For each prime e, try to see if we can find a pattern
# Since we have 3 ciphertexts and a hint, let's see if we can find e
# by checking if the decrypted values make sense

# Let's try a different approach - maybe N has a special structure
# Let's check if N is a product of many small primes or has some other pattern

print("\nChecking if N has any special mathematical properties...")

# Check if N is a factorial or has factorial-like properties
print("Checking if N is close to a factorial...")
for i in range(1, 20):
    fact = math.factorial(i)
    if abs(N - fact) < 1000:
        print(f"N is close to {i}! = {fact}")
        print(f"Difference: {N - fact}")

# Check if N is a power of a small number
print("\nChecking if N is a power of small numbers...")
for base in range(2, 20):
    for exp in range(2, 100):
        power = base ** exp
        if power == N:
            print(f"N = {base}^{exp}")
            break
        if power > N:
            break

# Check if N has any patterns in its digits
print("\nChecking for patterns in N...")
N_str = str(N)
print(f"First 50 digits: {N_str[:50]}")
print(f"Last 50 digits: {N_str[-50:]}")

# Check if N is a concatenation of smaller numbers
print("\nChecking if N is a concatenation...")
for i in range(1, len(N_str) // 2):
    part1 = int(N_str[:i])
    part2 = int(N_str[i:])
    if part1 * part2 == N:
        print(f"N = {part1} * {part2}")
        break

# Let's try to see if we can find any relationship between the ciphertexts
print("\nAnalyzing ciphertexts...")
for i, ct in enumerate(cts):
    print(f"ct[{i}] = {ct}")
    print(f"ct[{i}] bit length: {ct.bit_length()}")

# Check if any ciphertext is a multiple of another
for i in range(len(cts)):
    for j in range(i+1, len(cts)):
        if cts[i] % cts[j] == 0:
            print(f"ct[{i}] is a multiple of ct[{j}]: {cts[i] // cts[j]}")
        elif cts[j] % cts[i] == 0:
            print(f"ct[{j}] is a multiple of ct[{i}]: {cts[j] // cts[i]}")

# Check if the ciphertexts have any common factors
print("\nChecking for common factors...")
gcd_01 = math.gcd(cts[0], cts[1])
gcd_02 = math.gcd(cts[0], cts[2])
gcd_12 = math.gcd(cts[1], cts[2])
gcd_all = math.gcd(gcd_01, cts[2])

print(f"gcd(ct[0], ct[1]) = {gcd_01}")
print(f"gcd(ct[0], ct[2]) = {gcd_02}")
print(f"gcd(ct[1], ct[2]) = {gcd_12}")
print(f"gcd(all cts) = {gcd_all}")

# Check if any ciphertext is a perfect power
print("\nChecking if ciphertexts are perfect powers...")
for i, ct in enumerate(cts):
    for exp in range(2, 20):
        root = int(gmpy2.root(ct, exp))
        if root ** exp == ct:
            print(f"ct[{i}] = {root}^{exp}")
            break