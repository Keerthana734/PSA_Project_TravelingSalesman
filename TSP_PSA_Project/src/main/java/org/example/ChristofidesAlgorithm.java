package org.example;
import org.example.GraphFromCSV;
import org.example.PrimsMST;
import org.example.OddDegreeVertices;
import org.example.MinimumWeightPerfectMatching;
import java.util.ArrayList;
import java.util.List;




public class ChristofidesAlgorithm {
public static void main(String[] args){
    Graph graph = GraphFromCSV.constructGraphFromCSV("crimeSample.csv");
    PrimsMST mst=new PrimsMST();

    //System.out.println(mst.primMST(graph));
    //System.out.println(GraphFromCSV.haversine(51.465752,-0.173787,51.465515,-0.192743));
    //System.out.println(OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
 //   System.out.println(MinimumWeightPerfectMatching.computeMinimumWeightPerfectMatching(graph,OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph))));
  //  (MinimumWeightPerfectMatching.PerfectMatching(graph)).printAdjacencyList();

    Graph g = new Graph();
    g =  MinimumWeightPerfectMatching.minimumWeightMatching((mst.primMST(graph)), graph, OddDegreeVertices.getOddDegreeVertices(mst.primMST(graph)));
    HierholzerAlgorithm e = new HierholzerAlgorithm();
    System.out.println(e.findEulerTour(g));
//    e.findHamiltonCycle(g);


}
}
