package flashcards;

import java.util.*;

class CardBox {
    private ArrayList<Card> cards = new ArrayList<>();

    void add(Card card) {
        cards.add(card);
    }

    Card getCard(int index) {
        return cards.get(index);
    }
}
