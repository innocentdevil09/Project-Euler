import java.util.HashMap;
import java.util.Map;

public class Problem49 {
    /**
     * Method to check if a given integer is a prime number
     *
     * @param n
     */
    private static boolean isPrimeNumber(int n) {
        if (n == 2) { return true; }
        if (n < 2 || n % 2 == 0) { return false; }
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the given two integers are permutations of each other. Algo:
     * 1. Create a map containing number of times each single digit appears in it
     * 2. Check if both the maps contains same single digits and occurrences
     *
     * @param a
     * @param b
     */
    private static boolean isPermutation(int a, int b) {
        Map<String, Integer> aMap = getMap(String.valueOf(a));
        Map<String, Integer> bMap = getMap(String.valueOf(b));

        for (String s : aMap.keySet()) {
            if (!bMap.containsKey(s)) { return false; }
            if (aMap.get(s) != bMap.get(s)) { return false; }
        }
        return true;
    }

    /**
     * Creates a map of the integer represented as a string. The map contains each single digit of the integer and
     * its number of occurrences
     *
     * @param str
     */
    private static Map<String, Integer> getMap(String str) {
        Map<String, Integer> map = new HashMap<>();
        for (String s : str.split("")) {
            if (!map.containsKey(s)) {
                map.put(s, 1);
            } else {
                int count = map.get(s);
                map.put(s, count + 1);
            }
        }
        return map;
    }

    /**
     * Main method to get the integers which are prime as well as permutations of each other
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[] array = new int[3];

        for (int i = 1000; i < 10000; i++) {
            if (i == 1487) { continue; }
            if (isPrimeNumber(i)) {
                if (isPrimeNumber(i + 3330) && isPrimeNumber(i + 6660) && isPermutation(i, i + 3330) && isPermutation(i,
                        i + 6660)) {
                    array[0] = i;
                    array[1] = i + 3330;
                    array[2] = i + 6660;
                }
            }
        }
        String result = String.valueOf(array[0]) + String.valueOf(array[1]) + String.valueOf(array[2]);
        long endTime = System.currentTimeMillis();

        System.out.println("Result - " + result);
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}