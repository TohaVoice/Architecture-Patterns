package ru.otus.shatokhin;

public class QuadraticEquation {
    private final double precision;

    public QuadraticEquation(double precision) {
        this.precision = precision;
    }

    public double[] solve(double a, double b, double c) {
        if (Math.abs(a) < precision) {
            throw new IllegalArgumentException("Coefficient may not be 0");
        }

        double d = b * b - 4 * a * c;
        if (Math.abs(d) < precision) {
            double x = -b / (2 * a);
            return new double[]{x, x};
        } else if (d < -precision) {
            return new double[0];
        } else {
            double sqrt = Math.sqrt(d);
            double x1 = (-b + sqrt) / (2 * a);
            double x2 = (-b - sqrt) / (2 * a);
            return new double[]{x1, x2};
        }
    }
}
