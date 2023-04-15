package org.example;
import java.util.*;
public class HierholzerAlgorithm {

        public static List<String> findEulerTour(Graph graph) {
                List<String> tour = new ArrayList<>();
                Stack<String> stack = new Stack<>();
                Map<String, List<Edge>> visited = new HashMap<>();
                String startVertex = graph.getVertex(0);
                stack.push(startVertex);

                while (!stack.isEmpty()) {
                        String currentVertex = stack.peek();
                        List<Edge> edges = graph.getEdges(currentVertex);

                        if (!edges.isEmpty()) {
                                Edge edge = edges.get(0);
                                String nextVertex = edge.getDestination();
                                stack.push(nextVertex);
                                graph.removeEdge(currentVertex, nextVertex);
                                graph.removeEdge(nextVertex,currentVertex);

                                // Mark edge as visited
                                visited.computeIfAbsent(currentVertex, k -> new ArrayList<>()).add(edge);
                                visited.computeIfAbsent(nextVertex, k -> new ArrayList<>()).add(edge.getReverseEdge());

                        } else {
                                stack.pop();
                                tour.add(currentVertex);
                        }
                }

                Collections.reverse(tour);

                // Fix Euler tour by removing duplicate edges
                List<String> eulerTour = new ArrayList<>();
                for (int i = 0; i < tour.size() - 1; i++) {
                        String u = tour.get(i);
                        String v = tour.get(i+1);
                        if (!visited.get(u).isEmpty()) {
                                Edge e = visited.get(u).get(0);
                                if (e.getDestination().equals(v)) {
                                        visited.get(u).remove(e);
                                }
                        }
                        eulerTour.add(u);
                }
                eulerTour.add(tour.get(tour.size()-1));

                return tour;
        }



}
