package org.example;

import org.junit.Test;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;

public class OddDegreeVerticesTest {

    @Test
    public void testGetOddDegreeVertices() {
        // create a list of edges for a graph with odd degree vertices
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge("A", "B", 5));
        edges.add(new Edge("B", "C", 4));
        edges.add(new Edge("C", "D", 3));
        edges.add(new Edge("D", "E", 2));
        edges.add(new Edge("E", "F", 1));
        edges.add(new Edge("F", "A", 6));
        edges.add(new Edge("B", "F", 7));

        // compute the odd degree vertices
        Set<String> oddDegreeVertices = OddDegreeVertices.getOddDegreeVertices(edges);

        // check that the correct vertices were returned
        Set<String> expected = new HashSet<>();
        expected.add("B");
        expected.add("F");
        assertEquals(expected, oddDegreeVertices);
    }
}
