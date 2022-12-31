import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SeatReservationsTest {
    @Test
    void testEmptyString() {
        String test = "";
        assertEquals(10, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void testNoSeats() {
        String test = "1A 1B 1C 1D 1E 1F 1G 1H 1J 1K 2A 2B 2C 2D 2E 2F 2G 2H 2J 2K 3A 3B 3C 3D 3E 3F 3G 3H 3J 3K 4A 4B 4C 4D 4E 4F 4G 4H 4J 4K 5A 5B 5C 5D 5E 5F 5G 5H 5J 5K";
        assertEquals(0, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void test_1A_2F_1C_3E_4F_5H() {
        String test = "1A 2F 1C 3E 4F 5H";
        assertEquals(5, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void test_5E_5F_4E_4F_3E_3F_2E_2F_1E_1F() {
        String test = "5E 5F 4E 4F 3E 3F 2E 2F 1E 1F";
        assertEquals(0, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void test_4J_3J_2J_1J_5J() {
        String test = "4J 3J 2J 1J 5J";
        assertEquals(5, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void test_1B_1E_3A_3D_2H_4C_4E_5E() {
        String test = "1B 1E 3A 3D 2H 4C 4E 5E";
        assertEquals(5, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void testDEFGOnly() {
        String test = "1A 1F 2A 2B 2G 3F 4F 4J 5A 5G 5K";
        assertEquals(4, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void testFGHJOnly() {
        String test = "1A 1B 1C 1D 1E 1K 2B 2E 2K 3C 3D 4D 5C 5D 5G";
        assertEquals(4, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void testMixed() {
        String test = "1A 1K 3D 4C 4H 5F";
        assertEquals(7, SeatReservations.getMaxFamilies(test));
    }

    @Test
    void testStringRandomization() {
        String test1 = "1D 1G 2C";
        String test2 = "1G 1D 2C";
        String test3 = "2C 1G 1D";
        String test4 = "1G 2C 1D";
        assertEquals(7, SeatReservations.getMaxFamilies(test1));
        assertEquals(7, SeatReservations.getMaxFamilies(test2));
        assertEquals(7, SeatReservations.getMaxFamilies(test3));
        assertEquals(7, SeatReservations.getMaxFamilies(test4));
    }

    @Test
    void testNullString() {
        assertThrows(IllegalArgumentException.class, () -> {
            assertEquals(7, SeatReservations.getMaxFamilies(null));
        });
    }
}
