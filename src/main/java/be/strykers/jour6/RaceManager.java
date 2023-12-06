package be.strykers.jour6;

import be.strykers.utils.Logger.LoggerBuilder;
import org.javatuples.Pair;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class RaceManager {
    private static Logger LOGGER = LoggerBuilder.getLogger(RaceManager.class);
    Set<Race> races;

    public RaceManager() {
        races = new HashSet<>();
    }

    public void addRace(Race racetoadd) {
        races.add(racetoadd);
    }


    public long getFirstPartResult() {
        Set<Integer> winCounts = new HashSet<>();

        int raceCount = 1;
        for (Race race : races) {
            LOGGER.fine("Starting race " + raceCount++ + " using " + race.toString());

            Pair<Integer, Integer> winInterval = race.getWinInterval();

            int result = winInterval.getValue0() - winInterval.getValue1() - 1;
            LOGGER.fine("The result is " + result);

            winCounts.add(result);
        }

        long result = 1;
        for (Integer winCount : winCounts) {
            result *= winCount;
        }

        return result;
    }

    public long getSecondPartResult() {
        if (races.size() > 1)
            throw new RuntimeException("Too much races");

        LOGGER.fine("\nStarting the second part\n");

        Race race = races.iterator().next();

        Pair<Integer, Integer> winInterval = race.getWinInterval();

        int result = winInterval.getValue0() - winInterval.getValue1() - 1;
        LOGGER.fine("The result of the second part is " + result);

        return result;
    }
}
