public class Problem31 {

    private static int[] coins = {1, 2, 5, 10, 20, 50, 100, 200};
    private static final int TOTAL = 200;

    public static void main(String[] args) {
        int[][] totalWays = new int[coins.length + 1][TOTAL + 1];
        totalWays[0][0] = 1;
        for (int i = 0; i < totalWays.length - 1; i++) {
            for (int j = 0; j < totalWays[0].length; j++) {
                totalWays[i + 1][j] = totalWays[i][j] + (j >= coins[i] ? totalWays[i + 1][j - coins[i]] : 0);
            }
        }
        System.out.println("Total no. of ways : " + totalWays[coins.length][TOTAL]);
    }
}
