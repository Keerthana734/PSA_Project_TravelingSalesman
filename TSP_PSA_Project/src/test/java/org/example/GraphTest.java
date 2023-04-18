package org.example;
import org.junit.Test;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GraphTest {

    @Test
    public void testAddVertex() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        assertEquals(3, g.getNumVertices());
        assertTrue(g.getVertices().contains("A"));
        assertTrue(g.getVertices().contains("B"));
        assertTrue(g.getVertices().contains("C"));
    }

    @Test
    public void testAddEdge() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addEdge("A", "B", 2.0);
        List<Edge> edgesA = g.getEdges("A");
        List<Edge> edgesB = g.getEdges("B");
        assertEquals(1, edgesA.size());
        assertEquals(1, edgesB.size());
        assertEquals("B", edgesA.get(0).getDestination());
        assertEquals("A", edgesB.get(0).getDestination());
        assertEquals(2.0, edgesA.get(0).getWeight(), 0.0);
        assertEquals(2.0, edgesB.get(0).getWeight(), 0.0);
    }

    @Test
    public void testAddSingleEdge() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addSingleEdge("A", "B", 2.0);
        List<Edge> edgesA = g.getEdges("A");
        List<Edge> edgesB = g.getEdges("B");
        assertEquals(1, edgesA.size());
        assertEquals(0, edgesB.size());
        assertEquals("B", edgesA.get(0).getDestination());
        assertEquals(2.0, edgesA.get(0).getWeight(), 0.0);
    }

    @Test
    public void testGetWeight() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addEdge("A", "B", 2.0);
        g.addEdge("B", "C", 3.0);
        assertEquals(2.0, g.getWeight("A", "B"), 0.0);
        assertEquals(2.0, g.getWeight("B", "A"), 0.0);
        assertEquals(3.0, g.getWeight("B", "C"), 0.0);
        assertEquals(Double.POSITIVE_INFINITY, g.getWeight("A", "C"), 0.0);
    }

    @Test
    public void testRemoveEdge() {
        Graph g = new Graph();
        g.addVertex("A");
        g.addVertex("B");
        g.addVertex("C");
        g.addEdge("A", "B", 2.0);
        g.addEdge("B", "C", 3.0);
        g.removeEdge("A", "B");
        List<Edge> edgesA = g.getEdges("A");
        List<Edge> edgesB = g.getEdges("B");
        assertEquals(0, edgesA.size());
        assertEquals(1, edgesB.size());
        assertEquals("C", edgesB.get(0).getDestination());
        assertEquals(3.0, edgesB.get(0).getWeight(), 0.0);
    }


}
