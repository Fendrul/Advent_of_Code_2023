package be.strykers.jour7;

import lombok.Getter;

@Getter
public enum Card {
    ONE('1', 0x1),
    TWO('2', 0x2),
    THREE('3', 0x3),
    FOUR('4', 0x4),
    FIVE('5', 0x5),
    SIX('6', 0x6),
    SEVEN('7', 0x7),
    EIGHT('8', 0x8),
    NINE('9', 0x9),
    T('T', 0xA),
    J('J', 0xB),
    Q('Q', 0xC),
    K('K', 0xD),
    A('A', 0xE);

    private final char charValue;
    private final int hexaValue;

    Card(char value, int hexaValue) {
        this.charValue = value;
        this.hexaValue = hexaValue;
    }

    public static Card getHexaCardValue(char value) {
        for (Card card : Card.values()) {
            if (card.getCharValue() == value) return card;
        }
        throw new IllegalArgumentException("No hexaCardValue found for " + value);
    }
}
