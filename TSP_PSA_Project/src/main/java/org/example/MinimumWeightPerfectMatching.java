package org.example;
import java.util.*;


public class MinimumWeightPerfectMatching {
    public static List<Edge> computeMinimumWeightPerfectMatching(Graph graph, Set<String> oddDegreeVertices) {
        int n = oddDegreeVertices.size();
        double[][] costMatrix = new double[n][n];
        Map<String, Integer> indexMap = new HashMap<>();
        int i = 0;

        // Create cost matrix and index map for vertices in O
        for (String vertex : oddDegreeVertices) {
            indexMap.put(vertex, i);
            int j = 0;
            for (Edge e : graph.getEdges(vertex)) {
                if (oddDegreeVertices.contains(e.getDestination())) {
                    int k = indexMap.get(e.getDestination()); // add null check here
                    costMatrix[i][k] = e.getWeight() ; // update to handle fractional weights
                }
                j++;
            }
            i++;
        }


        int[] matching = new int[n];
        Arrays.fill(matching, -1);

        // Run Hungarian algorithm
        for (int k = 0; k < n; k++) {
            boolean[] rowVisited = new boolean[n];
            boolean[] colVisited = new boolean[n];
            int u = k;
            while (true) {
                double min = Double.MAX_VALUE;
                int v = -1;
                for (int j = 0; j < n; j++) {
                    if (!rowVisited[u] && !colVisited[j] && costMatrix[u][j] < min) {
                        min = costMatrix[u][j];
                        v = j;
                    }
                }
                if (v == -1) {
                    break;
                }
                colVisited[v] = true;
                rowVisited[u] = true;
                if (matching[v] == -1) {
                    while (true) {
                        int prev_u = u;
                        u = matching[prev_u];
                        matching[prev_u] = v;
                        v = prev_u;
                        if (v == k) {
                            break;
                        }
                    }
                    matching[u] = v;
                    break;
                } else {
                    u = matching[v];
                }
            }
        }

        // Construct minimum weight perfect matching edges
        List<Edge> matchingEdges = new ArrayList<>();
        for (int j = 0; j < n; j++) {
            if (matching[j] != -1) {
                String u = getVertexFromIndex(oddDegreeVertices, j);
                String v = getVertexFromIndex(oddDegreeVertices, matching[j]);
                Edge e = new Edge(u, v, costMatrix[j][matching[j]]);
                matchingEdges.add(e);
            }
        }

        return matchingEdges;
    }

    private static String getVertexFromIndex(Set<String> oddDegreeVertices, int index) {
        int i = 0;
        for (String vertex : oddDegreeVertices) {
            if (i == index) {
                return vertex;
            }
            i++;
        }
        return null;
    }

}
