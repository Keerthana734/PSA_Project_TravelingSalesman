package org.example;
import org.example.GraphFromCSV;
import org.example.PrimsMST;
import org.example.OddDegreeVertices;
import org.example.MinimumWeightPerfectMatching;

import java.util.ArrayList;
import java.util.List;


public class ChristofidesAlgorithm {
public static void main(String[] args){
    Graph graph = GraphFromCSV.constructGraphFromCSV("Book2.csv");
    PrimsMST mst=new PrimsMST();
    Graph g = new Graph();
    g =  MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)), graph, OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
    HierholzerAlgorithm e = new HierholzerAlgorithm();
    System.out.println(e.findEulerTour(g));

}

}
