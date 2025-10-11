set pagination off

break *0x400a5b
commands
  silent
  printf "\n[CREATE] Setting cur to %p\n", $rax
  x/2gx $rax
  continue
end

break *0x400b01
commands
  silent
  printf "\n[EDIT] Loading cur value\n"
  set $cur_addr = *(long*)0x602268
  printf "cur addr = %p\n", $cur_addr
  printf "Will read name into heap at:\n"
  x/gx $cur_addr
  continue
end

break *0x400b18
commands
  silent
  printf "\n[EDIT] After reading name, heap buffer:\n"
  set $cur_val = *(long*)0x602268
  set $heap_ptr = *(long*)$cur_val
  printf "Heap pointer: %p\n", $heap_ptr
  x/8xb $heap_ptr
  continue
end

break *0x400b3a
commands
  silent
  printf "\n[EDIT] Writing age value %d to [rbx+0x48]\n", $eax
  printf "rbx = %p\n", $rbx
  printf "rbx+0x48 = %p\n", $rbx + 0x48
  printf "Current content at target:\n"
  x/gx $rbx + 0x48
  continue
end

break *0x400b3d
commands
  silent
  printf "\n[EDIT] After writing age:\n"
  x/gx $rbx + 0x48
  printf "Checking cur value after edit:\n"
  x/2gx 0x602268
  continue
end

break *0x400a90
commands
  silent
  printf "\n[PRINT] About to print name\n"
  set $cur_val = *(long*)0x602268
  printf "cur = %p\n", $cur_val
  set $heap_ptr = *(long*)$cur_val
  printf "heap ptr = %p\n", $heap_ptr
  x/s $heap_ptr
  continue
end

run < /tmp/gdb_full_test.txt
quit
