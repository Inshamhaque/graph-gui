import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Creating the graph
        Graph graph = new Graph();

        // taking user inputs for number of vertices
        int numVertices = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of vertices:"));
        for (int i = 1; i <= numVertices; i++) {
            graph.addVertex(i);
        }

        // taking user inputs for number of edges
        int numEdges = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of edges:"));
        for (int i = 0; i < numEdges; i++) {
            int source = Integer.parseInt(JOptionPane.showInputDialog("Enter the source vertex for edge " + (i + 1) + ":"));
            int dest = Integer.parseInt(JOptionPane.showInputDialog("Enter the destination vertex for edge " + (i + 1) + ":"));
            int weight = Integer.parseInt(JOptionPane.showInputDialog("Enter the weight for edge " + (i + 1) + ":"));
            graph.addEdge(source, dest, weight);
        }

        // we use a circular type of representation for representing the graph
        Map<Integer, Point> vertexLocations = new HashMap<>();
        int radius = 250;  // Radius of the circle
        int centerX = 400; // X coordinate of the center
        int centerY = 300; // Y coordinate of the center
        double angleStep = 2 * Math.PI / numVertices;

        for (int i = 1; i <= numVertices; i++) {
            int x = (int) (centerX + radius * Math.cos(i * angleStep));
            int y = (int) (centerY + radius * Math.sin(i * angleStep));
            vertexLocations.put(i, new Point(x, y));
        }

        // Print the graph's adjacency list
        System.out.println("Graph adjacency list:"); //debugging statement
        System.out.println(graph.getAll());

        // basic create the JFrame and add the GraphVisualizer panel
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
