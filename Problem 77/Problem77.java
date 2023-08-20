public class Problem77 {

    /**
     * Method to return boolean array marking all prime numbers between 1 to n.
     *
     * @param n
     * @return Boolean array marking all prime numbers.
     */
    private static boolean[] primes(int n) {
        boolean[] primes = new boolean[n + 1];
        if (n >= 2) {
            primes[2] = true;
        }

        for (int i = 3; i <= n; i += 2) {
            primes[i] = true;
        }

        for (int k = 3; k * k <= n; k += 2) {
            if (primes[k]) {
                for (int j = k * k; j <= n; j += 2 * k) {
                    primes[j] = false;
                }
            }
        }
        return primes;
    }

    /**
     * To represent any integer as summation of two prime numbers:
     * Integer 4: 2+2
     * Integer 5: 2+3
     * Integer 6: 2+2+2, 3+3
     * Integer 7: 2+2+3, 2+5
     * Integer 8: 2+2+2+2, 2+3+3, 3+5
     * Integer 9: 2+2+2+3, 2+2+5, 2+7, 3+3+3
     * Integer 10: 2+2+2+2+2, 2+2+3+3, 5+5, 3+7, 2+3+5
     * etc.
     *
     * We use dynamic programming to get the count of possible combinations.
     * Intuition: Take a prime number, and increment count for all integer N where the prime number be present in a combination.
     *
     * @param n
     * @param primes
     * @return
     */
    private static int summationCountForPrime(int n, boolean[] primes) {
        int[] cache = new int[n + 1];
        cache[0] = 1;
        for (int i = 1; i <= n; i++) {
            if (primes[i]) {
                for (int j = i; j <= n; j++) {
                    cache[j] += cache[j - i];
                }
            }
        }
        return cache[n];
    }

    /**
     * Main method.
     * The problem statement includes counting all possible combinations, where integer N can be represented as sum of prime numbers.
     *
     * The solution involves using dynamic programming, as it requires using all combinations once.
     * @param args
     */
    public static void main(String[] args) {
        int n = 71;
        boolean[] primes = primes(n);
        int count = summationCountForPrime(n, primes);
        System.out.println(count);
    }
}
