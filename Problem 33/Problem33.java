import java.util.ArrayList;
import java.util.List;

public class Problem33 {

    private static int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    /**
     * Method to get all denominators for a given numerator 'ab' such that the denominator contains atleast one
     * common digit overlapping with numerator
     *
     * @param a
     * @param b
     */
    private static List<Integer> getDenominators(int a, int b) {
        List<Integer> denominators = new ArrayList<>();

        int numerator = Integer.parseInt("" + a + b);

        for (int i = 1; i < digits.length; i++) {
            if (digits[i] == a || digits[i] == b) { continue; }
            int denominator1 = Integer.parseInt("" + a + digits[i]);
            int denominator2 = Integer.parseInt("" + b + digits[i]);
            if (denominator1 > numerator) {
                denominators.add(denominator1);
            }
            if (denominator2 > numerator) {
                denominators.add(denominator2);
            }
        }
        return denominators;
    }

    /**
     * Method to check if the given fraction is a non-trivial fraction.
     * Non-trivial fraction e.g
     * 49 / 98 == 4 / 8
     * The fractions have the same value
     * <p>
     * Fractions like 30 / 50 == 3 / 5 are called trivial fractions
     *
     * @param numerator
     * @param denominator
     */
    private static boolean isNonTrivialFraction(int numerator, int denominator) {
        double origVal = (double) numerator / denominator;
        String num = String.valueOf(numerator);
        String den = String.valueOf(denominator);

        String common = null;
        for (int i = 0; i < num.length(); i++) {
            if (den.contains(String.valueOf(num.charAt(i)))) {
                common = String.valueOf(num.charAt(i));
            }
        }
        num = num.replace(common, "");
        den = den.replace(common, "");

        double newVal = Double.valueOf(num) / Double.valueOf(den);
        return origVal == newVal;
    }

    /**
     * Method to get greatest common divisor
     *
     * @param a
     * @param b
     */
    private static int getGcd(int a, int b) {
        if (a < b) {
            int temp = a;
            a = b;
            b = temp;
        }

        while (a % b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return b;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int numProduct = 1, denProduct = 1;

        for (int i = 1; i < digits.length; i++) {
            for (int j = 1; j < digits.length; j++) {
                if (digits[i] == digits[j]) { continue; }
                int numerator = Integer.parseInt("" + digits[i] + digits[j]);
                List<Integer> denominators = getDenominators(digits[i], digits[j]);

                for (int denominator : denominators) {
                    if (isNonTrivialFraction(numerator, denominator)) {
                        numProduct *= numerator;
                        denProduct *= denominator;
                    }
                }
            }
        }
        int gcd = getGcd(numProduct, denProduct);
        System.out.println(denProduct / gcd);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}