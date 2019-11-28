package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardBox quenya = new CardBox();
        CardMaker cardMaker = new CardMaker(quenya);
        String answer;

        cardMaker.createCard(scanner.nextLine(), scanner.nextLine());
        answer = scanner.nextLine();
        String definition = quenya.getCard().getDefinition();
        if(definition.equals(answer)){
            System.out.println("Your answer is right!\n");
        } else {
            System.out.println("Your answer is wrong...\n");
        }
    }
}
