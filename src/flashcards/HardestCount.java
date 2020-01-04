package flashcards;

import java.util.Set;

public interface HardestCount<E> {
    void add(E elem);

    void remove(E card);

    void clear();

    int getErrors(E elem);

    void setErrorCounter(E card, int errors);

    boolean contains(E elem);

    void countHardCards();

    int getMaxErrorCount();

    void printHardestCard();

    Set<E> toSet();
}
