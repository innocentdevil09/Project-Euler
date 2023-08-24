import java.io.BufferedReader;
import java.io.FileReader;

public class Problem82 {

    private static final int N = 80;

    /**
     * Method to read file and 2-dimensional matrix of numbers present in file.
     *
     * @return
     */
    private static int[][] getMatrix() {
        String filePath = "C:\\Users\\himagupta\\IdeaProjects\\Project-Euler\\Problem 82\\matrix.txt";
        int[][] matrix = new int[N][N];

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int i = N - 1;
            while ((line = reader.readLine()) != null) {
                String[] nums = line.split(",");
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(nums[j]);
                }
                i--;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return matrix;
    }

    /**
     * The concept is similar to Prim's & Kruskal's algorithm, i.e., to relax the node in graph such that the other
     * adjoining vertices cannot relax it further.
     * Using the same concept and dynamic programming, we solve this problem.
     *
     * @param matrix
     * @return
     */
    private static int minPathSum(int[][] matrix) {
        int[][] tempMatrix = new int[N][N];

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                if (col == 0) {
                    tempMatrix[row][col] = matrix[row][col];
                    continue;
                }
                int min = tempMatrix[row][col - 1];
                if (row > 0) {
                    min = Math.min(min, tempMatrix[row - 1][col]);
                }

                tempMatrix[row][col] = matrix[row][col] + min;
            }

            for (int row = N - 2; row >= 0; row--) {
                tempMatrix[row][col] = Math.min(tempMatrix[row][col], matrix[row][col] + tempMatrix[row + 1][col]);
            }
        }

        int min = Integer.MAX_VALUE;
        for (int row = 0; row < N; row++) {
            min = Math.min(min, tempMatrix[row][N - 1]);
        }
        return min;
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[][] matrix = getMatrix();
        int result = minPathSum(matrix);
        System.out.println(result);

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime));
    }
}
