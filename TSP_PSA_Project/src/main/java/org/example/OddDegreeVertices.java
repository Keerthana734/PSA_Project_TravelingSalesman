package org.example;
import java.io.*;
import java.util.*;

public class OddDegreeVertices {
    public static Set<String> getOddDegreeVertices(List<Edge> mst) {
        Set<String> oddDegreeVertices = new HashSet<>();
        Map<String, Integer> degreeMap = new HashMap<>();

        // Compute degree of each vertex in the MST
        for (Edge e : mst) {
            String u = e.getSource();
            String v = e.getDestination();

            degreeMap.put(u, degreeMap.getOrDefault(u, 0) + 1);
            degreeMap.put(v, degreeMap.getOrDefault(v, 0) + 1);
        }

        // Add vertices with odd degree to the set O
        for (String vertex : degreeMap.keySet()) {
            if (degreeMap.get(vertex) % 2 != 0) {
                oddDegreeVertices.add(vertex);
            }
        }

        return oddDegreeVertices;
    }

}
