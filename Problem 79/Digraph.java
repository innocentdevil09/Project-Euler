import java.util.ArrayList;
import java.util.List;

public class Digraph {

    private final int V;
    private final List<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.adj = new List[V];
        for (int v = 0; v < V; v++) {
            this.adj[v] = new ArrayList<>();
        }
    }

    public void addEdge(int v, int w) {
        this.adj[v].add(w);
    }

    public List<Integer> adj(int v) {
        return this.adj[v];
    }

    public int V() {
        return this.V;
    }
}
