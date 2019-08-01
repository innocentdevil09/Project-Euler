import java.util.HashSet;
import java.util.Set;

public class Problem7 {

    private static Set<Integer> primeNumbers = new HashSet<>();

    /**
     * Method to check if a given number is a prime number.
     * We use a memoizer in the form of primeNumbers set to reduce the time complexity of isPrimeNumber algo from
     * O(n) to O(log n)
     *
     * @param n
     */
    private static boolean isPrimeNumber(int n) {
        if (n <= 1) { return false; }
        for (int primeNumber : primeNumbers) {
            if (n % primeNumber == 0) {
                return false;
            }
        }
        if (n == 2) {
            primeNumbers.add(2);
            return true;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        primeNumbers.add(n);
        return true;
    }

    /**
     * Main method to get the 10001st prime number
     *
     * @param args
     */
    public static void main(String[] args) {
        int result = -1;
        for (int i = 2; ; i++) {
            if (isPrimeNumber(i) && primeNumbers.size() == 10001) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }
}
