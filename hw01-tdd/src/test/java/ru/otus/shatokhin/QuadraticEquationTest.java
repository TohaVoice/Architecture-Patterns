package ru.otus.shatokhin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class QuadraticEquationTest {

    private static final double PRECISION = 1e-6;

    private QuadraticEquation quadraticEquation;

    @BeforeEach
    void setUp() {
        quadraticEquation = new QuadraticEquation(PRECISION);
    }

    @Test
    @DisplayName("Check out x^2+1 = 0 has no roots")
    void caseHasNotRootsTest() {
        double a = 1d;
        double b = 0d;
        double c = 1d;

        double[] roots = quadraticEquation.solve(a, b, c);

        assertEquals(0, roots.length);
    }

    @Test
    @DisplayName("Check out x^2-1 = 0 there are two multiplicity roots 1 (x1=1, x2=-1)")
    void caseHasTwoRootsTest() {
        double a = 1d;
        double b = 0d;
        double c = -1d;

        double[] roots = quadraticEquation.solve(a, b, c);

        assertEquals(2, roots.length);
        assertThat(roots[0]).isEqualTo(1, withPrecision(PRECISION));
        assertThat(roots[1]).isEqualTo(-1, withPrecision(PRECISION));
    }
}
