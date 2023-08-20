public class Problem78 {

    private static final int TARGET = 1000000;

    private static int pilesCount(int n) {
        int[] count = new int[n + 1];
        count[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                count[j] = (count[j] + count[j - i]) % TARGET;
            }
        }

        for (int i = 1; i <= n; i++) {
            if (count[i] == 0) { return i; }
        }
        return -1;
    }

    /**
     * Main method.
     * We use exponential trick to double the coins by factor of 2 in each loop to meet the target.
     * Problem statement is exactly same as Problem 76, with an additional challenge to find integer N, whose summation is divisible by TARGET.
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int coins = 1, result = -1;
        for (result = -1; result == -1; coins *= 2) {
            result = pilesCount(coins);
        }
        System.out.println(result);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}
