import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashSet;
import java.util.Set;

public class Problem80 {

    private static final int PRECISION = 102;
    private static final MathContext MC = new MathContext(PRECISION);

    private static final int[] EXCLUDE = {1, 4, 9, 16, 25, 36, 49, 64, 81, 100};

    private static int squareRootDecimalDigitsSum(int n) {
        BigDecimal num = BigDecimal.valueOf(n);
        BigDecimal sqrt = num.sqrt(MC);
        String result = sqrt.toString();
        int sum = 0;
        for (int i = 0, j = 0; j < 100; i++, j++) {
            if (result.charAt(i) == '.') {
                j--;
                continue;
            }
            sum += result.charAt(i) - '0';
        }
        return sum;
    }

    /**
     * Main method.
     * Exclude the perfect square numbers. Using BigDecimal, we get the sqrt by defining mathematical precision
     * of 102 digits.
     * Using the above, we get the sum of all the digits.
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Set<Integer> set = new HashSet<>();
        for (int i : EXCLUDE) { set.add(i); }

        int total = 0;
        for (int i = 1; i <= 100; i++) {
            if (set.contains(i)) { continue; }
            total += squareRootDecimalDigitsSum(i);
        }
        System.out.println(total);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}
