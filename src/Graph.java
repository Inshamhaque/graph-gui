import java.util.*;

public class Graph {
    private Map<Integer, List<Edge>> adjList;
    /***
     * Format of adjList
     * {
     *     1 : {{2,2},{3,3},{4,10}},
     *     2 : {{1,2},{7,3},{2,10}} and so on...
     *     thus source : {{dest1,weight1},{dest2,weight2},...}
     * }
     ***/

    //graph constructor
    public Graph() {
        adjList = new HashMap<>();
    }

    //to add the vertex functionality
    public void addVertex(int v) {
        adjList.putIfAbsent(v, new ArrayList<>());
    }

    // to add the edge functionality
    public void addEdge(int source, int dest, int weight) {
        // if both edge and source are absent, then we create an empty vector to show the attached nodes
        adjList.putIfAbsent(source, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());

        // Add edge from source to destination
        adjList.get(source).add(new Edge(dest, weight));

        // Add edge from destination to source if the graph is undirected
        // remove this if the graph is undirected.
        adjList.get(dest).add(new Edge(source, weight));
    }

    // functionality to get the edges given vertices
    public List<Edge> getEdges(int v) {
        System.out.println("For node " + v + ", adjList is: " + adjList.get(v));
        return adjList.getOrDefault(v, new ArrayList<>());
    }

    // functionality to get the entire graph... keyset gives the entire hashmap in the form of a set
    public List<Integer> getVertices() {
        return new ArrayList<>(adjList.keySet());
    }

    /**
     * Returns a Map containing all the key-value pairs from the adjacency list.
     *
     * @return a Map where each key is a vertex and the corresponding value is the list of edges.
     */
    // this returns to in the form of a hashmap only
    public Map<Integer, List<Edge>> getAll() {
        return new HashMap<>(adjList);
    }
}
