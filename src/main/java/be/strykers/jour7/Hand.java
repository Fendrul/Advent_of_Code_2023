package be.strykers.jour7;

import be.strykers.utils.Logger.LoggerBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Getter
public class Hand implements Comparable<Hand> {
    private static final Logger LOGGER = LoggerBuilder.getLogger();
    //Cards are stored under the form of hexValues
    private final int cards;
    private final int bid;
    private final int type;

    public Hand(char[] cards, int bid) {
        if (cards.length != 5) throw new IllegalArgumentException("A hand must have 5 cards");

        this.bid = bid;
        this.type = getType(cards);
        this.cards = convertCards(cards);
    }

    private int convertCards(char[] cards) {
        int collapsedCardsValues = 0;
        List<Integer> cardsValues = new ArrayList<>();

        for (int i = cards.length - 1; i >= 0; i--) {
            HexaCardValue hexaCardValue = HexaCardValue.getHexaCardValue(cards[i]);
            cardsValues.add(hexaCardValue.getHexaValue());
        }

        cardsValues.sort(Integer::compareTo);

        for (int i = cardsValues.size() - 1; i >= 0; i--) {
            collapsedCardsValues = collapsedCardsValues << 4;
            collapsedCardsValues += cardsValues.get(i);
        }

        LOGGER.finest("The collapsed value of the cards is " + Integer.toHexString(collapsedCardsValues));

        return collapsedCardsValues;
    }

    private int getType(char[] cards) {
        int type = 0;
        int[] cardsCounts = new int[HexaCardValue.values().length];

        for (char card : cards) {
            HexaCardValue hexaCardValue = HexaCardValue.getHexaCardValue(card);
            cardsCounts[hexaCardValue.getHexaValue() - 1]++;
        }

        int maxCount = 0;
        for (int count : cardsCounts) {
            if (count > maxCount) maxCount = count;
        }

        type = switch (maxCount) {
            case 1 -> 1;

            case 2 -> {
                int pairCount = 0;
                for (int count : cardsCounts) {
                    if (count == 2) {
                        pairCount++;
                    }
                }

                if (pairCount == 2) {
                    yield 3;
                } else {
                    yield 2;
                }
            }

            case 3 -> {
                for (int count : cardsCounts) {
                    if (count == 2) {
                        yield 5;
                    }
                }
                yield 4;
            }

            default -> maxCount + 2;
        };

        return type;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + Integer.toHexString(cards) +
                ", bid=" + bid +
                ", type=" + type +
                '}';
    }

    @Override
    public int compareTo(Hand o) {
        if (this.type > o.type) return 1;
        if (this.type < o.type) return -1;

        return Integer.compare(this.cards, o.cards);
    }
}
