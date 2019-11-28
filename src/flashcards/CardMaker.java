package flashcards;

class CardMaker {
    private CardBox cardBox;

    CardMaker(CardBox cardBox) {
        this.cardBox = cardBox;
    }

    void createCard(String hint, String definition) {
        Card card = new Card(hint, definition);
        this.cardBox.add(card);
    }
}
