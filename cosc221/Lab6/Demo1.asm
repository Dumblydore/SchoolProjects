; an LC-3 program to demonstrate multiplication
; of 2 by 3 using repeated addition

.ORIG   x3000

AND     R0, R0, #0
LD      R1, NUM1
LD      R2, NUM2
ADD     R0, R0, R1
BRP     LOOP

LD      R3, POS48
ADD     R0, R0, R3
OUT

HALT

NUM1    .FILL   #2
NUM2    .FILL   #3
POS48   .FILL   #48

        .END
