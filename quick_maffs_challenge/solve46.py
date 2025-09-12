#!/usr/bin/env python3

from Crypto.Util.number import *
import gmpy2
from gmpy2 import mpz, gcd, isqrt
import math

# Extract parameters from output.txt
N = 5981664384988507891478572449251897296717727847212579781448791472718547112403550208352320926002397616312181279859738938646168022481824206589739320298482728968548378237391009138243024910596491172979923991673446034011260330224409794208875199561844435663744993504673450898288161482849187018770655419007178851937895764901674192425054643548670616348302447202491340266057221307744866082461604674766259695903766772980842036324667567850124019171425634526227426965833985082234968255176231124754301435374519312001547854794352023852342682220352109083558778402466358598254431167382653831478713628185748237886560605604945010671417

cts = [4064195644006411160585797813860027634920635349984344191047587061586620848352019080467087592184982883284356841385019453458842500930190512793665886381102812026066865666098391973664302897278510995945377153937248437062600080527317980210967973971371047319247120004523147629534186514628527555180736833194525516718549330721987873868571634294877416190209288629499265010822332662061001208360467692613959936438519512705706688327846470352610192922218603268096313278741647626899523312431823527174576009143724850631439559205050395629961996905961682800070679793831568617438035643749072976096500278297683944583609092132808342160168, 3972397619896893471633226994966440180689669532336298201562465946694941720775869427764056001983618377003841446300122954561092878433908258359050016399257266833626893700179430172867058140215023211349613449750819959868861260714924524414967854467488908710563470522800186889553825417008118394349306170727982570843758792622898850338954039322560740348595654863475541846505121081201633770673996898756298398831948133434844321091554344145679504115839940880338238034227536355386474785852916335583794757849746186832609785626770517073108801492522816245458992502698143396049695921044554959802743742110180934416272358039695942552488, 956566266150449406104687131427865505474798294715598448065695308619216559681163085440476088324404921175885831054464222377255942505087330963629877648302727892001779224319839877897857215091085980519442914974498275528112936281916338633178398286676523416008365096599844169979821513770606168325175652094633129536643417367820830724397070621662683223203491074814734747601002376621653739871373924630026694962642922871008486127796621355314581093953946913681152270251669050414866366693593651789709229310574005739535880988490183275291507128529820194381392682870291338920077175831052974790596134745552552808640002791037755434586]

hint = 2674558878275613295915981392537201653631411909654166620884912623530781

print(f"N = {N}")
print(f"Number of ciphertexts: {len(cts)}")
print(f"Hint (sum of plaintexts): {hint}")
print(f"N bit length: {N.bit_length()}")

# Let me try a completely different approach
# Maybe I need to focus on the mathematical properties more carefully

print(f"\nAnalyzing the mathematical properties...")

# Check if N has any special properties
print(f"N analysis:")
print(f"N mod 2 = {N % 2}")
print(f"N mod 3 = {N % 3}")
print(f"N mod 5 = {N % 5}")
print(f"N mod 7 = {N % 7}")

# Check if N is a perfect power
print(f"\nChecking if N is a perfect power:")
for k in range(2, 11):
    root = isqrt(N)
    if root ** k == N:
        print(f"N is a perfect {k}th power: {root}^{k}")
        break
    # Check if N is close to a perfect power
    for base in range(2, 1000):
        power = base ** k
        if abs(power - N) < 1000:
            print(f"N is close to {base}^{k} = {power}, difference = {abs(power - N)}")
            if abs(power - N) < 100:
                print(f"  *** POTENTIAL MATCH! ***")

# Check if N has any small factors
print(f"\nChecking for small factors:")
for p in range(2, 10000):
    if N % p == 0:
        print(f"Found factor: {p}")
        q = N // p
        print(f"q = {q}")
        print(f"p * q = {p * q}")
        print(f"p * q == N: {p * q == N}")
        break

# Check if N can be written as a product of small primes
print(f"\nChecking if N is a product of small primes:")
small_primes = [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]
for p in small_primes:
    if N % p == 0:
        print(f"Found small prime factor: {p}")
        q = N // p
        print(f"q = {q}")
        print(f"p * q = {p * q}")
        print(f"p * q == N: {p * q == N}")
        break

# Check if N can be written as a sum of powers
print(f"\nChecking if N can be written as a sum of powers:")
for base in range(2, 100):
    for exp in range(2, 20):
        power = base ** exp
        if power <= N:
            if N % power == 0:
                quotient = N // power
                print(f"N = {base}^{exp} * {quotient}")
                # Check if quotient is also a power
                for base2 in range(2, 100):
                    for exp2 in range(2, 20):
                        power2 = base2 ** exp2
                        if power2 <= quotient:
                            if quotient % power2 == 0:
                                quotient2 = quotient // power2
                                print(f"  {base2}^{exp2} * {quotient2}")
                                if quotient2 == 1:
                                    print(f"  *** N = {base}^{exp} * {base2}^{exp2} ***")

# Check if N has any patterns in its digits
print(f"\nChecking for patterns in N:")
N_str = str(N)
print(f"N as string: {N_str[:50]}...")
print(f"N length: {len(N_str)}")

# Check if N can be written as a concatenation of smaller numbers
print(f"\nChecking if N can be written as concatenation:")
for i in range(1, min(20, len(N_str))):
    part1 = int(N_str[:i])
    part2 = int(N_str[i:])
    if part1 * part2 == N:
        print(f"N = {part1} * {part2}")
        print(f"*** N IS A CONCATENATION! ***")
        break

# Check if N has any special mathematical properties
print(f"\nChecking for special mathematical properties:")
print(f"N is even: {N % 2 == 0}")
print(f"N is odd: {N % 2 == 1}")
print(f"N is divisible by 3: {N % 3 == 0}")
print(f"N is divisible by 5: {N % 5 == 0}")
print(f"N is divisible by 7: {N % 7 == 0}")

# Check if N can be written as a difference of squares
print(f"\nChecking if N can be written as a difference of squares:")
# Use gmpy2 for large number operations
N_mpz = mpz(N)
sqrt_N = isqrt(N_mpz)
print(f"sqrt(N) = {sqrt_N}")

for a in range(sqrt_N + 1, sqrt_N + 10000):  # Check a reasonable range
    a_mpz = mpz(a)
    b_squared = a_mpz * a_mpz - N_mpz
    if b_squared > 0:
        b = isqrt(b_squared)
        if b * b == b_squared:
            print(f"N = {a}^2 - {b}^2 = ({a} + {b})({a} - {b})")
            print(f"*** N IS A DIFFERENCE OF SQUARES! ***")
            p = a + b
            q = a - b
            print(f"p = {p}")
            print(f"q = {q}")
            print(f"p * q = {p * q}")
            print(f"p * q == N: {p * q == N}")
            break