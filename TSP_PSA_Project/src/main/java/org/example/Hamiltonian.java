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
        // Compute the total distance covered in the cycle
//        double totalDistance = 0;
//        for (int i = 0; i < hamiltonianCycle.size(); i++) {
//            // Get the indices of the current and next vertices in the cycle
//            int j = (i + 1) % hamiltonianCycle.size();
//            // Compute the distance between the current and next vertices using the edge weights
//            double distance = edgeWeight.get(hamiltonianCycle.get(i)).get(hamiltonianCycle.get(j));
//            // Add the distance to the total distance covered
//            totalDistance += distance;
//        }

        // Print the Hamiltonian cycle and the total distance covered in the cycle
        System.out.println("Hamiltonian cycle: " + String.join("->", hamiltonianCycle));
        //System.out.println("Total distance covered: " + totalDistance);

        // Return the Hamiltonian cycle
        return hamiltonianCycle;
    }
}
