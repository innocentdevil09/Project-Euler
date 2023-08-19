public class Problem76 {

    /**
     * To represent any integer as summation of two positive integers:
     * Integer 1: None
     * Integer 2: 1+1
     * Integer 3: 1+1+1, 2+1
     * Integer 4: 1+1+1+1, 2+1+1, 3+1, 2+2
     * Integer 5: 1+1+1+1+1, 2+1+1+1, 3+1+1, 4+1, 2+2+1, 3+2
     * etc.
     *
     * We use dynamic programming to get the count for possible combinations of summation.
     *
     * @param n
     * @return count of different ways integer n can be represented as summation of two positive integers.
     */
    private static int summationCount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j >= 1; j--) {
                if (i == j) {
                    dp[i][j] = 1;
                    continue;
                }
                dp[i][j] = dp[i][j + 1] + dp[i - j][j];
            }
        }
        return dp[n][1] - 1;
    }

    /**
     * Main method.
     * The problem statement is to find total number of ways integer 100 can be represented as summation of two positive integers.
     *
     * The solution involves dynamic programming, as the count depends on summation received for previous numbers.
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int n = 6;
        int count = summationCount(n);
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}
