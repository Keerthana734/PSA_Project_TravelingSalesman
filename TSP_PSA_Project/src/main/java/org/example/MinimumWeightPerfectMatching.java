package org.example;
import java.util.*;
import org.example.HierholzerAlgorithm;
import org.example.*;
public class MinimumWeightPerfectMatching {

    public static void minimumWeightMatching(List<Edge> MST, Graph G, Set<String> oddVert) {
        List<String> oddVertices = new ArrayList<>(oddVert);
       // Collections.shuffle(oddVertices);
        System.out.println(MST);
        while (!oddVertices.isEmpty()) {
            String v = oddVertices.remove(0);
            double length = Double.POSITIVE_INFINITY;
            String closest = "";
            for (String u : oddVertices) {
                if (!v.equals(u) && G.getWeight(v, u) < length) {
                    length = G.getWeight(v, u);
                    closest = u;
                }
            }
            MST.add(new Edge(v, closest, length));
            oddVertices.remove(closest);
        }
        System.out.println(MST);
        Graph H = new Graph();
//        Graph A = new Graph();
        for (Edge edge : MST) {
            H.addVertex(edge.getDestination());
            H.addVertex(edge.getSource());
//            A.addVertex(edge.getDestination());
//            A.addVertex(edge.getSource());
            H.addEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
//            A.addSingleEdge(edge.getSource(), edge.getDestination(), edge.getWeight());
        }
        H.printAdjacencyList();
//        A.printAdjacencyList();
        Map<String, Map<String, Double>> edgeWeight = G.getEdgeWeight();

//        for (Edge edge : MST) {
//            edgeWeight.putIfAbsent(edge.getSource(), new HashMap<>());
//            edgeWeight.get(edge.getSource()).put(edge.getDestination(), edge.getWeight());
//        }

        System.out.println(edgeWeight);
        List<String> tour = HierholzerAlgorithm.findEulerTour(H);
        System.out.println(tour);
        Hamiltonian.shortcutEulerianCycle(tour,edgeWeight);

        //EulerianCycleFinder e=new EulerianCycleFinder(MST);
       // e.findEulerianCycle();

       // EulerianCycleFinder.findEulerianCycle(H);
       // List<String> cycle=EulerianCycle.findEulerianCycle(H);
        /*if (cycle.size() != H.getNumVertices() + 1) {
            System.out.println("No Eulerian cycle exists in the graph.");
            return;
        }
        System.out.print("Eulerian cycle: ");
        for (String vertex : cycle) {
            System.out.print(vertex + " ");
        }
        System.out.println();*/

    }


}
/*public class MinimumWeightPerfectMatching {
    /*public static List<Edge> computeMinimumWeightPerfectMatching(Graph graph, Set<String> oddDegreeVertices) {
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

}*/
    /*public List<Edge> computeMinimumWeightPerfectMatching(Graph graph, Set<String> oddDegreeVertices) {
        // Create a list of unmatched vertices
        List<String> unmatchedVertices = new ArrayList<>(oddDegreeVertices);
        Map<String, String> matching = new HashMap<>();

        // Create a map of dual variables
        Map<String, Double> dualVariables = new HashMap<>();
        for (String vertex : oddDegreeVertices) {
            dualVariables.put(vertex, 0.0);
        }

        // Create a map of labels
        Map<String, String> labels = new HashMap<>();
        for (String vertex : oddDegreeVertices) {
            labels.put(vertex, vertex);
        }

        // Create a map of blossoms
        Map<String, Set<String>> blossoms = new HashMap<>();

        // Repeat until all vertices are matched
        while (!unmatchedVertices.isEmpty()) {
            // Choose an unmatched vertex
            String vertex = unmatchedVertices.get(0);

            // Initialize the queue and the visited set
            Queue<String> queue = new LinkedList<>();
            Set<String> visited = new HashSet<>();

            // Add the vertex to the queue and the visited set
            queue.add(vertex);
            visited.add(vertex);

            // Initialize the parent and the augmenting path
            Map<String, String> parent = new HashMap<>();
            List<String> augmentingPath = null;

            // Search for an augmenting path
            while (!queue.isEmpty() && augmentingPath == null) {
                // Dequeue a vertex from the queue
                String u = queue.poll();

                // Visit its neighbors
                for (Edge e : graph.getEdges(u)) {
                    String v = e.getDestination();

                    // Ignore edges that are not in O
                    if (!oddDegreeVertices.contains(v)) {
                        continue;
                    }

                    // Get the label of v
                    String w = labels.get(v);

                    // Check if v is unmatched
                    if (parent.get(w) == null) {
                        // Found an augmenting path
                        augmentingPath = new ArrayList<>();
                        augmentingPath.add(v);
                        augmentingPath.add(w);
                        String x = u;
                        while (!x.equals(vertex)) {
                            augmentingPath.add(x);
                            augmentingPath.add(parent.get(x));
                            x = labels.get(parent.get(x));
                        }
                        Collections.reverse(augmentingPath);
                        break;
                    }

                    // Check if v is visited
                    if (visited.contains(w)) {
                        continue;
                    }

                    // Compute the reduced cost of e
                    double reducedCost = e.getWeight() - dualVariables.get(u) - dualVariables.get(v);

                    // Check if e is tight
                    if (reducedCost == 0.0) {
                        // Label v with u
                        parent.put(w, u);
                        queue.add(labels.get(w));
                        visited.add(labels.get(w));
                    } else {
                        // Update the minimum reduced cost
                        double minimumReducedCost = Double.POSITIVE_INFINITY;
                        String minimumLabel = null;
                        for (String z : blossoms.getOrDefault(v, Collections.singleton(v))) {
                            double zReducedCost = reducedCost - dualVariables.get(z);
                            if (zReducedCost < minimumReducedCost) {
                                minimumReducedCost = zReducedCost;
                                minimumLabel = z;
                            }
                        }
                        for (String z : blossoms.getOrDefault(w, Collections.singleton(w))) {
                            double zReducedCost = reducedCost + dualVariables.get(z);
                            if (zReducedCost < minimumReducedCost) {
                                minimumReducedCost = zReducedCost;
                                minimumLabel = z;
                            }
                        }
// Update dual variables
                        for (String z : visited) {
                            if (blossoms.containsKey(z)) {
                                if (blossoms.get(z).contains(v)) {
                                    dualVariables.put(z, dualVariables.get(z) + minimumReducedCost);
                                } else if (blossoms.get(z).contains(w)) {
                                    dualVariables.put(z, dualVariables.get(z) - minimumReducedCost);
                                }
                            }
                        }
                        for (String z : unmatchedVertices) {
                            if (blossoms.containsKey(z)) {
                                if (blossoms.get(z).contains(v)) {
                                    dualVariables.put(z, dualVariables.get(z) + minimumReducedCost);
                                } else if (blossoms.get(z).contains(w)) {
                                    dualVariables.put(z, dualVariables.get(z) - minimumReducedCost);
                                }
                            }
                        }
                    }
                }

                // Create a blossom if an augmenting path was not found
                if (augmentingPath == null) {
                    // Find the minimum dual variable
                    double minimumDualVariable = Double.POSITIVE_INFINITY;
                    String minimumVertex = null;
                    for (String v : visited) {
                        if (blossoms.containsKey(v)) {
                            continue;
                        }
                        if (dualVariables.get(v) < minimumDualVariable) {
                            minimumDualVariable = dualVariables.get(v);
                            minimumVertex = v;
                        }
                    }
                    for (String v : unmatchedVertices) {
                        if (blossoms.containsKey(v)) {
                            continue;
                        }
                        if (dualVariables.get(v) < minimumDualVariable) {
                            minimumDualVariable = dualVariables.get(v);
                            minimumVertex = v;
                        }
                    }

                    // Create a blossom
                    Set<String> blossom = new HashSet<>();
                    blossom.add(minimumVertex);
                    while (!labels.get(minimumVertex).equals(minimumVertex)) {
                        minimumVertex = labels.get(minimumVertex);
                        blossom.add(minimumVertex);
                    }
                    blossoms.put(minimumVertex, blossom);

                    // Update dual variables
                    for (String v : blossom) {
                        if (blossom.contains(labels.get(v))) {
                            dualVariables.put(v, dualVariables.get(v) - minimumDualVariable);
                        } else {
                            dualVariables.put(v, dualVariables.get(v) + minimumDualVariable);
                        }
                    }

                    // Update the queue and the visited set
                    queue.clear();
                    visited.clear();
                    for (String v : unmatchedVertices) {
                        if (blossoms.containsKey(v)) {
                            continue;
                        }
                        visited.add(v);
                        if (dualVariables.get(v) == 0.0) {
                            queue.offer(v);
                        }
                    }
                    for (Map.Entry<String, Set<String>> entry : blossoms.entrySet()) {
                        visited.add(entry.getKey());
                        if (dualVariables.get(entry.getKey()) == 0.0) {
                            queue.offer(entry.getKey());
                        }
                    }
                }

                // Update the matching and the unmatched vertices
                if (augmentingPath != null) {
                    for (int i = 0; i < augmentingPath.size() - 1; i += 2) {
                        String v = augmentingPath.get(i);
                        String w = augmentingPath.get(i + 1);
                        matching.put(v, w);
                        matching.put(w, v);
                        unmatchedVertices.remove(v);
                        unmatchedVertices.remove(w);
                    }
                } else {
                    // No augmenting path was found, so we are done
                    break;
                }
            }

// Return the matching
            return matching;
        }
    }
}*/
