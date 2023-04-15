package org.example;

public class Edge {
    private String source;
    private String destination;
    private double weight;

    public Edge(String source, String destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public Edge getReverseEdge() {
        return new Edge(this.destination, this.source, this.weight);
    }


    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public double getWeight() {
        return this.weight;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %f)", this.source, this.destination, this.weight);
    }
}

