package org.example;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class PrimsMSTTest {

    @Test
    public void testPrimMST() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B", 1.0);
        graph.addEdge("B", "C", 2.0);
        graph.addEdge("C", "A", 3.0);

        List<Edge> mst = PrimsMST.primMST(graph);
        assertEquals(2, mst.size());
        assertTrue(PrimsMST.contains(mst, new Edge("A", "B", 1.0)));
        assertTrue(PrimsMST.contains(mst, new Edge("B", "C", 2.0)));
    }
}
