package be.strykers.jour8;

import be.strykers.utils.Cyclerable.Cyclerable;
import be.strykers.utils.Cyclerable.Cyclerator;

import java.util.Arrays;

public class DirectionMap implements Cyclerable<Character> {
    char[] directions;

    public DirectionMap(String directions) {
        this.directions = directions.toCharArray();
    }

    public int getSize() {
        return directions.length;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "directions=" + Arrays.toString(directions) +
                '}';
    }

    @Override
    public Cyclerator<Character> getCyclerator() {
        return new DirectionCyclerator(directions);
    }
}
