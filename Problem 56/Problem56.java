import java.math.BigInteger;

public class Problem56 {

    /**
     * Method to get sum of each digit in the number
     *
     * @param num
     */
    private static int getSum(String num) {
        if (num == null) { throw new IllegalArgumentException("Num input is null"); }

        int sum = 0;
        for (int i = 0; i < num.length(); i++) {
            sum += num.charAt(i) - '0';
        }
        return sum;
    }

    /**
     * Main method to get maximal sum of power of a^b for a, b < 100
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int result = -1;

        for (int a = 2; a < 100; a++) {
            for (int b = 2; b < 100; b++) {
                BigInteger bigA = new BigInteger(String.valueOf(a));
                BigInteger num = bigA.pow(b);
                int sum = getSum(num.toString());
                if (sum > result) {
                    result = sum;
                }
            }
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
