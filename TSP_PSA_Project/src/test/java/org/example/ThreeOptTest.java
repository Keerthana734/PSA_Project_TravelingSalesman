package org.example;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.*;

public class ThreeOptTest {

//    @Test
//    public void testThreeOpt() {
//        List<String> tour = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
//        Map<String, Map<String, Double>> edgeWeight = new HashMap<>();
//        edgeWeight.put("A", new HashMap<>());
//        edgeWeight.get("A").put("B", 2.0);
//        edgeWeight.get("A").put("C", 4.0);
//        edgeWeight.get("A").put("D", 3.0);
//        edgeWeight.put("B", new HashMap<>());
//        edgeWeight.get("B").put("A", 2.0);
//        edgeWeight.get("B").put("C", 1.0);
//        edgeWeight.get("B").put("D", 4.0);
//        edgeWeight.put("C", new HashMap<>());
//        edgeWeight.get("C").put("A", 4.0);
//        edgeWeight.get("C").put("B", 1.0);
//        edgeWeight.get("C").put("D", 5.0);
//        edgeWeight.put("D", new HashMap<>());
//        edgeWeight.get("D").put("A", 3.0);
//        edgeWeight.get("D").put("B", 4.0);
//        edgeWeight.get("D").put("C", 5.0);
//
//        List<String> expectedTour = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
//        double expectedDistance = 12.0;
//
//        List<String> actualTour = ThreeOpt.threeOpt(tour, edgeWeight);
//        double actualDistance = ThreeOpt.computeTourDistance(actualTour, edgeWeight);
//
//        assertEquals(expectedTour, actualTour);
//        assertEquals(expectedDistance, actualDistance, 0.00001);
//    }
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

        assertEquals("A->B->C->D->E", String.join("->", result));
    }
}
