package org.example;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class HamiltonianTest {

    @Test
    public void testShortcutEulerianCycle() {
        List<String> eulerianCycle = Arrays.asList("A", "B", "C", "D", "A");
        Map<String, Map<String, Double>> edgeWeight = new HashMap<>();
        edgeWeight.put("A", new HashMap<>());
        edgeWeight.put("B", new HashMap<>());
        edgeWeight.put("C", new HashMap<>());
        edgeWeight.put("D", new HashMap<>());
        edgeWeight.get("A").put("B", 1.0);
        edgeWeight.get("B").put("C", 2.0);
        edgeWeight.get("C").put("D", 3.0);
        edgeWeight.get("D").put("A", 4.0);

        List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerianCycle, edgeWeight);
        List<String> expectedCycle = Arrays.asList("A", "B", "C", "D", "A");
        assertEquals(expectedCycle, hamiltonianCycle);

        double expectedDistance = 10.0;
        double actualDistance = getTotalDistance(hamiltonianCycle, edgeWeight);
        assertEquals(expectedDistance, actualDistance, 0.001);

        // Test for an invalid input where there is no edge between A and C
        edgeWeight.get("A").remove("B");
        assertThrows(IllegalArgumentException.class, () -> {
            Hamiltonian.shortcutEulerianCycle(eulerianCycle, edgeWeight);
        });
    }

    private double getTotalDistance(List<String> cycle, Map<String, Map<String, Double>> edgeWeight) {
        double totalDistance = 0;
        for (int i = 0; i < cycle.size() - 1; i++) {
            String currVertex = cycle.get(i);
            String nextVertex = cycle.get(i + 1);
            Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
            Double edgeDistance = vertexWeights.get(nextVertex);
            if (edgeDistance == null) {
                throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
            }
            totalDistance += edgeDistance;
        }
        return totalDistance;
    }
}
