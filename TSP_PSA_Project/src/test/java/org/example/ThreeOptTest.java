package org.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

public class ThreeOptTest {

    @Test
    public void testThreeOpt() {
        // Define the tour and edge weights for testing
        List<String> tour = new ArrayList<>();
        tour.add("A");
        tour.add("B");
        tour.add("C");
        tour.add("D");
        tour.add("E");

        Map<String, Map<String, Double>> edgeWeight = new HashMap<>();
        Map<String, Double> AB = new HashMap<>();
        AB.put("B", 1.0);
        edgeWeight.put("A", AB);

        Map<String, Double> BA = new HashMap<>();
        BA.put("A", 1.0);
        BA.put("C", 2.0);
        edgeWeight.put("B", BA);

        Map<String, Double> CB = new HashMap<>();
        CB.put("B", 2.0);
        CB.put("D", 3.0);
        edgeWeight.put("C", CB);

        Map<String, Double> DC = new HashMap<>();
        DC.put("C", 3.0);
        DC.put("E", 4.0);
        edgeWeight.put("D", DC);

        Map<String, Double> ED = new HashMap<>();
        ED.put("D", 4.0);
        edgeWeight.put("E", ED);

        // Call the threeOpt() method and check the result
        List<String> result = ThreeOpt.threeOpt(tour, edgeWeight);

        assertEquals("A->B->C->D->E->A", String.join("->", result));
    }
}