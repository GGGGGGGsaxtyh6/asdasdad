set pagination off

break *0x400a62
commands
  silent
  printf "After setting cur in create_user\n"
  x/gx 0x602268
  continue
end

break *0x400b9d
commands
  silent
  printf "In main loop, cur value:\n"
  x/gx 0x602268
  continue
end

break *0x400a7d
commands
  silent
  printf "In print_user, loading cur:\n"
  x/gx 0x602268
  printf "cur value: 0x%lx\n", *(long*)0x602268
  if *(long*)0x602268 != 0
    printf "Dereferencing cur:\n"
    x/gx *(long*)0x602268
  end
  continue
end

run < <(echo -e "1\nTEST\n25\n2\n4")
quit
