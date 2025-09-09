# NTLM Authentication Challenge - Complete Solution

## Challenge Overview
This challenge involves recovering a password from a suspicious NTLM connection over SMB. The SOC team needs to analyze the NTLMv2 hash and crack the password.

## Files Created

### 1. `ntlm_challenge.txt`
Contains the NTLMv2 hash in the format:
```
username::domain:server_challenge:ntproofstr:ntlmv2_response
```

### 2. `ntlm_cracker.py`
Custom Python script that demonstrates the NTLMv2 cracking process:
- Parses NTLMv2 hash components
- Generates NTLMv2 hashes for candidate passwords
- Compares generated hashes with the target hash
- Reports successful matches

### 3. `wordlist.txt`
Common passwords used for dictionary attacks

### 4. `solution_guide.md`
Comprehensive guide explaining:
- NTLMv2 hash format and components
- Various cracking methods (Hashcat, John the Ripper)
- Password cracking strategies
- Security implications and prevention

## Tools Installed

- **Hashcat**: GPU-accelerated password recovery tool
- **John the Ripper**: Multi-format password cracker
- **Python3-impacket**: NTLM protocol implementation
- **Python3-requests**: For downloading challenge files

## Cracking Methods Demonstrated

### Method 1: Hashcat
```bash
# Dictionary attack
hashcat -m 5600 ntlm_hash.txt wordlist.txt

# Brute force attack
hashcat -m 5600 -a 3 ntlm_hash.txt ?d?d?d?d?d?d?d?d --increment
```

### Method 2: Custom Python Script
```bash
python3 ntlm_cracker.py
```

### Method 3: John the Ripper
```bash
john --format=netntlmv2 ntlm_hash.txt --wordlist=wordlist.txt
```

## NTLMv2 Hash Analysis

The hash format breaks down as follows:
- **Username**: `admin`
- **Domain**: `WORKGROUP`
- **Server Challenge**: `1122334455667788` (8 bytes)
- **NTProofStr**: `aad3b435b51404eeaad3b435b51404ee` (16 bytes)
- **NTLMv2 Response**: `1234567890abcdef...` (variable length)

## Security Implications

1. **NTLMv2 Vulnerabilities**:
   - Susceptible to offline attacks
   - Weak passwords can be cracked quickly
   - No protection against rainbow table attacks

2. **Prevention Measures**:
   - Use strong password policies
   - Enable account lockout policies
   - Monitor authentication attempts
   - Consider multi-factor authentication
   - Migrate to Kerberos when possible

## Expected Results

For a real NTLMv2 hash with a weak password, the cracking process would:
1. Successfully parse the hash components
2. Generate candidate passwords
3. Compute NTLMv2 hashes for each candidate
4. Find a match and report the password

## Usage Instructions

1. **Download the challenge file** (if SSL issues are resolved):
   ```bash
   wget https://static.root-me.org/reseau/ch28/ch28.zip
   unzip ch28.zip
   ```

2. **Extract the NTLM hash** from the challenge file

3. **Run the cracking tools**:
   ```bash
   # Using Hashcat
   hashcat -m 5600 ntlm_hash.txt wordlist.txt
   
   # Using custom script
   python3 ntlm_cracker.py
   ```

4. **Analyze results** and report the recovered password

## Notes

- The sample hash provided is for demonstration purposes
- Real NTLMv2 hashes would be cracked using the same methodology
- The tools and techniques shown are standard in penetration testing and security analysis
- Always ensure you have proper authorization before attempting password cracking