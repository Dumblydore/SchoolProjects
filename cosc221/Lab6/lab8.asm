.ORIG     x3000

DO_TOP
    LEA       R0, MESG1       ; Load MESG1 to R0
    PUTS                      ; print MESG1
    GETC
    OUT

    ST        R0, TMP

    





    LD        R0, CRLF        ; load newline character to R0
    OUT                       ; print new line

    LD        R0, END
    ADD       R0, R0, #1
    ST        R0, END

    LD        R1, TMP
    NOT       R1, R1
    ADD       R1, R1, #1
    ADD       R0, R0, R1
    BRn       DO_TOP



HALT

;Data
MESG1     .STRINGZ        "Enter Number> "
MESG2     .STRINGZ        "The largest number is: "
END       .FILL           #48
MAX       .FILL           #48
TMP       .FILL           #0
CRLF      .FILL           #10
          .END
