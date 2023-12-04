package be.strykers.jour4;

import java.util.Set;

public class Card {
    private final int gameID;

    private final Set<Integer> firstSet;

    private final Set<Integer> secondSet;
    private long instanceCount;

    public Card(int gameID, Set<Integer> firstSet, Set<Integer> secondSet) {
        this.gameID = gameID;
        this.firstSet = firstSet;
        this.secondSet = secondSet;
        instanceCount = 1;
    }

    public int samecardCount() {
        return (int) secondSet.stream()
                .filter(firstSet::contains)
                .count();
    }

    public int getFirstPartScore() {
        int count = samecardCount();
        count--;
        return (int) Math.pow(2, count);
    }

    public int getGameID() {
        return gameID;
    }

    public long getInstanceCount() {
        return instanceCount;
    }

    public void addInstanceCount(long instances) {
        this.instanceCount += instances;
    }
}
