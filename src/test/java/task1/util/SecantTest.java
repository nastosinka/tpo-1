package task1.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SecantTest {

    private final double limit = 1e-6;

    @Test
    void testZero() {
        assertEquals(1.0, Secant.run(0, 20), limit);
    }

    @Test
    void testSimpleValue() {
        assertEquals(1.0/Math.cos(0.5), Secant.run(0.5, 20), limit);
    }

    @Test
    void testNegativeValue() {
        assertEquals(1.0/Math.cos(-0.3), Secant.run(-0.3, 20), limit);
    }

    @Test
    void testPeriodicity() {
        double value1 = Secant.run(0.7, 20);
        double value2 = Secant.run(0.7 + 2 * Math.PI, 20);
        assertEquals(value1, value2, limit);
    }

    @Test
    void testNan() {
        assertTrue(Double.isNaN(Secant.run(Math.PI / 2, 20)));
    }

    @Test
    void testNearDiscontinuity() {
        double x = Math.PI / 2 - 0.001;
        assertEquals(1.0 / Math.cos(x), Secant.run(x, 50), 1e-3);
    }

    @Test
    void testEvenFunction() {
        double x = 0.4;
        assertEquals(Secant.run(x, 20),
                Secant.run(-x, 20),
                limit);
    }

    @Test
    void testAngleNormalization() {
        double normalized = Secant.run(10 * Math.PI, 20);
        assertEquals(1.0, normalized, limit);
    }

    @Test
    void testLargeNImprovesPrecision() {
        double smallN = Secant.run(0.5, 2);
        double largeN = Secant.run(0.5, 20);
        assertTrue(Math.abs(largeN - 1 / Math.cos(0.5)) < Math.abs(smallN - 1 / Math.cos(0.5)));
    }
}
