;redcode
;name ImpKiller
;assert 1
step equ 2667
gate: spl 1
      spl 1
      spl 1
      mov bomb, >gate
      add #step, gate
      jmp -2
bomb: dat #0, #0
end
