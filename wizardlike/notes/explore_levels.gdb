set pagination off
set confirm off

# Break at main after initialization
b *0x004024f6

# Run the game
run

# Check current level
printf "Current level: %d\n", *(int*)0x00533f7c

# Loop through levels to find flag
set $level = 0
while $level < 100
    # Set current level
    set *(int*)0x00533f7c = $level
    printf "Checking level %d\n", $level
    
    # Continue briefly to render
    continue
    
    set $level = $level + 1
end

quit
