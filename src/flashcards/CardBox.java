package flashcards;

import java.util.*;

class CardBox {
    private LinkedList<Card> cards = new LinkedList<>();

    void add(Card card) {
        cards.add(card);
    }

    public Card getCard() {
        return cards.element();
    }
}
