package flashcards;

import java.util.*;

public class HardestCards<E> implements HardestCount<E> {

    private Map<E, Integer> errorCounter = new HashMap<>();
    private List<E> hardCards = new ArrayList<>();

    @Override
    public void add(E card){
        if(errorCounter.containsKey(card)){
            errorCounter.put(card, errorCounter.get(card) + 1);
        } else {
            errorCounter.putIfAbsent(card, 1);
        }
    }

    @Override
    public void remove(E card){
        errorCounter.remove(card);
    }

    @Override
    public void clear() {
        // using for with iterator:
        for (Iterator<E> it = toSet().iterator(); it.hasNext();) {
            E next = it.next();
            if(next != null) {
                it.remove();
            }
        }
        // using Collection.removeIf() with method expression:
        hardCards.removeIf(Objects::nonNull);

        IO.println("Card statistics has been reset.\n");
    }


    @Override
    public int getErrors(E elem) {
        return errorCounter.getOrDefault(elem, 0);
    }

    @Override
    public void setErrorCounter(E card, int errors){
        errorCounter.put(card, errors);
    }

    @Override
    public boolean contains(E elem) {
        return getErrors(elem) > 0;
    }

    @Override
    public int getMaxErrorCount() {
        int max = Integer.MIN_VALUE;
        for (Integer counter : errorCounter.values()){
            max = counter > max ? counter : max;
        }
        return max;
    }

    @Override
    public void countHardCards() {
        hardCards.clear();
        for (E card : toSet()){
            if (errorCounter.get(card) == getMaxErrorCount()) {
                hardCards.add(card);
            }
        }
    }

    @Override
    public void printHardestCard() {
        countHardCards();
        int hardestCardCount = hardCards.size();
        int errorAmount = getMaxErrorCount();
        StringBuilder hardestCardLogger = new StringBuilder();

        if (hardestCardCount == 1) {
            hardestCardLogger.append(String.format("The hardest card is \"%s\". ", ((Card) hardCards.get(0)).getName()));
            if (errorAmount == 1) {
                hardestCardLogger.append("You have 1 error answering it.\n");
            } else {
                hardestCardLogger.append(String.format("You have %d errors answering it.\n", errorAmount));
            }
        } else if (hardestCardCount > 1) {
            hardestCardLogger.append("The hardest cards are ");
            for (int i = 0; i < hardestCardCount; i++) {
                if (i == hardestCardCount - 1) {
                    hardestCardLogger.append(String.format("\"%s\". ", ((Card) hardCards.get(i)).getName()));
                } else {
                    hardestCardLogger.append(String.format("\"%s\", ", ((Card) hardCards.get(i)).getName()));
                }
            }
            if (errorAmount == 1) {
                hardestCardLogger.append("You have 1 error answering it.\n");
            } else {
                hardestCardLogger.append(String.format("You have %d errors answering it.\n", errorAmount));
            }

        } else {
            hardestCardLogger.append("There are no cards with errors.\n");
        }

        IO.println(hardestCardLogger.toString());
    }

    @Override
    public Set<E> toSet() {
        return errorCounter.keySet();
    }
}
