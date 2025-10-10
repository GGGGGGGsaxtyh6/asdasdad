;redcode
;name Scanner
;assert 1
step    equ 4
        spl 1
        spl 1
        spl 1
        spl 1
loop:   mov bomb, >target
        add #step, target
        jmp loop
target: dat #step, #0
bomb:   dat #0, #0
end
