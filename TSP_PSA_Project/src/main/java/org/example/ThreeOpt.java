package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ThreeOpt {

    public static List<String> threeOpt(List<String> tour, Map<String, Map<String, Double>> edgeWeight) {
        boolean improvement = true;
        double totalDistance = computeTourDistance(tour, edgeWeight);;
        while (improvement) {
            improvement = false;
            for (int i = 0; i < tour.size() - 2; i++) {
                for (int j = i + 2; j < tour.size() - 1; j++) {
                    for (int k = j + 2; k < tour.size(); k++) {
                        List<String> newTour = reverseSubtour(tour, i + 1, j, k);
                        double newDistance = computeTourDistance(newTour, edgeWeight);

                        if (newDistance < totalDistance) {
                            tour = newTour;
                            improvement = true;
                            totalDistance = newDistance;
                        }
                    }
                }
            }
        }

        // Print the 3 opt  cycle and the total distance covered in the cycle
        System.out.println("3 opt cycle: " + String.join("->", tour));
        System.out.println("Total distance covered 3 opt: " + totalDistance);

        return tour;
    }

    private static List<String> reverseSubtour(List<String> tour, int i, int j, int k) {
        List<String> newTour = new ArrayList<>(tour.subList(0, i));
        newTour.addAll(reverseList(tour.subList(i, j + 1)));
        newTour.addAll(tour.subList(j + 1, k));
        newTour.addAll(reverseList(tour.subList(k, tour.size())));
        return newTour;
    }

    private static List<String> reverseList(List<String> list) {
        List<String> reversedList = new ArrayList<>(list);
        Collections.reverse(reversedList);
        return reversedList;
    }

    private static double computeTourDistance(List<String> tour, Map<String, Map<String, Double>> edgeWeight) {
        double distance = 0;

        for (int i = 0; i < tour.size() - 1; i++) {
            String currVertex = tour.get(i);
            String nextVertex = tour.get(i + 1);
            Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
            Double edgeDistance = vertexWeights.get(nextVertex);
            if (edgeDistance == null) {
                return Double.MAX_VALUE;
            }
            distance += edgeDistance;
        }

        return distance;
    }
}
