public class SeatReservations {
    // Number of rows given by the problem
    private static final int DEFAULT_NUM_ROWS = 5;

    /*
        returns the maximum amount of families that can be seated together in
        the following grid layout:

           A B C  D E F G  H J K
        1 [][][] [][][][] [][][]
        2 [][][] [][][][] [][][]
        3 [][][] [][][][] [][][]
        4 [][][] [][][][] [][][]
        5 [][][] [][][][] [][][]

        Parameters: type String takenSeats is the input string containing all seats
                    that is already occupied before computation of maximum number
                    of families.
        Returns: type int, maximum amount of families that can be seated together
        Throws: IllegalArgumentException if takenSeats is null

        n = # of rows = 5
        m = # of cols = 10
        s = input string length

        Runtime: O(2n + s) = O(n + s) = O(5 + s) = O(s)
        Space: O(n) = O(5) = O(1)

        We can remove n in runtime/space complexity because in this problem rows = 5
     */
    public static int getMaxFamilies(String takenSeats) {
        if (takenSeats == null) {
            throw new IllegalArgumentException("input String to getMaxFamilies is null");
        }

        /*
            - runs n times
            - constant work for Pair creation
         */
        Row[] rows = new Row[DEFAULT_NUM_ROWS];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Row();
        }

        /*
            - # of loop runs scales with s
            - Constant time operations
         */
        for (int i = 0; i < takenSeats.length(); i += 3) {
            int rowNumber = Character.getNumericValue(takenSeats.charAt(i));
            char col = takenSeats.charAt(i + 1);

            if (col == 'B' || col == 'C' || col == 'D' || col == 'E' && !rows[rowNumber - 1].BCDE) {
                rows[rowNumber - 1].BCDE = true;
            }

            if (col == 'D' || col == 'E' || col == 'F' || col == 'G' && !rows[rowNumber - 1].DEFG) {
                rows[rowNumber - 1].DEFG = true;
            }

            if (col == 'F' || col == 'G' || col == 'H' || col == 'J' && !rows[rowNumber - 1].FGHJ) {
                rows[rowNumber - 1].FGHJ = true;
            }
        }

        /*
            - runs n times
            - constant work inside
         */
        int families = 0;
        for (Row row : rows) {
            if (!row.BCDE && !row.FGHJ) {
                families += 2;
            } else if (!row.BCDE || !row.FGHJ || !row.DEFG) {
                families += 1;
            }
        }

        return families;
    }

    /*
        Utility Class that represents a row.

        For each row,
          - A and K cannot contribute to a valid family because that goes against the
            rule that families need to be next to each other
              - Thus we ignore A and K
          - There are three valid spots for families
            - BCDE
            - DEFG
            - FGJK
            - At most we can fit 2 valid families in a row (Family 1 = BCDE, Family 2 = FGHJ)
     */
    private static class Row {
        // True if a family cannot be seated in seats BCDE, false otherwise
        public boolean BCDE;

        // True if a family cannot be seated in seats DEFG, false otherwise
        public boolean DEFG;

        // True if a family cannot be seated in seats FGHJ, false otherwise
        public boolean FGHJ;
    }
}
