package flashcards;

import java.io.*;
import java.util.*;

class CardBox {
    private HashMap<String, String> cards = new LinkedHashMap<>();

    void add(Card card) {
        cards.putIfAbsent(card.getName(), card.getDefinition());
    }

    private void addOrUpdate(Card card) {
        cards.put(card.getName(), card.getDefinition());
    }

    Card getCard(String name){
        return new Card(name, cards.get(name));
    }

    boolean containsValue(String value){
        if (value != null){
            return cards.containsValue(value);
        }
        return false;
    }

    String getCardNameOrDefault(String value, String defaultValue) {
        for (String name : cards.keySet()) {
            if (getCard(name).getDefinition().equals(value)) {
                return name;
            }
        }
        return defaultValue;
    }

    Set<String> toSet() {
        return cards.keySet();
    }

    ArrayList<String> list(){
        return new ArrayList<>(cards.keySet());
    }

    void remove(String card){
        if (toSet().contains(card)) {
            cards.remove(card);
            System.out.println("The card has been removed.\n");
        } else {
            System.out.println(String.format("Can't remove \"%s\": there is no such card.\n", card));
        }
    }

    void importCards(String path) {
        File file = new File(path);
        int amount = 0;
        try (Scanner reader = new Scanner(file)){
            while (reader.hasNext()){
                String inputLine = reader.nextLine();
                String[] pair = inputLine.split(" : ");
                Card card = new Card(pair[0], pair[1]);
                addOrUpdate(card);
                amount++;
            }
            System.out.println(String.format("%d cards have been loaded.\n", amount));
        } catch(FileNotFoundException e){
            System.out.println("File not found.\n");
        } catch (ArrayIndexOutOfBoundsException ex){
            System.out.println("Wrong file formatting.\n");
        }
    }

    void exportCards(String path) {
        File file = new File(path);
        try (PrintWriter writer = new PrintWriter(file)){
            for (String card : toSet()){
                String definition = cards.get(card);
                writer.println(String.format("%s : %s", card, definition));
            }
            System.out.println(String.format("%d cards have been saved.\n", cards.size()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
