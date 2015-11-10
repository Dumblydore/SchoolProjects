; Maurice Edwards
; COSC 221
; Lab  7

.ORIG   x3000

LEA     R0, MESG1       ; Display input prompt
PUTS

; load 1st character into memory as a number
GETC
OUT
LD R1,NEG48
ADD             R0,R0,R1
ST R0, NUM1


; get second number and add the two together
LD R1,NUM1
GETC
OUT
ADD             R0,R0,R1
ST              R0,SUM

; print new line
LD              R0,CRLF
OUT


; display display sum
LEA R0,MESG2
PUTS

LD              R0,SUM
OUT

HALT

; Data
NEG48   .FILL           #-48
MESG1   .STRINGZ        "Enter a two-digit decimal number>"
MESG2   .STRINGZ        "The sum of the two numbers are: "
CRLF    .FILL           #10
NUM1    .BLKW           1
SUM     .BLKW           1
                .END
