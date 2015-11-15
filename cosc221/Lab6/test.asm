.ORIG     x3000

DO_TOP
    LEA       R0, MESG1       ; Load MESG1 to R0
    PUTS                      ; print MESG1
    LD        R0, COUNTER
    OUT

    ADD       R0, R0, #1
    ST        R0, COUNTER

    LD        R1, MAX
    NOT       R1, R1
    ADD       R1, R1, #1
    ADD       R0, R0, R1
    BRn       DO_TOP



HALT

;Data
MESG1     .STRINGZ        "Loop test: "
MESG2     .STRINGZ        "The largest number is: "
MAX       .FILL           #58
TMP       .BLKW           1
COUNTER   .FILL           #48
          .END
