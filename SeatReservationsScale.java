import java.util.*;

public class SeatReservationsScale {

    // Map to convert Letter to its corresponding index
    public static Map<Character, Integer> LETTER_TO_INDEX = new HashMap<>() {{
        put('A', 0);
        put('B', 1);
        put('C', 2);
        put('D', 3);
        put('E', 4);
        put('F', 5);
        put('G', 6);
        put('H', 7);
        put('J', 8);
        put('K', 9);
        put('L', 10);
        put('M', 11);
        put('N', 12);
        put('O', 13);
        put('P', 14);
        put('Q', 15);
        put('R', 16);
        put('S', 17);
        put('T', 18);
        put('U', 19);
        put('V', 20);
        put('W', 21);
        put('X', 22);
        put('Y', 23);
        put('Z', 24);
    }};

    /*
        returns the maximum amount of families that can be seated together in
        a given airplane seating layout

        Parameters:
            boolean[][] seats: The grid (2D array) that represents the seating
                               arrangement of the plane. seats[i][j] must indicate
                               if the seat is reserved or not (true if reserved,
                               false otherwise). Supports irregular layouts where
                               there can be missing seats in a grid like the following:
                                       A B C
                                    1 [][][]
                                    2 []  []
                                    3 [][][]
                                A hole in the grid is indicated with a true value, for
                                example, if my plane is missing the 2B seat I would still
                                have a 2D matrix of row size 3 and col size 3, but I would
                                make sure that seats[1][1] = true before passing seats into
                                this function.

            boolean[][] aisles: boolean array that indicates where the aisles are. A true
                                value at index i means there is an aisle between i and
                                i + 1. Must have size equal to the maximum number of columns
                                seats has, minus 1.

            String input: string containing all seats that is already occupied before
                          computation of maximum number of families.

        Returns: Type int, maximum amount of families that can be seated together
        Throws: IllegalArgumentException if input is null or seats is null or aisles is
                null or seats has length of 0 or aisles.length != max columns size of
                grid minus 1.

        s = length of input string, r = # of rows, c = # of columns
        Runtime: O(s + rc)
        Space: O(1), note that seats will likely be mutated
     */
    public static int getMaxFamilies(boolean[][] seats, boolean[] aisles, String input) {
        if (input == null) {
            throw new IllegalArgumentException("input String to getMaxFamilies is null");
        }

        if (seats == null) {
            throw new IllegalArgumentException("parameter boolean[][] seats is null");
        }

        if (aisles == null) {
            throw new IllegalArgumentException("parameter boolean[] aisles is null");
        }

        if (seats.length == 0) {
            throw new IllegalArgumentException("parameter boolean[][] seats is empty");
        }

        if (aisles.length != seats[0].length - 1) {
            throw new IllegalArgumentException("parameter boolean[] aisles is incorrect length");
        }

        seatReserved(seats, input);
        int max = 0;
        int rowIndex = 0;

        /*
            Runtime (outer loop): O(r) where r = number of rows
            Runtime (inner loop): O(c) where c = number of cols
            Runtime (Total): O(rc)
         */
        while (rowIndex < seats.length) {
            boolean[] row = seats[rowIndex];
            int colIndex = 0;

            while (colIndex < seats[rowIndex].length - 3) {
                boolean currSeatTaken = row[colIndex];
                boolean sndSeatTaken = row[colIndex + 1];
                boolean thdSeatTaken = row[colIndex + 2];
                boolean fthSeatTaken = row[colIndex + 3];

                // If current seat and next 3 seats are not taken
                if (!currSeatTaken && !sndSeatTaken && !thdSeatTaken && !fthSeatTaken) {

                    /*
                        Example: 1A1B1C1D
                        if there is not an aisle between 1A and 1B, and
                        no aisle between 1C and 1D.
                        (aisle between 1B and 1C is allowed)
                     */
                    if (!aisles[colIndex] && !aisles[colIndex + 2]) {
                        max++;
                        colIndex += 4;
                    } else {
                        colIndex++;
                    }

                } else {
                    colIndex += findFurthestNonTakenSeat(currSeatTaken, sndSeatTaken,
                                                        thdSeatTaken, fthSeatTaken);
                }
            }
            rowIndex++;
        }

        return max;
    }

    /*
        Given a string that tells what seats are reserved and 2D boolean
        array, marks the reserved seats on the 2D boolean array by setting
        them to true.

        Mutates seats.

        Parameters:
            boolean[][] seats: the airplane seats layout
            String input: The string indicating which seats are reserved
                          beforehand

        s = length of input string
        Runtime: O(s)
        Space: O(1)
     */
    public static void seatReserved(boolean[][] seats, String input) {

        // No need to do this algo if string is empty
        if (!input.equals("")) {
            String[] takenSeats = input.split(" ");

            for (int i = 0; i < takenSeats.length; i++) {
                int findLetter = 0;
                while (Character.isDigit(takenSeats[i].charAt(findLetter))) {
                    findLetter++;
                }
                int rowNumber = Integer.parseInt(takenSeats[i].substring(0, findLetter));

                // Here I assume that A col is indicated by a single char and it's A-Z without I.
                int col = LETTER_TO_INDEX.get(takenSeats[i].charAt(findLetter));
                seats[rowNumber - 1][col] = true;
            }
        }
    }

    /*
        Given 4 seats, returns an int that indicates how much seats we
        are able to safely skip (we can safely skip all seats that
        are taken but not ones that are not taken)

        Parameters:
            boolean fst: first seat
            boolean snd: second seat
            boolean thd: third seat
            boolean fth: fourth seat

        Returns: type int, how many seats we can skip.

        Runtime: O(1)
        Space: O(1)
     */
    private static int findFurthestNonTakenSeat(boolean fst, boolean snd, boolean thd, boolean fth) {
        if (fth) {
            return 4;
        } else if (thd) {
            return 3;
        } else if (snd) {
            return 2;
        } else {
            return 1;
        }
    }
}
