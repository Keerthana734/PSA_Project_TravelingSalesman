package org.example;
import java.util.*;
import org.example.HierholzerAlgorithm;
import org.example.*;
public class MinimumWeightPerfectMatching {

    public static void minimumWeightMatching(List<Edge> MST, Graph G, Set<String> oddVert) {
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
//        Graph A = new Graph();
        for (Edge edge : MST) {
            H.addVertex(edge.getDestination());
            H.addVertex(edge.getSource());
//            A.addVertex(edge.getDestination());
//            A.addVertex(edge.getSource());
            H.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
//            A.addSingleEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }
        H.printAdjacencyList();
//        A.printAdjacencyList();
        Map<String, Map<String, Double>> edgeWeight = G.getEdgeWeight();

//        for (Edge edge : MST) {
//            edgeWeight.putIfAbsent(edge.getSource(), new HashMap<>());
//            edgeWeight.get(edge.getSource()).put(edge.getDestination(), edge.getWeight());
//        }

        System.out.println(edgeWeight);
        List<String> tour = HierholzerAlgorithm.findEulerTour(H);
        System.out.println(tour);
        Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);

        //EulerianCycleFinder e=new EulerianCycleFinder(MST);
       // e.findEulerianCycle();

       // EulerianCycleFinder.findEulerianCycle(H);
       // List<String> cycle=EulerianCycle.findEulerianCycle(H);
        /*if (cycle.size() != H.getNumVertices() + 1) {
            System.out.println("No Eulerian cycle exists in the graph.");
            return;
        }
        System.out.print("Eulerian cycle: ");
        for (String vertex : cycle) {
            System.out.print(vertex + " ");
        }
        System.out.println();*/

    }


}
