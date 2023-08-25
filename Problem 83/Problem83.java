import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;

public class Problem83 {

    private static final int N = 80;

    /**
     * Method to read file and 2-dimensional matrix of numbers present in file.
     *
     * @return
     */
    private static int[][] getMatrix() {
        String filePath = "C:\\Users\\himagupta\\IdeaProjects\\Project-Euler\\Problem 83\\matrix.txt";
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
     * Djikstra's Algorithm.
     * Relax each vertex till there is no further vertex adjacent to it that can reduce its value.
     * We use a Priority Queue to sort the vertices in ascending order.
     *
     * @param matrix
     * @return
     */
    private static int minPathSum(int[][] matrix) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int[][] tempMatrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMatrix[i][j] = Integer.MAX_VALUE;
            }
        }

        int row = N - 1, col = 0;
        tempMatrix[row][col] = matrix[row][col];
        pq.add(new int[] { matrix[row][col], row, col });

        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            row = arr[1];
            col = arr[2];

            if (row > 0 && tempMatrix[row - 1][col] > tempMatrix[row][col] + matrix[row - 1][col]) {
                tempMatrix[row - 1][col] = tempMatrix[row][col] + matrix[row - 1][col];
                pq.add(new int[] { tempMatrix[row - 1][col], row - 1, col });
            }

            if (row < N - 1 && tempMatrix[row + 1][col] > tempMatrix[row][col] + matrix[row + 1][col]) {
                tempMatrix[row + 1][col] = tempMatrix[row][col] + matrix[row + 1][col];
                pq.add(new int[] { tempMatrix[row + 1][col], row + 1, col });
            }

            if (col > 0 && tempMatrix[row][col - 1] > tempMatrix[row][col] + matrix[row][col - 1]) {
                tempMatrix[row][col - 1] = tempMatrix[row][col] + matrix[row][col - 1];
                pq.add(new int[] { tempMatrix[row][col - 1], row, col - 1 });
            }

            if (col < N - 1 && tempMatrix[row][col + 1] > tempMatrix[row][col] + matrix[row][col + 1]) {
                tempMatrix[row][col + 1] = tempMatrix[row][col] + matrix[row][col + 1];
                pq.add(new int[] { tempMatrix[row][col + 1], row, col + 1 });
            }
        }
        return tempMatrix[0][N - 1];
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
        System.out.println("Time taken:" + (endTime - startTime));
    }
}
