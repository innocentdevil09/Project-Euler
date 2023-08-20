import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Stack;

public class Problem79 {

    /**
     * Main method.
     * Topological sort implementation to find the code.
     * '4' & '5' chars were not present in the given keylogs file.
     * @param args
     */
    public static void main(String[] args) {
        int v = 10;
        Digraph G = new Digraph(v);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\himagupta\\IdeaProjects\\Project-Euler\\Problem 79\\keylogs.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < line.length() - 1; i++) {
                    G.addEdge(line.charAt(i) - '0', line.charAt(i + 1) - '0');
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Topological topological = new Topological(G);
        StringBuilder sb = new StringBuilder();
        Stack<Integer> reverseOrder = topological.reverseOrder();
        while (!reverseOrder.isEmpty()) {
            sb.append(reverseOrder.pop());
        }
        System.out.println(sb);
    }
}
