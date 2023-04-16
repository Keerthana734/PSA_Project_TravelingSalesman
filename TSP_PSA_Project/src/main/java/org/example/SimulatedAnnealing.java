package org.example;

import java.util.*;
import java.lang.*;

//public class SimulatedAnnealing {
//    private static final double INITIAL_TEMPERATURE = 1000;
//    private static final double COOLING_RATE = 0.003;
//    private static final int NUM_ITERATIONS = 20000;
//
//    public static List<String> simulatedAnnealing(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
//        // Initialize the current and best Hamiltonian cycles
//        List<String> currentHamiltonianCycle = new ArrayList<>(hamiltonianCycle);
//        List<String> bestHamiltonianCycle = new ArrayList<>(hamiltonianCycle);
//
//        // Initialize the current and best distances
//        double currentDistance = computeDistance(currentHamiltonianCycle, edgeWeight);
//        double bestDistance = currentDistance;
//
//        // Initialize the temperature
//        double temperature = INITIAL_TEMPERATURE;
//
//        // Iterate for the specified number of iterations
//        for (int i = 0; i < NUM_ITERATIONS; i++) {
//            // Generate a new candidate Hamiltonian cycle by swapping two random vertices
//            List<String> candidateHamiltonianCycle = generateCandidate(currentHamiltonianCycle);
//
//            // Compute the distance of the candidate Hamiltonian cycle
//            double candidateDistance = computeDistance(candidateHamiltonianCycle, edgeWeight);
//
//            // Calculate the acceptance probability
//            double acceptanceProbability = calculateAcceptanceProbability(currentDistance, candidateDistance, temperature);
//
//            // Determine whether to accept the candidate Hamiltonian cycle
//            if (acceptanceProbability >= Math.random()) {
//                currentHamiltonianCycle = candidateHamiltonianCycle;
//                currentDistance = candidateDistance;
//            }
//
//            // Update the best Hamiltonian cycle and distance
//            if (currentDistance < bestDistance) {
//                bestHamiltonianCycle = currentHamiltonianCycle;
//                bestDistance = currentDistance;
//            }
//
//            // Cool the temperature
//            temperature *= 1 - COOLING_RATE;
//        }
//
//        // Print the best Hamiltonian cycle and distance
//        System.out.println("Best Hamiltonian cycle: " + String.join("->", bestHamiltonianCycle));
//        System.out.println("Best distance covered: " + bestDistance);
//
//        // Return the best Hamiltonian cycle
//        return bestHamiltonianCycle;
//    }
//    private static double computeDistance(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
//        double totalDistance = 0;
//        Map<String, Double> vertexDistances = new HashMap<>();
//        for (int i = 0; i < hamiltonianCycle.size() - 1; i++) {
//            String currVertex = hamiltonianCycle.get(i);
//            String nextVertex = hamiltonianCycle.get(i + 1);
//            Double distance = vertexDistances.get(nextVertex);
//            if (distance == null) {
//                Map<String, Double> vertexWeights = edgeWeight.get(currVertex);
//                if (vertexWeights == null) {
//                    throw new IllegalArgumentException("No edges from vertex " + currVertex);
//                }
//                distance = vertexWeights.get(nextVertex);
//                if (distance == null) {
//                    // Handle the case where there is no edge between the current and next vertices
//                    throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
//                }
//                vertexDistances.put(nextVertex, distance);
//            }
//            totalDistance += distance;
//        }
//        return totalDistance;
//    }
//
//
//
//
//    private static List<String> generateCandidate(List<String> currentHamiltonianCycle) {
//        // Create a copy of the current Hamiltonian cycle
//        List<String> candidateHamiltonianCycle = new ArrayList<>(currentHamiltonianCycle);
//
//        // Generate two random indices
//        int index1 = (int) (Math.random() * currentHamiltonianCycle.size());
//        int index2 = (int) (Math.random() * currentHamiltonianCycle.size());
//
//        // Swap the vertices at the two indices
//        String temp = candidateHamiltonianCycle.get(index1);
//        candidateHamiltonianCycle.set(index1, candidateHamiltonianCycle.get(index2));
//        candidateHamiltonianCycle.set(index2, temp);
//
//        // Return the modified candidate Hamiltonian cycle
//        return candidateHamiltonianCycle;
//    }
//
//    private static double calculateAcceptanceProbability(double currentDistance, double candidateDistance, double temperature) {
//        if (candidateDistance < currentDistance) {
//            return 1.0;
//        } else {
//            return Math.exp((currentDistance - candidateDistance) / temperature);
//        }
//    }
//}

public class SimulatedAnnealing {
    private static final double INITIAL_TEMPERATURE = 1000;
    private static final double COOLING_RATE = 0.003;
    private static final int NUM_ITERATIONS = 20000;

    public static List<String> simulatedAnnealing(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
        List<String> currentHamiltonianCycle = new ArrayList<>(hamiltonianCycle);
        List<String> bestHamiltonianCycle = new ArrayList<>(hamiltonianCycle);

        double currentDistance = computeDistance(currentHamiltonianCycle, edgeWeight);
        double bestDistance = currentDistance;

        double temperature = INITIAL_TEMPERATURE;
        Random random = new Random();

        for (int i = 0; i < NUM_ITERATIONS; i++) {
            List<String> candidateHamiltonianCycle = generateCandidate(currentHamiltonianCycle, random);

            double candidateDistance = computeDistance(candidateHamiltonianCycle, edgeWeight);

            double acceptanceProbability = calculateAcceptanceProbability(currentDistance, candidateDistance, temperature);

            if (acceptanceProbability >= random.nextDouble()) {
                currentHamiltonianCycle = candidateHamiltonianCycle;
                currentDistance = candidateDistance;
            }

            if (currentDistance < bestDistance) {
                bestHamiltonianCycle = currentHamiltonianCycle;
                bestDistance = currentDistance;
            }

            temperature *= 1 - COOLING_RATE;
        }

        System.out.println("Best Hamiltonian cycle: " + String.join("->", bestHamiltonianCycle));
        System.out.println("Best distance covered: " + bestDistance);


        return bestHamiltonianCycle;
    }

    private static double computeDistance(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
        double totalDistance = 0;
        Map<String, Double> vertexDistances = new HashMap<>();

        for (int i = 0; i < hamiltonianCycle.size() - 1; i++) {
            String currVertex = hamiltonianCycle.get(i);
            String nextVertex = hamiltonianCycle.get(i + 1);
            Double distance = vertexDistances.get(nextVertex);

            if (distance == null) {
                Map<String, Double> vertexWeights = edgeWeight.get(currVertex);

                if (vertexWeights == null) {
                    throw new IllegalArgumentException("No edges from vertex " + currVertex);
                }

                distance = vertexWeights.get(nextVertex);

                if (distance == null) {
                    throw new IllegalArgumentException("No edge between " + currVertex + " and " + nextVertex);
                }

                vertexDistances.put(nextVertex, distance);
            }

            totalDistance += distance;
        }

        return totalDistance;
    }

    private static List<String> generateCandidate(List<String> currentHamiltonianCycle, Random random) {
        List<String> candidateHamiltonianCycle = new ArrayList<>(currentHamiltonianCycle);

        int index1 = random.nextInt(currentHamiltonianCycle.size());
        int index2 = random.nextInt(currentHamiltonianCycle.size());

        Collections.swap(candidateHamiltonianCycle, index1, index2);

        return candidateHamiltonianCycle;
    }

    private static double calculateAcceptanceProbability(double currentDistance, double candidateDistance, double temperature) {
        if (candidateDistance < currentDistance) {
            return 1.0;
        } else {
            return Math.exp((currentDistance - candidateDistance) / temperature);
        }
    }
}
