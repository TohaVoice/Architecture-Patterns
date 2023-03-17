package ru.otus.shatokhin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuadraticEquationTest {

    private static final double PRECISION = 1e-6;

    private QuadraticEquation quadraticEquation;

    @BeforeEach
    void setUp() {
        quadraticEquation = new QuadraticEquation(PRECISION);
    }

    @Test
    @DisplayName("Check out x^2+1 = 0 doesn't have roots")
    void caseHasNotRootsTest() {
        double a = 1d;
        double b = 0d;
        double c = 1d;

        double[] roots = quadraticEquation.solve(a, b, c);

        assertEquals(0, roots.length);
    }
}
