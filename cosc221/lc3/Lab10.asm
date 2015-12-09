.ORIG     x3000

LEA       R0, MESG1      ; Load MESG1 to R0
PUTS                     ; print MESG1
LEA       R0, string
DO_TOP
    GETC                     ; load input to R0
    OUT                      ; display input

    STR R0,R7,#0             ; r0 -> ( memory address stored in r1 + 0 )
    ADD R1,R7,#1             ; increments the memory pointer so that it

    LD R1, NEG10
    ADD R0, R7, R1           ; compute i - limit
BRnp DO_TOP                  ; continue while i < 0


LEA       R0, string                ; load max number to R0
PUTS                          ; print max number

HALT

;Data
MESG1     .STRINGZ        "Enter Characters> "
MESG2     .STRINGZ        "Your string: "
NEG10     .FILL           #-10
END       .FILL           #0
string    .BLKW           100
TMP       .FILL           #0
CRLF      .FILL           #10
          .END
