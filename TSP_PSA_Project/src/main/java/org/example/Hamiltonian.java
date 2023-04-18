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
        List<String> twoOptstring = twoOpt(hamiltonianCycle, edgeWeight);
        List<String> threeOptstring =threeOpt(hamiltonianCycle,edgeWeight );
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

    public static List<String> threeOpt(List<String> tour, Map<String, Map<String, Double>> edgeWeight) {
        boolean improvement = true;

        while (improvement) {
            improvement = false;

            for (int i = 0; i < tour.size() - 2; i++) {
                for (int j = i + 2; j < tour.size() - 1; j++) {
                    for (int k = j + 2; k < tour.size(); k++) {
                        List<String> newTour = reverseSubtour(tour, i + 1, j, k);
                        double oldDistance = computeTourDistance(tour, edgeWeight);
                        double newDistance = computeTourDistance(newTour, edgeWeight);

                        if (newDistance < oldDistance) {
                            tour = newTour;
                            improvement = true;
                        }
                    }
                }
            }
        }

        double totalDistance = 0;
        for (int i = 0; i < tour.size() - 1; i++) {
            String currVertex = tour.get(i);
            String nextVertex = tour.get(i + 1);
            Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
            Double edgedistance = vertexWeights.get(nextVertex);
            if (edgedistance == null) {
                // Handle the case where there is no edge between the current and next vertices
                throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
            }
            totalDistance += edgedistance;
        }

        // Print the Hamiltonian cycle and the total distance covered in the cycle
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