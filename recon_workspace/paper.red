;redcode
;name Paper
;assert 1
bomb: dat #0, #-10
ptr:  sub #12, #0
      mov bomb, @ptr
      djn -1, #73
      add #653, ptr
      jmp -4
end
