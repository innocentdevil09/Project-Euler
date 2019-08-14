import java.util.HashSet;
import java.util.Set;

public class Problem32 {

    /**
     * Method to check if a given number is a pan-digital number
     * A pan-digital number is one which contains all the digits from 1 to 9 exactly once e.g. 724658913
     *
     * @param num
     */
    private static boolean isPanDigital(String num) {
        if (num.length() != 9) {
            return false;
        }
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < num.length(); i++) {
            int digit = num.charAt(i) - '0';
            if (digit != 0) {
                set.add(digit);
            }
        }
        return set.size() == 9;
    }

    /**
     * Main method to get the sum of all the products where multiplier, multiplicand and product together makes a
     * pan-digital number
     * <p>
     * We limit a to 10000 because in total the length of (a + b + product) can never exceed 9
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Set<Integer> products = new HashSet<>();

        for (int a = 1; a < 10000; a++) {
            for (int b = 1; b < a; b++) {
                int product = a * b;
                String num = String.valueOf(product) + a + b;
                if (num.length() > 9) { break; }
                if (isPanDigital(num)) {
                    products.add(product);
                }
            }
        }
        int result = products.stream().mapToInt(p -> p).sum();
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
