GhostRace Diagnostics Node
==========================

Category: Reversing (Hard)
Estimated time: 5-6 hours

Context
-------
Last week's rush patches for CVE-2024-3094 (xz backdoor) and the GhostRace speculative execution race left the Eclipse Lab telemetry node in a strange state. The engineers bolted a bespoke spectral VM on top of the mitigation pipeline and now only a very specific activation sequence will let the diagnostics relay boot.

Deliverables
------------
This bundle contains two files:

1. `ghostrace_node` — the interactive binary
2. `README.txt` — this brief dossier

Usage
-----
* Run the binary locally (`./ghostrace_node`) — expect a handshake gate and an interactive console.
* The `deploy` command feeds a speculative payload into a custom bytecode VM that combines GhostRace-style timing residue with the xz-hardened key lattice.
* Reverse the handshake, decode the VM program, and recover the flag that stabilises the relay. No networking is required once you have the binary.

Rules
-----
* The flag follows the usual `r3vCTF{...}` format.
* No bruteforcing — the signal collapses quickly if you hammer it.
* Share only the final flag, not the binary or your exploit chain.

Good luck, and mind the speculation window.
