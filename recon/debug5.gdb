set pagination off

break *0x400b3a
commands
  silent
  printf "=== About to write age ===\n"
  printf "rbx (cur) = 0x%lx\n", $rbx
  printf "rbx+0x48 = 0x%lx\n", $rbx + 0x48
  printf "eax (value) = 0x%x (%d)\n", $eax, $eax
  printf "rbp = 0x%lx\n", $rbp
  printf "saved RIP [rbp+8]:\n"
  x/gx $rbp + 8
  printf "Distance: %ld (0x%lx)\n", ($rbp + 8) - ($rbx + 0x48), ($rbp + 8) - ($rbx + 0x48)
  continue
end

run < /tmp/test_input.txt
quit
