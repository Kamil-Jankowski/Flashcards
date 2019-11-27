package flashcards;

class CardMaker {
    private CardBox cardBox;
    private Card card;

    CardMaker(CardBox cardBox) {
        this.cardBox = cardBox;
        createCard("partykuła rozkazująca", "á, wariant a");
        this.cardBox.add(card);
    }

    private void createCard(String hint, String definition) {
        this.card = new Card(hint, definition);
    }
}
