public class Problem15 {

    /**
     *
     * @param n
     * @param k
     * @return
     */
    private static long getBinomialVal(int n, int k) {
        long[][] binomial = new long[n + 1][k + 1];

        for (int i = 0; i < n + 1; i++) {
            binomial[i][0] = 1;
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                binomial[i][j] = binomial[i - 1][j - 1] + binomial[i - 1][j];
            }
        }
        return binomial[n][k];
    }

    public static void main(String[] args) {
        System.out.println(getBinomialVal(40, 20));
    }
}
