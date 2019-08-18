import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem43 {
    /**
     * Get the number containing 0 to 9 in a form of string
     */
    private static String getNumber() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= 9; i++) {
            sb.append(i);
        }
        return sb.toString();
    }

    /**
     * Get all the permutations of a given string in a list
     * Time complexity - O(n * n!)
     *
     * @param s
     */
    private static List<String> permutation(String s) {
        List<String> result = new ArrayList<>();

        if (s.length() == 0) {
            result.add(s);
        } else {
            int lastIndex = s.length() - 1;
            String lastString = s.substring(lastIndex);
            String remString = s.substring(0, lastIndex);

            result = merge(permutation(remString), lastString);
        }
        return result;
    }

    /**
     * Merges the string str at all positions in the string to get all permutations
     *
     * @param list
     * @param str
     */
    private static List<String> merge(List<String> list, String str) {
        List<String> strList = new ArrayList<>();

        for (String s : list) {
            for (int i = 0; i <= s.length(); i++) {
                String ps = new StringBuffer(s).insert(i, str).toString();
                strList.add(ps);
            }
        }
        return strList;
    }

    /**
     * Check if the sub-strings are divisible by prime numbers
     *
     * @param s
     */
    private static boolean isSubStringDivisible(String s) {
        List<Integer> primeNumbers = Arrays.asList(2, 3, 5, 7, 11, 13, 17);

        for (int i = 0; i < s.length() - 3; i++) {
            if (!isDivisible(s, i + 1, i + 2, i + 3, primeNumbers.get(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check the divisibility of the sub-string
     *
     * @param s
     * @param a
     * @param b
     * @param c
     * @param n
     */
    private static boolean isDivisible(String s, int a, int b, int c, int n) {
        String sb = String.valueOf(s.charAt(a)) + s.charAt(b) + s.charAt(c);
        int val = Integer.parseInt(sb);
        return val % n == 0;
    }

    public static void main(String[] args) {
        BigInteger sum = BigInteger.ZERO;
        long startTime = System.currentTimeMillis();

        String num = getNumber();
        List<String> allNumbers = permutation(num);
        for (String n : allNumbers) {
            if (isSubStringDivisible(n)) {
                sum = sum.add(new BigInteger(n));
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Total sum - " + sum.toString());
        System.out.println("Total time taken - " + (endTime - startTime));
    }
}