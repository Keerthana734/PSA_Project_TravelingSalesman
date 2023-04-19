package org.example;
import org.example.GraphFromCSV;
import org.example.PrimsMST;
import org.example.OddDegreeVertices;
import org.example.MinimumWeightPerfectMatching;

import java.util.List;
import java.util.Map;

import static org.example.SimulatedAnnealing.simulatedAnnealing;
import static org.example.ThreeOpt.threeOpt;
import static org.example.TwoOpt.twoOpt;


public class ChristofidesAlgorithm {
public static void main(String[] args){


    Graph graph = GraphFromCSV.constructGraphFromCSV("info6205.spring2023.teamproject.csv");
    double[][] gra=graph.convertToAdjacencyMatrix();
    PrimsMST mst=new PrimsMST();
    Graph H = MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
    // System.out.println(edgeWeight);
    Map<String, Map<String, Double>> edgeWeight = graph.getEdgeWeight();
    List<String> tour = HierholzerAlgorithm.findEulerTour(H);
    //System.out.println(tour);
    List<String> eulerTour = Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);

    List<String> hamiltonianCycle = Hamiltonian.shortcutEulerianCycle(eulerTour,edgeWeight);



    AntColonyOptimization ant=new AntColonyOptimization(gra);
    ant.startAntOptimization();

    List<String> twoOptRoute = twoOpt(hamiltonianCycle, edgeWeight);
    long startTime = System.currentTimeMillis();

    List<String> simulatedAnnealing = simulatedAnnealing(hamiltonianCycle,edgeWeight);
    long endTime = System.currentTimeMillis();
    long duration = endTime - startTime;
    System.out.println("Time :" + duration);
    List<String> thirdOptRoute = threeOpt(hamiltonianCycle,edgeWeight );



}

}
