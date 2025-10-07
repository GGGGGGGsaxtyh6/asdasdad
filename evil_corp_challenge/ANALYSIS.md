# Evil Corp Challenge Analysis

## Challenge Information
- Name: Evil Corp
- Category: PWN
- Difficulty: MEDIUM
- Host: 83.136.250.244:56528
- Description: "We turned our assembly tester off because a big mistake from our new C developer. Do you think there are other mistakes he made?"

## Binary Analysis

### Security Features
```
Arch:       amd64-64-little
RELRO:      Partial RELRO
Stack:      No canary found
NX:         NX enabled
PIE:        PIE enabled
Stripped:   No
```

### Key Functions
- `Login()`: Validates credentials (eliot/4007)
- `ContactSupport()`: Has buffer overflow vulnerability
- `Setup()`: Creates RWX page at 0x11000

### Vulnerabilities Found

1. **Buffer Overflow in ContactSupport()**
   - Reserves 0x3e80 (16000) bytes on stack
   - Reads up to 0x1000 (4096) wide chars with fgetws
   - Each wide char is 4 bytes = 16384 bytes total
   - Overflow of 384 bytes allows RIP overwrite

2. **Heap Overflow in SupportMsg**
   - SupportMsg mapped at 0x10000 (1200 bytes)
   - wcharToChar16() can write up to 8192 bytes
   - Can overflow into AssemblyTestPage at 0x11000 (RWX)

### Exploitation Strategy

The intended exploit path appears to be:
1. Login with eliot/4007
2. Select option 2 (Contact Support)
3. Send payload that:
   - Writes shellcode to AssemblyTestPage (0x11000) via SupportMsg overflow
   - Overwrites RIP via stack overflow to jump to shellcode

### Challenges Encountered

1. **Unicode/Wide Char Encoding**
   - fgetws reads wide chars based on locale (UTF-8)
   - Each ASCII byte becomes wide char 0x000000XX
   - wcharToChar16 converts to char16 (2 bytes)
   - Complex interaction between character encoding and binary data

2. **RIP Address Constraints**
   - Target address 0x11000 requires specific Unicode characters
   - Cannot use null bytes (terminates fgetws)
   - Address 0x10F00 (U+10F00) is within valid Unicode range

3. **Payload Delivery**
   - UTF-8 encoding affects byte count vs character count
   - Shellcode must be encoded as valid Unicode characters
   - RIP offset calculation: 16008 bytes / 4 bytes per wide char = 4002 chars

## Files Created
- exploit.py: Python exploit script using pwntools
- evil-corp: Downloaded binary from challenge

## Current Status
- Successfully identified vulnerabilities
- Can control RIP locally
- Remote exploit not yet successful (possible differences in server config)

## Next Steps for Completion
1. Verify exact server binary version
2. Test different shellcode encodings
3. Consider alternative exploitation techniques (ROP, ret2libc)
4. Debug remote vs local behavior differences
