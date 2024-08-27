import java.util.*;

public class GraphAlgo {

    public ArrayList<Integer> bfs(Graph graph, int start) {
        ArrayList<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        ArrayQueue<Integer> queue = new ArrayQueue<>(100);

        queue.enqueue(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.dequeue();
            System.out.println("Processing node: " + node); // Debug statement
            result.add(node);
            for (Edge edge : graph.getEdges(node)) {
                if (!visited.contains(edge.destination)) {
                    queue.enqueue(edge.destination);
                    visited.add(edge.destination);
                    System.out.println("Enqueueing node: " + edge.destination); // Debug statement
                    System.out.println("queue is: " + queue);
                }
            }
        }
        System.out.println("BFS array: " + result);
        return result;
    }

    public void dfs(int node, boolean[] vis, Graph graph, ArrayList<Integer> result) {
        vis[node] = true;
        result.add(node);
        System.out.println("Visiting node: " + node); // Debug statement

        // Traverse all adjacent vertices of the current node
        for (Edge edge : graph.getEdges(node)) {
            if (!vis[edge.destination]) {
                dfs(edge.destination, vis, graph, result);
            }
        }
    }

    public ArrayList<Integer> dfsOfGraph(Graph graph, int start) {
        boolean[] vis = new boolean[100]; // Assuming the graph has at most 100 vertices
        ArrayList<Integer> result = new ArrayList<>();
        dfs(start, vis, graph, result);
        System.out.println("DFS array: " + result);
        return result;
    }
}
