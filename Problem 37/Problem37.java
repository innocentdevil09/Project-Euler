public class Problem37 {

    private static final int MAX = 1000000;
    private static boolean[] primes = new boolean[MAX + 1];

    static {
        listOfPrimes();
    }

    /**
     * Method to list down all the primes below MAX before start of the application
     */
    private static void listOfPrimes() {
        primes[2] = true;
        for (int i = 3; i <= MAX; i += 2) {
            primes[i] = true;
        }

        for (int k = 3; k * k <= MAX; k += 2) {
            if (primes[k]) {
                for (int j = k * k; j <= MAX; j += 2 * k) {
                    primes[j] = false;
                }
            }
        }
    }

    /**
     * Method to check if the given number is a truncatable prime
     *
     * @param num
     */
    private static boolean isTruncatablePrime(String num) {
        if (!primes[Integer.parseInt(num)]) {
            return false;
        }
        for (int i = 1; i < num.length(); i++) {
            String num1 = num.substring(i);
            String num2 = num.substring(0, num.length() - i);
            if (!primes[Integer.parseInt(num1)] || !primes[Integer.parseInt(num2)]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int sum = 0;

        for (int i = 11; i < MAX; i++) {
            if (isTruncatablePrime(String.valueOf(i))) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
