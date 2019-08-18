import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Problem47 {

    private static final int THRESHOLD = 200;
    private static List<Integer> primes200 = new ArrayList<>();

    /**
     * Method to add prime numbers to the list
     */
    private static void getPrimes() {
        primes200.add(2);
        for (int i = 3; ; i += 2) {
            if (isPrimeNumber(i)) {
                primes200.add(i);
            }
            if (primes200.size() == THRESHOLD) {
                break;
            }
        }
    }

    /**
     * Method to check if a given number is a prime number
     *
     * @param n
     */
    private static boolean isPrimeNumber(int n) {
        if (n <= 1) { return false; }
        if (n == 2) { return true; }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to get prime factors of a given integer
     *
     * @param n
     */
    private static Set<Integer> getPrimeFactors(int n) {
        int temp = n;
        Set<Integer> set = new HashSet<>();

        for (int value : primes200) {
            while (temp % value == 0) {
                set.add(value);
                temp /= value;
            }
        }
        return set;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        getPrimes();
        int result;

        for (result = 1; ; result++) {
            if (getPrimeFactors(result).size() != 4) { continue; }
            if (getPrimeFactors(result + 1).size() != 4) { continue; }
            if (getPrimeFactors(result + 2).size() != 4) { continue; }
            if (getPrimeFactors(result + 3).size() != 4) { continue; }
            break;
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}