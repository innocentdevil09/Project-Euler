import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem74 {

    /**
     * Class variable to memoize the factorials of each digit -- to be used again and again
     */
    private static Map<Integer, BigInteger> factorialMap = new HashMap<>();

    /**
     * Method to store factorials of each digit in the map
     */
    private static void populateFactorialMap() {
        for (int i = 0; i < 10; i++) {
            BigInteger factorial = BigInteger.ONE;

            for (int j = i; j > 0; j--) {
                factorial = factorial.multiply(BigInteger.valueOf(j));
            }
            factorialMap.put(i, factorial);
        }
    }

    /**
     * Method to return the sum of digit factorials of the given number
     *
     * @param num
     */
    private static BigInteger getDigitFactorial(String num) {
        BigInteger digitFact = BigInteger.ZERO;

        for (char ch : num.toCharArray()) {
            digitFact = digitFact.add(factorialMap.get(ch - '0'));
        }
        return digitFact;
    }

    /**
     * Method to detect loop in the chain of numbers formed by getting the digit sum factorials for each number
     * Returns the size of the chain
     *
     * @param num
     */
    private static int getChainSize(int num) {
        Set<String> set = new HashSet<>();
        String number = String.valueOf(num);
        set.add(number);

        while (true) {
            BigInteger digitFactorial = getDigitFactorial(number);
            if (set.contains(digitFactorial.toString())) { break; }
            set.add(digitFactorial.toString());
            number = digitFactorial.toString();
        }
        return set.size();
    }

    /**
     * Main method to get the count of chains with size 60
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        populateFactorialMap();

        int count = 0;
        for (int i = 1; i < 1000000; i++) {
            int size = getChainSize(i);
            if (size == 60) { count++; }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
