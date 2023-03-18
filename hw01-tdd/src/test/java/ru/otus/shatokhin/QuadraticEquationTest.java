package ru.otus.shatokhin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.assertj.core.api.Assertions.*;

public class QuadraticEquationTest {

    private static final double PRECISION = 1e-6;

    private QuadraticEquation quadraticEquation;

    private static class InfinityCoefficientArrayProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                            new double[]{1d, 2d, Double.POSITIVE_INFINITY},
                            new double[]{1d, Double.POSITIVE_INFINITY, 3d},
                            new double[]{Double.NEGATIVE_INFINITY, 2d, 3d})
                    .map(Arguments::of);
        }
    }

    private static class NanCoefficientArrayProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                            new double[]{1d, 2d, Double.NaN},
                            new double[]{1d, Double.NaN, 3d},
                            new double[]{Double.NaN, 2d, 3d})
                    .map(Arguments::of);
        }
    }

    @BeforeEach
    void setUp() {
        quadraticEquation = new QuadraticEquation(PRECISION);
    }

    @Test
    @DisplayName("Check x^2+1 = 0 out, has no roots")
    void caseHasNotRootsTest() {
        double a = 1d;
        double b = 0d;
        double c = 1d;

        double[] roots = quadraticEquation.solve(a, b, c);

        assertEquals(0, roots.length);
    }

    @Test
    @DisplayName("Check x^2-1 = 0 out, there are two multiplicity roots 1 (x1=1, x2=-1)")
    void caseHasTwoMultiplicityRootsTest() {
        double a = 1d;
        double b = 0d;
        double c = -1d;

        double[] roots = quadraticEquation.solve(a, b, c);

        assertEquals(2, roots.length);
        assertThat(roots[0]).isEqualTo(1, withPrecision(PRECISION));
        assertThat(roots[1]).isEqualTo(-1, withPrecision(PRECISION));
    }

    @Test
    @DisplayName("Check x^2+2x+1 = 0 out, there is one multiplicity root 2 (x1= x2 = -1)")
    void caseHasOneMultiplicityRootsTest() {
        double a = 1d + 1e-10;
        double b = 2d + 1e-10;
        double c = 1d + 1e-10;

        double[] roots = quadraticEquation.solve(a, b, c);

        assertEquals(2, roots.length);
        assertThat(roots[0]).isEqualTo(-1, withPrecision(PRECISION));
        assertThat(roots[0]).isEqualTo(roots[1], withPrecision(PRECISION));
    }

    @Test
    @DisplayName("Check A is not to equal 0")
    public void caseThrowExceptionWhenCoefficientAIsEqualToZero() {
        double a = 0;
        double b = 2d;
        double c = 1d;

        assertThatThrownBy(() -> quadraticEquation.solve(a, b, c))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Coefficient may not be 0");
    }

    @DisplayName("Check coefficient cannot be infinity")
    @ParameterizedTest()
    @ArgumentsSource(InfinityCoefficientArrayProvider.class)
    public void caseThrowExceptionWhenCoefficientIsInfinite(double[] coefficients) {
        assertThatThrownBy(() ->
                quadraticEquation.solve(coefficients[0], coefficients[1], coefficients[2]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Coefficients cannot be infinity");
    }

    @ParameterizedTest()
    @DisplayName("Check coefficient cannot be infinity")
    @ArgumentsSource(NanCoefficientArrayProvider.class)
    public void shouldThrowExceptionWhenCoefficientIsNaN(double[] coefficients) {
        assertThatThrownBy(() -> quadraticEquation.solve(coefficients[0], coefficients[1], coefficients[2]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Coefficients cannot be NaN");
    }

}
