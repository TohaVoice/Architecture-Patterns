package ru.otus.shatokhin;

public class QuadraticEquation {
    private final double precision;

    public QuadraticEquation(double precision) {
        this.precision = precision;
    }

    public double[] solve(double a, double b, double c) {
        double d = b * b - 4 * a * c;
        return d < -precision ? new double[0] : null;
    }
}
