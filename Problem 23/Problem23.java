import java.util.HashSet;
import java.util.Set;

public class Problem23 {

    private static boolean[] isAbundant = new boolean[28124];

    /**
     * Method to return sum of all the factors of a given number.
     * The list of factors does not include the number itself
     *
     * @param n
     */
    private static int getFactorsSum(int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        if (n == 0 || n == 1) { return 0; }
        Set<Integer> factors = new HashSet<>();
        factors.add(1);

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                factors.add(i);
                factors.add(n / i);
            }
        }
        return factors.stream().mapToInt(f -> f).sum();
    }

    /**
     * Method to check if a given number is an abundant number
     * <p>
     * abundant number : sum of factors of num > num
     * e.g. 12 => 1, 2, 3, 4, 6 (sum = 16)
     *
     * @param n
     */
    private static boolean isAbundant(int n) {
        if (isAbundant[n]) {
            return true;
        } else if (getFactorsSum(n) > n) {
            isAbundant[n] = true;
        }

        return isAbundant[n];
    }

    /**
     * Method to determine if the current number be represented as sum of two abundant numbers
     *
     * @param n
     */
    private static boolean isSumOfAbundants(int n) {
        for (int i = 1; i <= n; i++) {
            if (isAbundant(i) && isAbundant(n - i)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        long sum = 0;
        for (int i = 1; i <= 28123; i++) {
            if (!isSumOfAbundants(i)) {
                sum += i;
            }
        }
        System.out.println(sum);
    }
}
