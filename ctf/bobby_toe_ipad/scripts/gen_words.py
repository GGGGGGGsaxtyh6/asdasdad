#!/usr/bin/env python3
from itertools import product, permutations

base_tokens = [
    ["bobby", "Bobby", "Bobby's", "bobby's"],
    ["toe", "Toe", "toes", "Toes", "Toe's", "toe's"],
    ["ipad", "iPad", "IPad", "iPad's", "ipad's"],
]

extra = [
    "bobbytoe", "BobbyToe", "BobbyToes", "bobbytoes", "BobbyToesiPad",
    "Bobby Toe", "Bobby Toes", "Bobby Toe iPad", "Bobby Toe's iPad",
    "you found me", "congrats you found me", "you win an iPad",
    "apple", "Apple", "h4ck3d", "hack3d", "hacked",
]

seps = ["", " ", "_", "-", ".", "'", " ' "]
nums = ["", "1", "12", "123", "1234", "2020", "2021", "2024"]

def variants(tokens):
    # Generate permutations of 2 and 3 tokens with separators and numeric suffixes
    words = set()
    token_lists = [t for t in base_tokens]
    flat = [x for sub in token_lists for x in sub]

    for r in (2, 3):
        for perm in permutations(flat, r):
            for sep in seps:
                base = sep.join(perm)
                for n in nums:
                    words.add(base + n)
    # Add extras and their numeric variants
    for w in extra:
        words.add(w)
        for n in nums[1:]:
            words.add(f"{w}{n}")
    return sorted(words)

def main():
    words = variants(base_tokens)
    out_path = "/workspace/ctf/bobby_toe_ipad/out/custom_words.txt"
    with open(out_path, "w", encoding="utf-8") as f:
        for w in words:
            f.write(w + "\n")
    print(out_path)

if __name__ == "__main__":
    main()

