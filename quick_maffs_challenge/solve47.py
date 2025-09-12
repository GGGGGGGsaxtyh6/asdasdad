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

# Check GCD relationships more carefully
print(f"GCD analysis:")
print(f"gcd(ct[0], ct[1]) = {gcd(cts[0], cts[1])}")
print(f"gcd(ct[0], ct[2]) = {gcd(cts[0], cts[2])}")
print(f"gcd(ct[1], ct[2]) = {gcd(cts[1], cts[2])}")
print(f"gcd(all cts) = {gcd(gcd(cts[0], cts[1]), cts[2])}")

# Check if the common factors give us any clues
common_factor = gcd(gcd(cts[0], cts[1]), cts[2])
print(f"Common factor: {common_factor}")

# Divide ciphertexts by common factor
cts_divided = [ct // common_factor for ct in cts]
print(f"Ciphertexts divided by common factor:")
for i, ct in enumerate(cts_divided):
    print(f"  ct[{i}] / {common_factor} = {ct}")

# Check GCD of divided ciphertexts
print(f"GCD of divided ciphertexts: {gcd(gcd(cts_divided[0], cts_divided[1]), cts_divided[2])}")

# Maybe the plaintexts are related to the common factor
# If pt1^e = ct1 and ct1 has a common factor, maybe pt1 also has a common factor

print(f"\nTrying to find plaintexts with common factor approach...")

# Try to find e by checking if any small number raised to e gives us the divided ciphertexts
# But this time, let's be more systematic

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

found_solution = False
for e in primes:
    if e > 100:  # Skip very large exponents for now
        break
    
    print(f"Trying e = {e}...")
    
    # Try to find plaintexts that sum to the hint
    # But this time, let's try to find plaintexts that might have a common factor
    
    # Try different common factors for the plaintexts
    for common_pt_factor in range(2, 100):
        if hint % common_pt_factor == 0:
            hint_divided = hint // common_pt_factor
            print(f"  Trying common plaintext factor {common_pt_factor}, hint_divided = {hint_divided}")
            
            # Try to find three numbers that sum to hint_divided
            for pt1_divided in range(1, 1000):
                if pow(pt1_divided * common_pt_factor, e, N) == cts[0]:
                    print(f"    Found pt1_divided = {pt1_divided} for ct[0]")
                    for pt2_divided in range(1, 1000):
                        if pow(pt2_divided * common_pt_factor, e, N) == cts[1]:
                            print(f"    Found pt2_divided = {pt2_divided} for ct[1]")
                            # Check if pt1_divided + pt2_divided + pt3_divided = hint_divided
                            remaining_divided = hint_divided - pt1_divided - pt2_divided
                            if remaining_divided > 0:
                                if pow(remaining_divided * common_pt_factor, e, N) == cts[2]:
                                    print(f"    Found pt3_divided = {remaining_divided} for ct[2]")
                                    print(f"    Sum: {pt1_divided + pt2_divided + remaining_divided}")
                                    print(f"    Hint_divided: {hint_divided}")
                                    if pt1_divided + pt2_divided + remaining_divided == hint_divided:
                                        print(f"    *** SOLUTION FOUND! ***")
                                        print(f"    e = {e}")
                                        print(f"    common_pt_factor = {common_pt_factor}")
                                        print(f"    pt1 = {pt1_divided * common_pt_factor}")
                                        print(f"    pt2 = {pt2_divided * common_pt_factor}")
                                        print(f"    pt3 = {remaining_divided * common_pt_factor}")
                                        
                                        # Convert to bytes
                                        try:
                                            pt1_bytes = long_to_bytes(pt1_divided * common_pt_factor)
                                            pt2_bytes = long_to_bytes(pt2_divided * common_pt_factor)
                                            pt3_bytes = long_to_bytes(remaining_divided * common_pt_factor)
                                            print(f"    pt1 as bytes: {pt1_bytes}")
                                            print(f"    pt2 as bytes: {pt2_bytes}")
                                            print(f"    pt3 as bytes: {pt3_bytes}")
                                            
                                            # Look for flag
                                            all_bytes = pt1_bytes + pt2_bytes + pt3_bytes
                                            if b'HTB{' in all_bytes:
                                                flag_start = all_bytes.find(b'HTB{')
                                                flag_end = all_bytes.find(b'}', flag_start) + 1
                                                flag = all_bytes[flag_start:flag_end]
                                                print(f"    *** FLAG: {flag} ***")
                                                
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
    print("No solution found with common factor approach")
    
    # Let me try one more approach - maybe the plaintexts are not small integers
    # but are constructed in a specific way
    
    print(f"\nTrying alternative approach - maybe plaintexts are constructed differently...")
    
    # Maybe the plaintexts are related to the hint in a specific way
    # Let's try to see if we can find a pattern
    
    # Check if hint can be decomposed in a meaningful way
    print(f"Hint decomposition attempts:")
    
    # Try to see if hint can be written as sum of powers
    for base in range(2, 20):
        for exp in range(2, 20):
            if base**exp <= hint:
                print(f"  {base}^{exp} = {base**exp}")
                if hint % (base**exp) == 0:
                    print(f"    Hint is divisible by {base}^{exp}")
    
    # Try to see if hint has any special structure
    hint_str = str(hint)
    print(f"Hint as string: {hint_str}")
    print(f"Hint length: {len(hint_str)}")
    
    # Check if hint can be split into meaningful parts
    for i in range(1, len(hint_str)):
        part1 = int(hint_str[:i])
        part2 = int(hint_str[i:])
        print(f"  Split at position {i}: {part1} + {part2} = {part1 + part2}")
        if part1 + part2 == hint:
            print(f"    *** HINT CAN BE SPLIT! ***")
            print(f"    Part 1: {part1}")
            print(f"    Part 2: {part2}")
            
            # Try to find e such that pow(part1, e, N) = ct[0] and pow(part2, e, N) = ct[1]
            for e in primes:
                if e > 100:
                    break
                if pow(part1, e, N) == cts[0] and pow(part2, e, N) == cts[1]:
                    print(f"    Found e = {e} for parts 1 and 2")
                    # Check if there's a third part
                    remaining = hint - part1 - part2
                    if remaining > 0:
                        if pow(remaining, e, N) == cts[2]:
                            print(f"    Found remaining = {remaining} for ct[2]")
                            print(f"    *** SOLUTION FOUND! ***")
                            print(f"    e = {e}")
                            print(f"    pt1 = {part1}")
                            print(f"    pt2 = {part2}")
                            print(f"    pt3 = {remaining}")
                            
                            # Convert to bytes
                            try:
                                pt1_bytes = long_to_bytes(part1)
                                pt2_bytes = long_to_bytes(part2)
                                pt3_bytes = long_to_bytes(remaining)
                                print(f"    pt1 as bytes: {pt1_bytes}")
                                print(f"    pt2 as bytes: {pt2_bytes}")
                                print(f"    pt3 as bytes: {pt3_bytes}")
                                
                                # Look for flag
                                all_bytes = pt1_bytes + pt2_bytes + pt3_bytes
                                if b'HTB{' in all_bytes:
                                    flag_start = all_bytes.find(b'HTB{')
                                    flag_end = all_bytes.find(b'}', flag_start) + 1
                                    flag = all_bytes[flag_start:flag_end]
                                    print(f"    *** FLAG: {flag} ***")
                                    
                            except:
                                pass
                            
                            found_solution = True
                            break
            if found_solution:
                break