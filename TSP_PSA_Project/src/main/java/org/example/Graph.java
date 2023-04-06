package org.example;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
}

