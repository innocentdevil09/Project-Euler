import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem42 {

    private static final String PATH = "Problem 42/words.txt";

    /**
     * Method to read all the words in the given file and return as list of words
     */
    private static List<String> getWords() {
        List<String> words = new ArrayList<>();

        try {
            Scanner sc = new Scanner(new File(PATH));
            sc.useDelimiter("[^a-zA-Z]+");

            while (sc.hasNext()) {
                words.add(sc.next().trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * Method to get the score of the word by adding numeric value of each character.
     * Check if the score is a triangular number
     *
     * @param word
     */
    private static boolean isTriangleWord(String word) {
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            score += word.charAt(i) - 'A' + 1;
        }

        for (int j = 1; j < score + 1; j++) {
            if (score == ((j * (j + 1)) / 2)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int count = 0;

        List<String> words = getWords();
        for (String word : words) {
            if (isTriangleWord(word)) {
                count++;
            }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
