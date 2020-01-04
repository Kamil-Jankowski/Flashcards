package flashcards;

import java.util.*;

class CardBox {
    private HashMap<String, String> cards = new LinkedHashMap<>();

    Set<String> toSet() {
        return cards.keySet();
    }

    ArrayList<String> list(){
        return new ArrayList<>(cards.keySet());
    }

    void add(Card card) {
        cards.putIfAbsent(card.getName(), card.getDefinition());
    }

    void addOrUpdate(Card card) {
        cards.put(card.getName(), card.getDefinition());
    }

    Card getCard(String name){
        return new Card(name, cards.getOrDefault(name, "definition to be updated"));
    }

    boolean containsValue(String value){
        if (value != null){
            return cards.containsValue(value);
        }
        return false;
    }

    String getCardNameOrDefault(String value, String defaultValue) {
        for (String name : cards.keySet()) {
            if (getCard(name).getDefinition().equals(value)) {
                return name;
            }
        }
        return defaultValue;
    }

    void remove(String card){
        if (toSet().contains(card)) {
            cards.remove(card);
            IO.println("The card has been removed.\n");
        } else {
            IO.println(String.format("Can't remove \"%s\": there is no such card.\n", card));
        }
    }

    public int size() {
        return cards.size();
    }
}
