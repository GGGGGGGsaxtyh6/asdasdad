# NTLM Authentication Challenge - Solution Guide

## Challenge Description
The SOC team needs to recover the password from a suspicious NTLM connection over SMB.

## NTLM Authentication Analysis

### NTLMv2 Hash Format
The hash format is: `username::domain:server_challenge:ntproofstr:ntlmv2_response`

Where:
- `username`: The user attempting authentication
- `domain`: The domain or workgroup name
- `server_challenge`: 8-byte challenge sent by the server
- `ntproofstr`: HMAC-MD5 of the NTLMv2 hash with server challenge + blob
- `ntlmv2_response`: Full NTLMv2 response (ntproofstr + blob)

### Our Sample Hash
```
admin::WORKGROUP:1122334455667788:aad3b435b51404eeaad3b435b51404ee:1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef
```

Breaking it down:
- Username: `admin`
- Domain: `WORKGROUP`
- Server Challenge: `1122334455667788`
- NTProofStr: `aad3b435b51404eeaad3b435b51404ee`
- NTLMv2 Response: `1234567890abcdef1234567890abcdef1234567890abcdef1234567890abcdef`

## Cracking Methods

### Method 1: Hashcat (Recommended)
```bash
# Dictionary attack
hashcat -m 5600 ntlm_hash.txt wordlist.txt

# Brute force attack
hashcat -m 5600 -a 3 ntlm_hash.txt ?d?d?d?d?d?d?d?d --increment

# Hybrid attack (dictionary + rules)
hashcat -m 5600 -a 0 ntlm_hash.txt wordlist.txt -r /usr/share/hashcat/rules/best64.rule
```

### Method 2: John the Ripper
```bash
# Convert to John format first
john --format=netntlmv2 ntlm_hash.txt --wordlist=wordlist.txt
```

### Method 3: Custom Python Script
For educational purposes, we can create a custom script to verify passwords.

## Password Cracking Strategies

### 1. Dictionary Attack
- Use common passwords from wordlists
- Include company-specific terms
- Try variations of usernames and domains

### 2. Brute Force Attack
- Try all possible combinations
- Start with shorter passwords
- Use character sets based on policy

### 3. Hybrid Attack
- Combine dictionary words with rules
- Add numbers, symbols, case variations
- Use common patterns

### 4. Rainbow Tables
- Precomputed hash tables
- Faster for common passwords
- Limited by storage space

## Tools Used

1. **Hashcat**: GPU-accelerated password recovery
2. **John the Ripper**: Multi-format password cracker
3. **Wordlists**: Common passwords and patterns
4. **Custom Scripts**: For specific scenarios

## Expected Results

For a real NTLMv2 hash, the cracking process would:
1. Extract the hash components
2. Generate candidate passwords
3. Compute NTLMv2 hash for each candidate
4. Compare with the target hash
5. Report successful matches

## Security Implications

- NTLMv2 is vulnerable to offline attacks
- Weak passwords can be cracked quickly
- Strong passwords with proper complexity are essential
- Consider using Kerberos instead of NTLM

## Prevention

1. Use strong password policies
2. Enable account lockout policies
3. Monitor for suspicious authentication attempts
4. Consider multi-factor authentication
5. Migrate to more secure protocols like Kerberos