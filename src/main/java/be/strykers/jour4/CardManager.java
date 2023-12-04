package be.strykers.jour4;

import java.util.HashMap;
import java.util.Map;

public class CardManager {
    Map<Integer, Card> cards = new HashMap<>();

    public CardManager() {

    }

    public void addCard(Card card) {
        cards.put(card.getGameID(), card);
    }

    public int getFirstPartScore() {
        int sum = 0;

        for (Card card : cards.values()) {
            sum += card.getFirstPartScore();
        }

        return sum;
    }

    public long getSecondPartScore() {
        for (int cardID = 1; cardID <= cards.size(); cardID++) {
            Card card = cards.get(cardID);
            int sameCardCount = card.samecardCount();

            for (int i = 1; i <= sameCardCount; i++) {
                Card cardToAddInstances = cards.get(cardID + i);

                if (cardToAddInstances == null) throw new RuntimeException("Card not found");

                cardToAddInstances.addInstanceCount(
                        card.getInstanceCount()
                );
            }

        }

        return cards.values().stream()
                .reduce(0L, (sum, card) -> sum + card.getInstanceCount(), Long::sum);
    }
}
