package org.example;
import java.util.*;
import org.example.*;
public class MinimumWeightPerfectMatching {

    public static Graph minimumWeightMatching(List<Edge> MST, Graph G, Set<String> oddVert) {
        List<String> oddVertices = new ArrayList<>(oddVert);
        // Collections.shuffle(oddVertices);
        System.out.println(MST);
        while (!oddVertices.isEmpty()) {
            String v = oddVertices.remove(0);
            double length = Double.POSITIVE_INFINITY;
            String closest = "";
            for (String u : oddVertices) {
                if (!v.equals(u) && G.getWeight(v, u) < length) {
                    length = G.getWeight(v, u);
                    closest = u;
                }
            }
            MST.add(new Edge(v, closest, length));
            oddVertices.remove(closest);
        }
        System.out.println(MST);
        Graph H = new Graph();
        for (Edge edge : MST) {
            H.addVertex(edge.getSource());
            H.addVertex(edge.getDestination());
            H.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }
        H.printAdjacencyList();
        Map<String, Map<String, Double>> edgeWeight = G.getEdgeWeight();
        System.out.println(edgeWeight);
        H.isEulerian();
        List<String> tour = HierholzerAlgorithm.findEulerTour(H);
        System.out.println(tour);
        Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);


        return H;


    }





}
