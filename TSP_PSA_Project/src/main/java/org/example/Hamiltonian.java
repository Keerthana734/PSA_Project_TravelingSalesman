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
//
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
        System.out.println("Hamiltonian cycle: " + String.join("->", hamiltonianCycle));
        System.out.println("Total distance covered: " + totalDistance);
        twoOpt(hamiltonianCycle, edgeWeight);
        // Return the Hamiltonian cycle
        return hamiltonianCycle;
    }

    public static List<String> twoOpt(List<String> cycle, Map<String, Map<String, Double>> edgeWeight) {
        boolean improved = true;
        while (improved) {
            improved = false;
            for (int i = 1; i < cycle.size() - 2; i++) {
                for (int j = i + 1; j < cycle.size() - 1; j++) {
                    List<String> newCycle = twoOptSwap(cycle, i, j);
                    double newDistance = computeDistance(newCycle, edgeWeight);
                    double oldDistance = computeDistance(cycle, edgeWeight);
                    if (newDistance < oldDistance) {
                        cycle = newCycle;
                        improved = true;
                        System.out.println("2 opt 2 opt" + newDistance);
                    }
                }
            }
        }

        double totalDistance = 0;
        for (int i = 0; i < cycle.size() - 1; i++) {
            String currVertex = cycle.get(i);
            String nextVertex = cycle.get(i + 1);
            Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
            Double edgedistance = vertexWeights.get(nextVertex);
            if (edgedistance == null) {
                // Handle the case where there is no edge between the current and next vertices
                throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
            }
            totalDistance += edgedistance;
        }

        // Print the Hamiltonian cycle and the total distance covered in the cycle
        System.out.println("2 opt cycle: " + String.join("->", cycle));
        System.out.println("Total distance covered: " + totalDistance);

        return cycle;
    }

    private static List<String> twoOptSwap(List<String> cycle, int i, int j) {
        List<String> newCycle = new ArrayList<>(cycle.subList(0, i));
        List<String> reversed = new ArrayList<>(cycle.subList(i, j + 1));
        Collections.reverse(reversed);
        newCycle.addAll(reversed);
        newCycle.addAll(cycle.subList(j + 1, cycle.size()));
        return newCycle;
    }

    private static double computeDistance(List<String> cycle, Map<String, Map<String, Double>> edgeWeight) {
        double totalDistance = 0;
        for (int i = 0; i < cycle.size() - 1; i++) {
            String currVertex = cycle.get(i);
            String nextVertex = cycle.get(i + 1);
            Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
            Double edgeDistance = vertexWeights.get(nextVertex);
            if (edgeDistance == null) {
                // Handle the case where there is no edge between the current and next vertices
                throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
            }
            totalDistance += edgeDistance;
        }
        return totalDistance;
    }

}