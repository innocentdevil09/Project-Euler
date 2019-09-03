public class Problem69 {

    private static final int ONE_MILLION = 1000000;

    /**
     * In number theory, Euler's totient function counts the positive integers up to a given integer n that are
     * relatively prime to n. It is written using the Greek letter phi as φ(n) or ϕ(n), and may also be called Euler's
     * phi function. In other words, it is the number of integers k in the range 1 ≤ k ≤ n for which the greatest common
     * divisor gcd(n, k) is equal to 1.
     * <p>
     * Algo :-
     * If n has the factor 2 every 2nd number will not be relatively prime to n
     * if n has the factor 3 every 3rd number will not be relatively prime to n
     * and so on....
     *
     * @param limit
     */
    private static int[] getTotients(int limit) {
        int[] totients = new int[limit + 1];
        for (int i = 0; i <= limit; i++) {
            totients[i] = i;
        }

        for (int i = 2; i <= limit; i++) {
            if (totients[i] == i) { // i is prime
                for (int j = i; j <= limit; j += i) {
                    totients[j] -= totients[j] / i;
                }
            }
        }
        return totients;
    }

    /**
     * Main method to get result with max ratio of n/φ(n)
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int result = -1;
        double maxRatio = -1d;
        int[] totients = getTotients(ONE_MILLION);
        for (int i = 2; i <= ONE_MILLION; i++) {
            double ratio = (double) i / totients[i];
            if (ratio > maxRatio) {
                maxRatio = ratio;
                result = i;
            }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
