package org.example;

import java.util.*;

//public class TSPGeneticAlgorithm {
//
//    private List<String> cycle;
//    private Map<String, Map<String, Double>> edgeWeight;
//
//    public TSPGeneticAlgorithm(List<String> cycle, Map<String, Map<String, Double>> edgeWeight) {
//        this.cycle = cycle;
//        this.edgeWeight = edgeWeight;
//    }
//
//    public List<String> solve(int populationSize, int numGenerations) {
//        List<List<String>> population = generatePopulation(populationSize);
//        for (int i = 0; i < numGenerations; i++) {
//            List<List<String>> newPopulation = new ArrayList<>();
//            while (newPopulation.size() < populationSize) {
//                List<String> parent1 = selectParent(population);
//                List<String> parent2 = selectParent(population);
//                List<String> child = crossover(parent1, parent2);
//                mutate(child);
//                newPopulation.add(child);
//            }
//            population = newPopulation;
//        }
//        return getBestSolution(population);
//    }
//
//    private List<List<String>> generatePopulation(int populationSize) {
//        List<List<String>> population = new ArrayList<>();
//        for (int i = 0; i < populationSize; i++) {
//            List<String> candidate = new ArrayList<>(cycle);
//            Collections.shuffle(candidate);
//            population.add(candidate);
//        }
//        return population;
//    }
//
//    private List<String> selectParent(List<List<String>> population) {
//        double totalFitness = 0.0;
//        List<Double> fitnesses = new ArrayList<>();
//        for (List<String> candidate : population) {
//            double fitness = evaluateFitness(candidate);
//            fitnesses.add(fitness);
//            totalFitness += fitness;
//        }
//        double randomFitness = Math.random() * totalFitness;
//        double cumulativeFitness = 0.0;
//        for (int i = 0; i < population.size(); i++) {
//            cumulativeFitness += fitnesses.get(i);
//            if (cumulativeFitness >= randomFitness) {
//                return population.get(i);
//            }
//        }
//        return null;
//    }
//
//    private List<String> crossover(List<String> parent1, List<String> parent2) {
//        int numCities = cycle.size();
//        int start = (int) (Math.random() * numCities);
//        int end = (int) (Math.random() * numCities);
//        if (start > end) {
//            int temp = start;
//            start = end;
//            end = temp;
//        }
//        List<String> child = new ArrayList<>(cycle);
//        Set<String> visited = new HashSet<>();
//        for (int i = start; i <end; i++) {
//            String city = parent1.get(i);
//            child.set(i, city);
//            visited.add(city);
//        }
//        int j = 0;
//        for (int i = 0; i < numCities && j < child.size(); i++) {
//            if (j == start) {
//                j = end + 1;
//            }
//            String city = parent2.get(i);
//            if (!visited.contains(city)) {
//                child.set(j++, city);
//                if (j == start) {
//                    j = end + 1;
//                }
//            }
//        }
//        return child;
//    }
//
//    private void mutate(List<String> candidate) {
//        int numCities = cycle.size();
//        for (int i = 0; i < numCities; i++) {
//            if (Math.random() < 0.01) {
//                int j = (int) (Math.random() * numCities);
//                Collections.swap(candidate, i, j);
//            }
//        }
//    }
//
//    public double evaluateFitness(List<String> candidate) {
//        double fitness = 0.0;
//        double distance = 0.0;
//        String currentCity = candidate.get(0);
//        for (int i = 1; i < candidate.size(); i++) {
//            String nextCity = candidate.get(i);
//            if (edgeWeight.containsKey(currentCity) && edgeWeight.get(currentCity).containsKey(nextCity)) {
//                distance += edgeWeight.get(currentCity).get(nextCity);
//            }
//            else if(currentCity.equals(nextCity))
//                distance += 0.0;
//            else {
//
//                throw new IllegalArgumentException("Invalid edge weight for cities: " + currentCity + ", " + nextCity);
//            }
//            currentCity = nextCity;
//        }
//        if (edgeWeight.containsKey(currentCity) && edgeWeight.get(currentCity).containsKey(candidate.get(0))) {
//            distance += edgeWeight.get(currentCity).get(candidate.get(0));
//        }
//
//        else {
//            distance += 0;
//          //  throw new IllegalArgumentException("Invalid edge weight for cities: " + currentCity + ", " + candidate.get(0));
//        }
//        return 1.0 / distance;
//    }
//
//
//    private List<String> getBestSolution(List<List<String>> population) {
//        double bestFitness = 0.0;
//        List<String> bestSolution = null;
//        for (List<String> candidate : population) {
//            double fitness = evaluateFitness(candidate);
//            if (fitness > bestFitness) {
//                bestFitness = fitness;
//                bestSolution = candidate;
//            }
//        }
//        return bestSolution;
//    }
//}
//
public class TSPGeneticAlgorithm {

    private List<String> cycle;
    private Map<String, Map<String, Double>> edgeWeight;

    public TSPGeneticAlgorithm(List<String> cycle, Map<String, Map<String, Double>> edgeWeight) {
        this.cycle = cycle;
        this.edgeWeight = edgeWeight;
    }

    public List<String> solve(int populationSize, int numGenerations) {
        List<List<String>> population = generatePopulation(populationSize);
        for (int i = 0; i < numGenerations; i++) {
            List<List<String>> newPopulation = new ArrayList<>();
            while (newPopulation.size() < populationSize) {
                List<String> parent1 = selectParent(population);
                List<String> parent2 = selectParent(population);
                List<String> child = crossover(parent1, parent2);
                mutate(child);
                newPopulation.add(child);
            }
            population = newPopulation;
        }
        return getBestSolution(population);
    }

    private List<List<String>> generatePopulation(int populationSize) {
        List<List<String>> population = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            List<String> candidate = new ArrayList<>(cycle);
            Collections.shuffle(candidate);
            population.add(candidate);
        }
        return population;
    }

    private List<String> selectParent(List<List<String>> population) {
        double totalFitness = 0.0;
        List<Double> fitnesses = new ArrayList<>();
        for (List<String> candidate : population) {
            double fitness = evaluateFitness(candidate);
            fitnesses.add(fitness);
            totalFitness += fitness;
        }
        double randomFitness = Math.random() * totalFitness;
        double cumulativeFitness = 0.0;
        for (int i = 0; i < population.size(); i++) {
            cumulativeFitness += fitnesses.get(i);
            if (cumulativeFitness >= randomFitness) {
                return population.get(i);
            }
        }
        return null;
    }

    private List<String> crossover(List<String> parent1, List<String> parent2) {
        int numCities = cycle.size();
        int start = (int) (Math.random() * numCities);
        int end = (int) (Math.random() * numCities);
        if (start > end) {
            int temp = start;
            start = end;
            end = temp;
        }
        List<String> child = new ArrayList<>(parent1);
        Set<String> visited = new HashSet<>();
        for (int i = start; i < end; i++) {
            String city = parent1.get(i);
            child.set(i, city);
            visited.add(city);
        }
        int j = start;
        for (int i = 0; i < numCities && j < child.size(); i++) {
            if (j == end) {
                j = 0;
            }
            String city = parent2.get(i);
            if (!visited.contains(city)) {
                child.set(j++, city);
                if (j == numCities) {
                    break;
                }
            }
        }
        return child;
    }

    private void mutate(List<String> candidate) {
        if (Math.random() < 0.01) {
            int index1 = (int) (Math.random() * candidate.size());
            int index2 = (int) (Math.random() * candidate.size());
            Collections.swap(candidate, index1, index2);
        }
    }

    public double evaluateFitness(List<String> candidate) {
        double fitness = 0.0;
        int numCities = cycle.size();
        for (int i = 0; i < numCities; i++) {
            String city1 = candidate.get(i);
            String city2 = candidate.get((i + 1) % numCities);
            if (edgeWeight.containsKey(city1) && edgeWeight.get(city1).containsKey(city2)) {
                fitness += edgeWeight.get(city1).get(city2);
            } else {
                // Handle null value, for example by skipping the calculation or setting a default value
            }
        }
        return 1 / fitness;
    }

    private List<String> getBestSolution(List<List<String>> population) {
        double bestFitness = Double.MAX_VALUE;
        List<String> bestSolution = null;
        for (List<String> candidate : population) {
            double fitness = evaluateFitness(candidate);
            if (fitness < bestFitness) {
                bestFitness = fitness;
                bestSolution = candidate;
            }
        }
        return bestSolution;
    }
}

