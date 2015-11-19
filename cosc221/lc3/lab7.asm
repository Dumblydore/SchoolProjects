; Maurice Edwards
; COSC 221
; Fall 2015
; Lab  7
; this program reads in a two-digit decimal
; number whose digits add up to less than 10

    .ORIG   x3000

    LEA       R0, MESG2       ; Load MESG1 to R0
    PUTS                      ; print MESG1

    ; load 1st character into memory as a number
    GETC                      ; get input character
    OUT                       ; display entered character
    LD        R1, NEG48       ; Load -48 into register 1
    ADD       R1, R0, R1      ; get 'real' number and load into R1

    ; get second number and add the two together
    GETC                      ; get input character
    OUT                       ; output input
    ADD       R0, R0, R1      ; add both inputs
    ST        R0, SUM         ; save to SUM variable

    ; print new line
    LD        R0, CRLF        ; load newline character to R0
    OUT                       ; print new line

    ; display display sum
    LEA       R0, MESG2       ; Load MESG2 to R0
    PUTS                      ; print MESG2

    LD        R0, SUM         ; load SUM variable to R0
    OUT                       ; print SUM

    ; print new line
    LD        R0, CRLF        ; load newline character to R0
    OUT                       ; print new line

    HALT                      ; stop

    ; Data
    NEG48     .FILL           #-48
    MESG1     .STRINGZ        "Enter a two-digit decimal number>"
    MESG2     .STRINGZ        "The sum of the two numbers are: "
    CRLF      .FILL           #10
    SUM       .BLKW           1
              .END
