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
# Maybe the issue is that I need to think about this as a mathematical problem

print(f"\nAnalyzing the mathematical properties more carefully...")

# Check GCD relationships
print(f"GCD analysis:")
print(f"gcd(ct[0], ct[1]) = {gcd(cts[0], cts[1])}")
print(f"gcd(ct[0], ct[2]) = {gcd(cts[0], cts[2])}")
print(f"gcd(ct[1], ct[2]) = {gcd(cts[1], cts[2])}")
print(f"gcd(all cts) = {gcd(gcd(cts[0], cts[1]), cts[2])}")

# Check if the common factors give us any clues
common_factor = gcd(gcd(cts[0], cts[1]), cts[2])
print(f"Common factor: {common_factor}")

# Maybe the vulnerability is that e is very small and we can take e-th roots directly
# Let me try to find e by checking if any small number raised to e gives us the ciphertexts

print(f"\nTrying to find e by direct e-th root approach...")

# Try very small e values and see if we can take e-th roots
for e in range(2, 100):  # Try all small e values
    print(f"Trying e = {e}...")
    
    # Try to take e-th root of each ciphertext
    try:
        # Use gmpy2 for arbitrary precision
        ct1_mpz = mpz(cts[0])
        ct2_mpz = mpz(cts[1])
        ct3_mpz = mpz(cts[2])
        
        # Try to find e-th root
        root1 = ct1_mpz ** (1/e)
        root2 = ct2_mpz ** (1/e)
        root3 = ct3_mpz ** (1/e)
        
        # Check if the roots are integers
        if root1 == int(root1) and root2 == int(root2) and root3 == int(root3):
            pt1 = int(root1)
            pt2 = int(root2)
            pt3 = int(root3)
            
            print(f"  Found potential plaintexts: {pt1}, {pt2}, {pt3}")
            print(f"  Sum: {pt1 + pt2 + pt3}")
            print(f"  Hint: {hint}")
            
            if pt1 + pt2 + pt3 == hint:
                print(f"  *** SOLUTION FOUND! ***")
                print(f"  e = {e}")
                print(f"  pt1 = {pt1}")
                print(f"  pt2 = {pt2}")
                print(f"  pt3 = {pt3}")
                
                # Convert to bytes and look for flag
                try:
                    pt1_bytes = long_to_bytes(pt1)
                    pt2_bytes = long_to_bytes(pt2)
                    pt3_bytes = long_to_bytes(pt3)
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
                    else:
                        # Maybe flag is in one of the individual parts
                        for i, pt_bytes in enumerate([pt1_bytes, pt2_bytes, pt3_bytes]):
                            if b'HTB{' in pt_bytes:
                                flag_start = pt_bytes.find(b'HTB{')
                                flag_end = pt_bytes.find(b'}', flag_start) + 1
                                flag = pt_bytes[flag_start:flag_end]
                                print(f"  *** FLAG FOUND in pt{i+1}! ***")
                                print(f"  FLAG: {flag}")
                                break
                                
                except Exception as e:
                    print(f"  Error converting to bytes: {e}")
                
                break
            else:
                print(f"  Sum doesn't match hint, trying next e...")
        else:
            print(f"  Roots are not integers, trying next e...")
            
    except Exception as e:
        print(f"  Error with e = {e}: {e}")
        continue

print("\nTrying alternative approach - maybe the plaintexts are constructed from the hint...")

# Maybe the plaintexts are constructed from the hint in a specific way
# Let me try to see if the hint can be decomposed into meaningful parts

hint_str = str(hint)
print(f"Hint as string: {hint_str}")
print(f"Hint length: {len(hint_str)}")

# Try to split the hint into three parts and see if they work as plaintexts
for split1 in range(1, len(hint_str)):
    for split2 in range(split1 + 1, len(hint_str)):
        part1 = int(hint_str[:split1])
        part2 = int(hint_str[split1:split2])
        part3 = int(hint_str[split2:])
        
        print(f"  Trying split at {split1}, {split2}: {part1} + {part2} + {part3} = {part1 + part2 + part3}")
        
        if part1 + part2 + part3 == hint:
            print(f"    *** HINT CAN BE SPLIT! ***")
            print(f"    Part 1: {part1}")
            print(f"    Part 2: {part2}")
            print(f"    Part 3: {part3}")
            
            # Try to find e such that these parts work as plaintexts
            for e in range(2, 100):
                if pow(part1, e, N) == cts[0] and pow(part2, e, N) == cts[1] and pow(part3, e, N) == cts[2]:
                    print(f"    *** SOLUTION FOUND! ***")
                    print(f"    e = {e}")
                    print(f"    pt1 = {part1}")
                    print(f"    pt2 = {part2}")
                    print(f"    pt3 = {part3}")
                    
                    # Convert to bytes and look for flag
                    try:
                        pt1_bytes = long_to_bytes(part1)
                        pt2_bytes = long_to_bytes(part2)
                        pt3_bytes = long_to_bytes(part3)
                        print(f"    pt1 as bytes: {pt1_bytes}")
                        print(f"    pt2 as bytes: {pt2_bytes}")
                        print(f"    pt3 as bytes: {pt3_bytes}")
                        
                        # Look for flag pattern
                        all_bytes = pt1_bytes + pt2_bytes + pt3_bytes
                        print(f"    All bytes: {all_bytes}")
                        
                        if b'HTB{' in all_bytes:
                            print(f"    *** FLAG FOUND! ***")
                            flag_start = all_bytes.find(b'HTB{')
                            flag_end = all_bytes.find(b'}', flag_start) + 1
                            flag = all_bytes[flag_start:flag_end]
                            print(f"    FLAG: {flag}")
                        else:
                            # Maybe flag is in one of the individual parts
                            for i, pt_bytes in enumerate([pt1_bytes, pt2_bytes, pt3_bytes]):
                                if b'HTB{' in pt_bytes:
                                    flag_start = pt_bytes.find(b'HTB{')
                                    flag_end = pt_bytes.find(b'}', flag_start) + 1
                                    flag = pt_bytes[flag_start:flag_end]
                                    print(f"    *** FLAG FOUND in pt{i+1}! ***")
                                    print(f"    FLAG: {flag}")
                                    break
                                    
                    except Exception as e:
                        print(f"    Error converting to bytes: {e}")
                    
                    exit()  # Found solution, exit

print("No solution found with any approach")