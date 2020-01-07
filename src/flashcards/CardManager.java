package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

class CardManager {

    private CardBox cardBox;
    private CardMaker cardMaker;
    private String cardName, cardDefinition, defaultAnswer;
    private HardestCount<Card> statistics;

    CardManager(CardBox boxOfCards, HardestCount<Card> statistics) {
        this.cardBox = boxOfCards;
        this.statistics = statistics;
        cardMaker = new CardMaker(cardBox);
        defaultAnswer = "No card name match the definition you provided.";
    }

    void addSingleCard() {
        IO.println("The card:");
        cardName = IO.scan();

        if (cardBox.toSet().contains(cardName)){
            IO.println(String.format("The card \"%s\" already exists.\n", cardName));
            return;
        } else {
            IO.println("The definition of the card:");
            cardDefinition = IO.scan();
            if (cardBox.containsValue(cardDefinition)) {
                IO.println(String.format("The definition \"%s\" already exists.\n", cardDefinition));
                return;
            }
        }

        cardMaker.createCard(cardName, cardDefinition);
        IO.println(String.format("The pair (\"%s\":\"%s\") has been added.\n", cardName, cardDefinition));
    }

    void removeSingleCard() {
        IO.println("The card:");
        String cardToBeRemoved = IO.scan();
        cardBox.remove(cardToBeRemoved);
    }

    void importCards() {
        File file = new File(setFilePath());
        importFromFile(file);
    }

    void importCardsFromArgs(String fileName){
        File file = new File("./" + fileName);
        importFromFile(file);
    }

    private void importFromFile(File file) {
        int amount = 0;
        try (Scanner reader = new Scanner(file)) {
            while (reader.hasNext()) {
                String inputLine = reader.nextLine();
                String[] input = inputLine.split(" : ");
                Card card = new Card(input[0], input[1]);
                int errors = Integer.parseInt(input[2]);
                cardBox.addOrUpdate(card);
                statistics.toSet().removeIf(next -> next.getName().equals(card.getName()));
                statistics.setErrorCounter(card, errors);
                amount++;
            }
            IO.println(String.format("%d cards have been loaded.\n", amount));
        } catch (FileNotFoundException e) {
            IO.println("File not found.\n");
        } catch (ArrayIndexOutOfBoundsException ex) {
            IO.println("Wrong file formatting.\n");
        }
    }

    void exportCards() {
        File file = new File(setFilePath());
        exportToFile(file);
    }

    public void exportCardsFromArgs(String fileName) {
        File file = new File("./" + fileName);
        exportToFile(file);
    }

    private void exportToFile(File file) {
        try (PrintWriter writer = new PrintWriter(file)){
            for (String cardName : cardBox.toSet()){
                Card card = cardBox.getCard(cardName);
                String definition = card.getDefinition();
                int errors = statistics.getErrors(card);
                writer.println(String.format("%s : %s : %d", cardName, definition, errors));
            }
            IO.println(String.format("%d cards have been saved.\n", cardBox.size()));
        } catch (IOException e) {
            IO.println("Error during export occurred\n");
        }
    }

    public void log() {
        IO.exportLog(setFilePath());
    }

    private String setFilePath() {
        IO.println("File name:");
        String fileName = IO.scan();
        return "./" + fileName;
    }

    void askAQuestion() {
        List<String> cardNames = cardBox.list();
        int deckSize = cardNames.size();
        Random rand = new Random();
        if (deckSize != 0) {
            IO.println("How many times to ask?");
            int amountOfQuestions = Integer.parseInt(IO.scan());

            for (int i = 0; i < amountOfQuestions; i++) {
                int randomCard = rand.nextInt(deckSize);
                cardName = cardNames.get(randomCard);
                cardDefinition = cardBox.getCard(cardName).getDefinition();

                IO.println(String.format("Print the definition of \"%s\":", cardName));

                String answer = IO.scan();
                String nameThatMatchesAnswer = cardBox.getCardNameOrDefault(answer, defaultAnswer);

                checkAnswer(cardName, nameThatMatchesAnswer);
            }
        } else {
            IO.println("No cards available");
        }
        IO.println();
    }

    private void checkAnswer(String cardName, String nameThatMatchesAnswer) {
        if(cardName.equals(nameThatMatchesAnswer)){
            IO.println("Correct answer.");
        } else if(!(nameThatMatchesAnswer.equals(defaultAnswer))){
            statistics.add(cardBox.getCard(cardName));
            IO.println(String.format("Wrong answer. The correct one is \"%s\", you've just written the definition of \"%s\".", cardDefinition, nameThatMatchesAnswer));
        } else {
            statistics.add(cardBox.getCard(cardName));
            IO.println(String.format("Wrong answer. The correct one is \"%s\".", cardDefinition));
        }
    }
}
