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

        hands.sort(Hand::compareTo);

//        LOGGER.fine("\nFinal values :");

        return resultFromSortedHands();
    }

    public int getSecondPartSolution() {
        hands.sort(
                (hand1, hand2) -> {
                    if (hand1.getTypeWithJ() > hand2.getTypeWithJ())
                        return 1;

                    if (hand1.getTypeWithJ() < hand2.getTypeWithJ())
                        return -1;

                    return Integer.compare(hand1.getHexCardsWithJ(), hand2.getHexCardsWithJ());
                }
        );

        LOGGER.fine("\nFinal values for part 2 :");

        return resultFromSortedHands();
    }

    private int resultFromSortedHands() {
        int result = 0;

        for (int i = 0; i < hands.size(); i++) {
            int valueToAdd = hands.get(i).getBid() * (i + 1);
            LOGGER.fine(hands.get(i).toString() + " :: " + (i + 1) + " :: " + valueToAdd + "\n");
            result += valueToAdd;
        }

        return result;
    }
}
