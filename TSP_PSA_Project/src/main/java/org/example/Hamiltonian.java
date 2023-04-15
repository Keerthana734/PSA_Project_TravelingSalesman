package org.example;
import java.util.*;

public class Hamiltonian {
    public static List<String> shortcutEulerianCycle(List<String> eulerianCycle, Map<String, Map<String, Double>> edgeWeight) {

        // Create a HashSet to keep track of visited vertices
        Set<String> visited = new HashSet<>();

        // Create an empty ArrayList to store the Hamiltonian cycle
        List<String> hamiltonianCycle = new ArrayList<>();

        // Iterate over the vertices in the Eulerian cycle
        for (String vertex : eulerianCycle) {
            // If the vertex hasn't been visited yet, add it to the Hamiltonian cycle
            if (!visited.contains(vertex)) {
                hamiltonianCycle.add(vertex);
                visited.add(vertex);
            }
        }
        hamiltonianCycle.add(eulerianCycle.get(0));
        //Compute the total distance covered in the cycle

        double totalDistance = 0;
        for (int i = 0; i < hamiltonianCycle.size() - 1; i++) {
            String currVertex = hamiltonianCycle.get(i);
            String nextVertex = hamiltonianCycle.get(i + 1);
            Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
            Double edgedistance = vertexWeights.get(nextVertex);
            if (edgedistance == null) {
                // Handle the case where there is no edge between the current and next vertices
                throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
            }
            totalDistance += edgedistance;
        }


        // Print the Hamiltonian cycle and the total distance covered in the cycle
       // System.out.println("Hamiltonian cycle: " + String.join("->", hamiltonianCycle));
        System.out.println("Total distance covered: " + totalDistance);

        // Return the Hamiltonian cycle
        return hamiltonianCycle;
    }
}
