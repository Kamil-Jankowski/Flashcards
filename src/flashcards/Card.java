package flashcards;

public class Card {
    private String hint;
    private String definition;

    Card(String hint, String definition){
        this.hint = hint;
        this.definition = definition;
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
}
