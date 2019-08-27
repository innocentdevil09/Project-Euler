import java.math.BigInteger;

public class Problem57 {

    /**
     * Main method
     *
     * It is possible to show that the square root of two can be expressed as an infinite continued fraction.
     *
     * √ 2 = 1 + 1/(2 + 1/(2 + 1/(2 + ... ))) = 1.414213...
     *
     * By expanding this for the first four iterations, we get:
     *
     * 1 + 1/2 = 3/2 = 1.5
     * 1 + 1/(2 + 1/2) = 7/5 = 1.4
     * 1 + 1/(2 + 1/(2 + 1/2)) = 17/12 = 1.41666...
     * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
     *
     * Continuing the pattern, we need to get all the counts when length of numerator is greater than denominator
     *
     * @param args
     */
    public static void main(String[] args) {
        BigInteger num = new BigInteger("2");
        BigInteger den = new BigInteger("3");

        int count = 0;
        for (int iter = 2; iter < 1001; iter++) {
            BigInteger tempNum = new BigInteger(num.toString());
            num = num.add(den.multiply(new BigInteger("2")));
            den = den.add(tempNum);

            if (num.toString().length() > den.toString().length()) {
                count++;
            }
        }
        System.out.println(count);
    }
}
