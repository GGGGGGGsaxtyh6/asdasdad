set pagination off

break *0x400b3a
commands
  silent
  printf "\n=== Writing age in edit_usr ===\n"
  printf "rbx (cur) = 0x%lx\n", $rbx
  printf "rbx+0x48 = 0x%lx\n", $rbx + 0x48
  printf "eax (value) = %d (0x%x)\n", $eax, $eax
  printf "rbp of edit_usr = 0x%lx\n", $rbp
  printf "Saved RIP:\n"
  x/gx $rbp + 8
  printf "Offset from write to saved RIP: %ld (0x%lx)\n", ($rbp + 8) - ($rbx + 0x48), ($rbp + 8) - ($rbx + 0x48)
  continue
end

run < /tmp/correct_input.txt
quit
