import java.util.Arrays;
import java.util.Stack;

public class ShortestPathsTopological {
    private int[] parent;
    private int s;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        parent = new int[G.V()];
        dist = new double[G.V()];
        Arrays.fill(dist, Double.MAX_VALUE);
        Arrays.fill(parent, -1);
        this.s = s;
        dist[s] = 0;

        TopologicalWD topologicalOrder = new TopologicalWD(G);
        topologicalOrder.dfs(s);

        Stack<Integer> order = topologicalOrder.order();
        while (!order.isEmpty()) {
            int vertex = order.pop();
            for (DirectedEdge edge : G.incident(vertex)) {
                relax(edge);
            }
        }
    }

    public void relax(DirectedEdge e) {
        if (dist[e.to()] > dist[e.from()] + e.weight()) {
            parent[e.to()] = e.from();
            dist[e.to()] = dist[e.from()] + e.weight();
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }

    /*
    public static void main(String[] args) {
        WeightedDigraph graph = new WeightedDigraph(8);
        graph.addEdge(0, 1, 1.0);
        graph.addEdge(0, 2, 3.0);
        graph.addEdge(1, 3, 1.0);
        graph.addEdge(1, 4, 6.0);
        graph.addEdge(2, 4, 1.0);
        graph.addEdge(2, 6, 4.0);
        graph.addEdge(3, 5, 2.0);
        graph.addEdge(4, 6, 2.0);
        graph.addEdge(5, 7, 8.0);
        graph.addEdge(6, 7, 2.0);

        ShortestPathsTopological test = new ShortestPathsTopological(graph, 0);

        System.out.println(Arrays.toString(test.dist));
        System.out.println(Arrays.toString(test.parent));

        WeightedDigraph graphTwo = new WeightedDigraph(7);
        graphTwo.addEdge(0, 1, 3.0);
        graphTwo.addEdge(0, 2, 2.0);
        graphTwo.addEdge(1, 2, -2.0);
        graphTwo.addEdge(1, 3, 4.0);
        graphTwo.addEdge(1, 4, 3.0);
        graphTwo.addEdge(2, 5, 3.0);
        graphTwo.addEdge(2, 6, 2.0);
        graphTwo.addEdge(3, 4, -2.0);
        graphTwo.addEdge(4, 5, -3.0);
        graphTwo.addEdge(5, 6, -2.0);

        ShortestPathsTopological testTwo = new ShortestPathsTopological(graphTwo, 0);

        System.out.println(Arrays.toString(testTwo.dist));
        System.out.println(Arrays.toString(testTwo.parent));
    }

     */
}

