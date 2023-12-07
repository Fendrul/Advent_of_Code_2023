package be.strykers.utils;

import org.javatuples.Pair;

public class MathSolver {

    public static Pair<Double, Double> solveQuadraticEquation(double a, double b, double c) {
        double delta = (b * b) - (4 * a * c);
        double x1 = (-b + Math.sqrt(delta)) / (2 * a);
        double x2 = (-b - Math.sqrt(delta)) / (2 * a);
        return new Pair<>(x1, x2);
    }
}
