package org.example;


import java.util.*;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class MinimumWeightPerfectMatchingTest {

    @Test
    public void testMinimumWeightMatching() {
        Graph G = new Graph();
        G.addVertex("A");
        G.addVertex("B");
        G.addVertex("C");
        G.addEdge("A", "B", 2);
        G.addEdge("B", "C", 3);
        G.addEdge("C", "A", 4);

        Set<String> oddVertices = new HashSet<>();
        oddVertices.add("A");
        oddVertices.add("C");

        List<Edge> MST = new ArrayList<>();
        Graph H = MinimumWeightPerfectMatching.minimumWeightMatching(MST, G, oddVertices);


        List<String> oddVerticesList = new ArrayList<>(oddVertices);
        int numOddVertices = oddVerticesList.size();
        int numEdges = H.getAdjacencyList().values().stream().flatMap(List::stream)
                .filter(edge -> oddVertices.contains(edge.getSource()))
                .filter(edge -> oddVertices.contains(edge.getDestination()))
                .toArray().length;
        assertEquals(numOddVertices, numEdges);
    }
}
