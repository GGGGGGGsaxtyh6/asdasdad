#!/usr/bin/env python3
from itertools import product

words_core = [
    ["what", "What"],
    ["is", "Is"],
    ["love", "Love"],
]

words_tail = [
    ["baby", "Baby"],
    ["don't", "dont", "Don\'t", "Dont"],
    ["hurt", "Hurt"],
    ["me", "Me"],
]

seps = [" ", "", "_"]
puncts = ["", "?", "!?", "!", "."]

def build_phrases():
    phrases = set()

    # Core question
    for w1 in words_core[0]:
        for w2 in words_core[1]:
            for w3 in words_core[2]:
                for s in seps:
                    base = s.join([w1, w2, w3])
                    for p in puncts:
                        phrases.add(base + p)

    # Tail statement
    for a in words_tail[0]:
        for b in words_tail[1]:
            for c in words_tail[2]:
                for d in words_tail[3]:
                    for s in seps:
                        base = s.join([a, b, c, d])
                        for p in puncts:
                            phrases.add(base + p)

    # Combined (question + separator + tail) in common orderings
    cores = []
    for w1 in words_core[0]:
        for w2 in words_core[1]:
            for w3 in words_core[2]:
                for s in seps:
                    cores.append(s.join([w1, w2, w3]))

    tails = []
    for a in words_tail[0]:
        for b in words_tail[1]:
            for c in words_tail[2]:
                for d in words_tail[3]:
                    for s in seps:
                        tails.append(s.join([a, b, c, d]))

    mid_seps = [" ", " - ", " | ", ": ", ", "]
    for core in cores:
        for tail in tails:
            for m in mid_seps:
                for p in puncts:
                    phrases.add(core + p + m + tail)
                    phrases.add(tail + m + core + p)

    # Squashed variants
    squashed = set()
    for ph in phrases:
        squashed.add(ph.replace(" ", ""))
        squashed.add(ph.replace(" ", "_").replace("__", "_"))
    phrases |= squashed

    # Simple extras
    phrases |= {
        "whatislove", "WhatIsLove", "babydonthurtme", "BabyDontHurtMe",
        "whatislove?", "WhatIsLove?",
    }
    return sorted(phrases)

def main():
    out = "/workspace/ctf/bobby_toe_ipad/out/ocr_passlist_large.txt"
    phrases = build_phrases()
    with open(out, "w", encoding="utf-8") as f:
        for ph in phrases:
            f.write(ph + "\n")
    print(out)

if __name__ == "__main__":
    main()

