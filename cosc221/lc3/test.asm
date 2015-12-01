; Maurice Edwards
; Fall 2015
; COSC 221
; Lab 9
;
; This program loads a bitstring in memory and iterates over it
; and displays number of ones (between 0 - 9)

  .ORIG   x3000

  LEA     R0, MESG1     ; Display message
  PUTS
  LEA     R1, ITEM      ; load bitstring to R1
  JSR     BITCNT        ; call subroutine BITCNT

  LD      R2, POS48     ; load 48
  ADD     R0,R0,R2      ; add to get character reperensentation of COUNTER
  OUT                   ; display counter

  LD    R0, CRLF        ; display newline
  OUT

  HALT

;Data
  COUNTER .BLKW       1
  POS48   .FILL       #48
  ITEM    .STRINGZ    "10101"
  MESG1   .STRINGZ    "Number of 1's in this string: "
  CRLF    .FILL       #10
          .END

; Subroutines
  BITCNT
  LOOP
    LD R1, COUNTER
    OUT
    ADD R0, R0, #1
    ADD R1, R0, #-16
    BRz DONE
    BR LOOP

  DONE RET

; Subroutine data
SaveR1  .BLKW  1
SaveR2  .BLKW  1
NEG49   .FILL #-49
