import java.io.BufferedReader;
import java.io.FileReader;

public class Problem81 {

    private static final int N = 80;

    /**
     * Method to read file and 2-dimensional matrix of numbers present in file.
     *
     * @return
     */
    private static int[][] getMatrix() {
        String filePath = "C:\\Users\\himagupta\\IdeaProjects\\Project-Euler\\Problem 81\\matrix.txt";
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
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                int min = Integer.MAX_VALUE;
                if (i < N - 1) {
                    min = Math.min(min, matrix[i][j] + matrix[i + 1][j]);
                }
                if (j > 0) {
                    min = Math.min(min, matrix[i][j] + matrix[i][j - 1]);
                }
                matrix[i][j] = min == Integer.MAX_VALUE ? matrix[i][j] : min;
            }
        }
        return matrix[0][N - 1];
    }

    /**
     * Main method.
     *
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int[][] matrix = getMatrix();
        int min = minPathSum(matrix);

        System.out.println(min);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken:" + (endTime - startTime));
    }
}
