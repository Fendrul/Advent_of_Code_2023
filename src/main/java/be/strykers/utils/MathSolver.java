package be.strykers.utils;

import org.javatuples.Pair;

/**
 * The MathSolver class provides methods for solving mathematical problems.
 */
public class MathSolver {

    /**
     * Solves a quadratic equation of the form ax^2 + bx + c = 0, where a, b, and c are real numbers.
     *
     * @param a the coefficient of x^2
     * @param b the coefficient of x
     * @param c the constant term
     * @return a Pair object containing the two solutions of the equation (x1, x2)
     */
    public static Pair<Double, Double> solveQuadraticEquation(double a, double b, double c) {
        double delta = (b * b) - (4 * a * c);
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
        return new Pair<>(x1, x2);
    }

    /**
     * Calculates the greatest common divisor (GCD) of two numbers using the Euclidean algorithm.
     *
     * @param a the first number
     * @param b the second number
     * @return the greatest common divisor of a and b
     */
    public static Long PGCD(Long a, Long b) {
        if (b == 0) return a;
        return PGCD(b, a % b);
    }

    /**
     * Calculates the greatest common divisor (GCD) of the given numbers.
     *
     * @param a the first number
     * @param b the second number
     * @return the greatest common divisor of a and b
     */
    public static Long PPCM(Long a, Long b) {
        return (a * b) / PGCD(a, b);
    }

    /**
     * Calculates the greatest common divisor (GCD) of the given numbers.
     *
     * @param numbers the numbers to calculate the PPCM for
     * @return the greatest common divisor of the given numbers
     */
    public static Long PPCM(Long... numbers) {
        Long result = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            result = PPCM(result, numbers[i]);
        }
        return result;
    }
}
