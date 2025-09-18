# MindMaze - HackTheBox Challenge

## Overview
**Name:** mindmaze  
**Category:** Reversing  
**Difficulty:** Insane (~6h)  
**Author:** AI Assistant  

## Description
"Has encontrado un binario que parece inocente: un simple juego de rompecabezas interactivo. Sin embargo, algo en su lógica de validación no cuadra. La única manera de vencerlo es desarmar su protección capa por capa. ¿Podrás resolver el laberinto mental y recuperar la flag?"

## Files Provided
- `mindmaze` - Binary ELF compiled for Linux x64 (stripped)

## Technical Design

### Protections Applied
1. **Stripped Binary** - No symbols, heavily obfuscated
2. **Control Flow Flattening** - State machine-based execution flow
3. **VM-based Obfuscation** - Custom virtual machine with embedded bytecode
4. **Anti-debugging** - Multiple techniques:
   - ptrace() calls
   - LD_PRELOAD detection
   - getppid() checks
   - Timing attack detection
   - Integrity checks
5. **Dynamic String Encryption** - AES-256-CBC with runtime key derivation
6. **Fragmented Flag System** - Flag split into 3 phases:
   - Phase 1: Polymorphic function reconstruction
   - Phase 2: VM bytecode execution
   - Phase 3: FSM validation

### Challenge Structure

#### Phase 1: Polymorphic Reconstruction
- Function uses obfuscated arithmetic operations
- Reconstructs first part of flag using XOR operations
- Anti-tampering checks prevent modification

#### Phase 2: Virtual Machine
- Custom VM with 28 instruction types
- Embedded bytecode in .rodata section
- Complex register operations and state management
- Extracts second part of flag through VM execution

#### Phase 3: Finite State Machine
- 10-state FSM with complex transition table
- Input validation through state progression
- Checksum validation with multiple layers
- Final validation requires exact input sequence

### Expected Solution Path

1. **Bypass Anti-debugging**
   - Identify and patch ptrace calls
   - Handle LD_PRELOAD checks
   - Bypass timing and integrity checks

2. **Reverse String Decryption**
   - Hook/analyze AES decryption functions
   - Extract runtime key derivation logic

3. **Reverse VM Interpreter**
   - Extract bytecode from .rodata section
   - Implement VM instruction set
   - Simulate execution to recover second flag part

4. **Decrypt FSM Logic**
   - Reverse engineer state transition table
   - Identify correct input sequence
   - Calculate required checksums

5. **Flag Reconstruction**
   - Combine all three flag parts
   - Final format: `HTB{<part1><part2><part3>}`

### Flag Format
```
HTB{mindmaze_1_vm_protected_2_fsm_validated_3}
```

### Estimated Solve Time
- **Beginner:** 12+ hours
- **Intermediate:** 8-10 hours  
- **Advanced:** 4-6 hours
- **Expert:** 2-4 hours

### Hints (Minimal)
1. The binary uses multiple layers of obfuscation
2. Each phase requires different reversing techniques
3. The VM bytecode contains important clues
4. Timing is critical for some validations

## Building
```bash
make clean
make
```

## Testing
```bash
# Test with correct input
echo "HTB{mind" | ./mindmaze

# Expected output:
# Welcome to MindMaze!
# Enter your solution: Congratulations! Flag: HTB{mindmaze_1_vm_protected_2_fsm_validated_3}
```

## Author Notes
This challenge is designed to test advanced reversing skills including:
- Anti-debugging bypass techniques
- Virtual machine analysis
- Control flow deobfuscation
- Cryptographic analysis
- State machine reverse engineering

The challenge is intentionally difficult and requires deep understanding of multiple reversing techniques. Good luck!