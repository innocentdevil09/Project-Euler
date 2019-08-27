import java.math.BigInteger;

public class Problem55 {

    /**
     * Method to check if a given number is a palindrome number
     *
     * @param num
     */
    private static boolean isPalindrome(String num) {
        if (num == null) { throw new IllegalArgumentException("Null input parameter!"); }
        int length = num.length() - 1;

        for (int i = 0; i < num.length() / 2; i++) {
            if (num.charAt(i) != num.charAt(length - i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to check if a given number is a Lychrel number
     * A number that never forms a palindrome through the reverse and add process is called a Lychrel number
     *
     * @param num
     */
    private static boolean isLychrelNumber(BigInteger num) {
        for (int i = 0; i < 50; i++) {
            num = num.add(reverseOf(num));

            if (isPalindrome(num.toString())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to return reverse of a number
     *
     * @param num
     */
    private static BigInteger reverseOf(BigInteger num) {
        StringBuilder sb = new StringBuilder(num.toString());
        sb.reverse();
        return new BigInteger(sb.toString());
    }

    /**
     * Main method to execute the code
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        int count = 0;
        for (int i = 1; i < 10000; i++) {
            if (isLychrelNumber(new BigInteger(String.valueOf(i)))) {
                count++;
            }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}