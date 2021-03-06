import java.math.BigInteger;

public class Problem36 {

    /**
     * Main method to check if the number is palindrome in both base 10 and base 2 values
     *
     * @param args
     */
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i < 1000000; i++) {
            String str = String.valueOf(i);
            if (isPalindrome(str)) {
                BigInteger b = new BigInteger(str);
                String st = b.toString(2);
                if (isPalindrome(st)) {
                    sum += i;
                }
            }
        }
        System.out.println("Total sum of palindromes - " + sum);
    }

    /**
     * Checks number if it is a palindrome
     *
     * @param num
     */
    private static boolean isPalindrome(String num) {
        char[] digits = num.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = digits.length - 1; i >= 0; i--) {
            sb.append(Character.toString(digits[i]));
        }
        return sb.toString().equals(num);
    }
}