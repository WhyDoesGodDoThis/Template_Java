package core.utils;

public final class MathUtil {
    public static final double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
