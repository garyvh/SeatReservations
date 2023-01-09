import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatReservationsScaleTest {
    @Test
    void testEmptyString() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "";

        assertEquals(10, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testNoSeats() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "1A 1B 1C 1D 1E 1F 1G 1H 1J 1K 2A 2B 2C 2D 2E 2F 2G 2H 2J 2K 3A 3B 3C 3D 3E 3F 3G 3H 3J 3K 4A 4B 4C 4D 4E 4F 4G 4H 4J 4K 5A 5B 5C 5D 5E 5F 5G 5H 5J 5K";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void test_1A_2F_1C_3E_4F_5H() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "1A 2F 1C 3E 4F 5H";

        assertEquals(5, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void test_5E_5F_4E_4F_3E_3F_2E_2F_1E_1F() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "5E 5F 4E 4F 3E 3F 2E 2F 1E 1F";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void test_4J_3J_2J_1J_5J() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "4J 3J 2J 1J 5J";

        assertEquals(5, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void test_1B_1E_3A_3D_2H_4C_4E_5E() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "1B 1E 3A 3D 2H 4C 4E 5E";

        assertEquals(5, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testDEFGOnly() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "1A 1F 2A 2B 2G 3F 4F 4J 5A 5G 5K";

        assertEquals(4, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testFGHJOnly() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "1A 1B 1C 1D 1E 1K 2B 2E 2K 3C 3D 4D 5C 5D 5G";

        assertEquals(4, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testMixed() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test = "1A 1K 3D 4C 4H 5F";

        assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testStringRandomization() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;
        String test1 = "1D 1G 2C";
        String test2 = "1G 1D 2C";
        String test3 = "2C 1G 1D";
        String test4 = "1G 2C 1D";

        assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, test1));
        assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, test2));
        assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, test3));
        assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, test4));
    }

    @Test
    void testNullString() {
        boolean[][] seats = new boolean[5][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true;
        aisles[6] = true;

        assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, null));
        });
    }

    @Test
    void testEmptyArrays() {
        boolean[][] seats = new boolean[0][0];
        boolean[] aisles = new boolean[0];

        assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, ""));
        });
    }

    @Test
    void testLargerAisles() {
        boolean[][] seats = new boolean[1][1];
        boolean[] aisles = new boolean[2];

        assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(7, SeatReservationsScale.getMaxFamilies(seats, aisles, ""));
        });
    }

    @Test
    void testSmallNoReservationsNoAisles() {
        boolean[][] seats = new boolean[4][4];
        boolean[] aisles = new boolean[3];
        String test = "";

        assertEquals(4, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallNoFreeSeatsNoAisle() {
        boolean[][] seats = new boolean[4][4];
        boolean[] aisles = new boolean[3];
        String test = "1A 1B 1C 1D 2A 2B 2C 2D 3A 3B 3C 3D 4A 4B 4C 4D";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallNoReservationsMiddleAisle() {
        boolean[][] seats = new boolean[4][4];
        boolean[] aisles = new boolean[3];
        aisles[1] = true;
        String test = "";

        assertEquals(4, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallNoReservationsLeftAisle() {
        boolean[][] seats = new boolean[4][4];
        boolean[] aisles = new boolean[3];
        aisles[0] = true;
        String test = "";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallNoReservationsRightAisle() {
        boolean[][] seats = new boolean[4][4];
        boolean[] aisles = new boolean[3];
        aisles[2] = true;
        String test = "";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallNoReservationsMaxAisles() {
        boolean[][] seats = new boolean[4][4];
        boolean[] aisles = new boolean[3];
        aisles[0] = true;
        aisles[1] = true;
        aisles[2] = true;
        String test = "";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallIrregularLayout() {
        boolean[][] seats = new boolean[4][4];
        seats[1][3] = true;
        seats[2][1] = true;
        seats[2][2] = true;
        seats[2][3] = true;
        seats[3][0] = true;
        seats[3][1] = true;
        seats[3][2] = true;
        seats[3][3] = true;
        boolean[] aisles = new boolean[3];
        String test = "";

        assertEquals(1, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testSmallIrregularLayoutTakenSeat() {
        boolean[][] seats = new boolean[4][4];
        seats[1][3] = true;
        seats[2][1] = true;
        seats[2][2] = true;
        seats[2][3] = true;
        seats[3][0] = true;
        seats[3][1] = true;
        seats[3][2] = true;
        seats[3][3] = true;
        boolean[] aisles = new boolean[3];
        String test = "1A";

        assertEquals(0, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testManyColsManyAisles() {
        boolean[][] seats = new boolean[1][20];
        boolean[] aisles = new boolean[19];
        aisles[2] = true;
        aisles[5] = true;
        aisles[8] = true;
        aisles[11] = true;
        aisles[14] = true;
        aisles[17] = true;
        String test = "";

        assertEquals(3, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testManyColsSomeAislesTakenSeat() {
        boolean[][] seats = new boolean[1][20];
        boolean[] aisles = new boolean[19];
        aisles[0] = true;
        aisles[2] = true;
        aisles[17] = true;
        aisles[18] = true;
        String test = "1M";

        assertEquals(3, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }

    @Test
    void testGivenInterview() {
        // 50 rows and 10 columns
        boolean[][] seats = new boolean[50][10];
        boolean[] aisles = new boolean[9];
        aisles[2] = true; // an aisle between col C and D
        aisles[6] = true; // an aisle between col G and H
        String test = "1B 1E 3A 3D 2H 4C 4E 5E 6B 6E 7A 7D 8H 9C 9E 10E 11B 11E 13A 13D 12H 14C 14E 15E 16B 16E 17A 17D 18H 19C 19E 20E 21B 21E 23A 23D 22H 24C 24E 25E 26B 26E 27A 27D 28H 29C 29E 30E 35E 35F 34E 34F 33E 33F 32E 32F 31E 31F 40E 40F 39E 39F 38E 38F 37E 37F 36E 36F";

        assertEquals(50, SeatReservationsScale.getMaxFamilies(seats, aisles, test));
    }
}
