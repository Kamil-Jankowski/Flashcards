package flashcards;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

class Logger {

    private List<String> lines = new ArrayList<>();

    public void add(String consoleLine){
        lines.add(consoleLine);
    }

    void logProgress(String filePath) {
        File file = new File(filePath);
        try (PrintWriter writer = new PrintWriter(file)){
            for (String line : lines) {
                writer.println(line);
            }
            IO.println("The log has been saved.\n");
        } catch (IOException e) {
            e.printStackTrace();
            IO.println("The log has NOT been saved.\n");
        }
    }
}
