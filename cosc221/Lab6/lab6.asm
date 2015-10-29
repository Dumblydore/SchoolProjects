; Maurice Edwards
; COSC 221
; Lab  6
 
 
.ORIG   x3000
 
LEA     R0, MESG1       ; Display input prompt
PUTS
 
; load 1st character into memory
GETC
OUT
ST              R0,CHAR1
 
; load 2nd character into memory
GETC
OUT
ST              R0,CHAR2
 
; load 3rd character into memory
GETC                            ; get character
OUT                             ; output character
ST              R0,CHAR3        ; save to variable
 
LD              R0,CRLF         ; print new line
OUT
 
LEA R0,MESG2
PUTS
 
; print new line 
LD              R0,CRLF
OUT
 
; display 1st character
LD              R0,CHAR1
LD              R1,NEG32
ADD             R0,R0,R1
OUT
 
; print new line
LD              R0,CRLF
OUT
 
; display 2nd character
LD              R0,CHAR2
LD              R1,NEG32
ADD             R0,R0,R1
OUT

; print new line
LD              R0,CRLF
OUT
 
; display 3rd character
LD              R0,CHAR3
LD              R1,NEG32
ADD             R0,R0,R1
OUT
 
HALT
 
; Data
NEG32   .FILL           #-32
MESG1   .STRINGZ        "Please enter your initials (First, Middle, Last)>"
MESG2   .STRINGZ        "Here are initials in uppercase: "
CRLF    .FILL           #10
CHAR1   .BLKW           1
CHAR2   .BLKW           1
CHAR3   .BLKW           1
 
                .END