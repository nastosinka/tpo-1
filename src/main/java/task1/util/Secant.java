package task1.util;

public class Secant {

    private static double cos(double x, int n) {
        double result = 0.0;
        double term = 1.0;
        result += term;

        for (int k = 1; k <= n; k++) {
            term *= -x * x / ((2 * k - 1) * (2 * k));
            result += term;
        }

        return result;
    }

    public static double run(double x, int n) {
        x = x % (2 * Math.PI);
        if (x < 0) {
            x += 2 * Math.PI;
        }

        double cosValue = cos(x, n);

        if (Math.abs(cosValue) < 1e-15) {
            return Double.NaN;
        }

        return 1.0 / cosValue;
    }

}
