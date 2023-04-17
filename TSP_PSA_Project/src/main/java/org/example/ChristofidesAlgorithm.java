package org.example;
import org.example.GraphFromCSV;
import org.example.PrimsMST;
import org.example.OddDegreeVertices;
import org.example.MinimumWeightPerfectMatching;

import java.util.List;


public class ChristofidesAlgorithm {
public static void main(String[] args){
    Graph graph = GraphFromCSV.constructGraphFromCSV("info6205.spring2023.teamproject.csv");
    PrimsMST mst=new PrimsMST();
    MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)),graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));








}

}
