package org.example;
import org.example.Graph;
import org.example.Edge;
import org.example.ChristofidesAlgorithm;
import java.io.*;
import java.util.*;

public class PrimsMST {
    public static List<Edge> primMST(Graph graph) {
        String startVertex = graph.getVertex(0); // Pick arbitrary starting vertex
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> Double.compare(e1.getWeight(), e2.getWeight()));
        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();

        visited.add(startVertex);
        pq.addAll(graph.getEdges(startVertex));

        while (!pq.isEmpty()) {
            Edge curr = pq.remove();

            if (visited.contains(curr.getDestination())) {
                continue;
            }

            visited.add(curr.getDestination());
            mst.add(curr);

            List<Edge> edges = graph.getEdges(curr.getDestination());
            for (Edge e : edges) {
                if (!visited.contains(e.getDestination())) {
                    pq.add(e);
                }
            }
        }

        return mst;
    }


}
