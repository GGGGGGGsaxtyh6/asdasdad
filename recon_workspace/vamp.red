;redcode
;name Vamp
;assert 1
step equ 2667
loop spl 1
     mov bomb, @loop
     add #step, loop
     jmp loop
bomb dat #0, #0
end
