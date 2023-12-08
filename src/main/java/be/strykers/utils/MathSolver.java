package be.strykers.utils;

import org.javatuples.Pair;

public class MathSolver {

    public static Pair<Double, Double> solveQuadraticEquation(double a, double b, double c) {
        double delta = (b * b) - (4 * a * c);
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
        return new Pair<>(x1, x2);
    }

    public static Long PGCD(Long a, Long b) {
        if (b == 0) return a;
        return PGCD(b, a % b);
    }

    public static Long PPCM(Long a, Long b) {
        return (a * b) / PGCD(a, b);
    }

    public static Long PPCM(Long... numbers) {
        Long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = PPCM(result, numbers[i]);
        }
        return result;
    }
}
