package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardBox quenya = new CardBox();
        CardMaker cardMaker = new CardMaker(quenya);
        String term;
        String definition;
        String answer;

        System.out.println("Input the number of cards:");
        int numberOfCards = scanner.nextInt();
        scanner.nextLine();                     // move scanner so it will not skip first input we want to read

        for (int i = 0; i < numberOfCards; i++){
            System.out.println(String.format("The card #%d", i+1));
            term = scanner.nextLine();
            System.out.printf("The definition of the card #%d\n", i+1);
            definition = scanner.nextLine();
            cardMaker.createCard(term, definition);
        }

        for (int i = 0; i < numberOfCards; i++){
            term = quenya.getCard(i).getName();
            definition = quenya.getCard(i).getDefinition();
            System.out.printf("Print the definition of \"%s\":\n", term);
            answer = scanner.nextLine();
            if(definition.equals(answer)){
                System.out.println("Correct answer.\n");
            } else {
                System.out.printf("Wrong answer. The correct one is \"%s\".\n", definition);
            }
        }
    }
}
