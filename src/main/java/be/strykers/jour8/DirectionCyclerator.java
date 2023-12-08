package be.strykers.jour8;

import be.strykers.utils.Cyclerable.Cyclerator;

public class DirectionCyclerator implements Cyclerator<Character> {
    private final int size;
    private final char[] directions;
    private int index;

    public DirectionCyclerator(char[] directions) {
        this.directions = directions;
        this.size = directions.length;
    }

    @Override
    public Character cycle() {
        if (index == size) index = 0;

        char charToReturn = directions[index];
        index++;

        return charToReturn;
    }

    @Override
    public int getIndex() {
        return index - 1;
    }
}
