import java.util.Arrays;

public class Problem70 {

    private static final int TEN_MILLION = (int) Math.pow(10, 7);

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
            if (totients[i] == i) {
                for (int j = i; j <= limit; j += i) {
                    totients[j] -= totients[j] / i;
                }
            }
        }
        return totients;
    }

    /**
     * Method to determine if the given two numbers are permutations of each other
     *
     * @param num1
     * @param num2
     */
    private static boolean isPermutation(int num1, int num2) {
        int[] numChars1 = new int[10];
        int[] numChars2 = new int[10];

        while (num1 > 0) {
            numChars1[num1 % 10]++;
            num1 /= 10;
        }
        while (num2 > 0) {
            numChars2[num2 % 10]++;
            num2 /= 10;
        }
        return Arrays.equals(numChars1, numChars2);
    }

    /**
     * Main method to get the result
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int[] totients = getTotients(TEN_MILLION);

        int result = -1;
        double minRatio = Double.MAX_VALUE;
        for (int i = 2; i <= TEN_MILLION; i++) {
            double ratio = (double) i / totients[i];
            if (isPermutation(i, totients[i]) && ratio < minRatio) {
                minRatio = ratio;
                result = i;
            }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
