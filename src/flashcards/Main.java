package flashcards;

public class Main {
    public static void main(String[] args) {
        CardBox quenya = new CardBox();
        CardMaker cardMaker = new CardMaker(quenya);
        Card firstCard = quenya.getCard();
        System.out.println(firstCard.toString());
    }
}
