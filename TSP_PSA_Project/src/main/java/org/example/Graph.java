package org.example;
import java.util.*;

import org.example.Edge;

public class Graph {
    private Map<String, List<Edge>> adjacencyList;
    private int numVertices;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.numVertices = 0;
    }

    public void addVertex(String vertex) {
        if (!this.adjacencyList.containsKey(vertex)) {
            this.adjacencyList.put(vertex, new ArrayList<>());
            this.numVertices++;
        }
    }

    public void addEdge(String u, String v, double weight) {
        Edge edgeUV = new Edge(u, v, weight);
        Edge edgeVU = new Edge(v, u, weight);
        this.adjacencyList.get(u).add(edgeUV);
        this.adjacencyList.get(v).add(edgeVU);
    }

    public List<Edge> getEdges(String vertex) {
        return this.adjacencyList.get(vertex);
    }

    public int getNumVertices() {
        return this.numVertices;
    }

    public List<String> getVertices() {
        List<String> vertices = new ArrayList<>(this.adjacencyList.keySet());
        return vertices;
    }

    public String getVertex(int index) {
        List<String> vertices = getVertices();
        return vertices.get(index);
    }
    public void printAdjacencyList() {
        for (String vertex : this.adjacencyList.keySet()) {
            System.out.print(vertex + ": ");
            List<Edge> edges = this.adjacencyList.get(vertex);
            for (Edge edge : edges) {
                System.out.print(edge.getDestination() + " (" + edge.getWeight() + ") ");
            }
            System.out.println();
        }
    }
    public double getWeight(String u, String v) {
        List<Edge> edges = this.adjacencyList.get(u);
        for (Edge edge : edges) {
            if (edge.getDestination().equals(v)) {
                return edge.getWeight();
            }
        }
        // If no edge exists between u and v, return infinity
        return Double.POSITIVE_INFINITY;
    }
    public boolean isEulerian() {
        // Check if the graph is connected
        if (!this.isConnected()) {
            return false;
        }

        // Check if the degree of each vertex is even
        for (String vertex : this.getVertices()) {
            if (this.getEdges(vertex).size() % 2 != 0) {
                return false;
            }
        }

        // If all of the above conditions are met, then the graph is Eulerian
        return true;
    }





    private boolean isConnected() {
        // Create a visited set
        Set<String> visited = new HashSet<>();

        // Start at any vertex
        String startVertex = this.getVertices().get(0);

        // Do a depth-first search from the start vertex
        dfs(startVertex, visited);

        // If all vertices have been visited, then the graph is connected
        return visited.size() == this.numVertices;
    }

    private void dfs(String vertex, Set<String> visited) {
        // Mark the vertex as visited
        visited.add(vertex);

        // For each neighbor of the vertex, do the following:
        for (Edge edge : this.getEdges(vertex)) {
            String neighbor = edge.getDestination();

            // If the neighbor has not been visited, do the following:
            if (!visited.contains(neighbor)) {
                // Do a depth-first search from the neighbor
                dfs(neighbor, visited);
            }
        }
    }

    public Map<String, List<Edge>> getAdjacencyList() {
        return this.adjacencyList;
    }

    public int getNumEdges() {
        int count = 0;
        for (List<Edge> edges : this.adjacencyList.values()) {
            count += edges.size();
        }
        // Divide by 2 since each undirected edge is represented by 2 directed edges
        return count / 2;
    }



    public void removeEdge(String u, String v) {
        List<Edge> edges = this.adjacencyList.get(u);
        Edge edgeToRemove = null;

        for (Edge edge : edges) {
            if (edge.getDestination().equals(v)) {
                edgeToRemove = edge;
                break;
            }
        }

        if (edgeToRemove != null) {
            edges.remove(edgeToRemove);
        }
    }

}

