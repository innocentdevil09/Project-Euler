public class Problem61 {

    /**
     * Data variables to define the limits of figurate numbers
     */
    private static final int LOWER_LIMIT = 999;
    private static final int UPPER_LIMIT = 10000;
    /**
     * Data variable to store all figurate numbers
     * e.g. "number[3][1035] = true" indicates that in triangular numbers -- 1035 is a triangle number
     */
    private static boolean[][] numbers = new boolean[9][UPPER_LIMIT];
    /**
     * Static boolean variable solved to help close the recursive loop
     */
    private static boolean solved = false;

    /**
     * Method to get all triangle numbers between lower and upper limit
     * T = n * (n + 1) / 2
     */
    private static void getTriangleNums() {
        for (int i = 1; ; i++) {
            int num = (i * (i + 1)) / 2;
            if (num > UPPER_LIMIT) { break; }
            if (num > LOWER_LIMIT) {
                numbers[3][num] = true;
            }
        }
    }

    /**
     * Method to get all square numbers between lower and upper limit
     * T = n^2
     */
    private static void getSquareNums() {
        for (int i = 1; ; i++) {
            int num = i * i;
            if (num > UPPER_LIMIT - 1) { break; }
            if (num > LOWER_LIMIT) {
                numbers[4][num] = true;
            }
        }
    }

    /**
     * Method to get all pentagonal numbers between lower and upper limit
     * T = n * (3n - 1) / 2
     */
    private static void getPentagonalNums() {
        for (int i = 1; ; i++) {
            int num = (i * ((3 * i) - 1)) / 2;
            if (num > UPPER_LIMIT - 1) { break; }
            if (num > LOWER_LIMIT) {
                numbers[5][num] = true;
            }
        }
    }

    /**
     * Method to get all hexagonal numbers between lower and upper limit
     * T = n * (2n - 1) / 2
     */
    private static void getHexagonalNums() {
        for (int i = 1; ; i++) {
            int num = i * ((2 * i) - 1);
            if (num > UPPER_LIMIT - 1) { break; }
            if (num > LOWER_LIMIT) {
                numbers[6][num] = true;
            }
        }
    }

    /**
     * Method to get all triangle numbers between lower and upper limit
     * T = n * (5n - 3) / 2
     */
    private static void getHeptagonalNums() {
        for (int i = 1; ; i++) {
            int num = (i * ((5 * i) - 3)) / 2;
            if (num > UPPER_LIMIT - 1) { break; }
            if (num > LOWER_LIMIT) {
                numbers[7][num] = true;
            }
        }
    }

    /**
     * Method to get all triangle numbers between lower and upper limit
     * T = n * (3n - 2)
     */
    private static void getOctagonalNums() {
        for (int i = 1; ; i++) {
            int num = i * ((3 * i) - 2);
            if (num > UPPER_LIMIT - 1) { break; }
            if (num > LOWER_LIMIT) {
                numbers[8][num] = true;
            }
        }
    }

    /**
     * Recursive function to get all the elements in the sumArray which satisfies the cyclic conditions
     * <p>
     * 'unique' is used to process only the unique number in the array
     * 'last' is the index of the most recent index filled in the sumArray. It is used to check cyclic conditions
     * if the count == 5, i.e. all the figurate numbers are identified, and number at index 3 forms a closed cyclic
     * loop with the recent number, the array is solved
     *
     * @param sumArray
     * @param last
     * @param count
     */
    private static void getCyclicSum(int[] sumArray, int last, int count) {

        for (int i = 4; i < 9; i++) {
            if (sumArray[i] != 0) { continue; }
            for (int j = LOWER_LIMIT; j < numbers[i].length; j++) {
                if (numbers[i][j]) {

                    boolean unique = isUnique(sumArray, j);
                    if (unique && (sumArray[last] % 100 == j / 100)) {
                        sumArray[i] = j;
                        if (count == 5 && sumArray[3] / 100 == j % 100) {
                            solved = true;
                            return;
                        }
                        getCyclicSum(sumArray, i, count + 1);
                        if (solved) { return; }
                    }
                }
            }
            sumArray[i] = 0;
        }
    }

    /**
     * Method to check if the given number is already present in the sumArray
     *
     * @param sumArray
     * @param k
     */
    private static boolean isUnique(int[] sumArray, int k) {
        for (int value : sumArray) {
            if (value == k) {
                return false;
            }
        }
        return true;
    }

    /**
     * Main method
     * Algo:-
     * 1. Get all figurate numbers and store them in the boolean matrix
     * 2. Initialize an array of size 9 to indicate the figurate number in the cycle through their index
     * 3. Start with triangle numbers and send the array inside a recursive loop.
     * 4. Two more parameters in the recursive loop -- last index and count.
     * 'count' is used to indicate if the sumArray has got all the figurate numbers at the index.
     * 'lastIndex' is used to check if the numbers are cyclic
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        getTriangleNums();
        getSquareNums();
        getPentagonalNums();
        getHexagonalNums();
        getHeptagonalNums();
        getOctagonalNums();

        int[] sumArray = new int[9];

        for (int num = LOWER_LIMIT + 1; num < UPPER_LIMIT; num++) {
            if (numbers[3][num]) {
                sumArray[3] = num;
                getCyclicSum(sumArray, 3, 1);
                if (solved) { break; }
            }
        }
        int sum = 0;
        for (int num : sumArray) {
            sum += num;
        }

        System.out.println(sum);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}