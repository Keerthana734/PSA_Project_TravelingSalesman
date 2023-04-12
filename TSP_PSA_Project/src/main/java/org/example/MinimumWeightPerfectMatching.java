package org.example;
import java.util.*;


public class MinimumWeightPerfectMatching {
    public static Graph PerfectMatching(Graph graph) {
        // Create a list of all the odd vertices
        List<String> odds = new ArrayList<>();
        for (String vertex : graph.getVertices()) {
            int degree = graph.getDegree(vertex);
            if (degree % 2 == 1) {
                odds.add(vertex);
            }
        }

        // While there are still odd vertices
        while (!odds.isEmpty()) {
            // Pop the first odd vertex from the list
            String v = odds.remove(0);

            // Initialize the length of the shortest edge to infinity
            double length = Double.MAX_VALUE;
            String closest = null;

            // Iterate over all the odd vertices
            for (String u : odds) {
                // If the weight of the edge between v and u is less than the current length
                if (graph.getWeight(v, u) < length) {
                    // Update the length and the closest vertex
                    length = graph.getWeight(v, u);
                    closest = u;
                }
            }

            // Add an edge between the closest vertex and v
            graph.addEdge(v, closest);
        }
        return graph;
    }

}
