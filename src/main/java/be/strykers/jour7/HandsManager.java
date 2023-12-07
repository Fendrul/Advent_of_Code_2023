package be.strykers.jour7;

import be.strykers.utils.Logger.LoggerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class HandsManager {
    private static final Logger LOGGER = LoggerBuilder.getLogger();
    List<Hand> hands;

    public HandsManager() {
        hands = new ArrayList<>();
    }

    ;

    public void addHand(Hand hand) {
        hands.add(hand);
    }

    public long getFirstPartSolution() {
        long result = 0;

        hands.sort(Hand::compareTo);

        LOGGER.fine("\nFinal values :");
        for (int i = 0; i < hands.size(); i++) {
            int valueToAdd = hands.get(i).getBid() * (i + 1);
            LOGGER.fine(hands.get(i).toString() + " :: " + (i + 1) + " :: " + valueToAdd);
            result += valueToAdd;
        }

        return result;
    }
}
