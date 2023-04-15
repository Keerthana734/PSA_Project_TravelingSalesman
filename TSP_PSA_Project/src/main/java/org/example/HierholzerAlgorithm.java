package org.example;


/*public class EulerianCycleFinder {
    private List<Edge> edges;
    private Map<String, List<Integer>> adjacencyList;

    public EulerianCycleFinder(List<Edge> edges) {
        this.edges = edges;
        this.adjacencyList = new HashMap<>();
        buildAdjacencyList();
    }

    private void buildAdjacencyList() {
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            String source = edge.getSource();
            String destination = edge.getDestination();

            // add source to destination's adjacency list
            if (!adjacencyList.containsKey(destination)) {
                adjacencyList.put(destination, new ArrayList<>());
            }
            adjacencyList.get(destination).add(i);

            // add destination to source's adjacency list
            if (!adjacencyList.containsKey(source)) {
                adjacencyList.put(source, new ArrayList<>());
            }
            adjacencyList.get(source).add(i);
        }
    }

    private boolean isBridge(int edgeIndex) {
        // remove the edge and check if the graph becomes disconnected
        Edge removedEdge = edges.remove(edgeIndex);
        buildAdjacencyList();
        boolean isBridge = !isConnected();
        edges.add(edgeIndex, removedEdge);
        buildAdjacencyList();
        return isBridge;
    }

    private boolean isConnected() {
        Set<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.push(edges.get(0).getSource());

        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                for (int i : adjacencyList.get(vertex)) {
                    Edge edge = edges.get(i);
                    String neighbor = vertex.equals(edge.getSource()) ? edge.getDestination() : edge.getSource();
                    stack.push(neighbor);
                }
            }
        }

        return visited.size() == adjacencyList.size();
    }

    private List<Integer> getNeighboringEdges(int vertexIndex) {
        List<Integer> neighboringEdges = new ArrayList<>();
        String vertex = edges.get(vertexIndex).getSource();
        for (int i : adjacencyList.get(vertex)) {
            if (i != vertexIndex) {
                neighboringEdges.add(i);
            }
        }
        return neighboringEdges;
    }

    public List<Edge> findEulerianCycle() {
        List<Edge> eulerianCycle = new ArrayList<>();

        // find a vertex with odd degree (if any)
        int startVertexIndex = -1;
        for (int i = 0; i < edges.size(); i++) {
            if (getNeighboringEdges(i).size() % 2 != 0) {
                startVertexIndex = i;
                break;
            }
        }

        // if there are no vertices with odd degree, start at any vertex
        if (startVertexIndex == -1) {
            startVertexIndex = 0;
        }

        // start DFS from the starting vertex
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertexIndex);

        while (!stack.isEmpty()) {
            int currentEdgeIndex = stack.peek();
            List<Integer> neighboringEdges = getNeighboringEdges(currentEdgeIndex);

            // if there are no neighboring edges, add the current edge to the cycle
            if (neighboringEdges.isEmpty()) {
                eulerianCycle.add(edges.get(currentEdgeIndex));
                stack.pop();
            } else {
                // otherwise, try to traverse a non-bridge edge
                for (int i : neighboringEdges) {
                    if (!isBridge(i)) {
                        stack.push(i);
                        edges.remove(i);
                        buildAdjacencyList();
                        break;
                    }
                }
            }
        }
        return eulerianCycle;
    }
}*/


/*public class EulerianCycle {
    public static List<String> findEulerianCycle(Graph graph) {
        List<String> cycle = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        Map<String, Integer> unexploredEdges = new HashMap<>();
        for (String vertex : graph.getVertices()) {
            unexploredEdges.put(vertex, graph.getEdges(vertex).size());
        }
        String startVertex = graph.getVertex(0);
        stack.push(startVertex);
        while (!stack.isEmpty()) {
            String v = stack.peek();
            if (unexploredEdges.get(v) == 0) {
                stack.pop();
                cycle.add(v);
            } else {
                Edge nextEdge = graph.getEdges(v).get(unexploredEdges.get(v) - 1);
                unexploredEdges.put(v, unexploredEdges.get(v) - 1);
                stack.push(nextEdge.getDestination());
                graph.getEdges(v).remove(nextEdge);
                graph.getEdges(nextEdge.getDestination()).removeIf(e -> e.getSource().equals(v) && e.getDestination().equals(nextEdge.getDestination()));
            }
        }
        Collections.reverse(cycle);
        Hamiltonian.Hamiltonin(cycle,graph);
        for (String str : cycle) {
            System.out.print(str+" ->");
        }
        return cycle;
    }

}*/


/*import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EulerianCycle {

    //private Graph graph;

    //public EulerianCycle(Graph graph) {
       // this.graph = graph;
    //}

    public static List<String> findEulerianCycle(Graph graph) {
        List<String> cycle = new LinkedList<>();
        Map<String, List<Edge>> adjacencyListCopy = new HashMap<>(graph.getAdjacencyList());

        String startVertex = graph.getVertex(0);
        cycle.add(startVertex);

        while (!cycle.isEmpty()) {
            String currentVertex = cycle.get(cycle.size() - 1);
            List<Edge> edges = adjacencyListCopy.get(currentVertex);

            if (edges != null && !edges.isEmpty()) {
                Edge edge = edges.get(0);
                String nextVertex = edge.getDestination();

                adjacencyListCopy.get(currentVertex).remove(edge);
                cycle.add(nextVertex);
            } else {
                cycle.remove(cycle.size() - 1);
            }
        }

        // Check if all edges have been visited
        for (String vertex : graph.getVertices()) {
            List<Edge> edges = adjacencyListCopy.get(vertex);
            if (edges != null && !edges.isEmpty()) {
                return null; // Graph has no Eulerian cycle
            }
        }
        System.out.println("hi keerthana " + cycle.size());
        // Verify that the cycle starts and ends at the same vertex
        String lastVertex = cycle.get(cycle.size() - 1);
        String firstVertex = cycle.get(0);
        List<Edge> edges = adjacencyListCopy.get(lastVertex);
        for (Edge edge : edges) {
            if (edge.getDestination().equals(firstVertex)) {
                cycle.add(firstVertex);
                return cycle;
            }
        }

        return null; // Graph has no Eulerian cycle
    }
}*/

   /* public static List<String> EulerTour(Graph G) {
        // Check if the graph is Eulerian
        if (!G.isEulerian()) {
            return null;
        }

        // Create a stack to store the vertices in the Euler tour
        Stack<String> stack = new Stack<>();

        // Start at any vertex
        String startVertex = G.getVertices().get(0);
        stack.push(startVertex);

        // While the stack is not empty, do the following:
        while (!stack.isEmpty()) {
            // Pop the top vertex from the stack
            String currentVertex = stack.pop();

            // Get all the edges connected to the current vertex
            List<Edge> edges = G.getEdges(currentVertex);

            // For each edge connected to the current vertex, do the following:
            for (Edge edge : edges) {
                // If the destination vertex of the edge is not in the stack, do the following:
                if (!stack.contains(edge.getDestination())) {
                    // Push the destination vertex onto the stack
                    stack.push(edge.getDestination());
                }
            }
        }

        // The Euler tour is the list of vertices in the stack in reverse order
        List<String> eulerTour = new ArrayList<>();
        while (!stack.isEmpty()) {
            eulerTour.add(stack.pop());
        }

        // Reverse the order of the Euler tour
        Collections.reverse(eulerTour);

        // Return the Euler tour
        for (String str : eulerTour) {
            System.out.println(str);
        }
        return eulerTour;
    }





}*/
/*public class EulerianCycleFinder {
}
private List<City> findEulerianCircuit(List<Edge> multigraph) {
        Map<City, List<City>> adjacencyList = buildAdjacencyList(multigraph);
        List<City> tour = new ArrayList<>();
        Stack<City> stack = new Stack<>();
        City startVertex = multigraph.get(0).source;

        stack.push(startVertex);
        while (!stack.isEmpty()) {
        City currentVertex = stack.peek();
        if (adjacencyList.get(currentVertex).size() > 0) {
        City nextVertex = adjacencyList.get(currentVertex).iterator().next();
        stack.push(nextVertex);
        adjacencyList.get(currentVertex).remove(nextVertex);
        adjacencyList.get(nextVertex).remove(currentVertex);
        } else {
        stack.pop();
        tour.add(currentVertex);
        }
        }
        return tour;
        }
   }*/

import java.util.*;

public class HierholzerAlgorithm {

//        public static List<String> findEulerTour(Graph graph) {
//                List<String> tour = new ArrayList<>();
//                Stack<String> stack = new Stack<>();
//                String startVertex = graph.getVertex(0);
//                stack.push(startVertex);
//
//                while (!stack.isEmpty()) {
//                        String currentVertex = stack.peek();
//                        List<Edge> edges = graph.getEdges(currentVertex);
//
//                        if (!edges.isEmpty()) {
//                                Edge edge = edges.get(0);
//                                String nextVertex = edge.getDestination();
//                                stack.push(nextVertex);
//                                graph.removeEdge(currentVertex, nextVertex);
//                        } else {
//                                stack.pop();
//                                tour.add(currentVertex);
//                        }
//                }
//
//                Collections.reverse(tour);
//                return tour;
//        }

//        public static List<String> findEulerTour(Graph graph) {
//                List<String> tour = new ArrayList<>();
//                Stack<String> stack = new Stack<>();
//                Map<String, List<Edge>> visited = new HashMap<>();
//                String startVertex = graph.getVertex(0);
//                stack.push(startVertex);
//
//                while (!stack.isEmpty()) {
//                        String currentVertex = stack.peek();
//                        List<Edge> edges = graph.getEdges(currentVertex);
//
//                        if (!edges.isEmpty()) {
//                                Edge edge = edges.get(0);
//                                String nextVertex = edge.getDestination();
//                                stack.push(nextVertex);
//                                graph.removeEdge(currentVertex, nextVertex);
//
//                                // Mark edge as visited
//                                visited.computeIfAbsent(currentVertex, k -> new ArrayList<>()).add(edge);
//                                visited.computeIfAbsent(nextVertex, k -> new ArrayList<>()).add(edge.getReverseEdge());
//                        } else {
//                                stack.pop();
//                                tour.add(currentVertex);
//                        }
//                }
//
//                Collections.reverse(tour);
//
//                // Fix Euler tour by removing duplicate edges
//                List<String> eulerTour = new ArrayList<>();
//                for (int i = 0; i < tour.size() - 1; i++) {
//                        String u = tour.get(i);
//                        String v = tour.get(i+1);
//                        if (!visited.get(u).contains(new Edge(u, v, 0))) {
//                                eulerTour.add(u);
//                        }
//                }
//                eulerTour.add(tour.get(tour.size()-1));
//
//                return eulerTour;
//        }

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
//                System.out.println("console");
        return tour;
    }



}