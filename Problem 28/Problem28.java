public class Problem28 {

    /**
     * Main method to get the Spiral Matrix and get the sum of its diagonal elements
     *
     * @param args
     */
    public static void main(String[] args) {
        int[][] spiralMatrix = getSpiralMatrix(1001);
        //        for (int r = 0; r < spiralMatrix.length; r++) {
        //            for (int c = 0; c < spiralMatrix[0].length; c++) {
        //                System.out.print(spiralMatrix[r][c] + "  ");
        //            }
        //            System.out.print("\n");
        //        }
        int sum = diagonalSum(spiralMatrix);
        System.out.println("Result - " + sum);
    }

    /**
     * Returns a matric containing elements arranged in a spiral around the pivot which is at the half-way mark of
     * the matrix
     *
     * @param n
     */
    private static int[][] getSpiralMatrix(int n) {
        if (n <= 1) { return null; }
        int[][] spiralMatrix = new int[n][n];
        int halfMark = (n - 1) / 2;
        int lastRow = halfMark, lastCol = halfMark;
        for (int i = 1; i <= n * n; i++) {
            if (spiralMatrix[lastRow][lastCol] == 0) {
                spiralMatrix[lastRow][lastCol] = i;
            }
            if (lastRow == halfMark && lastCol == halfMark) {
                lastCol++;
            } else if (lastRow != n - 1 && lastCol != 0 && spiralMatrix[lastRow][lastCol - 1] != 0
                    && spiralMatrix[lastRow + 1][lastCol] == 0) {
                lastRow++;
            } else if (lastRow != 0 && lastCol != 0 && spiralMatrix[lastRow - 1][lastCol] != 0
                    && spiralMatrix[lastRow][lastCol - 1] == 0) {
                lastCol--;
            } else if (lastRow != 0 && lastCol != n - 1 && spiralMatrix[lastRow][lastCol + 1] != 0
                    && spiralMatrix[lastRow - 1][lastCol] == 0) {
                lastRow--;
            } else if (lastRow != n - 1 && lastCol != n - 1 && spiralMatrix[lastRow + 1][lastCol] != 0
                    && spiralMatrix[lastRow][lastCol + 1] == 0) {
                lastCol++;
            }
        }
        return spiralMatrix;
    }

    /**
     * Gets the sum of all the elements in the matrix present at diagonal positions
     *
     * @param matrix
     */
    private static int diagonalSum(int[][] matrix) {
        if (matrix == null) { return 0; }
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (row == col || row == matrix[0].length - col - 1 || matrix.length - row - 1 == col
                        || matrix.length - row - 1 == matrix[0].length - col - 1) {
                    sum += matrix[row][col];
                }
            }
        }
        return sum;
    }
}
