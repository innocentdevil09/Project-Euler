public class Problem14 {

    /**
     * Method to get the chain size for the collatz sequence
     * n → n/2 (n is even)
     * n → 3n + 1 (n is odd)
     * <p>
     * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
     *
     * @param num
     */
    private static long getChainSize(long num) {
        long chainSize = 1;
        while (num > 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num = (3 * num) + 1;
            }
            chainSize++;
        }
        return chainSize;
    }

    public static void main(String[] args) {
        long longestChainSize = -1L, val = -1L;
        long i = 2;
        while (i < 1000000) {
            long chainSize = getChainSize(i);
            if (chainSize > longestChainSize) {
                longestChainSize = chainSize;
                val = i;
            }
            i++;
        }
        System.out.println(val);
    }
}
