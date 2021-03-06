public class Problem39 {

    /**
     * Method to check if the trio makes a right-angled triangle
     *
     * @param a
     * @param b
     * @param c
     */
    private static boolean isRightAngledTriangle(int a, int b, int c) {
        return ((a * a) + (b * b)) == (c * c);
    }

    /**
     * This method returns the no. of pairs which forms a right-angled triangle for a given perimeter
     * <p>
     * Assumptions for optimization
     * 1. The hypotenuse is always the largest of the three sides
     * 2. No two sides can have equal lengths, if all sides are integers. If the smaller two sides are equal in
     * length, the hypotenuse will be an irrational number
     * </p>
     *
     * @param p
     */
    private static int noOfPairs(int p) {
        int pairs = 0;
        for (int a = 1; a < p; a++) {
            for (int b = a + 1; b < p; b++) {
                int c = p - a - b;
                if (c <= a || c <= b) { break; }
                if (isRightAngledTriangle(a, b, c)) {
                    pairs++;
                }
            }
        }
        return pairs;
    }

    /**
     * Main method to get the result
     *
     * @param args
     */
    public static void main(String[] args) {
        int result = 0;
        int maxNoOfPairs = 0;

        long startTime = System.currentTimeMillis();
        for (int p = 4; p <= 1000; p++) {
            int n = noOfPairs(p);
            if (maxNoOfPairs < n) {
                result = p;
                maxNoOfPairs = n;
            }
        }
        long endTime = System.currentTimeMillis();

        System.out.println("Maximum no. of right-angled triangle pairs: " + maxNoOfPairs + " found for - " + result);
        System.out.println("Time taken: " + (endTime - startTime));
    }
}