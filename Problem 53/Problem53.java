import java.math.BigInteger;

public class Problem53 {

    /**
     * Method to get binomial matrix to get nCr values for all 1 <= n <= 100
     *
     * @param n
     */
    private static BigInteger[][] getBinomialMatrix(int n) {
        BigInteger[][] binomial = new BigInteger[n + 1][n + 1];
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                binomial[i][j] = BigInteger.ZERO;
            }
        }
        for (int i = 0; i < n + 1; i++) {
            binomial[i][0] = BigInteger.ONE;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                binomial[i][j] = binomial[i - 1][j - 1].add(binomial[i - 1][j]);
            }
        }
        return binomial;
    }

    /**
     * Main method to count the number of values above one million
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        BigInteger[][] binomial = getBinomialMatrix(100);
        BigInteger one_million = new BigInteger(String.valueOf(1000000));

        int count = 0;
        for (BigInteger[] row : binomial) {
            for (BigInteger num : row) {
                if (num.max(one_million).equals(num)) {
                    count++;
                }
            }
        }
        System.out.println(binomial[100][50]);
        System.out.println(count);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
