package org.example;

import java.util.*;
import java.lang.*;

public class SimulatedAnnealing {

    private static final int NUM_ITERATIONS = 20000;

    public static List<String> simulatedAnnealing(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
        // Initialize the current and best Hamiltonian cycles
        List<String> currentHamiltonianCycle = new ArrayList<>(hamiltonianCycle);
        List<String> simulatedAnnealingCycle = new ArrayList<>(hamiltonianCycle);

        // Initialize the current and best distances
        double currentDistance = computeDistance(currentHamiltonianCycle, edgeWeight);
        double bestDistance = currentDistance;

        // Initialize the temperature
        double temperature = 1000;

        // Initialize the coolingRate
        double coolingRate = 0.003;

        // Iterate for the specified number of iterations
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            // Generate a new candidate Hamiltonian cycle
            List<String> candidateHamiltonianCycle = generateCandidate(currentHamiltonianCycle, edgeWeight);

            // Compute the distance of the candidate Hamiltonian cycle
            double candidateDistance = computeDistance(candidateHamiltonianCycle, edgeWeight);

            // Calculate the acceptance probability
            double acceptanceProbability = calculateAcceptanceProbability(currentDistance, candidateDistance, temperature);

            // Determine whether to accept the candidate Hamiltonian cycle
            if (acceptanceProbability >= Math.random()) {
                currentHamiltonianCycle = candidateHamiltonianCycle;
                currentDistance = candidateDistance;
            }

            // Update the best simulatedAnnealingCycle and distance
            if (currentDistance < bestDistance) {
                simulatedAnnealingCycle = currentHamiltonianCycle;
                bestDistance = currentDistance;
            }

            // Update the temperature
            temperature = updateTemperature(temperature, i);

        }

        // Print the best Hamiltonian cycle and distance
        System.out.println("Best cycle in simulatedAnnealing: " + simulatedAnnealingCycle + "\nDistance: " + bestDistance * 1000.0);
        // Return the best simulatedAnnealingCycle
        return simulatedAnnealingCycle;
    }

    private static double computeDistance(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
        double distance = 0.0;
        int n = hamiltonianCycle.size();
        for (int i = 0; i < n; i++) {
            String start = hamiltonianCycle.get(i);
            String end = hamiltonianCycle.get((i + 1) % n);
            Double weight = 0.0;
            if( start == end ) {
                weight = 0.0;
            } else {
                weight = edgeWeight.get(start).get(end); }
            if (weight == null) {
                // Handle missing weight for edge
                System.err.println("Error: Missing weight for edge " + start + " -> " + end);
                continue;
            }
            distance += (weight != null) ? weight.doubleValue() : 0.0;
        }
        return distance;
    }



    private static double computeInitialTemperature(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
        // Compute the average distance between adjacent cities in the Hamiltonian cycle
        double totalDistance = 0;
        int n = hamiltonianCycle.size();
        for (int i = 0; i < n; i++) {
            String start = hamiltonianCycle.get(i);
            String end = hamiltonianCycle.get((i + 1) % n);
            Double weight = 0.0;
            if( start == end ) {
                weight = 0.0;
            } else {
             weight = edgeWeight.get(start).get(end); }
            if (weight == null) {
                // Handle missing weight for edge
                System.err.println("Error: Missing weight for edge " + start + " -> " + end);
                continue;
            }
            totalDistance += weight;
        }
        double averageDistance = totalDistance / n;

        // Set the initial temperature to be 10 times the average distance
        return 10 * averageDistance;
    }


    private static List<String> generateCandidate(List<String> hamiltonianCycle, Map<String, Map<String, Double>> edgeWeight) {
        // Randomly swap two cities in the Hamiltonian cycle
        List<String> candidateHamiltonianCycle = new ArrayList<>(hamiltonianCycle);
        int index1 = (int) (Math.random() * hamiltonianCycle.size());
        int index2 = (int) (Math.random() * hamiltonianCycle.size());
        Collections.swap(candidateHamiltonianCycle, index1, index2);

        return candidateHamiltonianCycle;
    }

    private static double calculateAcceptanceProbability(double currentDistance, double candidateDistance, double temperature) {
        // If the candidate is better, accept it
        if (candidateDistance < currentDistance) {
            return 1.0;
        }

        // Otherwise, calculate the acceptance probability based on the temperature and the difference in distance
        double delta = candidateDistance - currentDistance;
        return Math.exp(-delta / temperature);
    }

    private static double updateTemperature(double temperature, int iteration) {
        // Update the temperature using a cooling schedule
        return temperature * 0.003;
    }
}

