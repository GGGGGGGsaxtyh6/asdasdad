;redcode
;name Stone
;assert 1
step equ 3
      spl 1
      spl 1
      mov bomb, >target
      add step, target
target: jmp -2, step
bomb: dat #0, #0
end
