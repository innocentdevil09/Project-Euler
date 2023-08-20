import java.util.Stack;

public class Topological {

    private final boolean[] marked;
    private final Stack<Integer> reverseOrder;

    public Topological(Digraph G) {
        this.marked = new boolean[G.V()];
        this.reverseOrder = new Stack<>();
        for (int v = 0; v < G.V(); v++) {
            if (!this.marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        this.reverseOrder.push(v);
    }

    public Stack<Integer> reverseOrder() {
        return this.reverseOrder;
    }
}
