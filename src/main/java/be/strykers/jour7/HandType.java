package be.strykers.jour7;

import lombok.Getter;

@Getter
public enum HandType {
    HIGH_CARD(1),
    ONE_PAIR(2),
    TWO_PAIRS(3),
    THREE_OF_A_KIND(4),
    FULL_HOUSE(5),
    FOUR_OF_A_KIND(6),
    FIVE_OF_A_KIND(7);

    private final int value;

    HandType(int value) {
        this.value = value;
    }
}
