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
                    System.out.println("queue is : "+ queue);
                }
            }
        }
        System.out.println("bfs array :"+result);
        return result;
    }
}
