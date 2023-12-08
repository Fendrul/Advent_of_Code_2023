package be.strykers.jour7;

import be.strykers.utils.Logger.LoggerBuilder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Getter
public class Hand implements Comparable<Hand> {
    private static final Logger LOGGER = LoggerBuilder.getLogger();

    //Cards are stored under the form of hexValues
    private final int hexCards;
    private final String cardsString;
    private final int bid;
    private final int type;
    private final int typeWithJ;
    private int hexCardsWithJ;
    private char bestCard;

    public Hand(char[] cards, int bid) {
        if (cards == null) throw new NullPointerException("A hand must have cards");
        if (cards.length != 5) throw new IllegalArgumentException("A hand must have 5 cards");

        this.bid = bid;
        this.cardsString = Arrays.toString(cards);
        this.type = getType(cards);

        this.typeWithJ = getTypeWithJ(cards);

        LOGGER.finest("""
                Hand %s
                Type : %d
                Type with J : %d
                """.formatted(Arrays.toString(cards), this.type, this.typeWithJ));

        this.hexCards = convertCards(cards);
    }

    private int convertCards(char[] cards) {
        int collapsedCardsValues = 0;
        int collapsedCardsValuesWithJ = 0;
        List<Integer> cardsValues = new ArrayList<>();

        for (int i = cards.length - 1; i >= 0; i--) {
            Card card = Card.getHexaCardValue(cards[i]);
            cardsValues.add(card.getHexaValue());
        }

        for (int i = cardsValues.size() - 1; i >= 0; i--) {
            collapsedCardsValues = collapsedCardsValues << 4;
            collapsedCardsValuesWithJ = collapsedCardsValuesWithJ << 4;

            Integer cardValue = cardsValues.get(i);
            collapsedCardsValues += cardValue;

            if (cardValue == Card.J.getHexaValue()) {
                collapsedCardsValuesWithJ += Card.ONE.getHexaValue();
            } else {
                collapsedCardsValuesWithJ += cardValue;
            }


        }

        hexCardsWithJ = collapsedCardsValuesWithJ;

        LOGGER.finest("The collapsed value of the cards is " + Integer.toHexString(collapsedCardsValues));

        return collapsedCardsValues;
    }

    private int getType(char[] cards) {
        int type;
        int[] cardsCounts = new int[Card.values().length];

        for (char card : cards) {
            Card hexaCardValue = Card.getHexaCardValue(card);
            cardsCounts[hexaCardValue.getHexaValue() - 1]++;
        }

        int maxCount = 0;

        for (int i = 0; i < cardsCounts.length; i++) {
            if (cardsCounts[i] > maxCount) {
                maxCount = cardsCounts[i];
                this.bestCard = Card.values()[i].getCharValue();
            }
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

    private int getTypeWithJ(char[] cards) {
        int result;
        int JCount = 0;

        for (char card : cards) {
            Card hexaCardValue = Card.getHexaCardValue(card);
            if (hexaCardValue.getCharValue() == Card.J.getCharValue()) JCount++;
        }


        result = switch (this.type) {
            case 1 -> {
                /**
                 * 1 -> 2 1234J
                 */
                if (JCount == 1) yield 2;
                yield 1;
            }

            case 2 -> {
                /**
                 * 1 -> 4 1123J
                 * 2 -> 4 JJ123
                 */
                if (JCount == 1) yield 4;
                else if (JCount == 2) yield 4;
                else yield 2;
            }

            case 3 -> {
                /**
                 * 1 -> 5   1122J
                 * 2 -> 5   112JJ
                 */
                if (JCount == 1) yield 5;
                else if (JCount == 2) yield 6;
                else yield 3;
            }

            case 4 -> {
                /**
                 * 1 -> 6   1112J
                 * 3 -> 6   12JJJ
                 */
                if (JCount == 1) yield 6;
                else if (JCount == 3) yield 6;
                else yield 4;
            }

            case 5 -> {
                /**
                 * 2 -> 7   111JJ
                 * 3 -> 7   11JJJ
                 */

                if (JCount == 2 || JCount == 3) yield 7;
                else yield this.type;
            }

            case 6 -> {
                /**
                 * 1 -> 7   1111J
                 * 4 -> 7   1JJJJ
                 */

                if (JCount == 1 || JCount == 4) yield 7;
                else yield this.type;
            }

            case 7 -> 7;

            default -> throw new IllegalStateException("Unexpected value: " + this.type);
        };

        return result;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cards=" + cardsString +
                ", type=" + type +
                ", typeWithJ" + typeWithJ +
                ", bid=" + bid +
                '}';
    }

    @Override
    public int compareTo(Hand o) {
        if (this.type > o.type) return 1;
        if (this.type < o.type) return -1;

        return Integer.compare(this.hexCards, o.hexCards);
    }
}
