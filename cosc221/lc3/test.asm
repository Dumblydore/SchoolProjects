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
  BITCNT  ST          R1, SaveR1  ; load SaveR1 to R1
          ST          R2, SaveR2  ; load SaveR2 to R2
          AND         R0, R0, #0  ; Clear R0
  LOOP    LDR         R2, R1, #0  ; load char from string
          AND         R2, R2, #-1 ; check to see if character is nul
          BRz         DONE        ; if so, return to main
          LD          R4, NEG49   ; load -49 to R4
          ADD         R2, R2, R4  ; check if character is 1
          BRnp        SKIP        ; if true, skip over code
                ADD R0,R0,#1      ; increment counter if false
        SKIP
        ADD R1, R1, #1            ; increment through string
        BR  LOOP
  DONE  LD  R2, SaveR2            ; restore R2
        LD  R1,SaveR1             ; restore R1
        RET

; Subroutine data
SaveR1  .BLKW  1
SaveR2  .BLKW  1
NEG49   .FILL #-49
