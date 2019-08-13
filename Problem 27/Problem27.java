public class Problem27 {

    /**
     * Method to check if a given number is a prime number
     *
     * @param num
     */
    private static boolean isPrimeNumber(int num) {
        if (num < 2) { return false; }
        if (num == 2) { return true; }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if the given val is an integer
     *
     * @param val
     */
    private static boolean isInteger(double val) {
        return (int) val == val;
    }

    /**
     * For a given pair of coefficients to the equation : n^2 + a.n + b
     * it runs a loop over all values of i >= 0 and determine the largest value of i for which the val given by the
     * equation is not a prime number
     *
     * @param a
     * @param b
     */
    private static int getNoOfPrimes(int a, int b) {
        int count = 0;
        for (int i = 0; ; i++) {
            int val = (i * i) + (i * a) + b;
            if (!isPrimeNumber(val)) {
                break;
            }
            count++;
        }
        return count;
    }

    /**
     * Main method.
     * Equation: n^2 + a.n + b
     *
     * From the given examples of
     * 1. n^2 + n + 41
     * 2. n^2 - 79n + 1601
     * comes the relation => a^2 - 4 * b = -163
     *
     * Using this relation we iterate over all the values of "a" within the range -999 to 999 and derive the values
     * of "b"
     * Using these coefficients, we can easily get the maxNo of primes given for the equation with coefficients "a" &
     * "b"
     *
     * @param args
     */
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int maxPrimes = 0, product = -1;
        for (int a = -999; a < 1000; a++) {
            double b = (double) ((a * a) + 163) / 4;
            if (b < -999 || b > 999 || !isInteger(b)) {
                continue;
            }
            int noOfPrimes = getNoOfPrimes(a, (int) b);
            if (noOfPrimes > maxPrimes) {
                maxPrimes = noOfPrimes;
                product = (int) (a * b);
            }
        }
        System.out.println(product);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}