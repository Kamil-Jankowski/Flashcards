package flashcards;

import java.util.Scanner;

public class IO {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = new Logger();

    public static void println(String s){
        logger.add(s);
        System.out.println(s);
    }

    public static void println(){
        logger.add("");
        System.out.println();
    }

    public static String scan(){
        String newLine = scanner.nextLine();
        logger.add(newLine);
        return newLine;
    }

    public static void exportLog(String filePath){
        logger.logProgress(filePath);
    }
}
