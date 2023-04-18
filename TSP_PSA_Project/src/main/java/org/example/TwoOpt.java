package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TwoOpt {
    public static List<String> twoOpt(List<String> cycle, Map<String, Map<String, Double>> edgeWeight) {
        boolean improved = true;
        double totalDistance = computeDistance(cycle, edgeWeight);;
        while (improved) {
            improved = false;
            for (int i = 1; i < cycle.size() - 2; i++) {
                for (int j = i + 1; j < cycle.size() - 1; j++) {
                    List<String> newCycle = twoOptSwap(cycle, i, j);
                    double newDistance = computeDistance(newCycle, edgeWeight);
                    if (newDistance < totalDistance) {
                        cycle = newCycle;
                        improved = true;
                        totalDistance = newDistance;
                    }
                }
            }
        }


        // Print the 2 opt  cycle and the total distance covered in the cycle
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
