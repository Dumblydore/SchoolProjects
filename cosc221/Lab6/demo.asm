  .ORIG x3000

  AND   R2,R2,#0
  LEA   R3, STRING
  IN
  LDR   R1, R3, #0

TEST  ADD R4, R1, #0
      BRz OUTPUT

      NOT R1, R1
      ADD R1, R1, #0
      BRz OUTPUT

GETCHAR ADD R3, R3,#1
        LDR R1, R3, #0
        BR  TEST

OUTPUT  LEA R0, PRMPT
        PUTS
        LD  R0, POS48
        ADD R0,R0,R2
        OUT

        HALT

STRING     .STRINGZ    "ABSUDIDFA@"
PRMPT   .STRINGZ    "Total count: "
POS48   .FILL       #48
        .END
