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
        graph.addEdge("A", "B", 1.000000);
        graph.addEdge("B", "C", 2.000000);
        graph.addEdge("C", "A", 3.000000);

        List<Edge> mst = PrimsMST.primMST(graph);
        System.out.println(mst);
        assertEquals(2, mst.size());

    }
}