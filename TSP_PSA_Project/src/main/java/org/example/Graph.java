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

    public void addSingleEdge(String u, String v, double weight) {
        Edge edgeUV = new Edge(u, v, weight);
//        Edge edgeVU = new Edge(v, u, weight);
        this.adjacencyList.get(u).add(edgeUV);
//        this.adjacencyList.get(v).add(edgeVU);
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

    public Map<String, List<Edge>> getAdjacencyList() {
        return this.adjacencyList;
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

    public Map<String, Map<String, Double>> getEdgeWeight() {
        Map<String, Map<String, Double>> edgeWeight = new HashMap<>();
        for (String vertex : this.adjacencyList.keySet()) {
            Map<String, Double> neighbors = new HashMap<>();
            List<Edge> edges = this.adjacencyList.get(vertex);
            for (Edge edge : edges) {
                neighbors.put(edge.getDestination(), edge.getWeight());
            }
            edgeWeight.put(vertex, neighbors);
        }
        return edgeWeight;
    }
    public double[][] convertToAdjacencyMatrix() {
        // Get the number of vertices in the graph
        int n = this.getNumVertices();

        // Initialize the adjacency matrix with zeros
        double[][] adjacencyMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                adjacencyMatrix[i][j] = 0;
            }
        }

        // Populate the adjacency matrix with edge weights
        for (String vertex : this.getVertices()) {
            int i = this.getVertices().indexOf(vertex);
            List<Edge> edges = this.getEdges(vertex);
            for (Edge edge : edges) {
                String destination = edge.getDestination();
                int j = this.getVertices().indexOf(destination);
                adjacencyMatrix[i][j] = edge.getWeight();
            }
        }
         return adjacencyMatrix;
    }
}








