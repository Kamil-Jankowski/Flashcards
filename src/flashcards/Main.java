package flashcards;

import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static CardBox boxOfCards = new CardBox();
    private static CardMaker cardMaker = new CardMaker(boxOfCards);
    private static String cardName, cardDefinition, filePath;
    private static String defaultAnswer = "No card name match the definition you provided.";

    public static void main(String[] args) {
        String userInput;
        do {
            System.out.println("Input the action (add, remove, import, export, ask, exit):");
            userInput = scanner.nextLine();
            switch (userInput){
                case "add":
                    addSingleCard(boxOfCards);
                    break;
                case "remove":
                    removeSingleCard(boxOfCards);
                    break;
                case "import":
                    importCards(boxOfCards);
                    break;
                case "export":
                    exportCards(boxOfCards);
                    break;
                case "ask":
                    askAQuestion(boxOfCards);
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    break;
                default:
                    System.out.println("Wrong input provided.\n");
            }
        } while (!("exit".equals(userInput)));
    }

    private static void addSingleCard(CardBox cardBox) {
        System.out.println("The card:");
        cardName = scanner.nextLine();

        if (cardBox.toSet().contains(cardName)){
            System.out.printf("The card \"%s\" already exists.\n\n", cardName);
            return;
        } else {
            System.out.println("The definition of the card:");
            cardDefinition = scanner.nextLine();
            if (cardBox.containsValue(cardDefinition)) {
                System.out.printf("The definition \"%s\" already exists.\n\n", cardDefinition);
                return;
            }
        }

        cardMaker.createCard(cardName, cardDefinition);
        System.out.println(String.format("The pair (\"%s\":\"%s\") has been added.\n", cardName, cardDefinition));
    }

    private static void removeSingleCard(CardBox cardBox) {
        System.out.println("The card:");
        String cardToBeRemoved = scanner.nextLine();
        cardBox.remove(cardToBeRemoved);
    }

    private static void importCards(CardBox cardBox) {
        System.out.println("File name:");
        filePath = "./" + scanner.nextLine();
        cardBox.importCards(filePath);
    }

    private static void exportCards(CardBox cardBox) {
        System.out.println("File name:");
        filePath = "./" + scanner.nextLine();
        cardBox.exportCards(filePath);
    }

    private static void askAQuestion(CardBox cardBox) {
        List<String> cardNames = cardBox.list();
        int deckSize = cardNames.size();
        Random rand = new Random();

        System.out.println("How many times to ask?");
        int amountOfQuestions = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < amountOfQuestions; i++){
            int randomCard = rand.nextInt(deckSize);
            cardName = cardNames.get(randomCard);
            cardDefinition = cardBox.getCard(cardName).getDefinition();

            System.out.printf("Print the definition of \"%s\":\n", cardName);

            String answer = scanner.nextLine();
            String nameThatMatchesAnswer = cardBox.getCardNameOrDefault(answer, defaultAnswer);

            checkAnswer(cardName, nameThatMatchesAnswer);
        }
        System.out.println();
    }

    private static void checkAnswer(String cardName, String nameThatMatchesAnswer) {
        if(cardName.equals(nameThatMatchesAnswer)){
            System.out.println("Correct answer.");
        } else if(!(nameThatMatchesAnswer.equals(defaultAnswer))){
            System.out.printf("Wrong answer. The correct one is \"%s\"", cardDefinition);
            System.out.printf(", you've just written the definition of \"%s\".\n", nameThatMatchesAnswer);
        } else {
            System.out.printf("Wrong answer. The correct one is \"%s\".\n", cardDefinition);
        }
    }
}
