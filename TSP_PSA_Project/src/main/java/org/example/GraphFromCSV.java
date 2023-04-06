package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GraphFromCSV {
    public static Graph constructGraphFromCSV(String filename) {
        Graph graph = new Graph();

        try (BufferedReader br = new BufferedReader(new FileReader())) {
            String line;
            Map<Integer, Double> latitudes = new HashMap<>();
            Map<Integer, Double> longitudes = new HashMap<>();

            // Read each line of the CSV file
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                // Parse the crime ID, longitude, and latitude from the line
                int crimeID = Integer.parseInt(values[0]);
                double longitude = Double.parseDouble(values[1]);
                double latitude = Double.parseDouble(values[2]);

                // Add the crime ID as a vertex in the graph
                graph.addVertex(crimeID);

                // Store the longitude and latitude for later use
                latitudes.put(crimeID, latitude);
                longitudes.put(crimeID, longitude);
            }

            // Compute the edge weights based on the distances between the latitude and longitude points
            for (int i = 0; i < graph.getNumVertices(); i++) {
                int u = graph.getVertex(i);
                double latitude1 = latitudes.get(u);
                double longitude1 = longitudes.get(u);
                for (int j = i + 1; j < graph.getNumVertices(); j++) {
                    int v = graph.getVertex(j);
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

    // Compute the distance between two latitude and longitude points using the Haversine formula
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
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

    }
}