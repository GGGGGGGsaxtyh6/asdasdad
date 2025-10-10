;redcode
;name Combo
;assert 1
step equ 4
     spl 1
     spl 1
     spl 1
     spl 1
     mov bomb, >step
     jmp -1
bomb: dat #0, #0
end
