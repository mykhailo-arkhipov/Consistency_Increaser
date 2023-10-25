It's the latest simplified version of the program. The README file describes the algorithm from the inside.

The program is easy to use. Everything is reduced to user questions and instructions.

----------------Step 1:
You need to open the project package and run Main.main using F5 / Shift+F5.

----------------Step 2:
The user will be asked how to initialize the matrix and enter the number of elements.
1 - Generate
2 - Manual input
3 - CSV
4 - From the ORCON article

----------------Step 3:
The user needs to choose the mode of consistency - manual or automatic. After
selection, a new window with a visualized graph will appear.

----------------Step 4 (when choosing manual mode):
The user needs to choose one of the relationship changes, if any
appear. You need to enter the change number.

----------------Step 5 (when choosing manual mode):
Repeat Step 4 until exit (entering 0) or matrix consistency.

----------------BRIEF EXAMPLE----------------

Step 2 ----- Choose the type of matrix import: 1 - Generate; 2 - Manual input; 3 - CSV; 4 - From the ORCON article
1
Step 2.1 --- How many elements are in your square matrix?
5
(...)
Step 3 ----- Enter 1 for manual consistency improvement or 2 for automatic:
1
(...)
Step 4 ----- Enter the change number (or 0 to exit):
1
There are no more cycles. The program has finished.

(The example described above is sufficient to repeat the manual operation of the program. However, there is also a practical example of consistency below.)

----------------DETAILED EXAMPLE----------------

Choose the type of matrix import: 1 - Generate; 2 - Manual input; 3 - CSV; 4 - From the ORCON article
1
How many elements are in your square matrix?
5
0 1 0 1 0
0 0 2 0 0
0 2 0 2 2
0 0 2 0 1
0 0 2 0 0
Enter 1 for manual consistency improvement or 2 for automatic:
1
Ordinal consistency coefficient: 0.01639344262295082 - the matrix is sufficiently consistent.

The number of cycles found in the matrix: 1

indicates preference, *= indicates equivalent preference: 1

Found cycles:

A3 *= A4 > A5 *= A3
Step 1
Unique pairs:

A3 *= A4
A4 > A5
A5 *= A3
Rank 1:

The new relationship A4 *= A5 will result in the following number of cycles: 0
The new relationship A3 < A4 will result in the following number of cycles: 0
The new relationship A5 < A3 will result in the following number of cycles: 0
Rank 2:
4. The new relationship A3 > A4 will result in the following number of cycles: 1

The new relationship A5 > A3 will result in the following number of cycles: 1
The new relationship A4 < A5 will result in the following number of cycles: 1
Path to achieve ordinal consistency effectively:
The new relationship A4 *= A5, where 0 cycles will remain.
The new relationship A3 < A4, where 0 cycles will remain.
The new relationship A5 < A3, where 0 cycles will remain.

Enter the change number (or 0 to exit):
5

Modified matrix:
0 1 0 1 0
0 0 2 0 0
0 2 0 2 0
0 0 2 0 1
0 0 1 0 0

Ordinal consistency coefficient: 0.01639344262295082 - the matrix is sufficiently consistent.

Found cycles:

A3 *= A4 > A5 > A3
Step 2
Unique pairs:

A3 *= A4
A4 > A5
A5 > A3
Rank 1:

The new relationship A3 < A4 will result in the following number of cycles: 0
The new relationship A4 < A5 will result in the following number of cycles: 0
The new relationship A5 < A3 will result in the following number of cycles: 0
Rank 2:
4. The new relationship A3 > A4 will result in the following number of cycles: 1

The new relationship A4 *= A5 will result in the following number of cycles: 1
The new relationship A5 *= A3 will result in the following number of cycles: 1
Path to achieve ordinal consistency effectively:
The new relationship A3 < A4, where 0 cycles will remain.
The new relationship A4 < A5, where 0 cycles will remain.
The new relationship A5 < A3, where 0 cycles will remain.

Enter the change number (or 0 to exit):
2

Modified matrix:
0 1 0 1 0
0 0 2 0 0
0 2 0 2 0
0 0 2 0 0
0 0 1 1 0

Ordinal consistency coefficient: 0.0 - the matrix is fully consistent.

There are no more cycles. The program has finished.