# SeatReservations

> Assumptions:
  - This problem is only about a grid of 5 rows and 10 columns.
    - I used this infomation to make a simpler and faster algorithmn
      that lacks scaleability.
  - In to have a valid family, they all must be on the same row.
  - No leading and no trailing spaces on the input string.
  - A valid seat name is two characters, first character is a number between
    1 and 5, second character is either A, B, C, D, E, F, G, H, J, K

> Questions:

My questions are if my assumptions are correct.

> The different paths or solutions you contemplated

If this problem was a grid of n rows and m columns, the algorithmn
would be much different.

Since a family needs to be in the same row, we can apply logic to
a single row that finds the maximum number of families that can
sit in that single row at one time and do that for all the rows
to get the result we want.

A 2D array is sufficent to represent the grid of rows and columns of seats,
where each index of the array contains a boolean that represents if the
seat is taken or not (true if seat is taken).

Before doing logic of finding max families in a single row, we want to
process the input string and mark all seats in the 2D array of seats
that is indicated by the input string to be taken already.

For a single row r,
  1. Make a counter variable
  2. Find valid spots to place a family, if a valid spot to place a family
     is found, mark those seats as taken in the 2D array.
  3. When we reach the end of the row and thus considered all seats
     we add our counter variable to the final result

We do the single row algorithmn for all rows to get a final answer.

So far we did not add logic of aisles are how aisles can eliminate some valid
families. We would have to know which seats are seperated by an aisle. If we
have this we can change our logic of valid spots to place a family by knowing
where the aisles are.

This algorithmn needs to know number of rows, number of columns, and columns
are seperated by an aisle.
