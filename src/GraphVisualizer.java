import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GraphVisualizer extends JPanel {
    private Graph graph;
    private Map<Integer, Point> vertexLocations;
    private ArrayList<Integer> traversal;
    private GraphAlgo graphAlgo;
    private int step;
    private javax.swing.Timer timer;
    private JLabel resultLabel;

    public GraphVisualizer(Graph graph, Map<Integer, Point> vertexLocations) {
        this.graph = graph;
        this.vertexLocations = vertexLocations;
        this.graphAlgo = new GraphAlgo();
        this.traversal = null;
        this.step = 0;

        setPreferredSize(new Dimension(800, 600));
        setLayout(new BorderLayout());

        // BFS Button
        JButton bfsButton = new JButton("Start BFS");
        bfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int startNode = Integer.parseInt(JOptionPane.showInputDialog("Enter start node for BFS:"));
                    traversal = graphAlgo.bfs(graph, startNode);
                    System.out.println("BFS Traversal: " + traversal); // Debug statement
                    step = 0;
                    startAnimation();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GraphVisualizer.this, "Invalid node number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // DFS Button
        JButton dfsButton = new JButton("Start DFS");
        dfsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int startNode = Integer.parseInt(JOptionPane.showInputDialog("Enter start node for DFS:"));
                    traversal = graphAlgo.dfsOfGraph(graph, startNode);
                    System.out.println("DFS Traversal: " + traversal); // Debug statement
                    step = 0;
                    startAnimation();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GraphVisualizer.this, "Invalid node number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        resultLabel = new JLabel("Traversal: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(bfsButton);
        buttonPanel.add(dfsButton);

        add(buttonPanel, BorderLayout.NORTH);
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
                if (step >= traversal.size()) {
                    timer.stop();
                    updateResultLabel();
                }
                repaint();
            }
        });
        timer.start();
    }

    private void updateResultLabel() {
        if (traversal != null) {
            resultLabel.setText("Traversal: " + traversal.toString());
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
            if (traversal != null && traversal.contains(node) && traversal.indexOf(node) <= step) {
                g.setColor(Color.BLUE);  // Visited nodes
            } else {
                g.setColor(Color.RED);   // Unvisited nodes
            }
            g.fillOval(p.x - 10, p.y - 10, 20, 20);  // Larger size for better visibility
            g.setColor(Color.BLACK);
            g.drawString(Integer.toString(node), p.x - 5, p.y + 5);  // Centered text
        }

        // Highlight traversal path
        if (traversal != null && step > 0) {
            g.setColor(Color.GREEN);
            for (int i = 0; i < step; i++) {
                int node = traversal.get(i);
                Point p = vertexLocations.get(node);
                if (p != null) {
                    g.drawString(Integer.toString(node), p.x - 5, p.y - 15);
                    if (i > 0) {
                        int prevNode = traversal.get(i - 1);
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
