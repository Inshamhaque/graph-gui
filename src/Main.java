import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Create a new graph
        Graph graph = new Graph();

        // Input for vertices
        int numVertices = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of vertices:"));
        for (int i = 1; i <= numVertices; i++) {
            graph.addVertex(i);
        }

        // Input for edges
        int numEdges = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of edges:"));
        for (int i = 0; i < numEdges; i++) {
            int source = Integer.parseInt(JOptionPane.showInputDialog("Enter the source vertex for edge " + (i + 1) + ":"));
            int dest = Integer.parseInt(JOptionPane.showInputDialog("Enter the destination vertex for edge " + (i + 1) + ":"));
            int weight = Integer.parseInt(JOptionPane.showInputDialog("Enter the weight for edge " + (i + 1) + ":"));
            graph.addEdge(source, dest, weight);
        }

        // Define vertex locations dynamically
        Map<Integer, Point> vertexLocations = new HashMap<>();
        Random random = new Random();
        for (int i = 1; i <= numVertices; i++) {
            int x = 50 + random.nextInt(700); // X coordinate between 50 and 750
            int y = 50 + random.nextInt(500); // Y coordinate between 50 and 550
            vertexLocations.put(i, new Point(x, y));
        }

        // Print the graph's adjacency list
        System.out.println("Graph adjacency list:");
        System.out.println(graph.getAll());

        // Create the JFrame and add the GraphVisualizer panel
        JFrame frame = new JFrame("Graph Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create and add the GraphVisualizer panel
        GraphVisualizer visualizer = new GraphVisualizer(graph, vertexLocations);
        frame.getContentPane().add(visualizer, BorderLayout.CENTER);

        // Pack and display the frame
        frame.pack();
        frame.setVisible(true);
    }
}
