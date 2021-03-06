public class Problem6 {

    /**
     * Method to return square of a given number
     *
     * @param n
     */
    private static int sqr(int n) {
        if (n < 0) { throw new IllegalArgumentException(); }
        return n * n;
    }

    public static void main(String[] args) {
        int n = 100;
        int sumOfSquares = 0, squareOfTotal = 0;
        for (int i = 1; i <= n; i++) {
            sumOfSquares += sqr(i);
            squareOfTotal += i;
        }
        squareOfTotal = sqr(squareOfTotal);
        System.out.println(squareOfTotal - sumOfSquares);
    }
}
