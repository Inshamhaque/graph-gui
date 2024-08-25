import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addVertex(int v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    public void addEdge(int source, int dest, int weight) {
        // Ensure both source and destination vertices are present
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());

        // Add edge from source to destination
        adjList.get(source).add(new Edge(dest, weight));

        // Add edge from destination to source if the graph is undirected
        // Comment out the following line if the graph is directed
        adjList.get(dest).add(new Edge(source, weight));
    }

    public List<Edge> getEdges(int v) {
        System.out.println("For node " + v + ", adjList is: " + adjList.get(v));
        return adjList.getOrDefault(v, new ArrayList<>());
    }

    public List<Integer> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    /**
     * Returns a Map containing all the key-value pairs from the adjacency list.
     *
     * @return a Map where each key is a vertex and the corresponding value is the list of edges.
     */
    public Map<Integer, List<Edge>> getAll() {
        return new HashMap<>(adjList);
    }
}
