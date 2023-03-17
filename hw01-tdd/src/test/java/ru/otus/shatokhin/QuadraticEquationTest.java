package ru.otus.shatokhin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuadraticEquationTest {

    private static final double PRECISION = 1e-6;

    @Test
    @DisplayName("Check out x^2+1 = 0 doesn't have roots")
    void caseHasNotRootsTest() {
        double a = 1d;
        double b = 0d;
        double c = 1d;
        double[] roots;

        double d = b * b - 4 * a * c;
        if (d < - PRECISION)
            roots = new double[0];
        else
            roots = new double[1];

        assertEquals(0, roots.length);
    }
}
