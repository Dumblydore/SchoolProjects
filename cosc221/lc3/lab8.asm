.ORIG     x3000

DO_TOP
    LEA       R0, MESG1      ; Load MESG1 to R0
    PUTS                     ; print MESG1
    GETC                     ; load input to R0
    OUT                      ; display input
    ST        R0, TMP        ; save input to tmp

    LD  R1, MAX              ; load Max number
    NOT R1, R1               ; begin 2's complement of max number
    ADD R1, R1, #1           ; R1 now has -max number
    ADD R0, R0, R1           ; R0 = a + (-max)
                             ; condition code now set
    BRn SKIP                 ; if false, skip over code
        LD R0, TMP           ; if true, load tmp to R0
        ST R0, MAX           ; set T0 as max
    SKIP

    LD        R0, CRLF       ; load newline character to R0
    OUT                      ; print new line

    LD R0, TMP               ; get temp
    LD R1, NEG48
    ADD R0, R0, R1           ; compute i - limit
BRnp DO_TOP                  ; continue while i < 0


LEA       R0, MESG2          ; Load MESG2 to R0
PUTS                         ; print MESG2
LD R0, MAX                   ; load max number to R0
OUT                          ; print max number

HALT

                    ;Data
MESG1     .STRINGZ        "Enter Number> "
MESG2     .STRINGZ        "The largest number is: "
NEG48     .FILL           #-48
END       .FILL           #0
MAX       .FILL           #48
TMP       .FILL           #0
CRLF      .FILL           #10
          .END
