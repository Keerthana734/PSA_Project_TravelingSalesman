package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import static org.junit.Assert.*;

public class HierholzerAlgorithmTest {

    @Test
    public void testFindEulerTour() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 1);
        graph.addEdge("C", "D", 1);
        graph.addEdge("D", "A", 1);

        List<String> eulerTour = HierholzerAlgorithm.findEulerTour(graph);
        assertEquals(5, eulerTour.size());
        assertEquals("A", eulerTour.get(0));
        assertEquals("B", eulerTour.get(1));
        assertEquals("C", eulerTour.get(2));
        assertEquals("D", eulerTour.get(3));
        assertEquals("A", eulerTour.get(4));
    }
}
