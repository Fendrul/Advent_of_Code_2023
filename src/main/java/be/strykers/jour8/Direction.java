package be.strykers.jour8;

import java.util.Arrays;

public class Direction {
    char[] directions;

    public Direction(String directions) {
        this.directions = directions.toCharArray();
    }

    @Override
    public String toString() {
        return "Direction{" +
                "directions=" + Arrays.toString(directions) +
                '}';
    }
}
