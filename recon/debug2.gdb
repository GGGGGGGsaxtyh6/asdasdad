set pagination off

break *0x400a5b
commands
  silent
  printf "Storing to cur: rax = %p\n", $rax
  x/2gx $rax
  continue
end

break *0x400b2e
commands
  silent
  set $cur_val = *(long*)0x602268
  printf "In edit_usr before read_int32:\n"
  printf "  rbp = %p\n", $rbp
  printf "  cur = %p\n", $cur_val
  printf "  cur+0x48 = %p\n", $cur_val + 0x48
  printf "  rbp - (cur+0x48) = %ld (0x%lx)\n", $rbp - ($cur_val + 0x48), $rbp - ($cur_val + 0x48)
  continue
end

break *0x400b3d
commands
  silent
  set $cur_val = $rbx
  printf "After writing age:\n"
  printf "  Wrote to %p\n", $cur_val + 0x48
  x/wx $cur_val + 0x48
  continue
end

run < <(echo -e "1\nTEST\n25\n3\nEDIT\n999\n4")
quit
