package be.strykers.jour6;

import be.strykers.utils.Logger.LoggerBuilder;
import be.strykers.utils.MathSolver;
import lombok.Getter;
import org.javatuples.Pair;

import java.util.logging.Logger;

public class Race {
    private static final Logger LOGGER = LoggerBuilder.getLogger(Race.class);

    @Getter
    private final double time;
    @Getter
    private final double distance;

    public Race(double time, double distance) {
        this.time = time;
        this.distance = distance;
    }

    public Pair<Integer, Integer> getWinInterval() {
        Pair<Integer, Integer> result;

        Pair<Double, Double> mathResult = MathSolver.solveQuadraticEquation(1, -time, distance);
        result = new Pair<>(
                (int) Math.ceil(mathResult.getValue0()),
                (int) Math.floor(mathResult.getValue1())
        );

        LOGGER.finest(String.format("The win interval is between %s and %s", mathResult.getValue0(), mathResult.getValue1()));
        LOGGER.fine(String.format("The win interval converted is between %s and %s", result.getValue0(), result.getValue1()));

        return result;
    }

    @Override
    public String toString() {
        return "Race{" +
                "time=" + time +
                ", distance=" + distance +
                '}';
    }
}
