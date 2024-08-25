import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GraphVisualizer extends JPanel {
    private Graph graph;
    private Map<Integer, Point> vertexLocations;
    private ArrayList<Integer> bfsTraversal;
    private GraphAlgo graphAlgo;
    private int step;
    private javax.swing.Timer timer;
    private JLabel resultLabel;

    public GraphVisualizer(Graph graph, Map<Integer, Point> vertexLocations) {
        this.graph = graph;
        this.vertexLocations = vertexLocations;
        this.graphAlgo = new GraphAlgo();
        this.bfsTraversal = null;
        this.step = 0;

        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        JButton bfsButton = new JButton("Start BFS");
        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int startNode = Integer.parseInt(JOptionPane.showInputDialog("Enter start node for BFS:"));
                    bfsTraversal = graphAlgo.bfs(graph, startNode);
                    System.out.println("BFS Traversal: " + bfsTraversal); // Debug statement
                    step = 0;
                    startAnimation();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GraphVisualizer.this, "Invalid node number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resultLabel = new JLabel("BFS Traversal: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(bfsButton, BorderLayout.NORTH);
        add(resultLabel, BorderLayout.SOUTH);
    }

    private void startAnimation() {
        if (timer != null) {
            timer.stop();
        }
        timer = new javax.swing.Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                step++;
                if (step >= bfsTraversal.size()) {
                    timer.stop();
                    updateResultLabel();
                }
                repaint();
            }
        });
        timer.start();
    }

    private void updateResultLabel() {
        if (bfsTraversal != null) {
            resultLabel.setText("BFS Traversal: " + bfsTraversal.toString());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw edges
        g.setColor(Color.BLACK);
        for (int node : graph.getVertices()) {
            Point p1 = vertexLocations.get(node);
            if (p1 != null) {
                for (Edge edge : graph.getEdges(node)) {
                    Point p2 = vertexLocations.get(edge.destination);
                    if (p2 != null) {
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
            }
        }

        // Draw vertices
        for (Map.Entry<Integer, Point> entry : vertexLocations.entrySet()) {
            int node = entry.getKey();
            Point p = entry.getValue();
            if (bfsTraversal != null && bfsTraversal.contains(node) && bfsTraversal.indexOf(node) <= step) {
                g.setColor(Color.BLUE);  // Visited nodes
            } else {
                g.setColor(Color.RED);   // Unvisited nodes
            }
            g.fillOval(p.x - 10, p.y - 10, 20, 20);  // Larger size for better visibility
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(node), p.x - 5, p.y + 5);  // Centered text
        }

        // Highlight BFS traversal path
        if (bfsTraversal != null && step > 0) {
            g.setColor(Color.GREEN);
            for (int i = 0; i < step; i++) {
                int node = bfsTraversal.get(i);
                Point p = vertexLocations.get(node);
                if (p != null) {
                    g.drawString(Integer.toString(node), p.x - 5, p.y - 15);
                    if (i > 0) {
                        int prevNode = bfsTraversal.get(i - 1);
                        Point prevPoint = vertexLocations.get(prevNode);
                        if (prevPoint != null) {
                            g.drawLine(prevPoint.x, prevPoint.y, p.x, p.y);
                        }
                    }
                }
            }
        }
    }
}
