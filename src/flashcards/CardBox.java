package flashcards;

import java.util.*;

class CardBox {
    private HashMap<String, String> cards = new LinkedHashMap<>();

    void add(Card card) {
        cards.putIfAbsent(card.getName(), card.getDefinition());
    }

    String getCardDefinition(String key) {
        return cards.get(key);
    }

    String getCardNameOrDefault(String value, String defaultValue) {
        for (String name : cards.keySet()) {
            if (getCardDefinition(name).equals(value)) {
                return name;
            }
        }
        return defaultValue;
    }

    Set<String> toSet() {
        return cards.keySet();
    }
}
