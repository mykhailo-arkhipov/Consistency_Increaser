import java.awt.*;
import java.util.List;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

public class GraphVisualizer {
    public static void visualize(int[][] matrix, List<int[]> unicPairs) {
        // unicPairs - feature to be added
        Graph graph = new SingleGraph("Graph");

        // Adding graph nodes
        for (int i = 0; i < matrix.length; i++) {
            graph.addNode(Integer.toString(i));
            graph.getNode(Integer.toString(i)).setAttribute("ui.label", "A" + (i+1));
            graph.getNode(Integer.toString(i)).setAttribute("ui.style", "text-size: 30;");
            graph.getNode(Integer.toString(i)).setAttribute("ui.color", Color.BLUE);
            graph.getNode(Integer.toString(i)).setAttribute("ui.style", "text-offset: 0, -20;");

        }

        // Adding graph edges based on a matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 1 & i != j) {

                    if (graph.getEdge(i + "-" + j) == null) {
                        graph.addEdge(i + "-" + j, Integer.toString(i), Integer.toString(j), true);
                        // graph.getEdge(i + "-" + j).setAttribute("ui.color", Color.BLUE);
                    }

                    } else if (matrix[i][j] == 2 & graph.getEdge(i + "-" + j) == null & i != j & graph.getEdge(j + "-" + i) == null) {
                    graph.addEdge(i + "-" + j, Integer.toString(i), Integer.toString(j));
                    //graph.getEdge(i + "-" + j).setAttribute("ui.color", Color.GREEN);
                    // graph.setAttribute("ui.stylesheet", "edge { fill-color: green; }");
                }
            }
        }

        System.setProperty("org.graphstream.ui", "swing");
        graph.display();
    }
}