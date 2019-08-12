import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Problem22 {

    private static final String PATH = "Problem 22/names.txt";

    /**
     * Method to return the list of all the names in the file
     *
     * @throws FileNotFoundException
     */
    private static List<String> getAllNames() throws FileNotFoundException {
        Scanner sc = new Scanner(new File(PATH));
        List<String> names = new ArrayList<>();

        sc.useDelimiter("[^a-zA-Z]+");
        while (sc.hasNext()) {
            names.add(sc.next());
        }
        return names;
    }

    public static void main(String[] args) throws FileNotFoundException {
        List<String> names = getAllNames();
        System.out.println("Size : " + names.size());
        Collections.sort(names);
        long result = 0;

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            long sum = name.chars().mapToObj(c -> (char) c).mapToInt(c -> c - 'A' + 1).sum();
            result += sum * (i + 1);
        }

        System.out.println(result);
    }
}
