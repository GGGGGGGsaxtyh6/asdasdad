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

# Let me try a completely different approach
# Maybe the plaintexts are constructed from the hint in a specific way
# Or maybe there's a mathematical relationship I'm missing

print(f"\nTrying mathematical relationship approach...")

# Check if hint can be decomposed into meaningful parts
# Maybe the plaintexts are powers of some base, or have a specific structure

# Let's try to see if hint can be written as sum of powers
print(f"Checking if hint can be written as sum of powers:")

# Try different bases and exponents
for base in range(2, 20):
    for exp in range(2, 20):
        power = base ** exp
        if power <= hint:
            if hint % power == 0:
                quotient = hint // power
                print(f"  {base}^{exp} = {power}, hint = {power} * {quotient}")
                
                # Check if quotient can be written as sum of smaller powers
                for base2 in range(2, 20):
                    for exp2 in range(2, 20):
                        power2 = base2 ** exp2
                        if power2 <= quotient:
                            if quotient % power2 == 0:
                                quotient2 = quotient // power2
                                print(f"    {base2}^{exp2} = {power2}, quotient = {power2} * {quotient2}")
                                
                                # Check if we can find three numbers that sum to hint
                                remaining = hint - power - power2
                                if remaining > 0:
                                    print(f"      Remaining: {remaining}")
                                    
                                    # Check if this could be our three plaintexts
                                    print(f"      Potential plaintexts: {power}, {power2}, {remaining}")
                                    
                                    # Try to find e such that pow(power, e, N) = ct[0], etc.
                                    for e in [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]:
                                        if pow(power, e, N) == cts[0] and pow(power2, e, N) == cts[1] and pow(remaining, e, N) == cts[2]:
                                            print(f"      *** SOLUTION FOUND! ***")
                                            print(f"      e = {e}")
                                            print(f"      pt1 = {power}")
                                            print(f"      pt2 = {power2}")
                                            print(f"      pt3 = {remaining}")
                                            
                                            # Convert to bytes
                                            try:
                                                pt1_bytes = long_to_bytes(power)
                                                pt2_bytes = long_to_bytes(power2)
                                                pt3_bytes = long_to_bytes(remaining)
                                                print(f"      pt1 as bytes: {pt1_bytes}")
                                                print(f"      pt2 as bytes: {pt2_bytes}")
                                                print(f"      pt3 as bytes: {pt3_bytes}")
                                                
                                                # Look for flag
                                                all_bytes = pt1_bytes + pt2_bytes + pt3_bytes
                                                if b'HTB{' in all_bytes:
                                                    flag_start = all_bytes.find(b'HTB{')
                                                    flag_end = all_bytes.find(b'}', flag_start) + 1
                                                    flag = all_bytes[flag_start:flag_end]
                                                    print(f"      *** FLAG: {flag} ***")
                                                    
                                            except:
                                                pass
                                            
                                            exit()

print("No solution found with power decomposition")