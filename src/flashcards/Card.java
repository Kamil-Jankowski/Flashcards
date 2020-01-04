package flashcards;

import java.util.Objects;

public class Card {
    private String hint;
    private String definition;

    Card(String hint, String definition){
        this.hint = hint;
        this.definition = definition;
    }

    String getName() {
        return hint;
    }

    String getDefinition() {
        return definition;
    }

    @Override
    public String toString() {
        return "Card:\n" +
                hint + "\n" +
                "Definition:\n" +
                definition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return hint.equals(card.hint) &&
                definition.equals(card.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hint, definition);
    }
}
