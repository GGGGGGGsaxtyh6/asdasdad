set pagination off
break *0x400a54
commands
  silent
  printf "Before storing cur: rax = %p\n", $rax
  x/gx $rax
  continue
end

break *0x400a62
commands
  silent
  printf "After storing cur: cur points to %p\n", *(long*)0x602268
  x/2gx 0x602268
  continue
end

break *0x400b2e
commands
  silent
  printf "In edit_usr: cur = %p, rbp = %p\n", *(long*)0x602268, $rbp
  printf "Will write to %p (cur+0x48)\n", *(long*)0x602268 + 0x48
  continue
end

run < <(echo -e "1\nTEST\n25\n3\nEDIT\n999\n4")
quit
