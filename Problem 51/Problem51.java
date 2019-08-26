import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem51 {

    /**
     * Method to determine if a given number is prime
     *
     * @param n
     */
    private static boolean isPrime(int n) {
        if (n <= 1) { return false; }
        if (n == 2) { return true; }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return positions of digit that occurs exactly 3 times in the given integer
     *
     * @param num
     */
    private static List<Integer> hasThreeSameDigits(String num) {
        Map<Integer, List<Integer>> countedSet = new HashMap<>();
        int posNum = -1;
        for (int i = 0; i < num.length(); i++) {
            List<Integer> val = countedSet.containsKey(num.charAt(i) - '0') ? countedSet.get(num.charAt(i) - '0') :
                    new ArrayList<>();
            val.add(i);
            countedSet.put(num.charAt(i) - '0', val);
            if (countedSet.get(num.charAt(i) - '0').size() > 3) {
                return null;
            }
            if (countedSet.get(num.charAt(i) - '0').size() == 3) {
                posNum = num.charAt(i) - '0';
            }
        }
        if (posNum == -1) {
            return null;
        }
        return countedSet.get(posNum);
    }

    /**
     * Method to replace the given positions in the integer num by the replace number
     *
     * @param num
     * @param pos
     * @param replace
     */
    private static int replaceDigits(int num, List<Integer> pos, int replace) {
        char[] digits = String.valueOf(num).toCharArray();
        digits[pos.get(0)] = (char) (replace + '0');
        digits[pos.get(1)] = (char) (replace + '0');
        digits[pos.get(2)] = (char) (replace + '0');
        return Integer.parseInt(String.valueOf(digits));
    }

    /**
     * Main method to get the smallest prime number which is part of eight prime family which is got by replacing the
     * digits
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int result;
        int count = 0;
        for (result = 1000; ; result++) {
            count = 0;
            List<Integer> pos = hasThreeSameDigits(String.valueOf(result));
            if (pos == null) { continue; }
            for (int i = 0; i < 7; i++) {
                int replacedDigit = replaceDigits(result, pos, i);
                if (String.valueOf(replacedDigit).length() == String.valueOf(result).length() && isPrime(
                        replacedDigit)) {
                    count++;
                }
            }
            if (isPrime(replaceDigits(result, pos, 7))) { count++; }
            if (count == 8) { break; }
            if (isPrime(replaceDigits(result, pos, 8))) { count++; }
            if (count == 8) { break; }
            if (isPrime(replaceDigits(result, pos, 9))) { count++; }
            if (count == 8) { break; }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
