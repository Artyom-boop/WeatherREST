import aston.hw.Utils.Utils;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {
    private static final Random random = new Random();
    private static final int QUANTITY_ITERATIONS = 100;

    @Test
    public void isNumericTrueTest() {
        for (int i = 0; i < QUANTITY_ITERATIONS; i++) {
            assertTrue(Utils.isNumeric(String.valueOf(random.nextInt())));
            assertTrue(Utils.isNumeric(String.valueOf(random.nextDouble())));
            assertTrue(Utils.isNumeric(String.valueOf(random.nextLong())));
        }
    }

    @Test
    public void isNumericFalseTest() {
        assertFalse(Utils.isNumeric(null));
        assertFalse(Utils.isNumeric("Hello"));
        assertFalse(Utils.isNumeric("12543s"));
        assertFalse(Utils.isNumeric("s12543"));
    }
}
