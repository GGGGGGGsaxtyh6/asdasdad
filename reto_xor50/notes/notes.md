The Poopolator (xor50)

Findings:
- Banner: "The Poopolator". Input format: three integers: a b idx. a,b must be non-zero; idx in [0,9].
- Behavior: result[idx] = a ^ b; prints "Result: <value>"; loops.
- Bug: idx is only checked after scanf return count; signed negative indices are accepted before bounds check, enabling arbitrary 8-byte write relative to `result` base.
- Target: Overwrite a GOT entry with address of `win` (0xA21), e.g., `printf@GOT` or `exit@GOT`.
- Addresses:
  - result: 0x202200 (array of 10 qwords)
  - printf@GOT: 0x201FA0 (index -76 from result)
  - exit@GOT:   0x201FE8 (index -67 from result)
  - win:        0xA21 (PIE .text offset)
- Local test: writing to GOT with negative index causes segfault or hijack depending on target.
- Remote: attempted to overwrite GOT entries; no success observed within 10s timeout. Need refined approach (e.g., leak/align base or target printf path).

Next steps:
- Consider hijacking `printf` to `win` so `printf` call immediately runs `system("cat flag")`.
- If PIE base affects behavior, explore writable pointers in init/fini arrays for leak/anchor.
- Expand solver to loop and verify output after write, then trigger exit.

