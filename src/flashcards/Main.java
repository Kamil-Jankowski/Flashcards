package flashcards;

public class Main {
    private static CardBox boxOfCards = new CardBox();
    private static HardestCount<Card> statistics = new HardestCards<>();
    private static CardManager manager = new CardManager(boxOfCards, statistics);

    public static void main(String[] args) {
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
                    IO.println("Bye bye!\n");
                    break;
                default:
                    IO.println("Wrong input provided.\n");
            }
        } while (!("exit".equals(userInput)));
    }
}
