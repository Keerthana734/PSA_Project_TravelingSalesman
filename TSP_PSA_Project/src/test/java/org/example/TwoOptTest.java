package org.example;

import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class TwoOptTest {

    @Test
    public void testTwoOpt() {
        // Define a sample graph with edge weights
        Map<String, Map<String, Double>> edgeWeight = new HashMap<>();
        edgeWeight.put("A", new HashMap<>());
        edgeWeight.get("A").put("B", 10.0);
        edgeWeight.get("A").put("C", 15.0);
        edgeWeight.put("B", new HashMap<>());
        edgeWeight.get("B").put("A", 10.0);
        edgeWeight.get("B").put("C", 11.0);
        edgeWeight.put("C", new HashMap<>());
        edgeWeight.get("C").put("A", 15.0);
        edgeWeight.get("C").put("B", 11.0);

        // Define an initial cycle
        List<String> cycle = Arrays.asList("A", "B", "C");

        // Call the twoOpt method and check the output
        List<String> result = TwoOpt.twoOpt(cycle, edgeWeight);
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsAll(Arrays.asList("A", "B", "C")) || result.containsAll(Arrays.asList("A", "C", "B")));
    }
}