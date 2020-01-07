package flashcards;

import static flashcards.Parameters.*;

public class Main {
    private static CardBox boxOfCards = new CardBox();
    private static HardestCount<Card> statistics = new HardestCards<>();
    private static CardManager manager = new CardManager(boxOfCards, statistics);

    public static void main(String[] args) {

        if (args.length == 2 || args.length == 4){
            for (int i = 0; i < args.length-1; i++){
                if (args[i].equals(IMPORT.toString())){
                    manager.importCardsFromArgs(args[i+1]);
                }
            }
        } else {
            IO.println("Correct command is: ClassName.java -import <filename> -export <filename>");
            IO.println("No cards have been loaded\n");
        }

            String userInput;
            do {
                IO.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
                userInput = IO.scan();
                switch (userInput){
                    case "add":
                        manager.addSingleCard();
                        break;
                    case "remove":
                        manager.removeSingleCard();
                        break;
                    case "import":
                        manager.importCards();
                        break;
                    case "export":
                        manager.exportCards();
                        break;
                    case "ask":
                        manager.askAQuestion();
                        break;
                    case "log":
                        manager.log();
                        break;
                    case "hardest card":
                        statistics.printHardestCard();
                        break;
                    case "reset stats":
                        statistics.clear();
                        break;
                    case "exit":
                        if (args.length == 2 || args.length == 4){
                            for (int i = 0; i < args.length-1; i++){
                                if (args[i].equals(EXPORT.toString())){
                                    manager.exportCardsFromArgs(args[i+1]);
                                    return;
                                }
                            }
                        }
                        IO.println("Bye bye!\n");
                        break;
                    default:
                        IO.println("Wrong input provided.\n");
                }
            } while (!("exit".equals(userInput)));
    }
}
