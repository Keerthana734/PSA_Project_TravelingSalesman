package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.example.Graph;

public class GraphFromCSV {
    public static Graph constructGraphFromCSV(String filename) {
        Graph graph = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            Map<String, Double> latitudes = new HashMap<>();
            Map<String, Double> longitudes = new HashMap<>();


            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");


                String crimeID = values[0];
                crimeID = crimeID.substring(crimeID.length() - 5);
                double longitude = Double.parseDouble(values[1]);
                double latitude = Double.parseDouble(values[2]);


                graph.addVertex(crimeID);


                latitudes.put(crimeID, latitude);
                longitudes.put(crimeID, longitude);
            }


            for (int i = 0; i < graph.getNumVertices(); i++) {
                String u = graph.getVertex(i);
                double latitude1 = latitudes.get(u);
                double longitude1 = longitudes.get(u);
                for (int j = i + 1; j < graph.getNumVertices(); j++) {
                    String v = graph.getVertex(j);
                    double latitude2 = latitudes.get(v);
                    double longitude2 = longitudes.get(v);
                    double distance = haversine(latitude1, longitude1, latitude2, longitude2);
                    graph.addEdge(u, v, distance);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }


    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c; // Distance in km
        return distance;
    }
}


