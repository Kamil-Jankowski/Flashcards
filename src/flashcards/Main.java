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
        String correctName;

        System.out.println("Input the number of cards:");
        int numberOfCards = scanner.nextInt();
        scanner.nextLine();                     // move scanner so it will not skip first input we want to read

        // Putting cards into CardBox
        for (int i = 0; i < numberOfCards; i++){
            System.out.println(String.format("The card #%d", i+1));
            term = scanner.nextLine();
            while (quenya.toSet().contains(term)){
                System.out.printf("The card \"%s\" already exists. Try again:\n", term);
                term = scanner.nextLine();
            }
            System.out.printf("The definition of the card #%d\n", i+1);
            definition = scanner.nextLine();
            while (quenya.getCardNameOrDefault(definition, null) != null){
                System.out.printf("The definition \"%s\" already exists. Try again:\n", definition);
                definition = scanner.nextLine();
            }
            cardMaker.createCard(term, definition);
        }

        // Asking about the definition of cards from the CardBox
        for (String cardName : quenya.toSet()){
            definition = quenya.getCardDefinition(cardName);
            System.out.printf("Print the definition of \"%s\":\n", cardName);
            answer = scanner.nextLine();
            correctName = quenya.getCardNameOrDefault(answer, null);
            if(cardName.equals(correctName)){
                System.out.println("Correct answer.");
            } else if(correctName != null){
                System.out.printf("Wrong answer. The correct one is \"%s\"", definition);
                System.out.printf(", you've just written the definition of \"%s\".\n", correctName);
            } else {
                System.out.printf("Wrong answer. The correct one is \"%s\".\n", definition);
            }
        }
    }
}
