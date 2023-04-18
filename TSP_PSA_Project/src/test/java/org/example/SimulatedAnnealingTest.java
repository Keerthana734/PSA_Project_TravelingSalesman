package org.example;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;


public class SimulatedAnnealingTest {

    @Test
    public void testSimulatedAnnealing() {
        List<String> hamiltonianCycle = new ArrayList<>();
        hamiltonianCycle.add("A");
        hamiltonianCycle.add("B");
        hamiltonianCycle.add("C");
        hamiltonianCycle.add("D");
        hamiltonianCycle.add("E");

        Map<String, Map<String, Double>> edgeWeight = new HashMap<>();
        Map<String, Double> AB = new HashMap<>();
        AB.put("B", 2.0);
        AB.put("C", 5.0);
        AB.put("D", 3.0);
        AB.put("E", 1.0);
        edgeWeight.put("A", AB);
        Map<String, Double> BC = new HashMap<>();
        BC.put("A", 2.0);
        BC.put("C", 2.0);
        BC.put("D", 4.0);
        BC.put("E", 3.0);
        edgeWeight.put("B", BC);
        Map<String, Double> CD = new HashMap<>();
        CD.put("A", 5.0);
        CD.put("B", 2.0);
        CD.put("D", 6.0);
        CD.put("E", 4.0);
        edgeWeight.put("C", CD);
        Map<String, Double> DE = new HashMap<>();
        DE.put("A", 3.0);
        DE.put("B", 4.0);
        DE.put("C", 6.0);
        DE.put("E", 5.0);
        edgeWeight.put("D", DE);
        Map<String, Double> EA = new HashMap<>();
        EA.put("A", 1.0);
        EA.put("B", 3.0);
        EA.put("C", 4.0);
        EA.put("D", 5.0);
        edgeWeight.put("E", EA);

        List<String> result = SimulatedAnnealing.simulatedAnnealing(hamiltonianCycle, edgeWeight);
        assertTrue(result.contains("A"));
        assertTrue(result.contains("E"));
        assertTrue(result.contains("B"));
        assertTrue(result.contains("C"));
        assertTrue(result.contains("D"));
    }

}
