import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Problem59 {

    /**
     * Method to decrypt the given message by finding the key.
     * Please refer this article to understand the algo :- https://en.wikipedia.org/wiki/Frequency_analysis
     * <p>
     * Algo :
     * Divide the message into three pairs and assign the maximum occuring integer to the key array
     *
     * @param encrypted
     * @param max
     */
    private static int[] decryptMessage(List<Integer> encrypted, int max) {
        int[][] message = new int[3][max + 1];
        int[] key = new int[3];

        for (int i = 0; i < encrypted.size(); i++) {
            int index = i % key.length;
            message[index][encrypted.get(i)]++;
            if (message[index][encrypted.get(i)] > message[index][key[index]]) {
                key[index] = encrypted.get(i);
            }
        }
        int spaceAscii = 32;
        for (int i = 0; i < key.length; i++) {
            key[i] = key[i] ^ spaceAscii;
        }
        return key;
    }

    /**
     * Method to convert the encrypted message to its string message
     *
     * @param encrypted
     * @param key
     */
    private static String getMessage(List<Integer> encrypted, int[] key) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < encrypted.size(); i++) {
            sb.append((char) (encrypted.get(i) ^ key[i % key.length]));
        }
        return sb.toString();
    }

    /**
     * Method to get sum of the ascii characters in the decrypted message
     *
     * @param encrypted
     * @param key
     */
    private static int getSum(List<Integer> encrypted, int[] key) {
        int sum = 0;

        for (int i = 0; i < encrypted.size(); i++) {
            sum += encrypted.get(i) ^ key[i % key.length];
        }
        return sum;
    }

    /**
     * Main method to read the input and decrypt the message
     *
     * @param args
     *
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        String path = "Problem 59/cipher.txt";
        Scanner sc = new Scanner(new File(path));
        sc.useDelimiter("[^a-zA-Z0-9]");

        List<Integer> encrypted = new ArrayList<>();
        int max = -1;
        while (sc.hasNext()) {
            int input = Integer.parseInt(sc.next());
            encrypted.add(input);
            if (input > max) {
                max = input;
            }
        }

        int[] key = decryptMessage(encrypted, max);
        System.out.println(getMessage(encrypted, key));
        System.out.println(getSum(encrypted, key));
    }
}