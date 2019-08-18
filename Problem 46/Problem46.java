public class Problem46 {

    private static final int MAX = 6000;
    private static boolean[] primes = new boolean[MAX];

    /**
     * Method to populate a list of prime numbers in boolean form in a very efficient way
     */
    private static void listOfPrimes() {
        primes[2] = true;
        for (int i = 3; i < MAX; i += 2) {
            primes[i] = true;
        }
        for (int k = 3; k * k <= MAX; k += 2) {
            if (primes[k]) {
                for (int j = k * k; j < MAX; j += 2 * k) {
                    primes[j] = false;
                }
            }
        }
    }

    /**
     * Method to check if the given number is a GoldBach number
     *
     * @param n
     */
    private static boolean isGoldBachNumber(int n) {
        for (int i = 3; i < n; i++) {
            if (primes[i]) {
                double k = Math.sqrt((n - i) / 2);
                if (k == (int) k) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        listOfPrimes();

        int result;
        for (result = 3; result < MAX; result += 2) {
            if (!primes[result] && !isGoldBachNumber(result)) {
                break;
            }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
