package flashcards;

public enum Parameters {
    IMPORT("-import"),
    EXPORT("-export");

    private String value;

    Parameters(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
