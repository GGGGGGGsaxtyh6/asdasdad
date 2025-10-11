set pagination off

break *0x400b3a
commands
  silent
  printf "About to write age to [rbx+0x48]\n"
  printf "rbx (cur) = 0x%lx\n", $rbx
  printf "rbx+0x48 = 0x%lx\n", $rbx + 0x48
  printf "eax (value to write) = 0x%x (%d)\n", $eax, $eax
  printf "Current value at rbx+0x48:\n"
  x/gx $rbx + 0x48
  printf "rbp of edit_usr = 0x%lx\n", $rbp
  printf "Saved RIP at [rbp+8] = 0x%lx:\n", $rbp + 8
  x/gx $rbp + 8
  printf "Distance from write location to saved RIP: %ld bytes\n", ($rbp + 8) - ($rbx + 0x48)
  continue
end

break *0x400b3d
commands
  silent
  printf "\nAfter writing:\n"
  x/gx $rbx + 0x48
  continue
end

run < <(python3 -c "print(1); print(TEST); print(25); print(3); print(EDIT); print(999); print(4)")
quit
