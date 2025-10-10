;redcode
;name Ultra
;assert 1
step equ 4
gate: spl 1
      spl 1
      spl 1
      spl 1
      spl 1
      mov bomb, >gate
      add #step, gate
      jmp -2
bomb: dat #0, #0
end
